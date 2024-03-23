import java.io.IOException;
import java.util.Scanner;

public class SearchEngine {

    public static void main(String[] args) throws IOException {

        int choice;
        Scanner scan = new Scanner(System.in);
        LinkedList l = new LinkedList();

        do{
            System.out.println("Select a method or exit.");
            System.out.println("1. Load file.");
            System.out.println("2. Search strings.");
            System.out.println("3. Remove a document.");
            System.out.println("4. Clear list.");
            System.out.println("5. Exit");
            choice = Integer.parseInt(scan.nextLine());

            if(choice == 1){
                System.out.print("Enter input filepath: ");
                String filepath = scan.nextLine();
                l.load(filepath);
                System.out.println("Words added to list successfully!");
            }
            if(choice == 2){
                System.out.print("Enter query for desired and undesired(starts with '!') words(use comma to separate words): ");
                String query = scan.nextLine();
                l.search(query);
                System.out.println("Documents compatible with the query were written to the output file!");
            }
            if(choice == 3){
                System.out.print("Enter document name to delete: ");
                String docName = scan.nextLine();
                l.remove(docName);
                System.out.println("Document deleted!");
            }
            if(choice == 4){
                l.clearList();
                System.out.println("List cleared!");
            }
        }while(choice != 5);
        scan.close();
    }
}