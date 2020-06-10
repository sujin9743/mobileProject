package jsj.mjc.mobileproject;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddScheduleActivity extends AppCompatActivity {
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
    }

    //toolbar 완료 버튼
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.schedule_toolbar, menu);
        return true;
    }
}
