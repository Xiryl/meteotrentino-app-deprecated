package it.chiarani.meteotrentinoapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class WeatherSlotAdapter extends RecyclerView.Adapter<WeatherSlotAdapter.ViewHolder> {

  public WeatherSlotAdapter() {

  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    public ViewHolder(View v) {
      super(v);
    }
  }

  @Override
  public WeatherSlotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    //Define a layout file for individual list item
    return null;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    //Set data to the individual list item
  }

  @Override
  public int getItemCount() {
    //Return the number of items in your list
    return 0;
  }
  
}
