package com.example.evanluke.inventoryapproom;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by evanluke on 4/30/18.
 */

public class ItemViewModel extends AndroidViewModel {

    private ItemRepository mRepository;

    private LiveData<List<Item>> mAllItems;

    private int id;

    public ItemViewModel (Application application) {
        super(application);
        mRepository = new ItemRepository(application);
        mAllItems = mRepository.getAllItems();
    }



    LiveData<List<Item>> getAllItems() { return mAllItems; }

    public void insert(Item item) { mRepository.insert(item); }

    public void update(Item item) {mRepository.update(item); }

    public LiveData<Item> getItem(int id) {return mRepository.getItem(id); }
}
