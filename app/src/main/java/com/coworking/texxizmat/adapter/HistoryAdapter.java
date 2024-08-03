package com.coworking.texxizmat.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coworking.tehhizmat.R;
import com.coworking.texxizmat.HistoryItem;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<HistoryItem> historyItemList;

    public HistoryAdapter(List<HistoryItem> historyItemList) {
        this.historyItemList = historyItemList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        HistoryItem item = historyItemList.get(position);
        holder.address.setText(item.getAddress());
        holder.accountNum.setText(item.getAccountNum());
        holder.fullName.setText(item.getFullName());

        // Decode byte array to Bitmap
        byte[] imageBytes = item.getImageResId();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        holder.imageView.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return historyItemList.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView address, accountNum, fullName;
        ImageView imageView;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.tv_address);
            accountNum = itemView.findViewById(R.id.tv_accountNumber);
            fullName = itemView.findViewById(R.id.tv_abonentName);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
