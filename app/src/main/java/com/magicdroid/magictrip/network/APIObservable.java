package com.magicdroid.magictrip.network;


import rx.Observer;
import rx.Subscriber;

class APIObservable<T> extends Subscriber<T> implements Observer<T> {
    private IResponseCallBack iResponseCallBack;
    private int apiCode;

    APIObservable(IResponseCallBack IResponseCallBack, int apiCode) {
        this.iResponseCallBack = IResponseCallBack;
        this.apiCode = apiCode;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        iResponseCallBack.onFailureResponse(e, apiCode);
    }

    @Override
    public void onNext(T responseClass) {
        iResponseCallBack.onSuccessResponse(responseClass, apiCode);
    }

    public void unSubScribe() {
        unsubscribe();
    }

}