package com.example.recipemanager;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> implements Filterable {
    private List<Recipe> recipeList;
    private List<Category> categoryList;
    private List<RecipeCategory> recipeCategoryList;
    private List<Recipe> recipeListFull;
    private OnItemClickListener mListener;
    private String categories = "";

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;

    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder{
        public ImageView recipeImage;
        public TextView recipeName;
        public TextView recipeCategories;


        public RecipeViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.recipe_image);
            recipeName = itemView.findViewById(R.id.recipe_name);
            recipeCategories = itemView.findViewById(R.id.recipe_category);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public RecipeAdapter(List<Recipe> recipeList, List<Category> categoryList, List<RecipeCategory> recipeCategoryList){
        this.recipeList = recipeList;
        this.categoryList = categoryList;
        this.recipeCategoryList = recipeCategoryList;
        recipeListFull = new ArrayList<>(recipeList);
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        RecipeViewHolder vh = new RecipeViewHolder(v, mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe currentRecipe = recipeList.get(position);
        int recipeId = currentRecipe.getId();
        for (Category category : categoryList){
            for (RecipeCategory item : recipeCategoryList){
                if (recipeId == item.getRecipe_id() && category.getId() == item.getCategory_id()){
                    categories = categories + category.getName() + " ";
                }
            }
        }

        holder.recipeName.setText(currentRecipe.getName());
        holder.recipeCategories.setText(categories);
        holder.recipeImage.setImageResource(currentRecipe.getImageResource());
        categories = "";

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    @Override
    public Filter getFilter() {
        return recipeFilter;
    }

    private Filter recipeFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<Recipe> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(recipeListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Recipe item : recipeListFull){
                    int recipeId = item.getId();
                    categories = "";
                    for (Category category : categoryList){
                        for (RecipeCategory recipeCategory : recipeCategoryList){
                            if (recipeId == recipeCategory.getRecipe_id() &&
                                    category.getId() == recipeCategory.getCategory_id()){
                                categories = categories + category.getName() + " ";
                            }
                        }
                    }

                        if (categories.toLowerCase().contains(filterPattern)){
                            filteredList.add(item);
                        }
                }
                categories = "";
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            recipeList.clear();
            recipeList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
