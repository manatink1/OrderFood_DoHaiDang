package Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a40_day05_foodorder.Items;
import com.example.a40_day05_foodorder.R;
import com.example.a40_day05_foodorder.databinding.FoodorderScreenBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;

import Adapter.Adapter;
import Interface.clickOnItems;

public class FoodOrder extends Fragment {
    FoodorderScreenBinding binding;
    static ArrayList<Items> list;
    Adapter adapter;
    int count;
    int price;
    DecimalFormat currency = new DecimalFormat("###,###,###.##đ");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.foodorder_screen,container,false);
        Items item1 = new Items("Coffee Milk","https://europe-institute.com/wp-content/uploads/latte_art.jpg",24000,0);
        Items item2 = new Items("Black Coffee","https://www.healthifyme.com/blog/wp-content/uploads/2019/09/Black-coffee-feature-image.jpg",29000,0);
        Items item3 = new Items("Affogato Coffee","https://www.nespresso.com/shared_res/mos/free_html/au/b2b/recipes/images/double-shot-affogato-coffee-recipe-mobile.jpg",46000,0);
        Items item4 = new Items("Americano","https://www.irishtimes.com/polopoly_fs/1.4029828.1569402836!/image/image.jpg_gen/derivatives/ratio_4x3_w1200/image.jpg",54000,0);
        Items item5 = new Items("Flat White","https://globalassets.starbucks.com/assets/1ba88037116d4234807bce3ee442900e.jpg",36000,0);
        Items item6 = new Items("Macchiato Coffee","https://cooktoria.com/wp-content/uploads/2016/02/Caramel-Macchiato-Starbucks.jpg",59000,0);
        Items item7 = new Items("Mocha Coffee","https://i1.wp.com/gatherforbread.com/wp-content/uploads/2014/10/Dark-Chocolate-Mocha-Square.jpg?fit=1000%2C1000&ssl=1",59000,0);
        Items item8 = new Items("Espresso","https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Tazzina_di_caff%C3%A8_a_Ventimiglia.jpg/1200px-Tazzina_di_caff%C3%A8_a_Ventimiglia.jpg",44000,0);
        Items item9 = new Items("Pizza Panda","https://content3.jdmagicbox.com/comp/def_content/pizza_outlets/default-pizza-outlets-4.jpg",180000,0);
        Items item10 = new Items("KFC Super","https://www.kfc.com.au/sites/default/files/alohadata/images/products/group_giantfeast_web_mobile.jpg",85000,0);
        Items item11 = new Items("Bread Eggs","https://realhousemoms.com/wp-content/uploads/Eggs-in-a-Basket-IG.jpg",50000,0);
        Items item12 = new Items("Donut Cake","https://truffle-assets.imgix.net/588fe2a0-epic-donut-cake_lc.jpg",20000,0);
        Items item13 = new Items("PanCake","https://cdn77-s3.lazycatkitchen.com/wp-content/uploads/2017/10/vegan-carrot-pancakes-stack-800x1200.jpg",25000,0);
        Items item14 = new Items("Cup Cake","https://cdn.sallysbakingaddiction.com/wp-content/uploads/2018/09/chai-latte-cupcakes.jpg",32000,0);

        list = new ArrayList<>();
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);
        list.add(item6);
        list.add(item7);
        list.add(item8);
        list.add(item9);
        list.add(item10);
        list.add(item11);
        list.add(item12);
        list.add(item13);
        list.add(item14);

        adapter = new Adapter(list,getContext());
        RecyclerView.LayoutManager Gridlayoutmanager = new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false);
        binding.recyclerView.setLayoutManager(Gridlayoutmanager);
        binding.recyclerView.setAdapter(adapter);

        adapter.setClickOnItems(new clickOnItems() {
            @Override
            public void onclick(Items item) {
                count++;
                price += item.getPrice();
                item.setAmount(item.getAmount()+1);
                binding.tvCount.setText(String.valueOf(count));
                binding.tvPrice.setText(currency.format(price));
                Toast.makeText(getContext(), "Choose Success " + "'" +item.getName() + "'", Toast.LENGTH_SHORT).show();
            }
        });

        binding.ivStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YourOrder yo = new YourOrder();
                getFragmentManager().beginTransaction().add(R.id.Container,yo).commit();
            }
        });

        binding.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Your order is sent to restaurant!" + "\n" +"Total price : " + currency.format(price)+"đ", Toast.LENGTH_SHORT).show();
                binding.tvCount.setText("0");
                binding.tvPrice.setText("0");
                count = 0;
                price = 0;
                for(int i = 0; i < list.size(); i++){
                    list.get(i).setAmount(0);
                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
