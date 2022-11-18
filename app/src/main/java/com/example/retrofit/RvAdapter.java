package com.example.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {
    private final Context mContext;
    private final List<InfoModel> infoModels;

    public RvAdapter(Context mContext, List<InfoModel> infoModels) {
        this.mContext = mContext;
        this.infoModels = infoModels;
    }

    @NonNull
    @Override
    public RvAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mLayoutInflater=LayoutInflater.from(mContext);
        view=mLayoutInflater.inflate(R.layout.item_recycler_view,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter.MyViewHolder holder, int position) {
        holder.tvProductId.setText(infoModels.get(position).getProductId());
        holder.tvWidth.setText(infoModels.get(position).getWidth());
        holder.tvDepth.setText(infoModels.get(position).getDepth());
        holder.tvTable.setText(infoModels.get(position).getTable());
    }

    @Override
    public int getItemCount() {
        return infoModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductId,tvDepth,tvWidth,tvTable;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductId=itemView.findViewById(R.id.tvProductId);
            tvDepth=itemView.findViewById(R.id.tvDepth);
            tvWidth=itemView.findViewById(R.id.tvWidth);
            tvTable=itemView.findViewById(R.id.tvTable);
        }
    }
}
