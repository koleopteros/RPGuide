package ja.project.comp3074.rpguide.database.users;

import android.provider.BaseColumns;

public class UserContract{
    private UserContract(){}
    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COL_NAME_FNAME = "FIRST_NAME";
        public static final String COL_NAME_LNAME = "LAST_NAME";
        public static final String COL_NAME_PASSWD = "PASSWORD";
        public static final String COL_NAME_EMAIL = "EMAIL";

        public static final String COL_TYPE_TEXT = "TEXT";
    }
    public static final String SQL_CREATE_USERS =
            "create table " +UserEntry.TABLE_NAME +"("+
                    UserEntry._ID +" integer primary key, "+
                    UserEntry.COL_NAME_FNAME +" "+ UserEntry.COL_TYPE_TEXT+", "+
                    UserEntry.COL_NAME_LNAME +" "+UserEntry.COL_TYPE_TEXT+", "+
                    UserEntry.COL_NAME_EMAIL +" "+UserEntry.COL_TYPE_TEXT+", "+
                    UserEntry.COL_NAME_PASSWD+" "+ UserEntry.COL_TYPE_TEXT+")";
    public static final String SQL_DROP_USERS =
            "drop table if exists " + UserEntry.TABLE_NAME;
}
