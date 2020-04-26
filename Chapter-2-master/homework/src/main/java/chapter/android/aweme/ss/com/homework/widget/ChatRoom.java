package chapter.android.aweme.ss.com.homework.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import chapter.android.aweme.ss.com.homework.R;

public class ChatRoom extends AppCompatActivity {
    private TextView tv;
    protected void onCreate(Bundle saveInstance){
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_chatroom);
        tv = findViewById(R.id.tv_with_name);
        String number = getIntent().getStringExtra("name");
        tv.setText("这是第" + number + "个item");
    }
}
