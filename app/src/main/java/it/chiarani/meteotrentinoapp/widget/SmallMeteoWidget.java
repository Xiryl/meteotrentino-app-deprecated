package it.chiarani.meteotrentinoapp.widget;

import android.app.Application;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.arch.persistence.room.Room;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.List;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.api.API_weatherReport;
import it.chiarani.meteotrentinoapp.api.API_weatherReport_response;
import it.chiarani.meteotrentinoapp.database.AppDatabase;
import it.chiarani.meteotrentinoapp.database.dao.WeatherReportDao;
import it.chiarani.meteotrentinoapp.database.entity.LocationEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
import it.chiarani.meteotrentinoapp.helper.WeatherIconDescriptor;
import it.chiarani.meteotrentinoapp.models.WeatherForDay;
import it.chiarani.meteotrentinoapp.models.WeatherReport;
import it.chiarani.meteotrentinoapp.repositories.LocationRepository;
import it.chiarani.meteotrentinoapp.repositories.OpenWeatherDataRepository;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;

/**
 * Implementation of App Widget functionality.
 */
public class SmallMeteoWidget extends AppWidgetProvider {

    private static final String ACTION_SIMPLEAPPWIDGET = "ACTION_BROADCASTWIDGETSAMPLE";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.small_meteo_widget);


        Intent intent = new Intent(context, SmallMeteoWidget.class);
        intent.setAction(ACTION_SIMPLEAPPWIDGET);

        intent.putExtra("KEY_ID", appWidgetId);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        views.setOnClickPendingIntent(R.id.small_meteo_widget_btn_refresh, pendingIntent);

        new GetDataSmallMeteo(views, appWidgetId, appWidgetManager, context).execute();

        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (ACTION_SIMPLEAPPWIDGET.equals(intent.getAction())) {

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.small_meteo_widget);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            appWidgetManager.updateAppWidget(new ComponentName(context, SmallMeteoWidget.class), views);


            int widgetId = intent.getIntExtra("KEY_ID", -1);
            new GetDataSmallMeteo(views, widgetId, appWidgetManager, context).execute();
            Toast.makeText(context, "Aggiorno...", Toast.LENGTH_LONG).show();
        }
    }


    public static PendingIntent getPendingSelfIntent(Context context, String action, RemoteViews views, int appWidgetID, AppWidgetManager appWidgetManager) {
        Intent intent = new Intent(context, SmallMeteoWidget.class);
        intent.setAction(action);

        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}

class GetDataSmallMeteo extends AsyncTask<Void, Void, Void> implements API_weatherReport_response {

    private RemoteViews views;
    private int WidgetID;
    private AppWidgetManager WidgetManager;
    private Context app;

    public GetDataSmallMeteo(RemoteViews views, int appWidgetID, AppWidgetManager appWidgetManager, Context app){
        this.views = views;
        this.WidgetID = appWidgetID;
        this.WidgetManager = appWidgetManager;
        this.app = app;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        WeatherReportRepository r = new WeatherReportRepository(app);
        OpenWeatherDataRepository or = new OpenWeatherDataRepository(app);

        r.getAll().observeForever(e -> {
            if (e.size() > 1) {

                String loc = e.get(e.size() - 1).getPrevisione().getLocalita();
                LocationRepository repo = new LocationRepository(app);
                repo.getAll().observeForever( localityEntities -> {
                    LocationEntity tmp = new LocationEntity();
                    for (LocationEntity le : localityEntities)
                        if (le.getLoc().equals(loc)) {
                            tmp = le;
                            break;
                        }
                    new API_weatherReport(null, app, this::processFinish, loc, tmp.getLatitude(), tmp.getLongitude()).execute();
                });
            }
        });
    }

    @Override
    public void processFinish(String response) {

        if(response.equals("ok") || response.equals("ok1")) {
            WeatherReportRepository r = new WeatherReportRepository(app);
            OpenWeatherDataRepository or = new OpenWeatherDataRepository(app);

            r.getAll().observeForever(e -> {
                if (e.size() > 1) {

                    or.getAll().observeForever(oe -> {

                        WeatherForDay wfd = e.get(e.size()-1).getPrevisione().getGiorni().get(0);
                        views.setTextViewText(R.id.small_meteo_widget_txt_temperatura_min_max, wfd.gettMinGiorno() + "° / " + wfd.gettMaxGiorno() + "°");
                        views.setTextViewText(R.id.small_meteo_widget_txt_temperatura, oe.get(oe.size()-1).getActualTemperature()+ "°");
                        views.setTextViewText(R.id.small_meteo_widget_txt_position, e.get(e.size()-1).getPrevisione().getLocalita());
                        views.setTextViewText(R.id.small_meteo_widget_txt_descrizione, wfd.getDescIcona());

                        switch (WeatherIconDescriptor.getWeatherType(wfd.getIcona())){
                            case COPERTO:
                                views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_cloud_g);
                                break;
                            case COPERTO_CON_PIOGGIA:
                                views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_light_rain_g);
                                break;
                            case COPERTO_CON_PIOGGIA_ABBONDANTE :
                                views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_rain_g);
                                break;
                            case COPERTO_CON_PIOGGIA_E_NEVE:
                                views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_snow_rain_g);
                                break;
                            case NEVICATA:
                                views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_snow_g);
                                break;
                            case SOLE:
                                views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_sun_g);
                                break;
                            case SOLEGGIATO:
                                views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_sun_cloud_g);
                                break;
                            case SOLEGGIATO_CON_PIOGGIA:
                                views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_sun_cloud_rain_g);
                                break;
                            case SOLEGGIATO_CON_PIOGGIA_E_NEVE:
                                views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_sun_cloud_rain_snow_g);
                                break;
                            case TEMPORALE:
                                views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_thunderstorm_g);
                                break;
                            case UNDEFINED:
                                views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_sun_cloud_g);
                                break;
                        }

                        WidgetManager.updateAppWidget(WidgetID, views);

                    });
                }
            });
        }

    }
}


