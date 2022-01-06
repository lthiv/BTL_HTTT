package utils;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

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
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
        return list;
    }
}
