package JDBCRepository;

import model.MembruPremium;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;

public class JDBCRepositoryMembruPremium implements CrudRepository<MembruPremium> {
    public boolean add(MembruPremium membru){
        String sql = "INSERT INTO MEMBRU(nume,numarTelefon,email) VALUES ('" + membru.getNume() + "','" + membru.getNumarTelefon() + "','"+membru.getEmail()+"')";
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
        String sql = "SELECT MAX(idMembru) as ID FROM MEMBRU WHERE nume = " + nume;
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
    public void getAll() {}

    @Override
    public boolean update(MembruPremium membru) {
        String sql = "UPDATE MEMBRU SET email = '"+ membru.getEmail() + "' WHERE nume = '" + membru.getNume() +"'" ;
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
        return false;
    }
}
