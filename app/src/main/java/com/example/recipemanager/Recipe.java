package com.example.recipemanager;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {

    private String name;
    private String category;
    private int imageResource;


    public Recipe(String name, String category, int imageResource){
        this.name = name;
        this.category = category;
        this.imageResource = imageResource;
    }

    protected Recipe(Parcel in) {
        name = in.readString();
        category = in.readString();
        imageResource = in.readInt();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public int getImageResource(){
        return imageResource;
    }

    public String getName(){
        return name;
    }

    public String getCategory(){
        return category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(category);
        dest.writeInt(imageResource);
    }
}
