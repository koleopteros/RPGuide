package ja.project.comp3074.rpguide.database.users;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import ja.project.comp3074.rpguide.obj.users.User;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("select * from User")
    LiveData<List<User>> getAllUsers();

    @Query("select * from User where id = :id")
    User getUser(long id);

    @Query("delete * from User where id = :id")
    void deleteUser(long id);

}
