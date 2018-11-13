package it.chiarani.meteotrentinoapp.adapters;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Collections;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.models.BulletProbFascia;
import it.chiarani.meteotrentinoapp.models.BulletProbFull;
import it.chiarani.meteotrentinoapp.xml_parser.XmlDatiOggi;

public class BulletProbAdapter extends RecyclerView.Adapter<BulletProbAdapter.ViewHolder> {

    // #region private fields
    private BulletProbFull data;
    private int day;
    // #endregion


    public BulletProbAdapter(int day, BulletProbFull data) {
        this.day = day;
        this.data = data;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_fascia;
        CardView crd_prec;
        TextView txt_prec;
        CardView crd_temp;
        TextView txt_temp;
        CardView crd_vento_v;
        TextView txt_vento_v;
        CardView crd_vento_m;
        TextView txt_vento_m;
        CardView crd_neve;
        TextView txt_neve;
        TextView txt_quota_neve;
        TextView txt_zero_termico;

        public ViewHolder(View v) {
            super(v);
            txt_fascia = v.findViewById(R.id.item_prob_bullet_txt_fascia);
            crd_prec = v.findViewById(R.id.item_prob_bullet_crd_precipitazioni_abbondanti);
            txt_prec = v.findViewById(R.id.item_prob_bullet_txt_precipitazioni_abbondanti);
            crd_temp = v.findViewById(R.id.item_prob_bullet_crd_rovesci_o_temporali);
            txt_temp = v.findViewById(R.id.item_prob_bullet_txt_rovesci_o_temporali);
            crd_vento_v = v.findViewById(R.id.item_prob_bullet_crd_vento_forte_in_valle);
            txt_vento_v = v.findViewById(R.id.item_prob_bullet_txt_vento_forte_in_valle);
            crd_vento_m = v.findViewById(R.id.item_prob_bullet_crd_vento_forte_in_montagna);
            txt_vento_m = v.findViewById(R.id.item_prob_bullet_txt_vento_forte_in_montagna);
            crd_neve = v.findViewById(R.id.item_prob_bullet_crd_nevicate);
            txt_neve = v.findViewById(R.id.item_prob_bullet_txt_nevicate);
            txt_quota_neve = v.findViewById(R.id.item_prob_bullet_txt_quota_neve);
            txt_zero_termico = v.findViewById(R.id.item_prob_bullet_txt_zero_termico);
        }
    }

    @Override
    public BulletProbAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View singleItemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prob_bullet, parent, false);
        return new ViewHolder(singleItemLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BulletProbFascia fascia = data.getGiorni().get(day).getFasce().get(position);


        holder.txt_fascia.setText(fascia.getFasciaPer());


        if(fascia.getFenomeni().size() == 6) {
           // holder.txt_quota_neve.setText(fascia.getFenomeni().get(5).getValore() + "");
            holder.txt_zero_termico.setText(fascia.getFenomeni().get(5).getValore() + "");
        }
        else if(fascia.getFenomeni().size() > 6) {
            holder.txt_quota_neve.setText(fascia.getFenomeni().get(5).getValore() + "");
            holder.txt_zero_termico.setText(fascia.getFenomeni().get(6).getValore() + "");
        }


        // Precipitazioni abbondanti
        switch (fascia.getFenomeni().get(0).getValore()) {
            case 0:
                setElementAttribute(holder.crd_prec, holder.txt_prec, "#93E374", fascia.getFenomeni().get(0).getValore() + "");
                break;
            case 1:
                setElementAttribute(holder.crd_prec, holder.txt_prec, "#FEFF5C", fascia.getFenomeni().get(0).getValore() + "");
                break;
            case 2:
                setElementAttribute(holder.crd_prec, holder.txt_prec, "#F7CC54", fascia.getFenomeni().get(0).getValore() + "");
                break;
            case 3:
                setElementAttribute(holder.crd_prec, holder.txt_prec, "#EB3325", fascia.getFenomeni().get(0).getValore() + "");
                break;
        }

        // Rovesci o temporali
        switch (fascia.getFenomeni().get(1).getValore()) {
            case 0:
                setElementAttribute(holder.crd_temp, holder.txt_temp, "#93E374", fascia.getFenomeni().get(1).getValore() + "");
                break;
            case 1:
                setElementAttribute(holder.crd_temp, holder.txt_temp, "#FEFF5C", fascia.getFenomeni().get(1).getValore() + "");
                break;
            case 2:
                setElementAttribute(holder.crd_temp, holder.txt_temp, "#F7CC54", fascia.getFenomeni().get(1).getValore() + "");
                break;
            case 3:
                setElementAttribute(holder.crd_temp, holder.txt_temp, "#EB3325", fascia.getFenomeni().get(1).getValore() + "");
                break;
        }

        // Vento forte in valle
        switch (fascia.getFenomeni().get(2).getValore()) {
            case 0:
                setElementAttribute(holder.crd_vento_v, holder.txt_vento_v, "#93E374", fascia.getFenomeni().get(2).getValore() + "");
                break;
            case 1:
                setElementAttribute(holder.crd_vento_v, holder.txt_vento_v, "#FEFF5C", fascia.getFenomeni().get(2).getValore() + "");
                break;
            case 2:
                setElementAttribute(holder.crd_vento_v, holder.txt_vento_v, "#F7CC54", fascia.getFenomeni().get(2).getValore() + "");
                break;
            case 3:
                setElementAttribute(holder.crd_vento_v, holder.txt_vento_v, "#EB3325", fascia.getFenomeni().get(2).getValore() + "");
                break;
        }

        // Vento forte in montagna
        switch (fascia.getFenomeni().get(3).getValore()) {
            case 0:
                setElementAttribute(holder.crd_vento_m, holder.txt_vento_m, "#93E374", fascia.getFenomeni().get(3).getValore() + "");
                break;
            case 1:
                setElementAttribute(holder.crd_vento_m, holder.txt_vento_m, "#FEFF5C", fascia.getFenomeni().get(3).getValore() + "");
                break;
            case 2:
                setElementAttribute(holder.crd_vento_m, holder.txt_vento_m, "#F7CC54", fascia.getFenomeni().get(3).getValore() + "");
                break;
            case 3:
                setElementAttribute(holder.crd_vento_m, holder.txt_vento_m, "#EB3325", fascia.getFenomeni().get(3).getValore() + "");
                break;
        }

        // Nevicate
        switch (fascia.getFenomeni().get(4).getValore()) {
            case 0:
                setElementAttribute(holder.crd_neve, holder.txt_neve, "#93E374", fascia.getFenomeni().get(4).getValore() + "");
                break;
            case 1:
                setElementAttribute(holder.crd_neve, holder.txt_neve, "#FEFF5C", fascia.getFenomeni().get(4).getValore() + "");
                break;
            case 2:
                setElementAttribute(holder.crd_neve, holder.txt_neve, "#F7CC54", fascia.getFenomeni().get(4).getValore() + "");
                break;
            case 3:
                setElementAttribute(holder.crd_neve, holder.txt_neve, "#EB3325", fascia.getFenomeni().get(4).getValore() + "");
                break;
        }
    }

    private void setElementAttribute(CardView crdv, TextView txtv, String color, String text) {
        txtv.setText(text);
        crdv.setCardBackgroundColor(Color.parseColor(color));
        crdv.setRadius(4);
    }

    @Override
    public int getItemCount() {
        return data.getGiorni().get(day).getFasce().size();
    }
}
