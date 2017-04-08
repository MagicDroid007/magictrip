package com.magicdroid.magictrip.network;



import com.magicdroid.magictrip.models.responsemodel.placesmodels.PlacesModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by MagicDroid on 08/4/2017.
 */
public interface APIServices {

    String BASE_URL_GOOGLE = "https://maps.googleapis.com/maps/";

    @GET("api/place/nearbysearch/json?sensor=true&key=AIzaSyDN7RJFmImYAca96elyZlE5s_fhX-MMuhk")
    Call<PlacesModel> getNearbyPlaces(@Query("type") String type, @Query("location") String location, @Query("radius") int radius);

}

