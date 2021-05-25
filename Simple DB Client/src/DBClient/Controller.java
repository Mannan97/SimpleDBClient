package DBClient;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.sql.*;


public class Controller {


    @FXML
    private TextArea query;

    @FXML
    private TableView output;

    //Database Access
    public static void DBTest(String query){



    }

    public void results (Event evt){
        //use observable lists
        //Take the query
        String command = String.valueOf(query.getText());

        //Generate database access
        //String conn_url = "jdbc:mysql://localhost:3306/mysql"; //employees?user=root&password=&serverTimezone=UTC";


        ObservableList<Database> values = Database.getResults(command);

        //Put data into the View
        TableColumn<Database,String>Result=new TableColumn<Database,String>("Result");
        Result.setCellValueFactory(new PropertyValueFactory("Result"));

        output.getColumns().setAll(Result);
        output.setItems(values);









    }
}
