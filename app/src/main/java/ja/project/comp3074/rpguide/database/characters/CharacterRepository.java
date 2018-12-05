package ja.project.comp3074.rpguide.database.characters;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import ja.project.comp3074.rpguide.obj.ListInterface;
import ja.project.comp3074.rpguide.obj.characters.Characters;

public class CharacterRepository {
    private CharacterDao cDao;
    private LiveData<List<ListInterface>> allCharacters;

    CharacterRepository(Application app){
        CharacterDatabase db = CharacterDatabase.getDatabase(app);
        cDao = db.cDao();
        allCharacters = cDao.getAllCharacters();
    }

    public LiveData<List<ListInterface>> getAllCharacters(){
        return allCharacters;
    }

    public void insert(Characters c){
        new insertAsyncTask(cDao).execute(c);
    }

    private static class insertAsyncTask extends AsyncTask<Characters,Void,Void>{
        private CharacterDao dao;

        insertAsyncTask(CharacterDao cDao){
            dao = cDao;
        }

        @Override
        protected Void doInBackground(Characters... param) {
            dao.insertCharacter(param[0]);
            return null;
        }
    }
}
