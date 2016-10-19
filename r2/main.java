package r2;

import java.io.File;
import java.util.ArrayList;

public class main {

    static String directory = "%appdata%/searchdir";
    static String search = "";
    static boolean hashed = false;

    public static void main(String[] argv) {
        if (argv.length < 2) {
            System.out.println("Correct Usage:");
            System.out.println("java -jar Filesearcher\nFLAGS:\n-dir %directory% - Specifices the directory.\n-inclink - includes linkedIn database\n-search %string% - specifices what to search for, must be at the end.");
            System.exit(0);
        }
        for (int i = 0; i < argv.length; i++) {
            if (argv[i].contains("-dir")) {
                if (i + 1 <= argv.length - 1) {
                    directory = argv[i + 1];
                }
            }
            if (argv[i].contains("-search")) {
                for (int j = i; j < argv.length; j++) {
                    search += argv[j];
                    if (j != i) {
                        search += " ";
                    }
                }
                search = search.replace("-search", "");
            }
            if (argv[i].contains("-inclink")) {
                hashed = true;
            }
        }
        search = search.trim();
        System.out.println("Search - " + search);
        directory = directory.replace("\\", "/");
        File[] files = new File(directory).listFiles();
        ArrayList<searchingObject> objectList = new ArrayList();
        System.out.println("Files to Search:");
        System.out.println("----------------");
        for (int i = 0; i < files.length; i++) {
            if (!files[i].getName().contains("hash") || hashed) {
                System.out.println((i + 1) + " - " + files[i].getName());
                objectList.add(new searchingObject(search, files[i]));
            }
        }
        for (int i = 0; i < objectList.size(); i++) {
            objectList.get(i).start();
        }
    }
}
