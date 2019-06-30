package com.example.githubfetcher.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.githubfetcher.databinding.ListReposItemBinding;
import com.example.githubfetcher.model.Repos;
import com.example.githubfetcher.model.ReposList;
import com.example.githubfetcher.view.DetailActivity;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;

public class ListViewSearchAdapter extends RecyclerView.Adapter<ListViewSearchAdapter.ViewHolder> {
    private static final String TAG = ListViewSearchAdapter.class.getSimpleName();

    private final ArrayList<ReposList> repos = new ArrayList<>();
    private final ArrayList<Repos> reposRepo = new ArrayList<>();
    private Context context;

    public ListViewSearchAdapter(Context context) {
        Log.d(TAG, "ListViewSearchAdapter: ");
        this.context = context;
    }

    public void addRepos(ReposList reposList) {
        Log.i(TAG, "addRepos: " + reposList);
        for (int i = 0; i < reposList.getSize(); i++) {
            reposRepo.add(reposList.getItems().get(i));
        }
        repos.add(reposList);
        Log.i(TAG, "addRepos: " + repos);
        this.notifyItemInserted(repos.size() - 1);
        this.notifyDataSetChanged();
    }

    public void clearRepos() {
        reposRepo.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return reposRepo.size();
    }

    @Override
    public @NonNull
    ListViewSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListReposItemBinding binding = ListReposItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binds(reposRepo.get(position));
        holder.binding.setOnClickListener(v -> {
            Log.d(TAG, "onBindViewHolder: " + reposRepo.get(position));
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("username", reposRepo.get(position).getOwner().getLogin());
            intent.putExtra("repos", reposRepo.get(position).getName());
            context.startActivity(intent);

        });

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ListReposItemBinding binding;

        ViewHolder(ListReposItemBinding binding) {
            this(binding.getRoot());
            this.binding = binding;

        }

        ViewHolder(View view) {
            super(view);
        }


        void binds(@NonNull Repos repos) {
            Log.i(TAG, "binds: " + repos);
            binding.setRepos(repos);
            binding.executePendingBindings();
        }
    }
}
