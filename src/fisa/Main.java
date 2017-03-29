package fisa;

import Controller.ControllerPost;
import Controller.ControllerSarcina;
import Controller.ControllertPostTask;
import Entitate.ElementSarcina;
import Entitate.Sarcina;
import GUI.PostController;
import GUI.PostModel;
import GUI.PostView;
import Repository.RepoSerializable;
import fxml.TaskController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import xml.RepoPostXml;

public class Main extends Application{
	@Override
	public void start(Stage stage) throws Exception {
		Test t = new Test();
		t.testPost();
		t.testSarcina();
		t.testVector();
		t.testRepo();

		RepoSerializable<Sarcina> repTask = new RepoSerializable<Sarcina>("src/task.txt");
		RepoSerializable<ElementSarcina> repRel= new RepoSerializable<ElementSarcina>("src/rel.xt");
		RepoPostXml rep = new RepoPostXml("src/xml/post.xml");
		
		ControllerSarcina ctrTask = new ControllerSarcina(repTask);
		ControllerPost ctrPost = new ControllerPost(rep);
		ControllertPostTask ctrRel = new ControllertPostTask(repRel, rep, repTask);
		

        FXMLLoader loader=new FXMLLoader(Main.class.getResource("/fxml/task.fxml"));
        BorderPane rootLayout= (BorderPane) loader.load();       
        TaskController taskCtr = loader.getController();
        taskCtr.setCtr(ctrTask);
        taskCtr.setCtrRel(ctrRel);
  		
		PostModel m = new PostModel(rep);
		PostView postView = new PostView();
		PostController ctrui = new PostController(postView, ctrPost, m, ctrRel);
		postView.init();
		ctrui.setSarcinaCtr(taskCtr);
			
		Parent root = postView.getPane();
		HBox h = new HBox(root, rootLayout);
        Scene scene = new Scene(h);
		stage.setScene(scene);
		stage.show();
		
        ctrTask.addObserver(taskCtr);
        ctrTask.addObserver(ctrRel);
		ctrTask.addObserver(ctrui);
		
	}

	public static void main (String a[]) throws Exception{

		launch(a);



	}

}
