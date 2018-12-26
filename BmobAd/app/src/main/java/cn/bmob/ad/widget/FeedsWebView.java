package cn.bmob.ad.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.Map;

import cn.bmob.ad.R;

/**
 * Created on 17/11/17 09:22
 *
 * @author zhangchaozhou
 */

public class FeedsWebView extends WebView {
    private ProgressBar mProgressBar;
    public static final String ERROR_PAGE = "file:///android_asset/error.html";


    public FeedsWebView(Context context) {
        super(context);

        WebSettings mWebSettings = getSettings();
        mWebSettings.setAllowFileAccess(true);
        mWebSettings.setGeolocationEnabled(true);
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 不使用缓存，只从网络获取数据
        mWebSettings.setSaveFormData(true);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        // 设置了window.open()才能用
        mWebSettings.setDomStorageEnabled(true);
        //支持javascript
        mWebSettings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        mWebSettings.setSupportZoom(true);
        // 设置出现缩放工具
        mWebSettings.setBuiltInZoomControls(false);
        //扩大比例的缩放
        mWebSettings.setUseWideViewPort(true);
        //自适应屏幕
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebSettings.setLoadWithOverviewMode(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mWebSettings.setMediaPlaybackRequiresUserGesture(false);
        }

        //解决cookie操作问题
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            CookieManager.getInstance().setAcceptThirdPartyCookies(this,true);
        }

        //解决图片不显示
        mWebSettings.setBlockNetworkImage(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }


        mWebSettings.setSupportMultipleWindows(false);

        setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
                Uri uri = Uri.parse(url);

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                getContext().startActivity(intent);
            }
        });
        // 长按点击事件
        setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final WebView.HitTestResult hitTestResult = getHitTestResult();
                // 如果是图片类型或者是带有图片链接的类型
                if (hitTestResult.getType() == WebView.HitTestResult.IMAGE_TYPE ||
                        hitTestResult.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
                    // 弹出保存图片的对话框
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("提示");
                    builder.setMessage("保存图片到本地");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String url = hitTestResult.getExtra();

                            Uri uri = Uri.parse(url);

                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                            getContext().startActivity(intent);

                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        // 自动dismiss
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                return true;
            }
        });


        mProgressBar = new ProgressBar(context, null,
                android.R.attr.progressBarStyleHorizontal);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 8);
        mProgressBar.setLayoutParams(layoutParams);

        Drawable drawable = context.getResources().getDrawable(R.mipmap.ic_launcher);
        mProgressBar.setProgressDrawable(drawable);
        addView(mProgressBar);
        setWebChromeClient(new FeedsWebChromeClient());
        setWebViewClient(mWebViewClient);
    }


    public class FeedsWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                mProgressBar.setVisibility(GONE);
            } else {
                if (mProgressBar.getVisibility() == GONE) {
                    mProgressBar.setVisibility(VISIBLE);
                }
                mProgressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if ("se:blank".equals(title) || "about:blank".equals(title)) {
                view.stopLoading();
                view.loadUrl(ERROR_PAGE);
            }
        }


    }


    @Override
    public void loadUrl(String url) {
        loadUrl(url, null);
    }


    @Override
    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        if (additionalHttpHeaders == null) {
            additionalHttpHeaders = new HashMap<>();
        }

        super.loadUrl(url, additionalHttpHeaders);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) mProgressBar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        mProgressBar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }


    @Override
    public boolean canScrollHorizontally(int direction) {
        return true;
    }




    private WebViewClient mWebViewClient = new WebViewClient() {


        public boolean handleScheme(String url) {
            if (url.startsWith("http") || url.startsWith("https")) {
                return false;
            } else {

                try{
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    getContext().startActivity(intent);
                    ((Activity) (getContext())).finish();
                }catch(Exception e){
                    //TODO :handle exception

                }
                return true;
            }
        }


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
            //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
            //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242

            if (handleScheme(url)) {
                return true;
            }

            return false;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if (handleScheme(request.getUrl().toString())) {
                return true;
            }
            return false;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            if (handleScheme(request.getUrl().toString())) {
                return null;
            }
            return super.shouldInterceptRequest(view, request);

        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            if (handleScheme(url)) {
                return null;
            }
            return super.shouldInterceptRequest(view, url);

        }


        @Override
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.proceed();
        }


        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            view.loadUrl(ERROR_PAGE);
        }


        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            view.loadUrl(ERROR_PAGE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            String js = "javascript: var v=document.getElementById('myVideo'); "
                    + "v.play(); ";
            view.loadUrl(js);
        }
    };
}
