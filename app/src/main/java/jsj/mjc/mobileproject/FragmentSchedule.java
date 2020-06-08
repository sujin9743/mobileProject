package jsj.mjc.mobileproject;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentSchedule extends Fragment {

    TextView dateTxt;
    Button dateBtn;
    String today_year, today_month;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View 객체에 현재 뷰를 담음
        View view =  inflater.inflate(R.layout.fragment_schedule, container, false);

        dateTxt = view.findViewById(R.id.dateTxt);

        //todo 1. 현재 년, 월 가져와서 dateTxt(TextView)에 출력
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM");
        Date date = new Date();
        dateTxt.setText(dateFormat.format(date));
        //todo 2. dateText 클릭 시 datePicker layout visible


        return view;
    }
}
