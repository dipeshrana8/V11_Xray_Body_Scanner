package com.funxraysim.bodyprankscanner.spalsh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.funxraysim.bodyprankscanner.databinding.ActivityExploreBinding;

public class Intro1Activity extends BaseActivity {

    ActivityExploreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExploreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnNext.setOnClickListener(v -> {

            Intent intent = new Intent(Intro1Activity.this, CountryActivity.class);
            startActivity(intent);
        });

        binding.btnPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intro1Activity.this, A4_PrivacyActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }
}