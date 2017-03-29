package fxml;

import javax.xml.bind.ValidationException;

import Controller.ControllerSarcina;
import Validator.MyException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TaskWindowCtr {
	
	private ControllerSarcina ctr;
	
	private TaskController taskCtr;
	
	@FXML
	private Button add;
	
	@FXML
	private Button update;
	

	@FXML
	private TextField descr;
	
	@FXML
	private TextField time;
	
	int updateid = -1;
	
	Stage stage;
	
	/**
	 * adauga o sarcina cu atributele din text field uri daca sunt corecte
	 */
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
			stage.close();
		}
	}
	
	/**
	 * handlerul pt update modificam in lista sarcina selectata cu atributele din textfield uri daca sunt corecte datele si avem o sarcina selectata
	     
	 */
	@FXML 
	public void handleUpdate(){
			try {
				ctr.updateTask(updateid, descr.getText(), Integer.parseInt(time.getText()));
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
	
	
	@FXML
	public void initialize(){
		
	}
	
	public ControllerSarcina getCtr(){
		return ctr;
	}
	
	public void setId(Integer idT, String descrT, Integer timeT){
		updateid=idT;
		//id.setText(idT.toString());
		descr.setText(descrT);
		time.setText(timeT.toString());
	}
	
    public void setCtr( ControllerSarcina ctr, Stage stage, TaskController ctr1) {
        this.ctr = ctr;
        this.stage = stage;
        taskCtr=ctr1;
    }
    
    
}
