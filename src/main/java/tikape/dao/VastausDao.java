package tikape.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.database.Database;
import tikape.domain.Vastaus;

public class VastausDao implements Dao<Vastaus, Integer> {

    private Database database;

    public VastausDao(Database database) {
        this.database = database;
    }

    public List<Vastaus> findAll(Integer kysymysid) throws SQLException {
        List<Vastaus> vastaukset = new ArrayList<>();

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT id, teksti, oikein FROM Vastaus WHERE kysymys_id = ?");
        stmt.setInt(1, kysymysid);
        ResultSet result = stmt.executeQuery();

        while (result.next()) {
            vastaukset.add(new Vastaus(result.getInt("id"), result.getString("teksti"), result.getBoolean("oikein"), kysymysid));
        }

        return vastaukset;
    }

    @Override
    public Vastaus saveOrUpdate(Vastaus object) throws SQLException {
        try (Connection conn = database.getConnection()) {
            Vastaus byId = findOne(object.getId());
            Vastaus byName = findOne(object.getTeksti(), object.getKysymys());

            if (byId != null) {
                return byId;
            }

            if (byName != null) {
                return byName;
            }

            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Vastaus (teksti, oikein, kysymys_id) VALUES (?, ?, ?)");
            stmt.setString(1, object.getTeksti());
            stmt.setBoolean(2, object.getOikein());
            stmt.setInt(3, object.getKysymys());
            stmt.executeUpdate();
        }

        return object;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        try (Connection conn = database.getConnection()) {
            Vastaus byId = findOne(key);

            if (byId != null) {
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM Vastaus WHERE id = ?");
                stmt.setInt(1, key);
                stmt.executeUpdate();
            }

        }

    }

    @Override
    public List<Vastaus> findAll() throws SQLException {
        throw new UnsupportedOperationException("You need a question to search for answers.");
    }

    public Vastaus findOne(String teksti, int kysymysKey) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Vastaus WHERE teksti=? AND kysymys_id = ?");
            stmt.setString(1, teksti);
            stmt.setInt(2, kysymysKey);

            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                return null;
            }

            return new Vastaus(result.getInt("id"), result.getString("teksti"), result.getBoolean("oikein"), result.getInt("kysymys_id"));
        }
    }

    @Override
    public Vastaus findOne(Integer key) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Vastaus WHERE id = ?");
            stmt.setInt(1, key);

            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                return null;
            }

            return new Vastaus(result.getInt("id"), result.getString("teksti"), result.getBoolean("oikein"), result.getInt("kysymys_id"));
        }

    }
}
