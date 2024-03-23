import java.util.ArrayList;
import java.util.List;

public class Node {
    public String data;
    public Node next;
    List<String> documents;

    public Node() {
        next = null;
        data = null;
        documents = null;
    }
    public Node(String d, String docName){
        data=d;
        next=null;
        this.documents = new ArrayList<>();
        this.documents.add(docName);
    }
}