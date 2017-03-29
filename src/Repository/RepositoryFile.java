package Repository;

import java.io.*;
import java.util.StringTokenizer;

import Entitate.Post;
import Validator.MyException;

public class RepositoryFile extends Repository<Post > {
    private String fileName;

    public RepositoryFile(String fileName) throws MyException {
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

    /*
     * incarca datele din fisier in repository
     */
    private void loadData() throws FileNotFoundException, MyException{
        try {
            String line;
            BufferedReader reader = new BufferedReader( new InputStreamReader(new FileInputStream(fileName)));
            do {
                line = reader.readLine();
                if (line != null)
                {
                    StringTokenizer st = new StringTokenizer(line, "|");
                    if (st.countTokens() == 3){
                    	try{
                    		int a = Integer.parseInt(st.nextToken());
                    		Post post = new Post(a, st.nextToken(), st.nextToken());
    	                    addEl(post);
                    	}
                    	catch (Exception e){
                    		System.out.println(e);
                    	}
  
                    }
                }
            } while (line != null);
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * scrie datele din repository in fisier
     */
    public void saveData(){
        try
        {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
            for (Post post : all)
            {
                String line = post.getId().toString() + "|" + post.getNume() +"|" + post.getTip() + "\n";
                writer.write(line);
            }
            writer.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            try {
				throw e;
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}

