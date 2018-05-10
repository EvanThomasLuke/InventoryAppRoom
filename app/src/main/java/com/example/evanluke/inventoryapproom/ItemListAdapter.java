package com.example.evanluke.inventoryapproom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import android.support.v7.widget.RecyclerView;


/**
 * Created by evanluke on 5/4/18.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHolder> {

    // Define listener member variable
    private OnItemClickListener listener;
    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemItemView;
        private final TextView itemAmountView;

        private ItemViewHolder(final View itemView) {
            super(itemView);
            itemItemView = itemView.findViewById(R.id.textView);
            itemAmountView = itemView.findViewById(R.id.numberView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }
    }

    private final LayoutInflater mInflater;
    private List<Item> mItems; // Cached copy of words

    ItemListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (mItems != null) {
            Item current = mItems.get(position);
            holder.itemItemView.setText(current.getTitle());
            holder.itemAmountView.setText(Integer.toString(current.getCount()));
        } else {
            // Covers the case of data not being ready yet.
            holder.itemItemView.setText("No Word");
        }
    }

    void setItems(List<Item> items){
        mItems = items;
        notifyDataSetChanged();
    }
    public int getId(int position) {
        Item item = mItems.get(position);
        return item.getId();
        //return mItems.indexOf(position).getId();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mItems != null)
            return mItems.size();
        else return 0;
    }




}
