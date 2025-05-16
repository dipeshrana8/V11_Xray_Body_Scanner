package com.funxraysim.bodyprankscanner.spalsh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.funxraysim.bodyprankscanner.databinding.ActivitySkinToneBinding;

public class SkinToneActivity extends BaseActivity {

    ActivitySkinToneBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySkinToneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbarLayout.headerTitle.setText("Select Skin Tone");
        binding.toolbarLayout.btnBack.setOnClickListener(v -> onBackPressed());


        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SkinToneActivity.this, NicknameActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }
}