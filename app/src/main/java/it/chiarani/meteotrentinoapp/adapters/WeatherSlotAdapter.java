package it.chiarani.meteotrentinoapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.models.WeatherReport;

public class WeatherSlotAdapter extends RecyclerView.Adapter<WeatherSlotAdapter.ViewHolder> {

  WeatherReport _report;

  public WeatherSlotAdapter(WeatherReport report) {
    _report = report;
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
    holder.txt_time_slot.setText("Fascia Oraria: " + _report.getPrevisione().getGiorni().get(0).getFasce().get(position).getFasciaOre());
    holder.txt_prob_prec.setText(_report.getPrevisione().getGiorni().get(0).getFasce().get(position).getDescPrecProb());
    holder.txt_humidity.setText("75%");
    holder.txt_prob_temp.setText(_report.getPrevisione().getGiorni().get(0).getFasce().get(position).getDescTempProb());
    holder.txt_vento.setText(_report.getPrevisione().getGiorni().get(0).getFasce().get(position).getDescVentoIntValle());
  }

  @Override
  public int getItemCount() {
    //Return the number of items in your list
    return _report.getPrevisione().getGiorni().get(0).getFasce().size();
  }

}
