package tikape.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.database.Database;
import tikape.domain.Kysymys;

public class KysymysDao implements Dao<Kysymys, Integer> {

    private Database database;

    public KysymysDao(Database database) {
        this.database = database;
    }

    @Override
    public List<Kysymys> findAll() throws SQLException {
        List<Kysymys> kysymykset = new ArrayList<>();

        try (Connection conn = database.getConnection();
                ResultSet result = conn.prepareStatement("SELECT * FROM Kysymys ORDER BY kurssi").executeQuery()) {

            while (result.next()) {
                kysymykset.add(new Kysymys(result.getInt("id"), result.getString("kurssi"), result.getString("aihe"), result.getString("teksti")));
            }
        }

        return kysymykset;
    }

    @Override
    public Kysymys saveOrUpdate(Kysymys object) throws SQLException {
        try (Connection conn = database.getConnection()) {
            Kysymys byId = findOne(object.getId());
            Kysymys byName = findOne(object.getTeksti());

            if (byId != null) {
                return byId;
            }

            if (byName != null) {
                return byName;
            }

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Kysymys (kurssi, aihe, teksti) VALUES (?, ?, ?)");
            stmt.setString(1, object.getKurssi());
            stmt.setString(2, object.getAihe());
            stmt.setString(3, object.getTeksti());
            stmt.executeUpdate();
        }

        return object;

    }

    @Override
    public Kysymys findOne(Integer id) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Kysymys WHERE id = ?");
            stmt.setInt(1, id);

            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                return null;
            }

            return new Kysymys(result.getInt("id"), result.getString("kurssi"), result.getString("aihe"), result.getString("teksti"));
        }
    }

    @Override
    public void delete(Integer key) throws SQLException {
        try (Connection conn = database.getConnection()) {
            Kysymys byId = findOne(key);

            if (byId != null) {
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM Vastaus WHERE id = ?");
                stmt.setInt(1, key);
                stmt.executeUpdate();
            }
        }

    }

    public Kysymys findOne(String teksti) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Kysymys WHERE teksti =?");
            stmt.setString(1, teksti);

            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                return null;
            }

            return new Kysymys(result.getInt("id"), result.getString("kurssi"), result.getString("aihe"), result.getString("teksti"));
        }
    }

}
