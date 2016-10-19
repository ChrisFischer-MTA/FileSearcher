package r2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class searchingObject extends Thread {

    String search;
    BufferedReader reader;
    String filename = "";

    public searchingObject(String search, File file) {
        try {
            this.search = search.toLowerCase();
            this.filename = file.getName();
            this.reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(searchingObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        //System.out.println("S - Search Started on "+filename);
        try {
            int numSearched = 0;
            String line;
            while (reader.ready()) {
                numSearched += 1;
                line = "";
                line = reader.readLine();
                if (line.toLowerCase().contains(search)) {
                    if(line.length() > 300){
                        System.out.println("F - " + filename + "\nR - " + line.substring(0, 300));
                    }
                   
                        System.out.println("F - " + filename + "\nR - " + line);
                    
                }
            }
            reader.close();
            System.out.println("F - " + filename + " S Number of Lines Searched - " + numSearched);
        } catch (IOException ex) {
            Logger.getLogger(searchingObject.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
