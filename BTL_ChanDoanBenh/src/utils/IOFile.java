package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author whiwf
 */
public class IOFile {
    public static ArrayList<String> readFromFile(String fileName){
        ArrayList<String> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner (new File(fileName));
            while(sc.hasNextLine()){
                list.add(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } 
        
        return list;
    }
    
    public static void writeFromFile(String fileName, ArrayList<String> list  ) throws IOException{
        try {
            FileWriter fw = new FileWriter(fileName);
            for (String item : list) {
                fw.write(item);
            }
            fw.flush();
            fw.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }    
    }
    
}
