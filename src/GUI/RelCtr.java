package GUI;

import java.util.ArrayList;

import Controller.ControllertPostTask;
import Entitate.Post;
import Entitate.Sarcina;
import Observer.Observer;
import Validator.MyException;
import fxml.TaskController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class RelCtr implements Observer {

	@FXML
	private TableView<Sarcina> table ;
	
	@FXML
	private Button deleteBtn;
	
	@FXML
	private Button addBtn;
	
	@FXML
	private TableColumn<Sarcina,Integer> idText;
	
	@FXML
	private TableColumn <Sarcina,String> descrText;
	
	@FXML
	private TableColumn <Sarcina,Integer> timeText;
	
	ControllertPostTask ctr;
	
	Stage stage;
	
	ObservableList<Sarcina> model;
	
	
	public TaskController getS() {
		return s;
	}

	public void setS(TaskController s) {
		this.s = s;
	}

	public Post getP() {
		return p;
	}

	public void setP(Post p) {
		this.p = p;
	}

	TaskController s;
	
	Post p;
	
	
	public ObservableList<Sarcina> getModel() {
		return model;
	}
	
	/**
	 * setam controller ul grasp si scena
	 * @param ctr
	 * @param stage
	 */
	public void setCtr(ControllertPostTask ctr, Stage stage){
		this.ctr=ctr;
		this.stage = stage;
	}

	/**
	 * completeaza tabelul cu sarcini
	 * @param model lista observabila de sarcini
	 */
	public void setModel(ObservableList<Sarcina> model) {
		this.model = model;
		table.setItems(model);
	}


	
	@FXML 
	public void initialize(){
		idText.setCellValueFactory(new PropertyValueFactory <Sarcina, Integer>("id"));
		descrText.setCellValueFactory(new PropertyValueFactory <Sarcina, String>("descriere"));
		timeText.setCellValueFactory(new PropertyValueFactory < Sarcina, Integer> ("durata"));		
	}
	
	/**
	 * reincarca datele din tabel cu lista l
	 * @param l
	 */
	public void update(ArrayList<Sarcina> l){
		model.setAll(l);
	}
	
	/**
	 * inchide fereastra
	 */
	public void closeStage(){
		stage.close();
	}
	
	/**
	 * adauga o sarcina selectata din lista de sarcini
	 */
	@FXML 
	public void handleAdd() {
		try {
			if (s.getS()!=null) {
			
				ctr.addRel(p,s.getS());
				model.setAll(ctr.showAll(p.getId()));
			} else{
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("Trebuie sa selectati o sarcina");
				a.showAndWait();
			} 
		} catch (MyException e){
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(e.toString());
			a.showAndWait();
			
		}
	}
    
	/**
     * sterge item ul selectat daca exista un item selectat
     * @throws MyException 
     */
    @FXML
    public void handleDelete() throws MyException{
       try {
			ctr.removeRel(p.getId(), table.getSelectionModel().getSelectedItem().getId());
			model.setAll(ctr.showAll(p.getId()));
			
		} catch (Exception e) {

			e.printStackTrace();
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Trebuie sa selectati o sarcina");
			a.showAndWait();

		};
    }

    /**
     * inchide fereastra daca postul nu mai exista
     * schimba titlul cu cel curent
     * reincarca lista de sarcini
     */
	@Override
	public void update() {
		if (ctr.findPost(p.getId()) == null){
			stage.close();
		}
		
		else {
			setP(ctr.findPost(p.getId()));
			stage.setTitle(p.toString());
		}
		model.setAll(ctr.showAll(p.getId()));
		
	}
	
	
}
