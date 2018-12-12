package ja.project.comp3074.rpguide.database.characters;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class CharacterRepository {
    private CharacterDao cDao;
    private LiveData<List<Characters>> allCharacters;

    public CharacterRepository(Application app){
        CharacterDatabase db = CharacterDatabase.getInstance(app);
        cDao = db.characterDao();
        allCharacters = cDao.getAllCharacters();
    }

    public LiveData<List<Characters>> getAllCharacters(){
        return allCharacters;
    }

    public void insert(Characters c){
        new InsertAsyncTask(cDao).execute(c);
    }
    public void update(Characters c){
        new UpdateAsyncTask(cDao).execute(c);
    }
    public void delete(Characters c){
        new DeleteAsyncTask(cDao).execute(c);
    }

    private static class InsertAsyncTask extends AsyncTask<Characters,Void,Void>{
        private CharacterDao dao;

        InsertAsyncTask(CharacterDao cDao){
            dao = cDao;
        }

        @Override
        protected Void doInBackground(Characters... param) {
            dao.insert(param[0]);
            return null;
        }
    }
    private static class UpdateAsyncTask extends AsyncTask<Characters,Void,Void>{
        private CharacterDao dao;

        UpdateAsyncTask(CharacterDao cdao){ dao = cdao; }

        @Override
        protected Void doInBackground(Characters... characters) {
            dao.update(characters[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<Characters,Void,Void>{
        private CharacterDao dao;

        DeleteAsyncTask(CharacterDao cdao){ dao = cdao; }

        @Override
        protected Void doInBackground(Characters... characters) {
            dao.delete(characters[0]);
            return null;
        }
    }
}
