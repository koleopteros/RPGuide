package ja.project.comp3074.rpguide.database.users;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import ja.project.comp3074.rpguide.obj.ListInterface;
import ja.project.comp3074.rpguide.obj.users.User;

public class UserViewModel extends AndroidViewModel{
    private UserRepository userRepo;
    private LiveData<List<ListInterface>> allUsers;

    public UserViewModel(@NonNull Application app){
        super(app);
        userRepo = new UserRepository(app);
        allUsers = userRepo.getAllUsers();
    }
    public LiveData<List<ListInterface>> getAllUsers(){
        return allUsers;
    }
    public void insert(User user){
        userRepo.insert(user);
    }
}
