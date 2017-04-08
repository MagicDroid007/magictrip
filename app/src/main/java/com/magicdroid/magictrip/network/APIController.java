package com.magicdroid.magictrip.network;

import android.content.Context;
import android.util.Log;

import com.magicdroid.magictrip.models.requestmodels.BaseRequestModel;
import com.magicdroid.magictrip.models.requestmodels.PlacesRequestModel;
import com.magicdroid.magictrip.models.responsemodel.CityModel;
import com.magicdroid.magictrip.models.responsemodel.placesmodels.PlacesModel;
import com.magicdroid.magictrip.utililty.AppConstants;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by sanidhya.kumar on 31/3/17.
 */
public class APIController {
    private Context context;
    private APIServices apiService;
    private Subscription subscription;
    private long startTime;


    public APIController(Context context) {
        apiService = RetroFitServiceCreator.getClient(APIServices.class, APIServices.BASE_URL, context);
        this.context = context;
    }

    public void unSubScribe() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    public void executeRequest(final BaseRequestModel requestRModel, final IResponseCallBack iResponseCallBack, int apiCode) {
        if (!NetworkUtil.isInternetConnected(context)) {
            iResponseCallBack.onNetWorkFailure();
            return;
        }
        switch (apiCode) {
            case AppConstants.CITY:
                APIObservable<ArrayList<CityModel>> apiObservable = new APIObservable<>(iResponseCallBack, apiCode);
                final PlacesRequestModel placesRequestModel = (PlacesRequestModel) requestRModel;
                Observable<ArrayList<CityModel>> placesModelObservable = Observable.create(new Observable.OnSubscribe<ArrayList<CityModel>>() {
                    @Override
                    public void call(Subscriber<? super ArrayList<CityModel>> subscriber) {
                        try {
                            Call<ArrayList<CityModel>> restaurant = apiService.getCities(placesRequestModel.type);
                            ArrayList<CityModel> body = restaurant.execute().body();
                            subscriber.onNext(body);
                            subscriber.onCompleted();
                        } catch (Exception e) {
                            subscriber.onError(e);
                        }
                    }
                });

                subscription = placesModelObservable
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(apiObservable);
                break;

        }
    }
}