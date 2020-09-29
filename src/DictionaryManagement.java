import java.sql.PreparedStatement;
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
        String query = "SELECT * FROM " + ProjectConfig.databaseName + " WHERE word LIKE " + "'" + lookupWord + "'";
        ResultSet resultSet = mySQLite.executeQuery(query);
        try {
            return resultSet.getString("html");
        } catch (SQLException e) {
            return "<h1>Chúng tôi không tìm thấy từ mà bạn yêu cầu.</h1>";
        }
    }

    public ResultSet dictionarySearch(String searchWord) {
        String query = "SELECT * FROM "+ ProjectConfig.databaseName +" WHERE word LIKE " + "'" + searchWord + "%'";
        return mySQLite.executeQuery(query);
    }

    public void saveNewWord(Word newWord) {
        String sql = "INSERT INTO " + ProjectConfig.databaseName +
        "(id, word, html)" + "VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement;
            preparedStatement = mySQLite.connection.prepareStatement(sql);
            preparedStatement.setInt(1, newWord.id);
            preparedStatement.setString(2, newWord.word);
            preparedStatement.setString(3, newWord.html);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
