package it.chiarani.meteotrentinoapp.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForDayEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
import it.chiarani.meteotrentinoapp.helper.ItDays;
import it.chiarani.meteotrentinoapp.helper.WeatherIconDescriptor;
import it.chiarani.meteotrentinoapp.helper.WeatherTypes;
import it.chiarani.meteotrentinoapp.views.WeatherReportActivity;

public class WeatherSevenDayAdapter extends RecyclerView.Adapter<WeatherSevenDayAdapter.ViewHolder>  {

  // #region private fields
  private WeatherReportEntity weather_report;
  private final static String INTENT_PARAM_TAG = "DAY";
  // #endregion

  /**
   * Constructor
   * @param weather_report report data
   */
  public WeatherSevenDayAdapter(WeatherReportEntity weather_report) {
    this.weather_report = weather_report;
  }

  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView       txt_day;
    TextView       txt_temperature_max;
    TextView       txt_temperature_min;
    ImageView      img_weather;
    ImageView      img_warning;
    RelativeLayout main_rl;

    public ViewHolder(View v) {
      super(v);

      img_weather         = v.findViewById(R.id.item_sevenday_weather_ico);
      txt_day             = v.findViewById(R.id.item_sevenday_weather_txt_data);
      txt_temperature_max = v.findViewById(R.id.item_sevenday_weather_txt_tmax);
      txt_temperature_min = v.findViewById(R.id.item_sevenday_weather_txt_tmin);
      img_warning         = v.findViewById(R.id.item_sevenday_weather_img_warning);
      main_rl             = v.findViewById(R.id.item_sevenday_weather_rl);

      v.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      int pos = this.getLayoutPosition();

      // Call weather report activity
      Intent myIntent = new Intent(v.getContext(), WeatherReportActivity.class);
      myIntent.putExtra(INTENT_PARAM_TAG, pos);
      v.getContext().startActivity(myIntent);
    }
  }

  @Override
  public WeatherSevenDayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View singleItemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seven_day_weather, parent, false);

    return new ViewHolder(singleItemLayout);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {

    // Set current day as green background
    if(position == 0) {
      holder.main_rl.setBackgroundResource(R.color.seven_day_list_first_item_background_color);
    }

    Date date = new Date();
    Calendar data = Calendar.getInstance();
    data.setTime(date);
    data.add(Calendar.DAY_OF_MONTH, position);

    switch (data.get(Calendar.DAY_OF_WEEK)) {
      case 1: holder.txt_day.setText(String.format("%s\n%s", ItDays.DOM, data.get(Calendar.DAY_OF_MONTH)));break;
      case 2: holder.txt_day.setText(String.format("%s\n%s", ItDays.LUN, data.get(Calendar.DAY_OF_MONTH)));break;
      case 3: holder.txt_day.setText(String.format("%s\n%s", ItDays.MAR, data.get(Calendar.DAY_OF_MONTH)));break;
      case 4: holder.txt_day.setText(String.format("%s\n%s", ItDays.MER, data.get(Calendar.DAY_OF_MONTH)));break;
      case 5: holder.txt_day.setText(String.format("%s\n%s", ItDays.GIO, data.get(Calendar.DAY_OF_MONTH)));break;
      case 6: holder.txt_day.setText(String.format("%s\n%s", ItDays.VEN, data.get(Calendar.DAY_OF_MONTH)));break;
      case 7: holder.txt_day.setText(String.format("%s\n%s", ItDays.SAB, data.get(Calendar.DAY_OF_MONTH)));break;
      default:holder.txt_day.setText(String.format("%s\n%s", ItDays.SAB, data.get(Calendar.DAY_OF_MONTH)));break;
    }

    WeatherForDayEntity wfd = weather_report.getPrevisione().getGiorni().get(position);

    // Temp. massima e minima
    holder.txt_temperature_max.setText(String.format("%s°", wfd.gettMaxGiorno()));
    holder.txt_temperature_min.setText(String.format("%s°", wfd.gettMinGiorno()));

    if(!wfd.getIcoAllerte().isEmpty()) {
      holder.img_warning.setImageResource(R.drawable.ic_warning);

      String hex_color = wfd.getColoreAllerte();
      holder.img_warning.setBackgroundColor(Color.parseColor(hex_color));
    }

    WeatherTypes wtype = WeatherIconDescriptor.getWeatherType(wfd.getIcona());

    switch (wtype){
      case COPERTO:
        holder.img_weather.setImageResource(R.drawable.ic_w_cloud_g);
        break;
      case COPERTO_CON_PIOGGIA:
        holder.img_weather.setImageResource(R.drawable.ic_w_light_rain_g);
        break;
      case COPERTO_CON_PIOGGIA_ABBONDANTE :
        holder.img_weather.setImageResource(R.drawable.ic_w_rain_g);
        break;
      case COPERTO_CON_PIOGGIA_E_NEVE:
        holder.img_weather.setImageResource(R.drawable.ic_w_snow_rain_g);
        break;
      case NEVICATA:
        holder.img_weather.setImageResource(R.drawable.ic_w_snow);
        break;
      case SOLE:
        holder.img_weather.setImageResource(R.drawable.ic_w_sun_g);
        break;
      case SOLEGGIATO:
        holder.img_weather.setImageResource(R.drawable.ic_w_sun_cloud_g);
        break;
      case SOLEGGIATO_CON_PIOGGIA:
        holder.img_weather.setImageResource(R.drawable.ic_w_sun_cloud_rain_g);
        break;
      case SOLEGGIATO_CON_PIOGGIA_E_NEVE:
        holder.img_weather.setImageResource(R.drawable.ic_w_sun_cloud_rain_snow_g);
        break;
      case TEMPORALE:
        holder.img_weather.setImageResource(R.drawable.ic_w_thunderstorm_g);
        break;
      case UNDEFINED:
        holder.img_weather.setImageResource(R.drawable.ic_w_sun_cloud_g);
        break;
    }
   }

  @Override
  public int getItemCount() {
    return weather_report.getPrevisione().getGiorni().size();
  }
}
