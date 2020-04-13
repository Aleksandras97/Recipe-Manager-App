package com.example.recipemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    Data data = new Data();
    private static final String TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onClickRecipes(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
