package edu.lnu.audiotunes.data.network.features.info.podcastInfo.models;

import com.google.gson.annotations.SerializedName;

public class PodcastInfoModel  {
    private Integer id;
    @SerializedName("trackName")
    private String name;
    @SerializedName("artistName")
    private String artistName;
    @SerializedName("artworkUrl600")
    private String imageLink;
    @SerializedName("trackPrice")
    private Float price;
    @SerializedName("trackRentalPrice")
    private Float rentalPrice;
    @SerializedName("country")
    private String country;
    @SerializedName("primaryGenreName")
    private String genre;

    public PodcastInfoModel(Integer id, String name, String artistName, String imageLink, Float price, Float rentalPrice, String country, String genre) {
        this.id = id;
        this.name = name;
        this.artistName = artistName;
        this.imageLink = imageLink;
        this.price = price;
        this.rentalPrice = rentalPrice;
        this.country = country;
        this.genre = genre;
    }

    public Integer getId() {
        return id;
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

    public String getCountry() {
        return country;
    }

    public String getGenre() {
        return genre;
    }
}
