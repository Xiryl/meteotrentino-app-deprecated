## Meteo Trentino - App

*applicazione meteo per le previsioni metereologiche in Trentino*

 <img src="https://lh3.googleusercontent.com/Epk79x0nC0k4wBlcCDC8Vghzu4XV-9OrJGH6Gd3J6rlw1EOdovB8kmKcB7fODzsH66s=s360" height="75px"> <a href='https://play.google.com/store/apps/details?id=it.chiarani.meteotrentinoapp'><img alt='Disponibile su Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/it_badge_web_generic.png' height='70px' /></a> 

Meteo trentino è un'applicazione meteo che utilizza gli opendata della Provincia Autonoma di Trento.

Scaricala dal [Play Store](https://play.google.com/store/apps/details?id=it.chiarani.meteotrentinoapp).

### Screen
|Giorno|Notte|Tramonto|Pioggia|Settimana|Radar|Allerte|
| --- | --- | --- | --- | --- | --- | --- |
|<img src="https://github.com/Xiryl/MeteoTrentino-App/blob/master/UI/g-play/home1.png" height="200px">| <img src="https://github.com/Xiryl/MeteoTrentino-App/blob/master/UI/g-play/home2.png" height="200px">| <img src="https://github.com/Xiryl/MeteoTrentino-App/blob/master/UI/g-play/home3.png" height="200px">| <img src="https://github.com/Xiryl/MeteoTrentino-App/blob/master/UI/g-play/home4.png" height="200px">| <img src="https://github.com/Xiryl/MeteoTrentino-App/blob/master/UI/g-play/home5.png" height="200px">| <img src="https://github.com/Xiryl/MeteoTrentino-App/blob/master/UI/g-play/radar.png" height="200px">| <img src="https://github.com/Xiryl/MeteoTrentino-App/blob/master/UI/g-play/alert.png" height="200px">|

### FAQ
> D: L'applicazione è bloccata sull'animazione iniziale, perchè?

Può capitare che la connessione dati non sia sufficiente per scaricare i dati meteo. Prova a richiudere l'applicazione e riaprirla.

> D: La temperatura attuale è diversa da quella rilevata dalla stazione metereologica della stessa località, perchè?

La temperatura attuale presente nella homepage è ricavata da un servizio esterno (openweathermap) poichè la provincia non rilascia alcun dato per la temperatura attuale, ma bensì solo la massima e la minima. Viene utilizzato un servizio esterno poichè non tutte le località hanno una stazione metereologica da cui ricavare tutti i dati.

> D: La temperatura attuale è maggiore/minore della massima/minima, perchè?

Gli opendata di www.meteotrentino.it rilasciano solo le temperature massime e minime e, poiché la temperatura attuale non è disponibile, essa viene ricavata da un servizio esterno (openweathermap), il quale può rilevare una temperatura diversa da quella impostata nel primo bollettino della giornata. Ricordo che le previsioni meteo non sono una certezza ma l'analisi di una raccolta dati di processi atmosferici per ottenere una stima dell'evoluzione atmosferica.

> D: Il radar non si carica, perchè?

Può dipendere dalla tua connessione dati o da un disservizio dell'ente da cui si ricava l'immagine radar

> D: La webcam non si carica / non è aggiornata, perchè?

Può essere dovuto da disservizio dell'ente da cui si ricava l'immagine webcam

> D: La mia località non è presente, perchè?

Sono supportate tutte le località presenti all'interno del dataset di meteotrentino, la tua non è presente? Prova ad estendere la ricerca al comune.

> D: La notifica mattutina mi da fastidio, come la tolgo?

E' possibile disattivarla dalle impostazioni (menù laterale) dell'app

### Privacy
L'applicazione non salva alcun dato personale dell'utente. Vengono utilizzati i servizi:
  - GPS per eseguire le chiamate ai dataset metereologici
  - Firebase per tenere traccia dei crash
  - OneSignal per inviare le notifiche automatiche

### PR
L'applicazione è ancora in fase di sviluppo, ma si accettano comunque PR seguendo il seguente schema:
 - Creare branch dev
 - Commit con descrizione
 
happy coding.
