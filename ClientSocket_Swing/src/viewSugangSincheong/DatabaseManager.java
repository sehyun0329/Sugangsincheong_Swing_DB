package viewSugangSincheong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import valueObject.VLecture;

public class DatabaseManager {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/board";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    public static void saveLectures(Vector<VLecture> lectures) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            for (VLecture lecture : lectures) {
                String query = "INSERT INTO board (id, name, professor, credit, time) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, lecture.getId());
                    preparedStatement.setString(2, lecture.getName());
                    preparedStatement.setString(3, lecture.getProfessor());
                    preparedStatement.setString(4, lecture.getCredit());
                    preparedStatement.setString(5, lecture.getTime());

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
