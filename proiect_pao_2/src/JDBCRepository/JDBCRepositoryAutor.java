package JDBCRepository;

import model.Autor;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class JDBCRepositoryAutor implements CrudRepository<Autor>{
    public boolean add(Autor autor){
        String sql = "INSERT INTO AUTOR(nume,anNastere,rand) VALUES ('" + autor.getNume() + "','" + autor.getAnNastere()+"',"+autor.getRand() + ")";
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
        String sql = "SELECT MAX(idAutor) as ID FROM AUTOR WHERE nume = '" + nume + "'";
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
                System.out.println(id);
                return id;
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
        return 0;
    }
    @Override
    public void getAll() {
        String sql = "SELECT * FROM autor";
        DatabaseConnection conn = new DatabaseConnection();
        Statement statement = conn.conexiune();
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            List<Autor> autorList = new ArrayList<>();
            while (resultSet.next()) {
                String nume = resultSet.getString("nume");
                int anNastere = resultSet.getInt("anNastere");
                int rand = resultSet.getInt("rand");
                System.out.println(nume + " " + anNastere + " " +rand);
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
    public boolean update(Autor autor) {
        return false;
    }

    public boolean delete(String a){
        return false;
    }
}
