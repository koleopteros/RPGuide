package ja.project.comp3074.rpguide.obj.users;

import android.arch.persistence.room.Ignore;
import android.support.annotation.NonNull;

public class User {

    private long id;
    private String firstName;
    private String lastName;

    private String email;
    private String passwd;

    public User(long id, String firstName, String lastName, String email, String passwd){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwd = passwd;
    }
    public User(String firstName, String lastName, String email, String passwd){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwd = passwd;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Ignore
    public String getName() {
        return firstName+" "+lastName;
    }

    @Ignore
    public String getSubDetail() {
        return "";
    }
   }
