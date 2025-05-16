package com.funxraysim.bodyprankscanner.spalsh;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.funxraysim.bodyprankscanner.R;
import com.funxraysim.bodyprankscanner.databinding.ActivityLanguagesBinding;
import com.funxraysim.bodyprankscanner.databinding.ItemLanguageBinding;
import com.funxraysim.bodyprankscanner.model.LanguageModel;

import java.util.ArrayList;
import java.util.List;

public class BodyPartActivity extends BaseActivity {
    private final List<LanguageModel> languageList = new ArrayList<>();
    private ActivityLanguagesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toolbarHeaderText = "Select Body Part";
        super.onCreate(savedInstanceState);


        binding = ActivityLanguagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showSettings = true;
        binding.toolbarLayout.headerTitle.setText("Select Body Part");
        binding.toolbarLayout.btnBack.setOnClickListener(v -> onBackPressed());
        setupLanguageList();
        LanguageAdapter adapter = new LanguageAdapter(languageList);


        binding.languageRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        binding.languageRecyclerView.setAdapter(adapter);


        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BodyPartActivity.this, ScannerTypeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupLanguageList() {
        languageList.add(new LanguageModel("Brain", R.drawable.img_brain, true));
        languageList.add(new LanguageModel("Heart", R.drawable.img_heart, false));
        languageList.add(new LanguageModel("Endocrine", R.drawable.img_endocrine, false));
        languageList.add(new LanguageModel("Stomach", R.drawable.img_stomach, false));
        languageList.add(new LanguageModel("Lungs", R.drawable.img_lungs, false));
        languageList.add(new LanguageModel("Other", R.drawable.img_others, false));


    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }

    public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder> {

        private final List<LanguageModel> languageList;
        private int selectedPosition = 0;

        public LanguageAdapter(List<LanguageModel> languageList) {
            this.languageList = languageList;
            if (!languageList.isEmpty()) languageList.get(0).setSelected(true);
        }

        @NonNull
        @Override
        public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            ItemLanguageBinding binding = ItemLanguageBinding.inflate(inflater, parent, false);
            return new LanguageViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull LanguageViewHolder holder, int position) {
            LanguageModel model = languageList.get(position);
            holder.binding.languageName.setText(model.getLanguageName());
            holder.binding.languageFlag.setImageResource(model.getFlagResId());

            if (model.isSelected()) {
                holder.binding.getRoot().setBackgroundResource(R.drawable.bg_country_select);
                holder.binding.imgSelect.setImageResource(R.drawable.ic_selected);

            } else {
                holder.binding.getRoot().setBackgroundResource(R.drawable.bg_country_unselect);
                holder.binding.imgSelect.setImageResource(R.drawable.ic_unselected);

            }

            holder.binding.getRoot().setOnClickListener(v -> {
                if (selectedPosition != position) {
                    languageList.get(selectedPosition).setSelected(false);
                    model.setSelected(true);
                    notifyItemChanged(selectedPosition);
                    notifyItemChanged(position);
                    selectedPosition = position;
                }
            });
        }


        @Override
        public int getItemCount() {
            return languageList.size();
        }

        class LanguageViewHolder extends RecyclerView.ViewHolder {
            ItemLanguageBinding binding;

            LanguageViewHolder(ItemLanguageBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }
    }
}