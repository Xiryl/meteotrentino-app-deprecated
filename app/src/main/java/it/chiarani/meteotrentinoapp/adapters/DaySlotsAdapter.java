package it.chiarani.meteotrentinoapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.database.entity.OpenWeatherDataEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForDayEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForSlotEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
import it.chiarani.meteotrentinoapp.helper.WeatherIconDescriptor;


public class DaySlotsAdapter extends RecyclerView.Adapter<DaySlotsAdapter.ViewHolder> {

    // #region private fields
    private WeatherReportEntity weather_report;
    private OpenWeatherDataEntity open_weather_report;
    private int                   weather_day;
    private int                   mExpandedPosition = -1;
    private Context mContext;
    private ViewHolder viewClicked;
    private int oldPos = 0;
    private ClickListener listener;
    private int first_entry = 1;
    // #endregion

    /**
     * Adapter constructor
     * @param weather_report report data
     * @param open_weather_report openweather report data
     * @param weather_day day number (1-7)
     */
    public DaySlotsAdapter(Context mContext, WeatherReportEntity weather_report, OpenWeatherDataEntity open_weather_report, int weather_day, ClickListener listener) {
        this.mContext            = mContext;
        this.weather_report      = weather_report;
        this.open_weather_report = open_weather_report;
        this.weather_day         = weather_day;
        this.listener            = listener;
    }

    public interface ClickListener{
        void onClick(int day, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //TextView       txt_title;
        TextView       txt_description;
        ImageView      img_weather;
        RelativeLayout rl;

        public ViewHolder(View v) {
            super(v);
          //  txt_title = v.findViewById(R.id.item_day_slots__txt_title);
            txt_description = v.findViewById(R.id.item_day_slots__txt_subtitle);
            rl = v.findViewById(R.id.item_day_slots_rl);
            img_weather  = v.findViewById(R.id.item_day_slots_img_weather);

            //v.setOnClickListener(this);
        }


        public void onClick(View v) {
            int pos = this.getAdapterPosition();
            oldPos= pos;
            notifyDataSetChanged();
        }
    }

    @Override
    public DaySlotsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View singleItemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_slots, parent, false);
        return new ViewHolder(singleItemLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(first_entry == 1) {
            listener.onClick(weather_day, position);
            first_entry = 2;
        }
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPos = position;
                notifyDataSetChanged();
                listener.onClick(weather_day, position);
            }
        });

        WeatherForDayEntity wfd = weather_report.getPrevisione().getGiorni().get(weather_day);
        WeatherForSlotEntity wfs = wfd.getFasce().get(position); // TODO: CHECK HERE
        holder.txt_description.setText(wfs.getFasciaPer().toUpperCase() + "\nFascia (" + wfs.getFasciaOre()+ ")" );

        if(oldPos == position) {

            switch (WeatherIconDescriptor.getWeatherType(wfs.getIcona())){
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

            holder.rl.setBackground(mContext.getResources().getDrawable(R.drawable.blu_rounded_bg));
            holder.txt_description.setTextColor(mContext.getResources().getColor(R.color.white));
        }
        else
        {


            switch (WeatherIconDescriptor.getWeatherType(wfs.getIcona())){
                case COPERTO:
                    holder.img_weather.setImageResource(R.drawable.ic_w_cloud_b);
                    break;
                case COPERTO_CON_PIOGGIA:
                    holder.img_weather.setImageResource(R.drawable.ic_w_light_rain_b);
                    break;
                case COPERTO_CON_PIOGGIA_ABBONDANTE :
                    holder.img_weather.setImageResource(R.drawable.ic_w_rain_b);
                    break;
                case COPERTO_CON_PIOGGIA_E_NEVE:
                    holder.img_weather.setImageResource(R.drawable.ic_w_snow_rain_b);
                    break;
                case NEVICATA:
                    holder.img_weather.setImageResource(R.drawable.ic_w_snow_b);
                    break;
                case SOLE:
                    holder.img_weather.setImageResource(R.drawable.ic_w_sun_b);
                    break;
                case SOLEGGIATO:
                    holder.img_weather.setImageResource(R.drawable.ic_w_sun_cloud_b);
                    break;
                case SOLEGGIATO_CON_PIOGGIA:
                    holder.img_weather.setImageResource(R.drawable.ic_w_sun_cloud_rain_b);
                    break;
                case SOLEGGIATO_CON_PIOGGIA_E_NEVE:
                    holder.img_weather.setImageResource(R.drawable.ic_w_sun_cloud_rain_snow_b);
                    break;
                case TEMPORALE:
                    holder.img_weather.setImageResource(R.drawable.ic_w_thunderstorm_b);
                    break;
                case UNDEFINED:
                    holder.img_weather.setImageResource(R.drawable.ic_w_sun_cloud_b);
                    break;
            }

            holder.rl.setBackground(mContext.getResources().getDrawable(R.drawable.white_rounded_bg));
            holder.txt_description.setTextColor(mContext.getResources().getColor(R.color.textgray));

        }
    }


    @Override
    public int getItemCount() {
        return weather_report.getPrevisione().getGiorni().get(weather_day).getFasce().size();
    }
}
