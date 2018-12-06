package ja.project.comp3074.rpguide.database.shops;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import ja.project.comp3074.rpguide.obj.ListInterface;
import ja.project.comp3074.rpguide.obj.shops.Shops;

public class ShopRepository {
    private ShopDao sDao;
    private LiveData<List<ListInterface>> allShops;
    ShopRepository(Application app){
        ShopDatabase db = ShopDatabase.getDatabase(app);
        sDao = db.shopDao();
        allShops = sDao.getAllShops();
    }
    public LiveData<List<ListInterface>> getAllShops(){
        return allShops;
    }
    public void insert(Shops shop){
        new insertAsyncTask(sDao).execute(shop);
    }

    private static class insertAsyncTask extends AsyncTask<Shops,Void,Void>{
        private ShopDao dao;
        insertAsyncTask(ShopDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Shops... shops) {
            dao.insertShop(shops[0]);
            return null;
        }
    }
}
