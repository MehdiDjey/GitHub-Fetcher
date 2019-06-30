package com.example.githubfetcher.view;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubfetcher.R;
import com.example.githubfetcher.model.Branches;
import com.example.githubfetcher.model.Contributors;
import com.example.githubfetcher.view.adapter.ListViewBranchesAdapter;
import com.example.githubfetcher.view.adapter.ListViewContributorsAdapter;
import com.example.githubfetcher.viewModels.BranchesContributorsViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;


public class DetailActivity extends AppCompatActivity {
    private static final String TAG = DetailActivity.class.getSimpleName();
    private final CompositeDisposable subscriptions = new CompositeDisposable();
    BranchesContributorsViewModel viewModel;
    String username;
    String reposName;
    @BindView(R.id.listContribut)
    RecyclerView listViewContribut;
    @BindView(R.id.listBranches)
    RecyclerView listViewBranche;
    private ListViewBranchesAdapter adapter;
    private ListViewContributorsAdapter adapterContribut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.d(TAG, "onCreate: ");
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(BranchesContributorsViewModel.class);
        initAdapter();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
            reposName = extras.getString("repos");
            subscriptions.add(viewModel.loadBranches(username, reposName).subscribe(this::onResponse, this::onFailure));
            subscriptions.add(viewModel.loadContributors(username, reposName).subscribe(this::onResponses, this::onFailures));
        }

    }

    private void onFailures(Throwable throwable) {
        Log.e(TAG, throwable.getMessage() + " [Contribut]");
    }

    private void onResponses(ArrayList<Contributors> contributors) {
        adapterContribut.addContributor(contributors);
        Log.i(TAG, "onResponses: " + contributors);
    }

    private void onFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage() + " [Branches]");
    }

    private void onResponse(ArrayList<Branches> branches) {
        adapter.addBranch(branches);
        Log.i(TAG, "onResponse: " + branches);
    }

    public void showCont(View view) {
        listViewBranche.setVisibility(View.GONE);
        listViewContribut.setVisibility(View.VISIBLE);
    }

    public void ShowBranche(View view) {
        listViewContribut.setVisibility(View.GONE);
        listViewBranche.setVisibility(View.VISIBLE);
    }

    void initAdapter() {
        Log.d(TAG, "initAdapter: ");
        listViewBranche.setHasFixedSize(true);
        listViewBranche.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListViewBranchesAdapter(this);
        listViewBranche.setAdapter(adapter);


        listViewContribut.setHasFixedSize(true);
        listViewContribut.setLayoutManager(new LinearLayoutManager(this));
        adapterContribut = new ListViewContributorsAdapter(this);
        listViewContribut.setAdapter(adapterContribut);

        listViewBranche.setNestedScrollingEnabled(false);
        listViewContribut.setNestedScrollingEnabled(false);

        listViewBranche.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        listViewContribut.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }
}
