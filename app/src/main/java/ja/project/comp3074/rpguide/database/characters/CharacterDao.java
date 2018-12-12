package ja.project.comp3074.rpguide.database.characters;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Characters character);

    @Update
    void update(Characters character);

    @Query("SELECT * FROM characters ORDER BY name ASC")
    LiveData<List<Characters>> getAllCharacters();

    @Delete
    void delete(Characters character);
}
