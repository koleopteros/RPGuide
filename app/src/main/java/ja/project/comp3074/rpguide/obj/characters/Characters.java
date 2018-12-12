package ja.project.comp3074.rpguide.obj.characters;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import ja.project.comp3074.rpguide.obj.ListInterface;

@Entity(tableName="characters")
public class Characters implements ListInterface {
    @PrimaryKey
    private long id;
    @NonNull
    private long userID;
    @NonNull
    private String name;
    private String race;
    private String job;
    private int level;
    private int str;
    private int dex;
    private int intel;
    private int wis;
    private int cha;
    private int con;
    private String desc;

    private double rating;
    private int ratingCount;

    public Characters(long id, @NonNull long userID, @NonNull String name, String race, String job, int level, int str, int dex, int con, int intel, int wis, int cha, String desc, double rating, int ratingCount) {
        this.id = id;
        this.userID = userID;
        this.name = name;
        this.race = race;
        this.job = job;
        this.level = level;
        this.str = str;
        this.dex = dex;
        this.intel = intel;
        this.wis = wis;
        this.cha = cha;
        this.con = con;
        rating =rating;
        ratingCount=ratingCount;
        this.desc = desc;
    }
    //Constructor for non-DB source
    @Ignore
    public Characters(@NonNull long userID, @NonNull String name, String race, String job, int str, int dex, int con, int intel, int wis, int cha, String desc) {
        this.id = -1;
        this.userID = userID;
        this.race = race;
        this.job = job;
        this.level = 1;
        this.name = name;
        this.str = str;
        this.dex = dex;
        this.intel = intel;
        this.wis = wis;
        this.cha = cha;
        this.con = con;
        rating =0;
        ratingCount=0;
        this.desc = desc;
    }
    @Ignore
    public Characters(@NonNull long userID, @NonNull String name, String race, String job,int level, int str, int dex, int con, int intel, int wis, int cha, String desc) {
        this.id = -1;
        this.userID = userID;
        this.race = race;
        this.job = job;
        this.level = level;
        this.name = name;
        this.str = str;
        this.dex = dex;
        this.intel = intel;
        this.wis = wis;
        this.cha = cha;
        this.con = con;
        rating =0;
        ratingCount=0;
        this.desc = desc;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public long getUserID() {
        return userID;
    }

    public void setUserID(@NonNull long userID) {
        this.userID = userID;
    }

    @Override
    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public int getCon() {
        return con;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    @Ignore
    public void applyRating(double value){
        rating = (rating+value)/2;
        ++ratingCount;
    }

    @Ignore
    @Override
    public String getSubDetail() {
        return "Level : "+level+" "+race+" "+job;
    }
    @Ignore
    @Override
    public int getObjType() {
        return 1;
    }

}