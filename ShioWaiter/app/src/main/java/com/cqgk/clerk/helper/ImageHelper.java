package com.cqgk.clerk.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.cqgk.clerk.R;
import com.cqgk.clerk.base.Basic;
import com.cqgk.clerk.http.RequestHelper;
import com.cqgk.clerk.http.UrlApi;
import com.cqgk.clerk.utils.CheckUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.xutils.image.ImageOptions;

import java.io.File;

/**
 * 远程图片加载
 * 1.自带款存机制根据url标识
 *
 * @author duke
 */
public class ImageHelper extends Basic {

    private static ImageHelper helper = new ImageHelper();

    public static ImageHelper getInstance() {
        return helper;
    }

    public void init() {
    }

    private static int default_zhan_img = R.drawable.ic_launcher;
    private ImageOptions imageOptions;
    private DisplayImageOptions options;
    private ImageLoaderConfiguration imageLoaderConfiguration;


    public ImageHelper() {

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_launcher)
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .showImageOnFail(R.drawable.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }


    public <T extends View> void display(ImageView into, String picPath) {
        display(into, picPath, options, new imageLoaderLisener());
    }

    public <T extends View> void display(ImageView into, String picPath, @DrawableRes int drawableRes) {
        display(into, picPath, options);

    }

    public <T extends View> void display(ImageView into, String picPath, @NonNull DisplayImageOptions config) {
        display(into, picPath, config, new imageLoaderLisener());
    }

    public <T extends View> void display(ImageView into, String picPath, @Nullable SimpleImageLoadingListener loadCallBack) {
        display(into, picPath, options, loadCallBack);
    }

    public <T extends View> void display(ImageView into, String picPath, @Nullable DisplayImageOptions config,
                                         @Nullable SimpleImageLoadingListener loadCallBack) {

        if (!CheckUtils.isAvailable(picPath))
            return;

        //LogUtils.e(picPath);


        if (picPath.indexOf("http") > -1) {

        } else if (picPath.indexOf("sdcard") > -1) {

        } else {
            picPath = picPath + UrlApi.getApiUrl(picPath);
        }


        try {
            //LogUtil.e(picPath);
            ImageLoader.getInstance().displayImage(picPath, into, config, loadCallBack);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public <T extends View> void displayAvatar(ImageView into, String picPath) {
        display(into, picPath, default_zhan_img);
    }

    public <T extends View> void displayWeb(ImageView into, String picPath) {
        display(into, picPath, default_zhan_img);
    }

    public <T extends View> void displayZhan(ImageView into, String picPath) {
        display(into, picPath, default_zhan_img);
    }

    public <T extends View> void displayZhan(ImageView into, String picPath, int zhan) {
        display(into, picPath, zhan);
    }


    public <T extends View> void displayZhan(ImageView into, String picPath,
                                             @Nullable SimpleImageLoadingListener loadCallBack) {
        display(into, picPath, options, loadCallBack);
    }


    class imageLoaderLisener extends SimpleImageLoadingListener {
        private ImageView.ScaleType scaleType;
        @Override
        public void onLoadingStarted(String imageUri, View view) {
            super.onLoadingStarted(imageUri, view);
            if (view instanceof ImageView) {
                ImageView img = (ImageView) view;
                scaleType = img.getScaleType();
                img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }
        }

        @Override
        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
            super.onLoadingFailed(imageUri, view, failReason);
        }

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            super.onLoadingComplete(imageUri, view, loadedImage);
            if (view instanceof ImageView)
                ((ImageView) view).setScaleType(scaleType);
        }

        @Override
        public void onLoadingCancelled(String imageUri, View view) {
            super.onLoadingCancelled(imageUri, view);
        }

    }

    /**
     * 清空缓存
     */
    public void clearCache() {
        ImageLoader.getInstance().clearDiskCache();
        ImageLoader.getInstance().clearMemoryCache();
    }

    public Drawable bitmapToDrawble(Bitmap bitmap, Context mcontext) {
        Drawable drawable = new BitmapDrawable(mcontext.getResources(), bitmap);
        return drawable;
    }

    public <T extends View> void displayLocal(ImageView into, String picPath) {
        picPath = RequestHelper.LOCALIMG_PATH + picPath;
        if (new File(picPath).exists()) {
            String imageUrl = ImageDownloader.Scheme.FILE.wrap(picPath);
            display(into, imageUrl);
        }
    }


}
