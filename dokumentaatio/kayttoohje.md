# Käyttöohje

## joitain testattavia komentorivejä (jar)

ICAOt:

- EFHK = Helsinki-Vantaa
- EFRO = Rovaniemi
- RJFM = Miyazaki Airport, Japani

```
java -jar harjoitustyo-1.0-SNAPSHOT.jar dijkstra EFHK RJFM 200  # ei löydä reittiä

java -jar harjoitustyo-1.0-SNAPSHOT.jar dijkstra EFHK RJFM 250  # löytää reitin
java -jar harjoitustyo-1.0-SNAPSHOT.jar idastar EFHK RJFM 250  # räjähtää

java -jar harjoitustyo-1.0-SNAPSHOT.jar dijkstra EFHK RJFM 1500  # löytää reitin
java -jar harjoitustyo-1.0-SNAPSHOT.jar idastar EFHK RJFM 1500  # löytää reitin

java -jar build\libs\harjoitustyo-1.0-SNAPSHOT.jar dijkstra EFHK EFRO 150  # löytää reitin
java -jar build\libs\harjoitustyo-1.0-SNAPSHOT.jar idastar EFHK EFRO 150  # löytää reitin

# löytää reitin ilman välilaskuja kuten pitääkin
java -jar harjoitustyo-1.0-SNAPSHOT.jar dijkstra EFHK EFRO 1500

# löytää reitin, mutta kahdella välilaskulla - pitäisi löytää suora lento
java -jar harjoitustyo-1.0-SNAPSHOT.jar idastar EFHK EFRO 1500  
```


## Kartan käyttöliittymä

Kun ohjelma käynnistää karttanäkymän (komennot airports, dijkstra, idastar) karttaa voi siirtää klikkaamalla hiiren napin pohjaan ja siirtämällä hiirtä. Kartan zoom-tasoa voi muuttaa hiiren rullalla.


## Komentorivikäyttöliittymä

Mikäli käytössä on jar, komennot ovat muotoa

```
java -jar harjoitustyo-1.0-SNAPSHOT.jar <komentoriviargumentit>
```

Mikäli taas ajetaan suoraan lähdekoodihakemistosta, komennot ovat muotoa

```
./gradlew run --args='<komentoriviargumentit>'
```


## Ohjelman  komentojen suoritus


### Lentokoneiden kantamat

```
./gradlew run --args='planes'

java -jar harjoitustyo-1.0-SNAPSHOT.jar planes
```

Antaa listauksen joidenkin lentokoneiden tyypeistä ja kantamista.

### ICAO

```
./gradlew run --args='icao <lentokentan_nimi>'

java -jar harjoitustyo-1.0-SNAPSHOT.jar icao <lentokentan_nimi>
```
lentokentän ICAO-koodin nimen perusteella (case insensitive) 

### Dijkstra

```
./gradlew run --args='dijkstra <lähtokentän_ICAO> <kohdekentän_ICAO> <kantama_kilometreissä>'

java -jar harjoitustyo-1.0-SNAPSHOT.jar dijkstra <lähtokentän_ICAO> <kohdekentän_ICAO> <kantama_kilometreissä>
```

Etsii lyhimmän reitin käyttäen Dijkstran algoritmia lähtökentältä kohdekentälle. Kentät annetaan käyttäen ICAO-koodeja. Kantama annetaan kilometreissä.

Esimerkki: 

```
./gradlew run --args='dijkstra EFHK RJNH 500'

java -jar harjoitustyo-1.0-SNAPSHOT.jar dijkstra EFHK RJNH 500
```

### IDA*

```
./gradlew run --args='idastar <lähtokentän_ICAO> <kohdekentän_ICAO> <kantama_kilometreissä>'

java -jar harjoitustyo-1.0-SNAPSHOT.jar idastar <lähtokentän_ICAO> <kohdekentän_ICAO> <kantama_kilometreissä>
```

Etsii lyhimmän reitin käyttäen IDA*-algoritmia lähtökentältä kohdekentälle. Kentät annetaan käyttäen ICAO-koodeja. Kantama annetaan kilometreissä.

### Lentokentät

```
./gradlew run --args='airports'

java -jar harjoitustyo-1.0-SNAPSHOT.jar airports
```

Näyttää kaikki datasetissä olevat lentokentät kartalla.

### Lentokoneet

Eri lentokoneiden kantamia kilometreissä:

- Cessna 152 (pienkone, kantama: 768km)
- Cessna 172 Turbo Skyhawk JT-A (pienkone, kantama: 1639km)
- Cessna Citation Longitude (liikesuihkukone, kantama: 6500km)
- Airbus A380 (isorunkoinen suihkukone, kantama: 14800km)
