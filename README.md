# Sovelluksen kuvaus

Kurssin Tietokantojen perusteet (lempinimeltään tikape), syksy 2018, toinen harjoitustyö. Lähettäjä Sara Pyykölä.

Sovellus on kysymyspankki, johon voi lisätä kysymyksiä eri kursseista ja aiheista, sekä poistaa niitä.
Juurisivulla kaikki pankkiin lisätyt kysymykset on listattu kursseittain järjestettynä, ja jokaisesta kysymyksestä 
kerrotaan kurssin lisäksi aihe.
Sivun alaosassa on lomake, josta voi lisätä uuden kysymyksen listaan.
Klikkaamalla kysymystä pääsee kysymyssivulle, josta näkee vaihtoehdot kuhunkin kysymykseen. 

Kysymyssivulla kysymykseen voi lisätä oikeita ja vääriä vaihtoehtoja yksitellen, ja useampi kuin yksi vastaus voi olla oikein.
Jokaisen vaihtoehdon vieressä on tieto sen oikeellisuudesta, sekä poista-nappi, jolla voi poistaa kyseisen vaihtoehdon (sivu ohjautuu
juureen eli kysymyslistaan tämän jälkeen).
Kysymys-sivulla voi lisätä myös uusia vaihtoehtoja sivun alaosasta löytyvällä lomakkeella, ja tarvittaessa päästä
takaisin kysymyslistaan linkistä.

Käyttövirheinä on huomioitu tyhjät kentät, saman kysymyksen lisääminen, ja saman vastauksen lisääminen saman kymyksen alle.
Lisäksi delete-metodi on ohjelmoitu poistamaan vain olemassa olevaa tietoa, jotta tietokanta pysyy siistinä ja sinne ei suoriteta turhia
kyselyjä.

Jos Heroku-sovelluksessa ilmenee virheitä, voi virheen helposti korjata muokkaamalla GitHub-repositoriota, sillä repositorio on
linkitetty sovellukseen. Lisätessä uutta tietoa repositorioon sovellus käynnistää itsensä automaattisesti uudelleen uusimman kansio- ja
tiedostorakenteen mukaan.

Tässä ongelmana on, että myös tietokanta nollautuu tällöin alkutilaan, eli kysymyspankki.db-tiedoston malliin, joka
repositoriossa on, ja koska se pysyy erillään Herokun omasta PostgreSQL-tietokannsta, Herokussa tehdyt muutokset katoavat.
Tämän ongelman voisi ratkaista sillä, että uusin versio Heroku-sovelluksen PostgreSQL-tietokannasta ladattaisiin
aika ajoin tietokoneelle, jolloin se yhdistettäisiin alkuperäisen SQLite-tiedoston kanssa ja muokattu tiedosto lisättäisiin takaisin
repositorioon, jolloin muutokset päivittyisivät myös Herokun PostgreSQL-kantaan ja sovelluksen rakenteen muokkaaminen ei hävittäisi
kaikkia muutoksia tietokannasta.

Käyttäjän on kuitenkin hyvä huomata, että virhe tulee esiin vain, jos sovelluksen rakennetta muutetaan ennen uudelleenkäynnistystä:
sovelluksen uudelleenkäynnistäminen päivittämällä tai käynnistämällä uudestaan Herokusta ei nollaa tietokantaa alkutilaan, sillä
sovelluksen rakenne on edelleen sama, ja vastaavasti Herokussa tehdyt muutokset säilyvät niin kauan,
kun sovelluksen/repositorion rakennetta ei muuteta, koska vain silloin sovellus käynnistyy uudelleen repositorion mallista.

Kansiorakenteesta sen verran, että templates-pakkauksesta (src/main/resources) löytyvät sivujen HTML-mallit (kysymykset.html, juurisivu
ja kysymys.html, kysymyssivu), ja Source Packagesissa (src/main/java) on kaikki muu. Main (käynnistysluokka) on pakkauksessa tikape,
luokkaoliot pakkauksessa tikape.domain, dao-pinnat pakkauksessa tikape.dao ja tietokanta pakkauksessa tikape.database. Pom.xml-sisältää
riippuvuudet, ja se löytyy juuresta, samoin kuin Procfile, joka huolehtii sovelluksen käynnistämisestä Herokussa.
