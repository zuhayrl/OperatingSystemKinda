public class test {
    
    public static void main(String[] args) {
        // Create an instance of the tree class
        tree treeSystem = new tree();
        
        System.out.println("=== Testing Tree File System ===\n");
        
        // Test 1: Create basic nodes
        System.out.println("Test 1: Creating basic nodes");
        tree.Node basicNode = treeSystem.new Node("BasicNode");
        System.out.println("Created node: " + basicNode.getName());
        
        // Test 2: Create files
        System.out.println("\nTest 2: Creating files");
        tree.File file1 = treeSystem.new File("document.txt", "This is a text document");
        tree.File file2 = treeSystem.new File("readme.md");
        tree.File file3 = treeSystem.new File("config.ini", "port=8080\nhost=localhost");
        
        System.out.println("Created file: " + file1.getName() + " with data: " + file1.getData());
        System.out.println("Created file: " + file2.getName() + " with data: '" + file2.getData() + "'");
        System.out.println("Created file: " + file3.getName() + " with data: " + file3.getData());
        
        // Test 3: Create folders
        System.out.println("\nTest 3: Creating folders");
        tree.Folder rootFolder = treeSystem.new Folder("root");
        tree.Folder documentsFolder = treeSystem.new Folder("Documents");
        tree.Folder picturesFolder = treeSystem.new Folder("Pictures");
        tree.Folder projectFolder = treeSystem.new Folder("MyProject");
        
        System.out.println("Created folder: " + rootFolder.getName());
        System.out.println("Created folder: " + documentsFolder.getName());
        System.out.println("Created folder: " + picturesFolder.getName());
        System.out.println("Created folder: " + projectFolder.getName());
        
        // Test 4: Add files to folders
        System.out.println("\nTest 4: Adding files to folders");
        documentsFolder.addContents(file1);
        documentsFolder.addContents(file2);
        projectFolder.addContents(file3);
        
        System.out.println("Added files to Documents folder");
        System.out.println("Added config file to MyProject folder");
        
        // Test 5: Create nested folder structure
        System.out.println("\nTest 5: Creating nested folder structure");
        rootFolder.addContents(documentsFolder);
        rootFolder.addContents(picturesFolder);
        rootFolder.addContents(projectFolder);
        
        // Add some image files to pictures
        tree.File image1 = treeSystem.new File("photo1.jpg", "JPEG image data");
        tree.File image2 = treeSystem.new File("photo2.png", "PNG image data");
        picturesFolder.addContents(image1);
        picturesFolder.addContents(image2);
        
        System.out.println("Created nested folder structure");
        
        // Test 6: Test Backend and print tree
        System.out.println("\nTest 6: Testing Backend and printing tree structure");
        tree.Backend backend = treeSystem.new Backend(rootFolder);
        System.out.println("Root folder: " + backend.getRoot().getName());
        
        System.out.println("\nComplete file system tree:");
        backend.printTree();
        
        // Test 7: Test folder contents retrieval
        System.out.println("\nTest 7: Testing folder contents retrieval");
        System.out.println("Documents folder contains:");
        for (tree.Node node : documentsFolder.getContents()) {
            System.out.println("  - " + node.getName() + " (type: " + 
                             (node instanceof tree.File ? "File" : "Folder") + ")");
        }
        
        System.out.println("Pictures folder contains:");
        for (tree.Node node : picturesFolder.getContents()) {
            System.out.println("  - " + node.getName() + " (type: " + 
                             (node instanceof tree.File ? "File" : "Folder") + ")");
        }
        
        // Test 8: Test printContents function
        System.out.println("\nTest 8: Testing printContents function");
        System.out.println("Documents folder printContents output:");
        documentsFolder.printContents();
        System.out.println(); // New line after printContents
        
        System.out.println("Pictures folder printContents output:");
        picturesFolder.printContents();
        System.out.println(); // New line after printContents
        
        System.out.println("Root folder printContents output:");
        rootFolder.printContents();
        System.out.println(); // New line after printContents
        
        // Test empty folder
        tree.Folder emptyTestFolder = treeSystem.new Folder("EmptyTest");
        System.out.println("Empty folder printContents output:");
        emptyTestFolder.printContents();
        System.out.println("(should be empty)");
        
        // Test 9: Test file data modification
        System.out.println("\nTest 9: Testing file data modification");
        System.out.println("Attempting to modify file data (should show nano message):");
        file1.seteData("New content for the document");
        
        // Test 10: Test removing contents
        System.out.println("\nTest 10: Testing remove contents");
        System.out.println("Removing readme.md from Documents folder...");
        boolean removed = documentsFolder.removeContents(file2);
        System.out.println("Remove successful: " + removed);
        
        System.out.println("Documents folder now contains:");
        for (tree.Node node : documentsFolder.getContents()) {
            System.out.println("  - " + node.getName());
        }
        
        // Test 10: Test node name modification
        System.out.println("\nTest 10: Testing node name modification");
        System.out.println("Original name: " + image1.getName());
        image1.setName("vacation_photo.jpg");
        System.out.println("New name: " + image1.getName());
        
        // Test 11: Final tree structure
        System.out.println("\nTest 11: Final tree structure after modifications");
        backend.printTree();
        
        // Test 12: Edge cases
        System.out.println("\nTest 12: Testing edge cases");
        
        // Empty folder
        tree.Folder emptyFolder = treeSystem.new Folder("EmptyFolder");
        System.out.println("Empty folder contents count: " + emptyFolder.getContents().size());
        
        // Try to remove non-existent item
        boolean removeResult = documentsFolder.removeContents(file2); // file2 was already removed
        System.out.println("Removing already removed file: " + removeResult);
        
        // Test with null-like scenarios (empty file data)
        tree.File emptyFile = treeSystem.new File("empty.txt");
        System.out.println("Empty file data: '" + emptyFile.getData() + "'");
        
        System.out.println("\n=== All tests completed! ===");
    }
}
