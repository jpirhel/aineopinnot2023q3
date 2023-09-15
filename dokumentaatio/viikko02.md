# Viikkoraportti viikko 2

## K�ytetty aika:

- 2023-09-14 to: 8h (ohjelmointi)
- 2023-09-15 pe: 3h (dokumentaatio, testit + checkstyle)

viikko yht. 11h, kurssin alusta: 15h


## Mit� olen tehnyt t�ll� viikolla?

T�ll� viikolla olen ohjelmoinut datasetin importtausta. T�ss� tein hieman quick-n-dirty -version, mutta n�ytt�� toimivan ja lukee onnistuneesti OpenFlights:in lentoasematiedot. Lentoasemia on datasetiss� hieman yli 7500.

Tein verkkogeneraattorin, jonka avulla saa luotua verkon lentokent�lt� saavutettavista kentist�. Funktio ottaa argumentteina l�ht�pisteen sek� lentokoneen kantaman, esim. 768km (Cessna 152).


## Miten ohjelma on edistynyt?

Ohjelmaan on tullut aika iso l�j� uutta koodia, joskaan en ole viel� p��ssyt varsinaisten algoritmien (Dijkstra, IDA*) kimppuun. K�yn n�iden kimmppuun ensi viikolla. Jo kirjoitetulle koodille tein kattavan, joskin hyvin yksinkertaisen (ei viel� "oikeita asioita" testaavan) testipatteriston.


## Mit� opin t�ll� viikolla?

CheckStyle:n konfigurointia ja testien kirjoitusta. Testit ovat viel� aika raakileita, mutta tarkoitus on laajentaa niit� testaamaan oikeita asioita l�hiaikoina. My�s lentokenttien verkon generoiminen on jotain, millaista en ole aiemmin tehnyt.


## Mik� j�i ep�selv�ksi tai tuottanut vaikeuksia?

Aika pienell� datam��r�ll� (n = <8000) jos k�ytt�� O(n^2) -algoritmia, niin aikarajat paukkuvat nopeasti. Lentokenttien et�isyyksien laskeminen on naiivilla algoritmilla aika hidasta, kest�� noin 5 sekuntia. T�m� tehd��n kuitenkin kerran, ja "oikeassa" ohjelmassa tuon kerran generoidut et�isyystiedot voinee sis�llytt�� ohjelmaan, joten tuota ei tarvitse laskea ohjelman toimesta lainkaan. J��k��n noin toistaiseksi.

Tyylini ohjelmoida on selke�sti v�hemm�n algoritminen ja enemm�n "Enterprise Java" -tyyppinen. Valitettavasti.

## Mit� teen seuraavaksi?

Seuraavalla viikolla ryhdyn siirtym��n varsinaisten toteutettavien algoritmien (Dijkstra, IDA*) kimppuun.
