package com.werb.gankwithzhihu.bean.daily;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Werb on 2016/9/2.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 */
public class Daily implements Serializable {

    private String image;
    private int type;
    private Post post;
    private String headline_genre;
    private List<HeadLine> list;

    public List<HeadLine> getList() {
        return list;
    }

    public String getHeadline_genre() {
        return headline_genre;
    }

    public void setHeadline_genre(String headline_genre) {
        this.headline_genre = headline_genre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Daily{" +
                "image='" + image + '\'' +
                ", type=" + type +
                ", post=" + post +
                ", headline_genre='" + headline_genre + '\'' +
                '}';
    }
}
