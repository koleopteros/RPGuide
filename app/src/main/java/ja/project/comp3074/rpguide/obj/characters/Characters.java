package ja.project.comp3074.rpguide.obj.characters;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import ja.project.comp3074.rpguide.obj.ListInterface;

@Entity(tableName="characters")
public class Characters {
    @PrimaryKey
    @NonNull
    private String name;
    private String race;
    private String job;
    private String desc;

    private int rating;
    private int ratingCount;

    public Characters(String name, String race, String job, String desc, int rating, int ratingCount) {
        this.name = name;
        this.race = race;
        this.job = job;
        rating =rating;
        ratingCount=ratingCount;
        this.desc = desc;
    }
    //Constructor for non-DB source
    @Ignore
    public Characters(@NonNull String name, String race, String job, String desc) {
        this.race = race;
        this.job = job;
        this.name = name;
        rating =0;
        ratingCount=0;
        this.desc = desc;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    @Ignore
    public String getSubDetail() {
        return race+", "+job+", Ratings: "+rating+" out of "+ratingCount+" votes!";
    }
    @Ignore
    public int getObjType() {
        return 1;
    }

    @Ignore
    public void upvote(){
        rating++;
        ++ratingCount;
    }
    @Ignore
    public void downvote(){
        rating--;
        ++ratingCount;
    }

}