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
import it.chiarani.meteotrentinoapp.database.AppDatabase;
import it.chiarani.meteotrentinoapp.database.dao.WeatherReportDao;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
import it.chiarani.meteotrentinoapp.helper.WeatherIconDescriptor;
import it.chiarani.meteotrentinoapp.models.WeatherForDay;
import it.chiarani.meteotrentinoapp.models.WeatherReport;
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

        new GetData(views, appWidgetId, appWidgetManager, context).execute();

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
            new GetData(views, widgetId, appWidgetManager, context).execute();
        }
    }


    public static PendingIntent getPendingSelfIntent(Context context, String action, RemoteViews views, int appWidgetID, AppWidgetManager appWidgetManager) {
        Intent intent = new Intent(context, SmallMeteoWidget.class);
        intent.setAction(action);

        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}

class GetData extends AsyncTask<Void, Void, Void> {

    private RemoteViews views;
    private int WidgetID;
    private AppWidgetManager WidgetManager;
    private Context app;

    public GetData(RemoteViews views, int appWidgetID, AppWidgetManager appWidgetManager, Context app){
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
        r.getAll().observeForever(e -> {
            if(e.size() > 1 ){
                String x = e.get(e.size()-1).getPrevisione().getLocalita();
                WeatherForDay wfd = e.get(e.size()-1).getPrevisione().getGiorni().get(0);
                views.setTextViewText(R.id.small_meteo_widget_txt_descrizione, wfd.getDescIcona());
                views.setTextViewText(R.id.small_meteo_widget_txt_position, x);
                views.setTextViewText(R.id.small_meteo_widget_txt_temperatura, wfd.gettMaxGiorno()+"°");
                views.setTextViewText(R.id.small_meteo_widget_txt_temperatura_min_max, wfd.gettMinGiorno() + "° | " + wfd.gettMaxGiorno()+"°");

                switch (WeatherIconDescriptor.getWeatherType(wfd.getIcona())) {
                    case COPERTO:
                        views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_cloud);
                        break;

                    case COPERTO_CON_PIOGGIA:
                        views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_light_rain);
                        break;

                    case COPERTO_CON_PIOGGIA_ABBONDANTE:
                        views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_rain);

                        break;

                    case COPERTO_CON_PIOGGIA_E_NEVE:
                        views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_snow_rain);
                        break;

                    case NEVICATA:
                        views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_snow);
                        break;

                    case SOLE:
                        views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_sun);
                        break;

                    case SOLEGGIATO:
                        views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_sun_cloud);
                        break;

                    case SOLEGGIATO_CON_PIOGGIA:
                        views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_sun_cloud_rain);
                        break;

                    case SOLEGGIATO_CON_PIOGGIA_E_NEVE:
                        views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_sun_cloud_rain_snow);
                        break;

                    case TEMPORALE:
                        views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_thunderstorm);
                        break;

                    case UNDEFINED:
                        views.setImageViewResource(R.id.small_meteo_widget_weather_img, R.drawable.ic_w_sun_cloud);
                        break;
                }

                WidgetManager.updateAppWidget(WidgetID, views);
                Toast.makeText(app, "Aggiornato.", Toast.LENGTH_LONG).show();

            }
        });


    }
}

