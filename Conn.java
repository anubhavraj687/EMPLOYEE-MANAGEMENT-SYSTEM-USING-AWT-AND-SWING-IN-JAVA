package Employee.Management.System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {

    Connection connection;
    public Statement statement;

    public Conn(){
        try {
        Class.forName ("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeemanagementsystem","root","Sonu@932014");
        statement = connection.createStatement();

        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
