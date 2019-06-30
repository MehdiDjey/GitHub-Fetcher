package com.example.githubfetcher.repository;




/*public class ReposDataRepository {
    private Repos repos;
    private List<ReposList> list;
    private GithubApiService githubApiService;
    private CompositeDisposable compositeDisposable;




    private Observable<ReposList> reposReutnr = githubApiService.searchRepos("tom");


    public Observable<List<ReposList>> getReposReutnr() {
        reposReutnr
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ReposList>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(List<ReposList> reposLists) {
                        list = reposLists;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return reposReutnr;
    }
}*/
