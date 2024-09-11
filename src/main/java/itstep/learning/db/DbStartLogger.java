package itstep.learning.db;

import com.google.inject.Inject;

import java.sql.*;

public class DbStartLogger {
    @Inject
    private Connection connection;

    public void run() {
        System.out.println("DB Start Logger");
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(
                    "INSERT INTO `app_start` (`start_date`, `start_time`) VALUES (CURDATE(), CURTIME())"
            );

            System.out.println("Record added.");
        }
        catch( Exception ex ) {
            System.err.println( ex.getMessage() );
        }
        finally {
            try {
                if(statement != null) {
                    statement.close();
                }
            }
            catch( SQLException ex ) {
                System.err.println( ex.getMessage() );
            }
        }
    }

    public void ShowAll(){
        System.out.println("============= SHOW TABLE DATA =============");

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(
                    "SELECT * FROM `app_start`"
            );
            while ( res.next() ) {
                int id = res.getInt(1);
                String startDate = res.getString(2);  // Індекс 2 для start_date
                String startTime = res.getString(3);  // Індекс 3 для start_time

                System.out.printf("ID: %d, Start Date: %s, Start Time: %s%n", id, startDate, startTime);            }
            res.close();
        }
        catch( Exception ex ) {
            System.err.println( ex.getMessage() );
        }
        finally {
            try {
                if(statement != null) {
                    statement.close();
                }
            }
            catch( SQLException ex ) {
                System.err.println( ex.getMessage() );
            }
        }    }
}
