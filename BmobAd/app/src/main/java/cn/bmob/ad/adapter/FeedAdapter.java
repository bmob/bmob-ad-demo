package cn.bmob.ad.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.bmob.ad.AdWebActivity;
import cn.bmob.ad.Config;
import cn.bmob.ad.R;
import cn.bmob.ad.bean.Feed;
import cn.bmob.ad.bean.Label;
import cn.bmob.ad.util.TimeUtils;

/**
 * Created on 17/11/15 11:50
 *
 * @author zhangchaozhou
 */

public class FeedAdapter extends BaseAdapter {

    private Context mContext;
    private List<Feed> mFeeds;


    public FeedAdapter(Context context, List<Feed> feeds) {
        mContext = context;
        mFeeds = feeds;
    }

    @Override
    public int getCount() {
        return mFeeds == null ? 0 : mFeeds.size();
    }

    @Override
    public Object getItem(int i) {
        return mFeeds == null ? null : mFeeds.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        View view;
        final Holder holder;

        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_feed, null);
            holder = new Holder();
            holder.mTvTitle = view.findViewById(R.id.tv_title);
            holder.mLlFeed = view.findViewById(R.id.ll_feed);
            holder.mIvSingle = view.findViewById(R.id.iv_single);
            holder.mLlImagesMulti = view.findViewById(R.id.ll_images_multi);
            holder.mTvSecond = view.findViewById(R.id.tv_seconds);
            holder.mFlVideo = view.findViewById(R.id.fl_video);
            holder.mLlLabels = view.findViewById(R.id.ll_labels);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (Holder) view.getTag();
        }

        final Feed feed = mFeeds.get(position);

        holder.mLlLabels.removeAllViews();
        holder.mLlImagesMulti.removeAllViews();


        holder.mTvTitle.setText(feed.getTitle().trim());
        List<Label> labels = feed.getLabels();
        List<String> images = feed.getImages();



        holder.mLlFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, AdWebActivity.class)
                        .putExtra(Config.INFO_TITLE, feed.getTitle().trim())
                        .putExtra(Config.INFO_URL, feed.getUrl()));
            }
        });



        if (feed.getSeconds() != null && feed.getSeconds() != 0) {
            //视频类型
            final String videoUrl = feed.getVideoUrl();
            holder.mFlVideo.setVisibility(View.VISIBLE);
            holder.mIvSingle.setVisibility(View.GONE);
            holder.mLlImagesMulti.setVisibility(View.GONE);
            holder.mTvTitle.setVisibility(View.GONE);
            holder.mTvSecond.setText(TimeUtils.formatLongToTimeStr(feed.getSeconds()*1000L));

        } else {
            holder.mFlVideo.setVisibility(View.GONE);
            holder.mTvTitle.setVisibility(View.VISIBLE);
            //图片类型
            if (images != null) {
                if (images.size()==1){
                    holder.mIvSingle.setVisibility(View.VISIBLE);
                    holder.mLlImagesMulti.setVisibility(View.GONE);
                    Glide.with(mContext).load(images.get(0)).thumbnail(0.1f).into(holder.mIvSingle);
                }else {
                    holder.mLlImagesMulti.setVisibility(View.VISIBLE);
                    holder.mIvSingle.setVisibility(View.GONE);
                    for (int i = 0; i < images.size(); i++) {
                        ImageView imageView = new ImageView(mContext);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        layoutParams.weight=1;
                        layoutParams.setMargins(2,0,2,0);
                        layoutParams.gravity = Gravity.CENTER_VERTICAL;
                        Glide.with(mContext).load(images.get(i)).thumbnail(0.1f).into(imageView);
                        holder.mLlImagesMulti.addView(imageView, layoutParams);
                    }
                }

            }
        }


        //标签
        if (labels != null) {
            for (int i = 0; i < labels.size(); i++) {
                TextView tv = new TextView(mContext);
                tv.setId(i);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.gravity = Gravity.CENTER_VERTICAL;
                layoutParams.setMargins(0, 0, 6, 0);

                tv.setText(labels.get(i).getText());
                GradientDrawable gd = new GradientDrawable();
                gd.setColor(ContextCompat.getColor(mContext, android.R.color.transparent));
                gd.setCornerRadius(15);


                try {
                    tv.setTextColor(Color.parseColor(labels.get(i).getColor()));
                    gd.setStroke(1, Color.parseColor(labels.get(i).getColor()));
                } catch (Exception e) {
                    tv.setTextColor(Color.parseColor("#" + labels.get(i).getColor()));
                    gd.setStroke(1, Color.parseColor("#" + labels.get(i).getColor()));
                }
                tv.setPadding(6, 0, 6, 0);
                tv.setBackgroundDrawable(gd);
                tv.setTextSize(12);
                holder.mLlLabels.addView(tv, layoutParams);
            }
        }


        //来源
        TextView tv = new TextView(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        layoutParams.setMargins(0, 0, 16, 0);
        tv.setText(feed.getSource());
        tv.setTextSize(12);
        tv.setPadding(16, 0, 16, 0);
        holder.mLlLabels.addView(tv, layoutParams);


        return view;
    }

    class Holder {
        TextView mTvTitle;
        LinearLayout mLlFeed;
        LinearLayout mLlImagesMulti;
        ImageView mIvSingle;
        FrameLayout mFlVideo;
        TextView mTvSecond;
        LinearLayout mLlLabels;
    }
}
