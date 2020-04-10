package com.example.recipemanager;

import androidx.annotation.NonNull;
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
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecipeAdapter adapter;
    private List<Recipe> recipeList;
    private List<Category> categoryList;
    private List<Ingredient> ingredientList;
    public static final String EXTRA_NAME = "nameText";
    public static final String EXTRA_CATEGORY = "categoryText";

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

        Intent intent = getIntent();
        String nameText = intent.getStringExtra(EXTRA_NAME);
        String categoryText = intent.getStringExtra(EXTRA_CATEGORY);
        //insertRecipe(nameText, categoryText);
    }

    public void insertRecipe(String name, String category){
        recipeList.add(new Recipe(name, category, R.drawable.ic_image));
        adapter.notifyItemInserted(recipeList.size());
    }

    private void fillRecipeList() {
        recipeList = new ArrayList<>();
        SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(this);
        try {
            SQLiteDatabase db = recipeDatabaseHelper.getReadableDatabase();
            Cursor cursorRecipe = db.query ("RECIPE",
                    new String[] {"_id", "NAME", "INSTRUCTION", "IMAGE_RESOURCE_ID"},
                    null, null, null, null, null);
            while (cursorRecipe.moveToNext()){
                    int id = cursorRecipe.getInt(0);
                    String name = cursorRecipe.getString(1);
                    String instruction = cursorRecipe.getString(2);
                    int photoId = cursorRecipe.getInt(3);
                    recipeList.add(new Recipe(id, name, instruction, photoId));
            }
            cursorRecipe.close();
            db.close();
        } catch (SQLException e){
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        }




//        recipeList.add(new Recipe("Blynai", "Pusryciai", R.drawable.homemadechickenpotpie));
//        recipeList.add(new Recipe("diavolo", "Pica", R.drawable.diavolo));
//        recipeList.add(new Recipe("funghi", "Pica", R.drawable.funghi));
//        recipeList.add(new Recipe("Blynai", "Pusryciai", R.drawable.homemadechickenpotpie));
//        recipeList.add(new Recipe("diavolo", "Pica", R.drawable.diavolo));
//        recipeList.add(new Recipe("funghi", "Pica", R.drawable.funghi));
//        recipeList.add(new Recipe("Blynai", "Pusryciai", R.drawable.homemadechickenpotpie));
//        recipeList.add(new Recipe("diavolo", "Pica", R.drawable.diavolo));
//        recipeList.add(new Recipe("funghi", "Pica", R.drawable.funghi));
    }

    private void setUpRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new RecipeAdapter(recipeList);

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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
