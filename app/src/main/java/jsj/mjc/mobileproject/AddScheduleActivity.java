package jsj.mjc.mobileproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
    String t_year, t_month, t_day, t_hour, t_min,
           select_year, select_month, select_day, select_AM_PM, select_hour, select_min;
    TextView start_date, start_time, end_time;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_schedule);

        //Toolbar 설정
        Toolbar toolbar = findViewById(R.id.addSch_toolbar); //Toolbar
        setSupportActionBar(toolbar); //Toolbar 적용
        getSupportActionBar().setTitle("일정"); //Toolbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //toolbar 닫기 아이콘
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp); //닫기 아이콘

        //날짜 설정 버튼 클릭 시 DatePicker Dialog
        start_date_layout = findViewById(R.id.start_date_layout);
        start_date_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });

        //시간 설정 버튼 클릭 시 TimePicker Dialog
        start_time_layout = findViewById(R.id.start_time_layout);
        end_time_layout = findViewById(R.id.end_time_layout);
        start_time_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTime();
            }
        });
        end_time_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTime();
            }
        });

        //현재 날짜, 시간 저장
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        SimpleDateFormat hourFormat = new SimpleDateFormat("hh");
        SimpleDateFormat minFormat = new SimpleDateFormat("mm");
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        t_year = yearFormat.format(date);
        t_month = monthFormat.format(date);
        t_day = dayFormat.format(date);
        t_hour = hourFormat.format(date);
        t_min = minFormat.format(date);

        //날짜, 시간 현재 TextView 현재 날짜, 시간으로 설정
        start_date = findViewById(R.id.start_date);
        start_time = findViewById(R.id.start_time);
        end_time = findViewById(R.id.end_time);
        start_date.setText(t_year+"."+t_month+"."+t_day);
        start_time.setText(t_hour+":"+t_min);
        end_time.setText(t_hour+":"+t_min);

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
    void showTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog_NoActionBar, timePickerListener, Integer.parseInt(t_hour), Integer.parseInt(t_min), true);
        timePickerDialog.setTitle("시간을 선택해주세요.");
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        }
    };

    //DatePicker Dialog
    void showDate(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, datePickerListener, Integer.parseInt(t_year), Integer.parseInt(t_month), Integer.parseInt(t_day));
        datePickerDialog.setTitle("날짜를 선택해주세요.");
        datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        datePickerDialog.show();
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        }
    };
}
