package ja.project.comp3074.rpguide.database.shops;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ShopViewModel extends AndroidViewModel{
    private ShopRepository repo;
    private LiveData<List<Shops>> liveData;
    private List<Shops> data;

    public ShopViewModel(@NonNull Application application) {
        super(application);
        repo = new ShopRepository(application);
        liveData = repo.getLiveData();
    }

    public void insert(Shops shop){
        repo.insert(shop);
    }
    public void update(Shops shop) { repo.update(shop); }
    public void delete(Shops shop) { repo.delete(shop); }

    public LiveData<List<Shops>> getLiveData() {
        return liveData;
    }
    public List<Shops> getAllData() { return data; }
}
