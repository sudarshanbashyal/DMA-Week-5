package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> shoppingItems = new ArrayList<>();
    List<TextView> textViews = new ArrayList<>();

    LinearLayout listContainer;
    FloatingActionButton addButton;

    public static final String EXTRA_MESSAGE = "com.example.twoactivity.extra.REPLY";
    public static final int MAIN_ACTIVITY_REQUEST= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listContainer = findViewById(R.id.list_container);
        addButton = findViewById(R.id.floatingActionButton);

        // add all the textviews to an array
        for(int i=0; i<listContainer.getChildCount(); i++){
            textViews.add((TextView) listContainer.getChildAt(i));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MAIN_ACTIVITY_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);

                this.shoppingItems.add(reply);
                this.renderList(reply);
            }
        }
    }

    private void renderList(String newItem){
        // check the number of entires and add new entry according to (e-1) of index, since older entries are already filled
        int lastAvailableIndex = this.shoppingItems.size()-1 ;

        this.textViews.get(lastAvailableIndex).setText(lastAvailableIndex+1+" - "+newItem);
    }

    public void addItem(View view) {
        if(this.shoppingItems.size()>=10){
            Toast.makeText(this, "All Items Added.", Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(this, SecondActivity.class);

        // sending a list of all items that have already been added
        intent.putExtra(EXTRA_MESSAGE, TextUtils.join(",", this.shoppingItems));
        startActivityForResult(intent, MAIN_ACTIVITY_REQUEST);
    }
}