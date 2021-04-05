package edu.lnu.audiotunes.data.network.features.info.movieInfo.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

public class MovieInfoModel {
    @SerializedName("trackName")
    private String name;
    @SerializedName("artistName")
    private String artistName;
    @SerializedName("artworkUrl100")
    private String imageLink;
    @SerializedName("trackPrice")
    private Float price;
    @SerializedName("trackRentalPrice")
    private Float rentalPrice;
    @SerializedName("trackHdPrice")
    private Float hdPrice;
    @SerializedName("trackHdRentalPrice")
    private Float hdRentalPrice;
    @SerializedName("primaryGenreName")
    private String genre;
    @SerializedName("country")
    private String country;
    @SerializedName("longDescription")
    private String description;

    public MovieInfoModel(String name, String artistName, String imageLink, Float price, Float rentalPrice, Float hdPrice, Float hdRentalPrice, String genre, String country, String description) {
        this.name = name;
        this.artistName = artistName;
        this.imageLink = imageLink;
        this.price = price;
        this.rentalPrice = rentalPrice;
        this.hdPrice = hdPrice;
        this.hdRentalPrice = hdRentalPrice;
        this.genre = genre;
        this.country = country;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getImageLink() {
        return imageLink;
    }

    public Float getPrice() {
        return price;
    }

    public Float getRentalPrice() {
        return rentalPrice;
    }

    public Float getHdPrice() {
        return hdPrice;
    }

    public Float getHdRentalPrice() {
        return hdRentalPrice;
    }

    public String getGenre() {
        return genre;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }
}


