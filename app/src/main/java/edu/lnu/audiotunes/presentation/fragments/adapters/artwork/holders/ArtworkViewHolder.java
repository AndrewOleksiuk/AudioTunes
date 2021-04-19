package  edu.lnu.audiotunes.presentation.fragments.adapters.artwork.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.like.LikeButton;
import com.like.OnLikeListener;
import  edu.lnu.audiotunes.R;

import edu.lnu.audiotunes.presentation.base.models.IArtworkModel;

public class ArtworkViewHolder<M extends IArtworkModel> extends RecyclerView.ViewHolder {
    private TextView artistName;
    private TextView name;
    private ImageView artworkImage;
    private LikeButton starButton;

    public ArtworkViewHolder(@NonNull View itemView) {
        super(itemView);
        artistName = itemView.findViewById(R.id.artistName);
        name = itemView.findViewById(R.id.name);
        artworkImage = itemView.findViewById(R.id.artworkImage);
        starButton = itemView.findViewById(R.id.starButton);
    }



    public void bind(M model) {
        name.setText(model.getName());
        artistName.setText(model.getArtistName());
        starButton.setLiked(model.isFavourite());
        Glide.with(itemView)
                .load(model.getImageLink())
                .transform(new RoundedCorners(20))
                .into(artworkImage);
    }
    public void setOnClickListener(OnItemClickedListener listener) {
        itemView.setOnClickListener((View view) -> listener.onClick(itemView, getAdapterPosition()));
    }
    public void setOnStarClickListener(StarButtonListener listener){
        starButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                listener.onLike(getAdapterPosition());
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                listener.onUnlike(getAdapterPosition());
            }
        });
    }
    public void unbind() {
        this.itemView.setOnClickListener(null);
        this.starButton.setOnLikeListener(null);
    }
}
