package edu.lnu.audiotunes.presentation.fragments.movieInfo;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import edu.lnu.audiotunes.R;
import edu.lnu.audiotunes.data.network.features.info.movieInfo.models.MovieInfoModel;
import edu.lnu.audiotunes.presentation.base.BaseFragment;
import edu.lnu.audiotunes.presentation.fragments.movieInfo.presentation.IMovieInfoView;
import edu.lnu.audiotunes.presentation.fragments.movieInfo.presentation.MovieInfoPresenter;

public class MovieInfoFragment extends BaseFragment<MovieInfoPresenter, IMovieInfoView>
        implements IMovieInfoView {
    private static final String ARTWORK_ID = "artwork_id";


    @Nullable
    private TextView name;
    @Nullable
    private TextView artistName;
    @Nullable
    private TextView price;
    @Nullable
    private TextView rentalPrice;
    @Nullable
    private TextView genre;
    @Nullable
    private TextView country;
    @Nullable
    private TextView description;
    @Nullable
    private ImageView artworkImage;


    public static MovieInfoFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(ARTWORK_ID, id);
        MovieInfoFragment fragment = new MovieInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_info, container, false);
    }

    @Override
    public void createPresenterInstance() {
        presenter = new MovieInfoPresenter();
    }

    @Override
    public void initPresenter() {
        presenter.attachView(this);
        presenter.initMovieID(getArguments().getInt(ARTWORK_ID));
    }

    @Override
    public void initViews(View view) {
        name = view.findViewById(R.id.name);
        artistName = view.findViewById(R.id.artistName);
        price = view.findViewById(R.id.price);
        rentalPrice = view.findViewById(R.id.rentalPrice);
        genre = view.findViewById(R.id.genre);
        country = view.findViewById(R.id.country);
        description = view.findViewById(R.id.description);
        artworkImage = view.findViewById(R.id.artworkImage);
    }

    @Override
    public void setData(MovieInfoModel model) {
        if(model.getName() == null)
            name.setVisibility(View.GONE);
        else name.setText(model.getName());

        if(model.getArtistName() == null)
            artistName.setVisibility(View.GONE);
        else
            artistName.setText(model.getArtistName());

        if(model.getPrice() == null || model.getHdPrice()== null)
            price.setVisibility(View.GONE);
        else if (model.getPrice() == 0)
            price.setText("Free");
        else
            price.setText("Price: " + model.getPrice() + "$(SD) / " + model.getHdPrice() + "$(HD)");

        if(model.getRentalPrice() == null || model.getHdPrice() == null)
            rentalPrice.setVisibility(View.GONE);
        else if (model.getRentalPrice() == 0f)
            rentalPrice.setText("Free");
        else
            rentalPrice.setText("Rental price: " + model.getRentalPrice() + "$(SD) / " + model.getHdRentalPrice() + "$(HD)");

        if(model.getGenre() == null)
            genre.setVisibility(View.GONE);
        else
            genre.setText(model.getGenre());

        if(model.getCountry() == null)
            country.setVisibility(View.GONE);
        else
            country.setText(model.getCountry());

        if(model.getDescription() == null)
            description.setVisibility(View.GONE);
        else
            description.setText(Html.fromHtml(model.getDescription()));

        if(model.getImageLink() == null)
            artworkImage.setVisibility(View.GONE);
        else
            Glide.with(this)
                .load(model.getImageLink())
                .into(artworkImage);
    }
}
