package com.magicdroid.magictrip.network;


/**
 * Created by MagicDroid on 08/4/2017.
 */
public interface IResponseCallBack<Object> {
    public void onSuccessResponse(Object t, int apiCode);

    public void onFailureResponse(Throwable e, int apCode);

    public void onNetWorkFailure();

}
