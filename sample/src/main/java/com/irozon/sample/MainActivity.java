package com.irozon.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.irozon.justbar.BarItem;
import com.irozon.justbar.JustBar;
import com.irozon.justbar.interfaces.OnBarItemClickListener;

public class MainActivity extends AppCompatActivity {

    JustBar justBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        justBar = findViewById(R.id.bottomBar);

        justBar.setOnBarItemClickListener(new OnBarItemClickListener() {
            @Override
            public void onBarItemClick(BarItem barItem, int position) {
            }
        });
    }
}
