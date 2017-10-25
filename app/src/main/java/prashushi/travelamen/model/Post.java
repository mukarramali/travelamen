package prashushi.travelamen.model;

/**
 * Created by Dell User on 3/26/2017.
 */
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by Dell User on 1/4/2017.
 */
public class Post {
    @SerializedName("url")
    private String Url;
    @SerializedName("id")
    private String id;
    @SerializedName("description")
    private String description;
    @SerializedName("title")
    private String title;
    @SerializedName("subtitle")
    private String subtitle;
    @SerializedName("ratings")
    private int ratings;
    @SerializedName("object")
    private JSONObject object;


    public Post(String id){
        this.id=id;
    }

    public String getUrl(){
        return Url;
    }

    public void setUrl(String Url){
        this.Url=Url;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id=id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setSubitle(String subtitle) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public JSONObject getObject() {
        return object;
    }

    public void setObject(JSONObject object) {
        this.object = object;
    }
}
