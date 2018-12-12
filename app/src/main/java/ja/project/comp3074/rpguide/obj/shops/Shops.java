package ja.project.comp3074.rpguide.obj.shops;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Set;

import ja.project.comp3074.rpguide.obj.ListInterface;

@Entity(tableName = "shops")
public class Shops implements ListInterface{
    @PrimaryKey
    @NonNull
    private String name;
    private String street;
    private String city;
    private String province;
    private String postalCode;
    private String phoneNumber;
    private String email;
    private String comment;
    private Set<String> tags;

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

    @Override
    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Ignore
    @Override
    public String getSubDetail() {
        return street+", "+city+", "+province+" "+postalCode;
    }
    @Ignore
    @Override
    public int getObjType() {
        return 2;
    }
}
