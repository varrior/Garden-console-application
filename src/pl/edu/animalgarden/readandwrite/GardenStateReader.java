package pl.edu.animalgarden.readandwrite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GardenStateReader {
    public String read(String fileName){
        BufferedReader br = null;
        String readItemsFromFile = "";
        try {
            FileReader fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String line;

            do {
                line = br.readLine();
                if(line != null){
                    readItemsFromFile += line + " ";
                }
            } while (line != null);

            //System.out.println("Data saved in " + fileName);
        } catch (IOException e) {
            System.out.println("Error opening file");
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Unknown error occurred");
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Error reading file");
                    e.printStackTrace();
                }
            }
        }

        return readItemsFromFile;
    }
}
