package cn.com.cnpc.mainpage.seminar.po;

import java.io.Serializable;

/**
 * Created by dawn on 16/1/24.
 */
public class MenuEntity implements Serializable {

    private String title;
    private boolean isChecked;

    public MenuEntity(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
