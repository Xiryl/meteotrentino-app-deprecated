package it.chiarani.meteotrentinoapp.adapters;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.api.API_endpoint;

/**
 * Adapter for allerte used in AllerteActivity
 */

public class AlertListAdapter extends RecyclerView.Adapter<AlertListAdapter.ViewHolder> {

  // #region private fields
  private ArrayList<String> alerts;
  // #endregion

  public AlertListAdapter(ArrayList<String> data) {
    this.alerts = data;
  }

  /**
   * ViewHolder
   */
  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    /**
     * UI elements
     */
    TextView       txt_name;
    TextView       txt_day;
    ImageButton    btn_link;
    CardView       card;
    RelativeLayout rl;

    public ViewHolder(View v) {
      super(v);
      txt_name = v.findViewById(R.id.item_allerte_txt_title);
      txt_day  = v.findViewById(R.id.item_allerte_txt_subtitle);
      btn_link = v.findViewById(R.id.item_allerte_btn_link);
      card     = v.findViewById(R.id.item_allerte_cardview);
      rl       = v.findViewById(R.id.item_allerte_rl);
      v.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      int pos = this.getLayoutPosition();
      String link = alerts.get(pos).split(";")[2];

      // Open link
      Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(API_endpoint.GOOGLE_DOCS_BASE + link));
      v.getContext().startActivity(browserIntent);
    }
  }

  @Override
  public AlertListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View singleItemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_allerte, parent, false);
    return new ViewHolder(singleItemLayout);
  }

  /**
   * Single Item
   * the position is the absolute
   */
  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {

    if(position == 0) {
      holder.rl.setBackgroundResource(R.drawable.allerte_item_list_gradient_accent);
    }
    else {
      holder.rl.setBackgroundResource(R.drawable.allerte_item_list_gradient);
    }

    String[] alert = alerts.get(position).split(";");
    String data    = alert[1];
    String title   = alert[0];
    String link    = alert[2];
    holder.txt_day.setText( String.format("%s", data));
    holder.txt_name.setText(String.format("%s", title));

    holder.btn_link.setOnClickListener(v -> {
      // open link
      Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(API_endpoint.GOOGLE_DOCS_BASE + link));
      v.getContext().startActivity(browserIntent);
    });
  }

  @Override
  public int getItemCount() {
    return alerts.size();
  }
}
