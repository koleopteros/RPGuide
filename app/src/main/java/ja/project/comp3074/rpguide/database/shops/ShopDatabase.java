package ja.project.comp3074.rpguide.database.shops;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import ja.project.comp3074.rpguide.database.helper.Converter;
import ja.project.comp3074.rpguide.obj.shops.Shops;

@Database(entities = Shops.class, version=1)
@TypeConverters({Converter.class})
public abstract class ShopDatabase extends RoomDatabase{
    public abstract ShopDao shopDao();
    private static volatile ShopDatabase instance;

    static ShopDatabase getDatabase(final Context context){
        if(instance == null){
            synchronized (ShopDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(), ShopDatabase.class,"shops").addCallback(callback).build();
                }
            }
        }
        return instance;
    }
    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new populateAsyncTask(instance.shopDao()).execute();
        }
    };
    private static class populateAsyncTask extends AsyncTask<Void,Void,Void>{
        private final ShopDao dao;
        populateAsyncTask(ShopDao dao){
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            String[] g1 = {"Game Shop", "Public","Open Playspace","Scheduled"};
            String[] g2 = {"Pub","Private"};

            Set<String> set1 = new HashSet<>(Arrays.asList(g1));
            Set<String> set2 = new HashSet<>(Arrays.asList(g2));
            dao.insert(new Shops("401 Games Downtown","518 Yonge St","Toronto","ON","M4X 1X9","416-599-6447","info@401games.ca","Game Store and Open Play Space", set1));
            dao.insert(new Shops("401 Games Vaughan","7700 Keele St Unit #6B","Concord","ON","L4K 2A1","905-660-4464","info@401games.ca", "Game Store and Open Play Space",set1));
            dao.insert(new Shops("The Sword & Board","1193 Bloor St W.","Toronto","ON","M6H 1N4","647-350-7529","theswordandboardtoronto@gmail.com","Game Store and Open Play Space",set1));
            dao.insert(new Shops("The Madison Pub 2nd Floor","14 Madison Ave.","Toronto","ON","","","","Pub, Organized group",set2));
            dao.insert(new Shops("BoardAgain Games","225 Sackville St Unit 1210","Toronto","ON","M5A 0B9","416-843-7344","","",set1));
            dao.insert(new Shops("Fusion Gaming","1473 Pembina Hwy","Winnipeg","MB","R3T 2C5","204-452-3121","help@fusiongamingonline.com","",set1));
            dao.insert(new Shops("Face to Face Games Toronto","2077a Danforth Ave","Toronto","ON","M4C 1J8", "416-690-6664","customerservice@facetofacegames.com","",set1));
            dao.insert(new Shops("Face to Face Games Montreal","4425 rue Wellington","Verdun","QC","H4G 1W6","514-769-7007","customerservice@facetofacegames.com","",set1));
            return null;
        }
    }
}
