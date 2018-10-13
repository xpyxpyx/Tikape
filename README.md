# tikape

Kurssin Tietokantojen perusteet (lempinimeltään tikape), syksy 2018, toinen harjoitustyö. Lähettäjä Sara Pyykölä.


Sovellus on kysymyspankki, johon voi lisätä kysymyksiä eri kursseista ja aiheista, sekä poistaa niitä.
Juurisivulla kaikki pankkiin lisätyt kysymykset on listattu kursseittain järjestettynä, ja jokaisesta kerrotaan lisäksi aihe.
Sivun alaosassa on lomake, josta voi lisätä uuden kysymyksen listaan.
Klikkaamalla kysymystä pääsee kysymyssivulle, josta näkee vaihtoehdot kuhunkin kysymykseen. 

Kysymyssivulla kysymykseen voi lisätä oikeita ja vääriä vaihtoehtoja yksitellen, ja useampi kuin vastaus voi olla oikein.
Jokaisen vaihtoehdon vieressä on tieto oikeellisuudesta, sekä poista-nappi, jolla voi poistaa kyseisen vaihtoehdon (sivu ohjautuu
juureen eli kysymyslistaan tämän jälkeen).
Kysymys-sivulla voi lisätä myös uusia vaihtoehtoja sivun alaosasta löytyvällä lomakkeella, ja tarvittaessa päästä 
takaisin kysymyslistaan linkistä.

Käyttövirheinä on huomioitu tyhjät kentät, saman kysymyksen lisääminen, ja saman vastauksen lisääminen saman kymyksen alle.
Lisäksi delete-metodi on ohjelmoitu poistamaan vain olemassa olevaa tietoa, jotta tietokanta pysyy siistinä ja sinne ei suoriteta turhia kyselyjä.
