package com.example.githubfetcher.service;

import com.example.githubfetcher.model.Branches;
import com.example.githubfetcher.model.Contributors;
import com.example.githubfetcher.model.Repos;
import com.example.githubfetcher.model.ReposList;
import com.example.githubfetcher.model.User;

import java.util.ArrayList;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public final class GitHubApiService {
    private static volatile GitHubApiService instance = null;
    private GitHubApiGet clients;

    private GitHubApiService() {

        OkHttpClient clientToken = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request newRequest = chain.request().newBuilder()
                    .addHeader("oauth", "Token token=dc1a29be1efaf1f3c574865c69882d14")
                    .build();
            return chain.proceed(newRequest);
        }).build();


        Retrofit retrofit =
                new Retrofit.Builder().baseUrl(ConstantUrl.BASE_URL)
                        .client(clientToken)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create()).build();
        clients = retrofit.create(GitHubApiGet.class);


    }

    public static GitHubApiService getInstance() {
        if (instance == null) {
            synchronized (GitHubApiService.class) {
                if (instance == null) {
                    instance = new GitHubApiService();
                }
            }
        }
        return instance;
    }


    public GitHubApiGet provideClient() {
        return clients;
    }

    public interface GitHubApiGet {


        @GET("users/{username}")
        Call<User> getUser(@Path("username") String username);

        @GET("/users/{username}/repos")
        Call<Repos> getReposList(@Path("username") String user);

        @GET("/search/repositories")
        Observable<ReposList> searchRepos(@Query("q") String query);

        @GET("/repos/{username}/{repos}/branches")
        Observable<ArrayList<Branches>> getBranchesList(@Path("username") String username, @Path("repos") String repos);

        @GET("/repos/{username}/{repos}/contributors")
        Observable<ArrayList<Contributors>> getContributorList(@Path("username") String username, @Path("repos") String repos);


    }
}
