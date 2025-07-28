import java.util.ArrayList;
import java.util.List;

public class tree {
    
    // create a node class
    public class Node{
        private String name;             // Name of the node

        //constructor
        public Node(String name){
            this.name = name;
        }

        // get name
        public String getName(){return name;}

        // set name
        public void setName(String name){this.name = name;}

    }

    //file class
    public class File extends Node{
        private String fileData;
        
        //constructor
        public File(String name, String fileData){
            super(name);
            this.fileData = fileData;
        }
        public File(String name){
            super(name);
            fileData = "";
        }

        //get name
        public String getData(){return fileData;}

        //set data
        public void seteData(String fileData){
            // insert nano function here
            System.out.println("Nano function unavailable at this moment");
        }
    }

    public class Folder extends Node{
        private List<Node> contents;
        private Folder parent;

        //constructor
        public Folder(String name){
            super(name);
            this.contents = new ArrayList<>();
        }
        
        // Constructor with parent
        public Folder(String name, Folder parent){
            super(name);
            this.contents = new ArrayList<>();
            this.parent = parent;
        }

        //get contents
        public List<Node> getContents(){return contents;}

        //addContents
        public void addContents(Node node) {
            this.contents.add(node);
        }

        //remove contents
        public boolean removeContents(Node node){ // boolean since .remove has a boolean return type
            return this.contents.remove(node);
        }

        //remove all subContents
        public void removeSubContents(){
            this.contents.clear();
        }

        //print contents
        public void printContents(){
            for (int i = 0;i < contents.size();i++){
                System.out.print(contents.get(i).getName() + " ");
                System.out.println();
            }
        }

        // Getter for parent
        public Folder getParent() {
            return parent;
        }
    }

    // Backend Class
    public class Backend {
        private Node root;

        //constructor
        public Backend(Node root){
            this.root = root;
        }

        //get root
        public Node getRoot(){return root;}

        // Print Tree
        public void printTree() {
            printTreeHelper(root, 0);
        }

        private void printTreeHelper(Node node, int depth) {
            if (node == null) return;
            for (int i = 0; i < depth; i++) {
                System.out.print("  ");
            }
            System.out.println(node.getName());
            if (node instanceof Folder) {
                for (Node child : ((Folder) node).getContents()) {
                    printTreeHelper(child, depth + 1);
                }
            }
        }

    }

}// end of tree class