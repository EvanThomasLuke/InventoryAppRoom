package com.example.evanluke.inventoryapproom;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by evanluke on 4/30/18.
 */
@Database(entities = {Item.class}, version =1)
public abstract class InventoryRoomDatabase extends RoomDatabase {


    public abstract ItemDao itemDao();

    private static InventoryRoomDatabase INSTANCE;

    public static InventoryRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (InventoryRoomDatabase.class){
                if (INSTANCE == null) {
                    //Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            InventoryRoomDatabase.class, "inventory_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ItemDao mDao;

        PopulateDbAsync(InventoryRoomDatabase db) {
            mDao = db.itemDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();

            Item item = new Item("Hello", 1);
            mDao.insert(item);
            item = new Item("World", 2);
            mDao.insert(item);
            return null;
        }
    }

}
