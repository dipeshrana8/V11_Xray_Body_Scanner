package com.funxraysim.bodyprankscanner.spalsh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.funxraysim.bodyprankscanner.R;
import com.funxraysim.bodyprankscanner.databinding.ActivityEditClotheBinding;

import java.io.IOException;
import java.io.OutputStream;

public class EditClotheActivity extends BaseActivity {

    private ActivityEditClotheBinding binding;
    private Bitmap currentBitmap;
    private Uri downloadedImageUri;

    private int currentDrawableId = R.drawable.img_cloth_1; // Default

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditClotheBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbarLayout.headerTitle.setText("Edit Clothe");
        binding.toolbarLayout.btnBack.setOnClickListener(v -> onBackPressed());
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");

        binding.txtName.setText(name);
        binding.imgBackground.setImageResource(currentDrawableId);

        // Cloth buttons
        binding.btnCloth1.setOnClickListener(v -> updateCloth(R.drawable.img_cloth_1));
        binding.btnCloth2.setOnClickListener(v -> updateCloth(R.drawable.img_cloth_2));
        binding.btnCloth3.setOnClickListener(v -> updateCloth(R.drawable.img_cloth_3));

        // Save button
        binding.btnSave.setOnClickListener(v -> {
            binding.btnSave.setVisibility(View.GONE);
            binding.btnShareAvtar.setVisibility(View.VISIBLE);
            binding.btnDownLoad.setVisibility(View.VISIBLE);
        });

        // Download button
        binding.btnDownLoad.setOnClickListener(v -> {

            currentBitmap = getBitmapFromImageView(binding.imgBackground);

            if (downloadedImageUri != null) {
                Toast.makeText(this, "Image already downloaded", Toast.LENGTH_SHORT).show();
            } else {
                launchDocumentTreeToSave();
            }
        });

        // Share button
        binding.btnShareAvtar.setOnClickListener(v -> {
            if (downloadedImageUri != null) {
                shareImage(downloadedImageUri);
            } else {
                Toast.makeText(this, "Download the image first", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateCloth(int drawableId) {
        currentDrawableId = drawableId;
        binding.imgBackground.setImageResource(drawableId);
        downloadedImageUri = null; // Reset so image can be downloaded again
    }

    private Bitmap getBitmapFromImageView(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(
                imageView.getWidth(),
                imageView.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private void launchDocumentTreeToSave() {
        String filename = "cloth_" + System.currentTimeMillis() + ".jpg";

        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_TITLE, filename);
        startActivityForResult(intent, 101);
    }

    private void saveBitmapToUri(Uri uri, Bitmap bitmap) {
        try (OutputStream out = getContentResolver().openOutputStream(uri)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            downloadedImageUri = uri;
            Toast.makeText(this, "Image downloaded successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void shareImage(Uri uri) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpeg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "Share Image"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            saveBitmapToUri(uri, currentBitmap);
        }
    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }
}
