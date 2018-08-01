package it.chiarani.meteotrentinoapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.chiarani.meteotrentinoapp.R;

public class WeatherSlotAdapter extends RecyclerView.Adapter<WeatherSlotAdapter.ViewHolder> {

  String[] _data;

  public WeatherSlotAdapter(String[] data) {
    _data = data;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    TextView txt_time_slot;

    public ViewHolder(View v) {
      super(v);
      txt_time_slot = v.findViewById(R.id.item_slot_weather_txt_fascia);
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
    holder.txt_time_slot.setText(_data[position]);
  }

  @Override
  public int getItemCount() {
    //Return the number of items in your list
    return _data.length;
  }

}
