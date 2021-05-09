package edu.lnu.audiotunes.presentation.fragments.podcastInfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import edu.lnu.audiotunes.R;
import edu.lnu.audiotunes.data.network.features.info.podcastInfo.models.PodcastInfoModel;
import edu.lnu.audiotunes.presentation.base.BaseFragment;
import edu.lnu.audiotunes.presentation.fragments.podcastInfo.presentation.IPodcastInfoView;
import edu.lnu.audiotunes.presentation.fragments.podcastInfo.presentation.PodcastInfoPresenter;

public class PodcastInfoFragment extends BaseFragment<PodcastInfoPresenter, IPodcastInfoView>
        implements IPodcastInfoView {

    private static final String ARTWORK_ID = "artwork_id";

    public static PodcastInfoFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(ARTWORK_ID, id);
        PodcastInfoFragment fragment = new PodcastInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    private TextView name;
    @Nullable
    private TextView artwistName;
    @Nullable
    private TextView price;
    @Nullable
    private TextView rentalPrice;
    @Nullable
    private TextView genre;
    @Nullable
    private TextView country;
    @Nullable
    private ImageView artworkImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_podcast_info, container, false);
    }

    @Override
    public void createPresenterInstance() {
        presenter = new PodcastInfoPresenter();
    }

    @Override
    public void initPresenter() {
        presenter.attachView(this);
        presenter.initArtworkId(getArguments().getInt(ARTWORK_ID));
    }

    @Override
    public void initViews(View view) {
        name = view.findViewById(R.id.name);
        artwistName = view.findViewById(R.id.artistName);
        price = view.findViewById(R.id.price);
        rentalPrice = view.findViewById(R.id.rentalPrice);
        genre = view.findViewById(R.id.genre);
        country = view.findViewById(R.id.country);
        artworkImage = view.findViewById(R.id.artworkImage);
    }

    @Override
    public void setData(PodcastInfoModel model) {
        if(model.getName() == null)
            name.setVisibility(View.GONE);
        else
            name.setText(model.getName());

        if(model.getArtistName() == null)
            artwistName.setVisibility(View.GONE);
        else
            artwistName.setText(model.getArtistName());

        if(model.getPrice() == null)
            price.setVisibility(View.GONE);
        else if (model.getPrice() == 0.f)
            price.setText("Price: Free");
        else
            price.setText("Price: " + model.getPrice().toString());

        if(rentalPrice == null)
            rentalPrice.setVisibility(View.GONE);
        else if (model.getRentalPrice() == 0.f)
            rentalPrice.setText("Rental price: Free");
        else
            rentalPrice.setText("Rental price: " + model.getRentalPrice().toString());

        if(model.getGenre() == null)
            genre.setVisibility(View.GONE);
        else
            genre.setText(model.getGenre());

        if(model.getCountry() == null)
            country.setVisibility(View.GONE);
        else
            country.setText(model.getCountry());

        if(model.getImageLink() == null)
            artworkImage.setVisibility(View.GONE);
        else
            Glide.with(this)
                .load(model.getImageLink())
                .into(artworkImage);
    }
}
