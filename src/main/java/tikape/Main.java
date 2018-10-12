package tikape;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.dao.KysymysDao;
import tikape.dao.VastausDao;
import tikape.database.Database;
import tikape.domain.Kysymys;
import tikape.domain.Vastaus;

public class Main {

    public static void main(String[] args) throws Exception {

        if (System.getenv("PORT") != null) {
            Spark.port(Integer.valueOf(System.getenv("PORT")));
        }
        
        Database database1 = new Database("jdbc:sqlite:kysymyspankki.db");
        KysymysDao kysymysDao = new KysymysDao(database1);
        VastausDao vastausDao = new VastausDao(database1);

        Spark.get("/kysymykset", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("kysymykset", kysymysDao.findAll());

            return new ModelAndView(map, "kysymykset");
        }, new ThymeleafTemplateEngine());

        Spark.post("/kysymykset", (req, res) -> {
            String kurssi = req.queryParams("kurssi");
            String aihe = req.queryParams("aihe");
            String teksti = req.queryParams("teksti");
            if (kurssi.isEmpty() || teksti.isEmpty() || aihe.isEmpty()) {
                return "Virhe: tekstikentät eivät voi olla tyhjiä. Palaa takaisin, ja täytä kentät.";
            }

            Kysymys kysymys = new Kysymys(-1, kurssi, aihe, teksti);
            kysymysDao.saveOrUpdate(kysymys);

            res.redirect("/kysymykset");
            return "";
        });

        Spark.post("/kysymys/:id", (req, res) -> {
            String teksti = req.queryParams("teksti");
            String oikein = req.queryParams("oikein");
            boolean oikea = false;
            Integer kysymysId = Integer.parseInt(req.params(":id"));

            if (oikein == null) {
                oikein = "";
            }

            if (oikein.equals("true")) {
                oikea = true;
            }

            if (teksti.isEmpty()) {
                return "Virhe: tekstikenttä ei voi olla tyhjä. Palaa takaisin, ja täytä kenttä.";
            }

            Vastaus vastaus = new Vastaus(-1, teksti, oikea, kysymysId);
            vastausDao.saveOrUpdate(vastaus);

            res.redirect("/kysymykset");
            return "";
        });

        Spark.get("/kysymys/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            Integer kysymysId = Integer.parseInt(req.params(":id"));
            map.put("kysymys", kysymysDao.findOne(kysymysId));
            map.put("vastaukset", vastausDao.findAll(kysymysId));

            return new ModelAndView(map, "kysymys");
        }, new ThymeleafTemplateEngine());

        Spark.post("/delete/vastaus/:id", (req, res) -> {
            Integer vastausId = Integer.parseInt(req.params(":id"));
            vastausDao.delete(vastausId);

            res.redirect("/kysymykset");
            return "";
        });

        Spark.post("/delete/kysymys/:id", (req, res) -> {
            Integer kysymysId = Integer.parseInt(req.params(":id"));
            kysymysDao.delete(kysymysId);

            res.redirect("/kysymykset");
            return "";
        });

    }
}
