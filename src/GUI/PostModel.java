package GUI;

import java.util.List;

import Entitate.Post;
import Repository.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PostModel {


    private ObservableList<Post> model;

    /**
     * 
     * @return modelul
     */
    public ObservableList<Post> getModel() {
        return model;
    }
 
    /**
     *setam modelul ca find observeblelist din lista din repo 
     * @param repo
     */
    public PostModel(Repository<Post> repo) {
        model = FXCollections.observableArrayList((List<Post>)repo.getAll());
    }
    
    /**
     * setam datele ca fiind din noua lista
     * @param list
     */
    public void update(List<Post> list) {
    	model.setAll(list);
    }
}

