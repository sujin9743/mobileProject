package jsj.mjc.mobileproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import de.hdodenhof.circleimageview.CircleImageView;

public class AddScheduleActivity extends AppCompatActivity {
    String[] emoticon_source = {"ic_check_black_24dp", "ic_favorite_black_24dp", "ic_flight_black_24dp", "ic_star_24dp", "ic_turned_in_black_24dp"};
    Integer[] emoticon_btn_id = {R.id.checkEmo, R.id.starEmo, R.id.turnedEmo, R.id.heartEmo, R.id.flightEmo};
    Button[] emoticonBtn = new Button[5];
    CircleImageView imageBox;
    LinearLayout start_date_layout, start_time_layout, end_time_layout;
    String t_year, t_month, t_day, t_hour, t_min;
    int select_year, select_month, select_day, select_start_hour, select_start_min, select_end_hour, select_end_min;
    TextView start_date, start_time, end_time;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_schedule);

        start_date_layout = findViewById(R.id.start_date_layout);
        start_date = findViewById(R.id.start_date);
        start_time = findViewById(R.id.start_time);
        end_time = findViewById(R.id.end_time);
        start_time_layout = findViewById(R.id.start_time_layout);
        end_time_layout = findViewById(R.id.end_time_layout);

        //Toolbar 설정
        Toolbar toolbar = findViewById(R.id.addSch_toolbar); //Toolbar
        setSupportActionBar(toolbar); //Toolbar 적용
        getSupportActionBar().setTitle("일정"); //Toolbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //toolbar 닫기 아이콘
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp); //닫기 아이콘

        //날짜 설정 버튼 클릭 시 DatePicker Dialog
        start_date_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });

        //시간 설정 버튼 클릭 시 TimePicker Dialog
        start_time_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStartTime();
            }
        });
        end_time_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEndTime();
            }
        });

        //현재 날짜, 시간 저장
        SimpleDateFormat hourFormat = new SimpleDateFormat("hh");
        SimpleDateFormat minFormat = new SimpleDateFormat("mm");
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        t_hour = hourFormat.format(date);
        t_min = minFormat.format(date);

        //날짜, 시간 현재 TextView 현재 날짜, 시간으로 설정
        select_start_hour = Integer.parseInt(t_hour);
        select_start_min = Integer.parseInt(t_min);
        changeStartTime(select_start_hour, select_start_min);
        changeEndTime(select_start_hour+1, select_start_min);

        //FragmentScheduel에서 선택된 날짜 설정
        Intent intent = getIntent();
        select_year = intent.getExtras().getInt("select_year");
        select_month = intent.getExtras().getInt("select_month");
        select_day = intent.getExtras().getInt("select_day");
        changeDate(select_year, select_month, select_day);

        imageBox = findViewById(R.id.imageBox);
        //이모티콘 버튼 객체 생성
        for(int i=0; i<emoticon_btn_id.length; i++) {
            emoticonBtn[i] = findViewById(emoticon_btn_id[i]);
        }

        for(int i=0; i<emoticonBtn.length; i++) {
            emoticonBtn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageBox.setBackground(v.getBackground());
                }
            });
        }
    }

    //toolbar 완료 버튼
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.schedule_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //x버튼 누르면 창닫기
            case android.R.id.home:
                finish();
                return true;
            //todo. 완료 버튼 누르면  입력한 데이터가 DB에 저장되고, RecyclerView에 출력되고, 창 닫기
            case R.id.toolbar_complete_btn:
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //TimePicker Dialog
    void showStartTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog_NoActionBar, startTimePickerListener, select_start_hour, select_start_min, true);
        timePickerDialog.setTitle("시간을 선택해주세요.");
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
    }

    private TimePickerDialog.OnTimeSetListener startTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            select_start_hour = hourOfDay; select_start_min = minute;
            changeStartTime(select_start_hour, select_start_min);
        }
    };

    //TimePicker Dialog
    void showEndTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog_NoActionBar, endTimePickerListener, select_end_hour, select_end_min, true);
        timePickerDialog.setTitle("시간을 선택해주세요.");
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
    }

    private TimePickerDialog.OnTimeSetListener endTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            select_end_hour = hourOfDay; select_end_min = minute;
            changeEndTime(select_end_hour, select_end_min);
        }
    };

    //DatePicker Dialog
    void showDate(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, datePickerListener, select_year, select_month, select_day);
        datePickerDialog.setTitle("날짜를 선택해주세요.");
        datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        datePickerDialog.show();
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            select_year = year; select_month = month+1; select_day = dayOfMonth;
            changeDate(select_year, select_month, select_day);
        }
    };

    //날짜 출력 포맷
    void changeDate(int select_year, int select_month, int select_day) {
        if(select_month < 10 && select_day <10) {
            start_date.setText(select_year + ".0" + select_month + ".0" + select_day);
        } else if(select_month < 10) {
            start_date.setText(select_year + ".0" + select_month + "." + select_day);
        } else if(select_day < 10) {
            start_date.setText(select_year + "." + select_month + ".0" + select_day);
        }
        else {
            start_date.setText(select_year + "." + select_month + "." + select_day);
        }
    }

    //시간 출력 포맷(start_time)
    void changeStartTime(int select_hour, int select_min) {
        if(select_hour < 10 && select_min <10) {
            start_time.setText("0" + select_hour + ":0" + select_min);
        } else if(select_hour < 10) {
            start_time.setText("0" + select_hour + ":" + select_min);
        } else if(select_min < 10) {
            start_time.setText(select_hour + ":0" + select_min);
        }
        else {
            start_time.setText(select_hour + ":" + select_min);
        }
    }

    //시간 출력 포맷(end_time)
    void changeEndTime(int select_hour, int select_min) {
        if(select_hour < 10 && select_min <10) {
            end_time.setText("0" + select_hour + ":0" + select_min);
        } else if(select_hour < 10) {
            end_time.setText("0" + select_hour + ":" + select_min);
        } else if(select_min < 10) {
            end_time.setText(select_hour + ":0" + select_min);
        }
        else {
            end_time.setText(select_hour + ":" + select_min);
        }
    }
}
