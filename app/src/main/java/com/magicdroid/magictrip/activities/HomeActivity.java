package com.magicdroid.magictrip.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.magicdroid.magictrip.R;
import com.magicdroid.magictrip.adapters.CitiesListAdapter;
import com.magicdroid.magictrip.models.requestmodels.PlacesRequestModel;
import com.magicdroid.magictrip.models.responsemodel.CityModel;
import com.magicdroid.magictrip.network.APIController;
import com.magicdroid.magictrip.network.APIServices;
import com.magicdroid.magictrip.network.IResponseCallBack;
import com.magicdroid.magictrip.network.RetroFitServiceCreator;
import com.magicdroid.magictrip.utililty.AppConstants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by magic on 08/04/17.
 */
public class HomeActivity extends BaseActivity implements IResponseCallBack {

    private CitiesListAdapter citiesListAdapter;
    private ArrayList<CityModel> cityModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.txtAutoCompleteCity);

        cityModelArrayList = new ArrayList<>();
        autoCompleteTextView.setThreshold(1);
        citiesListAdapter = new CitiesListAdapter(this);
        autoCompleteTextView.setAdapter(citiesListAdapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                autoCompleteTextView.setText(cityModelArrayList.get(position).text);
                autoCompleteTextView.setSelection(cityModelArrayList.get(position).text.length());
            }
        });
        final APIController apiController = new APIController(mContext);

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                String data = autoCompleteTextView.getText().toString();
                if (data.startsWith(" ")) {
                    autoCompleteTextView.setText("");
                    return;
                }

                if (data.isEmpty()) {

                    cityModelArrayList.clear();

                }
                if (s.length() >= 3) {
                    PlacesRequestModel placesRequestModel = new PlacesRequestModel();
                    placesRequestModel.cityName = s.toString();
                    apiController.executeRequest(placesRequestModel, HomeActivity.this, AppConstants.CITY);

                } else {
                    cityModelArrayList.clear();
                    citiesListAdapter.addAll(cityModelArrayList);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void onSuccessResponse(Object t, int apiCode) {

        cityModelArrayList = (ArrayList<CityModel>) t;
        citiesListAdapter.addAll(cityModelArrayList);
    }

    @Override
    public void onFailureResponse(Throwable e, int apCode) {

    }

    @Override
    public void onNetWorkFailure() {

    }
}

