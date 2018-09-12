package it.chiarani.meteotrentinoapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.database.entity.OpenWeatherDataEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
import it.chiarani.meteotrentinoapp.helper.WeatherIconDescriptor;
import it.chiarani.meteotrentinoapp.helper.WeatherTypes;
import it.chiarani.meteotrentinoapp.views.WeatherReportActivity;

public class WeatherSevenDayAdapter extends RecyclerView.Adapter<WeatherSevenDayAdapter.ViewHolder>  {

  /**
   * entities for get weather data
   */
  private WeatherReportEntity weather_report;

  public WeatherSevenDayAdapter(WeatherReportEntity weather_report) {
    this.weather_report = weather_report;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView txt_day;
    TextView txt_report;
    TextView txt_temperature;
    ImageView img_weather;

    public ViewHolder(View v) {
      super(v);

      img_weather = (ImageView) v.findViewById(R.id.item_seven_day_img);
      txt_day = v.findViewById(R.id.item_seven_day_txt_day);
      txt_report = v.findViewById(R.id.item_seven_day_txt_report);
      txt_temperature = v.findViewById(R.id.item_seven_day_txt_temperature);

      v.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      int pos = this.getLayoutPosition();

      Intent myIntent = new Intent(v.getContext(), WeatherReportActivity.class);
      myIntent.putExtra("DAY", pos);
      v.getContext().startActivity(myIntent);
    }

  }

  @Override
  public WeatherSevenDayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    Boolean attachViewImmediatelyToParent = false;

    View singleItemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seven_day_weather, parent, attachViewImmediatelyToParent);
    ViewHolder myViewHolder = new ViewHolder(singleItemLayout);

    return myViewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    //Set data to the individual list item

    Date date = new Date();
    Calendar data = Calendar.getInstance();
    data.setTime(date);
    data.add(Calendar.DAY_OF_MONTH, position);

    switch (data.get(Calendar.DAY_OF_WEEK)) {
      //case 0: holder.txt_day.setText("Lunedì " + data.get(Calendar.DAY_OF_MONTH)); break;
      case 1: holder.txt_day.setText("Domenica " + data.get(Calendar.DAY_OF_MONTH));break;
      case 2: holder.txt_day.setText("Lunedì " + data.get(Calendar.DAY_OF_MONTH));break;
      case 3: holder.txt_day.setText("Martedì " + data.get(Calendar.DAY_OF_MONTH));break;
      case 4: holder.txt_day.setText("Mercoledì " + data.get(Calendar.DAY_OF_MONTH));break;
      case 5: holder.txt_day.setText("Giovedì " + data.get(Calendar.DAY_OF_MONTH));break;
      case 6: holder.txt_day.setText("Venerdì " + data.get(Calendar.DAY_OF_MONTH));break;
      default: holder.txt_day.setText("Sabato " + data.get(Calendar.DAY_OF_MONTH));break;
    }

    holder.txt_report.setText(weather_report.getPrevisione().getGiorni().get(position).getDescIcona());
    holder.txt_temperature.setText(weather_report.getPrevisione().getGiorni().get(position).gettMinGiorno() + "° / " +
        weather_report.getPrevisione().getGiorni().get(position).gettMaxGiorno() + "°");


    if(WeatherIconDescriptor.getWeatherType(weather_report.getPrevisione().getGiorni().get(position).getIcona()).equals(WeatherTypes.COPERTO)){
      holder.img_weather.setImageResource(R.drawable.ic_w_cloud);
    }
      else if(WeatherIconDescriptor.getWeatherType(weather_report.getPrevisione().getGiorni().get(position).getIcona()).equals(WeatherTypes.COPERTO_CON_PIOGGIA)) {
      holder.img_weather.setImageResource(R.drawable.ic_w_light_rain);
    }
    else if(WeatherIconDescriptor.getWeatherType(weather_report.getPrevisione().getGiorni().get(position).getIcona()).equals(WeatherTypes.COPERTO_CON_PIOGGIA_ABBONDANTE)) {
      holder.img_weather.setImageResource(R.drawable.ic_w_rain);
    }
    else if(WeatherIconDescriptor.getWeatherType(weather_report.getPrevisione().getGiorni().get(position).getIcona()).equals(WeatherTypes.COPERTO_CON_PIOGGIA_E_NEVE)) {
      holder.img_weather.setImageResource(R.drawable.ic_w_snow_rain);
    }
    else if(WeatherIconDescriptor.getWeatherType(weather_report.getPrevisione().getGiorni().get(position).getIcona()).equals(WeatherTypes.NEVICATA)) {
      holder.img_weather.setImageResource(R.drawable.ic_w_sun);
    }
    else if(WeatherIconDescriptor.getWeatherType(weather_report.getPrevisione().getGiorni().get(position).getIcona()).equals(WeatherTypes.SOLE)) {
      holder.img_weather.setImageResource(R.drawable.ic_w_sun);
    }
    else if(WeatherIconDescriptor.getWeatherType(weather_report.getPrevisione().getGiorni().get(position).getIcona()).equals(WeatherTypes.SOLEGGIATO)) {
      holder.img_weather.setImageResource(R.drawable.ic_w_sun_cloud);
    }
    else if(WeatherIconDescriptor.getWeatherType(weather_report.getPrevisione().getGiorni().get(position).getIcona()).equals(WeatherTypes.SOLEGGIATO_CON_PIOGGIA)) {
      holder.img_weather.setImageResource(R.drawable.ic_w_sun_cloud_rain);
    }
    else if(WeatherIconDescriptor.getWeatherType(weather_report.getPrevisione().getGiorni().get(position).getIcona()).equals(WeatherTypes.SOLEGGIATO_CON_PIOGGIA_E_NEVE)) {
      holder.img_weather.setImageResource(R.drawable.ic_w_sun_cloud_rain_snow);
    }
    else if(WeatherIconDescriptor.getWeatherType(weather_report.getPrevisione().getGiorni().get(position).getIcona()).equals(WeatherTypes.TEMPORALE)) {
      holder.img_weather.setImageResource(R.drawable.ic_w_thunderstorm);
    }
    else if(WeatherIconDescriptor.getWeatherType(weather_report.getPrevisione().getGiorni().get(position).getIcona()).equals(WeatherTypes.UNDEFINED)) {
      holder.img_weather.setImageResource(R.drawable.ic_w_sun_cloud);
    }
   }

  @Override
  public int getItemCount() {
    //Return the number of items in your list
    return weather_report.getPrevisione().getGiorni().size();
  }
}
