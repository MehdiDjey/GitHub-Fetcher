package com.example.githubfetcher.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Repos implements Parcelable {
    public static final Creator<Repos> CREATOR = new Creator<Repos>() {
        @Override
        public Repos createFromParcel(Parcel in) {
            return new Repos(in);
        }

        @Override
        public Repos[] newArray(int size) {
            return new Repos[size];
        }
    };
    @SerializedName("name")
    private String name;
    @SerializedName("full_name")
    private String full_name;
    @SerializedName("description")
    private String description;
    @SerializedName("url")
    private String url;
    @SerializedName("contributors_url")
    private String url_contributors;
    @SerializedName("owner")
    private Owner owner;

    protected Repos(Parcel in) {
        name = in.readString();
        full_name = in.readString();
        description = in.readString();
        url = in.readString();
        url_contributors = in.readString();
        owner = in.readParcelable(Owner.class.getClassLoader());
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl_contributors() {
        return url_contributors;
    }

    public void setUrl_contributors(String url_contributors) {
        this.url_contributors = url_contributors;
    }

    @Override
    public String toString() {
        return "Repos{" +
                "name='" + name + '\'' +
                ", full_name='" + full_name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", url_contributors='" + url_contributors + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(full_name);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeString(url_contributors);
        dest.writeParcelable(owner, flags);
    }
}
