# Käyttöohje

Ohjelma suoritetaan seuraavasti:

```
./gradlew run --args='icao <lentokentan_nimi>'
```
(EI VIELÄ IMPLEMENTOITU) Etsii lentokentän ICAO-koodin nimen perusteella (case insensitive) 

```
./gradlew run --args='dijkstra <lähtokentän_ICAO> <kohdekentän_ICAO> <kantama_kilometreissä>' 
```

Etsii lyhimmän reitin käyttäen Dijkstran algoritmia lähtökentältä kohdekentälle. Kentät annetaan käyttäen ICAO-koodeja. Kantama annetaan kilometreissä.

Esimerkki: 

```
./gradlew run --args='dijkstra EFHK RJNH 500'
```

```
./gradlew run --args='idastar <lähtokentän_ICAO> <kohdekentän_ICAO> <kantama_kilometreissä>' 
```

(EI VIELÄ IMPLEMENTOITU) Etsii lyhimmän reitin käyttäen IDA*-algoritmia lähtökentältä kohdekentälle. Kentät annetaan käyttäen ICAO-koodeja. Kantama annetaan kilometreissä.

Eri lentokoneiden kantamia kilometreissä:

- Cessna 152 (pienkone, kantama: 768km)
- Cessna 172 Turbo Skyhawk JT-A (pienkone, kantama: 1639km)
- Cessna Citation Longitude (liikesuihkukone, kantama: 6500km)
- Airbus A380 (isorunkoinen suihkukone, kantama: 14800km)
