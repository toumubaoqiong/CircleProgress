package com.example.vince.circleprogress.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.vince.circleprogress.R;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onClick(View v){

        switch (v.getId()){
            case R.id.Button_AppleAnimation:
                startActivity(new Intent(MenuActivity.this,AppleAnimationActivity.class));
                break;
            case R.id.Button_OtherAnimation:
                startActivity(new Intent(MenuActivity.this,OtherAnimationActivity.class));
                break;
        }
    }
}
