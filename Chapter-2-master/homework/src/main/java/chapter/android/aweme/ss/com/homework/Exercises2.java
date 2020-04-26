package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {
    TextView tv_count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        int view_num = getAllChildViewCount(findViewById(R.id.linear_view));
        tv_count = findViewById(R.id.tv_count);
        tv_count.setText("页面所有view个数为：" + String.valueOf(view_num));
    }

    public int getAllChildViewCount(View view) {
        int viewCount = 0;
        if(view == null){
            return 0;
        }
        if(view instanceof ViewGroup){
            for (int i = 0; i < ((ViewGroup)view).getChildCount(); i++) {
                View view1 = ((ViewGroup)view).getChildAt(i);
                if(view1 instanceof ViewGroup)
                    viewCount += getAllChildViewCount(view1);
                else
                    viewCount++;
            }
        }
        return viewCount;
    }
}
