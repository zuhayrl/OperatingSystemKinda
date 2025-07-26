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

        //constructor
        public Folder(String name){
            super(name);
            this.contents = new ArrayList<>();
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
        
        
    }
}
