package bdeb.socialmediaapp.data_access;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseFactory {
    private static DatabaseFactory instance;
    private Connection connection;
    private String URL;
    private Properties prop;

    private DatabaseFactory() {
        try{
            URLBuilder();
            //JDBC ne semblait pas trouver automatique le driver,
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.connection = DriverManager.getConnection(URL,
                    this.prop.getProperty("USERNAME"),
                    this.prop.getProperty("PASSWORD"));
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    //Methode utiliser pour construire le string de connection
    private String URLBuilder(){
        this.prop = new Properties();
        try{
            InputStream fis = DatabaseFactory.class.getClassLoader().getResourceAsStream("sql/db.properties");
            prop.load(fis);
            String host = prop.getProperty("HOST");
            String port = prop.getProperty("PORT");
            String database = prop.getProperty("DATABASENAME");
            this.URL = String.format("jdbc:sqlserver://%s:%s;databaseName=%s;encrypt=true;trustServerCertificate=true;"
                    , host, port, database);
        } catch (RuntimeException | IOException e) {
            System.out.println("ficher non trouver");
            throw new RuntimeException(e);
        }
        return null;
    }


    public static DatabaseFactory getInstance() {
        if (instance == null) {
            instance = new DatabaseFactory();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }
}
