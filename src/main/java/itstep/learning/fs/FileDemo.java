package itstep.learning.fs;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.HashMap;

public class FileDemo {

    public void run(){
        //createDbIni(
//                "mysql",
//                "localhost",
//                3308,
//                "start_date_time",
//                "user_dch",
//                "pass987",
//                true,
//                "UTF-8"
//        );

        getIniData();
    }
    public void run1() {
        // Ресурси - файли, що автоматично переносяться у target/classes
        // і доступні при запуску через ClassLoader але ClassLoader-и різні для різних збірок
        try( InputStream rs =                // Як гарантовано дістатись даної збірки?
            this                             // Звернутись до гарантовано її об'єкту - this
            .getClass()                      // Визначаємо його тип (Class)
            .getClassLoader()                // Визначаємо його завантажувач
            .getResourceAsStream("db.ini")   // і звертаємось до ресурсу
        ) {
            String content = readStream( rs );
            Map<String, String> ini = new HashMap<>();

            String[] lines = content.split("\n");
            for(String line : lines) {
                String[] parts = line.split("=");
                ini.put( parts[0].trim(), parts[1].trim() );
            }
            System.out.println(
                    String.format( "jdbc:%s://%s:%s/%s",
                            ini.get("dbms"),
                            ini.get("host"),
                            ini.get("port"),
                            ini.get("schema")
                    )
            );
        }
        catch (IOException ex) {
            System.err.println( ex.getMessage() );
        }
    }

    public void createDbIni(String dbms,
                            String host,
                            int port,
                            String schema,
                            String user,
                            String password,
                            boolean useUnicode,
                            String characterEncoding) {
        try (OutputStream os = new FileOutputStream("db1.ini")) {
            // Створюємо рядки для кожного параметра і записуємо їх у файл
            os.write(("dbms=" + dbms + "\n").getBytes());
            os.write(("host=" + host + "\n").getBytes());
            os.write(("port=" + port + "\n").getBytes());
            os.write(("schema=" + schema + "\n").getBytes());
            os.write(("user=" + user + "\n").getBytes());
            os.write(("password=" + password + "\n").getBytes());
            os.write(("useUnicode=" + useUnicode + "\n").getBytes());
            os.write(("characterEncoding=" + characterEncoding + "\n").getBytes());

            System.out.println("=> File was created.");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public String getIniData() {
        try( InputStream rs = this.getClass().getClassLoader().getResourceAsStream("db.ini")
        ) {
            String content = readStream( rs );
            Map<String, String> ini = new HashMap<>();

            String[] lines = content.split("\n");
            for(String line : lines) {
                String[] parts = line.split("=");
                ini.put( parts[0].trim(), parts[1].trim() );
            }
            String out = String.format(
                    "jdbc:%s://%s:%s/%s?useUnicode=%s&characterEncoding=%s&user=%s&password=%s",
                    ini.get("dbms"),
                    ini.get("host"),
                    ini.get("port"),
                    ini.get("schema"),
                    ini.get("useUnicode"),
                    ini.get("characterEncoding"),
                    ini.get("user"),
                    ini.get("password")
            );

            System.out.println(out);
            return out;

        }
        catch (IOException ex) {
            System.err.println( ex.getMessage() );
            return null;
        }
    }


    public void runFile() {
        System.out.println("File System");
        // Основним об'єктом для роботи з файлами є java.io.File
        File file = new File( "test.txt" );
        // !! створення об'єкту File ніяк не впливає на саму файлову систему
        // !! File-об'єкти відповідають як за файли, так і за директорії

        // Робота з даними у файлах здійснюється через потоки даних (Stream)
        // У Java багаторівнева ієрархія засобів роботи з потоками
        // Основні: InputStream / OutputStream - вища абстракція
        // Імплементації: FileInputStream / ...

        // Задача - одержати дані з ресурсу у вигляді String
        // Складність: можливість multibyte кодування (UTF-8)
        // Рішення: одержати всі байти з ресурсу і провести декодування
        // Складність: ми не знаємо об'єм даних (потік передає по одному)

        // try() - try-with-resources  -- аналог using - блок з автоматичним
        // вивільненням некерованих ресурсів
        try( InputStream is = new FileInputStream( file ) ) {
            System.out.println( readStream( is ) );
        }
        catch ( IOException ex ) {
            System.err.println( ex.getMessage() );
        }
    }

    private String readStream(InputStream in, Charset charset) throws IOException {
        byte[] buffer = new byte[4096];
        try(
                ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream();
                BufferedInputStream bis = new BufferedInputStream(in)
        ) {
            int len;
            while ((len = bis.read(buffer)) != -1) {
                byteBuilder.write(buffer, 0, len);
            }
            return byteBuilder.toString( charset.name() );
        }
    }

    public String readStream(InputStream in) throws IOException {
        return readStream( in, StandardCharsets.UTF_8 );
    }
}
/*
Робота з файлами. Ресурси.
Д.З. Створити метод, який буде формувати (записувати)
файл db.ini з усіма налаштуваннями
для підключення (+дані про кодування символів).

Модифікувати конфігураційний модуль підключення до БД з урахуванням
ресурсного файлу db.ini
 */