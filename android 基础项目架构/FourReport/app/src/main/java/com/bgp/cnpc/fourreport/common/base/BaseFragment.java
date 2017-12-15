package com.bgp.cnpc.fourreport.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.bgp.cnpc.fourreport.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liuheng on 2016/5/7.
 */
public abstract class BaseFragment extends Fragment {
    protected BaseActivity mActivity;
    private View mView;
    private Unbinder unbinder;

    //获取宿主Activity
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_base, container, false);
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (null != parent) {
            parent.removeView(mView);
        }

        addChildView(inflater);

        unbinder = ButterKnife.bind(this, mView);

        afterCreate(savedInstanceState);

        return mView;
    }

    /**
     * 添加子Fragment的布局文件
     *
     * @param inflater
     */
    private void addChildView(LayoutInflater inflater) {
        FrameLayout container = (FrameLayout) mView.findViewById(R.id.fl_fragment_child_container);
        View child = inflater.inflate(getLayoutId(), null);
        container.addView(child, 0);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BaseActivity) activity;
    }

    protected abstract int getLayoutId();

    protected abstract void afterCreate(Bundle savedInstanceState);

//    public void FragmentRemove(String tag) {
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction transaction = fm.beginTransaction();
//        for (int i = 0; i < ConstantUtil.tags.length; i++) {
//            if (ConstantUtil.tags[i] != tag) {
//                Fragment fragment = fm.findFragmentByTag(ConstantUtil.tags[i]);
//                transaction.remove(fragment);
//            }
//        }
//        transaction.commit();
//    }

    /**
     * 显示软键盘弹出
     *
     * @param editText
     */
    public void showSoftInputMode(Activity activity, EditText editText) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 隐藏软键盘
     */
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) mView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(mView.getApplicationWindowToken(), 0);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
