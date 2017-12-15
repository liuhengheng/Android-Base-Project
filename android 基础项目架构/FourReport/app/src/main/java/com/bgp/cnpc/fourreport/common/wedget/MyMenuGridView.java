package com.bgp.cnpc.fourreport.common.wedget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by liuheng on 2016/5/6.
 */
public class MyMenuGridView extends GridView {

    public MyMenuGridView(Context context) {
        super(context);
    }

    public MyMenuGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyMenuGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
