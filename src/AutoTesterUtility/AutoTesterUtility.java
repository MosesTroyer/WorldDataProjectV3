/*******************************************
 * Class: AutoTesterUtility
 * Description: Runs all of the different main classes in the program, so I don't have to do it myself
 * Project: WorldDataProject V3
 * Author: Moses Troyer
 * For Dr. Kaminski's 3310 Data and File Structures, WMU
 *******************************************/

package AutoTesterUtility;

import SetupProgram.SetupProgram;
import java.io.*;

public class AutoTesterUtility {
    
    public static void main(String[] args) throws IOException{
        System.out.println("Starting AutoTesterUtility");
        
        DeleteFile("Log.txt");
        
        SetupProgram.main(new String[] {"Running from AutoTesterUtility"});
    
    } //end Main
    
    //************************PRIVATE METHODS************************//  
    
    //from Dr. Kaminski, World Data Project 1
    private static boolean DeleteFile(String fileName) {
        boolean delete = false;
        File f = new File(fileName);
        if (f.exists()) {
            delete = f.delete();
        }
        return delete;
    } //end DeleteFile  
} //end AutoTesterUtility class
