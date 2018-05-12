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
    private LiveData<Item> item;
    private int id;
    private Item item2;

    EditText mEditText;
    EditText mEditTextCount;
    Button mSaveButton;
    TextView mTitleText;
    TextView mCountText;



    //Make the query call here
    //check that it does query on back end



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

            Toast toast = Toast.makeText(ItemDetailActivity.this, "Intent SUCCESS " + id, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            //error
        }



        // COMPLETED (12) Observe the LiveData object in the ViewModel. Use it also when removing the observer
        mItemViewModel.getItem(id).observe(this, new Observer<Item>() {
            @Override
            public void onChanged(@Nullable Item item) {
                mItemViewModel.getItem(id).removeObserver(this);
                //populateUI(taskEntry);
                String title = item.getTitle();
                mTitleText.setText(title);
                mEditText.setText(title);
                Toast.makeText(ItemDetailActivity.this, title, Toast.LENGTH_SHORT).show();
                int count = item.getCount();
                mCountText.append(Integer.toString(count));
                mEditTextCount.setText(Integer.toString(count));
                Toast.makeText(ItemDetailActivity.this, "" + count, Toast.LENGTH_SHORT).show();
            }
        });






/*        item = mItemViewModel.getItem(id);
        item2 = item.getValue();*/
        //String title = item2.getTitle();
        //Toast.makeText()
        //item2 = item.getValue();
       // String title = item2.getTitle();
/*        if (item != null) {
            Toast.makeText(ItemDetailActivity.this, "Success item not null", Toast.LENGTH_SHORT).show();
            Toast.makeText(ItemDetailActivity.this, "title : " + title, Toast.LENGTH_SHORT);

        }*/



/*
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == EDIT_ITEM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
                //Item item = new Item(data.getStringExtra(NewItemActivity.EXTRA_REPLY));

                Bundle extras = data.getExtras();
                Item item = new Item(extras.getString("EXTRA_REPLY"), extras.getInt("EXTRA_REPLY_INT", 0));
                //Item item = new Item(data.getStringExtra(NewItemActivity.EXTRA_REPLY), data.getIntExtra(NewItemActivity.EXTRA_REPLY, 0));
                mItemViewModel.insert(item);
            } else {
                Toast.makeText(
                        getApplicationContext(),
                        R.string.empty_not_saved,
                        Toast.LENGTH_LONG).show();
            }
        }*/



    }

    public void onSaveButtonClicked() {
        String title = mEditText.getText().toString();
        //int priority = getPriorityFromViews();
        //Date date = new Date();
        int count = Integer.parseInt(mEditTextCount.getText().toString());

        if (count >= 0 ) {
            final Item item = new Item(title, count);
            item.setId(id);
            mItemViewModel.update(item);

            finish();
        } else {
            Toast.makeText(ItemDetailActivity.this, "Count must be 0 or greater.", Toast.LENGTH_LONG);

        }

/*
        final Item item = new Item(description, priority, date);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (mTaskId == DEFAULT_TASK_ID) {
                    // insert new task
                    mDb.taskDao().insertTask(task);
                } else {
                    //update task
                    task.setId(mTaskId);
                    mDb.taskDao().updateTask(task);
                }
                finish();
            }*/
        // });
    }

}
