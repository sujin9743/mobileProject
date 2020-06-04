package jsj.mjc.mobileproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    //FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
    FragmentManager fragmentManager = getSupportFragmentManager();
    //Fragment들
    FragmentTodo fragmentTodo = new FragmentTodo();
    FragmentSchedule fragmentSchedule = new FragmentSchedule();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);

        //첫 화면
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragmentSchedule).commitAllowingStateLoss();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (menuItem.getItemId()) {
                    case R.id.todo: {
                        fragmentTransaction.replace(R.id.frameLayout, fragmentTodo).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.schedule: {
                        fragmentTransaction.replace(R.id.frameLayout, fragmentSchedule).commitAllowingStateLoss();
                        return true;
                    }
                }
                return false;
            }
        });
    }
}