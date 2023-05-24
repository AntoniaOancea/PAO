package JDBCRepository;

import model.Membru;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class JDBCRepositoryMembru implements CrudRepository<Membru> {
    public boolean add(Membru membru){
        String sql = "INSERT INTO MEMBRU(nume,numarTelefon) VALUES ('" + membru.getNume() + "','" + membru.getNumarTelefon() + "')";
        DatabaseConnection conn = new DatabaseConnection();
        Statement statement = conn.conexiune();

        try{
            statement.executeUpdate(sql);
            try (FileWriter writer = new FileWriter("text.csv", true)) {
                writer.append(sql+ Instant.now()+'\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            try (FileWriter writer = new FileWriter("text.csv", true)) {
                writer.append(sql+" "+ Instant.now() +" "+e.getMessage()+'\n');
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }
    public int get(String nume) {
        String sql = "SELECT MAX(idMembru) as ID FROM MEMBRU WHERE nume = '" + nume + "'";
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
        String sql = "SELECT * FROM MEMBRU";
        DatabaseConnection conn = new DatabaseConnection();
        Statement statement = conn.conexiune();
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            List<Membru> membruList = new ArrayList<>();
            while (resultSet.next()) {
                String nume = resultSet.getString("nume");
                String numarTelefon = resultSet.getString("numarTelefon");
                System.out.println(nume + "----" + numarTelefon);
                Membru membru = new Membru(nume, numarTelefon);
                membruList.add(membru);
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
    public boolean update(Membru membru) {
        String sql = "UPDATE MEMBRU SET numarTelefon = "+ "'"+membru.getNumarTelefon()+"' WHERE nume = '" + membru.getNume() + "'";
        DatabaseConnection conn = new DatabaseConnection();
        Statement statement = conn.conexiune();
        System.out.println(sql);
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


    public boolean delete(String a){
        String sql = "DELETE from MEMBRU WHERE nume = '" + a + "'";
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
