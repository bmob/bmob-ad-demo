package cn.bmob.ad.bean;


/**
 * Created on 17/12/11 17:48
 */

public class Item {
    private Act mAct;//信息流广告
    private String content;//开发者自己的列表属性

    public Act getAct() {
        return mAct;
    }

    public Item setAct(Act act) {
        mAct = act;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Item setContent(String content) {
        this.content = content;
        return this;
    }
}
