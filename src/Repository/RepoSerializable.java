package Repository;

import java.io.*;
import java.util.ArrayList;

import Entitate.Sarcina;

public class RepoSerializable<E extends HasId> extends Repository<E> {
	private String fileName;

    public RepoSerializable(String fileName) {
        super();
        this.fileName = fileName;

        try
        {
            loadData();
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
	public void loadData() throws FileNotFoundException
    {
        try
        {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName));
            all = (ArrayList<E>) input.readObject();
            input.close();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void saveData() {
        try
        {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName));
            output.writeObject(all);
            output.close();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

}
