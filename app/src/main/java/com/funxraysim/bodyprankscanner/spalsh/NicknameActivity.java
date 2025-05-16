package com.funxraysim.bodyprankscanner.spalsh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.funxraysim.bodyprankscanner.databinding.ActivityCalculatorPreviewBinding;

public class NicknameActivity extends BaseActivity {

    ActivityCalculatorPreviewBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalculatorPreviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbarLayout.headerTitle.setText("Select Your Nickname");
        binding.toolbarLayout.btnBack.setOnClickListener(v -> onBackPressed());


        showSettings = true;

        setupToolbar(
                binding.toolbarLayout.headerTitle,
                binding.toolbarLayout.btnBack,
                binding.toolbarLayout.btnSettings
        );


        setupListeners();

    }


    private void setupListeners() {

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.etCal.getText().toString().trim();

                if (name.isEmpty()) {
                    Toast.makeText(NicknameActivity.this, "Add name first", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name", name);
                    editor.apply();

                    Intent intent = new Intent(NicknameActivity.this, EditClotheActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        myBackActivity();
    }

}