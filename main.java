import java.util.*;

public class main {
    
    static Scanner kb = new Scanner(System.in);
    static boolean running = true;
    static tree.Folder currentFolder; // Make currentFolder static so it can be updated by functions
    
    // Function to update the current folder
    public static void updateCurrent(tree.Folder newFolder) {
        currentFolder = newFolder;
    }

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
        currentFolder = root; // Use the static field instead of creating a local variable
        
        // Main OS loop
        while (running) {
            System.out.print(currentFolder.getName() + "$ ");
            String input = kb.nextLine().trim();
            
            if (!input.isEmpty()) {
                commandCheck(input, treeSystem, root);
            }
        }



    }

    //functions for each command
    public static void commandCheck(String line, tree treeSystem, tree.Folder root){
        List<String> strings = Arrays.asList(line.split(" "));
        String command = strings.get(0);
        switch (command) {
            case "help":
                System.out.println("Available commands:");
                System.out.println("help      - Show this help message");
                System.out.println("ls        - List folder contents");
                System.out.println("cd <dir>  - Change directory");
                System.out.println("cat <file>- Print file contents");
                System.out.println("pwd       - Print working directory");
                System.out.println("mkdir <name> - Create a new folder");
                System.out.println("nano <file> <data> - Create a file with data");
                System.out.println("touch <file> - Create an empty file");
                //System.out.println("rm <file/folder> - Remove a file or folder");
                System.out.println("exit      - Exit the OS");
                break;
            
            case "ls":
                // ls code
                currentFolder.printContents();
                break;
        
            case "cd":
                // cd code
                String path = strings.get(1); //since it could be a path
                //System.out.println(path);
                
                //check if ~ or ..
                if (path.equals("~")){
                    updateCurrent(root);
                    break;
                }
                if (path.equals("..")){
                    if (!currentFolder.equals(root)){    
                        updateCurrent(currentFolder.getParent());
                        break;
                    }
                    else{System.out.println("root has no parent");break;}
                }
                
                //check for leading \ or /
                if (path.charAt(0) == '/' || path.charAt(0) == '\\'){
                    path = path.substring(1);
                }
                if (path.charAt(path.length()-1) == '/' || path.charAt(0) == '\\'){
                    path = path.substring(0,path.length()-1);
                }

                List<String> directories = Arrays.asList(path.split("[/\\\\]+"));
                //System.out.println(directories);

                //loop to move into directory
                for (int i=0; i<directories.size();i++){
                    
                    try {//try and move into child i.e. first directory in path
                        List<tree.Node> contents = currentFolder.getContents(); //get all subfolders of current folder
                        //System.out.println(contents.get(i).getName());
                        boolean found = false;
                        for (tree.Node node : contents) {
                            if (node instanceof tree.Folder && node.getName().equals(directories.get(i))) {
                                updateCurrent((tree.Folder) node);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Directory '" + directories.get(i) + "' not found.");
                            break;
                        }
                    
                    } catch (Exception e) {
                        // handle exception
                        System.out.println("Path does not exist");
                        break;
                    }
                }

                
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
                if (strings.size() > 2) {
                    String newFileName = strings.get(1);
                    String newFileData = strings.get(2);
                    tree.File newFile = treeSystem.new File(newFileName, newFileData);
                    currentFolder.addContents(newFile);
                    System.out.println("Folder '" + newFileName + "' created.");
                } else {
                    System.out.println("Usage: nano <filename> <data>");
                }
                break;

                case "rm":

                break;

                case "touch":
                //create file
                if (strings.size() > 1) {
                    String newFileName = strings.get(1);
                    tree.File newFile = treeSystem.new File(newFileName);
                    currentFolder.addContents(newFile);
                    System.out.println("Folder '" + newFileName + "' created.");
                } else {
                    System.out.println("Usage: touch <filename>");
                }
                break;

                case "exit":
                // exit code
                System.out.println("Goodbye :)");
                running = false;
                break;

                default:
                    System.out.println("Invalid command. Type 'help' for a list of commands.");
                    break;
        }
    }
}
