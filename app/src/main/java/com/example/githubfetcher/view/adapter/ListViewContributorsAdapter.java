package com.example.githubfetcher.view.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.githubfetcher.databinding.ItemDetailContributorBinding;
import com.example.githubfetcher.model.Contributors;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;

public class ListViewContributorsAdapter extends RecyclerView.Adapter<ListViewContributorsAdapter.ViewHolder> {
    private static final String TAG = ListViewContributorsAdapter.class.getSimpleName();

    private final ArrayList<Contributors> itemsContributor = new ArrayList<>();


    Context context;

    public ListViewContributorsAdapter(Context context) {
        Log.d(TAG, "ListViewContributorsAdapter: ");
        this.context = context;
    }


    public void addContributor(ArrayList<Contributors> contributors) {
        Log.i(TAG, "addContributor: " + contributors);
        itemsContributor.addAll(contributors);
        Log.i(TAG, "addContributor: " + itemsContributor);
        this.notifyItemInserted(itemsContributor.size() - 1);
    }

    @Override
    public int getItemCount() {
        return itemsContributor.size();
    }

    @Override
    public @NonNull
    ListViewContributorsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDetailContributorBinding binding = ItemDetailContributorBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindContribut(itemsContributor.get(position));

    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemDetailContributorBinding binding;

        ViewHolder(ItemDetailContributorBinding binding) {
            this(binding.getRoot());
            this.binding = binding;


        }

        ViewHolder(View view) {
            super(view);
        }


        void bindContribut(@NonNull Contributors repos) {
            Log.i(TAG, "bindContribut: " + repos);
            binding.setContributors(repos);
            binding.executePendingBindings();
        }


    }
}