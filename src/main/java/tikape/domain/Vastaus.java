package tikape.domain;

public class Vastaus {

    Integer id;
    String teksti;
    Boolean oikein;
    Integer kysymys_id;

    public Vastaus(int id, String teksti, boolean oikein, int kysymysid) {
        this.id = id;
        this.teksti = teksti;
        this.oikein = oikein;
        kysymys_id = kysymysid;
    }

    public int getId() {
        return id;
    }

    public String getTeksti() {
        return teksti;
    }

    public boolean getOikein() {
        return oikein;
    }

    public int getKysymys() {
        return kysymys_id;
    }

}
