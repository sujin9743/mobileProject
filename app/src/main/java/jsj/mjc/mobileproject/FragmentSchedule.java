package jsj.mjc.mobileproject;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.text.FieldPosition;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.*;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentSchedule extends Fragment {

    TextView dateTxt, schedule_date;
    String today_year, today_month;
    CalendarView calendar;
    LinearLayout date_layout;
    ConstraintLayout datePicker_layout;
    FloatingActionButton sch_floatingBtn;
    RecyclerView schedule_recycler;
    ArrayList<ScheduleItem> scheduleList;
    ScheduleAdapter scheduleAdapter;

    int select_year, select_month, select_day;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View 객체에 현재 뷰를 담음
        View view =  inflater.inflate(R.layout.fragment_schedule, container, false);

        dateTxt = view.findViewById(R.id.dateTxt);
        schedule_date = view.findViewById(R.id.schedule_date);
        sch_floatingBtn = view.findViewById(R.id.sch_floatingBtn);
        calendar = view.findViewById(R.id.calendar);
        //todo 0. DatePicker, CalendarView -> 현재 날짜로 셋팅

        //todo 1. 현재 년, 월 가져와서 date_layout dateTxt(TextView)에 출력
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        select_year = Integer.parseInt(yearFormat.format(date));
        select_month = Integer.parseInt(monthFormat.format(date));
        select_day = Integer.parseInt(dayFormat.format(date));
        changeDate(select_year, select_month, select_day);

        //todo 4. 캘린더에서 선택한 월, 일, 요일 scheduleTxt(TextView)에 출력
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                select_year = year; select_month = month+1; select_day = dayOfMonth;
                changeDate(select_year, select_month, select_day);
                //todo 7. floatingActionButton 클릭 시 일정 추가 Activity 출력
                sch_floatingBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), AddScheduleActivity.class);
                        intent.putExtra("select_year", select_year);
                        intent.putExtra("select_month", select_month);
                        intent.putExtra("select_day", select_day);
                        startActivity(intent);
                    }
                });
            }
        });

        //캘린터뷰 날짜 변경하지 않고 floatingBtn 눌렀을 때
        sch_floatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddScheduleActivity.class);
                intent.putExtra("select_year", select_year);
                intent.putExtra("select_month", select_month);
                intent.putExtra("select_day", select_day);
                startActivity(intent);
            }
        });

        //todo 8. 일정 추가 시 schedule_rcV에 출력
        schedule_recycler = view.findViewById(R.id.schedule_recycler);
        scheduleList = new ArrayList<>();
        scheduleAdapter = new ScheduleAdapter(scheduleList);

        //RecyclerView를 LinearLayout으로 지정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        schedule_recycler.setLayoutManager(linearLayoutManager);
        //recyclerView 구분선 추가
        schedule_recycler.addItemDecoration(new DividerItemDecoration(schedule_recycler.getContext(), 1));
        //임시데이터
        for(int i=0; i<20; i++) {
            ScheduleItem data = new ScheduleItem("일정"+i, "시간"+i);
            scheduleList.add(data);
        }
        schedule_recycler.setAdapter(scheduleAdapter);

        //todo 9. 리사이클러뷰 슬라이딩 시 삭제 구현

        //todo 10. 리사이클러뷰 해당 리스트 클릭 시 일정 조회 및 수정

       return view;
    }

    void changeDate(int select_year, int select_month, int select_day) {
        if(select_month < 10 && select_day <10) {
            dateTxt.setText(select_year + ".0" + select_month + ".0" + select_day);
            schedule_date.setText("0" + select_month + ".0" + select_day);
        } else if(select_month < 10) {
            dateTxt.setText(select_year + ".0" + select_month + "." + select_day);
            schedule_date.setText("0" + select_month + "." + select_day);
        } else if(select_day < 10) {
            dateTxt.setText(select_year + "." + select_month + ".0" + select_day);
            schedule_date.setText(select_month + ".0" + select_day);
        }
        else {
            dateTxt.setText(select_year + "." + select_month + "." + select_day);
            schedule_date.setText(select_month + "." + select_day);
        }
    }
}
