package GUI;


import java.io.IOException;

import javax.xml.bind.ValidationException;

import Controller.ControllerPost;
import Controller.ControllertPostTask;
import Entitate.Post;
import Entitate.Sarcina;
import Observer.Observer;
import Validator.MyException;
import fxml.TaskController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PostController implements Observer{
    private PostView postView;
    private ControllerPost postCtr;
    private ControllertPostTask relCtr;
	PostModel model ;
	private TaskController ctrTask;

    public PostController(PostView postView, ControllerPost postCtr, PostModel model, ControllertPostTask relCtr) {
        this.postView = postView;
        this.postCtr = postCtr;
        this.model = model;
        postView.setController(this);
        this.relCtr=relCtr;
    }
    

    /*
     * handlerul pt add. adaugam in lista postul cu atributele din textfield uri daca sunt corecte datele
     */
    
    public EventHandler<ActionEvent> addHandler(){
        EventHandler<ActionEvent> evAdd = new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent event) {
                try {
	                String nume = postView.numeText.getText();
	                String tip = postView.tipText.getText();
	                
					postCtr.addPost(nume,tip);
					update();
				} catch (ValidationException e) {
					Alert a = new Alert(AlertType.ERROR);
					a.setContentText(e.toString());
					a.showAndWait();
				} catch (MyException e) {
					Alert a = new Alert(AlertType.ERROR);
					a.setContentText(e.toString());
					a.showAndWait();
				} catch (Exception e) {
					Alert a = new Alert(AlertType.ERROR);
					a.setContentText("Id ul trbuie sa fie intreg ");
					a.showAndWait();
				}
            }
        };
        return evAdd;
    }

    /*
     * handlerul pt update modificam in lista postul selectat cu atributele din textfield uri daca sunt corecte datele si avem un post selectat
     */
    public EventHandler<ActionEvent> updateHandler(){
        EventHandler<ActionEvent> evAdd = new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent event) {
                try {

	                String nume = postView.numeText.getText();
	                String tip = postView.tipText.getText();               
					postCtr.updatePost(postView.table.getSelectionModel().getSelectedItem().getId(),nume,tip);
					update();
					//relCtr.update();
					ctrTask.getCtr().notifyObservers();
				} catch (ValidationException e) {
					Alert a = new Alert(AlertType.ERROR);
					a.setContentText(e.toString());
					a.showAndWait();
				} catch (MyException e) {
					Alert a = new Alert(AlertType.ERROR);
					a.setContentText(e.toString());
					a.showAndWait();
				} catch (NullPointerException e){
					Alert a = new Alert(AlertType.ERROR);
					a.setContentText("Trebuie sa selectati un post");
					a.showAndWait();
				} catch (Exception e) {
					Alert a = new Alert(AlertType.ERROR);
					a.setContentText("Id ul nu poate fi vid");
					a.showAndWait();
				}            
            }
        };
        return evAdd;
    }
    
    /**
     * afiseaza toate posturile
     * @return evenimentul
     */
    public EventHandler<ActionEvent> viewAllPostsHandler(){
        EventHandler<ActionEvent> evAdd = new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent event) {
            	update();
            }
        };
        return evAdd;
    }
    
    /**
     * afiseaza toate posturile filtrate dupa nume
     * @return eveniment
     */
    public EventHandler<ActionEvent> filterByNameHandler(){
        EventHandler<ActionEvent> evAdd = new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent event) {
            	model.update(postCtr.filterPostbyDen(postView.numeText.getText()));
            }
        };
        return evAdd;
    }
    
    /**
     * afiseaza poate posturile filtrate dupa tip
     * @return
     */
    public EventHandler<ActionEvent> filterByTipHandler(){
        EventHandler<ActionEvent> evAdd = new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent event) {
            	model.update(postCtr.filterPostbyTip(postView.tipText.getText()));
            }
        };
        return evAdd;
    }
    
	/**
	 * adauga o sarcina selectata din lista de sarcini
	 * @return 
	 */

	public EventHandler<ActionEvent> handleAddTask() {
		EventHandler<ActionEvent> evAdd = new EventHandler<ActionEvent> (){
			public void handle(ActionEvent event){
				try {
					if (ctrTask.getS()!=null) {
						Post p = postView.table.getSelectionModel().getSelectedItem();
						relCtr.addRel(postView.table.getSelectionModel().getSelectedItem(),ctrTask.getS());
						//model.setAll(relCtr.showAll(p.getId()));
						ctrTask.getCtr().notifyObservers();
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
		};
		return evAdd;
	}
    
    /**
     * 
     *  handlerul delete pt stergem din lista postul selectat daca avem un post selectat
     */
    public EventHandler<ActionEvent> removeHandler(){
        EventHandler<ActionEvent> evAdd = new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent event) {
                try {
					postCtr.removePost(postView.table.getSelectionModel().getSelectedItem().getId());
					//relCtr.update();
					ctrTask.getCtr().notifyObservers();
					update();
				} catch (Exception e) {
					Alert a = new Alert(AlertType.ERROR);
					e.printStackTrace();;
					a.setContentText("Trebuie sa selectati un post");
					a.showAndWait();
				};
            }
        };
        return evAdd;
    }
    

   /**
    * deschide o noua fereastra cu postul selectat
    * @return
    */
    public EventHandler<ActionEvent> openWindowHandler(){
        EventHandler<ActionEvent> evAdd = new EventHandler<ActionEvent> (){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
		        FXMLLoader fxml = new FXMLLoader();

		        fxml.setLocation(getClass().getResource("TaskSarcina.fxml"));

		        Pane pane;
				try {

			   
			        if (postView.table.getSelectionModel().getSelectedItem()!=null){
						pane = fxml.load();			
				        RelCtr ctrl = fxml.getController();
				        Scene scene = new Scene(pane);
				        Stage stage = new Stage();
				        stage.setScene(scene);
			        	Post p = postView.table.getSelectionModel().getSelectedItem();
			        	stage.setTitle(p.toString());
			        	ObservableList<Sarcina> modelTask= FXCollections.observableArrayList(relCtr.showAll(p.getId()));
			        	ctrl.setCtr(relCtr, stage);
			        	ctrl.setModel(modelTask);
			        	ctrl.setP(p);
			        	ctrl.setS(ctrTask);
			        	ctrTask.getCtr().addObserver(ctrl);
				        stage.show();

			        }
			        
			        else {
						Alert a = new Alert(AlertType.ERROR);

						a.setContentText("Trebuie sa selectati un post");
						a.showAndWait();  	
			        }
		
		        
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
        };
        return evAdd;
    }
	  
    /**
     * 
     * @return modelul
     */
    public ObservableList<Post> getModel() {
    	return this.model.getModel();
    }
    
    
    /**
     * reincarca lista
     * @return 
     */
    @Override
    public  void update() {
    	
    	this.model.update(this.postCtr.getList());

    }


	public void setSarcinaCtr(TaskController ctrTask) {
		this.ctrTask=ctrTask;
		
		
	}
}
