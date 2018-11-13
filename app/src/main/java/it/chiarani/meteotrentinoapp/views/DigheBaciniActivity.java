package it.chiarani.meteotrentinoapp.views;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.databinding.ActivityDigheBaciniBinding;
import it.chiarani.meteotrentinoapp.helper.JSONUtilities;
import it.chiarani.meteotrentinoapp.helper.WeatherStation;

public class DigheBaciniActivity extends SampleActivity {

    ActivityDigheBaciniBinding binding;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_dighe_bacini;
    }

    @Override
    protected void setActivityBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutID());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.fragmentRadarDayBtnMenu.setOnClickListener(v -> onBackPressed());

        try {
            JSONObject jsonObject = new JSONObject(JSONUtilities.loadJSONFromAsset(this, "bacini.json"));
            List<String> bacini = new ArrayList<>();
            bacini.add("brenta");
            bacini.add("agno");
            bacini.add("piave");
            bacini.add("livenza");
            bacini.add("monticano");
            bacini.add("scolante laguna");
            bacini.add("adige");
            bacini.add("po/mincio");
            bacini.add("brenta/cismon");

            // fill spinner
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bacini);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.activityDigheBaciniSpinnerBacini.setAdapter(adapter);
            //binding.activityDigheBaciniSpinnerBacini.setOnItemSelectedListener(this);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
