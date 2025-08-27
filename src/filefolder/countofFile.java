package filefolder;


import java.io.File;
import java.util.Stack;

public class countofFile {
    public static int getfilescount(String path){
        File root = new File(path);
        if (!root.isFile() && !root.isDirectory()){
            return 0;
        }

        if (root.isFile()){
            return 1;
        }
        Stack<File> directory = new Stack<>();
        directory.add(root);
        int files = 0;
        while (!directory.empty()){
            File folder = directory.pop();
            for (File next:folder.listFiles()){
                if (next.isFile()){files++;}
                if (next.isDirectory()){
                    directory.push(next);
                }

            }
        }
        return files;
    }
    public static void main(String[] args) {

    }
}
