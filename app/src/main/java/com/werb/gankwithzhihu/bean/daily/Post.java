package com.werb.gankwithzhihu.bean.daily;

import java.io.Serializable;

/**
 * Created by Werb on 2016/9/2.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 */
public class Post implements Serializable{

    private String appview;
    private String title;
    private String id;
    private String description;
    private Category category;

    public Category getCategory() {
        return category;
    }

    public String getAppview() {
        return appview;
    }

    public void setAppview(String appview) {
        this.appview = appview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Post{" +
                "appview='" + appview + '\'' +
                ", title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }
}
