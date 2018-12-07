package ja.project.comp3074.rpguide.database.characters;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import ja.project.comp3074.rpguide.obj.ListInterface;
import ja.project.comp3074.rpguide.obj.characters.Characters;

@Dao
public interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCharacter(Characters character);

    @Query("Select * from Characters")
    LiveData<List<Characters>> getAllCharacters();


    @Query("delete from Characters where id = :id")
    void deleteCharacter(int id);
}
