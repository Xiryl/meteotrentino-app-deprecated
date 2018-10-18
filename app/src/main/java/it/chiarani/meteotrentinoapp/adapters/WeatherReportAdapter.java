package it.chiarani.meteotrentinoapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.database.entity.OpenWeatherDataEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForDayEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForSlotEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
import it.chiarani.meteotrentinoapp.models.WeatherForDay;
import it.chiarani.meteotrentinoapp.models.WeatherForSlot;

/**
 * Adapter for weather detail used in BulletinActivity
 */
public class WeatherReportAdapter extends RecyclerView.Adapter<WeatherReportAdapter.ViewHolder> {

  // #region private fields
  private WeatherReportEntity   weather_report;
  private OpenWeatherDataEntity open_weather_report;
  private int                   weather_day;
  private int                   mExpandedPosition = -1;
  private Context               mContext;
  // #endregion

  /**
   * Adapter constructor
   * @param weather_report report data
   * @param open_weather_report openweather report data
   * @param weather_day day number (1-7)
   */
  public WeatherReportAdapter(Context mContext, WeatherReportEntity weather_report, OpenWeatherDataEntity open_weather_report, int weather_day) {
    this.mContext            = mContext;
    this.weather_report      = weather_report;
    this.open_weather_report = open_weather_report;
    this.weather_day         = weather_day;
  }


  class ViewHolder extends RecyclerView.ViewHolder {

    TextView     txt_time_slot;
    TextView     txt_tmin;
    TextView     txt_tmax;
    TextView     txt_pressure;
    TextView     txt_prob_prec;
    TextView     txt_intensita_prec;
    TextView     txt_prob_temp;
    TextView     txt_vento_quota;
    TextView     txt_vento_valle;
    TextView     txt_vento_quota_dir;
    TextView     txt_vento_valle_dir;
    TextView     txt_zero_termico;
    TextView     txt_prev_breve;
    TextView     txt_allerta;
    ImageView    exp_arrow;
    LinearLayout ll_gen;
    LinearLayout ll_prec;
    LinearLayout ll_vento;

