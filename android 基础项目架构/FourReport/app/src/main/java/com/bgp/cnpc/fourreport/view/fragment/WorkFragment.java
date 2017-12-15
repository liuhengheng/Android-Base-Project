package com.bgp.cnpc.fourreport.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bgp.cnpc.fourreport.R;
import com.bgp.cnpc.fourreport.common.base.BaseFragment;
import com.bgp.cnpc.fourreport.view.activity.SwipDemoActivity;
import com.bgp.cnpc.fourreport.view.activity.WorkSiteStartActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: liuheng.
 * date: 17-12-15.
 * description:
 */

public class WorkFragment extends BaseFragment {
    @BindView(R.id.btn_start_from_home)
    Button btnStartFromHome;
    @BindView(R.id.btn_arrive_worksite)
    Button btnArriveWorksite;
    @BindView(R.id.btn_return_from_worksite)
    Button btnReturnFromWorksite;
    @BindView(R.id.btn_arrive_home)
    Button btnArriveHome;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_work;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_start_from_home, R.id.btn_arrive_worksite, R.id.btn_return_from_worksite, R.id.btn_arrive_home})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_start_from_home:
                getHoldingActivity().startActivityWithAnim(getHoldingActivity()
                        , new Intent(getHoldingActivity(), WorkSiteStartActivity.class));

//                Drawable drawable = getResources().getDrawable(R.mipmap.right_green);
//                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                btnStartFromHome.setCompoundDrawables(null, null, drawable, null);
                break;
            case R.id.btn_arrive_worksite:
                getHoldingActivity().startActivityWithAnim(getHoldingActivity()
                        , new Intent(getHoldingActivity(), SwipDemoActivity.class));

                break;
            case R.id.btn_return_from_worksite:
                break;
            case R.id.btn_arrive_home:
                break;
            default:
                break;
        }
    }

}
