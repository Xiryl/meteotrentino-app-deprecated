package it.chiarani.meteotrentinoapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.RemoteViews;
import android.widget.Toast;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.helper.WeatherIconDescriptor;
import it.chiarani.meteotrentinoapp.models.WeatherForDay;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;

/**
 * Implementation of App Widget functionality.
 */
public class LongMeteoWidget extends AppWidgetProvider {

    private static final String ACTION_SIMPLEAPPWIDGET = "ACTION_BROADCASTWIDGETLONG";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.long_meteo_widget);


        Intent intent = new Intent(context, LongMeteoWidget.class);
        intent.setAction(ACTION_SIMPLEAPPWIDGET);


        intent.putExtra("KEY_ID", appWidgetId);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //views.setOnClickPendingIntent(R.id.lon, pendingIntent);

        new GetDataLongMeteo(views, appWidgetId, appWidgetManager, context).execute();

      //  appWidgetManager.updateAppWidget(appWidgetId, views);

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

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.long_meteo_widget);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            appWidgetManager.updateAppWidget(new ComponentName(context, LongMeteoWidget.class), views);


            int widgetId = intent.getIntExtra("KEY_ID", -1);
            new GetDataLongMeteo(views, widgetId, appWidgetManager, context).execute();
            Toast.makeText(context, "Aggiorno...", Toast.LENGTH_LONG).show();
        }
    }


    public static PendingIntent getPendingSelfIntent(Context context, String action, RemoteViews views, int appWidgetID, AppWidgetManager appWidgetManager) {
        Intent intent = new Intent(context, SmallMeteoWidget.class);
        intent.setAction(action);

        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}

class GetDataLongMeteo extends AsyncTask<Void, Void, Void> {

    private RemoteViews views;
    private int WidgetID;
    private AppWidgetManager WidgetManager;
    private Context app;

    public GetDataLongMeteo(RemoteViews views, int appWidgetID, AppWidgetManager appWidgetManager, Context app){
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


                WeatherForDay wtf = e.get(e.size()-1).getPrevisione().getGiorni().get(0);
                switch (WeatherIconDescriptor.getWeatherType(wtf.getIcona())){
                    case COPERTO:
                        views.setImageViewResource(R.id.long_meteo_widget_weather_img, R.drawable.ic_w_cloud_b);
                        break;
                    case COPERTO_CON_PIOGGIA:
                        views.setImageViewResource(R.id.long_meteo_widget_weather_img, R.drawable.ic_w_light_rain_b);
                        break;
                    case COPERTO_CON_PIOGGIA_ABBONDANTE :
                        views.setImageViewResource(R.id.long_meteo_widget_weather_img, R.drawable.ic_w_rain_b);
                        break;
                    case COPERTO_CON_PIOGGIA_E_NEVE:
                        views.setImageViewResource(R.id.long_meteo_widget_weather_img, R.drawable.ic_w_snow_rain_b);
                        break;
                    case NEVICATA:
                        views.setImageViewResource(R.id.long_meteo_widget_weather_img, R.drawable.ic_w_snow_b);
                        break;
                    case SOLE:
                        views.setImageViewResource(R.id.long_meteo_widget_weather_img, R.drawable.ic_w_sun_b);
                        break;
                    case SOLEGGIATO:
                        views.setImageViewResource(R.id.long_meteo_widget_weather_img, R.drawable.ic_w_sun_cloud_b);
                        break;
                    case SOLEGGIATO_CON_PIOGGIA:
                        views.setImageViewResource(R.id.long_meteo_widget_weather_img, R.drawable.ic_w_sun_cloud_rain_b);
                        break;
                    case SOLEGGIATO_CON_PIOGGIA_E_NEVE:
                        views.setImageViewResource(R.id.long_meteo_widget_weather_img, R.drawable.ic_w_sun_cloud_rain_snow_b);
                        break;
                    case TEMPORALE:
                        views.setImageViewResource(R.id.long_meteo_widget_weather_img, R.drawable.ic_w_thunderstorm_b);
                        break;
                    case UNDEFINED:
                        views.setImageViewResource(R.id.long_meteo_widget_weather_img, R.drawable.ic_w_sun_cloud_b);
                        break;
                }

                WidgetManager.updateAppWidget(WidgetID, views);

            }
        });


    }
}


