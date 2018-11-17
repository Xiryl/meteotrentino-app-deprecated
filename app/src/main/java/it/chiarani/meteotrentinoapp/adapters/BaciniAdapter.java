package it.chiarani.meteotrentinoapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.xml_parser.XmlDatiOggi;

public class BaciniAdapter extends RecyclerView.Adapter<BaciniAdapter.ViewHolder> {

    // #region private fields
    private List<String> data;
    // #endregion

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    public BaciniAdapter(List<String> data) {
        this.data = data;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_data;
        TextView txt_temp;

        public ViewHolder(View v) {
            super(v);
            txt_data = v.findViewById(R.id.item_weather_station_data);
            txt_temp = v.findViewById(R.id.item_weather_station_ora);
        }
    }

    @Override
    public BaciniAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View singleItemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_station, parent, false);
        return new ViewHolder(singleItemLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            holder.txt_temp.setText(String.format("[%s]", data.get(position).split(";")[1]));
            holder.txt_data.setText(String.format("%s", data.get(position).split(";")[0]));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
