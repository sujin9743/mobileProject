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
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.*;

public class FragmentSchedule extends Fragment {

    TextView dateTxt, dateTxt_picker;
    Button dateBtn, dateBtn_picker;
    String today_year, today_month;
    DatePicker datePicker;
    LinearLayout date_layout;
    ConstraintLayout datePicker_layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View 객체에 현재 뷰를 담음
        View view =  inflater.inflate(R.layout.fragment_schedule, container, false);

        dateTxt = view.findViewById(R.id.dateTxt);
        dateBtn = view.findViewById(R.id.dateBtn);
        //todo 1. 현재 년, 월 가져와서 date_layout dateTxt(TextView)에 출력
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM");
        Date date = new Date();
        dateTxt.setText(dateFormat.format(date));

        dateTxt_picker = view.findViewById(R.id.dateTxt_picker);
        dateBtn_picker = view.findViewById(R.id.dateBtn_picker);

        //todo 2. 현재 년, 월 가져와서 date_layout dateTxt(TextView)에 출력
        dateTxt_picker.setText(dateFormat.format(date));

        //todo 3. dateText 클릭 시 datePickerActivity 출력
        datePicker_layout = view.findViewById(R.id.datePicker_layout);
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker_layout.setVisibility(View.VISIBLE);
            }
        });
        dateBtn_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    datePicker_layout.setVisibility(View.INVISIBLE);
            }
        });

        //todo 4. 캘린더에서 선택한 월, 일, 요일 scheduleTxt(TextView)에 출력

        //todo 5. DatePicker로 날짜 클릭 시 선택한 년,월 dateTxt, dateTxt_picker에 출력

        //todo 6. DatePicker로 년, 월 클릭 시 해당 날짜의 캘린더로 변경

        //todo 7. floatingActionButton 클릭 시 일정 추가 Activity 출력

        //todo 8. 일정 추가 시 schedule_rcV에 출력

        //todo 9. 리사이클러뷰 슬라이딩 시 삭제 구현

        //todo 10. 리사이클러뷰 해당 리스트 클릭 시 일정 조회 및 수정
       return view;
    }
}
