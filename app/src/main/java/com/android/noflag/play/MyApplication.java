package com.android.noflag.play;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * description:
 * User: stridercheng
 * Date: 2015-10-06
 * Time: 13:19
 * FIXME
 */
public class MyApplication extends Application {
    public final static String appPath = Environment.getExternalStorageDirectory().getPath() + "/";
    public final static boolean debugMode = true;
    public final static String movieUrl = "http://apis.baidu.com/apistore/movie/cinema";
    public final static String sceneUrl = "http://apis.baidu.com/qunartravel/travellist/travellist";
    public final static String movieKey = "8a281740ed78c7868e1634e703ae7d5b";
    public final static String sceneKey = "8a281740ed78c7868e1634e703ae7d5b";

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        File cacheDir = StorageUtils.getOwnCacheDirectory(
                getApplicationContext(), "imageloader/Cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this)
                .memoryCacheExtraOptions(480, 800)
                        // max
                .discCacheExtraOptions(480, 800, Bitmap.CompressFormat.JPEG, 75, null)
                        // Can
                .threadPoolSize(3)
                        // 线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                        // You
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                        // 将保存的时候的URI名称用MD5
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCacheFileCount(100)
                        // 缓存的文件数量
                .discCache(new UnlimitedDiscCache(cacheDir))
                        // 自定义缓存路径
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(
                        new BaseImageDownloader(this, 5 * 1000, 30 * 1000)) // connectTimeout
                .build();// 开始构建
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
    }
}
