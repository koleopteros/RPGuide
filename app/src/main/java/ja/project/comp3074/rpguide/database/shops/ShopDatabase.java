package ja.project.comp3074.rpguide.database.shops;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import ja.project.comp3074.rpguide.obj.shops.Shops;

@Database(entities = {Shops.class}, version=1)
public abstract class ShopDatabase extends RoomDatabase{
    private static volatile ShopDatabase instance;

    public abstract ShopDao shopDao();

    public static synchronized ShopDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),ShopDatabase.class, "shopdb").fallbackToDestructiveMigration().addCallback(callback).build();
        }
        return instance;
    }
    private static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(instance).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
    private static class PopulateAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private ShopDao sdao;
        private PopulateAsyncTask(ShopDatabase db){
            sdao = db.shopDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            sdao.insert(new Shops("The Sword & Board", "1193 Bloor St. W., Toronto, ON", "647-350-7529", "Open Public Playspace"));
            sdao.insert(new Shops("401 Games Downtown","518 Yonge St., Toronto, ON","416-599-6447", "Open Public Playspace"));
            sdao.insert(new Shops("The Madison Pub","14 Madison Ave., Toronto, ON", "", "Private"));
            return null;
        }
    }
}
