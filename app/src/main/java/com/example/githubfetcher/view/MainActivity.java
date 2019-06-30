package com.example.githubfetcher.view;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubfetcher.R;
import com.example.githubfetcher.model.ReposList;
import com.example.githubfetcher.view.adapter.ListViewSearchAdapter;
import com.example.githubfetcher.viewModels.SearchViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private final CompositeDisposable subscriptions = new CompositeDisposable();
    @BindView(R.id.listview)
    RecyclerView listView;
    @BindView(R.id.search_view)
    SearchView searchView;
    private ListViewSearchAdapter adapter;
    private SearchViewModel viewModel;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        ButterKnife.bind(this);
        inidAdapter();
        searchQuery();
    }

    void inidAdapter() {
        Log.d(TAG, "inidAdapter: ");
        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(this));
        context = this;
        adapter = new ListViewSearchAdapter(context);
        listView.setAdapter(adapter);
        listView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    void searchQuery() {
        Log.d(TAG, "searchQuery: ");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d(TAG, "onQueryTextSubmit: " + s);
                subscriptions.add(viewModel.loadRepos(s.trim()).subscribe(this::onResponse, this::onFailure));

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(TAG, "onQueryTextChange: " + s);
                adapter.clearRepos();
                return false;
            }

            private void onFailure(Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
            }

            private void onResponse(ReposList reposList) {
                Log.i(TAG, "onResponse: " + reposList);
                adapter.addRepos(reposList);
            }
        });

    }
}
