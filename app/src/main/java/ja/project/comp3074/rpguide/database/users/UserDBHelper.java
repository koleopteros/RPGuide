package ja.project.comp3074.rpguide.database.users;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ja.project.comp3074.rpguide.obj.users.User;

public class UserDBHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "users.db";

    public UserDBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserContract.SQL_CREATE_USERS);
        addNewUser(db, new User("General","Kenobi","coolguy@jedi.temple","order66"));
        addNewUser(db, new User("Charlie","Brown","peanut@gallery.org","peanuts"));
        addNewUser(db, new User("Dennis","D'menace","den@men.io","wrecker"));
        addNewUser(db, new User("Lazy","Login","1","1"));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addNewUser(SQLiteDatabase db,User user){
        ContentValues values = new ContentValues();
        values.put(UserContract.UserEntry.COL_NAME_FNAME,user.getFirstName());
        values.put(UserContract.UserEntry.COL_NAME_LNAME,user.getLastName());
        values.put(UserContract.UserEntry.COL_NAME_EMAIL,user.getEmail());
        values.put(UserContract.UserEntry.COL_NAME_PASSWD,user.getPasswd());

        return db.insert(UserContract.UserEntry.TABLE_NAME,null,values);
    }
    public User authenticate(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                UserContract.UserEntry._ID,
                UserContract.UserEntry.COL_NAME_FNAME,
                UserContract.UserEntry.COL_NAME_LNAME,
                UserContract.UserEntry.COL_NAME_EMAIL
        };
        String selection = UserContract.UserEntry.COL_NAME_EMAIL+" = ? and "+UserContract.UserEntry.COL_NAME_PASSWD+" = ? ";
        String[] selectionArgs = {email,password};

        Cursor c = db.query(UserContract.UserEntry.TABLE_NAME, projection,
                selection,
                selectionArgs, null,null,null);
        if(c.moveToFirst()) {
            User user = new User(
                    c.getLong(c.getColumnIndexOrThrow(UserContract.UserEntry._ID)),
                    c.getString(c.getColumnIndexOrThrow(UserContract.UserEntry.COL_NAME_FNAME)),
                    c.getString(c.getColumnIndexOrThrow(UserContract.UserEntry.COL_NAME_LNAME)),
                    c.getString(c.getColumnIndexOrThrow(UserContract.UserEntry.COL_NAME_EMAIL)),
                    ""
            );
            c.close();
            return user;
        }
        c.close();
        return null;
    }
}
