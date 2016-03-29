package com.example.vince.circleprogress.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.vince.circleprogress.R;
import com.example.vince.circleprogress.controls.RoundProgress;

import java.lang.ref.WeakReference;

/**
 * description:类似苹果安装进度动效
 * author:vince
 */
public class AppleAnimationActivity extends Activity {

    private RoundProgress mRoundProgress;
    private Button mBtnClick;
    private int progress = 0;
    private MyHandler mMyHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        init();
    }

    public void init() {
        mRoundProgress = (RoundProgress) findViewById(R.id.roundProgress);
        mBtnClick = (Button) findViewById(R.id.Button_click);
        mMyHandler = new MyHandler(this);

        mBtnClick.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                mBtnClick.setEnabled(false);
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        progress = 0;
                        while (progress <= 100) {
                            progress += 3;
                            if (progress > 100) {
                                progress = 100;
                            }
                            System.out.println(progress);

                            Message msg = mMyHandler.obtainMessage();
                            msg.arg1 = progress;
                            msg.sendToTarget();

                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();
            }
        });

        mRoundProgress.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), progress + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    static class MyHandler extends Handler {
        WeakReference<AppleAnimationActivity> mActivity;

        MyHandler(AppleAnimationActivity activity) {
            mActivity = new WeakReference(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            AppleAnimationActivity activity = mActivity.get();

            activity.mRoundProgress.setProgress(msg.arg1);
        }
    }

    ;
}
