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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.bmob.ad.AdWebActivity;
import cn.bmob.ad.Config;
import cn.bmob.ad.R;
import cn.bmob.ad.bean.Act;
import cn.bmob.ad.bean.Item;
import cn.bmob.ad.bean.Label;

/**
 * Created on 17/12/11 17:02
 * @author zhangchaozhou
 */

public class FlowDeveloperAdapter extends BaseAdapter {

    private int TYPE_CONTENT = 0;
    private int TYPE_FLOW_AD = 1;

    private Context mContext;
    private List<Item> mItems;

    public FlowDeveloperAdapter(Context context, List<Item> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getAct() == null ? TYPE_CONTENT : TYPE_FLOW_AD;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        FlowAdHolder flowAdHolder = null;

        ContentHolder contentHolder = null;
        if (convertView == null) {
            if (getItemViewType(position)==TYPE_FLOW_AD){
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_flow_ad, null);
                flowAdHolder = new FlowAdHolder();
                flowAdHolder.mTvTitle = convertView.findViewById(R.id.tv_title);
                flowAdHolder.mLlFeed = convertView.findViewById(R.id.ll_feed);
                flowAdHolder.mIvSingle = convertView.findViewById(R.id.iv_single);
                flowAdHolder.mLlImagesMulti = convertView.findViewById(R.id.ll_images_multi);
                flowAdHolder.mLlLabels = convertView.findViewById(R.id.ll_labels);
                convertView.setTag(flowAdHolder);
            }else {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_flow_content, null);
                contentHolder = new ContentHolder();
                contentHolder.mTvContent = convertView.findViewById(R.id.tv_content);
                convertView.setTag(contentHolder);
            }

        } else {

            if (getItemViewType(position)==TYPE_FLOW_AD){
                flowAdHolder = (FlowAdHolder) convertView.getTag();
            }else {
                contentHolder = (ContentHolder) convertView.getTag();
            }
        }



        Item item = mItems.get(position);
        if (getItemViewType(position)==TYPE_FLOW_AD){

            flowAdHolder.mLlImagesMulti.removeAllViews();
            flowAdHolder.mLlLabels.removeAllViews();
            final Act act = item.getAct();
            List<String> images = act.getImages();

            flowAdHolder.mTvTitle.setText(act.getTitle().trim());

            if (images != null) {
                if (images.size()==1){
                    flowAdHolder.mIvSingle.setVisibility(View.VISIBLE);
                    flowAdHolder.mLlImagesMulti.setVisibility(View.GONE);
                    Glide.with(mContext).load(images.get(0)).thumbnail(0.1f).into(flowAdHolder.mIvSingle);
                }else {
                    flowAdHolder.mLlImagesMulti.removeAllViews();
                    flowAdHolder.mLlImagesMulti.setVisibility(View.VISIBLE);
                    flowAdHolder.mIvSingle.setVisibility(View.GONE);
                    for (int i = 0; i < images.size(); i++) {
                        ImageView imageView = new ImageView(mContext);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        layoutParams.weight=1;
                        layoutParams.setMargins(2,0,2,0);
                        layoutParams.gravity = Gravity.CENTER_VERTICAL;
                        Glide.with(mContext).load(images.get(i)).thumbnail(0.1f).into(imageView);
                        flowAdHolder.mLlImagesMulti.addView(imageView, layoutParams);
                    }
                }
            }



            List<Label> labels =act.getLabels();
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
                    flowAdHolder.mLlLabels.addView(tv, layoutParams);
                }
            }


            //来源
            TextView tv = new TextView(mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER_VERTICAL;
            layoutParams.setMargins(0, 0, 16, 0);
            tv.setText(act.getSource());
            tv.setTextSize(12);
            tv.setPadding(16, 0, 16, 0);
            flowAdHolder.mLlLabels.addView(tv, layoutParams);



            flowAdHolder.mLlFeed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    mContext.startActivity(new Intent(mContext, AdWebActivity.class)
                            .putExtra(Config.INFO_URL, act.getUrl()));
                }
            });
        }else {
            contentHolder.mTvContent.setText(item.getContent()+" - "+position);
        }
        return convertView;
    }


    class FlowAdHolder{
        private TextView mTvTitle;
        private LinearLayout mLlFeed;
        private ImageView mIvSingle;
        private LinearLayout mLlImagesMulti;
        private LinearLayout mLlLabels;
    }

    class ContentHolder{
        private TextView mTvContent;
    }


}
