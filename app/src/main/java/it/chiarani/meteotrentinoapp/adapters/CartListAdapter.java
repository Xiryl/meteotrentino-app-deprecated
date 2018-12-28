package it.chiarani.meteotrentinoapp.adapters;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.models.CardRemovableInformation;

import static android.app.Activity.RESULT_OK;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.MyViewHolder> {

    private Context context;
    private List<CardRemovableInformation> cartList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, description;
        public ImageView img;
        public RelativeLayout viewBackground, viewForeground;

        public MyViewHolder(View view) {
            super(view);
            description = view.findViewById(R.id.fragment_info_description);
            title = view.findViewById(R.id.fragment_info_title);
            img = view.findViewById(R.id.fragment_info_img);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = this.getAdapterPosition();
            if(pos == 2) {
                context.startActivity(cartList.get(pos).getIntent());
            }
        }
    }


    public CartListAdapter(Context context, List<CardRemovableInformation> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_info_widget, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.title.setText(cartList.get(position).getTitle());
        holder.description.setText(cartList.get(position).getDescription());
        holder.img.setImageDrawable(this.context.getResources().getDrawable(cartList.get(position).getResource()));
        holder.viewForeground.setBackground(this.context.getResources().getDrawable(cartList.get(position).getBackgroundRes()));
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public void removeItem(int position) {
        cartList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(CardRemovableInformation item, int position) {
        cartList.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }
}