    public ViewHolder(View v) {
      super(v);
      txt_time_slot       = v.findViewById(R.id.item_weather_report_txt_fascia);
      txt_tmin            = v.findViewById(R.id.item_weather_report_txt_tmin);
      txt_tmax            = v.findViewById(R.id.item_weather_report_txt_tmax);
      txt_pressure        = v.findViewById(R.id.item_weather_report_txt_pressione);
      txt_prob_prec       = v.findViewById(R.id.item_weather_report_txt_prob_prec);
      txt_intensita_prec  = v.findViewById(R.id.item_weather_report_txt_intensita_prec);
      txt_prob_temp       = v.findViewById(R.id.item_weather_report_txt_prob_temp);
      txt_vento_quota     = v.findViewById(R.id.item_weather_report_txt_vento_quota);
      txt_vento_valle     = v.findViewById(R.id.item_weather_report_txt_vento_valle);
      txt_vento_quota_dir = v.findViewById(R.id.item_weather_report_txt_vento_quota_dir);
      txt_vento_valle_dir = v.findViewById(R.id.item_weather_report_txt_vento_valle_dir);
      txt_zero_termico    = v.findViewById(R.id.item_weather_report_txt_zero);
      txt_prev_breve      = v.findViewById(R.id.item_weather_report_txt_prev_breve);
      txt_allerta         = v.findViewById(R.id.item_weather_report_allerta);
      ll_gen              = v.findViewById(R.id.item_weather_report_l_gen);
      ll_prec             = v.findViewById(R.id.item_weather_report_l_pioggia);
      ll_vento            = v.findViewById(R.id.item_weather_report_l_vento);
      exp_arrow           = v.findViewById(R.id.item_weather_report_img_arrow);
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
     * To collapse elements:
     * https://stackoverflow.com/questions/27203817/recyclerview-expand-collapse-items
     */

    final boolean isExpanded = position == mExpandedPosition;

    holder.exp_arrow          .setVisibility(isExpanded?View.GONE:View.VISIBLE);
    holder.txt_tmin           .setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_tmax           .setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_pressure       .setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_prob_prec      .setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_intensita_prec .setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_prob_temp      .setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_vento_quota    .setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_vento_valle    .setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_vento_quota_dir.setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_vento_valle_dir.setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_zero_termico   .setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.txt_prev_breve     .setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.ll_gen             .setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.ll_prec            .setVisibility(isExpanded?View.VISIBLE:View.GONE);
    holder.ll_vento           .setVisibility(isExpanded?View.VISIBLE:View.GONE);

    holder.itemView.setActivated(isExpanded);

    WeatherForDayEntity wfd  = weather_report.getPrevisione().getGiorni().get(weather_day);
    WeatherForSlotEntity wfs = wfd.getFasce().get(position);

    if(wfd.getDescIconaAllerte().isEmpty()) {
      holder.txt_allerta.setVisibility(View.GONE);
    }
    else {
      // show allerta info
      holder.txt_allerta.setVisibility(View.VISIBLE);
      holder.txt_allerta.setText(wfd.getDescIconaAllerte());

      String hex_color = wfd.getColoreAllerte();
      holder.txt_allerta.setBackgroundColor(Color.parseColor(hex_color));
    }

    holder.itemView.setOnClickListener(v -> {
      mExpandedPosition = isExpanded ? -1 : position;
      notifyItemChanged(position);
    });

    // set item data
    holder.txt_tmin           .setText(String.format("%s: %s°", mContext.getResources().getString(R.string.s_weatherreportadapter_tmin), wfd.gettMinGiorno()));
    holder.txt_tmax           .setText(String.format("%s: %s°", mContext.getResources().getString(R.string.s_weatherreportadapter_tmax), wfd.gettMaxGiorno()));
    holder.txt_pressure       .setText(String.format("%s: %s hPa", mContext.getResources().getString(R.string.s_weatherreportadapter_pressure), open_weather_report.getPressure()));
    holder.txt_prob_prec      .setText(String.format("%s: %s%%", mContext.getResources().getString(R.string.s_weatherreportadapter_prob_prec), wfs.getDescPrecProb()));
    holder.txt_intensita_prec .setText(String.format("%s: %s", mContext.getResources().getString(R.string.s_weatherreportadapter_int_prec), wfs.getDescPrecInten()));
    holder.txt_prob_temp      .setText(String.format("%s: %s%%", mContext.getResources().getString(R.string.s_weatherreportadapter_prob_temporali), wfs.getDescTempProb()));
    holder.txt_vento_quota    .setText(String.format("%s: %s", mContext.getResources().getString(R.string.s_weatherreportadapter_dir_vento_quota), wfs.getDescVentoIntQuota()));
    holder.txt_vento_valle    .setText(String.format("%s: %s", mContext.getResources().getString(R.string.s_weatherreportadapter_dir_vento_valle), wfs.getDescVentoIntValle()));
    holder.txt_vento_quota_dir.setText(String.format("%s: %s" , mContext.getResources().getString(R.string.s_weatherreportadapter_int_vento_quota), wfs.getDescVentoDirQuota()));
    holder.txt_vento_valle_dir.setText(String.format("%s: %s", mContext.getResources().getString(R.string.s_weatherreportadapter_int_vento_valle), wfs.getDescVentoDirValle()));
    holder.txt_zero_termico   .setText(String.format("%s: %s m", mContext.getResources().getString(R.string.s_weatherreportadapter_zero_termico), wfs.getZeroTermico()));
    holder.txt_prev_breve     .setText(String.format("%s: %s", mContext.getResources().getString(R.string.s_weatherreportadapter_previsione), wfd.getDescIcona()));
    holder.txt_time_slot      .setText(String.format("%s: %s (%s)", mContext.getResources().getString(R.string.s_weatherreportadapter_fascia),wfs.getFasciaPer(), wfs.getFasciaOre()));

  }

  @Override
  public int getItemCount() {
    return weather_report.getPrevisione().getGiorni().get(weather_day).getFasce().size();
  }
}
