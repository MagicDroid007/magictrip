package com.magicdroid.magictrip.models;

/**
 * Created by magic on 8/4/17.
 */

public class RecentDataEntity {

    private String cityId;
    private long originCityId;
    private String cityName;
    private String stateName;
    private String countryName;
    private long longitude;
    private long latitude;
    private long timestamp;

    public RecentDataEntity(){}
    public RecentDataEntity(String cityId, long originCityId, String cityName, String stateName,
                            String countryName, long longitude, long latitude, long timestamp) {
        this.cityId = cityId;
        this.originCityId = originCityId;
        this.cityName = cityName;
        this.stateName = stateName;
        this.countryName = countryName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.timestamp = timestamp;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public long getOriginCityId() {
        return originCityId;
    }

    public void setOriginCityId(long originCityId) {
        originCityId = originCityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
