import java.util.*;

public class main {
    
    static Scanner kb = new Scanner(System.in);
    static boolean running = true;

    public static void main(String[] args) {

        //functions for OS functionality
        /*
         * cd: move into folder
         * ls: list folder contents
         * cat: print file contents
         * pwd: print working directory
         * cd ~: go to home directory
         * mkdir
         * rmdir
         * cp
         * mv
         * rm
         * nano
         * clear
         * exit
         */

        

        System.out.println("Welcome to Useless OS");
        System.out.println("Type 'exit' to quit, 'help' for commands");
        
        // Create the tree system and set up initial directory structure
        tree treeSystem = new tree();
        tree.Folder root = treeSystem.new Folder("root");
        tree.Folder currentFolder = root;
        
        // Main OS loop
        while (running) {
            System.out.print(currentFolder.getName() + "$ ");
            String input = kb.nextLine().trim();
            
            if (!input.isEmpty()) {
                commandCheck(input, currentFolder, treeSystem);
            }
        }



    }

    //functions for each command
    public static void commandCheck(String line, tree.Folder currentFolder, tree treeSystem){
        List<String> strings = Arrays.asList(line.split(" "));
        String command = strings.get(0);
        switch (command) {
            case "ls":
                // ls code
                currentFolder.printContents();
                break;
        
            case "cd":
                // cd code
                //String directory = strings.get(1);
                System.out.println("In progress");
                break;

            case "cat":
                // cat code
                boolean exists = false;
                List<tree.Node> contents = currentFolder.getContents();
                for (int i=0; i<contents.size(); i++){
                    if (contents.get(i) instanceof tree.File) { // check if file is in folder
                        tree.File file = (tree.File) contents.get(i);
                        if (file.getName().equals(strings.get(1))) {
                            exists = true;
                            System.out.println(file.getData());
                            break;
                        }
                    }
                }

                if (!exists){
                    System.out.println("File not found.");
                }
                break;

            case "pwd":
                // pwd code
                System.out.println(currentFolder.getName());
                break;

            case "mkdir":
                // create folder
                if (strings.size() > 1) {
                    String newFolderName = strings.get(1);
                    tree.Folder newFolder = treeSystem.new Folder(newFolderName, currentFolder);
                    currentFolder.addContents(newFolder);
                    System.out.println("Folder '" + newFolderName + "' created.");
                } else {
                    System.out.println("Usage: mkdir <foldername>");
                }
                break;

            case "nano":
                //create file
                if (strings.size() > 1) {
                    String newFileName = strings.get(1);
                    String newFileData = strings.get(2);
                    tree.File newFile = treeSystem.new File(newFileName, newFileData);
                    currentFolder.addContents(newFile);
                    System.out.println("Folder '" + newFileName + "' created.");
                } else {
                    System.out.println("Usage: nano <filename> <data>");
                }
                break;

                case "exit":
                // exit code
                System.out.println("Goodbye :)");
                running = false;
                break;
        }
    }
}
