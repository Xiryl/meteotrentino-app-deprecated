package it.chiarani.meteotrentinoapp.adapters;

import android.arch.persistence.room.util.StringUtil;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.database.entity.OpenWeatherDataEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForSlotEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;

/**
 * Adapter for weather dayly detail Recyclerview
 */
public class WeatherReportAdapter extends RecyclerView.Adapter<WeatherReportAdapter.ViewHolder> {

  // #region private fields
  private WeatherReportEntity weather_report;
  private OpenWeatherDataEntity open_weather_report;
  private int weather_day;
  private int mExpandedPosition = -1;
  // #endregion

  /**
   * Adapter constructor
   * @param weather_report report data
   * @param open_weather_report openweather report data
   * @param weather_day day number (1-7)
   */
  public WeatherReportAdapter(WeatherReportEntity weather_report, OpenWeatherDataEntity open_weather_report, int weather_day) {
    this.weather_report = weather_report;
    this.open_weather_report = open_weather_report;
    this.weather_day = weather_day;
  }


  public static class ViewHolder extends RecyclerView.ViewHolder {

    TextView txt_time_slot;
    TextView txt_tmin;
    TextView txt_tmax;
    TextView txt_pressure;
    TextView txt_prob_prec;
    TextView txt_intensita_prec;
    TextView txt_prob_temp;
    TextView txt_vento_quota;
    TextView txt_vento_valle;
    TextView txt_vento_quota_dir;
    TextView txt_vento_valle_dir;
    TextView txt_zero_termico;
    TextView txt_prev_breve;
    ImageView exp_arrow;
    LinearLayout ll_gen;
    LinearLayout ll_prec;
    LinearLayout ll_vento;

    public ViewHolder(View v) {
      super(v);
      txt_time_slot         = v.findViewById(R.id.item_weather_report_txt_fascia);
      txt_tmin              = v.findViewById(R.id.item_weather_report_txt_tmin);
      txt_tmax              = v.findViewById(R.id.item_weather_report_txt_tmax);
      txt_pressure          = v.findViewById(R.id.item_weather_report_txt_pressione);
      txt_prob_prec         = v.findViewById(R.id.item_weather_report_txt_prob_prec);
      txt_intensita_prec    = v.findViewById(R.id.item_weather_report_txt_intensita_prec);
      txt_prob_temp         = v.findViewById(R.id.item_weather_report_txt_prob_temp);
      txt_vento_quota       = v.findViewById(R.id.item_weather_report_txt_vento_quota);
      txt_vento_valle       = v.findViewById(R.id.item_weather_report_txt_vento_valle);
      txt_vento_quota_dir   = v.findViewById(R.id.item_weather_report_txt_vento_quota_dir);
      txt_vento_valle_dir   = v.findViewById(R.id.item_weather_report_txt_vento_valle_dir);
      txt_zero_termico      = v.findViewById(R.id.item_weather_report_txt_zero);
      txt_prev_breve        = v.findViewById(R.id.item_weather_report_txt_prev_breve);
      ll_gen = v.findViewById(R.id.item_weather_report_l_gen);
      ll_prec = v.findViewById(R.id.item_weather_report_l_pioggia);
      ll_vento = v.findViewById(R.id.item_weather_report_l_vento);
      exp_arrow = v.findViewById(R.id.item_weather_report_img_arrow);
    }
  }

  @Override
  public WeatherReportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View singleItemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_report, parent, false);
    return new ViewHolder(singleItemLayout);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {

  /**
   * https://stackoverflow.com/questions/27203817/recyclerview-expand-collapse-items
   */

    final boolean isExpanded = position==mExpandedPosition;
    holder.exp_arrow.setVisibility(isExpanded?View.GONE:View.VISIBLE); // reverse
    holder.txt_tmin.setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_tmax.setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_pressure.setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_prob_prec.setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_intensita_prec.setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_prob_temp.setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_vento_quota.setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_vento_valle.setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_vento_quota_dir.setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_vento_valle_dir.setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_zero_termico.setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_prev_breve.setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.ll_gen.setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.ll_prec.setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.ll_vento.setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.itemView.setActivated(isExpanded);

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mExpandedPosition = isExpanded ? -1:position;
        notifyItemChanged(position);
      }
    });

    //Set data to the individual list item
    WeatherForSlotEntity wfs = weather_report.getPrevisione().getGiorni().get(weather_day).getFasce().get(position);
    holder.txt_tmin.setText( "Temperatura minima: " + weather_report.getPrevisione().getGiorni().get(weather_day).gettMinGiorno() + "°");
    holder.txt_tmax.setText("Temperatura massima: " + weather_report.getPrevisione().getGiorni().get(weather_day).gettMaxGiorno() + "°");
    holder.txt_pressure.setText("Pressione atm.: " + open_weather_report.getPressure()+ " hPa");
    holder.txt_prob_prec.setText("Prob. precipitazioni: " +wfs.getDescPrecProb() + " %");
    holder.txt_intensita_prec.setText("Intensità precipitazioni: " +wfs.getDescPrecInten() + "");
    holder.txt_prob_temp.setText("Prob. Temporali: " +wfs.getDescTempProb() + " %");
    holder.txt_vento_quota.setText("Intensità vento in quota: " +wfs.getDescVentoIntQuota() + "");
    holder.txt_vento_valle.setText("Intensità vento in valle: " +wfs.getDescVentoIntValle() + "");
    holder.txt_vento_quota_dir.setText("Direzione vento in quota: " +wfs.getDescVentoDirQuota() + "");
    holder.txt_vento_valle_dir.setText("Direzione vento in valle: " +wfs.getDescVentoDirValle() + "");
    holder.txt_zero_termico.setText("Zero termico: " + wfs.getZeroTermico() + " m");
    holder.txt_prev_breve.setText("Previsione: " + weather_report.getPrevisione().getGiorni().get(weather_day).getDescIcona());

    holder.txt_time_slot.setText("Fascia per: "+wfs.getFasciaPer()+ " [" + wfs.getFasciaOre() + "]");

  }

  @Override
  public int getItemCount() {
    //Return the number of items in your list
    return weather_report.getPrevisione().getGiorni().get(weather_day).getFasce().size();
  }

}
