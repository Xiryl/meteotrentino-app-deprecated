package it.chiarani.meteotrentinoapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.xml_parser.XmlDatiOggi;

public class WeatherStationAdapter extends RecyclerView.Adapter<WeatherStationAdapter.ViewHolder> {

  // #region private fields
  private XmlDatiOggi report;
  private int type = 0; // 0 pioggia; 1 temperature
  // #endregion

  public void clear() {
    report.getTemperature().get(0).getTemperature().clear();
    notifyDataSetChanged();
  }

  public WeatherStationAdapter(XmlDatiOggi report, int type) {
    if(report != null && report.getTemperature() != null && report.getTemperature().get(0).getTemperature() != null)
      Collections.reverse(report.getTemperature().get(0).getTemperature());

    if(report != null && report.getPrecipitazioni() != null && report.getPrecipitazioni().get(0).getPrecipitazione() != null)
    Collections.reverse(report.getPrecipitazioni().get(0).getPrecipitazione());

    this.report = report;
    this.type = type;
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
  public WeatherStationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View singleItemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_station, parent, false);
    return new ViewHolder(singleItemLayout);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {

    if(type == 1) {
      // temperature
      String data = report.getTemperature().get(0).getTemperature().get(position).getData().split("T")[0];
      String ora  = report.getTemperature().get(0).getTemperature().get(position).getData().split("T")[1];
      String temp = report.getTemperature().get(0).getTemperature().get(position).getTemperatura();

      holder.txt_data.setText(String.format("[%s] Ore: %s", data, ora.substring(0,5)));
      holder.txt_temp.setText(String.format("%s °C", temp));
    }
    if(type == 0) {
      // pioggie
      String data = report.getPrecipitazioni().get(0).getPrecipitazione().get(position).getData().split("T")[0];
      String ora  = report.getPrecipitazioni().get(0).getPrecipitazione().get(position).getData().split("T")[1];
      String prec = report.getPrecipitazioni().get(0).getPrecipitazione().get(position).getPioggia();

      holder.txt_data.setText(String.format("[%s] Ore: %s", data, ora.substring(0,5)));
      holder.txt_temp.setText(String.format("%s mm", prec));
    }
  }

  @Override
  public int getItemCount() {
    return report.getTemperature().get(0).getTemperature().size();
  }
}
