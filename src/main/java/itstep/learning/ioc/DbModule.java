package itstep.learning.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbModule extends AbstractModule {

    private Connection connection = null;
    private Driver mysqlDriver = null;

    @Provides  // методи-провайдери -- керована інжекція
    private Connection getConnection() {
        // кожна точка інжекції типу даних "Connection" буде запускати
        // цей метод і його повернення інжектувати як залежність.
        if(connection == null) {
            try {
                mysqlDriver = new com.mysql.cj.jdbc.Driver();  // створюємо об'єкт драйвера СУБД
                DriverManager.registerDriver( mysqlDriver );  // реєструємо його
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3308/start_date_time" +
                                "?useUnicode=true&characterEncoding=utf8",
                        "user_dch", "pass987"
                );   // на відміну від ADO підключення відкривається при створенні
            }
            catch( SQLException ex ) {
                System.err.println( "DbModule::getConnection " + ex.getMessage() );
            }
        }
        return connection;
    }
}
/*
Д.З. Створити "журнал" запуску застосунку:
при кожному запуску здійснюється запис до таблиці БД дата-час запуску
* додати параметр командного рядку який при запуску виведе весь журнал
... App.class --journal
 */