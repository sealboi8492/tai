import java.sql.*;

public class Main{

}

void main() throws SQLException {

    String url = "jdbc:mysql://localhost:3306/biblioteka";
    String user = "root";
    String password = "";



    Connection conn = DriverManager.getConnection(url, user, password);
    Statement stmt = conn.createStatement();
    String sqlSelect = "SELECT tytul, autor, data_wydania, tomatometer FROM ksiazki";
    ResultSet rs = stmt.executeQuery(sqlSelect);

// Przetwarzanie wyników - pętla przechodzi przez wiersze
    while (rs.next()) {
        String tytul = rs.getString("tytul");
        String autor = rs.getString("autor");
        String data = rs.getString("data_wydania");
        String rec = rs.getString("tomatometer");
        System.out.println("Książka: " + tytul + " Autor: " + autor + " Data wydania: " + data + " Opinie: " + rec + "%");
    }
}
