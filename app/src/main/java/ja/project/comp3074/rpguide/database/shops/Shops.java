package ja.project.comp3074.rpguide.database.shops;

public class Shops {
    private long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String tags;

    public Shops(String name, String address, String phoneNumber, String tags) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSubDetail() {
        return address+", "+phoneNumber;
    }
}
