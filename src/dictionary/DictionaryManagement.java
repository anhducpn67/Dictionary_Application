package dictionary;

import sun.plugin2.main.client.MessagePassingOneWayJSObject;
import utility.ProjectConfig;

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

    public String dictionaryLookup(String word) {
        String query = "SELECT * FROM " + ProjectConfig.databaseName + " WHERE word LIKE " + "'" + word + "'";
        ResultSet resultSet = mySQLite.executeQuery(query);
        try {
            return resultSet.getString("html");
        } catch (SQLException e) {
            return "<h1>Chúng tôi không tìm thấy từ mà bạn yêu cầu.</h1>";
        }
    }

    public ResultSet dictionarySearch(String word) {
        String query = "SELECT * FROM "+ ProjectConfig.databaseName
                + " WHERE word LIKE " + "'" + word + "%'";
        return mySQLite.executeQuery(query);
    }

    public boolean isContain(Word word) {
        String result = dictionaryLookup(word.word);
        return !result.equals("<h1>Chúng tôi không tìm thấy từ mà bạn yêu cầu.</h1>");
    }

    public boolean saveWord(Word word) {
        if (isContain(word)) {
            return false;
        }
        String sql = "INSERT INTO " + ProjectConfig.databaseName
                + "(id, word, html)"
                + "VALUES(?,?,?)";
        int numberRows = mySQLite.countRows();
        try {
            PreparedStatement preparedStatement;
            preparedStatement = mySQLite.connection.prepareStatement(sql);
            preparedStatement.setInt(1, numberRows + 1);
            preparedStatement.setString(2, word.word);
            preparedStatement.setString(3, word.html);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void editWord(String word, String newHtml) {
        String sql = "UPDATE " + ProjectConfig.databaseName
                + " SET html = ?"
                + " WHERE word = ?";
        try {
            PreparedStatement preparedStatement;
            preparedStatement = mySQLite.connection.prepareStatement(sql);
            preparedStatement.setString(1, newHtml);
            preparedStatement.setString(2, word);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteWord(String word) {
        String sql = "DELETE FROM " + ProjectConfig.databaseName
                + " WHERE word LIKE " + "'" + word + "'";
        try {
            PreparedStatement preparedStatement;
            preparedStatement = mySQLite.connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
