import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class FileLister {
    public static void main(String[] args) {
        FileLister lister = new FileLister();
        while (true) {
            lister.run();
        }
    }

    public void run() {
        Scanner scnr = new Scanner(System.in);
        System.out.println("What file token are you looking for? (Type Exit to stop, leave blank for all)");
        String fileType = scnr.nextLine();
        System.out.println("What format should the output be?");
        String format = scnr.nextLine();
        if (fileType.toLowerCase().equals("exit"))
            System.exit(0);    
        fileType = fileType.replace(".", "");
        if(!format.startsWith(".")) format = "." + format;
        output(("Files Of Type ." + fileType + " in this directory: "), fileType, format);
        File directory = new File(".");
        search(directory, fileType, 0, format);
    }

    public void search(File directory, String fileType, int lvl, String format) {
        String[] fileList = directory.list();
        if (fileList != null) {
            for (String f : fileList) {
                if (!f.contains(".")) {
                    File down = new File(directory + "/" + f);
                    search(down, fileType, lvl + 1, format);
                } else if (f.endsWith(fileType)) {
                    output(("\t" + directory + "\\" +  f).replaceFirst("\\.", ""), fileType, format);
                }
            }
        }
        return;
    }

    private void output(String fileName, String fileType, String format) {
        File file = new File("All" + fileType + format);
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("\n" + fileName);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}