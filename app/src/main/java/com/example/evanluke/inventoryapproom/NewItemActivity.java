package com.example.evanluke.inventoryapproom;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewItemActivity extends AppCompatActivity {


    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_REPLY_NUMBER = "com.example.android.wordlistsql.REPLY";


    private  EditText mEditItemView;
    private EditText mEditAmountView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        mEditItemView = findViewById(R.id.edit_word);
        mEditAmountView = findViewById(R.id.edit_amount);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditItemView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    //TODO pass it either string, and item count or just have
                    //constructor with 0 item count and increment id when it creates

                    String word = mEditItemView.getText().toString();
                    int number = Integer.parseInt(mEditAmountView.getText().toString());
                    Bundle extras = new Bundle();
                    extras.putString("EXTRA_REPLY", word);
                    extras.putInt("EXTRA_REPLY_INT", number);
                    replyIntent.putExtras(extras);
                    /*
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    replyIntent.putExtra(EXTRA_REPLY, number);
                    Log.e ("My AspectX var is:", ""+number);*/


                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

}
