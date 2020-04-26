package chapter.android.aweme.ss.com.homework.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import chapter.android.aweme.ss.com.homework.R;
import chapter.android.aweme.ss.com.homework.model.Message;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{

    private static final String TAG = "hjinbing";

    private final ListItemClickListener listItemListener;

    private int numItems;
    private  List<Message> messages;

    private static int viewHolderCount;

    public MyAdapter(int numListItems, List<Message> msg, ListItemClickListener listener){
        numItems = numListItems;
        listItemListener = listener;
        messages = msg;
        viewHolderCount = 0;
        Log.d(TAG, "myAdapter initialized");
    }

    public MyHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        Log.d(TAG, "viewHolder creating");
        Context context = viewGroup.getContext();
        int layoutID = R.layout.im_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutID, viewGroup, shouldAttachToParentImmediately);
        MyHolder holder = new MyHolder(view);

        Log.d(TAG, "onCreateViewHolder: the viewHolderCount is " + viewHolderCount);
        viewHolderCount++;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        Log.d(TAG, "bind viewHolder: count on " + i);
        myHolder.bind(i, messages.get(i));
    }

    public int getItemCount(){
        return numItems;
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final CircleImageView circleView;
        private final ImageView robotNotice;
        private final TextView tvTitle;
        private final TextView tvDescription;
        private final TextView tvTime;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            circleView = itemView.findViewById(R.id.iv_avatar);
            robotNotice = itemView.findViewById(R.id.robot_notice);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            itemView.setOnClickListener(this);
        }

        public void bind(int para, Message message){
            //添加资源
            tvTitle.setText(message.getTitle());
            tvTime.setText(message.getTime());
            tvDescription.setText(message.getDescription());
            robotNotice.setImageResource(R.drawable.im_icon_notice_official);
            circleView.setImageResource(R.drawable.icon_girl);
            Log.d(TAG, "bind finished");
        }

        public void onClick(View view){
            if(listItemListener != null){
                listItemListener.onItemClicked(getAdapterPosition());
            }
        }
    }

    public interface ListItemClickListener{
        void onItemClicked(int clickedItemIndex);
    }
}
