# Toteutus

## Ohjelman yleisrakenne

Ohjelma on jaettu useampaan osioon lähdekoodin tasolla:

- org.example.Main - komentoriviargumenttien mukaan tapahtuva toiminnallisuuden laukaisu
- org.example.data.* - datan importtaamiseen tarvittava toiminnallisuus, sekä tarvittavat datarakenteet
- org.example.gui.* - graafiseen käyttöliittymään liittyvät ominaisuudet, esim. kartta
- org.example.geo - Geografiseen laskentaan liittyvä toiminnallisuus, esim. kahden pisteen välinen etäisyys
- org.example.logic.* - varsinaiset algoritmit (Dijkstra & IDA*, sekä Dijkstraa varten tehty oma implementaatio PriorityQueue:sta) 

Hakemistossa org.example.Data löytyvät luokat Airport*, joiden avulla mallinnetaan lentokenttien välisiä graafeja.

Kun ohjelman reitinlaskentaan käytettävä toiminnallisuus käynnistetään, ohjelma muodostaa ensin annetun lentokonekantaman (kilometreissä) perusteella graafin, jota käytetään laskennassa. Tämä graafin muodostamisen toiminnallisuus löytyy luokasta org.example.data.AirportDataGenerator metodista generateAirportGraph.

Kun graafi on laskettu, käynnistetään Dijkstra tai IDA* -haku, jotka löytyvät luokista org.example.Logic.{DijkstraSearch,IdastarSearch}. Jos reitti löytyy, näytetään lopputulos graafisena karttaesityksenä käyttäen org.example.gui.* -luokkia.

## Saavutetut aika- ja tilavaativuudet




## Suorituskyky- ja O-analyysivertailu

Huomionarvoista on se, että joillain syötteillä IDA* räjähtää niin, että sen tekemä laskenta on huomattavasti hitaampaa kuin Dijkstran vastaava. Dijkstra vaikutti toimivan kaikilla syötteillä, eli joko löysi reitin nopeasti tai sitten, jos reittiä ei ollut, palautti tämänkin vastauksen nopeasti. IDA* käytti joihinkin tapauksiin niin pitkän aikaa, että en jäänyt odottelemaan laskennan valmistumista. Timeout on IDA*:lla 10 sekuntia, ja tätä timeoutia ei tarvittu Dijkstrassa.

En testannut, mutta arvelisin tämän johtuvan siitä, että IDA* on haavoittuvainen isolle branching factorille. Lentokentällä voi olla useita kymmeniä kohdelentokenttiä reitin seuraavaa pistettä varten.

Toinen huomionarvoinen seikka on se, että IDA* löysi Dijkstran kanssa yhtä lyhyitä reittejä, mutta Dijkstra suosi pienempiä välilaskumääriä kuin IDA*. Esim. alla olevassa Helsinki-Vantaa -> Miyazaki -reitissä 1500km kantamalla Dijkstra löysi reitin, jossa oli 8 lentokenttää, kun IDA* löysi reitin, jossa oli 13 lentokenttää. Reittien pituudet kilometreissä olivat kuitenkin identtiset. Tämä johtunee siitä, että IDA*-algoritmissä Priority Queue:ssa tutkitaan ensin seuraavan pisteen lentokenttiä, jotka ovat mahdollisimman lähellä reitin edellistä lentokenttää.  

## Löydettyjä polkuja

### Dijkstra: Helsinki-Vantaa -> Miyazaki, Japani, range: 500km

"total distance: 8165 km, number of hops: 24"

![](dijkstra_EFHK_RJFM_500.png)

### IDA*: Helsinki-Vantaa -> Miyazaki, Japani, range: 500km

Ei löytänyt reittiä - timeout (10 sekuntia).

### Dijkstra: Helsinki-Vantaa -> Miyazaki, Japani, range: 1500km

"total distance: 7810 km, number of hops: 8"

![](dijkstra_EFHK_RJFM_1500.png)

### IDA*: Helsinki-Vantaa -> Miyazaki, Japani, range: 1500km

"total distance: 7810 km, number of hops: 13"

![](idastar_EFHK_RJFM_1500.png)

## Puutteet ja parannusehdotukset

Ohjelman puutteeksi voidaan laskea se, että suuri osa toiminnallisuudesta on toteutettu komentoriviargumentteina, ja graafista käyttöliittymää ei ole hiottu. Kaikki toiminnallisuus olisi hyvä implementoida UI-elementteinä, jos oltaisiin tekemässä "varsinaista" ohjelmistoa.

Kartan puutteita ovat:

- Ei karttanavigaatiotoiminnallisuutta UI-elementteinä (zoom in / out)
- Kartan markerit eivät ole kovin kuvaavia, eikä niistä ilmene esim. hiiren kursorin avulla lentokentän nimeä
- Vasemman kolumnin reittinäyttö on varsin yksinkertainen. Esim. jos hiiren kursorin siirtää reitillä olevan lentokentän nimen päälle, ko. lentokentän markerin voisi korostaa jotenkin. 

Ehkä isompi puute on se, että ohjelmassa ei ole toiminnallisuutta piirtää yhtäaikaisesti kartalle sekä Dijkstran että IDA*:n löytämää reittiä, jotta niitä voisi vertailla. Useimmissa tapauksissa reitti on kuitenkin identtinen.
