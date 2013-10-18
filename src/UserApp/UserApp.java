/*******************************************
 * Class: UserApp
 * Description:
 * Project: WorldDataProject V3
 * Author: Moses Troyer
 * For Dr. Kaminski's 3310 Data and File Structures, WMU
 *******************************************/

package UserApp;

import SharedClassLibrary.Logger;
import SharedClassLibrary.MainData;
import SharedClassLibrary.NameIndex;
import java.io.*;
import java.util.Scanner;

public class UserApp {
 
    public static void main(String[] args) throws IOException {
        Logger log = new Logger();
        log.writeln("Starting UserApp");
        String command[];
        int i;
        
        FileInputStream tF = new FileInputStream("TransDataA3.txt");
        Scanner transFile = new Scanner(tF);
        log.writeln("opened TransDataA3 file");

        NameIndex ni = new NameIndex();
        
        ni.recoverFromBackup();
        
        while(transFile.hasNextLine()){
            command = transFile.nextLine().split(" ");
            
            for(i = 2;i<command.length;i++){
                command[1] += " " + command[i];
            }
            
            log.write(command[0]);
            
            switch (command[0]) { 
                case "LN":
                    log.writeln();
                    ni.listAllByName();
                    break;
                case "QN":
                    log.writeln(" " + command[1]);
                    ni.queryByName(command[1]);
                    break;                  
                default:
                    log.writeln("**Invalid Command");
                    break;
            }
            
        }
        
        log.close();
    } //end main
    
} // end UserApp
