package com.bgp.cnpc.fourreport.common.util;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bgp.cnpc.fourreport.R;
import com.bgp.cnpc.fourreport.common.wedget.WaveProgress;

import java.util.Random;

/**
 * Created by liuheng on 2017/4/17.
 * 加载对话框工具类
 */

public class ProgressDialogUtils {

    private static final long TIME_DISMISS_DEFAULT = 1500;
    private Dialog mDialog;
    private View mDialogContentView;
    private TextView tv_loadText;
    private ImageView iv_loadImage;
    private ProgressBar pb_loadProgress;
    private WaveProgress waveProgress;
    private Random mRandom;
//    Context context;
    public ProgressDialogUtils(Context context) {
        this(context, 0);
//        this.context = context;
    }

    @SuppressLint("InflateParams")
    public ProgressDialogUtils(Context context, int style) {
        mDialog = new Dialog(context, style);
        mDialogContentView = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        tv_loadText = (TextView) mDialogContentView.findViewById(R.id.tv_loading_text);
        iv_loadImage = (ImageView) mDialogContentView.findViewById(R.id.iv_load_image);
        pb_loadProgress = (ProgressBar) mDialogContentView.findViewById(R.id.pb_load_progress);
        waveProgress = (WaveProgress) mDialogContentView.findViewById(R.id.wave_progress_bar);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setContentView(mDialogContentView);
        Window window = mDialog.getWindow();
        mRandom = new Random();
        if (null != window) {
//            window.getAttributes().width = (int) (UIUtils.getScreenWidth() * 0.5);
//            window.getAttributes().height = (int) (UIUtils.getScreenHegith() * 0.2);
            window.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        }
    }

    /**
     * 显示加载进度条的ProgressDialog
     */
    public void showScheduleProgressDialog() {
        if (mDialog != null && !mDialog.isShowing()) {
            waveProgress.setVisibility(View.VISIBLE);
            waveProgress.setValue(0);
            pb_loadProgress.setVisibility(View.GONE);
            iv_loadImage.setVisibility(View.GONE);
            tv_loadText.setVisibility(View.GONE);
            mDialog.show();
        }
    }
    /**
     * 更新进度条的ProgressDialog
     */
    public void updateScheduleProgressDialog(int i){
        waveProgress.setValue(i);
    }

    /**
     * 显示加载的ProgressDialog
     */
    public void showProgressDialog() {
        if (mDialog != null && !mDialog.isShowing()) {
            pb_loadProgress.setVisibility(View.VISIBLE);
            iv_loadImage.setVisibility(View.GONE);
            tv_loadText.setVisibility(View.GONE);
            waveProgress.setVisibility(View.GONE);
            mDialog.show();
        }
    }

    /**
     * 显示有加载文字ProgressDialog，文字显示在ProgressDialog的下面
     *
     * @param text 需要显示的文字
     */
    public void showProgressDialogWithText(String text) {
        if (TextUtils.isEmpty(text)) {
            showProgressDialog();
        } else {
            if (mDialog != null && !mDialog.isShowing()) {
                pb_loadProgress.setVisibility(View.VISIBLE);
                iv_loadImage.setVisibility(View.GONE);
                tv_loadText.setText(text);
                tv_loadText.setVisibility(View.VISIBLE);
                waveProgress.setVisibility(View.GONE);
                mDialog.show();
            }
        }
    }

    /**
     * 显示加载成功的ProgressDialog，文字显示在ProgressDialog的下面
     *
     * @param message 加载成功需要显示的文字
     * @param time    需要显示的时间长度(以毫秒为单位)
     */
    public void showProgressSuccess(String message, long time) {
        if (TextUtils.isEmpty(message)) {
            return;
        }

        if (mDialog != null && !mDialog.isShowing()) {
            pb_loadProgress.setVisibility(View.GONE);
            waveProgress.setVisibility(View.GONE);
            iv_loadImage.setBackgroundResource(R.mipmap.ic_load_success_white);
            iv_loadImage.setVisibility(View.VISIBLE);
            tv_loadText.setText(message);
            tv_loadText.setVisibility(View.VISIBLE);
            mDialog.show();

            mDialogContentView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDialog.dismiss();
                }
            }, time);
        }
    }

    /**
     * 显示加载成功的ProgressDialog，文字显示在ProgressDialog的下面
     * ProgressDialog默认消失时间为1秒(1000毫秒)
     *
     * @param message 加载成功需要显示的文字
     */
    public void showProgressSuccess(String message) {
        showProgressSuccess(message, TIME_DISMISS_DEFAULT);
    }

    /**
     * 显示加载失败的ProgressDialog，文字显示在ProgressDialog的下面
     *
     * @param message 加载失败需要显示的文字
     * @param time    需要显示的时间长度(以毫秒为单位)
     */
    public void showProgressFail(String message, long time) {
        if (TextUtils.isEmpty(message)) {
            return;
        }

        if (mDialog != null && !mDialog.isShowing()) {
            pb_loadProgress.setVisibility(View.GONE);
            waveProgress.setVisibility(View.GONE);
            iv_loadImage.setBackgroundResource(R.mipmap.ic_load_fail_white);
            iv_loadImage.setVisibility(View.VISIBLE);
            tv_loadText.setText(message);
            tv_loadText.setVisibility(View.VISIBLE);
            mDialog.show();

            mDialogContentView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDialog.dismiss();
                }
            }, time);
        }
    }

    /**
     * 显示加载失败的ProgressDialog，文字显示在ProgressDialog的下面
     * ProgressDialog默认消失时间为1秒(1000毫秒)
     *
     * @param message 加载成功需要显示的文字
     */
    public void showProgressFail(String message) {
        showProgressFail(message, TIME_DISMISS_DEFAULT);
    }

    /**
     * 隐藏加载的ProgressDialog
     */
    public void dismissProgressDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
