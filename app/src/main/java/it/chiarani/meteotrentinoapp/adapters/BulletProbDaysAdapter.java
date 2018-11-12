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

import com.amulyakhare.textdrawable.TextDrawable;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.database.entity.OpenWeatherDataEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForDayEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForSlotEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
import it.chiarani.meteotrentinoapp.helper.WeatherIconDescriptor;
import it.chiarani.meteotrentinoapp.models.BulletProbFull;


public class BulletProbDaysAdapter extends RecyclerView.Adapter<BulletProbDaysAdapter.ViewHolder> {

    // #region private fields
    private BulletProbFull data;
    private Context mContext;
    private int oldPos = 0;
    private ClickListener listener;
    private int first_entry = 1;
    // #endregion

    /**
     * Adapter constructor
     */
    public BulletProbDaysAdapter(Context mContext, BulletProbFull data, ClickListener listener) {
        this.mContext            = mContext;
        this.data                = data;
        this.listener            = listener;
    }

    public interface ClickListener{
        void onClick(int day, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //TextView       txt_title;
        TextView txt_description;
        ImageView img_weather;
        RelativeLayout rl;

        public ViewHolder(View v) {
            super(v);
            txt_description = v.findViewById(R.id.item_day_slots__txt_subtitle);
            rl = v.findViewById(R.id.item_day_slots_rl);
            img_weather  = v.findViewById(R.id.item_day_slots_img_weather);
        }
    }

    @Override
    public BulletProbDaysAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View singleItemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_slots, parent, false);
        return new ViewHolder(singleItemLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // declare the builder object once.
        TextDrawable.IBuilder builder = TextDrawable.builder()
                .beginConfig()
                .withBorder(0)
                .fontSize(40) /* size in px */
                .endConfig()
                .roundRect(70);

        // reuse the builder specs to create multiple drawables
        TextDrawable ic1 =  builder.build(data.getGiorni().get(position).getDataGiorno().split("-")[2], Color.GRAY);
        holder.img_weather.setImageDrawable(ic1);

        if(first_entry == 1) {
            listener.onClick(-1, position);
            first_entry = 2;
        }

        holder.txt_description.setText(data.getGiorni().get(position).getNomeGiorno());

        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPos = position;
                notifyDataSetChanged();
                listener.onClick(-1, position);
            }
        });


        if(oldPos == position) {
            holder.rl.setBackground(mContext.getResources().getDrawable(R.drawable.blu_rounded_bg));
            holder.txt_description.setTextColor(Color.WHITE);
        }
        else {
            holder.rl.setBackground(mContext.getResources().getDrawable(R.drawable.white_rounded_bg));
            holder.txt_description.setTextColor(Color.GRAY);
        }
    }


    @Override
    public int getItemCount() {
        return data.getGiorni().size();
    }
}
