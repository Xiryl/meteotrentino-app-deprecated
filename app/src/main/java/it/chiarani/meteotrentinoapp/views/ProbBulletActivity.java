package it.chiarani.meteotrentinoapp.views;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.daasuu.ei.Ease;
import com.daasuu.ei.EasingInterpolator;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.adapters.BulletProbAdapter;
import it.chiarani.meteotrentinoapp.adapters.BulletProbDaysAdapter;
import it.chiarani.meteotrentinoapp.adapters.DaySlotsAdapter;
import it.chiarani.meteotrentinoapp.api.API_bullet_prob;
import it.chiarani.meteotrentinoapp.api.API_bullet_prob_response;
import it.chiarani.meteotrentinoapp.databinding.ActivityProbBulletBinding;
import it.chiarani.meteotrentinoapp.helper.CustomDialog;
import it.chiarani.meteotrentinoapp.models.BulletProbFull;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;

public class ProbBulletActivity extends SampleActivity implements API_bullet_prob_response, BulletProbDaysAdapter.ClickListener {

    private ActivityProbBulletBinding binding;
    private BulletProbFull data;
    private final static String URL_BOLLETTINO_PROBABILISTICO = "https://www.meteotrentino.it/protcivtn-meteo/api/front/bollettinoProb?idPrevisione=";


    @Override
    protected int getLayoutID() {
        return R.layout.activity_prob_bullet;
    }

    @Override
    protected void setActivityBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutID());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        API_bullet_prob api_bullet_prob = new API_bullet_prob(getApplication(), this, this::processFinish);
        api_bullet_prob.execute();

        binding.fragmentRadarDayBtnMenu.setOnClickListener(v -> onBackPressed());

        binding.btnInfoBullet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog cdd = new CustomDialog(ProbBulletActivity.this, "[0] VERDE: Molto bassa\n[1] GIALLO: bassa\n[2] ARANCIO: Media\n[3] ROSSO: Alta");
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();
            }
        });

        binding.btnDownloadProbBullettin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherReportRepository repo = new WeatherReportRepository(getApplication());
                repo.getAll().observeForever( entries -> {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL_BOLLETTINO_PROBABILISTICO + entries.get(entries.size()-1).getIdPrevisione() + "&history=0"));
                    startActivity(browserIntent);
                });

            }
        });
    }

    @Override
    public void processFinish(BulletProbFull data) {
        this.data = data;
        binding.activityProbBulletRvSlot.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManagerslot1 = new LinearLayoutManager(this);
        linearLayoutManagerslot1.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.activityProbBulletRvSlot.setLayoutManager(linearLayoutManagerslot1);

        BulletProbDaysAdapter adapterslot = new BulletProbDaysAdapter(getApplicationContext(), data, this::onClick);
        binding.activityProbBulletRvSlot.setAdapter(adapterslot);

        doBounceAnimation(binding.activityProbBulletRvSlot);
    }

    @Override
    public void onClick(int day, int position) {
        binding.activityProbBulletMainRvBulletProb.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManagerslot = new LinearLayoutManager(this);
        linearLayoutManagerslot.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.activityProbBulletMainRvBulletProb.setLayoutManager(linearLayoutManagerslot);

        BulletProbAdapter adapterslot = new BulletProbAdapter(position, this.data);
        binding.activityProbBulletMainRvBulletProb.setAdapter(adapterslot);
    }

    private void doBounceAnimation(View targetView) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(targetView, "translationX", 0, 30, 0);
        animator.setInterpolator(new EasingInterpolator(Ease.ELASTIC_IN_OUT));
        animator.setStartDelay(500);
        animator.setDuration(1500);
        animator.start();
    }



}
