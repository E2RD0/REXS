/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.model;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Clase utilizada para establecer la conexión con el servidor de SQL Server.
 * @author Eduardo
 * @version 1.2
 */
public class DbConnection {
    
    public Connection conectar(){
    Connection cn = null;
    SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser(ENV.DB_USER);
        ds.setPassword(ENV.DB_PASSWORD);
        ds.setServerName(ENV.DB_HOST);
        ds.setPortNumber(ENV.DB_PORT);
        ds.setDatabaseName(ENV.DB_NAME);
        
//        ds.setURL("jdbc:sqlserver://192.168.1.3:1433;databaseName=dbrexs; user = "+ENV.DB_USER+";"+"password = "+ENV.DB_PASSWORD+";");

    try{ 
        Connection con = ds.getConnection();
//        System.out.println("Conexion exitosa");
        cn = con;
    }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            System.out.println("Fallo en conexion db: " + e);
        }
    return cn;
    };
}
