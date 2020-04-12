package com.example.recipemanager;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {

    private int id;
    private String name;
    private String instruction;
    private int imageResource;


    public Recipe(String name, String instruction, int imageResource){
        this.name = name;
        this.instruction = instruction;
        this.imageResource = imageResource;
    }

    public Recipe(int id, String name, String instruction, int imageResource){
        this.id = id;
        this.name = name;
        this.instruction = instruction;
        this.imageResource = imageResource;
    }

    protected Recipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        instruction = in.readString();
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

    public int getId() {
        return id;
    }

    public int getImageResource(){
        return imageResource;
    }

    public String getName(){
        return name;
    }

    public String getInstruction(){
        return instruction;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(instruction);
        dest.writeInt(imageResource);
    }
}
