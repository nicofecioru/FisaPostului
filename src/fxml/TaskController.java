package fxml;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.bind.ValidationException;


import Controller.ControllerSarcina;
import Controller.ControllertPostTask;
import Entitate.Sarcina;
import GUI.RelCtr;
import Observer.Observer;
import Validator.MyException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskController implements Observer {
	

    private static final String PDF_PATH = "E:/export.pdf";
    
	@FXML
	private TableView<Sarcina> table ;
	
	@FXML
	private Button deleteBtn;
	
	@FXML
	private Button addBtn;
	
	@FXML
	private Button updateBtn;
	
	@FXML
	private Button filterDescrBtn;
	
	@FXML
	private Button filterTimeBtn;
	
	@FXML
	private Button showAllBtn;
	
	@FXML
	private Button raports;
	
	@FXML
	private TableColumn<Sarcina,Integer> idText;
	
	@FXML
	private TableColumn <Sarcina,String> descrText;
	
	@FXML
	private TableColumn <Sarcina,Integer> timeText;


	@FXML
	private TextField descr;
	
	@FXML
	private TextField time;
	
	ControllerSarcina ctr;
	
	Hashtable<Integer, Integer> top3;
	
	 ArrayList<PieChart.Data> dataArray;
	 
	 ObservableList<PieChart.Data> pieChartData;
	
	public ControllertPostTask getCtrRel() {
		return ctrRel;
	}

	public void setCtrRel(ControllertPostTask ctrRel) {
		this.ctrRel = ctrRel;
	}


	ControllertPostTask ctrRel;
	
	public ControllerSarcina getCtr() {
		return ctr;
	}


	ObservableList<Sarcina> model;
	
	public Sarcina getS() {
		return table.getSelectionModel().getSelectedItem();
	}

	public void setS(Sarcina s) {
		this.s = s;
	}


	private Sarcina s;
	
	
	
	public RelCtr getRelCtr() {
		return relCtr;
	}

	public void setRelCtr(RelCtr relCtr) {
		this.relCtr = relCtr;
	}


	RelCtr relCtr;
	
	/**
	 * seteaza tipul coloanelor
	 */
	@FXML
	private void initialize() {
		idText.setCellValueFactory(new PropertyValueFactory<Sarcina,Integer>("id"));
		descrText.setCellValueFactory(new PropertyValueFactory<Sarcina,String>("descriere"));
		timeText.setCellValueFactory(new PropertyValueFactory<Sarcina,Integer>("durata"));
	    table.getSelectionModel().selectedItemProperty().addListener(new
	    		ChangeListener<Sarcina>() {
	    		public void changed(ObservableValue<? extends Sarcina> observable, Sarcina
	    		oldValue, Sarcina newValue) {
	    		if (newValue != null) {
		    		try{
		    		s=newValue;
		    		descr.setText(newValue.getDescriere());
		    		time.setText(newValue.getDurata().toString());
		    		}catch(NullPointerException e){}
	    		}}});
	}
	
	/**
	 * 
	 * @param ctr controller grasp
	 * incarca datele in tabel cu datele din lista din controller
	 * 
	 */
    public void setCtr( ControllerSarcina ctr) {
        this.ctr = ctr;

        model = FXCollections.observableArrayList((List<Sarcina>)ctr.getList());
        table.setItems(model);
    }
    
    /**
     * sterge item ul selectat daca exista un item selectat
     */
    @FXML
    public void handleDelete(){
        try {
			ctr.removeTask(table.getSelectionModel().getSelectedItem().getId());
			setCtr(ctr);
			
		} catch (Exception e) {

			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Trebuie sa selectati o sarcina");
			a.showAndWait();

		};
    }
    
    /**
     * afiseaza posturile filtrate dupa descriere
     */
    @FXML
    public void handleFiltrDescr(){
        
    	model.setAll(ctr.filterTaskbyDescr(descr.getText()));
    	ctr.filterTaskbyDescr(descr.getText()).forEach(x->System.out.println(x));

    }
    
    /**
     * afiseaza posturile filtrate dupa durata
     */
    @FXML
    public void handleFiltrTime(){
        
    	try {
	    	model.setAll(ctr.filterTaskbyDurata(Integer.parseInt(time.getText())));
	    	ctr.filterTaskbyDescr(descr.getText()).forEach(x->System.out.println(x));
		} catch (Exception e) {

			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Durata trebuie sa fie un numar");
			a.showAndWait();

		};

    }

    
    /**
     * creaza o fereastra cu un piechart cu cele mai solicitate sarcini
     */
    public void viewRaport() {
		TextField dim = new TextField("3");
		Label l = new Label("Numar");
		HBox h = new HBox(l, dim);
		h.setPadding(new Insets(20));
		h.setSpacing(20);

		Scene scene = new Scene(new Group());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Raports");
		stage.setWidth(500);
		stage.setHeight(500);

		top3 = ctrRel.getTopN(Integer.parseInt(dim.getText()));
		dataArray = new ArrayList<PieChart.Data>();
		for (Integer key : top3.keySet()) {
			dataArray.add(new PieChart.Data(ctr.findTask(key).getId() + " " + ctr.findTask(key).getDescriere() + " (" + top3.get(key) + ")", top3.get(key)));
		}
		pieChartData = FXCollections.observableArrayList(dataArray);
		final PieChart chart = new PieChart(pieChartData);
		chart.setTitle("Cele mai solicitate sarcini");


		dim.textProperty().addListener(
				new ChangeListener<String>() {
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						if (!newValue.matches("\\d+")) {
							dim.setText(newValue.replaceAll("[^\\d]", ""));
						}
						try {
							top3 = ctrRel.getTopN(Integer.parseInt(dim.getText()));
							dataArray = new ArrayList<PieChart.Data>();
							for (Integer key : top3.keySet()) {
								dataArray.add(new PieChart.Data(ctr.findTask(key).getId() + " " + ctr.findTask(key).getDescriere() + " (" + top3.get(key) + ")", top3.get(key)));
							}
							pieChartData.setAll(dataArray);
						} catch (Exception e) {
						}
					}
				});


		VBox v = new VBox(h, chart);
		((Group) scene.getRoot()).getChildren().add(v);




        stage.setScene(scene);
        stage.show();
    
    }

    /**
     * afiseaza toate sarcinile
     */
	@Override
	public void update() {
		model.setAll(ctr.getList());
		
	}

	@FXML 
	public void handleAdd(){
		try {
			ctr.addTask( descr.getText(), Integer.parseInt(time.getText()));
			//taskCtr.update(ctr);
		} catch (ValidationException e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(e.toString());
			a.showAndWait();
		} catch (Exception e){
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(e.toString()/*"Id ul si durata trebuie sa fie intregi"*/);
			a.showAndWait();
		} finally{

		}
	}



	
	/**
	 * handlerul pt update modificam in lista sarcina selectata cu atributele din textfield uri daca sunt corecte datele si avem o sarcina selectata
	     
	 */
	@FXML 
	public void handleUpdate(){
			try {
				ctr.updateTask(table.getSelectionModel().getSelectedItem().getId(), descr.getText(), Integer.parseInt(time.getText()));
			} catch (ValidationException e) {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText(e.toString());
				a.showAndWait();
			} catch (MyException e) {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText(e.toString());
				a.showAndWait();
			}catch (NumberFormatException e) {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("Id ul si durata trebuie sa fie intregi");
				a.showAndWait();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		 /*catch (Exception e){
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(printStakTrace()/*"Id ul si durata trebuie sa fie intregi");
			a.showAndWait();
		}*/
	}
		
	    
    }
    


