
package com.magicdroid.magictrip.models.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityModel {

    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("ct")
    @Expose
    public String ct;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("cn")
    @Expose
    public Object cn;
    @SerializedName("en")
    @Expose
    public boolean en;
    @SerializedName("rt")
    @Expose
    public String rt;
    @SerializedName("st")
    @Expose
    public String st;
    @SerializedName("co")
    @Expose
    public String co;
    @SerializedName("_oid")
    @Expose
    public int oid;
    @SerializedName("eid")
    @Expose
    public String eid;
    @SerializedName("cid")
    @Expose
    public Object cid;
    @SerializedName("useNLP")
    @Expose
    public boolean useNLP;
    @SerializedName("lat")
    @Expose
    public double lat;
    @SerializedName("lon")
    @Expose
    public double lon;
    @SerializedName("xid")
    @Expose
    public int xid;

}
