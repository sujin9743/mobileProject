package jsj.mjc.mobileproject;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {

    private ArrayList<ScheduleItem> scheduleList;

    public class ScheduleViewHolder extends RecyclerView.ViewHolder {
        ImageView emoticon;
        TextView scheduleTxt, scheduleTime;

        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            emoticon = itemView.findViewById(R.id.emoticon);
            scheduleTxt = itemView.findViewById(R.id.scheduleTxt);
            scheduleTime = itemView.findViewById(R.id.scheduleTime);
        }
    }

    //데이터 리스트 객체 전달
    ScheduleAdapter(ArrayList<ScheduleItem> scheduleList) {
        this.scheduleList = scheduleList;
    }

    //아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recycler_schedule, parent, false);
        ScheduleViewHolder viewHolder = new ScheduleViewHolder(view);
        return viewHolder;
    }

    //position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        holder.emoticon.setImageResource(R.drawable.ic_favorite_black_24dp);
        holder.scheduleTxt.setText(scheduleList.get(position).getSchedule());
        holder.scheduleTime.setText(scheduleList.get(position).getScheduleTime());
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }
}
