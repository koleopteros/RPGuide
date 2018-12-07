package ja.project.comp3074.rpguide.database.shops;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import ja.project.comp3074.rpguide.obj.ListInterface;
import ja.project.comp3074.rpguide.obj.shops.Shops;

public class ShopViewModel extends AndroidViewModel{
    private ShopRepository shopRepo;
    private LiveData<List<Shops>> allShops;

    public ShopViewModel(@NonNull Application application) {
        super(application);
        shopRepo = new ShopRepository(application);
        allShops = shopRepo.getAllShops();
    }

    public LiveData<List<Shops>> getAllShops() {
        return allShops;
    }
    public void insert(Shops shop){
        shopRepo.insert(shop);
    }
}
