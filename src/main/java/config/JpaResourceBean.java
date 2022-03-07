/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 *
 * @author user
 */
public class JpaResourceBean {

    private static EntityManagerFactory emf;

    public static Connection getConnection() {
        String datasource = "java:/is2";
        try {
            Context context = new InitialContext();
            
            DataSource dataSource= (DataSource) context.lookup(datasource);
            return dataSource.getConnection();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static void close(Connection connection){
         try {
           connection.close();
        } catch (Exception e) {
        }
    }

}
