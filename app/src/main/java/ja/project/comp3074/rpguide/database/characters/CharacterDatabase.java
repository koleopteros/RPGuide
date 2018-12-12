package ja.project.comp3074.rpguide.database.characters;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import ja.project.comp3074.rpguide.obj.characters.Characters;

@Database(entities={Characters.class}, version =2)
public abstract class CharacterDatabase extends RoomDatabase{
    public abstract CharacterDao characterDao();
    private static CharacterDatabase instance;

    static synchronized CharacterDatabase getInstance(final Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),CharacterDatabase.class,"characters").fallbackToDestructiveMigration().addCallback(callback).build();
        }
        return instance;
    }
    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(instance).execute();
        }
    };

    private static class PopulateAsyncTask extends AsyncTask<Void,Void,Void> {
        private final CharacterDao dao;

        private PopulateAsyncTask(CharacterDatabase db){
            this.dao = db.characterDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            dao.insert(new Characters("Bob Dylan","Human","Dog Trainer","Loves dogs, hates cats."));
            dao.insert(new Characters("Iseria Arisphodel","Elf","Ranger","Known as the Knight of White Flowers, she has a calm and solemn personality."));
            return null;
        }
    }
}
