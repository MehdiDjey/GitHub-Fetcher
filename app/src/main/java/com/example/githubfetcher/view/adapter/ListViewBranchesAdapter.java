package com.example.githubfetcher.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.githubfetcher.databinding.ItemDetailBinding;
import com.example.githubfetcher.model.Branches;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;

public class ListViewBranchesAdapter extends RecyclerView.Adapter<ListViewBranchesAdapter.ViewHolder> {
    private static final String TAG = ListViewBranchesAdapter.class.getSimpleName();
    private final ArrayList<Branches> itemsBranches = new ArrayList<>();


    Context context;

    public ListViewBranchesAdapter(Context context) {
        Log.d(TAG, "ListViewBranchesAdapter: ");
        this.context = context;
    }


    public void addBranch(ArrayList<Branches> item) {
        Log.i(TAG, "addBranch: " + item);
        itemsBranches.addAll(item);
        Log.i(TAG, "addBranch: " + itemsBranches);
        this.notifyItemInserted(itemsBranches.size() - 1);
    }

    @Override
    public int getItemCount() {
        return itemsBranches.size();
    }

    @Override
    public @NonNull
    ListViewBranchesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDetailBinding binding = ItemDetailBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bindBranch(itemsBranches.get(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemDetailBinding binding;

        ViewHolder(ItemDetailBinding binding) {
            this(binding.getRoot());
            this.binding = binding;


        }

        ViewHolder(View view) {
            super(view);
        }


        void bindBranch(@NonNull Branches repos) {
            Log.i(TAG, "bindBranch: " + repos);
            binding.setBranches(repos);
            binding.executePendingBindings();
        }
    }
}
