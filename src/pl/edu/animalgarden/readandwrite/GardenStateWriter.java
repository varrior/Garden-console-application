package pl.edu.animalgarden.readandwrite;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GardenStateWriter {
    public void save(String fileName, String dataToSave){

        BufferedWriter bw = null;
        try {
            FileWriter fw = new FileWriter(fileName);
            bw = new BufferedWriter(fw);
            bw.write(dataToSave);
            System.out.println("Data saved in " + fileName);
        } catch (IOException e) {
            System.out.println("Error opening file");
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Unknown error occurred");
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    System.out.println("Error saving file");
                    e.printStackTrace();
                }
            }
        }
    }
}

