/*******************************************
 * Class: AutoTesterUtility
 * Description: Runs all of the different main classes in the program, so I don't have to do it myself
 * Project: WorldDataProject V3
 * Author: Moses Troyer
 * For Dr. Kaminski's 3310 Data and File Structures, WMU
 *******************************************/

//ASK DR KAMISKI IF SHE WANTS OBJECT CREATED MESSAGES

package AutoTesterUtility;

import SetupProgram.SetupProgram;
import PrettyPrintUtility.PrettyPrintUtility;
import UserApp.UserApp;
import java.io.*;

public class AutoTesterUtility {
    
    public static void main(String[] args) throws IOException{
        System.out.println("Starting AutoTesterUtility");
        
        DeleteFile("Log.txt");
        DeleteFile("IndexBackup.bin");
        
        SetupProgram.main(new String[] {"Running from AutoTesterUtility"});
        PrettyPrintUtility.main(new String[] {"Running from AutoTesterUtility"});
        UserApp.main(new String[] {"Running from AutoTesterUtility"});
        PrettyPrintUtility.main(new String[] {"Running from AutoTesterUtility"});
                
    
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
