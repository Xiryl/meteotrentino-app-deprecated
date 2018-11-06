package it.chiarani.meteotrentinoapp.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

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
    ImageView      img_colored;
    RelativeLayout rl;

    public ViewHolder(View v) {
      super(v);
      txt_name = v.findViewById(R.id.item_allerte_txt_title);
      txt_day  = v.findViewById(R.id.item_allerte_txt_subtitle);
      //btn_link = v.findViewById(R.id.item_allerte_btn_link);
      img_colored     = v.findViewById(R.id.item_allerte_img_colored);
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

    ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT


    String[] alert = alerts.get(position).split(";");
    String data    = alert[1];
    String title   = alert[0];
    String link    = alert[2];
    holder.txt_day.setText( String.format("%s", data));
    holder.txt_name.setText(String.format("%s", title));

    // generate color based on a key (same key returns the same color), useful for list/grid views
    int color2 = generator.getColor(convertMonth(data.split(" ")[1]));

    // declare the builder object once.
    TextDrawable.IBuilder builder = TextDrawable.builder()
            .beginConfig()
            .withBorder(0)
            .fontSize(40) /* size in px */
            .endConfig()
            .roundRect(70);

    // reuse the builder specs to create multiple drawables
    TextDrawable ic1 =  builder.build(convertMonth(data.split(" ")[1]), color2);
    holder.img_colored.setImageDrawable(ic1);

   /* holder.btn_link.setOnClickListener(v -> {
      // open link
      Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(API_endpoint.GOOGLE_DOCS_BASE + link));
      v.getContext().startActivity(browserIntent);
    });*/
  }


  private String convertMonth(String month) {
    switch (month) {
      case "gennaio":       return "GEN";
      case "febbraio":      return "FEB";
      case "marzo":         return "MAR";
      case "aprile":        return "APR";
      case "maggio":        return "MAG";
      case "giugno":        return "GIU";
      case "luglio":        return "LUG";
      case "agosto":        return "AGO";
      case "settembre":     return "SET";
      case "ottobre":       return "OTT";
      case "novembre":      return "NOV";
      case "dicembre":      return "DIC";
      default:              return " ";

    }
  }

  @Override
  public int getItemCount() {
    return alerts.size();
  }
}
