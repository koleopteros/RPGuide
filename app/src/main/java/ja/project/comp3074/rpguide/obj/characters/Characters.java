package ja.project.comp3074.rpguide.obj.characters;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName="characters")
public class Characters {
    @PrimaryKey
    private long id;

    private long userID;
    @NonNull
    private String name;
    private int str;
    private int dex;
    private int intel;
    private int wis;
    private int cha;
    private int luk;

    private double rating;
    private int ratingCount;

    public Characters(long id, long userID, String name){
        this.id = id;
        this.name = name;
        this.userID = userID;
        str = 0;
        dex = 0;
        intel = 0;
        wis = 0;
        cha = 0;
        luk = 0;
        rating = 0;
        ratingCount =0;
    }

    public Characters(long id, long userID, @NonNull String name, int str, int dex, int intel, int wis, int cha, int luk) {
        this.id = id;
        this.userID = userID;
        this.name = name;
        this.str = str;
        this.dex = dex;
        this.intel = intel;
        this.wis = wis;
        this.cha = cha;
        this.luk = luk;
        rating =0;
        ratingCount=0;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStr() {
        return str;
    }
    public void setStr(int str) {
        this.str = str;
    }

    public int getDex() {
        return dex;
    }
    public void setDex(int dex) {
        this.dex = dex;
    }

    public int getIntel() {
        return intel;
    }
    public void setIntel(int intel) {
        this.intel = intel;
    }

    public int getWis() {
        return wis;
    }
    public void setWis(int wis) {
        this.wis = wis;
    }

    public int getCha() {
        return cha;
    }
    public void setCha(int cha) {
        this.cha = cha;
    }

    public int getLuk() {
        return luk;
    }
    public void setLuk(int luk) {
        this.luk = luk;
    }

    public double getRating(){
        return rating;
    }
    public int getRatingCount(){
        return ratingCount;
    }
    public void applyRating(double value){
        rating = (rating+value)/2;
        ++ratingCount;
    }
    public int incrementAttribute(int attribute){
        switch(attribute){
            case 0:
                return ++str;
            case 1:
                return ++dex;
            case 2:
                return ++intel;
            case 3:
                return ++wis;
            case 4:
                return ++cha;
            case 5:
                return ++luk;
            default:
                return -1;
        }
    }
    public int incrementBy(int attribute, int value){
        switch(attribute){
            case 0:
                str = str+value;
                return str;
            case 1:
                dex = dex+value;
                return dex;
            case 2:
                intel = intel+value;
                return intel;
            case 3:
                wis = wis+value;
                return wis;
            case 4:
                cha = cha+value;
                return cha;
            case 5:
                luk = luk+value;
                return luk;
            default:
                return -1;
        }
    }
    public int getModifier(int attribute) {
        switch(attribute){
            case 0:
                return (int)((str-10)/2);
            case 1:
                return (int)((dex-10)/2);
            case 2:
                return (int)((intel-10)/2);
            case 3:
                return (int)((wis-10)/2);
            case 4:
                return (int)((cha-10)/2);
            case 5:
                return (int)((luk-10)/2);
            default:
                return -1;
        }
    }
}
