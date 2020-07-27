package Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a40_day05_foodorder.Items;
import com.example.a40_day05_foodorder.R;
import com.example.a40_day05_foodorder.databinding.YourorderScreenBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;

import Adapter.Adapter2;
import Interface.onClick;

public class YourOrder extends Fragment {
    YourorderScreenBinding binding;
    ArrayList<Items> list2;
    Adapter2 adapter2;
    int price;
    DecimalFormat currency = new DecimalFormat("###,###,###.##đ");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.yourorder_screen,container,false);
        list2 = new ArrayList<>();
        for(int i = 0; i < FoodOrder.list.size(); i++){
            if(FoodOrder.list.get(i).getAmount() > 0){
                list2.add(FoodOrder.list.get(i));
                price += FoodOrder.list.get(i).getAmount() * FoodOrder.list.get(i).getPrice();
            }
        }

        binding.tvPrice.setText(currency.format(price));

        adapter2 = new Adapter2(list2,getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        binding.recyclerview.setLayoutManager(layoutManager);
        binding.recyclerview.setAdapter(adapter2);
        adapter2.setOnclick(new onClick() {
            @Override
            public void clickMinus(Items item) {
                if(item.getAmount() > 0){
                    item.setAmount(item.getAmount()-1);
                    price -= item.getPrice();
                    binding.tvPrice.setText(currency.format(price));
                    binding.recyclerview.setAdapter(adapter2);
                }
            }

            @Override
            public void clickPlus(Items item) {
                item.setAmount(item.getAmount()+1);
                price += item.getPrice();
                binding.tvPrice.setText(currency.format(price));
                binding.recyclerview.setAdapter(adapter2);
            }
        });
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().remove(YourOrder.this).commit();

            }
        });

        return binding.getRoot();
    }
}
