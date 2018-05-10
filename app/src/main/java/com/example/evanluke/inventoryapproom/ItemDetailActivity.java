package com.example.evanluke.inventoryapproom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by evanluke on 5/9/18.
 */

public class ItemDetailActivity extends AppCompatActivity {


    private ItemViewModel mItemViewModel;
    private LiveData<Item> item;
    private int id;
    private Item item2;



    //Make the query call here
    //check that it does query on back end



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        mItemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("ITEM_DETAIL_KEY");

            Toast toast = Toast.makeText(ItemDetailActivity.this, "Intent SUCCESS " + id, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            //error
        }

        item = mItemViewModel.getItem(id);

        item2 = item.getValue();
       // String title = item2.getTitle();
        if (item != null) {
            Toast.makeText(ItemDetailActivity.this, "Success item not null", Toast.LENGTH_SHORT).show();
            Toast.makeText(ItemDetailActivity.this, "title : " + title, Toast.LENGTH_SHORT);

        }



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





}
