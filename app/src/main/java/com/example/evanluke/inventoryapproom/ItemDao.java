package com.example.evanluke.inventoryapproom;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by evanluke on 4/30/18.
 */
@Dao
public interface ItemDao {

    @Insert
    void insert(Item item);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Item item);

    @Query("DELETE FROM inventory_table")
    void deleteAll();


    @Query("SELECT * FROM inventory_table WHERE id = :id")
    LiveData<Item> getItem(int id);


    //TODO add order by word asc, add order
    @Query("SELECT * FROM inventory_table")
    LiveData<List<Item>> getAllItems();
}
