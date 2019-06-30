package com.example.githubfetcher.viewmodel;


import androidx.lifecycle.ViewModel;

import com.example.githubfetcher.model.ReposList;
import com.example.githubfetcher.service.GitHubApiService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


@SuppressWarnings("WeakerAccess")
public class SearchViewModel extends ViewModel {


    public Observable<ReposList> loadRepos(String search) {

        return GitHubApiService.getInstance().provideClient()
                .searchRepos(search)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
