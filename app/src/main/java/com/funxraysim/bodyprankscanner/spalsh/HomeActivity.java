package com.funxraysim.bodyprankscanner.spalsh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.funxraysim.bodyprankscanner.databinding.ActivityHomeBinding;

public class HomeActivity extends BaseActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toolbarHeaderText = "Body Scanner";
        showBackButton = false;

        super.onCreate(savedInstanceState);


        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbarLayout.headerTitle.setText("Body Scanner");
        binding.toolbarLayout.btnBack.setOnClickListener(v -> onBackPressed());

        binding.btnFullBodyScan.setOnClickListener(v -> {

            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("category", "btnFullBodyScan");
            editor.apply();


            Intent intent = new Intent(HomeActivity.this, SelectGenderActivity.class);
            startActivity(intent);
        });

        binding.btnExploreBodyPart.setOnClickListener(v -> {

            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("category", "btnExploreBodyPart");
            editor.apply();

            Intent intent = new Intent(HomeActivity.this, SelectGenderActivity.class);
            startActivity(intent);
        });

        binding.btnEditAvtar.setOnClickListener(v -> {

            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("category", "btnEditAvtar");
            editor.apply();

            Intent intent = new Intent(HomeActivity.this, SelectGenderActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(HomeActivity.this, A7_ExitActivity.class);
        startActivity(intent);


    }


}