package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;

import chapter.android.aweme.ss.com.homework.model.PullParser;
import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.widget.ChatRoom;
import chapter.android.aweme.ss.com.homework.widget.MyAdapter;

/**
 * 大作业:实现一个抖音消息页面,
 * 1、所需的data数据放在assets下面的data.xml这里，使用PullParser这个工具类进行xml解析即可
 * <p>如何读取assets目录下的资源，可以参考如下代码</p>
 * <pre class="prettyprint">
 *
 *         @Override
 *     protected void onCreate(@Nullable Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 *         setContentView(R.layout.activity_xml);
 *         //load data from assets/data.xml
 *         try {
 *             InputStream assetInput = getAssets().open("data.xml");
 *             List<Message> messages = PullParser.pull2xml(assetInput);
 *             for (Message message : messages) {
 *
 *             }
 *         } catch (Exception exception) {
 *             exception.printStackTrace();
 *         }
 *     }
 * </pre>
 * 2、所需UI资源已放在res/drawable-xxhdpi下面
 *
 * 3、作业中的会用到圆形的ImageView,可以参考 widget/CircleImageView.java
 */
public class Exercises3 extends AppCompatActivity {

    public static List<Message> messages;

    private static final String TAG = "hjinbing";

    private MyAdapter myAdapter;
    private RecyclerView recyclerView;

    private Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        try {
            InputStream assetInput = getAssets().open("data.xml");
            messages = PullParser.pull2xml(assetInput);
//            for (Message message : messages) {
//
//            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        // Log.d(TAG, messages.toString());

        recyclerView = findViewById(R.id.rv_list);
        LinearLayoutManager llmanager = new LinearLayoutManager(this);
        llmanager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llmanager);
        MyAdapter.ListItemClickListener listener = new MyAdapter.ListItemClickListener() {
            @Override
            public void onItemClicked(int clickedItemIndex) {
                // 跳转页面
                Intent intent = new Intent(Exercises3.this, ChatRoom.class);
                intent.putExtra("name", String.valueOf(clickedItemIndex + 1));
                startActivity(intent);
            }
        };
        Log.d(TAG, "into MyAdapter");
        myAdapter = new MyAdapter(messages.size(), messages, listener);
        recyclerView.setAdapter(myAdapter);
        Log.d(TAG, "recyclerView.setAdapter(myAdapter) finished");
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastPosition;
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (visibleItemCount > 0 && lastPosition >= totalItemCount - 1) {
                        Toast.makeText(Exercises3.this, "已滑动到底部!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    lastPosition = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                }
                // Log.d(TAG, "onScrolled: lastVisiblePosition=" + lastPosition);
            }
        });

    }

}
