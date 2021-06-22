package com.example.simpletodo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class itemsAdapter extends RecyclerView.Adapter<itemsAdapter.ViewHolder> {
    public interface OnLongClickListener {
        //needs to now psition of long press to notify adapter of deletion position
        void onItemLongClicked (int position);
    }
    //add OnLongClickListener to input of items adapter
    List<String> items;
    OnLongClickListener longClickListener;
    public itemsAdapter(List<String> items, OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate view using LayoutInflater
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        //wrap in ViewHolder and return
        return new ViewHolder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get item at position
        String item = items.get(position);
        //bind item to specified view holder
        holder.bind(item);

    }
    //gets total number of items in the list for the rv
    @Override
    public int getItemCount() {
        //string list length
        return items.size();
    }

    // Container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder {
        //TextView is found in simple_list_item_1 (hold command and click on it to see wrap)
        TextView tvItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }
        //update view inside ViewHolder with this item data
        public void bind(String item) {
            tvItem.setText(item);
            //add remove item functionality
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //removes the item from the rv
                    longClickListener.onItemLongClicked(getAdapterPosition()); //Notifies which position was long-pressed
                    return false;
                    //go back and communicate this to main activity to actually remove
                    // make interface at top to do this
                }
            });
        }
    }
}
