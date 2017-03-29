package GUI;

import Entitate.Post;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PostView {

	protected Label nume;
	protected Label tip;
	
	protected PostController ctr;
	
	protected TextField numeText;
	protected TextField tipText;
	
	protected Button save;
	protected Button delete;
	protected Button update;
	protected Button viewAll;
	protected Button addTask;
	protected Button viewAllPosts;	
	protected Button filtrBynume;
	protected Button filterBytip;
	
	protected VBox pane;
	
	protected TableView<Post> table;
	protected TableColumn<Post, Integer> idc;
	protected TableColumn<Post, String> numec;
	protected TableColumn<Post, String> tipc;
	

	
	@SuppressWarnings("unchecked")
	/**
	 * 
	 * @return CenterPane
	 * cream tabelul cu 3 coloane pt campuri care va fi pozitionat in centru
	 * cand un item va fi selectat datele vor fi incarcate intr un text field
	 */
	private Node createCenterPane(){
		table = new TableView<>();
        idc = new TableColumn<> ("Id");
        numec = new TableColumn<>("Nume");
        tipc = new TableColumn<>("Tip");
        
        idc.setCellValueFactory(new PropertyValueFactory<Post, Integer>("id"));
        numec.setCellValueFactory(new PropertyValueFactory<Post,String >("nume"));
        tipc.setCellValueFactory(new PropertyValueFactory<Post,String >("tip"));
        
        table.getColumns().addAll(idc, numec, tipc);

		table.setItems(ctr.getModel());
		
	    table.getSelectionModel().selectedItemProperty().addListener(new
	    		ChangeListener<Post>() {
	    		public void changed(ObservableValue<? extends Post> observable, Post
	    		oldValue, Post newValue) {
	    		if (newValue != null){
		    		try{

		    		numeText.setText(newValue.getNume());
		    		tipText.setText(newValue.getTip());
		    		}catch(NullPointerException e){
		    		}
	    		}
    		}
		});
		return table;
	}
	
	/**
	 * cream partea de deasupra a interfetei
	 * contine cate un label si text field pt fiecare atribut
	 * @return gridpane
	 */
	private Pane createTopPane(){
		numeText = new TextField();
		tipText = new TextField();

		nume = new Label("Nume");
		tip = new Label("Tip");
		
		
		GridPane pane = new GridPane();
		pane.setVgap(5);
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(0, 0, 10, 0));

		pane.add(numeText, 1, 1);
		pane.add(tipText, 1, 2);

		pane.add(nume, 0, 1);
		pane.add(tip, 0, 2);
		
		return pane;
	}
	
	/**
	 * cream bara cu butoane si pt fiecare buton setam handlelul
	 * @return butoanele
	 */
    private Pane createRightPane () {
		GridPane pane1 = new GridPane();
		
        save = new Button("Save");
        update = new Button("Update");
        delete = new Button("Delete");
        viewAll = new Button("ViewAllTasks");
        addTask = new Button("AddTask");
        viewAllPosts = new Button("ViewAll");
        filtrBynume = new Button("FilterByName");
        filterBytip = new Button("FilterByTip");

        ButtonBar bar = new ButtonBar();
        bar.setPadding(new Insets(10));
        bar.getButtons().addAll(save,delete,update);
        
        ButtonBar bar1 = new ButtonBar();
        bar.setPadding(new Insets(10));
        bar1.getButtons().addAll(viewAll,addTask);
        
        ButtonBar bar2 = new ButtonBar();
        bar.setPadding(new Insets(10));
        bar2.getButtons().addAll(viewAllPosts,filtrBynume, filterBytip );

        save.setOnAction(ctr.addHandler());
        delete.setOnAction(ctr.removeHandler());
        update.setOnAction(ctr.updateHandler());
        viewAll.setOnAction(ctr.openWindowHandler());
        addTask.setOnAction(ctr.handleAddTask());
        viewAllPosts.setOnAction(ctr.viewAllPostsHandler());
        filterBytip.setOnAction(ctr.filterByTipHandler());
        filtrBynume.setOnAction(ctr.filterByNameHandler());
        pane1.add(bar, 0, 0);
        pane1.add(bar2, 0, 2);
        pane1.add(bar1, 0, 1);
        
		pane1.setVgap(10);
		pane1.setHgap(10);
		pane1.setAlignment(Pos.CENTER);
		pane1.setPadding(new Insets(5));
        return pane1;
    }

    /**
     * 
     * @return pane
     */
	public Parent getPane() {
		return pane;
	}
	
	/**
	 * pozitionam coponentele in vbox
	 */
	public void init(){
		pane = new VBox(createTopPane(),createCenterPane(),createRightPane());
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(5, 10, 10, 10));
	}
	

	/**
	 * setam controllerul
	 * @param ctr1
	 */
	public void setController(PostController ctr1){
		ctr = ctr1;
	}
}
