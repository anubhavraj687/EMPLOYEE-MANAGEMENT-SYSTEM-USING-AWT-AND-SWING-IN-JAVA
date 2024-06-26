package Employee.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class View_Employee extends JFrame implements ActionListener {
    JTable table;

    Choice choiceEMP;

     JButton searchbtn, print, update,back;
    View_Employee(){

        getContentPane().setBackground(new Color(255, 131, 122));
        JLabel search = new JLabel("search  by employee id");
        search.setBounds(20,20,150,20);
        add(search);

        choiceEMP = new Choice();
        choiceEMP.setBounds(180,20,150,20);
       add(choiceEMP);

       try {
           Conn c= new Conn();
           ResultSet resultSet= c.statement.executeQuery("select * from employee");
           while (resultSet.next()){
               choiceEMP.add(resultSet.getString("empId"));
           }
       } catch(Exception e){
           e.printStackTrace();
       }

       table = new JTable();
       try {
           Conn c = new Conn();
           ResultSet resultSet = c.statement.executeQuery("select * from employee");
          table.setModel(DbUtils.resultSetToTableModel(resultSet));

       }catch (Exception e){
           e.printStackTrace();
       }

       JScrollPane jp = new JScrollPane(table);
       jp.setBounds(0,100,900,600);
       add(jp);

        searchbtn = new JButton("Search");
        searchbtn.addActionListener(this);
        searchbtn.setBounds(20,70,80,20);
        add(searchbtn);

        print = new JButton("Print");
        print.addActionListener(this);
        print.setBounds(120,70,80,20);
        add(print);

        update = new JButton("Update");
        update.addActionListener(this);
        update.setBounds(220,70,80,20);
        add(update);

        back = new JButton("Back");
        back.addActionListener(this);
        back.setBounds(320,70,80,20);
        add(back);

        setSize(900,700);
        setLayout(null);
        setLocation(300,100);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==searchbtn){
            String query = "select * from employee where empID='"+choiceEMP.getSelectedItem()+"'";
            try {
                Conn c= new Conn();
                ResultSet resultSet = c.statement.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (Exception E){
                E.printStackTrace();
            }
        }
        else if (e.getSource()==print){
            try {
            table.print();
            } catch (Exception E){
                E.printStackTrace();
            }
        }

           else if (e.getSource()==update){
            setVisible(false);
            new UpdateEmployee(choiceEMP.getSelectedItem());

        }
        else  {
            setVisible(false);
            new Main_class();
        }

    }
    public static void main(String [] args){
      new View_Employee();
    }


}
