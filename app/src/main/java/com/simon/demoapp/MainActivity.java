package com.simon.demoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.simon.demoapp.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TextView mTvResult;
    private FileOutputStream mFileOutputStream;
    private BufferedReader mBufferedReader;
    private BufferedWriter mBufferedWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvResult = findViewById(R.id.tv_result);

        File file = new File("proc/android_touch/diag");
        try {
            mFileOutputStream = new FileOutputStream(file);
            mBufferedWriter = new BufferedWriter(new FileWriter(file));
            mBufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CountDownTimer countDownTimer = new CountDownTimer(1000, 5 * 60 * 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setData("11");
                getResult();
                setData("0");
                showData();
            }

            @Override
            public void onFinish() {

            }
        };

        countDownTimer.start();
    }


    private void setData(String str) {
        try {
            mBufferedWriter.write(str);
            mBufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getResult() {
        String str = null;
        String resultTemp = "";
        try {
            while ((str = mBufferedReader.readLine()) != null) {
                Log.i(TAG, "getResult: " + str);
                if (str.contains("Mutual Max:")) {
                    Log.i(TAG, "str: " + str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showData() {

    }
}
