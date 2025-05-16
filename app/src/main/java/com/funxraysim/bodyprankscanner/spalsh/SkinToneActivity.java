package com.funxraysim.bodyprankscanner.spalsh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.funxraysim.bodyprankscanner.R;
import com.funxraysim.bodyprankscanner.databinding.ActivitySkinToneBinding;

public class SkinToneActivity extends BaseActivity {

    private final int[] normalImages = {
            R.drawable.img_vector_0,
            R.drawable.img_vector_1,
            R.drawable.img_vector_2,
            R.drawable.img_vector_3,
            R.drawable.img_vector_4,
            R.drawable.img_vector_5
    };
    private final int[] selectedImages = {
            R.drawable.img_vector_select_0,
            R.drawable.img_vector_select_1,
            R.drawable.img_vector_select_2,
            R.drawable.img_vector_select_3,
            R.drawable.img_vector_select_4,
            R.drawable.img_vector_select_5
    };
    ActivitySkinToneBinding binding;
    private int selectedIndex = -1; // No selection at start
    private ImageView[] clothButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySkinToneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbarLayout.headerTitle.setText("Select Skin Tone");
        binding.toolbarLayout.btnBack.setOnClickListener(v -> onBackPressed());

        clothButtons = new ImageView[]{
                binding.btnCloth0,
                binding.btnCloth1,
                binding.btnCloth2,
                binding.btnCloth3,
                binding.btnCloth4,
                binding.btnCloth5
        };

        // Set click listeners for each skin tone image
        for (int i = 0; i < clothButtons.length; i++) {
            int finalI = i;
            clothButtons[i].setOnClickListener(v -> updateSelection(finalI));
        }

        binding.btnNext.setOnClickListener(v -> {
            if (selectedIndex == -1) {
                Toast.makeText(this, "Please select a skin tone", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(SkinToneActivity.this, NicknameActivity.class);
                intent.putExtra("selected_skin_index", selectedIndex);
                startActivity(intent);
            }
        });
    }

    private void updateSelection(int index) {
        selectedIndex = index;

        for (int i = 0; i < clothButtons.length; i++) {
            if (i == index) {
                clothButtons[i].setImageResource(selectedImages[i]);
            } else {
                clothButtons[i].setImageResource(normalImages[i]);
            }
        }
    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }
}