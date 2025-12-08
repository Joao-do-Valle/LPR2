import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String URL = "jdbc:sqlite:patients.db";

    public Database() {
        initDB();
    }

    // Inicializa a base (cria tabela se não existir)
    public void initDB() {
        String sql = "CREATE TABLE IF NOT EXISTS patients (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "nome TEXT NOT NULL," +
                     "idade INTEGER," +
                     "peso REAL," +
                     "altura REAL" +
                     ");";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insertPatient(String nome, int idade, double peso, double altura) {
        String sql = "INSERT INTO patients(nome,idade,peso,altura) VALUES(?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setInt(2, idade);
            pstmt.setDouble(3, peso);
            pstmt.setDouble(4, altura);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retorna todas as linhas formatadas
    public List<String> getAllPatients() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT id, nome, idade, peso, altura FROM patients ORDER BY id";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String line = String.format("ID: %d | Nome: %s | Idade: %d | Peso: %.2f | Altura: %.2f",
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getDouble("peso"),
                        rs.getDouble("altura"));
                list.add(line);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Pesquisa por nome usando LIKE (case-insensitive)
    public List<String> searchByName(String namePattern) {
        List<String> list = new ArrayList<>();
        String sql = "SELECT id, nome, idade, peso, altura FROM patients WHERE nome LIKE ? ORDER BY id";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + namePattern + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String line = String.format("ID: %d | Nome: %s | Idade: %d | Peso: %.2f | Altura: %.2f",
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getInt("idade"),
                            rs.getDouble("peso"),
                            rs.getDouble("altura"));
                    list.add(line);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
