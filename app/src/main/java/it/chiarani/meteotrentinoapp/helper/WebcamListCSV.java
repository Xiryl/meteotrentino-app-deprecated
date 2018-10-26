package it.chiarani.meteotrentinoapp.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WebcamListCSV {
  public final static String webcamListCSV =
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
      "ARCO - vista lago;http://www.meteosystem.com/webcam/torbole/torbole.jpg\n" +
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
      "MADONNA DI CAMPIGLIO - Vista sulla Catena del Lagorai dall'Albergo Panorama;\n" +
      "MEZZOCORONA - Vista sulle vigne circostanti;http://meteorotaliana.altervista.org/webcam/current.jpg\n" +
      "MEZZOLOMBARDO - Rocchetta;http://ftp.vit.argentea.it/vit/images/cam2.jpg\n" +
      "MEZZOLOMBARDO - Loc. Mezzolombardo Campo sportivo;http://ftp.vit.argentea.it/vit/images/cam48.jpg\n" +
      "MEZZOLOMBARDO - Statale Mezzolombardo Sud;http://ftp.vit.argentea.it/vit/images/cam40.jpg\n" +
      "MEZZOLOMBARDO - Loc. Mezzolombardo Nord\n;http://ftp.vit.argentea.it/vit/images/cam41.jpg\n" +
      "MOENA - Panorama sul Lago di Soraga;http://www.dolomitiwebcam.com/lago/mega.jpg\n" +
      "ALPE LUISA - Seggiovia campo-laste;http://srv2.realcam.it/live/pub/10-7.jpg\n" +
      "ALPE LUISA - Seggiovia Piava;http://srv2.realcam.it/live/pub/10-2.jpg\n" +
      "ALPE LUISA - Seggiovia campo Cune;http://srv2.realcam.it/live/pub/10-5.jpg\n" +
      "MOENA - Webcam passo S. Pellegrino;http://meteocam.it/live/pub/meteocam1_3.jpg\n" +
      "MOENA - Panorama verso Moena, Cima Dodici e Monzoni;http://www.fassaturismo.com/webcam/degiampietro/degiampietro_1000.jpg\n" +
      "MOENA - Comprensorio Trevalli. Sciovia Chiesetta;http://www.meteocam.it/live/pub/meteocam1_3.jpg\n" +
      "MOENA - dall'hotel Patrizia;http://www.fassaweb.net/webcam/ht003401s.jpg\n" +
      "MOENA - Pista le Cune 2 e vista panoramica;http://srv2.realcam.it/live/pub/10-4.jpg\n" +
      "MOENA - Passo S.Pellegrino: rifugio Col Margherita;http://srv2.realcam.it/live/pub/19-2.jpg\n" +
      "MOENA - Comprensorio Trevalli - Sciovia Chiesetta;http://www.meteocam.it/live/pub/meteocam1_5.jpg\n" +
      "MOENA - Le Cune;http://srv2.realcam.it/live/pub/10-1.jpg\n" +
      "MOENA - Comprensorio Trevalli;http://www.meteocam.it/live/pub/meteocam1_4.jpg\n" +
      "MOENA - Pista Intermedia;http://srv2.realcam.it/live/pub/10-3.jpg\n" +
      "MOENA - Moena nord;http://ftp.vit.argentea.it/vit/images/cam6.jpg\n" +
      "MOENA - Pista Piavac;http://srv2.realcam.it/live/pub/10-6.jpg\n" +
      "MOENA - Arrivo Col Margherita alt. 2513M;http://srv2.realcam.it/live/pub/19-3.jpg\n" +
      "MOENA - Passo San Pellegrino. Rifugio Cima Uomo;http://srv2.realcam.it/live/pub/19-1.jpg\n" +
      "MOLVENO - Playa Lago Molveno;http://webcam.molveno.it/webcam/playa.jpg\n" +
      "MOLVENO - Area sosta Camper Molveno;http://webcam.molveno.it/webcam/current_sosta.jpg\n" +
      "MOLVENO - Vista sul Lago di Molveno;http://molveno-webcam.hotelnevada.it/nevada_webcam.jpg\n" +
      "MOLVENO - Loc. Pradel, Terrazza allarrivo della Cabinovia La Panoramica;http://webcam.molveno.it/webcam/pradel.jpg\n" +
      "MOLVENO - Vista del lago di Molveno;http://www.agriturismomolveno.com/images/stories/webcam/webcam.jpg\n" +
      "MOLVENO - Webcam Lago di Molveno dalla loc. Pradel;http://webcam.molveno.it/webcam/palo7.jpg\n" +
      "MOLVENO - Panorama su Molveno e il lago;http://webcam.molveno.it/webcam/paese.jpg\n" +
      "MONTE BONDONE - Webcam Hotel Montana;http://213.21.161.66:1024/jpeg?id=0&uniq=1492416471610\n" +
      "TORBOLE - Vista sul Lago di Garda dal ristorante alla Terrazza;http://www.gardameteo.com/webcam/torbole.jpg\n" +
      "TORBOLE - Vista del lago di Garda dal SurfCenter Lido Blu;http://www.surflb.com/uploads/webcam/webcam.jpg\n" +
      "TORBOLE - Vista sul Lago di Garda dal Circolo Vela Torbole;http://www.meteosystem.com/webcam/circolovelatorbole/torbole.jpg\n" +
      "TORBOLE -Vista dall'Hotel Villa Clara;http://www.villaclara.it/images/webcam/webcam.php\n" +
      "PARDEGNONE - Vista;http://meteoweb.valledeilaghi.it/meteopadergnone/padergnone.jpg\n" +
      "PANCHIA' - Vista sulla Catena del Lagorai dall'Albergo Panorama;http://www.skylinewebcams.com/ext/images/cam/469.jpg\n" +
      "PEJO - Monte Vioz;http://www.protezionecivile.tn.it/statico/webcam/careser/Vioz.jpg\n" +
      "PEJO - Vista su Peio paese;http://content.meteotrentino.it/dati-meteo/webcam/pejo/pejo_14_.jpg\n" +
      "PEJO - Impianti Pejo 3000;http://skipejo.altervista.org/panorama2.jpg\n" +
      "PEJO - Vista sul palo misura altezza neve;http://www.protezionecivile.tn.it/statico/webcam/careser/Paloneve.jpg\n" +
      "PEJO - Coma del Monte Cevedale 3.769M;http://www.protezionecivile.tn.it/statico/webcam/careser/CimaCevedale.jpg\n" +
      "PEJO - Pejo - Tarlenta Rifugio Scoiattolo - mt. 2000;http://skipejo.altervista.org/panorama1.jpg\n" +
      "PANAROTTA - Vista sulla Paganella e Dolomiti del Brenta;http://www.protezionecivile.tn.it/statico/webcam/panarotta/Paganella.jpg\n" +
      "PANAROTTA - Vista Fravort;http://www.protezionecivile.tn.it/statico/webcam/panarotta/Fravort.jpg\n" +
      "PANAROTTA - Vista su Levico Terme;http://www.protezionecivile.tn.it/statico/webcam/panarotta/Levico.jpg\n" +
      "PERGINE - Loc. San Cristoforo SS 47 - Km 117,200;http://ftp.vit.argentea.it/vit/images/cam39.jpg\n" +
      "PANAROTTA - Vista cima Panarotta;http://www.protezionecivile.tn.it/statico/webcam/panarotta/Panarotta.jpg\n" +
      "PANAROTTA - Vista panoramica sulla Valle dei Mocheni;http://www.protezionecivile.tn.it/statico/webcam/panarotta/ValdeiMocheni.jpg\n" +
      "PINZOLO - Doss del Sabion. Tuolt;http://www.panoramawebcam.it/live/pinzolo-tulot/800.php?.jpg\n" +
      "POZZA DI FASSA - Camping Vidor;http://www.campingvidor.it/webcam/webcam2/cam2.jpg\n" +
      "POZZA DI FASSA - Panorama sul paese e la vallata;http://www.fassaweb.net/webcam/ht000104.jpg\n" +
      "POZZA DI FASSA - Pala del Geiget;http://srv2.realcam.it/live/pub/24-3.jpg\n" +
      "PERA DI FASSA - Panorama sul Larsec(2.778 m) Catinaccio da Pera;http://www.dolomitiwebcam.com/larsec/mega.jpg\n" +
      "POZZA DI FASSA - Vista panoramica;http://www.campingvidor.it/webcam/webcam4/cam4.jpg\n" +
      "PREDAZZO - Pista Agnello;http://srv2.realcam.it/live/pub/9-9.jpg\n" +
      "PREDAZZO - Campo scuola;https://www.realcam4k.it/live/pub/30-1-1280.jpg\n" +
      "PREDAZZO - Pala Santa;http://srv2.realcam.it/live/pub/9-2.jpg\n" +
      "PREDAZZO - Catena del Lagorai;https://www.realcam4k.it/live/pub/30-5-1280.jpg\n" +
      "PREDAZZO - Cima Cavignon. Vista dal rifugio Torre di Pisa;http://www.predameteo.it/ace/current.jpg\n" +
      "PREDAZZO - Passo Feudo;http://srv2.realcam.it/live/pub/9-1.jpg\n" +
      "PREDAZZO - Pista Castelir;https://www.realcam4k.it/live/pub/30-2-1280.jpg\n" +
      "PREDAZZO - Panorama sull'abitato di Predazzo;http://www.meteopredazzo.it/predazzo.jpg\n" +
      "PREDAZZO - Panorama piste;http://srv2.realcam.it/live/pub/9-10.jpg\n" +
      "PREDAZZO - Rifugio Latemar;http://srv2.realcam.it/live/pub/9-8.jpg\n" +
      "PREDAZZO - Rifugi Zisch e Genischer;http://srv2.realcam.it/live/pub/9-6.jpg\n" +
      "PREDAZZO - Pista Castelir;https://www.realcam4k.it/live/pub/30-2-1280.jpg\n" +
      "PREDAZZO - Seggiovia Monte Agnello;http://srv2.realcam.it/live/pub/9-3.jpg\n" +
      "BELLAMONTE - Pista Castelir;https://www.realcam4k.it/live/pub/30-2-1280.jpg\n" +
      "PREDAZZO - Predazzo: vista verso nord sul Monte Feudo;http://www.predameteo.it/predazzo.jpg\n" +
      "BELLAMONTE - Pale di San Martino;https://www.realcam4k.it/live/pub/30-4-1280.jpg\n" +
      "BELLAMONTE - Pista Castelir;https://www.realcam4k.it/live/pub/30-2-1280.jpg\n" +
      "BELLAMONTE - Rifugio Passo Feudo;http://srv2.realcam.it/live/pub/9-7.jpg\n" +
      "PREDAZZO - Le piste del versante di Pampeago;http://srv2.realcam.it/live/pub/9-4.jpg\n" +
      "PREDAZZO - Pista Castelir;https://www.realcam4k.it/live/pub/30-2-1280.jpg\n" +
      "RABBI - Località  San Bernardo;http://webcam.g2k.it/?webcam=rabbi\n" +
      "RIVA DEL GARDA - Località  Ponale;http://www.awrivameteo.it/webcam/cam.jpg\n" +
      "RIVA DEL GARDA - Surf sul Lago;http://www.windinfo.eu/fileadmin/user_upload/webcam_upload/pier/webcam/640_zoom_pierwindsurf.jpg\n" +
      "RIVA DEL GARDA - Porto San Nicolò;http://webcam.g2k.it/default.aspx?webcam=rcristine2\n" +
      "RIVA DEL GARDA - Porto di San Nicolo;http://www.awriva.com/webcam/cam.jpg\n" +
      "RIVA DEL GARDA - Vista verso il Lago;http://www.awriva.com/webcam/cam.jpg\n" +
      "RIVA DEL GARDA - Il Lago di Garda;http://www.windinfo.eu/fileadmin/user_upload/webcam_upload/pier/webcam/640_wide_pierwindsurf.jpg\n" +
      "VARONE - Vista panoramica sull abitato;http://www.garda-meteo.com/matteo/webcam/varone/cam.jpg\n" +
      "RIVA DEL GARDA - Località  Porfina;http://www.garda-meteo.com/matteo/webcam/varone/cam2.jpg\n" +
      "RIVA DEL GARDA - Vista su Varone;http://www.garda-meteo.com/matteo/webcam/varone/cam2.jpg\n" +
      "RIVA DEL GARDA - Rive est;http://www.garda-meteo.com/matteo/webcam/gabry/cam.jpg\n" +
      "BORDALA RONZO - Vista Panoramica;http://www.baitabordala.com/cam1/foto1jpg.jpg\n" +
      "BORDALA RONZO - Snow Park;http://www.baitabordala.com/cam2/foto2jpg.jpg\n" +
      "ROVERETO - Vista panoramica sulla vallata di Rovereto;http://www.meteorovereto.it/webcam/cam.jpg\n" +
      "ROVERETO - Splendida vista dal Castello di Rovereto;http://www.meteotrentinoaltoadige.it/webcam/castello/webcam.jpg\n" +
      "ROVERETO - panoramica;http://www.meteotrentinoaltoadige.it/webcam/maso_carpene/webcam.jpg\n" +
      "RUMO - Web cam dall'Albergo Cavallino Bianco di Rumo;http://www.cavallinobiancorumo.it/WebCam/Image.aspx\n" +
      "SAN LORENZO IN BANALE - Lago di Nembia;http://www.altosarca.it/webcam/webcamNEMBIA_PE.jpg\n" +
      "SAN MARTINO DI CASTROZZA - Cima Tognola;http://srv2.realcam.it/live/pub/8-6.jpg\n" +
      "SAN MARTINO DI CASTROZZA - Le pale di san Martino;http://srv2.realcam.it/live/pub/8-1.jpg\n" +
      "SAN MARTINO DI CASTROZZA - Tognola pista uno;http://srv2.realcam.it/live/pub/8-4.jpg\n" +
      "SAN MARTINO DI CASTROZZA - Alpe Tognola. Panorama 360;http://srv2.realcam.it/live/pub/8_360ok.jpg\n" +
      "SAN MARTINO DI CASTROZZA - Impianti di risalita CES;http://www.impiantices.it/webcam/webcam.jpg\n" +
      "SAN MARTINO DI CASTROZZA - Punta Rolle;http://srv2.realcam.it/live/pub/8-9.jpg\n" +
      "SAN MARTINO DI CASTROZZA - Ces - Malga Valcigolera;http://srv2.realcam.it/live/pub/8-7.jpg\n" +
      "SAN MARTINO DI CASTROZZA - Tognola Snowpark;http://srv2.realcam.it/live/pub/8-3.jpg\n" +
      "SAN MARTINO DI CASTROZZA - Tognola Rododendro;http://srv2.realcam.it/live/pub/8-2.jpg\n" +
      "SAN MARTINO DI CASTROZZA - Malga Tognola;http://srv2.realcam.it/live/pub/8-5.jpg\n" +
      "SAN MARTINO DI CASTROZZA - Punta Ces;http://srv2.realcam.it/live/pub/8-10.jpg\n" +
      "SAN MARTINO DI CASTROZZA - Ski area Col Verde;http://srv2.realcam.it/live/pub/8-8.jpg\n" +
      "CIMA PENGAL - Panorama su Appiano e Bolzano;http://static.ras.bz.it/web/uploads/webcams/pen/cam1/current.jpg\n" +
      "PASSO ROLLE - Panoramica a 360°;http://srv2.realcam.it/live/pub/21_360ok.jpg\n" +
      "PASSO ROLLE - Il Castellazzo;http://srv2.realcam.it/live/pub/21-2.jpg\n" +
      "PASSO ROLLE - Veduta sul Cimon della Pala;http://srv2.realcam.it/live/pub/21-1.jpg\n" +
      "PASSO ROLLE - Vista sulle Pale di San Martino;http://srv2.realcam.it/live/pub/21-3.jpg\n" +
      "PASSO ROLLE - Il Colbricon;http://srv2.realcam.it/live/pub/21-5.jpg\n" +
      "SOGARA - Pese;http://www.fassaturismo.com/webcam/miravalle/miravalle_1000.jpg\n" +
      "TAIO - loc Mollaro;http://ftp.vit.argentea.it/vit/images/cam30.jpg\n" +
      "TERLAGO - vista su paganella;http://meteoweb.valledeilaghi.it/meteolaghilamar/laghilamar.jpg\n" +
      "VAL DI SOLE - Panorama della vallata;http://www.webdesigner-europe.biz/Webcam/webcam1280x960/terzolas.jpg\n" +
      "TIONE DI TRENTO - Località Saone. Vista sull'abitato;http://www.meteosaone.giudicarie.com/webcam/webcam.php\n" +
      "TIONE DI TRENTO - Rifugio Trivena: WebCam & Animazione Storica Oraria;http://www.trivena.com/s/img/emotionheader.JPG\n" +
      "TORNADICO - Stupenda vista del Gruppo Pale di San Martino - Dolomiti;http://www.castelpietra.it/webcam/webcam.jpg\n" +
      "VARENA - Vista della valle di Fiemme;http://www.meteovaldifiemme.it/varena_image/current.jpg\n" +
      "VERMIGLIO -  Presanella vista dall'Hotel Chalet al Foss;http://www.webdesigner-europe.biz/Webcam/webcam1280x960/presanella.jpg\n" +
      "VEZZANO - Valle dei Laghi - Margone;http://meteoweb.valledeilaghi.it/meteomargone/margone5.jpg\n" +
      "VEZZANO - Vista verso est;http://meteoweb.valledeilaghi.it/meteomargone/margone4.jpg\n" +
      "VIGNOLA FAESINA - Vista panormaica su Pergine da Falesina;http://www.gallianetwork.it/webcamimages/falesina/last.jpg\n" +
      "VIGOLO DI FASSA - Panorama sulla Val di Fassa da Larcionè;http://www.dolomitiwebcam.com/valdifassa/mega.jpg\n" +
      "VIGOLO DI FASSA - Seggiovia campo scuola;http://srv2.realcam.it/live/pub/11-4.jpg\n" +
      "VIGOLO DI FASSA - Panorama sul Sassolungo alt. 2114 mt;http://www.dolomitiwebcam.com/fassa/mega.jpg\n" +
      "VIGOLO DI FASSA - Catinaccio Rosengarten;http://srv2.realcam.it/live/pub/11-2.jpg\n" +
      "VIGOLO DI FASSA - RIFUGIO;http://www.rifuginrete.com/rifugio/rodadivael/webcam2/cam.jpg\n" +
      "VIGOLO DI FASSA - Panorama su Cima Dodici alt. 2446 mt e da Larcionè alt. 1400 mt;http://www.dolomitiwebcam.com/cimadodici3/mega.jpg\n" +
      "VIGOLO DI FASSA - Vael;http://srv2.realcam.it/live/pub/11-5.jpg\n" +
      "VIGOLO DI FASSA - Vista panoramica dal Piccolo hotel;http://www.dolomitiwebcam.com/piccolo/cif.jpg?c=1.6840784612902422\n" +
      "VIGOLO DI FASSA - Baby park;http://srv2.realcam.it/live/pub/11-1.jpg\n" +
      "VIGOLO DI FASSA - Seggiovia Pramartin;http://srv2.realcam.it/live/pub/11-3.jpg\n" +
      "VIGOLO DI FASSA - Conca del Gardeccia;http://srv2.realcam.it/live/pub/11-6.jpg\n" +
      "VIGOLO VATTARO - PANORAMA;http://meteovigolo.no-ip.org:1025/video.mjpg";

  public static List<String> getWebcamNames() {
    String[] arr = webcamListCSV.split("\n");
    Arrays.sort(arr);
    arr[0] = "Seleziona una Webcam;https://static.thenounproject.com/png/3624-200.png";

    List<String> tmp = new ArrayList<>();
    for (String anArr : arr) {
      tmp.add(anArr.split(";")[0]);
    }
    return tmp;
  }

  public static String getWebcamUrl(int pos) {
    String[] arr = webcamListCSV.split("\n");
    Arrays.sort(arr);
    arr[0] = "Seleziona una Webcam;https://static.thenounproject.com/png/3624-200.png";

    int i = 0;
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
