# M��rittelydokumentti


## Yleist�

Opinto-ohjelma: Tietojenk�sittelytieteen kandidaatti (TKT)
K�ytett�v� ohjelmointikieli: Java, mahdollisesti Python, shell (skriptit)
K�ytett�v�t kielet: Englanti (l�hdekoodi), Suomi (l�hdekoodin kommentit, dokumentaatio, muut)
Laajojen kielimallien k�ytt�: Ei k�yt�ss� projektin miss��n osassa

## Kuvaus

T�m�n aineopintojen harjoitusty�n tarkoitus on vertailla, kuinka kaksi erilaista reitinhakualgoritmia l�yt�v�t lyhimm�n reitin lentokent�lt� toiselle. Lentokenttien sijaintien ja k�ytett�v�n lentokoneen ominaisuuksien perusteella luodaan puurakenne kenttien v�lill�, ja haku suoritetaan t�ss� puussa.


## K�ytett�v� datasetti


airports.dat (ladattu 2023-09-05).

Suora linkki tiedostoon: https://raw.githubusercontent.com/jpatokal/openflights/master/data/airports.dat
Lataussivu: https://openflights.org/data


## Vertailtavat algoritmit

Toteutuksessa vertaillaan IDA*- ja Dijkstran algoritmeja. Tarkoituksena on l�yt�� mahdollisimman lyhyt reitti kahden lentokent�n v�lill�.

Datasetiss� lentokentist� on koordinaatit. N�ist� koordinaateista muodostetaan puu l�ht�pisteen mukaan. Jos puuta ei rajattaisi, kaikilta lentokentilt� p��sisi kaikille muille lentokentille suoraan; puuta rajataan sen mukaan, mik� niiden v�linen et�isyys on. T�m� et�isyys m��r�ytyy valitun kulkuv�lineen mukaan. Rajaukseen otetaan mukaan ainakin seuraavat lentokoneet:

Cessna 152 (pienkone, kantama: 768km)
Cessna 172 Turbo Skyhawk JT-A (pienkone, kantama: 1639km)
Cessna Citation Longitude (liikesuihkukone, kantama: 6500km)
Airbus A380 (isorunkoinen suihkukone, kantama: 14800km)

Etsittess� reitti� lentokenttien v�lill� otetaan huomioon ainoastaan et�isyys, eik� muita lent�miseen vaikuttavia tekij�it� (esim. lentokent�n kiitoradan soveltuvuus ko. lentokoneelle jne.). Lentokenttien v�listen puurakenteiden ohjelmointi suoritetaan t�t� sovellusta varten, k�ytt�en relevantteja tietorakenteita ja algoritmeja. Mik�li puun generoinnissa k�ytet��n ulkopuolisia algoritmeja, n�ist� laitetaan mukaan viittet.


### Dijkstran algoritmi

T�ss� sovelluksessa Dijkstran algoritmi toteutetaan bin��rikeolla. Dijkstran algoritmin odotettu aikavaativuus on $O(\mid E \mid + \mid V \mid log \mid V \mid)$ [1]. 

### IDA*

T�ss� sovelluksessa IDA*-algoritmin keoksi valitaan soveltuva Javan valmis tietorakenne, kuten Aiheideoita-dokumentissa ohjeistettiin. IDA*-algoritmin aikavaativuus riippuu k�ytett�v�st� heuristiikasta [2]. Osana sovelluksen ohjelmointia on soveltuvan heuristiikan l�yt�minen.

L�hteet:

[1] Michael Barbehenn: A Note on the Complexity of Dijkstra's Algorithm for Graphs with Weighted Edges. DOI: 10.1109/12.663776

[2] Richard E. Korf, Michael Reid, Stefan Edelkamp: Time complexity of iterative-deepening-A*. DOI: 10.1016/S0004-3702(01)00094-7
