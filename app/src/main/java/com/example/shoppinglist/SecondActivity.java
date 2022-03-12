package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    String[] allItems = {"Cheese", "Rice", "Coffee", "Noodles", "Pasta", "Vinegar", "Chicken", "Eggs", "Milk", "Salt"};
    List<String> addedItems;

    public static final String EXTRA_REPLY = "com.example.twoactivity.extra.REPLY";
    LinearLayout buttonsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        buttonsContainer = findViewById(R.id.buttons_container);
        this.renderButtons();

        // get list of added items
        Intent intent = getIntent();
        String items = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        this.addedItems = Arrays.asList(items.split(","));
    }

    private void renderButtons(){
        for(int i=0; i<allItems.length; i++){
            Button button = new Button(this);
            button.setText(allItems[i]);

            buttonsContainer.addView(button);
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        String text = clickedButton.getText().toString();

        // check if this particular item was already added
        if(this.addedItems.contains(text)){
            Toast.makeText(this, "This Item is already added.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, text);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}