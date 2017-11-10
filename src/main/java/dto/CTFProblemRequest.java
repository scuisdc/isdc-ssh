package dto;

import java.util.Date;

/**
 * Created by WaterMelon on 2017/11/8.
 */
public class CTFProblemRequest {
    private int id;
    private String userAccessToken;
    private String title;
    private String data;
    private String flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getUserAccessToken() {
        return userAccessToken;
    }

    public void setUserAccessToken(String userAccessToken) {
        this.userAccessToken = userAccessToken;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String date) {
        this.data = date;
    }


}
