# 比目互动广告




## 一、对接价值 

1.引入更多大牌广告主知名广告主，对流量主的日活有要求，目前暂定要求日活1K以上。


## 二、产品形态 

产品路径： 

![](http://www.bmob.cn/uploads/attached/image/20181214/20181214102519_56722.png)


## 三、对接方式 

满足要求的流量主会收到一个广告链接，例如：

https://engine.easytui.com.cn/activities?appkey=8e60b3bebd65495caf3bfcd25e928125&adslotid=10691&mediatype=3

流量主可以以任何形式的广告入口进入该链接的广告页面，可以参考demo进行接入。

## 四、广告参考展现形式

参考地址：https://github.com/bmob/bmob-ad-demo

其中图片素材可以使用通用素材：

### 1、开屏引导页
```
mFsv.setTargetIntent(this,new Intent(mContext,MainActivity.class));
mFsv.setCountTime(6);
//图片素材地址、广告链接地址
mFsv.loadFeeds("图片素材地址","广告链接地址");
```
### 2、插屏弹窗
```
mFeedsInterstitialView  = new FeedsInterstitialView(this);
//图片地址、广告地址
mFeedsInterstitialView.loadFeeds("图片素材地址","广告链接地址");
```
### 3、单张横幅Banner
```
//图片地址、广告地址
mBannerTail.loadFeeds("图片素材地址","广告链接地址");
mBannerTop.loadFeeds("图片素材地址","广告链接地址");

```

### 4、多张轮播横幅Banner
```
List<String> image = new ArrayList<>();
image.add("图片素材地址");
image.add("图片素材地址");

List<String> urls = new ArrayList<>();
urls.add("广告链接地址");
urls.add("广告链接地址");
//图片地址、广告地址
mFeedsBannerMultiView.loadFeeds(image,urls);

```
### 5、浮标悬浮窗
```
//图片地址、广告地址
mFdv.loadFeeds("图片素材地址","广告链接地址");
```
![](https://github.com/bmob/bmob-ad-demo/blob/master/%E6%B5%AE%E6%A0%87%E5%B9%BF%E5%91%8A.png)
### 6、推送
```
FeedsPush feedsPush = new FeedsPush(mContext);
//广告链接地址
feedsPush.loadFeeds("广告链接地址");
```

## 五：计费

采用双重结算方式：
1、用户点击领券，获得收益。
2、用户进入广告主落地页，填写信息，获得收益。




## 六：收益提现

流量主提供Bmob应用的AppId，Bmob后台会每天更新收益情况，提现按照目前Bmob系统的提现规则：

![](https://github.com/bmob/bmob-ad-demo/blob/master/%E6%94%B6%E7%9B%8A.jpg?raw=true)

## 七、合作流程 

通过以下联系方式，告知应用名称、下载链接以及日活跃量。
目前该广告仅限APP接入，暂时不支持小程序广告接入，后续完善了会通知大家。

微信1：cloudid6666

微信2：13760289294

QQ1：3184067858

QQ2：1769379819

QQ群：534067293



