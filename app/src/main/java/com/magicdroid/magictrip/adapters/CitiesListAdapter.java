package com.magicdroid.magictrip.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.magicdroid.magictrip.R;
import com.magicdroid.magictrip.models.responsemodel.CityModel;

import java.util.ArrayList;

public class CitiesListAdapter extends BaseAdapter implements Filterable {

    private ArrayList<CityModel> mStringList;
    private ArrayList<CityModel> mStringFilterList;
    private LayoutInflater mInflater;
    private ValueFilter valueFilter;

    public CitiesListAdapter(Context context) {
        this.mStringList = new ArrayList<>();
        this.mStringFilterList = mStringList;
        mInflater = LayoutInflater.from(context);
        getFilter();
    }

    public void addAll(ArrayList<CityModel> arrayList) {
        this.mStringList = arrayList;
        notifyDataSetChanged();
    }

    // How many items are in the data set represented by this Adapter.
    @Override
    public int getCount() {

        return mStringList.size();
    }

    // Get the data item associated with the specified position in the data set.
    @Override
    public Object getItem(int position) {

        return mStringList.get(position);
    }

    // Get the row id associated with the specified position in the list.
    @Override
    public long getItemId(int position) {

        return position;
    }

    // Get a View that displays the data at the specified position in the data
    // set.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder viewHolder;

        if (convertView == null) {

            viewHolder = new Holder();

            convertView = mInflater.inflate(R.layout.list_item, null);
            viewHolder.nameTv = (TextView) convertView.findViewById(R.id.txt_listitem);
            convertView.setTag(viewHolder);

        } else {

            viewHolder = (Holder) convertView.getTag();
        }

        viewHolder.nameTv.setText(mStringList.get(position).text);

        return convertView;
    }

    private class Holder {

        TextView nameTv;
    }

    // Returns a filter that can be used to constrain data with a filtering
    // pattern.
    @Override
    public Filter getFilter() {

        if (valueFilter == null) {

            valueFilter = new ValueFilter();
        }

        return valueFilter;
    }

    private class ValueFilter extends Filter {

        // Invoked in a worker thread to filter the data according to the
        // constraint.
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {

                ArrayList<CityModel> filterList = new ArrayList<>();

                for (int i = 0; i < mStringFilterList.size(); i++) {

                    if (mStringFilterList.get(i).text.contains(constraint)) {

                        filterList.add(mStringFilterList.get(i));

                    }
                }

                results.count = filterList.size();

                results.values = filterList;

            } else {

                results.count = mStringFilterList.size();

                results.values = mStringFilterList;

            }

            return results;
        }

        // Invoked in the UI thread to publish the filtering results in the user
        // interface.
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            mStringList = (ArrayList<CityModel>) results.values;

            notifyDataSetChanged();

        }

    }
}