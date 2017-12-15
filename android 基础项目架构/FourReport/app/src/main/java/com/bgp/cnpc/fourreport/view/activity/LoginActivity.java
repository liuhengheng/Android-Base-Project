package com.bgp.cnpc.fourreport.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bgp.cnpc.fourreport.R;
import com.bgp.cnpc.fourreport.common.base.BaseActivity;
import com.bgp.cnpc.fourreport.common.util.KeyBoardUtils;
import com.bgp.cnpc.fourreport.common.util.NetworkUtils;
import com.bgp.cnpc.fourreport.common.util.SharedPreferencesUtils;
import com.bgp.cnpc.fourreport.model.User;
import com.xw.repo.XEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author liuheng
 * @date 17-4-24
 */
public class LoginActivity extends BaseActivity {

    SharedPreferencesUtils sp;
    User user;
    @BindView(R.id.et_login_phone)
    XEditText etLoginPhone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        sp = new SharedPreferencesUtils(LoginActivity.this, "phone_number");
        user = sp.getObject("user", User.class);
        if (user != null) {
            etLoginPhone.setText(user.getPhone());
        }
    }

    @OnClick({R.id.btn_login})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                KeyBoardUtils.HideKeyboard(view);
                if (!NetworkUtils.isConnected()) {
                    showNoNetworkView();
                    return;
                }
                boolean flag = login();
                if (flag) {
                    sp.setObject("user", user);
                    startActivity(new Intent(this, MainActivity.class));
                }
                break;
            default:
                break;
        }
    }

    /**
     * 执行登录操作
      */

    private boolean login() {
        return true;
    }
}
