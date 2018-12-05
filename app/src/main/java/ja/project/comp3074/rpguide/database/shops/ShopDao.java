package ja.project.comp3074.rpguide.database.shops;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import ja.project.comp3074.rpguide.obj.ListInterface;
import ja.project.comp3074.rpguide.obj.shops.Shops;

@Dao
public interface ShopDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertShop();

    @Query("select * from shops where id = :id")
    Shops getShop(long id);

    @Query("select * from shops where name like :name")
    List<ListInterface> getShopsLike(String name);

    @Query("select * from shops")
    LiveData<List<ListInterface>> getAllShops();
}