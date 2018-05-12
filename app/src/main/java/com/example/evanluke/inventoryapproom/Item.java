package com.example.evanluke.inventoryapproom;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by evanluke on 4/30/18.
 */

@Entity(tableName = "inventory_table")
public class Item {

    @NonNull
    @ColumnInfo(name = "title")
    private String mTitle;
    @NonNull
    @ColumnInfo(name = "count")
    private int mCount;
    @PrimaryKey(autoGenerate = true)
    private int id;

    @Ignore
    public Item(@NonNull String mTitle, @NonNull int mCount /*int id*/) {
        this.mTitle = mTitle;
        this.mCount = mCount;
        //this.id = id;
    }

    public Item(@NonNull int id, @NonNull String mTitle, @NonNull int mCount) {
        this.mTitle = mTitle;
        this.id = id;
        this.mCount = mCount;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(@NonNull String mTitle) {
        this.mTitle = mTitle;
    }

    @NonNull
    public int getCount() {
        return mCount;
    }

    public void setCount(@NonNull int mCount) {
        this.mCount = mCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
