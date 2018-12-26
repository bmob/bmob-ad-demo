package cn.bmob.ad.bean;

import java.util.List;

/**
 * Created on 17/11/14 17:26
 * @author zhangchaozhou
 */

public class Feed {
    private Integer weight;//权重
    private String classification;//所属导航的类别
    private List<String> images;//图片：一张为视频缩略图，大图；三张为图片缩略图，小图；
    private List<Label> labels;//标签：名称，颜色；
    private Long releaseTime;//发布时间
    private Long seconds;//视频长度：此字段为空或者长度大小为0，则images为图片缩略图，否则为视频缩略图；
    private String source;//信息来源
    private Integer status;//审核状态：0，未审核；1，审核通过；2，审核不通过；3，下架；
    private String title;//标题
    private String body;//详情富文本
    private String url;
    private String videoUrl;




    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public Long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Long getSeconds() {
        return seconds;
    }

    public void setSeconds(Long seconds) {
        this.seconds = seconds;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getVideoUrl() {
        return videoUrl;
    }

    public Feed setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }
}
