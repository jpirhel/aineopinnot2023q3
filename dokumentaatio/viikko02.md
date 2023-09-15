# Viikkoraportti viikko 2

## Käytetty aika:

- 2023-09-14 to: 8h (ohjelmointi)
- 2023-09-15 pe: 3h (dokumentaatio, testit + checkstyle)

viikko yht. 11h, kurssin alusta: 15h


## Mitä olen tehnyt tällä viikolla?

Tällä viikolla olen ohjelmoinut datasetin importtausta. Tässä tein hieman quick-n-dirty -version, mutta näyttää toimivan ja lukee onnistuneesti OpenFlights:in lentoasematiedot. Lentoasemia on datasetissä hieman yli 7500.

Tein verkkogeneraattorin, jonka avulla saa luotua verkon lentokentältä saavutettavista kentistä. Funktio ottaa argumentteina lähtöpisteen sekä lentokoneen kantaman, esim. 768km (Cessna 152).


## Miten ohjelma on edistynyt?

Ohjelmaan on tullut aika iso läjä uutta koodia, joskaan en ole vielä päässyt varsinaisten algoritmien (Dijkstra, IDA*) kimppuun. Käyn näiden kimmppuun ensi viikolla. Jo kirjoitetulle koodille tein kattavan, joskin hyvin yksinkertaisen (ei vielä "oikeita asioita" testaavan) testipatteriston.


## Mitä opin tällä viikolla?

CheckStyle:n konfigurointia ja testien kirjoitusta. Testit ovat vielä aika raakileita, mutta tarkoitus on laajentaa niitä testaamaan oikeita asioita lähiaikoina. Myös lentokenttien verkon generoiminen on jotain, millaista en ole aiemmin tehnyt.


## Mikä jäi epäselväksi tai tuottanut vaikeuksia?

Aika pienellä datamäärällä (n = <8000) jos käyttää O(n^2) -algoritmia, niin aikarajat paukkuvat nopeasti. Lentokenttien etäisyyksien laskeminen on naiivilla algoritmilla aika hidasta, kestää noin 5 sekuntia. Tämä tehdään kuitenkin kerran, ja "oikeassa" ohjelmassa tuon kerran generoidut etäisyystiedot voinee sisällyttää ohjelmaan, joten tuota ei tarvitse laskea ohjelman toimesta lainkaan. Jääköön noin toistaiseksi.

Tyylini ohjelmoida on selkeästi vähemmän algoritminen ja enemmän "Enterprise Java" -tyyppinen. Valitettavasti.

## Mitä teen seuraavaksi?

Seuraavalla viikolla ryhdyn siirtymään varsinaisten toteutettavien algoritmien (Dijkstra, IDA*) kimppuun.
