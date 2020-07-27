package Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.a40_day05_foodorder.MainActivity;
import com.example.a40_day05_foodorder.R;
import com.example.a40_day05_foodorder.databinding.LoginScreenBinding;

import java.util.regex.Pattern;

public class Login extends Fragment {
    LoginScreenBinding binding;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
            "(?=.*[0-9])" +
            "(?=.*[a-z])" +
            "(?=.*[A-Z])" +
            "(?=.*[!@#$%^&*+=,.])" +
            "(?=\\S+$)" +
            ".{8,}" + "$");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_screen,container,false);

        binding.Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.Username.length() == 0){
                    binding.Report.setVisibility(View.VISIBLE);
                    binding.Report.setText("You must enter your account");
                }else if(checkPassword() == true){
                    FoodOrder fo = new FoodOrder();
                    getFragmentManager().beginTransaction().replace(R.id.Container,fo).commit();
                }
            }
        });
        binding.ivLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.ivL.setVisibility(View.VISIBLE);
                binding.ivLO.setVisibility(View.GONE);
                binding.Password.setInputType(InputType.TYPE_CLASS_TEXT);
                binding.Password.setTransformationMethod(null);
            }
        });
        binding.ivL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.ivLO.setVisibility(View.VISIBLE);
                binding.ivL.setVisibility(View.GONE);
                binding.Password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                binding.Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
        return binding.getRoot();
    }

    private boolean checkPassword(){
        String password = binding.Password.getText().toString();
        if(password.trim().isEmpty()){
            binding.Report.setVisibility(View.VISIBLE);
            binding.Report.setText("Password can't be empty");
            return false;
        }else if(password.length() < 8){
            binding.Report.setVisibility(View.VISIBLE);
            binding.Report.setText("Password too weak");
            return false;
        }else if(!PASSWORD_PATTERN.matcher(password).matches()){
            binding.Report.setVisibility(View.VISIBLE);
            binding.Report.setText("Password need to include lower, upper, digit and special characters");
            return false;
        }else{
            binding.Report.setVisibility(View.GONE);
            return true;
        }
    }
}
