package jsj.mjc.mobileproject;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Array;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import de.hdodenhof.circleimageview.CircleImageView;

public class AddScheduleActivity extends AppCompatActivity {
    String[] emoticon_source = {"ic_check_black_24dp", "ic_favorite_black_24dp", "ic_flight_black_24dp", "ic_star_24dp", "ic_turned_in_black_24dp"};
    Integer[] emoticon_btn_id = {R.id.checkEmo, R.id.starEmo, R.id.turnedEmo, R.id.heartEmo, R.id.flightEmo};
    Button[] emoticonBtn = new Button[5];
    CircleImageView imageBox;
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
}
