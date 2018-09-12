package it.chiarani.meteotrentinoapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.database.entity.OpenWeatherDataEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForSlotEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;

public class WeatherReportAdapter extends RecyclerView.Adapter<WeatherReportAdapter.ViewHolder> {

  WeatherReportEntity weather_report;
  OpenWeatherDataEntity open_weather_report;
  int weather_day;

  public WeatherReportAdapter(WeatherReportEntity weather_report, OpenWeatherDataEntity open_weather_report, int weather_day) {
    this.weather_report = weather_report;
    this.open_weather_report = open_weather_report;
    this.weather_day = weather_day;
  }


  public static class ViewHolder extends RecyclerView.ViewHolder {

    TextView txt_time_slot;
    TextView txt_tmin;
    TextView txt_tmax;
    TextView txt_humidity;
    TextView txt_pressure;
    TextView txt_prob_prec;
    TextView txt_intensita_prec;
    TextView txt_prob_temp;
    TextView txt_vento_quota;
    TextView txt_vento_valle;
    TextView txt_vento_quota_dir;
    TextView txt_vento_valle_dir;
    TextView txt_zero_termico;

    public ViewHolder(View v) {
      super(v);
      txt_time_slot         = v.findViewById(R.id.item_weather_report_txt_fascia);
      txt_tmin              = v.findViewById(R.id.item_weather_report_txt_t_min);
      txt_tmax              = v.findViewById(R.id.item_weather_report_txt_t_max);
      txt_humidity          = v.findViewById(R.id.item_weather_report_txt_humidity);
      txt_pressure          = v.findViewById(R.id.item_weather_report_txt_pressure);
      txt_prob_prec         = v.findViewById(R.id.item_weather_report_txt_prob_prec);
      txt_intensita_prec    = v.findViewById(R.id.item_weather_report_txt_intensita_prec);
      txt_prob_temp         = v.findViewById(R.id.item_weather_report_txt_prob_temp);
      txt_vento_quota       = v.findViewById(R.id.item_weather_report_txt_vento_quota);
      txt_vento_valle       = v.findViewById(R.id.item_weather_report_txt_vento_valle);
      txt_vento_quota_dir   = v.findViewById(R.id.item_weather_report_txt_vento_quota_dir);
      txt_vento_valle_dir   = v.findViewById(R.id.item_weather_report_txt_vento_valle_dir);
      txt_zero_termico      = v.findViewById(R.id.item_weather_report_txt_zero_termico);
    }
  }

  @Override
  public WeatherReportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    Boolean attachViewImmediatelyToParent = false;

    View singleItemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_report, parent, attachViewImmediatelyToParent);
    ViewHolder myViewHolder = new ViewHolder(singleItemLayout);

    return myViewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    //Set data to the individual list item
    WeatherForSlotEntity wfs = weather_report.getPrevisione().getGiorni().get(weather_day).getFasce().get(position);

    holder.txt_time_slot.setText(wfs.getFasciaPer() + ": " + wfs.getFasciaOre());
    holder.txt_tmin.setText("Temperatura min: " + weather_report.getPrevisione().getGiorni().get(weather_day).gettMinGiorno() + "°");
    holder.txt_tmax.setText("Temperatura max: " + weather_report.getPrevisione().getGiorni().get(weather_day).gettMaxGiorno() + "°");
    holder.txt_humidity.setText("Umidità: " + open_weather_report.getHumidity() + "%");
    holder.txt_pressure.setText("Pressione: " + open_weather_report.getPressure()+ " hPa");
    holder.txt_prob_prec.setText("Prob. precipitazioni: " +wfs.getDescPrecProb() + "");
    holder.txt_intensita_prec.setText("Intensità precipitazioni: " +wfs.getDescPrecInten() + "");
    holder.txt_prob_temp.setText("Prob. Temporali: " +wfs.getDescTempProb() + "");
    holder.txt_vento_quota.setText("Intensità vento in quota: " +wfs.getDescVentoIntQuota() + "");
    holder.txt_vento_valle.setText("Intensità vento in valle: " +wfs.getDescVentoIntValle() + "");
    holder.txt_vento_quota_dir.setText("Direzione vento in quota: " +wfs.getDescVentoIntQuota() + "");
    holder.txt_vento_valle_dir.setText("Direzione vento in valle: " +wfs.getDescVentoIntValle() + "");
    holder.txt_zero_termico.setText("Zero termico: " +wfs.getZeroTermico() + "m");
  }

  @Override
  public int getItemCount() {
    //Return the number of items in your list
    return weather_report.getPrevisione().getGiorni().get(weather_day).getFasce().size();
  }

}
