package it.chiarani.meteotrentinoapp.views;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;

import com.daasuu.ei.Ease;
import com.daasuu.ei.EasingInterpolator;
import com.takusemba.spotlight.OnSpotlightStateChangedListener;
import com.takusemba.spotlight.OnTargetStateChangedListener;
import com.takusemba.spotlight.Spotlight;
import com.takusemba.spotlight.shape.Circle;
import com.takusemba.spotlight.target.SimpleTarget;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.adapters.DaySlotsAdapter;
import it.chiarani.meteotrentinoapp.adapters.WeatherReportAdapter;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForDayEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForSlotEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
import it.chiarani.meteotrentinoapp.databinding.ActivityWeatherReportBinding;
import it.chiarani.meteotrentinoapp.repositories.OpenWeatherDataRepository;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;

public class WeatherReportActivity extends SampleActivity implements DaySlotsAdapter.ClickListener {

  // #region private fields
  private ActivityWeatherReportBinding binding;
  private int report_day = 0;
  private final String INTENT_DAY_TAG = "DAY";
    private int                   mExpandedPosition = -1;
  // #endregion

  @Override
  protected int getLayoutID() {
    return R.layout.activity_weather_report;
  }

  @Override
  protected void setActivityBinding() {
    binding = DataBindingUtil.setContentView(this, getLayoutID());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);


    // set toolbar color
    Window window = this.getWindow();
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    window.setStatusBarColor(getResources().getColor(R.color.toolbar_color));

    Intent intent = getIntent();
    if(intent.hasExtra(INTENT_DAY_TAG)) {
      report_day = intent.getExtras().getInt(INTENT_DAY_TAG);
    }

    WeatherReportRepository repository = new WeatherReportRepository(getApplication());
    repository.getAll().observe(this, entries -> {
      WeatherReportEntity report = entries.get(entries.size()-1);

       // activity_weather_report_rv_slot

    binding.activityWeatherReportRvSlot.setHasFixedSize(true);

    LinearLayoutManager linearLayoutManagerslot = new LinearLayoutManager(this);
        linearLayoutManagerslot.setOrientation(LinearLayoutManager.HORIZONTAL);
    binding.activityWeatherReportRvSlot.setLayoutManager(linearLayoutManagerslot);



      OpenWeatherDataRepository repository_op = new OpenWeatherDataRepository(getApplication());
      repository_op.getAll().observe(this, entries_op -> {

        if(entries_op == null) {
          return;
        }

          DaySlotsAdapter adapterslot = new DaySlotsAdapter(this, report, entries_op.get(entries_op.size()-1), report_day, this::onClick);
          binding.activityWeatherReportRvSlot.setAdapter(adapterslot);

          if(report.getPrevisione().getGiorni().get(report_day).getTestoGiorno().isEmpty()){
              binding.activityWeatherReportTxtPrevisione.setText("Bollettino metereologico non ancora pubblicato.");
          }
          else {
              binding.activityWeatherReportTxtPrevisione.setText(report.getPrevisione().getGiorni().get(report_day).getTestoGiorno() + "        ");
          }

          doBounceAnimation(binding.activityWeatherReportRvSlot);

        binding.activityWeatherReportTxtPosition.  setText(String.format("%s (%s m)", report.getPrevisione().getLocalita(), report.getPrevisione().getQuota()));


        if(report.getPrevisione().getGiorni().get(report_day).getDescIconaAllerte().isEmpty() == true)
        {
            binding.activityWeatherReportTxtTitleAllerte.setVisibility(View.GONE);
            binding.activityWeatherReportRlAllerte.setVisibility(View.GONE);
            binding.activityWeatherReportTxtDescriptionAllerte.setVisibility(View.GONE);
        }
        else
        {
            binding.activityWeatherReportTxtTitleAllerte.setVisibility(View.VISIBLE);
            binding.activityWeatherReportTxtDescriptionAllerte.setText(report.getPrevisione().getGiorni().get(report_day).getDescIconaAllerte() +
            "\nIl colore rappresenta l'intensità dell'allerta.");
            binding.activityWeatherReportRlAllerte.setVisibility(View.VISIBLE);
         //   binding.activityWeatherReportRlAllerte.setBackgroundResource(R.drawable.orange_rounded_bg);
           // binding.activityWeatherReportRlAllerte.setBackgroundColor(Color.parseColor("#F41E29"));

            String hex_color = report.getPrevisione().getGiorni().get(report_day).getColoreAllerte();

            GradientDrawable backgroundGradient = new GradientDrawable();
            backgroundGradient.setColor(Color.parseColor(hex_color));
            backgroundGradient.setCornerRadius(80);


            binding.activityWeatherReportRlAllerte.setBackgroundDrawable(backgroundGradient);

            binding.activityWeatherReportTxtDescriptionAllerte.setVisibility(View.VISIBLE);


        }

          SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
          callHelperGuide(getPrefs);

      });

    });

