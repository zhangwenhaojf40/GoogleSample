package sample.google.interpolator;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Scroller;

/***
 *  Create By ZWH  On  2018/12/19 0019
 **/
public class MyView extends LinearLayout {
    Scroller scroller;
    public MyView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context  context) {
        scroller = new Scroller(context);

    }
    //设置mScroller的滚动偏移量

    public void startScroll() {
        scroller.startScroll(0, 0,0, -1000,3000);
        invalidate();

    }



    @Override
    public void computeScroll() {
        //先判断mScroller滚动是否完成
        if (scroller.computeScrollOffset()) {
            //这里调用View的scrollTo()完成实际的滚动
            findViewById(R.id.tv).scrollTo(scroller.getCurrX(), scroller.getCurrY());
            //必须调用该方法，否则不一定能看到滚动效果
            postInvalidate();
        }
        super.computeScroll();
    }


    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


}
