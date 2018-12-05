package ja.project.comp3074.rpguide.obj.shops;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

import ja.project.comp3074.rpguide.obj.ListInterface;

@Entity(tableName = "shops")
public class Shops implements ListInterface{
    @PrimaryKey
    private long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    public Shops(long id, String name, String address, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Shops(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSubDetail() {
        return address;
    }
}
