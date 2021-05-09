package edu.lnu.audiotunes.presentation.fragments.audiobookInfo;

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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import edu.lnu.audiotunes.R;
import edu.lnu.audiotunes.data.network.features.info.audiobookInfo.models.AudiobookInfoModel;
import edu.lnu.audiotunes.presentation.base.BaseFragment;
import edu.lnu.audiotunes.presentation.fragments.audiobookInfo.presentation.AudiobookInfoPresenter;
import edu.lnu.audiotunes.presentation.fragments.audiobookInfo.presentation.IAudiobookInfoView;

import static android.view.View.GONE;

public class AudiobookInfoFragment extends BaseFragment<AudiobookInfoPresenter, IAudiobookInfoView>
        implements IAudiobookInfoView {
    private static final String ARTWORK_ID = "artwork_id";

    @Nullable
    private TextView name;
    @Nullable
    private TextView artistName;
    @Nullable
    private TextView price;
    @Nullable
    private TextView country;
    @Nullable
    private TextView description;
    @Nullable
    private ImageView artworkImage;


    public static AudiobookInfoFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(ARTWORK_ID, id);
        AudiobookInfoFragment fragment = new AudiobookInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_audiobook_info, container, false);
    }

    @Override
    public void createPresenterInstance() {
        presenter = new AudiobookInfoPresenter();
    }

    @Override
    public void initPresenter() {
        presenter.attachView(this);
        presenter.initAudiobookID(getArguments().getInt(ARTWORK_ID));
    }

    @Override
    public void initViews(View view) {
        name = view.findViewById(R.id.name);
        artistName = view.findViewById(R.id.artistName);
        price = view.findViewById(R.id.price);
        description = view.findViewById(R.id.description);
        country = view.findViewById(R.id.country);
        artworkImage = view.findViewById(R.id.artworkImage);
    }

    @Override
    public void setData(AudiobookInfoModel model) {

        if (model.getName() == null)
            name.setVisibility(GONE);
        else
            name.setText(model.getName());

        if (model.getArtistName() == null)
            artistName.setVisibility(GONE);
        else
            artistName.setText(model.getArtistName());

        if (model.getPrice() == null)
            price.setVisibility(GONE);
        else if (model.getPrice() == 0.f)
            price.setText("Free");
        else
            price.setText("Price: " + model.getPrice() + "$");

        if (model.getDescription() == null)
            description.setVisibility(GONE);
        else description.setText(Html.fromHtml(model.getDescription()));

        if (model.getCountry() == null)
            country.setVisibility(GONE);
        else
            country.setText(model.getCountry());

        if (model.getImageLink() == null)
            artworkImage.setVisibility(GONE);
        else
            Glide.with(this)
                    .load(model.getImageLink())
                    .transform(new RoundedCorners(20))
                    .into(artworkImage);
    }
}
