package it.chiarani.meteotrentinoapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.database.entity.OpenWeatherDataEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;

public class WeatherSlotAdapter extends RecyclerView.Adapter<WeatherSlotAdapter.ViewHolder> {

  /**
   * entities for get weather data
   */
  private WeatherReportEntity weather_report;
  private OpenWeatherDataEntity open_weather_report;

  public WeatherSlotAdapter(WeatherReportEntity weather_report, OpenWeatherDataEntity open_weather_report) {
    this.weather_report = weather_report;
    this.open_weather_report = open_weather_report;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    TextView txt_time_slot;
    TextView txt_humidity;
    TextView txt_prob_prec;
    TextView txt_prob_temp;
    TextView txt_vento;

    public ViewHolder(View v) {
      super(v);
      txt_time_slot = v.findViewById(R.id.item_slot_weather_txt_fascia);
      txt_humidity = v.findViewById(R.id.item_slot_weather_txt_humidity);
      txt_prob_prec = v.findViewById(R.id.item_slot_weather_txt_prob_prec);
      txt_prob_temp = v.findViewById(R.id.item_slot_weather_txt_prob_temp);
      txt_vento = v.findViewById(R.id.item_slot_weather_txt_vento);
    }
  }

  @Override
  public WeatherSlotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    Boolean attachViewImmediatelyToParent = false;

    View singleItemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slot_weather, parent, attachViewImmediatelyToParent);
    ViewHolder myViewHolder = new ViewHolder(singleItemLayout);

    return myViewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    //Set data to the individual list item
    holder.txt_time_slot.setText("Fascia Oraria: " + weather_report.getPrevisione().getGiorni().get(0).getFasce().get(position).getFasciaOre()); // Fascia oraria
    holder.txt_prob_prec.setText(weather_report.getPrevisione().getGiorni().get(0).getFasce().get(position).getDescPrecProb());                  // Probabilità Precipitazioni
    holder.txt_humidity.setText(open_weather_report.getHumidity() + "%");                                                                     // Umidità
    holder.txt_prob_temp.setText(weather_report.getPrevisione().getGiorni().get(0).getFasce().get(position).getDescTempProb());                  // Probabilità Temporali
    holder.txt_vento.setText(weather_report.getPrevisione().getGiorni().get(0).getFasce().get(position).getDescVentoIntValle());                 // Intensità Vento
  }

  @Override
  public int getItemCount() {
    //Return the number of items in your list
    return weather_report.getPrevisione().getGiorni().get(0).getFasce().size();
  }
}
