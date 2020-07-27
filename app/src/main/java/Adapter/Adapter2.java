package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a40_day05_foodorder.Items;
import com.example.a40_day05_foodorder.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import Interface.onClick;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHolder> {
    ArrayList<Items> list;
    Context context;
    onClick onclick;
    DecimalFormat currency = new DecimalFormat("###,###,###.##đ");
    public void setOnclick(onClick onclick) {
        this.onclick = onclick;
    }

    public Adapter2(ArrayList<Items> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items2,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter2.ViewHolder holder, final int position) {
        final Items item = list.get(position);
        holder.tvName.setText(item.getName());
        holder.tvAmount.setText(String.valueOf(item.getAmount()));
        holder.Minus.setVisibility(View.VISIBLE);
        holder.Plus.setVisibility(View.VISIBLE);
        holder.tvPrice.setText(currency.format(item.getPrice()));
        Glide.with(context).load(item.getImageURL()).fitCenter().into(holder.ivImage);

        holder.Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick.clickMinus(item);
            }
        });
        holder.Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick.clickPlus(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvAmount,tvPrice;
        ImageView ivImage,Minus,Plus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAmount = itemView.findViewById(R.id.Amount);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            ivImage = itemView.findViewById(R.id.ivImage);
            Minus = itemView.findViewById(R.id.Minus);
            Plus = itemView.findViewById(R.id.Plus);
        }
    }
}
