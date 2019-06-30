package com.example.githubfetcher.viewmodel;


import androidx.lifecycle.ViewModel;

import com.example.githubfetcher.model.Branches;
import com.example.githubfetcher.model.Contributors;
import com.example.githubfetcher.service.GitHubApiService;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BranchesContributorsViewModel extends ViewModel {

    public Observable<ArrayList<Contributors>> loadContributors(String username, String reposname) {
        return GitHubApiService.getInstance().provideClient()
                .getContributorList(username, reposname)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ArrayList<Branches>> loadBranches(String username, String reposname) {
        return GitHubApiService.getInstance().provideClient()
                .getBranchesList(username, reposname)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
