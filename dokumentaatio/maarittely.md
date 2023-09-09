# Määrittelydokumentti


## Yleistä

- Opinto-ohjelma: Tietojenkäsittelytieteen kandidaatti (TKT)
- Käytettävä ohjelmointikieli: Java, Kotlin (gradle-skriptit)
- Käytettävät kielet: Englanti (lähdekoodi), Suomi (lähdekoodin kommentit, dokumentaatio, muut)
- Laajojen kielimallien käyttö: Ei käytössä projektin missään osassa

## Kuvaus

Tämän aineopintojen harjoitustyön tarkoitus on vertailla, kuinka kaksi erilaista reitinhakualgoritmia löytävät lyhimmän reitin lentokentältä toiselle. Lentokenttien sijaintien ja käytettävän lentokoneen ominaisuuksien perusteella luodaan puurakenne kenttien välillä, ja haku suoritetaan tässä puussa.


## Käytettävä datasetti


airports.dat (ladattu 2023-09-05).

- Suora linkki tiedostoon: https://raw.githubusercontent.com/jpatokal/openflights/master/data/airports.dat
- Lataussivu: https://openflights.org/data


## Vertailtavat algoritmit

Toteutuksessa vertaillaan _IDA*_- ja _Dijkstran_ algoritmeja. Tarkoituksena on löytää mahdollisimman lyhyt reitti kahden lentokentän välillä.

Datasetissä lentokentistä on koordinaatit. Näistä koordinaateista muodostetaan puu lähtöpisteen mukaan. Jos puuta ei rajattaisi, kaikilta lentokentiltä pääsisi kaikille muille lentokentille suoraan; puuta rajataan sen mukaan, mikä niiden välinen etäisyys on. Tämä etäisyys määräytyy valitun kulkuvälineen mukaan. Rajaukseen otetaan mukaan ainakin seuraavat lentokoneet:

- Cessna 152 (pienkone, kantama: 768km)
- Cessna 172 Turbo Skyhawk JT-A (pienkone, kantama: 1639km)
- Cessna Citation Longitude (liikesuihkukone, kantama: 6500km)
- Airbus A380 (isorunkoinen suihkukone, kantama: 14800km)

Etsittäessä reittiä lentokenttien välillä otetaan huomioon ainoastaan etäisyys, eikä muita lentämiseen vaikuttavia tekijöitä (esim. lentokentän kiitoradan soveltuvuus ko. lentokoneelle jne.). Lentokenttien välisten puurakenteiden ohjelmointi suoritetaan tätä sovellusta varten, käyttäen relevantteja tietorakenteita ja algoritmeja. Mikäli puun generoinnissa käytetään ulkopuolisia algoritmeja, näistä laitetaan mukaan viitteet.


### Dijkstran algoritmi

Tässä sovelluksessa Dijkstran algoritmi toteutetaan binäärikeolla. Dijkstran algoritmin odotettu aikavaativuus on $O(\mid E \mid + \mid V \mid log \mid V \mid)$ [1]. 

### IDA*

Tässä sovelluksessa IDA*-algoritmin keoksi valitaan soveltuva Javan valmis tietorakenne, kuten Aiheideoita-dokumentissa ohjeistettiin. IDA*-algoritmin aikavaativuus riippuu käytettävästä heuristiikasta [2]. Osana sovelluksen ohjelmointia on soveltuvan heuristiikan löytäminen.

Lähteet:

[1] Michael Barbehenn: A Note on the Complexity of Dijkstra's Algorithm for Graphs with Weighted Edges. DOI: 10.1109/12.663776

[2] Richard E. Korf, Michael Reid, Stefan Edelkamp: Time complexity of iterative-deepening-A*. DOI: 10.1016/S0004-3702(01)00094-7
