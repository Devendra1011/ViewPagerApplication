package com.example.viewpagerapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {


    ViewPager2 viewPager;
    MyViewPagerAdapter myViewPagerAdapter;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tabLayout = findViewById(R.id.tab_layout);

        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),getLifecycle());


        myViewPagerAdapter.addFragment(new FirstFragment());
        myViewPagerAdapter.addFragment(new SecondFragment());
        myViewPagerAdapter.addFragment(new ThirdFragment());

        // set the orientation in viewpager2
        viewPager = findViewById(R.id.view_pager);
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        // connecting tha adapter with viewpager2
        viewPager.setAdapter(myViewPagerAdapter);

        // connecting tab layout with viewpager
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText("Fragment "+(position +1));

            }
        }).attach();
    }
}