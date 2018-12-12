package ja.project.comp3074.rpguide.database.shops;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ShopRepository {
    private ShopDao sdao;
    private LiveData<List<Shops>> liveData;
    private List<Shops> data;
    public ShopRepository(Application app){
        ShopDatabase db = ShopDatabase.getInstance(app);
        sdao = db.shopDao();
        liveData = sdao.getLiveShops();
        data = sdao.getAllShops();
    }
    public void insert(Shops shop){
        new InsertAsyncTask(sdao).execute(shop);
    }
    public void update(Shops shop){ new UpdateAsyncTask(sdao).execute(shop);}
    public void delete(Shops shop){ new DeleteAsyncTask(sdao).execute(shop);}

    public LiveData<List<Shops>> getLiveData(){
        return liveData;
    }
    public List<Shops> getData(){ return data; }

    private static class InsertAsyncTask extends AsyncTask<Shops,Void,Void>{
        private ShopDao dao;
        InsertAsyncTask(ShopDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Shops... shops) {
            dao.insert(shops[0]);
            return null;
        }
    }
    private static class UpdateAsyncTask extends AsyncTask<Shops,Void,Void>{
        private ShopDao dao;
        UpdateAsyncTask(ShopDao dao){this.dao=dao;}

        @Override
        protected Void doInBackground(Shops... shops) {
            dao.update(shops[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<Shops,Void,Void>{
        private ShopDao dao;
        DeleteAsyncTask(ShopDao dao){this.dao=dao;}

        @Override
        protected Void doInBackground(Shops... shops) {
            dao.delete(shops[0]);
            return null;
        }
    }
}
