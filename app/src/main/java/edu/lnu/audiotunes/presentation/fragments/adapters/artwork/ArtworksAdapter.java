package edu.lnu.audiotunes.presentation.fragments.adapters.artwork;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.lnu.audiotunes.R;
import edu.lnu.audiotunes.presentation.base.models.IArtworkModel;
import edu.lnu.audiotunes.presentation.fragments.adapters.artwork.holders.ArtworkViewHolder;
import edu.lnu.audiotunes.presentation.fragments.adapters.artwork.holders.OnItemClickedListener;
import edu.lnu.audiotunes.presentation.fragments.adapters.artwork.holders.StarButtonListener;

import java.util.ArrayList;

public class ArtworksAdapter<M extends IArtworkModel> extends RecyclerView.Adapter<ArtworkViewHolder> {

    private OnItemClickedListener itemClickedListener;
    private StarButtonListener starButtonlistener;

    private ArrayList<M> data;
    public void setData(ArrayList<M> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setItemClickedListener(OnItemClickedListener itemClickedListener) {
        this.itemClickedListener = itemClickedListener;
    }

    public void setStarButtonlistener(StarButtonListener starButtonlistener) {
        this.starButtonlistener = starButtonlistener;
    }

    public ArtworksAdapter() {
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public ArtworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_layout, parent, false);

        ArtworkViewHolder holder = new ArtworkViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArtworkViewHolder holder, int position) {
        holder.bind(data.get(position));
        holder.setOnClickListener(itemClickedListener);
        holder.setOnStarClickListener(starButtonlistener);
    }

    @Override
    public void onViewRecycled(@NonNull ArtworkViewHolder holder) {
        super.onViewRecycled(holder);
        holder.unbind();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
