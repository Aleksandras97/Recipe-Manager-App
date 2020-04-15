package com.example.recipemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecipeAdapter adapter;
    private List<Recipe> recipeList;
    private List<Category> categoryList;
    private List<RecipeCategory> recipeCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        fillRecipeList();
        setUpRecycleView();
    }

    private void fillRecipeList() {
        recipeList = new ArrayList<>();
        categoryList = new ArrayList<>();
        recipeCategoryList = new ArrayList<>();
        SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(MainActivity.this);
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
            while (cursorRecipe.moveToNext()){
                int id = cursorRecipe.getInt(0);
                String name = cursorRecipe.getString(1);
                String instruction = cursorRecipe.getString(2);
                int photoId = cursorRecipe.getInt(3);
                recipeList.add(new Recipe(id, name, instruction, photoId));
            }
            while(cursorCategory.moveToNext()){
                int id = cursorCategory.getInt(0);
                String name = cursorCategory.getString(1);
                categoryList.add(new Category(id, name));
            }
            while (cursorRecipeCategory.moveToNext()){
                int recipeId = cursorRecipeCategory.getInt(0);
                int category_id = cursorRecipeCategory.getInt(1);
                recipeCategoryList.add(new RecipeCategory(recipeId, category_id));
            }
            cursorRecipe.close();
            cursorCategory.close();
            cursorRecipeCategory.close();
            db.close();
        } catch (SQLException e){
            Toast.makeText(MainActivity.this, "Database unavailable", Toast.LENGTH_SHORT).show();

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
                Intent intent = new Intent(MainActivity.this, RecipeDetailActivity.class);
                intent.putExtra("Recipe Item", recipeList.get(position));
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create_recipe:
                Intent intent = new Intent(this, NewRecipeActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_filter:
                showOptionsDialog();
                return true;
            case R.id.action_create_category:
                Intent intent1 = new Intent(this, NewCategoryActivity.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




    private void showOptionsDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Select Category");

        LayoutInflater inflater = this.getLayoutInflater();
        View filter_layout = inflater.inflate(R.layout.dialog_options, null);

        RecyclerView recycler_options = (RecyclerView) filter_layout.findViewById(R.id.recycle_options);
        recycler_options.setHasFixedSize(true);
        recycler_options.setLayoutManager(new GridLayoutManager(this, 2));
        final MultipleChooseAdapter adapter = new MultipleChooseAdapter(getBaseContext(), categoryList);
        recycler_options.setAdapter(adapter);

        alertDialog.setView(filter_layout);
        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 dialog.dismiss();
            }
        });
        alertDialog.setPositiveButton("FILTER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fetchFilterCategory(adapter.getFilterArray());
            }
        });

        alertDialog.show();
    }

    private void fetchFilterCategory(List<Integer> filterList){
        ArrayList<Integer> filterArray = (ArrayList<Integer>) filterList;
        Intent intent = new Intent(this, CategoryFilterActivity.class);
        intent.putIntegerArrayListExtra(CategoryFilterActivity.EXTRA_FILTERARRAY, filterArray);
        startActivity(intent);
    }
}
