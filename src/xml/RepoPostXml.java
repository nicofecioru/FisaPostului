package xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Entitate.Post;
import Repository.Repository;
import Validator.MyException;


/**
 * Created by arni on 11/29/16.
 */
public class RepoPostXml extends Repository<Post>{

    private String filename;
    public RepoPostXml(String filename) {
        super();
        this.filename = filename;
        try {
			loadData();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void saveData()  {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc=null;

        try {
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            doc = docBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Node root=doc.createElement("posts");
        doc.appendChild(root);
        for( Post p: all){
            Element postEl=doc.createElement("post");
            root.appendChild(postEl);
            getElementFromPostField(postEl,doc,"id",p.getId().toString());
            getElementFromPostField(postEl,doc,"nume",p.getNume().toString());
            getElementFromPostField(postEl,doc,"tip",p.getTip().toString());

        }
        TransformerFactory factory1=TransformerFactory.newInstance();
        Transformer transformer=null;
        try {

            transformer=factory1.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        DOMSource source=new DOMSource(doc);
        StreamResult result=new StreamResult(filename);
        try {
            transformer.transform(source,result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
    
    /**
     * 
     * @param el elementul
     * @param doc documentul
     * @param tagName atributul din fisier
     * @param value atributul obiectului
     */
    private void getElementFromPostField(Element el,Document doc,String tagName,String value){
        Element e= doc.createElement(tagName);
        e.setTextContent(value);
        el.appendChild(e);
    }

    /**
     * 
     * @param e elementul curent
     * @return postul 
     */
    private Post getPostFromEl(Element e){
        Post post=new Post();
        post.setId(Integer.parseInt(e.getElementsByTagName("id").item(0).getTextContent()));
        post.setNume(e.getElementsByTagName("nume").item(0).getTextContent());
        post.setTip(e.getElementsByTagName("tip").item(0).getTextContent());

        return post;
    }
    
    /**
     * incarca in repo datele din fisier
     * @throws MyException
     */
    public void loadData() throws MyException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc=null;
        try {
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            doc = docBuilder.parse(new FileInputStream(filename));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node root=doc.getDocumentElement();

        NodeList nodeList=root.getChildNodes();
        for (int i=0;i<nodeList.getLength();i++){
            if(nodeList.item(i).getNodeType()==Node.ELEMENT_NODE){
                Element e=(Element)nodeList.item(i);

                Post post=getPostFromEl(e);
                addEl(post);
            }
        }
    }

}
