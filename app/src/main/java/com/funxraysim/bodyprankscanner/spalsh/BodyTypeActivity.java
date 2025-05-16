package com.funxraysim.bodyprankscanner.spalsh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.funxraysim.bodyprankscanner.R;
import com.funxraysim.bodyprankscanner.databinding.ActivityBodyTypeBinding;

public class BodyTypeActivity extends BaseActivity {

    ActivityBodyTypeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBodyTypeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbarLayout.headerTitle.setText("Select Body Type");
        binding.toolbarLayout.btnBack.setOnClickListener(v -> onBackPressed());

        binding.btnThin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnThin.setImageResource(R.drawable.img_thin_selected);
                binding.btnSlim.setImageResource(R.drawable.img_slim_unselect);
            }
        });
        binding.btnSlim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnSlim.setImageResource(R.drawable.img_slim_select);
                binding.btnThin.setImageResource(R.drawable.img_thin_unselected);
            }
        });
        binding.btnNext.setOnClickListener(v -> {

            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            String name = sharedPreferences.getString("category", "");


            if (name.equals("btnFullBodyScan")) {

                Intent intent = new Intent(BodyTypeActivity.this, BodyPortionActivity.class);
                startActivity(intent);
            } else if (name.equals("btnEditAvtar")) {

                Intent intent = new Intent(BodyTypeActivity.this, SkinToneActivity.class);
                startActivity(intent);
            }


        });

    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }
}