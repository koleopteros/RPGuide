package ja.project.comp3074.rpguide.database.users;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import ja.project.comp3074.rpguide.obj.ListInterface;
import ja.project.comp3074.rpguide.obj.users.User;

public class UserRepository {
    private UserDao uDao;
    private List<User> allUsers;
    private LiveData<List<User>> allLiveUsers;

    UserRepository(Application app){
        UserDatabase db = UserDatabase.getDatabase(app);
        uDao = db.userDao();
        allUsers=uDao.getAllUsers();
        allLiveUsers = uDao.getLiveUsers();
    }
    public LiveData<List<User>>getLiveUsers(){return allLiveUsers;}
    public List<User>getAllUsers(){
        return allUsers;
    }
    public void insert(User user){
        new insertAsyncTask(uDao).execute(user);
    }
    private static class insertAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao dao;
        insertAsyncTask(UserDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(User... users) {
            dao.insert(users[0]);
            return null;
        }
    }
}
