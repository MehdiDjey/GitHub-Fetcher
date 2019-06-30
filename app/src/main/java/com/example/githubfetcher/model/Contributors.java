package com.example.githubfetcher.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Contributors implements Parcelable {
    public static final Creator<Contributors> CREATOR = new Creator<Contributors>() {
        @Override
        public Contributors createFromParcel(Parcel in) {
            return new Contributors(in);
        }

        @Override
        public Contributors[] newArray(int size) {
            return new Contributors[size];
        }
    };
    @SerializedName("login")
    private String login_contributor;
    @SerializedName("avatar_url")
    private String avatar_url_contributor;

    protected Contributors(Parcel in) {
        login_contributor = in.readString();
        avatar_url_contributor = in.readString();
    }

    public String getLogin_contributor() {
        return login_contributor;
    }

    public void setLogin_contributor(String login_contributor) {
        this.login_contributor = login_contributor;
    }

    public String getAvatar_url_contributor() {
        return avatar_url_contributor;
    }

    public void setAvatar_url_contributor(String avatar_url_contributor) {
        this.avatar_url_contributor = avatar_url_contributor;
    }

    @Override
    public String toString() {
        return "Contributors{" +
                "login_contributor='" + login_contributor + '\'' +
                ", avatar_url_contributor='" + avatar_url_contributor + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login_contributor);
        dest.writeString(avatar_url_contributor);
    }
}
