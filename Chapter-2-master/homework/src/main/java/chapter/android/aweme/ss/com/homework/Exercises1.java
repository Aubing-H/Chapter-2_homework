package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import chapter.android.aweme.ss.com.homework.widget.MyDialog;
import chapter.android.aweme.ss.com.homework.MainActivity;

/**
 * 作业1：
 * Logcat在屏幕旋转的时候 #onStop() #onDestroy()会展示出来
 * 但我们的 mLifecycleDisplay 由于生命周期的原因(Tips:执行#onStop()之后，UI界面我们是看不到的)并没有展示
 * 在原有@see Exercises1 基础上如何补全它，让其跟logcat的展示一样?
 * <p>
 * Tips：思考用比Activity的生命周期要长的来存储？  （比如：application、static变量）
 */
public class Exercises1 extends AppCompatActivity {
    private static final String TAG = "hjinbing";

    private static final String ON_CREATE = "onCreate()";
    private static final String ON_START = "onStart()";
    private static final String ON_RESTORE = "onRestoreInstanceState()";
    private static final String ON_RESUME = "onResume()";
    private static final String ON_PAUSE = "onPause()";
    private static final String ON_SAVE = "onSaveInstanceState()";
    private static final String ON_STOP = "onStop()";
    private static final String ON_RESTART = "onRestart()";
    private static final String ON_DESTROY = "onDestroy()";
    private static final int max_num = 100; // 最大显示生命周期的条数

    private SharedPreferences.Editor editor = MainActivity.sp.edit();
    private TextView tv;

    public void gotoDialog(View view){
        startActivity(new Intent(this, MyDialog.class));
    }

    public void reset(View view){
        tv.setText("");
        editor.clear();
        editor.commit();
    }

    private void showOnText(){
        tv.setText("");
        for (int i = 0; i < max_num; i++) {
            String tp = String.valueOf(i + 1);
            if(MainActivity.sp.getString(tp, null) != null){
                tv.append(MainActivity.sp.getString(tp, null) + '\n');
            }
            else
                break;
        }
    }

    private void update(String state){
        for (int i = 0; i < max_num; i++) {
            String tp = String.valueOf(i + 1);
            if(MainActivity.sp.getString(tp, null) == null){
                editor.putString(tp, state);
                editor.commit();
                break;
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise1_main);
        tv = findViewById(R.id.tv_lifecycle);
        Log.d(TAG, ON_CREATE);
        update(ON_CREATE);
        showOnText();
    }

    protected void onStart(){
        super.onStart();
        Log.d(TAG, ON_START);
        update(ON_START);
        showOnText();
    }

    protected void onRestoreInstanceState(Bundle restoreState){
        super.onRestoreInstanceState(restoreState);
        Log.d(TAG, ON_RESTORE);
        update(ON_RESTORE);
        showOnText();
    }

    protected void onResume(){
        super.onResume();
        Log.d(TAG,ON_RESUME);
        update(ON_RESUME);
        showOnText();
    }

    protected void onPause(){
        super.onPause();
        Log.d(TAG, ON_PAUSE);
        update(ON_PAUSE);
        showOnText();
    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(TAG, ON_SAVE);
        update(ON_SAVE);
        showOnText();
    }

    protected void onStop(){
        super.onStop();
        Log.d(TAG, ON_STOP);
        update(ON_STOP);
        showOnText();
    }

    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, ON_RESTART);
        update(ON_RESTART);
        showOnText();
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, ON_DESTROY);
        update(ON_DESTROY);
        showOnText();
    }
}
