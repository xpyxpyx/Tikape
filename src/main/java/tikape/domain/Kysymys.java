package tikape.domain;

public class Kysymys {

    String kurssi;
    String teksti;
    String aihe;
    Integer id;

    public Kysymys(int id, String kurssi, String aihe, String teksti) {
        this.id = id;
        this.kurssi = kurssi;
        this.aihe = aihe;
        this.teksti = teksti;
    }

    public int getId() {
        return id;
    }

    public String getTeksti() {
        return teksti;
    }

    public String getKurssi() {
        return kurssi;
    }

    public String getAihe() {
        return aihe;
    }

}
