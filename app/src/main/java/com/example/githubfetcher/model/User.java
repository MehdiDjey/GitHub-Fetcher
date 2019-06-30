package com.example.githubfetcher.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    @SerializedName("login")
    private String login_user;
    @SerializedName("avatar_url")
    private String avatar_url_user;
    @SerializedName("url")
    private String url_user;

    protected User(Parcel in) {
        login_user = in.readString();
        avatar_url_user = in.readString();
        url_user = in.readString();
    }

    public String getLogin_user() {
        return login_user;
    }

    public void setLogin_user(String login_user) {
        this.login_user = login_user;
    }

    public String getAvatar_url_user() {
        return avatar_url_user;
    }

    public void setAvatar_url_user(String avatar_url_user) {
        this.avatar_url_user = avatar_url_user;
    }

    public String getUrl_user() {
        return url_user;
    }

    public void setUrl_user(String url_user) {
        this.url_user = url_user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login_user);
        dest.writeString(avatar_url_user);
        dest.writeString(url_user);
    }

    @Override
    public String toString() {
        return "User{" +
                "login_user='" + login_user + '\'' +
                ", avatar_url_user='" + avatar_url_user + '\'' +
                ", url_user='" + url_user + '\'' +
                '}';
    }
}
