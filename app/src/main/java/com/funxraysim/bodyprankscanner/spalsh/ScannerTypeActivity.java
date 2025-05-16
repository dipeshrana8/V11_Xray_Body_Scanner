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

public class ScannerTypeActivity extends BaseActivity {
    private final List<LanguageModel> languageList = new ArrayList<>();
    private ActivityLanguagesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toolbarHeaderText = "Select Scanner Type";
        super.onCreate(savedInstanceState);


        binding = ActivityLanguagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbarLayout.headerTitle.setText("Select Scanner Type");
        showSettings = true;


        setupLanguageList();
        LanguageAdapter adapter = new LanguageAdapter(languageList);


        binding.languageRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        binding.languageRecyclerView.setAdapter(adapter);


        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ScannerTypeActivity.this, AddImageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupLanguageList() {
        languageList.add(new LanguageModel("Muscular Type", R.drawable.img_muscular_type, true));
        languageList.add(new LanguageModel("Vascular Type", R.drawable.img_vascular_type, false));
        languageList.add(new LanguageModel("Skeletal Type", R.drawable.img_skeletal_type, false));
        languageList.add(new LanguageModel("Nervous Type", R.drawable.img_nervous_type, false));


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