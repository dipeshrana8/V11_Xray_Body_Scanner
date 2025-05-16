package com.funxraysim.bodyprankscanner.spalsh;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.Nullable;

import com.funxraysim.bodyprankscanner.R;
import com.funxraysim.bodyprankscanner.databinding.ActivityAddImageBinding;

public class AddImageActivity extends BaseActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ActivityAddImageBinding binding;
    private boolean isImageSelected = false;
    private boolean isDoneState = false; // Track state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Header title if needed

        binding.toolbarLayout.headerTitle.setText("Add Image");
        binding.toolbarLayout.btnBack.setOnClickListener(v -> onBackPressed());
        binding.btnNext.setOnClickListener(v -> {

            if (!isImageSelected) {
                openGallery();
            } else if (!isDoneState) {
                // Change to done state
                binding.btnNext.setImageResource(R.drawable.img_done);
                isDoneState = true;
            } else {

                Intent intent = new Intent(AddImageActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            binding.btnAddImage.setImageURI(imageUri);
            binding.imgTxtPreview.setImageResource(R.drawable.img_add_image_preview_txt);
            binding.btnNext.setImageResource(R.drawable.img_done);
            isDoneState = true;
            isImageSelected = true;
        }
    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }
}