package ja.project.comp3074.rpguide.obj.shops;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Set;

import ja.project.comp3074.rpguide.obj.ListInterface;

@Entity(tableName = "shops")
public class Shops implements ListInterface{
    @PrimaryKey
    private long id;
    private String name;
    private String street;
    private String city;
    private String province;
    private String postalCode;
    private String phoneNumber;
    private String email;
    private String comment;
    private Set<String> tags;

    public Shops(long id, String name, String street, String city, String province, String postalCode, String phoneNumber, String email, String comment,Set<String> tags) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.comment = comment;
        this.tags = tags;
    }

    public Shops(String name, String street, String city, String province, String postalCode, String phoneNumber, String email, String comment, Set<String> tags) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.comment = comment;
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getComment() {
        return comment;
    }

    public Set<String> getTags() {
        return tags;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSubDetail() {
        return street+", "+city+", "+province+" "+postalCode;
    }
}
