package com.example.recipemanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

public class NewCategoryActivity extends AppCompatActivity {

    EditText editView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        editView = (EditText) findViewById(R.id.editText);
        editView.setImeOptions(EditorInfo.IME_ACTION_DONE);
    }

    public void onClickDone(View view){
        editView = (EditText) findViewById(R.id.editText);
        String categoryText = editView.getText().toString();

        if (categoryText == null){
            Toast.makeText(this, "Category cant be null", Toast.LENGTH_SHORT).show();
        } else {
            SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(this);
            try{
                SQLiteDatabase db = recipeDatabaseHelper.getWritableDatabase();
                ContentValues categoryValues = new ContentValues();
                categoryValues.put("NAME", categoryText);
                db.insert("CATEGORY", null, categoryValues);
                db.close();
            } catch (SQLException e){
                Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
            }
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }
}
