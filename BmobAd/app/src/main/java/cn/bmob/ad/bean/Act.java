package cn.bmob.ad.bean;

import java.util.List;

/**
 * Created on 17/12/11 17:34
 */

public class Act {


    private String url;
    private List<String> images;
    private String title;
    private String descript;
    private List<Label> labels;
    private String source;



    public String getUrl() {
        return url;
    }

    public Act setUrl(String url) {
        this.url = url;
        return this;
    }

    public List<String> getImages() {
        return images;
    }

    public Act setImages(List<String> images) {
        this.images = images;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Act setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescript() {
        return descript;
    }

    public Act setDescript(String descript) {
        this.descript = descript;
        return this;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public Act setLabels(List<Label> labels) {
        this.labels = labels;
        return this;
    }

    public String getSource() {
        return source;
    }

    public Act setSource(String source) {
        this.source = source;
        return this;
    }
}
