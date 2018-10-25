package it.chiarani.meteotrentinoapp.helper;

import java.util.ArrayList;
import java.util.List;

public class WebcamListCSV {
  public final static String webcamListCSV = ";" +
      "Seleziona una webcam;\n"+
      "TRENTO(TN) - Vista sul Monte Bondone;http://www.mykol.altervista.org/cam_1.jpg\n" +
      "POVO(TN) - Centro Povo - Piazza;http://www.famigliacooperativapovo.it/images/camera/image.jpg\n" +
      "TRENTO(TN) - Trento Centro - SS 45 bis - Km 153;http://ftp.vit.argentea.it/vit/images/cam51.jpg\n" +
      "Gardolo di Trento (TN) - Bondone;http://www.meteogardolo.it/meteo/webcam2.jpg\n" +
      "TRENTO(TN) - Vista su Trento nord e Paganella da Martignano;http://www.martignanometeo.it/webcam/cam.jpg\n" +
      "TRENTO(TN) - Trento, loc. Cadine SS 45 bis;http://ftp.vit.argentea.it/vit/images/cam56.jpg\n" +
      "Autostrada A22 - Trento (TN)- Piedicastello Sud direzione sud km 138;http://www.autobrennero.it/WebCamImg/km138.jpg\n" +
      "TRENTO(TN) - Trento, loc. Ravina Cavalcavia SS 12;http://ftp.vit.argentea.it/vit/images/cam49.jpg\n" +
      "TRENTO(TN) - Panorama della città da Radio Dolomiti;http://webcam.radiodolomiti.com/wcamtn.jpg\n" +
      "Gardolo di Trento(TN) -  sulla Paganella;http://www.meteo-system.com/stazioni/gardolo/webcam.jpg\n" +
      "TRENTO(TN) - Trento, loc. Ravina Cavalcavia SS 12 - Km 375,3;http://ftp.vit.argentea.it/vit/images/cam50.jpg\n" +
      "TRENTO(TN) - Trento, loc. Piedicastello SUD SS 12;http://ftp.vit.argentea.it/vit/images/cam55.jpg\n" +
      "TRENTO(TN) - Loc. Mattarello Sud SS 12 - Km 367,100;http://ftp.vit.argentea.it/vit/images/cam42.jpg\n" +
      "TRENTO(TN) - Loc. Piedicastello SUD SS 12 - Km 378,000;http://ftp.vit.argentea.it/vit/images/cam35.jpg\n" +
      "TRENTO(TN) - Loc. Trento Commerciale SS 12;http://ftp.vit.argentea.it/vit/images/cam54.jpg\n" +
      "TRENTO(TN) - Vista del Monte Bondone;http://www.meteovela.it/webcam_1.jpg\n" +
      "ALA -  Ala puntamento Sud;http://www.alameteo.it/webcam/current/ala.jpg\n" +
      "ANDALO - Campo scuola Rindole;http://webcam.paganella.net/paganella-webcam-05.jpg\n" +
      "ANDALO - Arrivo Paganella 2 - Partenza S.Antonio;http://webcam.paganella.net/paganella-webcam-07.jpg\n" +
      "ANDALO - Paganella. Localita Meritz;http://webcam.paganella.net/paganella-webcam-01.jpg\n" +
      "ANDALO - Pista Olimpionica 3;https://panodata.panomax.com/cams/1634/canfedin.jpg\n" +
      "ANDALO - Vista sulle Dolomiti del Brenta;https://panodata.panomax.com/cams/1634/dolomitidibrenta.jpg\n" +
      "ANDALO - Rifugio Meriz;http://webcam.paganella.net/paganella-webcam-117.jpg\n" +
      "ANDALO - Prati di Gaggia;http://www.paganella.net/sito/external_url.php?url=http://webcam.paganella.net/paganella-webcam-04.jpg\n" +
      "ANDALO - Partenza Cima Andalo;http://webcam.paganella.net/paganella-webcam-03.jpg\n" +
      "ANDALO - Rifugio la Roda;https://panodata.panomax.com/cams/1634/rifugiolaroda.jpg\n" +
      "ANDALO - Cima Paganella;https://panodata.panomax.com/cams/1634/arrivocima.jpg\n" +
      "ANDALO - Partrenza Salare;http://webcam.paganella.net/paganella-webcam-06.jpg\n" +
      "ANDALO - Rifugio Albi de Mez;https://panodata.panomax.com/cams/1634/albidemez.jpg\n" +
      "ANDALO - Località  Selletta;http://webcam.paganella.net/paganella-webcam-26.jpg\n" +
      "ANDALO - Vista sull'Hotel Serena;http://www.hotelserena.it/extension/design_interline/design/standard/images/webcam/piazzale.jpg\n" +
      "ANDALO - Pian Dosson;http://www.paganella.net/sito/external_url.php?url=http://webcam.paganella.net/paganella-webcam-02.jpg\n" +
      "ANDALO - Prati di Gaggia;http://webcam.paganella.net/paganella-webcam-04.jpg\n" +
      "ANDALO - Vista su Lago di Garda;https://panodata.panomax.com/cams/1634/lagodigarda.jpg\n" +
      "ANDALO - Campo scuola Laghet;http://webcam.paganella.net/paganella-webcam-10.jpg\n" +
      "ARCO;http://www.meteosystem.com/webcam/torbole/torbole.jpg\n" +
      "ARCO - Vista monte;http://meteoarco.it/public/webcam.php\n" +
      "AVIO -Stazione meteo polifunzionale del Trentino Alto Adige;http://www.smaniotto.eu/webcam/webcam.jpg\n" +
      "BASELGA DI PINE - Vista sull'abitato;http://88.36.180.26/SnapshotJPEG?Resolution=640x480&Quality=Standard&Count=1\n" +
      "BEDOLLO - Vista panoramica;http://www.bedollo.com/ispy.jpg\n" +
      "BESENELLO - capoluogo con vista sulle montagne;http://www.meteogardolo.it/meteo/besenello/webcam/webcam.php\n" +
      "BORGO VALSUGANA - Strada Statale 47;http://ftp.vit.argentea.it/vit/images/cam37.jpg\n" +
      "POLSA - Piste di Polsa San Valentino;http://polsa.tpwebcam.com/webcam/web2.jpg\n" +
      "PISTA POLSA - Pista Rosa del Sole;http://polsa.tpwebcam.com/webcam/web3.jpg\n" +
      "POLSA - Le piste dall hotel Bellavista;http://polsa.tpwebcam.com/webcam/campeggio.jpg\n" +
      "BRENTONICO - Vista sulla Val Lagarina, gli abitati di Mori e Rovereto;http://www.pizzeriapineta.com/webcam/cam1.jpg\n" +
      "CALCERANICA AL LAGO - Vista sul Lago di Caldonazzo dal Camping Punta Lago;http://www.campingpuntalago.com/webcam/puntalago.jpg\n" +
      "CALCERANICA AL LAGO - Vista sul Lago;http://www.meteotrentinoaltoadige.it/webcam/calceranica/webcam000M.jpg\n" +
      "CALDONAZZO - Panoramica sul lago di Caldonazzo dall'Hotel lido Caldonazzo;http://www.hotel-lidocaldonazzo.it/lidocaldonazzo.jpg\n" +
      "CAMPITELLO DI FASSA - Vista su Campitello dall'hotel Ramon;http://www.fassaweb.net/webcam/webcamHotelRamon.jpg\n" +
      "CAMPITELLO DI FASSA - Skilift Col de Lin da Campitello di Fassa (1.448 m)offerto da Hotel Villa Campitello;http://www.campitellodifassahotel.it/webcam/vga.jpg\n" +
      "CAMPITELLO DI FASSA - Col Rodella;http://srv2.realcam.it/live/pub/12-2.jpg\n" +
      "PIAN DEL FIACCONI - Vista spettacolare sulla Marmolada;http://www.rifuginrete.com/rifugio/piandeifiacconi/webcam/cam.jpg\n" +
      "CANAZEI - Vista panorama;http://www.dolomitiwebcam.com/vernel/cif.jpg\n" +
      "ALBA DI CANAZEI - Ciampac - Colac;https://panodata.panomax.com/cams/1674/colac.jpg\n" +
      "CANAZEI - Ski Area Ciampac;https://panodata.panomax.com/cams/1674/skiareaciampac.jpg\n" +
      "CANAZEI - Ciampal Roseal e Sasso di Rocca;https://panodata.panomax.com/cams/1674/roseal.jpg\n" +
      "CANAZEI - Ski area Belvedere;http://www.dolomitiwebcam.com/belvedere/mega.jpg\n" +
      "ALBA DI CANAZEI - Sella Brunech;https://panodata.panomax.com/cams/1674/sellabrunech.jpg\n" +
      "CARISOLO - Vista panoramica;http://www.dadosoftware.com/webcam.jpg\n" +
      "PASSO BROCON - Passo Brocon mt.1616 vista sulle piste da sci Monte Agaro dall'albergo Passo Brocon;http://www.brocon.it/webcam/webcam.jpg\n" +
      "CASTELLO MOLINA DI FIEMME - Castello molina di fiemme verso Egna - Ora. Bivio statale partenza Cermis;http://ftp.vit.argentea.it/vit/images/cam10.jpg\n" +
      "CAVALESE - Alpe Cermis;http://srv2.realcam.it/live/pub/22-1.jpg\n" +
      "CAVALESE - Vista sul centro di Cavalese;http://www.meteocavalese.it/cavalese.jpg\n" +
      "CAVALESE - Pista Olimpia 1;http://srv2.realcam.it/live/pub/22-2.jpg\n" +
      "CAVALESE - Webcam panoramica;http://srv3.realcam.it/22/immagini_360/360_490ok.jpg\n" +
      "CAVALESE - Vista sulle piste. Sullo sfondo l'abitato di Cavalese;http://srv2.realcam.it/live/pub/22-3.jpg\n" +
      "CAVALESE - Seggiovia Lagorai;http://srv2.realcam.it/live/pub/22-4.jpg\n" +
      "CAVALESE - Rifugio;http://srv2.realcam.it/live/pub/22-5.jpg\n" +
      "CAVALESE - Pista Lagorai;http://srv2.realcam.it/live/pub/22-1.jpg\n" +
      "CAVEDAGO - Vista a Sud Ovest;http://www.tedeschi.it/cavedago/snapshot.jpg\n" +
      "CIS - Cis, loc. Mostizzolo SS 43 - Km 189,200;http://ftp.vit.argentea.it/vit/images/cam32.jpg\n" +
      "CLES - Webcam in diretta dalla sede di Radio Anaunia rivolta verso il Monte di Cles;http://www.radioanaunia.it/webcam/cam_1.jpg\n" +
      "TAVON - Vista sulle Dolomiti del Brenta e Val di Non dal paese di Tavon;http://www.pinetahotels.it/webcam/Image.aspx\n" +
      "DRO - Pietramurata di Dro;https://lh6.googleusercontent.com/-vt7LsuP1_es/T8sfwehFbhI/AAAAAAAAAUw/YfGxIf8UUmc/s400/livefeed.jpg\n" +
      "FAI DELLA PAGANELLA - Vista sulla Cima Tosa;http://www.protezionecivile.tn.it/statico/webcam/paganella/CimaTosa.jpg\n" +
      "FAI DELLA PAGANELLA - Vista La Roda;http://www.protezionecivile.tn.it/statico/webcam/paganella/LaRoda.jpg\n" +
      "FAI DELLA PAGANELLA - Splendida veduta sulle Dolomiti del Brenta;http://www.protezionecivile.tn.it/statico/webcam/paganella/Brenta.jpg\n" +
      "FAI DELLA PAGANELLA - Vista sulla Val di Non;http://www.protezionecivile.tn.it/statico/webcam/paganella/ValdiNon.jpg\n" +
      "FAI DELLA PAGANELLA - Cista sulla cima (Cimirlo);http://www.protezionecivile.tn.it/statico/webcam/paganella/Cimirlo.jpg\n" +
      "FIERA DI PRIMIERO - Vista su Fiera di Primiero, Transacqua, Siror;http://www.arifeltre.it/Cam3/webcam.jpg\n" +
      "FOLGARIA - Fondo Piccolo;http://srv2.realcam.it/live/pub/7-8.jpg\n" +
      "FOLGARIA - Monte Coston;http://www.belledolomiti.it/res/webcam/640x480/fiorentini.jpg\n" +
      "FOLGARIA - Monte Pioverna;http://srv2.realcam.it/live/pub/7-7.jpg\n" +
      "FOLGARIA - Stazione valle Cima Spill;http://srv2.realcam.it/live/pub/7-3.jpg\n" +
      "FOLGARIA - Arrivo Salizzona;http://srv2.realcam.it/live/pub/7-1.jpg\n" +
      "FOLGARIA - Trugalait;http://srv2.realcam.it/live/pub/7-4.jpg\n" +
      "FOLGARIA - Agonistica Martinella Nord;http://srv2.realcam.it/live/pub/7-5.jpg\n" +
      "FOLGARIA - Monte Cornetto;http://srv2.realcam.it/live/pub/7-6.jpg\n" +
      "FOLGARIA - Arrivo Martinella nord;http://srv2.realcam.it/live/pub/7-2.jpg\n" +
      "GRUMES - Visuale da Grumes a Sover;http://www.meteosover.it/fwink_webcam.jpg\n" +
      "IMER - Loc. Totoga;http://ftp.vit.argentea.it/vit/images/cam44.jpg\n" +
      "IMER - Vista panoramica;http://www.radioprimiero.com/webcam/webcam.jpg\n" +
      "LAVARONE - Partenza impianti Bertoldi;http://www.webcamlavarone.it/images/bertoldi.jpg\n" +
      "LAVARONE - Webcam sul Lago di Lavarone;http://webcamlavarone.it/images/lago.jpg\n" +
      "LAVARONE - Malga Laghetto;http://www.malgalaghetto.com/webcam/index_htm_files/webcam.jpg\n" +
      "LAVIS - Lavis, loc. Lavis Nord SS 12 - Km 386,000;http://ftp.vit.argentea.it/vit/images/cam28.jpg\n" +
      "LAVIS - Autostrada del Brennero, Paganella Ovest direzione sud km 129;http://www.autobrennero.it/WebCamImg/km129.jpg\n" +
      "COMANO TERME - Agritur Casa Riga;http://www.panoramawebcam.it/live/comano-casariga/800.php\n" +
      "MADONNA DI CAMPIGLIO - Vista sulla Catena del Lagorai dall'Albergo Panorama;" ;

  public static List<String> getWebcamNames() {
    String[] arr = webcamListCSV.split("\n");

    List<String> tmp = new ArrayList<>();
    for (String anArr : arr) {
      tmp.add(anArr.split(";")[0]);
    }
    return tmp;
  }

  public static String getWebcamUrl(int pos) {
    String[] arr = webcamListCSV.split("\n");

    int i = 1;
    for (String anArr : arr) {
      if(i == pos) {
        String[] x =  anArr.split(";");
        return x[1];
      }
      i++;
    }

    return "";
  }
}
