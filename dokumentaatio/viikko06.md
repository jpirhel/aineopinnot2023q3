# Viikkoraportti viikko 6

## Käytetty aika

- 2023-10-12 1h vertaisarviointi #2
- 2023-10-13 6h ohjelmointi (Dijkstra)
- 2023-10-14 5h ohjelmointi (käyttöliittymä), dokumentaatio

## Mitä olet tehnyt tällä viikolla

Tällä viikolla tein vertaisarvioinnin numero 2. 

Implementoin Dijkstran algoritmin, jolla etsitään lyhin reitti kahden lentokentän välillä. Implementoin myös alustavan testauksen ko. algoritmille.

Pyörittelin käyttöliittymää, ja ajanpuutteen vuoksi en kerkeä opiskella JavaFX:ää tarpeeksi, joten implementoin käyttöliittymän osittain komentiriviltä.

Graafisella puolella implementoin kartan, joka piirtää lasketun reitin laittamalla reitin lentokentät waypoint:eina kartalle.

Käyttämälläni karttapohjalla on mahdollista piirtää reitti myös viivoja käyttäen, mutta ajanpuutteen vuoksi todennäköisesti jätän noin; reitti piirtyy melko kauniisti tälläkin metodilla.

## Mikä jäi epäselväksi ja mikä on tuottanut vaikeuksia

Sekä Dijkstra että IDA* on jäänyt toiminnaltaan hieman epäselväksi. Dijkstra näyttäisi toimivan oikein karttavisualisoinnin perusteella.

## Mitä teen seuraavaksi

Jatkan GUI:n parissa työskentelyä ja projektin kiinni kirimistä edelleen. GUI:n hienosäädön lisäksi on tarkoitus seuraavaksi implementoida toinen algoritmi eli IDA*. 

Tehtävänä on myös prioriteettijonon oma implementaatio Dijkstraa varten. Viimeiselle viikolle jää siis useamman viikon tehtävät, valitettavasti.
