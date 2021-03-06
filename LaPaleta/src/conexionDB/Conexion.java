package conexionDB;

import java.sql.*;

/**
 *
 * @author kevindominguez 
 */
public class Conexion {

    public static final boolean UPDATE_SUCCESSFULL = true;
    public static final boolean UPDATE_FAILED = false;
    public Connection connection;

    private String user = "root";
    private String password = "";
    private String server = "localhost";
    private String port = "3306";
    private String database = "lapaletadb";
    private String url = "jdbc:mysql://" + server + ":" + port + "/" + database;

    public Conexion() {
        try {
            connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                System.out.println("CONEXION EXITOSA");
            }

        } catch (SQLException e) {
            System.out.println("Conexion.java says -> ERROR" + e);
        }
    }

    /**
     * Creates a PreparedStatement object for sending parameterized SQL
     * statements to the database.
     *
     * @param statement an SQL statement.
     * @return a new default PreparedStatement object containing the
     * pre-compiled SQL statement.
     * @throws SQLException if a database access error occurs.
     */
    public PreparedStatement prepareStatement(String statement) throws SQLException {
        return connection.prepareStatement(statement);
    }

    /**
     * Executes the SQL query and returns the ResultSet object generated by the
     * query.
     *
     * @param statement an SQL statement.
     * @return a ResultSet object that contains the data produced by the query;
     * never null.
     * @throws SQLException if a database access error occurs.
     */
    public ResultSet executeQuery(String statement) throws SQLException {
        return prepareStatement(statement).executeQuery();
    }

    /**
     * Executes the SQL statement which must be an SQL Data Manipulation
     * Language (DML) statement, such as INSERT, UPDATE or DELETE; or an SQL
     * statement that returns nothing,such as a DDL statement.
     *
     * @return either (1) the row count for SQL Data Manipulation Language (DML)
     * statements or (2) 0 for SQL statements that return nothing.
     * @param statement an SQL statement.
     * @throws SQLException if a database access error occurs.
     */
    public int executeUpdate(String statement) throws SQLException {
        return prepareStatement(statement).executeUpdate();
    }
}
