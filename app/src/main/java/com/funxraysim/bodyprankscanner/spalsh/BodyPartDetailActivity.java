package com.funxraysim.bodyprankscanner.spalsh;

import android.content.Intent;
import android.os.Bundle;

import com.funxraysim.bodyprankscanner.R;
import com.funxraysim.bodyprankscanner.databinding.ActivityBodyPartDetailBinding;

public class BodyPartDetailActivity extends BaseActivity {

    private ActivityBodyPartDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBodyPartDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbarLayout.headerTitle.setText("Explore Body Part");
        binding.toolbarLayout.btnBack.setOnClickListener(v -> onBackPressed());
        Intent intent = getIntent();

        int image = intent.getIntExtra("image", R.mipmap.ic_launcher);
        String description = intent.getStringExtra("description");

        binding.imgBodyPart.setImageResource(image);
        binding.txtDescription.setText(description);

    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }
}