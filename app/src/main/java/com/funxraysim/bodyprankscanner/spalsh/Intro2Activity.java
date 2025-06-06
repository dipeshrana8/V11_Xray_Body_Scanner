package com.funxraysim.bodyprankscanner.spalsh;

import android.content.Intent;
import android.os.Bundle;

import com.funxraysim.bodyprankscanner.databinding.ActivityEveryDayBinding;

public class Intro2Activity extends BaseActivity {

    ActivityEveryDayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEveryDayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnNext.setOnClickListener(v -> {

            Intent intent = new Intent(Intro2Activity.this, HomeActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }
}