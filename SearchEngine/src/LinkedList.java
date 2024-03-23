import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LinkedList {

    Node head;
    Node walk;
    boolean flag;

    public LinkedList(){
        head = null;
        walk = null;
    }

    public void addWord(String word, String docName){
        word = word.replaceAll("^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", "");//remove punctuation
        walk = new Node(word, docName);
        if(head == null || word.compareTo(head.data) < 0){
            walk.next = head;
            head = walk;
        }
        else{
            Node track = head;
            while(track.next != null && word.compareTo(track.next.data) > 0){
                track = track.next;
            }
            //word already exist in linked list
            if(track.next != null && word.equals(track.next.data)){
                if (track.next.documents == null) {
                    track.next.documents = new ArrayList<>();
                }
                track.next.documents.add(docName);
            }
            //new word
            else{
                Node newNode = new Node(word, docName);
                newNode.next = track.next;
                track.next = newNode;
            }
        }
    }

    public void clearList(){
        walk = head;
        head = null;
        Node track;
        while(walk != null){
            track = walk;
            walk = null;
            walk = track.next;
        }
    }

    public void load(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        String word;
        String docName = "";
        Scanner scan = new Scanner(f);
        flag = true;

        while(scan.hasNext()){
            if(flag){
                docName = scan.nextLine().toLowerCase();//if flag is true read new doc's name
                while(docName.equals("")){
                    docName = scan.nextLine().toLowerCase();
                }
            }
            word = scan.next().toLowerCase();
            if(word.equals("####")){
                flag = !flag;//change flag value at ####
            }
            else{
                addWord(word, docName);//if flag is false add next word to list
            }
        }
        scan.close();
    }

    public void search(String query) throws IOException {
        String[] allWords = query.split(", ");
        List<String> desiredWords = new ArrayList<>();
        List<String> undesiredWords = new ArrayList<>();
        List<String> documents = new ArrayList<>();

        for(String word : allWords){
            if(word.startsWith("!")){
                undesiredWords.add(word.substring(1));
            }
            else{
                desiredWords.add(word);
            }
        }

        for(String desiredWord : desiredWords){
            walk = head;
            while(walk != null){
                if(walk.data.equals(desiredWord)){
                    if(documents.size() == 0){
                        documents.addAll(walk.documents);
                    }
                    else{
                        documents.retainAll(walk.documents);
                    }
                }
                walk = walk.next;
            }
        }

        for(String undesiredWord : undesiredWords){
            walk = head;
            while(walk != null){
                if(walk.data.equals(undesiredWord)){
                    documents.removeAll(walk.documents);
                }
                walk = walk.next;
            }
        }
        writeOutput(documents);
    }

    public void remove(String document){
        walk = head;
        Node prev = null;

        while(walk != null){
            if(walk.documents.contains(document)){
                walk.documents.remove(document);
                if(walk.documents.size() == 0){
                    if(prev == null){
                        head = walk.next;
                    }
                    else{
                        prev.next = walk.next;
                    }
                }
            }
            prev = walk;
            walk = walk.next;
        }
    }

    public void writeOutput(List<String> documents) throws IOException {
        File file = new File("out.txt");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter buffW = new BufferedWriter(fileWriter);

        for(String docName : documents){
            buffW.write(docName + "\n");
        }
        buffW.close();
    }
}