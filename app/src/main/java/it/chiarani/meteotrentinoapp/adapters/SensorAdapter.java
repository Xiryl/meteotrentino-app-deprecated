package it.chiarani.meteotrentinoapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.database.entity.OpenWeatherDataEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForDayEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForSlotEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
import it.chiarani.meteotrentinoapp.helper.WeatherIconDescriptor;
import it.chiarani.meteotrentinoapp.models.BulletProbFull;
import it.chiarani.meteotrentinoapp.models.StazioniBacini;


public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.ViewHolder> {

    // #region private fields
    private StazioniBacini stazione;
    private int                   mExpandedPosition = -1;
    private Context mContext;
    private ViewHolder viewClicked;
    private int day;
    private int oldPos = 0;
    private ClickListener listener;
    private int first_entry = 1;
    // #endregion

    /**
     * Adapter constructor
     */
    public SensorAdapter(Context mContext, StazioniBacini stazione, ClickListener listener,int day) {
        this.mContext            = mContext;
        this.listener            = listener;
        this.day = day;
        this.stazione = stazione;
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
    public SensorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View singleItemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_slots, parent, false);
        return new ViewHolder(singleItemLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(first_entry == 1) {
            listener.onClick(day, position);
            first_entry = 2;
        }
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPos = position;
                notifyDataSetChanged();
                listener.onClick(0, position);
            }
        });

        switch (stazione.getSensori().get(position).getNome_sensore())
        {
            case "pluviometro" :
                holder.txt_description.setText("Pluviometro");
                holder.img_weather.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.ic_bacino_pluviometro));
                break;
            case "idrometro" :
                holder.txt_description.setText("Barometro");
                holder.img_weather.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.ic_bacino_idrometro));
                break;
            case "temperatura_aria" :
                holder.txt_description.setText("Temperatura\nAria");
                holder.img_weather.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.ic_bacino_temperatura_aria));
                break;
            case "igrometro" :
                holder.txt_description.setText("Igrometro");
                holder.img_weather.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.ic_bacini_igrometro));
                break;
            case "nivometro" :
                holder.txt_description.setText("Nivometro");
                holder.img_weather.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.ic_bacini_nivometro));
                break;
            case "barometro" :
                holder.txt_description.setText("Barometro");
                holder.img_weather.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.ic_bacini_barometer));
                break;
            case "radiometro" :
                holder.txt_description.setText("Radiometro");
                holder.img_weather.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.ic_bacini_radiometer));
                break;
            case "vento" :
                holder.txt_description.setText("Vento");
                holder.img_weather.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.ic_bacini_vento));
                break;
            case "direzione_vento" :
                holder.txt_description.setText("Direzione\nVento");
                holder.img_weather.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.ic_bacini_dir_vento));
                break;
            case "portata" :
                holder.txt_description.setText("Portata");
                holder.img_weather.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.ic_bacini_portata));
                break;
        }

        if(oldPos == position) {
            holder.rl.setBackground(mContext.getResources().getDrawable(R.drawable.gray_rounded_bg));
        }
        else
        {
            holder.rl.setBackground(mContext.getResources().getDrawable(R.drawable.white_rounded_bg));
        }


    }


    @Override
    public int getItemCount() {
        return stazione.getSensori().size();
    }
}
