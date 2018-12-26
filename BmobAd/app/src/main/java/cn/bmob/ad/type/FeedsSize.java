package cn.bmob.ad.type;

/**
 * Created on 17/11/30 09:09
 */

public class FeedsSize {
    public static final FeedsSize BANNER_640_280 = new FeedsSize(640, 280, "640x280_mb");
    public static final FeedsSize BANNER_640_150 = new FeedsSize(640, 150, "640x150_mb");
    public static final FeedsSize BANNER_750_420 = new FeedsSize(750, 420, "750x420_as");
    public static final FeedsSize BANNER_750_180 = new FeedsSize(750, 180, "750x180_as");



    private int mWidth;
    private int mHeight;

    private String mSize;


    public FeedsSize(int width, int height, String size) {
        mWidth = width;
        mHeight = height;
        mSize = size;
    }



    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    public String getSize() {
        return mSize;
    }

    public void setSize(String size) {
        mSize = size;
    }
}
