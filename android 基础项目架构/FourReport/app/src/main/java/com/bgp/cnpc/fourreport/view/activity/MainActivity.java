package com.bgp.cnpc.fourreport.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bgp.cnpc.fourreport.R;
import com.bgp.cnpc.fourreport.common.base.ActivityUtils;
import com.bgp.cnpc.fourreport.common.base.BaseActivity;
import com.bgp.cnpc.fourreport.common.util.T;
import com.bgp.cnpc.fourreport.view.fragment.WorkFragment;
import com.bgp.cnpc.fourreport.view.fragment.WorkFragment2;
import com.bgp.cnpc.fourreport.view.fragment.WorkFragment3;
import com.wx.ovalimageview.RoundImageView;

import butterknife.BindView;

/**
 * 主界面
 *
 * @author liuheng
 */
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String apkName = "FourReport.apk";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.coordinator)
    CoordinatorLayout mCoordinatorLayout;
    RoundImageView roundImageview;
    private TabLayout mTabLayout;
    private MyViewPagerAdapter mAdapter;
    private ViewPager mViewPager;
    /**
     * Tab 文字
     */
    private final String[] TAB_TITLES = new String[]{"工作", "生活", "其他"};
    /**
     * Tab 图片
     */
    private final int[] TAB_IMGS = new int[]{R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
    /**
     * Fragment 数组
     */
    private final Fragment[] TAB_FRAGMENTS = new Fragment[]{new WorkFragment(), new WorkFragment2(), new WorkFragment3()};
    /**
     * Tab 数目
     */
    private final int COUNT = TAB_TITLES.length;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        /*初始化ToolBar*/
        initToolBar();
        /*初始化侧滑菜单*/
        initSideslip();
        /**初始化Tab和ViewPager*/
        initTabAndViewPager();
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

    }

    private void initSideslip() {
        /*控制侧滑菜单*/
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //获取屏幕的宽高
                WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                //设置右面的布局位置  根据左面菜单的right作为右面布局的left   左面的right+屏幕的宽度（或者right的宽度这里是相等的）为右面布局的right
                mCoordinatorLayout.layout(mNavigationView.getRight(), 0,
                        mNavigationView.getRight() + display.getWidth(), display.getHeight());
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        toggle.syncState();
        //设置在setSupportActionBar( mToolbar );之前无效；
        //设置在DrawerLayout.setDrawerListener(ActionBarDrawerToggle);之前也无效；
//        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        /*设定NavigationView菜单的选择事件*/
        mNavigationView.setNavigationItemSelectedListener(this);
        View headerLayout = mNavigationView.inflateHeaderView(R.layout.nav_header_main);
        roundImageview = (RoundImageView) headerLayout.findViewById(R.id.round_imageview1);
        roundImageview.setImageResource(R.mipmap.image, Color.WHITE, 5, true);
//        tvUserName = (TextView) headerLayout.findViewById(R.id.tv_username);
//        tvEmail = (TextView) headerLayout.findViewById(R.id.tv_email);
//        tvName = (TextView) headerLayout.findViewById(R.id.tv_name);
//        try {
//            if (user != null) {
//                tvUserName.setText(user.getUsername());
//                tvEmail.setText(user.getEmail());
//                tvName.setText(user.getName());
////                Bitmap bitmap = getBitmap("http://dynamic-image.yesky.com/740x-/uploadImages/2016/340/14/7W489GT15S26.jpg");
////                roundImageview.setImageBitmap(bitmap, Color.parseColor("#ffffffff"), 5, true);
//                //            ImageLoader.loadImageWithPlaceHolder(this,roundImageview,u.getImage());
//                //            ImageLoader.loadImageWithPlaceHolder(this,roundImageview,"http://dynamic-image.yesky.com/740x-/uploadImages/2016/340/14/7W489GT15S26.jpg");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.e("exception", Log.getStackTraceString(e));
//        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_camera:
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:

                break;
            case R.id.nav_logout:
                ActivityUtils.removeAllActivity();
                startActivityWithAnim(this, new Intent(this, LoginActivity.class));
                break;
            case R.id.nav_send:
//                appUpdate();
                break;
            default:
                break;
        }
        /*关闭侧滑菜单*/
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            T.showLong("设置");
        } else if (id == R.id.action_share) {

        } else if (id == R.id.action_more) {

        }
        return super.onOptionsItemSelected(item);
    }

    private void initTabAndViewPager() {
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        setTabs(mTabLayout, this.getLayoutInflater(), TAB_TITLES, TAB_IMGS);

        mAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    /**
     * @description: 设置添加Tab
     */
    private void setTabs(TabLayout tabLayout, LayoutInflater inflater, String[] tabTitlees, int[] tabImgs) {
        for (int i = 0; i < tabImgs.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View view = inflater.inflate(R.layout.tab_custom, null);
            tab.setCustomView(view);
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_tab);
            tvTitle.setText(tabTitlees[i]);
            ImageView imgTab = (ImageView) view.findViewById(R.id.img_tab);
            imgTab.setImageResource(tabImgs[i]);
            tabLayout.addTab(tab);

        }
    }

    /**
     * @description: ViewPager 适配器
     */
    private class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TAB_FRAGMENTS[position];
        }

        @Override
        public int getCount() {
            return COUNT;
        }
    }
}
