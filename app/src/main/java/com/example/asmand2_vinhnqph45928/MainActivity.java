package com.example.asmand2_vinhnqph45928;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.asmand2_vinhnqph45928.Fragment.ThongTinFragment;
import com.example.asmand2_vinhnqph45928.Fragment.VanDongFragment;
import com.example.asmand2_vinhnqph45928.Fragment.HoatDongFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
private DrawerLayout drawerLayout;
private Toolbar toolbar;
private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar001);
        navigationView = findViewById(R.id.nav001);

        // gắn toolbar vào activity
        setSupportActionBar( toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        // cài đặt cập nhật trạng thái
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener( drawerToggle );

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fr;
                if(item.getItemId()==R.id.mthongtin){

                    fr=new ThongTinFragment();
                    toolbar.setTitle("Quản lý thông tin");
                }else if(item.getItemId()==R.id.mhoatdong){
                    fr=new HoatDongFragment();
                    toolbar.setTitle("Quản lý hoạt động");

                }else if(item.getItemId()==R.id.mvandong){

                    fr=new ThongTinFragment();
                    toolbar.setTitle("Quản lý vận động");
                }
                else{
                    fr=new GioiThieu();
                    toolbar.setTitle(item.getTitle());

                }
                //sau khi kiểm tra hết các th
                drawerToggle.setDrawerIndicatorEnabled(true);
                drawerToggle.syncState();
                drawerLayout.addDrawerListener(drawerToggle);


                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_container001, fr)
                        .commit();
                drawerLayout.close(); // đóng menu
                return true;
            }
        });
    }
}