package it.chiarani.meteotrentinoapp.api;

        import android.os.AsyncTask;
        import android.util.Log;

        import com.thoughtworks.xstream.XStream;

        import java.io.BufferedReader;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;

        import it.chiarani.meteotrentinoapp.database.entity.WeatherForDayEntity;
        import it.chiarani.meteotrentinoapp.database.entity.WeatherForWeekEntity;
        import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
        import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;
        import it.chiarani.meteotrentinoapp.xml_parser.XmlDatiOggi;
        import it.chiarani.meteotrentinoapp.xml_parser.XmlPrecipitazione;
        import it.chiarani.meteotrentinoapp.xml_parser.XmlPrecipitazioni;
        import it.chiarani.meteotrentinoapp.xml_parser.XmlTemperaturaAria;
        import it.chiarani.meteotrentinoapp.xml_parser.XmlTemperature;

public class API_bacini extends AsyncTask<Void, Void, Void> {

    // #region private fields
    private final static String             CLASS_TAG    = "API_bacini";
    private final int                       HTTP_TIMEOUT = 15000;
    private API_bacini_response delegate;
    private String API_URL;
    String data = "";
    // #endregion

    /**
     * Main constructor
     * @param delegate callback delegate
     */
    public API_bacini(API_bacini_response delegate, String endpoint) {
        this.delegate = delegate;
        this.API_URL  = endpoint;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpURLConnection connection;
        BufferedReader reader;
        try {
                URL url = new URL(API_URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                connection.setConnectTimeout(HTTP_TIMEOUT);
                connection.setReadTimeout(HTTP_TIMEOUT);

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                data = buffer.toString();


        } catch (Exception e) {
            Log.e(CLASS_TAG, e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        delegate.processFinish(data);
    }

}
