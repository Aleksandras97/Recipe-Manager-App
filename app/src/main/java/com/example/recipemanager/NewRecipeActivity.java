package com.example.recipemanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class NewRecipeActivity extends AppCompatActivity {

    ImageView chooseImage;
    Button chooseButton;
    private List<Category> categoryList;
    private MultipleChooseAdapter adapter;
    private static final int PERMISION_CODE = 1001;
    private static final int IMAGE_PICK_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Views
        chooseImage = findViewById(R.id.recipe_image);
        chooseButton = findViewById(R.id.choose_image_btn);

        fillCategoryList();
        OptionsForCategory();

        //Handle button click
        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check runtime permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED){
                        //permission not granted, request it
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISION_CODE);

                    } else {
                        //permission already granted
                        pickImageFromGallery();
                    }
                } else {
                    //system os is less then marshmallow
                    pickImageFromGallery();
                }
            }
        });
    }

    private void OptionsForCategory() {

        //LayoutInflater inflater = this.getLayoutInflater();
        //View filter_layout = inflater.inflate(R.layout.dialog_options, null);

        RecyclerView recycler_options = (RecyclerView) findViewById(R.id.recycle_options);
        recycler_options.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        //recycler_options.setLayoutManager(new LinearLayoutManager(this));
        final MultipleChooseAdapter adapter = new MultipleChooseAdapter(getBaseContext(), categoryList);
        recycler_options.setLayoutManager(layoutManager);
        recycler_options.setAdapter(adapter);
        //recycler_options.set
        //alertDialog.setView(filter_layout);
    }

    public void onClickDone(View view){
        EditText nameView = (EditText) findViewById(R.id.recipeName_detail);
        String nameText = nameView.getText().toString();
        EditText categoryView = (EditText) findViewById(R.id.recipeCategory_detail);
        String categoryText = categoryView.getText().toString();
        SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(this);
        try{
            SQLiteDatabase db = recipeDatabaseHelper.getReadableDatabase();

        } catch (SQLException e){
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        }


        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_NAME, nameText);
        intent.putExtra(MainActivity.EXTRA_CATEGORY, categoryText);
        startActivity(intent);

        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator), "New Recipe created!", Snackbar.LENGTH_SHORT);
        snackbar.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewRecipeActivity.this, "Undone!", Toast.LENGTH_SHORT).show();
            }
        });
        snackbar.show();
    }

    private void pickImageFromGallery() {
        //intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                } else {
                    Toast.makeText(this, "Permision denied...!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE){
            //set image to image view
            chooseImage.setImageURI(data.getData());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void fillCategoryList() {
        categoryList = new ArrayList<>();
        SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(this);
        try {
            SQLiteDatabase db = recipeDatabaseHelper.getReadableDatabase();
            Cursor cursorCategory = db.query("CATEGORY",
                    new String[] {"_id", "NAME"},
                    null, null, null, null, null);
            while(cursorCategory.moveToNext()){
                int id = cursorCategory.getInt(0);
                String name = cursorCategory.getString(1);
                categoryList.add(new Category(id, name));
            }
            cursorCategory.close();
            db.close();
        } catch (SQLException e){
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        }
    }
}
