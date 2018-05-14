package com.example.evanluke.inventoryapproom;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by evanluke on 4/30/18.
 */

public class ItemRepository {

    private ItemDao mItemDao;
    private LiveData<List<Item>> mAllItems;

    ItemRepository(Application application) {
        InventoryRoomDatabase db = InventoryRoomDatabase.getDatabase(application);
        mItemDao = db.itemDao();
        mAllItems = mItemDao.getAllItems();
    }

    LiveData<List<Item>> getAllItems() {
        return mAllItems;
    }

    LiveData<Item> getItem(int id) {
        return mItemDao.getItem(id);
    }

    public void insert (Item item) {
        new insertAsyncTask(mItemDao).execute(item);
    }


    private static class insertAsyncTask extends android.os.AsyncTask<Item, Void, Void> {

        private ItemDao mAsyncTaskDao;

        insertAsyncTask(ItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Item... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void update (Item item) {new updateAsyncTask(mItemDao).execute(item); }

    private static class updateAsyncTask extends AsyncTask<Item, Void, Void> {

        private ItemDao mAsyncTaskDao;

        updateAsyncTask(ItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Item... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

}
