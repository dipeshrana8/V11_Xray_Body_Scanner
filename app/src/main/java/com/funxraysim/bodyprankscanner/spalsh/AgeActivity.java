package com.funxraysim.bodyprankscanner.spalsh;

import static android.view.View.GONE;

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

public class AgeActivity extends BaseActivity {
    private final List<LanguageModel> languageList = new ArrayList<>();
    private ActivityLanguagesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toolbarHeaderText = "Select Age";
        super.onCreate(savedInstanceState);


        binding = ActivityLanguagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showSettings = true;

        binding.toolbarLayout.headerTitle.setText("Select Age");
        binding.toolbarLayout.btnBack.setOnClickListener(v -> onBackPressed());

        setupLanguageList();
        AgeAdapter adapter = new AgeAdapter(languageList);


        binding.languageRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        binding.languageRecyclerView.setAdapter(adapter);


        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AgeActivity.this, BodyTypeActivity.class);
                startActivity(intent);


            }
        });
    }

    private void setupLanguageList() {
        languageList.add(new LanguageModel("15-25", R.drawable.img_india, true));
        languageList.add(new LanguageModel("26-35", R.drawable.img_india, false));
        languageList.add(new LanguageModel("36-45", R.drawable.img_india, false));
        languageList.add(new LanguageModel("46-55", R.drawable.img_india, false));
        languageList.add(new LanguageModel("56-65", R.drawable.img_india, false));
        languageList.add(new LanguageModel("66-75", R.drawable.img_india, false));

    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }

    public class AgeAdapter extends RecyclerView.Adapter<AgeAdapter.AgeViewHolder> {

        private final List<LanguageModel> languageList;
        private int selectedPosition = 0;

        public AgeAdapter(List<LanguageModel> languageList) {
            this.languageList = languageList;
            if (!languageList.isEmpty()) languageList.get(0).setSelected(true);
        }

        @NonNull
        @Override
        public AgeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            ItemLanguageBinding binding = ItemLanguageBinding.inflate(inflater, parent, false);
            return new AgeViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull AgeViewHolder holder, int position) {
            LanguageModel model = languageList.get(position);
            holder.binding.languageName.setText(model.getLanguageName());
            holder.binding.languageFlag.setImageResource(model.getFlagResId());
            holder.binding.languageFlag.setVisibility(GONE);
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

        class AgeViewHolder extends RecyclerView.ViewHolder {
            ItemLanguageBinding binding;

            AgeViewHolder(ItemLanguageBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }
    }

}