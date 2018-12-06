package ja.project.comp3074.rpguide.database.users;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import ja.project.comp3074.rpguide.obj.users.User;

@Database(entities = (User.class), version=1)
public abstract class UserDatabase extends RoomDatabase{
    public abstract UserDao userDao();
    private static volatile UserDatabase instance;
    static UserDatabase getDatabase(final Context context){
        if(instance == null){
            synchronized (UserDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class,"shops").addCallback(callback).build();
                }
            }
        }
        return instance;
    }
    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new populateAsyncTask(instance.uDao()).execute();
        }
    };
    private static class populateAsyncTask extends AsyncTask<Void,Void,Void>{
        private final UserDao dao;
        populateAsyncTask(UserDao dao){
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            dao.insert(new User("Bob","Ross","friendly@trees.com","bobrosspaints4days"));
            dao.insert(new User("Aphrodite","Silva","afro@silvas.com","abcd1234"));
            dao.insert(new User("Charlie","Brown","charlie@brown.com","abcd1234"));
            dao.insert(new User("Dennis","D'Menace","dennis@menace.com","abcd1234"));
            dao.insert(new User("Elson","Elmo","elmo@takesovertheworld.com","abcd1234"));
            dao.insert(new User("Fig","Pies","ohfrancis@hotmale.com","abcd1234"));
            dao.insert(new User("General","Kenobi","general@reposti.com","abcd1234"));
            dao.insert(new User("Honey","Strudel","happy@hotfemale.com","abcd1234"));
            return null;
        }
    }
}
