package ja.project.comp3074.rpguide.database.characters;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import ja.project.comp3074.rpguide.obj.ListInterface;
import ja.project.comp3074.rpguide.obj.characters.Characters;

public class CharacterViewModel extends AndroidViewModel {
    private CharacterRepository cRepo;
    private LiveData<List<ListInterface>> allCharacters;

    public CharacterViewModel(@NonNull Application application) {
        super(application);
        cRepo = new CharacterRepository(application);
        allCharacters = cRepo.getAllCharacters();
    }

    public LiveData<List<ListInterface>> getAllCharacters(){
        return allCharacters;
    }

    public void insert(Characters c){
        cRepo.insert(c);
    }
}
