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
import it.chiarani.meteotrentinoapp.adapters.AllerteListAdapter;
import it.chiarani.meteotrentinoapp.api.API_protezioneCivileAvvisiAllerte;
import it.chiarani.meteotrentinoapp.api.API_protezioneCivileAvvisiAllerte_response;
import it.chiarani.meteotrentinoapp.databinding.ActivityAllerteBinding;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;

public class AllerteActivity extends SampleActivity implements API_protezioneCivileAvvisiAllerte_response, RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener{

  // #region private fields
  private ActivityAllerteBinding binding;
  private final static String URL_PROVINCIA = "http://www.protezionecivile.tn.it/news_comunicati_stampa/";
  private final static String URL_METEO     = "https://www.meteotrentino.it/#!/content?menuItemDesktop=44";

  private RapidFloatingActionLayout rfaLayout;
  private RapidFloatingActionButton rfaBtn;
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

    rfaLayout = findViewById(R.id.activity_main_rfal);
    rfaBtn = findViewById(R.id.activity_main_rfab);

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
    // set toolbar color
    Window window = this.getWindow();
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    window.setStatusBarColor(Color.parseColor("#65A8D9"));

    WeatherReportRepository repo = new WeatherReportRepository(getApplication());
    repo.getAll().observe(this, entries -> {

      binding.fragmentRadarDayBtnMenu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          onBackPressed();
        }
      });
    });

    new API_protezioneCivileAvvisiAllerte(getApplication(), this, this::processFinish).execute();
  }

  @Override
  public void processFinish(ArrayList<String> data) {
    // use this setting to improve performance if you know that changes
    // in content do not change the layout size of the RecyclerView
    binding.activityAllerteRv.setHasFixedSize(true);

    LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    binding.activityAllerteRv.setLayoutManager(horizontalLayoutManagaer);
    AllerteListAdapter adapter1 = new AllerteListAdapter(data);
    binding.activityAllerteRv.setAdapter(adapter1);


  }

  @Override
  public void onRFACItemLabelClick(int position, RFACLabelItem item) {
    Toast.makeText(this, "clicked label: " + position, Toast.LENGTH_SHORT).show();

    if(position == 0) {
      // meteo
      Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(URL_METEO));
      startActivity(i);
    }
    else
    {
      Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(URL_PROVINCIA));
      startActivity(i);
    }
    rfabHelper.toggleContent();
  }

  @Override
  public void onRFACItemIconClick(int position, RFACLabelItem item) {
    if(position == 0) {
      // meteo
      Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(URL_METEO));
      startActivity(i);
    }
    else
    {
      Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(URL_PROVINCIA));
      startActivity(i);
    }
    rfabHelper.toggleContent();
  }
}
