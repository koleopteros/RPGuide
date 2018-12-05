package ja.project.comp3074.rpguide.obj.users;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import ja.project.comp3074.rpguide.obj.ListInterface;

@Entity
public class User implements ListInterface{
    @PrimaryKey
    private long id;
    private String firstName;
    private String lastName;
    @NonNull
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

    @Override
    public String getName() {
        return firstName+" "+lastName;
    }

    @Override
    public String getSubDetail() {
        return "";
    }
}
