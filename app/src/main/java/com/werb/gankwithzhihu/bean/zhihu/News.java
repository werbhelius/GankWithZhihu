package com.werb.gankwithzhihu.bean.zhihu;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Werb on 2016/8/19.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 */
public class News implements Serializable {

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private String type;
    private String id;
    private String[] js;
    private String[] css;
    private List<Recommender> recommenders;
    private Section section;

    public String getBody() {
        return body;
    }

    public String getImage_source() {
        return image_source;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getShare_url() {
        return share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String[] getJs() {
        return js;
    }

    public String[] getCss() {
        return css;
    }

    public List<Recommender> getRecommenders() {
        return recommenders;
    }

    public Section getSection() {
        return section;
    }

    public class Recommender implements Serializable {
        private String avatar;

        public String getAvatar() {
            return avatar;
        }

        @Override
        public String toString() {
            return "Recommender{" +
                    "avatar='" + avatar + '\'' +
                    '}';
        }
    }

    public class Section implements Serializable {
        private String thumbnail;
        private String id;
        private String name;

        public String getThumbnail() {
            return thumbnail;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Section{" +
                    "thumbnail='" + thumbnail + '\'' +
                    ", id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "News{" +
                "body='" + body + '\'' +
                ", image_source='" + image_source + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", share_url='" + share_url + '\'' +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", js=" + Arrays.toString(js) +
                ", css=" + Arrays.toString(css) +
                ", recommenders=" + recommenders +
                ", section=" + section +
                '}';
    }
}
