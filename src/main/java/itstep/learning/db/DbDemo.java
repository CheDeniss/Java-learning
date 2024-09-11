package itstep.learning.db;

import com.google.inject.Inject;

import java.sql.*;

public class DbDemo {
    @Inject
    private Connection connection;

    public void run() {
        System.out.println("DB Demo");
       try {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery( "SHOW DATABASES" ) ;
            while ( res.next() ) {
                System.out.println( res.getString(1) ) ;  // !!
                // у JDBC нумерація полів починається з 1
            }
            res.close();
            statement.close();
        }
        catch( SQLException ex ) {
            System.err.println( ex.getMessage() );
        }
    }
}

/*
JDBC - Java DB Connectivity - технологія доступу до даних, еквівалентна ADO.NET або PDO (PHP)
- попередньо створюємо БД та користувача для неї
- підбираємо конектор (драйвер БД) для відповідної СУБД (MySQL), додаємо його до
   проєкту (pom.xml -- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j)

 */
