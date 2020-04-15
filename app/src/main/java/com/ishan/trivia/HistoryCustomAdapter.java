package com.ishan.trivia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryCustomAdapter extends RecyclerView.Adapter<HistoryCustomAdapter.HistoryViewHolder> {

        private ArrayList<AddingItemsHistoryModel> mlist;

        public static class HistoryViewHolder extends RecyclerView.ViewHolder{

            public TextView history_name;
            public TextView history_time;
            public TextView history_index;
            public TextView history_ans1;
            public TextView history_ans2;




            public HistoryViewHolder(@NonNull View itemView) {
                super(itemView);

                 history_name=itemView.findViewById(R.id.item_name);
                 history_time=itemView.findViewById(R.id.item_date);
                 history_index=itemView.findViewById(R.id.item_index);
                 history_ans1=itemView.findViewById(R.id.item_ans1);
                 history_ans2=itemView.findViewById(R.id.item_ans2);


            }
        }

        @NonNull
        @Override
        public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item,parent,false);
            HistoryViewHolder historyViewHolder=new HistoryViewHolder(v);
            return historyViewHolder;
        }

        public HistoryCustomAdapter(ArrayList<AddingItemsHistoryModel> list) {

            mlist=list;
        }

    @Override
    public void onBindViewHolder(@NonNull final HistoryViewHolder holder, int position) {
        AddingItemsHistoryModel currentItem=mlist.get(position);
        holder.history_index.setText(currentItem.getIndex());
        holder.history_name.setText(currentItem.getName());
        holder.history_time.setText(currentItem.getDate());
        holder.history_ans1.setText(currentItem.getAns1());
        holder.history_ans2.setText(currentItem.getAns2());

    }


        @Override
        public int getItemCount() {
            return mlist.size();
        }
}


