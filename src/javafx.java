package src;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;

public class javafx extends Application {
	ObservableList<MyItem> items = FXCollections.observableArrayList();
	TableView<MyItem> tableView = new TableView<>();

    @Override
    public void start(Stage primaryStage) {
        // 创建表格视图

        // 创建列并设置单元格值工厂
        for (int i = 0; i < 14; i++) {
            TableColumn<MyItem, String> tableColumn = new TableColumn<>("属性" + (i + 1));
            tableColumn.setCellValueFactory(new PropertyValueFactory<MyItem, String>("property" + (i + 1)));
            tableView.getColumns().add(tableColumn);
        }

        // 创建一个可观察的列表来动态添加行
        for(ArrayList<String> currentString : netcap.listOfArrays){
        	items.add(new MyItem(currentString.get(0), currentString.get(1), currentString.get(2), 
        			currentString.get(3),currentString.get(4), currentString.get(5),currentString.get(6),
        			currentString.get(7), currentString.get(8), currentString.get(9), currentString.get(10),
            		currentString.get(11),currentString.get(12), currentString.get(13),currentString.get(14),currentString.get(15)));
        	
        }

        // 将数据绑定到表格视图
        tableView.setItems(items);
        // 创建一个场景，并将表格视图添加到场景中
        Scene scene = new Scene(tableView, 1200, 600);
        primaryStage.setTitle("Infinite Table View Example");
        primaryStage.setScene(scene);
        
        
        
        
        primaryStage.show();
        
    }
   
	// 简单的JavaFX模型类
    public static class MyItem {

        private String property1,property2, property3, property4, property5, property6, property7, property8, property9,property10,property11,property12,property13,property14,property15,property16;

        public MyItem(String property1, String property2, String property3, String property4, String property5, String property6, String property7, String property8, String property9, String property10, String property11, String property12, String property13, String property14,String property15,String property16) {
            this.property1 = property1;
            this.property2 = property2;
            this.property3 = property3;
            this.property4 = property4;
            this.property5 = property5;
            this.property6 = property6;
            this.property7 = property7;
            this.property8 = property8;
            this.property9 = property9;
            this.property10 = property10;
            this.property11 = property11;
            this.property12 = property12;
            this.property13 = property13;
            this.property14 = property14;
            this.property15 = property15;
            this.property16 = property16;
        }

        // Getter和Setter
        public String getProperty1() {
            return property1;
        }

        public void setProperty1(String property1) {
            this.property1 = property1;
        }
        public String getProperty2() {
            return property2;
        }

        public void setProperty2(String property2) {
            this.property2 = property2;
        }
        public String getProperty3() {
            return property3;
        }

        public void setProperty3(String property3) {
            this.property3 = property3;
        }
        public String getProperty4() {
            return property4;
        }

        public void setProperty4(String property4) {
            this.property4 = property4;
        }
        public String getProperty5() {
            return property5;
        }

        public void setProperty5(String property5) {
            this.property5 = property5;
        }
        public String getProperty6() {
            return property6;
        }

        public void setProperty6(String property6) {
            this.property6 = property6;
        }
        public String getProperty7() {
            return property7;
        }

        public void setProperty7(String property7) {
            this.property7 = property7;
        }
        public String getProperty8() {
            return property8;
        }

        public void setProperty8(String property8) {
            this.property8 = property8;
        }
        public String getProperty9() {
            return property9;
        }

        public void setProperty9(String property9) {
            this.property9 = property9;
        }
        public String getProperty10() {
            return property10;
        }

        public void setProperty10(String property10) {
            this.property10 = property10;
        }
        public String getProperty11() {
            return property11;
        }

        public void setProperty11(String property11) {
            this.property11 = property11;
        }
        public String getProperty12() {
            return property12;
        }

        public void setProperty12(String property12) {
            this.property12 = property12;
        }
        public String getProperty13() {
            return property13;
        }

        public void setProperty13(String property13) {
            this.property13 = property13;
        }
        public String getProperty14() {
            return property14;
        }

        public void setProperty14(String property14) {
            this.property14 = property14;
        }
        public String getProperty15() {
            return property15;
        }

        public void setProperty15(String property15) {
            this.property15 = property15;
        }
        public String getProperty16() {
            return property16;
        }

        public void setProperty16(String property16) {
            this.property16 = property16;
        }
        // 为其他属性添加相应的getter和setter
    }

    public static void fxpr(String[] args) {
        launch(args);
        
    }
}