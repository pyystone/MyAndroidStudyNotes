package com.androidstudynotes.ui;

import android.view.View;
import android.widget.Button;

import com.felipecsl.gifimageview.library.GifImageView;

import com.androidstudynotes.BaseActivity;
import com.androidstudynotes.R;
import com.androidstudynotes.util.ApplicationUtil;
import com.androidstudynotes.util.FileUtil;
import com.androidstudynotes.util.HttpDownload;

/**
 * Created by pyysotne on 2015/08/21/0021.
 * email: pyystone@163.com
 * QQ: 862429936
 *
 * compile 'com.felipecsl:gifimageview:1.2.0'
 * https://github.com/felipecsl/GifImageView
 */
public class GifUIActivtiy extends BaseActivity implements View.OnClickListener {
    private static final String GIF_IMAGE_URL = "http://g.picphotos.baidu.com/album/s%3D1600%3Bq%3D90/sign=257d26da2cdda3cc0fe4bc2631d90270/f636afc379310a55dd5261b1b54543a982261004.jpg";
    private static final String IMAGE_URL = "http://img4.duitang.com/uploads/item/201212/12/20121212161025_YGFjR.gif";
    private static final String GIF_FILE_NAME = "gif.gif";

    private GifImageView gifImageView;
    private Button btnToggle;

    @Override
    protected void initUI() {
        gifImageView = (GifImageView) findViewById(R.id.givTest);
        btnToggle = (Button) findViewById(R.id.btnController);
        btnToggle.setOnClickListener(this);
//        refreshGifImage();
        new Thread(new Runnable() {
            @Override
            public void run() {
                downloadImage();
            }
        }).start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.gif_ui_activity;
    }

    public void downloadImage() {
        HttpDownload task = new HttpDownload();
        FileUtil.createFilePath(FileUtil.HOME_PHOTO_PATH);
        final int result = task.downFile(GIF_IMAGE_URL, FileUtil.HOME_PHOTO_PATH, GIF_FILE_NAME);
        if (result >= 0) {
            try {
                gifImageView.setBytes(FileUtil.getByte(FileUtil.HOME_PHOTO_PATH, GIF_FILE_NAME));
            } catch (Exception e) {
                e.printStackTrace();
            }
            gifImageView.startAnimation();
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ApplicationUtil.getInstance().toast(Integer.toString(result));
                }
            });
        }
    }

    @Override
    public void onClick(final View v) {
        if (v.equals(btnToggle)) {
            if (gifImageView.isAnimating())
                gifImageView.stopAnimation();
            else
                gifImageView.startAnimation();
        } else {
            gifImageView.clear();
        }
    }
}
