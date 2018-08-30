package it.chiarani.meteotrentinoapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.models.WeatherReport;

public class WeatherReportAdapter extends RecyclerView.Adapter<WeatherReportAdapter.ViewHolder> {

  WeatherReport _report;

  public WeatherReportAdapter(WeatherReport report) {
    _report = report;
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
      txt_time_slot = v.findViewById(R.id.item_weather_report_txt_fascia);
      txt_tmin = v.findViewById(R.id.item_weather_report_txt_t_min);
      txt_tmax = v.findViewById(R.id.item_weather_report_txt_t_max);
      txt_humidity = v.findViewById(R.id.item_weather_report_txt_humidity);
      txt_pressure = v.findViewById(R.id.item_weather_report_txt_pressure);
      txt_prob_prec = v.findViewById(R.id.item_weather_report_txt_prob_prec);
      txt_intensita_prec = v.findViewById(R.id.item_weather_report_txt_intensita_prec);
      txt_prob_temp = v.findViewById(R.id.item_weather_report_txt_prob_temp);
      txt_vento_quota = v.findViewById(R.id.item_weather_report_txt_vento_quota);
      txt_vento_valle = v.findViewById(R.id.item_weather_report_txt_vento_valle);
      txt_vento_quota_dir = v.findViewById(R.id.item_weather_report_txt_vento_quota_dir);
      txt_vento_valle_dir = v.findViewById(R.id.item_weather_report_txt_vento_valle_dir);
      txt_zero_termico = v.findViewById(R.id.item_weather_report_txt_zero_termico);
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
    holder.txt_time_slot.setText("pos" + position);
  }

  @Override
  public int getItemCount() {
    //Return the number of items in your list
    return _report.getPrevisione().getGiorni().get(0).getFasce().size();
  }

}
