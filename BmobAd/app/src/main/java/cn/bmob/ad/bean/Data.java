package cn.bmob.ad.bean;

import java.util.List;

/**
 * Created on 17/11/15 10:01
 * @author zhangchaozhou
 */

public class Data {

    private Param params;
    private List<Feed> feeds;
    private List<String> classifications;
    private List<Column> columns;


    public Param getParams() {
        return params;
    }

    public void setParams(Param params) {
        this.params = params;
    }

    public List<Feed> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<Feed> feeds) {
        this.feeds = feeds;
    }

    public List<String> getClassifications() {
        return classifications;
    }

    public void setClassifications(List<String> classifications) {
        this.classifications = classifications;
    }


    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    private String baid;
    private String url;
    private String icon;
    private String title;
    private String descript;
    private String image;
    private List<String> images;


    public String getBaid() {
        return baid;
    }

    public void setBaid(String baid) {
        this.baid = baid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    private List<Label> labels;

    public List<Label> getLabels() {
        return labels;
    }

    public Data setLabels(List<Label> labels) {
        this.labels = labels;
        return this;
    }

    private String source;

    public String getSource() {
        return source;
    }

    public Data setSource(String source) {
        this.source = source;
        return this;
    }

    private List<Scheme> schemes;

    public List<Scheme> getSchemes() {
        return schemes;
    }

    public void setSchemes(List<Scheme> schemes) {
        this.schemes = schemes;
    }
}
