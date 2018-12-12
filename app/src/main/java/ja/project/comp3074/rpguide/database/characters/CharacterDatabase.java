package ja.project.comp3074.rpguide.database.characters;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import ja.project.comp3074.rpguide.obj.characters.Characters;

@Database(entities=Characters.class, version =1)
public abstract class CharacterDatabase extends RoomDatabase{
    public abstract CharacterDao characterDao();
    private static volatile CharacterDatabase instance;

    static CharacterDatabase getDatabase(final Context context){
        if(instance == null){
            synchronized (CharacterDatabase.class){
                if(instance==null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),CharacterDatabase.class,"characters").addCallback(callback).build();
                }
            }
        }
        return instance;
    }
    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new populateAsyncTask(instance.characterDao()).execute();
        }
    };

    private static class populateAsyncTask extends AsyncTask<Void,Void,Void> {
        private final CharacterDao dao;

        populateAsyncTask(CharacterDao dao){
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            dao.insertCharacter(new Characters(7,"Alexa Lovelace","Human","Thief",8,15,10,12,13,14,"Loves dogs, hates cats."));
            dao.insertCharacter(new Characters(1,"Bob Dylan","Human","Dog Trainer",12,14,10,13,8,15,"Loves dogs, hates cats."));
            dao.insertCharacter(new Characters(2,"Cid, Thunder God","Human","Machinist",12,15,14,13,10,8,"Has many alter egos and incarnations.  Also loves to tinkering, machining and being awesome."));
            dao.insertCharacter(new Characters(2,"Delita Heiral","Human","Holy Knight",12,15,14,13,10,8,"Vengeful and machinator.  Witnessed his best friend's brother order the death of his innocent sister held hostage by an insurgency."));
            dao.insertCharacter(new Characters(3,"Edward Elric","Human","Alchemist",10,13,15,12,14,8,"A registered Alchemist best known for his skills.  Hates being called short.  Ironically, has a short fuse."));
            dao.insertCharacter(new Characters(4,"Ash Ketchup","Human","\"Pokemon Trainer\"",12,15,10,13,14,8,"Some kid from a town on a Pallet.  Claims his dream is to be a Pokemon Master"));
            dao.insertCharacter(new Characters(7,"Iseria Arisphodel","Elf","Ranger",8,15,13,12,14,10,"Iseria is an Elf unable to return to her ancestral home of Dun Blyraia due to some reason. Known as the Knight of White Flowers, she has a calm and solemn personality."));
            return null;
        }
    }
}
