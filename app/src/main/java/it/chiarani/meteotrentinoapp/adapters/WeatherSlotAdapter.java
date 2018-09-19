package it.chiarani.meteotrentinoapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.database.entity.OpenWeatherDataEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForDayEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForSlotEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
import it.chiarani.meteotrentinoapp.helper.WeatherIconDescriptor;
import it.chiarani.meteotrentinoapp.helper.WeatherTypes;
import it.chiarani.meteotrentinoapp.models.WeatherForDay;

public class WeatherSlotAdapter extends RecyclerView.Adapter<WeatherSlotAdapter.ViewHolder> {

  // #region private fields
  private WeatherReportEntity weather_report;
  private OpenWeatherDataEntity open_weather_report;
  // #endregion

  /**
   * Constructor
   * @param weather_report weather report data
   * @param open_weather_report openweather report data
   */
  public WeatherSlotAdapter(WeatherReportEntity weather_report, OpenWeatherDataEntity open_weather_report) {
    this.weather_report = weather_report;
    this.open_weather_report = open_weather_report;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    TextView txt_time_slot;
    TextView txt_humidity;
    TextView txt_prob_prec;
    TextView txt_prob_temp;
    TextView txt_cielo;
    ImageView img_ico_weather;

    public ViewHolder(View v) {
      super(v);
      txt_time_slot   = v.findViewById(R.id.item_slot_weather_txt_fascia);
      txt_humidity    = v.findViewById(R.id.item_slot_weather_txt_humidity);
      txt_prob_prec   = v.findViewById(R.id.item_slot_weather_txt_prob_prec);
      txt_prob_temp   = v.findViewById(R.id.item_slot_weather_txt_prob_temp);
      txt_cielo       = v.findViewById(R.id.item_slot_weather_txt_vento);
      img_ico_weather = v.findViewById(R.id.item_slot_weather_img);
    }
  }

  @Override
  public WeatherSlotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View singleItemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slot_weather, parent, false);
    return new ViewHolder(singleItemLayout);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    //Set data to the individual list item

    WeatherForSlotEntity wfs = weather_report.getPrevisione().getGiorni().get(0).getFasce().get(position);
    // Fascia oraria
    holder.txt_time_slot.setText("Fascia Oraria: " + weather_report.getPrevisione().getGiorni().get(0).getFasce().get(position).getFasciaOre());

    // Probabilità Precipitazioni
    holder.txt_prob_prec.setText(wfs.getDescPrecProb());

    // Umidità
    holder.txt_humidity.setText(open_weather_report.getHumidity() + "%");

    // Probabilità Temporali
    holder.txt_prob_temp.setText(wfs.getDescTempProb());

    // Intensità Vento
    holder.txt_cielo.setText(wfs.getDescIcona());

    WeatherForDay wfd = weather_report.getPrevisione().getGiorni().get(0);

    WeatherTypes wtype = WeatherIconDescriptor.getWeatherType(wfd.getFasce().get(position).getIcona());

    switch (wtype){
      case COPERTO:
        holder.img_ico_weather.setImageResource(R.drawable.ic_w_cloud_g);
        break;
      case COPERTO_CON_PIOGGIA:
        holder.img_ico_weather.setImageResource(R.drawable.ic_w_light_rain_g);
        break;
      case COPERTO_CON_PIOGGIA_ABBONDANTE :
        holder.img_ico_weather.setImageResource(R.drawable.ic_w_rain_g);
        break;
      case COPERTO_CON_PIOGGIA_E_NEVE:
        holder.img_ico_weather.setImageResource(R.drawable.ic_w_snow_rain_g);
        break;
      case NEVICATA:
        holder.img_ico_weather.setImageResource(R.drawable.ic_w_snow);
        break;
      case SOLE:
        holder.img_ico_weather.setImageResource(R.drawable.ic_w_sun_g);
        break;
      case SOLEGGIATO:
        holder.img_ico_weather.setImageResource(R.drawable.ic_w_sun_cloud_g);
        break;
      case SOLEGGIATO_CON_PIOGGIA:
        holder.img_ico_weather.setImageResource(R.drawable.ic_w_sun_cloud_rain_g);
        break;
      case SOLEGGIATO_CON_PIOGGIA_E_NEVE:
        holder.img_ico_weather.setImageResource(R.drawable.ic_w_sun_cloud_rain_snow_g);
        break;
      case TEMPORALE:
        holder.img_ico_weather.setImageResource(R.drawable.ic_w_thunderstorm_g);
        break;
      case UNDEFINED:
        holder.img_ico_weather.setImageResource(R.drawable.ic_w_sun_cloud_g);
        break;
    }
  }

  @Override
  public int getItemCount() {
    //Return the number of items in your list
    return weather_report.getPrevisione().getGiorni().get(0).getFasce().size();
  }
}
