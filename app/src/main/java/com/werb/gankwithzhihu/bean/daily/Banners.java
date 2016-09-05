package com.werb.gankwithzhihu.bean.daily;

import java.io.Serializable;

/**
 * Created by Werb on 2016/9/2.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 */
public class Banners implements Serializable {

    private String image;
    private int type;
    private Post post;

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
        return "Banners{" +
                "image='" + image + '\'' +
                ", type=" + type +
                ", post=" + post +
                '}';
    }
}
