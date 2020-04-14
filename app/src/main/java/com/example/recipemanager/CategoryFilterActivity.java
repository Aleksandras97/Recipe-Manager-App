package com.example.recipemanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CategoryFilterActivity extends AppCompatActivity {

    private RecipeAdapter adapter;
    public static final String EXTRA_FILTERARRAY = "nameText";
    private List<Recipe> recipeList;
    private List<Category> categoryList;
    private ArrayList<Integer> tempCategoryId;
    private ArrayList<Integer> tempRecipeCategoryId;
    private List<RecipeCategory> recipeCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_filter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        fillRecipeList();
        setUpRecycleView();
    }

    private void fillRecipeList() {
        new FillRecipeList().execute();
    }

    private class FillRecipeList extends AsyncTask<Integer, Void, Boolean>{

        private ArrayList<Integer> filteredList;
        protected void onPreExecute(){
            recipeList = new ArrayList<>();
            categoryList = new ArrayList<>();
            recipeCategoryList = new ArrayList<>();
            tempCategoryId = new ArrayList<>();
            tempRecipeCategoryId = new ArrayList<>();
            Intent intent = getIntent();
            filteredList = intent.getIntegerArrayListExtra(EXTRA_FILTERARRAY);
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
            SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(CategoryFilterActivity.this);
            try {
                SQLiteDatabase db = recipeDatabaseHelper.getReadableDatabase();
                Cursor cursorRecipe = db.query ("RECIPE",
                        new String[] {"_id", "NAME", "INSTRUCTION", "IMAGE_RESOURCE_ID"},
                        null, null, null, null, null);
                Cursor cursorRecipeCategory = db.query("RECIPECATEGORY",
                        new String[] {"RECIPE_ID", "CATEGORY_ID"},
                        null, null, null, null, null);
                Cursor cursorCategory = db.query("CATEGORY",
                        new String[] {"_id", "NAME"},
                        null, null, null, null, null);
                while(cursorCategory.moveToNext()){
                    int id = cursorCategory.getInt(0);
                    String name = cursorCategory.getString(1);
                    if (filteredList.contains(id)){
                        categoryList.add(new Category(id, name));
                        tempCategoryId.add(id);
                    }
                }
                while (cursorRecipeCategory.moveToNext()){
                    int recipeId = cursorRecipeCategory.getInt(0);
                    int category_id = cursorRecipeCategory.getInt(1);
                    if (tempCategoryId.contains(category_id)){
                        recipeCategoryList.add(new RecipeCategory(recipeId, category_id));
                        tempRecipeCategoryId.add(recipeId);
                    }
                }
                while (cursorRecipe.moveToNext()){
                    int id = cursorRecipe.getInt(0);
                    String name = cursorRecipe.getString(1);
                    String instruction = cursorRecipe.getString(2);
                    int photoId = cursorRecipe.getInt(3);
                    if (tempRecipeCategoryId.contains(id)){
                        recipeList.add(new Recipe(id, name, instruction, photoId));
                    }
                }

                cursorRecipeCategory.close();
                cursorCategory.close();
                cursorRecipe.close();
                db.close();
                return true;
            } catch (SQLException e){
                return false;
            }
        }

        protected void onPostExecute(Boolean success){
            if (!success){
                Toast.makeText(CategoryFilterActivity.this, "Database unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setUpRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new RecipeAdapter(recipeList, categoryList, recipeCategoryList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new RecipeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(CategoryFilterActivity.this, RecipeDetailActivity.class);
                intent.putExtra("Recipe Item", recipeList.get(position));
                startActivity(intent);

            }
        });
    }
}
