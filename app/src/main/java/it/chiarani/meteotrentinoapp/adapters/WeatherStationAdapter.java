package it.chiarani.meteotrentinoapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.xml_parser.XmlDatiOggi;

public class WeatherStationAdapter extends RecyclerView.Adapter<WeatherStationAdapter.ViewHolder> {

  // #region private fields
  private XmlDatiOggi report;
  // #endregion

  public void clear() {
    report.getTemperature().get(0).getTemperature().clear();
    notifyDataSetChanged();
  }

  public WeatherStationAdapter(XmlDatiOggi report) {
    this.report = report;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    TextView txt_data;
    TextView txt_temp;

    public ViewHolder(View v) {
      super(v);
      txt_data = v.findViewById(R.id.item_weather_station_data);
      txt_temp = v.findViewById(R.id.item_weather_station_ora);
    }
  }

  @Override
  public WeatherStationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View singleItemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_station, parent, false);
    return new ViewHolder(singleItemLayout);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    String data = report.getTemperature().get(0).getTemperature().get(position).getData().split("T")[0];
    String ora  = report.getTemperature().get(0).getTemperature().get(position).getData().split("T")[1];
    String temp = report.getTemperature().get(0).getTemperature().get(position).getTemperatura();

    holder.txt_data.setText(String.format("%s %s", data, ora));
    holder.txt_temp.setText(String.format("%s °C", temp));
  }

  @Override
  public int getItemCount() {
    return report.getTemperature().get(0).getTemperature().size();
  }
}
