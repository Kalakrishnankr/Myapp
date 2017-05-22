package com.example.kalakrishnankr.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by kalakrishnan.kr on 17/5/17.
 */

public class Homepage extends AppCompatActivity {

    Button reg,log,view;
    ImageView imv1;
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home_activity);
        reg = (Button) findViewById(R.id.btn_reg);
        log = (Button) findViewById(R.id.btn_login);
        view = (Button) findViewById(R.id.btn_view);
        imv1 = (ImageView) findViewById(R.id.imv);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this,MainActivity.class);
                startActivity(intent);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Homepage.this,Viewpage.class);
                startActivity(intent);
            }
        });


    }
}
