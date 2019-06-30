package com.example.githubfetcher.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Branches implements Parcelable {
    public static final Creator<Branches> CREATOR = new Creator<Branches>() {
        @Override
        public Branches createFromParcel(Parcel in) {
            return new Branches(in);
        }

        @Override
        public Branches[] newArray(int size) {
            return new Branches[size];
        }
    };
    @SerializedName("name")
    private String name_branche;

    protected Branches(Parcel in) {
        name_branche = in.readString();
    }

    public String getName_branche() {
        return name_branche;
    }

    public void setName_branche(String name_branche) {
        this.name_branche = name_branche;
    }

    @Override
    public String toString() {
        return "Branches{" +
                "name_branche='" + name_branche + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name_branche);
    }
}
