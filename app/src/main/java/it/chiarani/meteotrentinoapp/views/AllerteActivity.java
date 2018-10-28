package it.chiarani.meteotrentinoapp.views;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;

import java.util.ArrayList;
import java.util.List;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.adapters.AlertListAdapter;
import it.chiarani.meteotrentinoapp.api.API_endpoint;
import it.chiarani.meteotrentinoapp.api.API_protezioneCivileAvvisiAllerte;
import it.chiarani.meteotrentinoapp.api.API_protezioneCivileAvvisiAllerte_response;
import it.chiarani.meteotrentinoapp.databinding.ActivityAllerteBinding;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;

public class AllerteActivity extends SampleActivity implements API_protezioneCivileAvvisiAllerte_response, RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener{

  // #region private fields
  private ActivityAllerteBinding binding;
  private RapidFloatingActionHelper rfabHelper;
  // #endregion

  @Override
  protected int getLayoutID() {
    return R.layout.activity_allerte;
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

    RapidFloatingActionLayout rfaLayout = findViewById(R.id.activity_main_rfal);
    RapidFloatingActionButton rfaBtn    = findViewById(R.id.activity_main_rfab);

    RapidFloatingActionContentLabelList rfaContent = new RapidFloatingActionContentLabelList(this);
    rfaContent.setOnRapidFloatingActionContentLabelListListener(this);
    List<RFACLabelItem> items = new ArrayList<>();

    items.add(new RFACLabelItem<Integer>()
        .setLabel("Allerte Meteotrentino")
        .setResId(R.drawable.ic_link_w)
        .setIconNormalColor(Color.parseColor("#F8CE5D"))
        .setIconPressedColor(Color.parseColor("#F8CE5D"))
        .setLabelSizeSp(14)
        .setWrapper(1)
    );

    items.add(new RFACLabelItem<Integer>()
        .setLabel("Allerte Protezione Civile")
        .setResId(R.drawable.ic_link_w)
        .setIconNormalColor(Color.parseColor("#489E64"))
        .setIconPressedColor(Color.parseColor("#489E64"))
        .setWrapper(2)
    );

    rfaContent
        .setItems(items)
        .setIconShadowColor(0xff888888)
    ;
    rfabHelper = new RapidFloatingActionHelper(
        this,
        rfaLayout,
        rfaBtn,
        rfaContent
    ).build();

    WeatherReportRepository repo = new WeatherReportRepository(getApplication());
    repo.getAll().observe(this, entries -> binding.fragmentRadarDayBtnMenu.setOnClickListener(v -> onBackPressed()));

    new API_protezioneCivileAvvisiAllerte(this, this::processFinish).execute();
  }

  @Override
  public void processFinish(ArrayList<String> data) {
    binding.activityAllerteRv.setHasFixedSize(true);

    LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    binding.activityAllerteRv.setLayoutManager(horizontalLayoutManagaer);

    AlertListAdapter adapter1 = new AlertListAdapter(data);
    binding.activityAllerteRv.setAdapter(adapter1);
  }

  @Override
  public void onRFACItemLabelClick(int position, RFACLabelItem item) {
    launchBrowser(position);
    rfabHelper.toggleContent();
  }

  @Override
  public void onRFACItemIconClick(int position, RFACLabelItem item) {
    launchBrowser(position);
    rfabHelper.toggleContent();
  }

  private void launchBrowser(int position) {
    if(position == 0) {
      // meteo
      Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(API_endpoint.URL_ALLERTE_METEOTT));
      startActivity(i);
    }
    else
    {
      Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(API_endpoint.URL_ALLERTE_PROVINCIA));
      startActivity(i);
    }
  }
}
