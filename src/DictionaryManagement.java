import java.sql.ResultSet;
import java.sql.SQLException;

public class DictionaryManagement extends Dictionary {

    private static DictionaryManagement dictionaryManagement;

    private DictionaryManagement() {

    }

    public static DictionaryManagement getDictionaryManagement() {
        if (dictionaryManagement == null) {
            dictionaryManagement = new DictionaryManagement();
        }
        return dictionaryManagement;
    }

    public void loadWordFromDatabase() {
        System.out.println("Load dictionary from file...");
        mySQLite.connectDatabase(ProjectConfig.databasePath);
        System.out.println("Loaded!");
    }

    public String dictionaryLookup(String lookupWord) {
        String query = "SELECT * FROM av WHERE word LIKE " + "'" + lookupWord + "'";
        ResultSet resultSet = mySQLite.executeQuery(query);
        try {
            return resultSet.getString("html");
        } catch (SQLException e) {
            return "<h1>Chúng tôi không tìm thấy từ mà bạn yêu cầu.</h1>";
        }
    }
}
