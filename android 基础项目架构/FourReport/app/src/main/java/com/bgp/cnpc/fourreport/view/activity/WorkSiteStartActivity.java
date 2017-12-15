package com.bgp.cnpc.fourreport.view.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bgp.cnpc.fourreport.R;
import com.bgp.cnpc.fourreport.common.base.MySwipeBackActivity;

import butterknife.BindView;

/**
 * @author: liuheng.
 * date: 17-12-15.
 * description:
 */

public class WorkSiteStartActivity extends MySwipeBackActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @Override

    protected int getLayoutId() {
        return R.layout.fragment_worksite_start;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        initToolBar();
        initView();
    }

    private void initView() {
        toolbarTitle.setText("驻地出发");
    }

    private void initToolBar() {
        //app logo
//        toolbar.setLogo(R.mipmap.ic_launcher);
        //title
        toolbar.setTitle("");
        //sub title
        toolbar.setSubtitle("");

        //以上3个属性必须在setSupportActionBar(toolbar)之前调用
        setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishWithAnim();
    }
}
