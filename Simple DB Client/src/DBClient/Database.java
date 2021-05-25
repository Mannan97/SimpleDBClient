package DBClient;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private String[] information;

    public Database(String[] information){
        this.information=information;

    }


    public String value(int i){
        return information[i];
    }


    public static ObservableList<Database> getResults(String command){
        ObservableList<Database> ret_val= FXCollections.observableArrayList();
        String conn_url="jdbc:mysql://localhost:3306/employees?user=root&password=";
        Connection conn= null;

        //Preset Parametsr
        int row_num=0;
        int col_num=0;

        try{
            conn=DriverManager.getConnection(conn_url);
            ResultSet rs=null;
            PreparedStatement stmt = conn.prepareStatement(command);
            rs = stmt.executeQuery();

            while(rs!=null && rs.next()){
                //Get number of rows
                rs.last();
                row_num=rs.getRow();
                rs.first(); //Returns cursors to the first row
                //Get number of columns
                ResultSetMetaData rsmd=rs.getMetaData();
                col_num=rsmd.getColumnCount();

                String[] results=new String[col_num+1];


                for (int i = 0; i<col_num; i++){
                    if (i == 0){
                        results[i]=rsmd.getColumnName(i+1);
                    }
                    else{
                        String info = rs.getString(i+1);
                        results[i]=info;
                    }
                }

                ret_val.add(new Database(results));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret_val;
    }


}
