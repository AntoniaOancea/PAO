package JDBCRepository;

import model.Carte;
import model.MembruPremium;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class JDBCRepositoryCarte implements CrudRepository<Carte>{
    public boolean add(Carte carte){
        DatabaseConnection conn = new DatabaseConnection();
        Statement statement = conn.conexiune();
        JDBCRepositoryAutor x = new JDBCRepositoryAutor();
        int id = x.get(carte.getAutor().getNume());
        String sql = "INSERT INTO CARTE(titlu,idAutor,anPublicare) VALUES ('" + carte.getTitlu() + "'," + id + "," + carte.getAnPublicare() + ")";
        System.out.println(sql);

        try {
                statement.executeUpdate(sql);
                try (FileWriter writer = new FileWriter("text.csv", true)) {
                    writer.append(sql + Instant.now() + '\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }catch (Exception e){
                System.out.println(e.getMessage());
                try (FileWriter writer = new FileWriter("text.csv", true)) {
                    writer.append(sql + " " + Instant.now() + " " + e.getMessage() + '\n');
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        return false;
    }
    public int get(String nume) {
        String sql = "SELECT MAX(idMembru) as ID FROM CARTE WHERE nume = '" + nume + "'";
        DatabaseConnection conn = new DatabaseConnection();
        Statement statement = conn.conexiune();

        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                try (FileWriter writer = new FileWriter("text.csv", true)) {
                    writer.append(sql + " " + Instant.now() + '\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return id;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try (FileWriter writer = new FileWriter("text.csv", true)) {
                writer.append(sql + " " + Instant.now() + " " + e.getMessage() + '\n');
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return 0;
        }
    }
    @Override
    public void getAll() {
        String sql = "SELECT * FROM CARTE";
        DatabaseConnection conn = new DatabaseConnection();
        Statement statement = conn.conexiune();
        System.out.println(sql);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            List<MembruPremium> membruList = new ArrayList<>();
            while (resultSet.next()) {
                String titlu = resultSet.getString("titlu");
                int idAutor = resultSet.getInt("idAutor");
                String anPublicare = resultSet.getString("anPublicare");
                System.out.println(titlu + "----" + anPublicare);
            }
            try (FileWriter writer = new FileWriter("text.csv", true)) {
                writer.append(sql + " " + Instant.now() + '\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(Carte carte) {
        return false;
    }


    public boolean delete(String a){
        String sql = "DELETE from CARTE WHERE titlu = '" + a + "'";
        DatabaseConnection conn = new DatabaseConnection();
        Statement statement = conn.conexiune();
        try {
            statement.executeUpdate(sql);
            try (FileWriter writer = new FileWriter("text.csv", true)) {
                writer.append(sql + " " + Instant.now() + '\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
