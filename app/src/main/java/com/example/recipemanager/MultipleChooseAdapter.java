package com.example.recipemanager;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MultipleChooseAdapter extends RecyclerView.Adapter<MultipleChooseAdapter.ViewHolder> {

    Context context;
    List<Category> categoryList;
    SparseBooleanArray itemStateArray = new SparseBooleanArray();
    List<Category> selectedCategotyList = new ArrayList<>();

    public MultipleChooseAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox ckb_options;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ckb_options = (CheckBox)itemView.findViewById(R.id.check_options);
            ckb_options.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int adapterPosition = getAdapterPosition();
                    itemStateArray.put(adapterPosition, isChecked);
                    if (isChecked){
                        selectedCategotyList.add(categoryList.get(adapterPosition));
                    } else {
                        selectedCategotyList.remove(categoryList.get(adapterPosition));
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.check_item, parent, false);
        return new ViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ckb_options.setText(categoryList.get(position).getName());
        holder.ckb_options.setChecked(itemStateArray.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public List<Integer> getFilterArray(){
        List<Integer> id_selected = new ArrayList<>();
        for (Category category:selectedCategotyList){
            id_selected.add(category.getId());
        }
        return id_selected;
    }
}
