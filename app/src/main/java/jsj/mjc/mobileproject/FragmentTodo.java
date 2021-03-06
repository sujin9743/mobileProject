package jsj.mjc.mobileproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

public class FragmentTodo extends Fragment {

    TextView dateTxt_todo, dateTxt_picker_todo;
    Button dateBtn_todo, dateBtn_picker_todo;
    ConstraintLayout datePicker_layout_todo;
    RecyclerView todo_recycler;
    ArrayList<TodoItem> todoList;
    TodoAdapter todoAdapter;
    FloatingActionButton todo_floatingBtn;
    View dialog;
    EditText todoEdt;
    DatePicker datePicker_todo;

    int select_year, select_month, select_day;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View 객체에 현재 뷰를 담음
        View view =  inflater.inflate(R.layout.fragment_todo, container, false);

        dateTxt_todo = view.findViewById(R.id.dateTxt_todo);
        dateBtn_todo = view.findViewById(R.id.dateBtn_todo);
        dateTxt_picker_todo = view.findViewById(R.id.dateTxt_picker_todo);
        dateBtn_picker_todo = view.findViewById(R.id.dateBtn_picker_todo);
        datePicker_layout_todo = view.findViewById(R.id.datePicker_layout_todo);
        todo_floatingBtn = view.findViewById(R.id.todo_floatingBtn);
        datePicker_todo = view.findViewById(R.id.datePicker_todo);
        //todo 1. 현재 년, 월 가져와서 date_layout dateTxt(TextView)에 출력
        SimpleDateFormat dateFormatFull = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        dateTxt_todo.setText(dateFormatFull.format(date));

        //todo 2. 현재 년, 월 가져와서 date_layout dateTxt(TextView)에 출력
        dateTxt_picker_todo.setText(dateFormatFull.format(date));

        //todo 3. dateBtn 클릭 시 datePickerActivity 출력
        dateBtn_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker_layout_todo.setVisibility(View.VISIBLE);
            }
        });
        dateBtn_picker_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker_layout_todo.setVisibility(View.INVISIBLE);
            }
        });

        //datePicker 선택 시 날짜 변경
        datePicker_todo.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                select_year = year; select_month = monthOfYear+1; select_day = dayOfMonth;
                changeDate(select_year, select_month, select_day);
            }
        });

        //todo 4. 입력한 todo RecyclerView에 출력
        todo_recycler = view.findViewById(R.id.todo_recycler);
        todoList = new ArrayList<>();
        todoAdapter = new TodoAdapter(todoList);

        //RecyclerView를 LinearLayout으로 지정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        todo_recycler.setLayoutManager(linearLayoutManager);
        //recyclerView 구분선 추가
        todo_recycler.addItemDecoration(new DividerItemDecoration(todo_recycler.getContext(), 1));
        //임시데이터
        for(int i=0; i<20; i++) {
            TodoItem data = new TodoItem("to-do"+i);
            todoList.add(data);
        }
        todo_recycler.setAdapter(todoAdapter);

        //todo 5.floatingButton누르면 todo 추가 Dialog
        todo_floatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog_add_todo(사용자 정의 dialog)를 인플레이트하여 dialog에 대입
                dialog = v.inflate(getContext(), R.layout.dialog_add_todo, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(getContext());
                dlg.setTitle("할 일을 입력해주세요.");
                //인플레이트한 뷰를 대화상자로 사용
                dlg.setView(dialog);
                //확인 버튼 눌렀을 때 이벤트 처리
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                //취소 버튼 눌렀을 때 이벤트 처리
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dlg.show();
            }
        });
        return view;
    }
    void changeDate(int select_year, int select_month, int select_day) {
        if(select_month < 10 && select_day <10) {
            dateTxt_todo.setText(select_year + ".0" + select_month + ".0" + select_day);
            dateTxt_picker_todo.setText(select_year + ".0" + select_month + ".0" + select_day);
        } else if(select_month < 10) {
            dateTxt_todo.setText(select_year + ".0" + select_month + "." + select_day);
            dateTxt_picker_todo.setText(select_year + ".0" + select_month + "." + select_day);
        } else if(select_day < 10) {
            dateTxt_todo.setText(select_year + "." + select_month + ".0" + select_day);
            dateTxt_picker_todo.setText(select_year + "." + select_month + ".0" + select_day);
        }
        else {
            dateTxt_todo.setText(select_year + "." + select_month + "." + select_day);
            dateTxt_picker_todo.setText(select_year + "." + select_month + "." + select_day);
        }
    }
}
