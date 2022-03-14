package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int count;
    Button countButton;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            this.count = savedInstanceState.getInt("count");
        }else{
            this.count = 0;
        }

        countButton = findViewById(R.id.button);
        text = findViewById(R.id.textView);

        text.setText(count+"");
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putInt("count", count);
    }

    public void countUp(View view) {
        this.count++;
        this.text.setText(count+"");
    }
}