## Meteo Trentino - App

[![forthebadge](https://forthebadge.com/images/badges/built-for-android.svg)](https://forthebadge.com)

*applicazione meteo per le previsioni metereologiche in Trentino*

 <img src="https://lh3.googleusercontent.com/Epk79x0nC0k4wBlcCDC8Vghzu4XV-9OrJGH6Gd3J6rlw1EOdovB8kmKcB7fODzsH66s=s360" height="75px"> <a href='https://play.google.com/store/apps/details?id=it.chiarani.meteotrentinoapp'><img alt='Disponibile su Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/it_badge_web_generic.png' height='70px' /></a> 

Meteo trentino è un'applicazione meteo che utilizza gli opendata della Provincia Autonoma di Trento.

Scaricala dal [Play Store](https://play.google.com/store/apps/details?id=it.chiarani.meteotrentinoapp).



### Screen
| | | |
| --- | --- | --- |
|<img src="https://github.com/Xiryl/MeteoTrentino-App/blob/master/UI/g-play/T2.png" height="300px">| <img src="https://github.com/Xiryl/MeteoTrentino-App/blob/master/UI/g-play/T1.png" height="300px">| <img src="https://github.com/Xiryl/MeteoTrentino-App/blob/master/UI/g-play/T3.png" height="300px"> |

### FAQ
> D: La notifica di ogni giorno mattutina mi da fastidio, come la tolgo?

E' possibile disattivarle e gestire le notifiche/preferenze dell'applicazione dalle impostazioni (menù laterale).

> D: L'applicazione è bloccata sull'animazione iniziale, perchè?

Può capitare che la connessione dati non sia sufficiente per scaricare i dati meteo. Prova a richiudere l'applicazione e riaprirla.

> D: La temperatura attuale è diversa da quella rilevata dalla stazione metereologica della stessa località e/o temperatura attuale è maggiore/minore della massima/minima, perchè?

La temperatura attuale presente nella homepage è ricavata da un servizio esterno (openweathermap) poichè la provincia non rilascia alcun dato per la temperatura attuale, ma bensì solo la massima e la minima.Viene utilizzato un servizio esterno poichè non tutte le località hanno una stazione metereologica da cui ricavare tutti i dati. Il servizio openweathermap offre una stima della temperatura con la versione grautita: essendo l'app totalmente free e priva di pubblicità, ho deciso di non accquistare alcun servizio a pagamento. Ricordo che le previsioni meteo non sono una certezza ma l'analisi di una raccolta dati di processi atmosferici per ottenere una stima dell'evoluzione atmosferica.

> D: Il radar non si carica, perchè?

La velocità di caricamento delle immagini radar può dipendere dalla tua connessione dati o da un disservizio dell'ente da cui si ricava l'immagine radar.

> D: La webcam non si carica / non è aggiornata, perchè?

Può essere dovuto da un disservizio dell'ente da cui viene ricavata l'immagine webcam.

> D: La mia località non è presente, perchè?

Sono supportate tutte le località presenti all'interno del dataset di meteotrentino, se la tua non è presente prova ad estendere la ricerca al comune, oppure contattami.

### Privacy
L'applicazione non salva alcun dato personale dell'utente. Vengono utilizzati i servizi:
  - GPS per eseguire le chiamate ai dataset metereologici
  - Firebase per tenere traccia dei crash
  - OneSignal per inviare le notifiche automatiche

### PR
L'applicazione è ancora in fase di sviluppo, ma si accettano comunque PR seguendo il seguente schema:
 - Creare branch feature-nomeFeature
 - Seguire lo standard per lo sviluppo android
 - Usare lo schema dei commit con la seguente sintassi:
   * [UP] in caso di update di uno o più file
   * [REF] in caso di refactor di uno o più file
   * [ADD] in caso di aggiunta di uni o più file
 - Verificare che compili senza errori
 - Inviare la PR
 
happy coding.
