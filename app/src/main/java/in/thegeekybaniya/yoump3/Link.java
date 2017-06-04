package in.thegeekybaniya.yoump3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kabir on 04/06/2017.
 */

public class Link {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("length")
    @Expose
    private String length;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("error")
    @Expose
    public String error;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }




}
