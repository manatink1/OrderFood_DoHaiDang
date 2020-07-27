package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a40_day05_foodorder.Items;
import com.example.a40_day05_foodorder.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import Interface.clickOnItems;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    ArrayList<Items> list;
    Context context;
    clickOnItems ClickOnItems;
    DecimalFormat currency = new DecimalFormat("###,###,###.##đ");
    public Adapter(ArrayList<Items> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setClickOnItems(clickOnItems clickOnItems) {
        ClickOnItems = clickOnItems;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, final int position) {
        final Items item = list.get(position);
        holder.tvName.setText(item.getName());
        holder.tvPrice.setText(currency.format(item.getPrice()));
        Glide.with(context).load(item.getImageURL()).fitCenter().into(holder.ivImange);

        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickOnItems.onclick(item);
            }
        });
        holder.tvPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickOnItems.onclick(item);
            }
        });
        holder.ivImange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickOnItems.onclick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvPrice;
        ImageView ivImange;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            ivImange = itemView.findViewById(R.id.ivImage);
        }
    }
}
