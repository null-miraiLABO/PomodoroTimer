package com.example.pomodorotimerappa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final long START_TIME=10000;

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button getmButtonReset;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;

    private long mTimerLeftInMillis=START_TIME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewCountDown=findViewById(R.id.text_view_countdown);
        mButtonStartPause=findViewById(R.id.button_start);
        getmButtonReset=findViewById(R.id.button_reset);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning){
                    pauseTimer();
                }else{
                    startTime();
                }
            }
        });

        getmButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTime();
            }
        });

        updateCountDownText();

    }

    private void startTime(){
        mCountDownTimer=new CountDownTimer(mTimerLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimerLeftInMillis=millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning=false;
                mButtonStartPause.setText("スタート");
                getmButtonReset.setVisibility(View.INVISIBLE);

            }
        }.start();

        mTimerRunning=true;
        mButtonStartPause.setText("一時停止");
        getmButtonReset.setVisibility(View.INVISIBLE);

    }

    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning=false;
        mButtonStartPause.setText("一時停止");
        getmButtonReset.setVisibility(View.INVISIBLE);

    }

    private void resetTime(){
        mTimerLeftInMillis=START_TIME;
        updateCountDownText();
        mButtonStartPause.setVisibility(View.VISIBLE);
        getmButtonReset.setVisibility(View.INVISIBLE);

    }

    private void updateCountDownText(){
        int minutes = (int)(mTimerLeftInMillis/1000)/60;
        int second  = (int)(mTimerLeftInMillis/1000)%60;
        String timerLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,second);
        mTextViewCountDown.setText(timerLeftFormatted);
    }



}