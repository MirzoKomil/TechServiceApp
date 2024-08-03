package com.coworking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coworking.tehhizmat.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList name_id, email_id, age_id;

    public MyAdapter(Context context, ArrayList name_id, ArrayList email_id, ArrayList age_id) {
        this.context = context;
        this.name_id = name_id;
        this.email_id = email_id;
        this.age_id = age_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.history,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name_id.setText(String.valueOf(name_id.get(position)));
        holder.email_id.setText(String.valueOf(email_id.get(position)));
        holder.age_id.setText(String.valueOf(age_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return name_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_id, email_id, age_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name_id = itemView.findViewById(R.id.textname);
            email_id = itemView.findViewById(R.id.emailname);
            age_id = itemView.findViewById(R.id.age);
        }
    }
}