    binding.fragmentRadarDayBtnMenu.setOnClickListener(v -> onBackPressed());

  }

    private void doBounceAnimation(View targetView) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(targetView, "translationX", 0, 30, 0);
        animator.setInterpolator(new EasingInterpolator(Ease.ELASTIC_IN_OUT));
        animator.setStartDelay(500);
        animator.setDuration(1500);
        animator.start();
    }

    @Override
    public void onClick(int day, int position) {
        WeatherReportRepository repository = new WeatherReportRepository(getApplication());
        repository.getAll().observe(this, entries -> {

            WeatherForDayEntity wfd  = entries.get(entries.size()-1).getPrevisione().getGiorni().get(day);
            WeatherForSlotEntity wfs = wfd.getFasce().get(position);
            binding.activityWeatherReportDatiMetereologici.setText("DATI MERETOLOGICI PER: " + wfs.getFasciaPer().toUpperCase());
            binding.itemWeatherReportTxtTmin           .setText(String.format("%s: %s°", this.getResources().getString(R.string.s_weatherreportadapter_tmin), wfd.gettMinGiorno()));
            binding.itemWeatherReportTxtTmax           .setText(String.format("%s: %s°", this.getResources().getString(R.string.s_weatherreportadapter_tmax), wfd.gettMaxGiorno()));
            binding.itemWeatherReportTxtPressione       .setText(String.format("%s: %s hPa", this.getResources().getString(R.string.s_weatherreportadapter_pressure), ""));
            binding.itemWeatherReportTxtProbPrec      .setText(String.format("%s: %s%%", this.getResources().getString(R.string.s_weatherreportadapter_prob_prec), wfs.getDescPrecProb()));
            binding.itemWeatherReportTxtIntensitaPrec .setText(String.format("%s: %s", this.getResources().getString(R.string.s_weatherreportadapter_int_prec), wfs.getDescPrecInten()));
            binding.itemWeatherReportTxtProbTemp      .setText(String.format("%s: %s%%", this.getResources().getString(R.string.s_weatherreportadapter_prob_temporali), wfs.getDescTempProb()));
            binding.itemWeatherReportTxtVentoQuota    .setText(String.format("%s: %s", this.getResources().getString(R.string.s_weatherreportadapter_dir_vento_quota), wfs.getDescVentoIntQuota()));
            binding.itemWeatherReportTxtVentoValle    .setText(String.format("%s: %s", this.getResources().getString(R.string.s_weatherreportadapter_dir_vento_valle), wfs.getDescVentoIntValle()));
            binding.itemWeatherReportTxtVentoQuotaDir.setText(String.format("%s: %s" , this.getResources().getString(R.string.s_weatherreportadapter_int_vento_quota), wfs.getDescVentoDirQuota()));
            binding.itemWeatherReportTxtVentoValleDir.setText(String.format("%s: %s", this.getResources().getString(R.string.s_weatherreportadapter_int_vento_valle), wfs.getDescVentoDirValle()));
            binding.itemWeatherReportTxtZero   .setText(String.format("%s: %s m", this.getResources().getString(R.string.s_weatherreportadapter_zero_termico), wfs.getZeroTermico()));
            binding.itemWeatherReportTxtPrevBreve     .setText(String.format("%s: %s", this.getResources().getString(R.string.s_weatherreportadapter_previsione), wfd.getDescIcona()));
           // binding.activityWeatherReportRvSlot      .setText(String.format("%s: %s (%s)", this.getResources().getString(R.string.s_weatherreportadapter_fascia),wfs.getFasciaPer(), wfs.getFasciaOre()));
        });

    }


    private void callHelperGuide(SharedPreferences getPrefs) {

        //  Create a new boolean and preference and set it to true
        boolean isFirstStart = getPrefs.getBoolean("firstStartWeatherSlot", true);

        //  If the activity has never started before...
        if (isFirstStart) {
            SimpleTarget simpleTarget = new SimpleTarget.Builder(this)
                    .setPoint(binding.activityWeatherReportRvSlot)
                    .setShape(new Circle(200f))
                    .setTitle("Seleziona la fascia")
                    .setDescription("Scorri orizzontalmente per vedere tutte le fasce del giorno!")
                    .setOnSpotlightStartedListener(new OnTargetStateChangedListener<SimpleTarget>() {
                        @Override
                        public void onStarted(SimpleTarget target) {
                        }
                        @Override
                        public void onEnded(SimpleTarget target) {
                        }
                    })
                    .build();


            Spotlight.with(this)
                    .setOverlayColor(R.color.background)
                    .setDuration(10)
                    .setAnimation(new DecelerateInterpolator(1f))
                    .setTargets(simpleTarget)
                    .setClosedOnTouchedOutside(true)
                    .setOnSpotlightStateListener(new OnSpotlightStateChangedListener() {
                        @Override
                        public void onStarted() {
                        }

                        @Override
                        public void onEnded() {
                        }
                    })
                    .start();

            //  Make a new preferences editor
            SharedPreferences.Editor e = getPrefs.edit();

            //  Edit preference to make it false because we don't want this to run again
            e.putBoolean("firstStartWeatherSlot", false);

            //  Apply changes
            e.apply();
        }
    }
}
