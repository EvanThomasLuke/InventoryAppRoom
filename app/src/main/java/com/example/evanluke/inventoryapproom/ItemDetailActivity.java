package com.example.evanluke.inventoryapproom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by evanluke on 5/9/18.
 */

public class ItemDetailActivity extends AppCompatActivity {

    private ItemViewModel mItemViewModel;
    private int id;
    EditText mEditText;
    EditText mEditTextCount;
    Button mSaveButton;
    TextView mTitleText;
    TextView mCountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        mEditText = findViewById(R.id.editText);
        mEditTextCount = findViewById(R.id.editCount);
        mSaveButton = findViewById(R.id.saveBtn);
        mTitleText = findViewById(R.id.txtViewTitle);
        mCountText = findViewById(R.id.txtViewAmount);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveButtonClicked();
            }
        });
        mItemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("ITEM_DETAIL_KEY");
        } else {
            //error
            finish();
        }

        // COMPLETED (12) Observe the LiveData object in the ViewModel. Use it also when removing the observer
        mItemViewModel.getItem(id).observe(this, new Observer<Item>() {
            @Override
            public void onChanged(@Nullable Item item) {
                mItemViewModel.getItem(id).removeObserver(this);
                String title = item.getTitle();
                mTitleText.setText(title);
                mEditText.setText(title);
                int count = item.getCount();
                mCountText.append(Integer.toString(count));
                mEditTextCount.setText(Integer.toString(count));
            }
        });

    }

    public void onSaveButtonClicked() {
        String title = mEditText.getText().toString();
        int count = Integer.parseInt(mEditTextCount.getText().toString());
        if (count >= 0 ) {
            final Item item = new Item(title, count);
            item.setId(id);
            mItemViewModel.update(item);
            finish();
        } else {
            Toast.makeText(ItemDetailActivity.this, "Count must be 0 or greater.", Toast.LENGTH_LONG);
        }
    }

}
