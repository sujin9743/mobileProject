package jsj.mjc.mobileproject;

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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class FragmentTodo extends Fragment {

    TextView dateTxt_todo, dateTxt_picker_todo;
    Button dateBtn_todo, dateBtn_picker_todo;
    ConstraintLayout datePicker_layout_todo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View 객체에 현재 뷰를 담음
        View view =  inflater.inflate(R.layout.fragment_todo, container, false);

        dateTxt_todo = view.findViewById(R.id.dateTxt_todo);
        dateBtn_todo = view.findViewById(R.id.dateBtn_todo);
        //todo 1. 현재 년, 월 가져와서 date_layout dateTxt(TextView)에 출력
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM");
        SimpleDateFormat dateFormatFull = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        dateTxt_todo.setText(dateFormatFull.format(date));

        dateTxt_picker_todo = view.findViewById(R.id.dateTxt_picker_todo);
        dateBtn_picker_todo = view.findViewById(R.id.dateBtn_picker_todo);

        //todo 2. 현재 년, 월 가져와서 date_layout dateTxt(TextView)에 출력
        dateTxt_picker_todo.setText(dateFormatFull.format(date));

        //todo 3. dateText 클릭 시 datePickerActivity 출력
        datePicker_layout_todo = view.findViewById(R.id.datePicker_layout_todo);
        dateTxt_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker_layout_todo.setVisibility(View.VISIBLE);
            }
        });
        dateTxt_picker_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker_layout_todo.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }
}
