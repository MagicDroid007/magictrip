package com.magicdroid.magictrip.network;

import android.content.Context;

import com.magicdroid.magictrip.models.responsemodel.placesmodels.PlacesModel;
import com.magicdroid.magictrip.models.requestmodels.BaseRequestModel;
import com.magicdroid.magictrip.models.requestmodels.PlacesRequestModel;
import com.magicdroid.magictrip.utililty.AppConstants;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscription;

/**
 * Created by MagicDroid on 08/4/2017.
 */
public class APIController {
    private final APIServices apiServiceMap;
    private APIServices apiService;
    private NetworkManager networkManager;
    private Subscription subscription;
    private long startTime;


    public APIController(Context context) {
        apiServiceMap = RetroFitServiceCreator.getClient(APIServices.class, APIServices.BASE_URL_GOOGLE, context);
        networkManager = new NetworkManager(context);
    }

    public void executeRequest(final BaseRequestModel requestRModel, final IResponseCallBack iResponseCallBack, int apiCode) {
        if (!networkManager.isInternetConnected()) {
            iResponseCallBack.onNetWorkFailure();
            return;
        }
        switch (apiCode) {
            case AppConstants.API.CITY:
                final PlacesRequestModel requestRModel1 = (PlacesRequestModel) requestRModel;
                Call<PlacesModel> restaurant = apiServiceMap.getNearbyPlaces(requestRModel1.type, requestRModel1.latitude + "," + requestRModel1.longitude, requestRModel1.radius);
                restaurant.enqueue(new Callback<PlacesModel>() {
                    @Override
                    public void onResponse(Call<PlacesModel> call, Response<PlacesModel> response) {
                        iResponseCallBack.onSuccessResponse(response, AppConstants.API.CITY);
                    }

                    @Override
                    public void onFailure(Call<PlacesModel> call, Throwable t) {
                        iResponseCallBack.onFailureResponse(t, AppConstants.API.CITY);
                    }
                });
                break;

        }
    }

    public void unSubScribe() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }
}