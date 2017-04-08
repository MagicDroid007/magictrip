package com.magicdroid.magictrip.network;



import com.magicdroid.magictrip.models.responsemodel.CityModel;
import com.magicdroid.magictrip.models.responsemodel.placesmodels.PlacesModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by MagicDroid on 08/4/2017.
 */
public interface APIServices {

    String BASE_URL = "http://build2.ixigo.com/";

    @GET("action/content/zeus/autocomplete?searchFor=tpAutoComplete&neCategories=City")
    Call<ArrayList<CityModel>> getCities(@Query("query") String type);

}

