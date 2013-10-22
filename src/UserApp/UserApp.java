/*******************************************
 * Class: UserApp
 * Description:
 * Project: WorldDataProject V3
 * Author: Moses Troyer
 * For Dr. Kaminski's 3310 Data and File Structures, WMU
 *******************************************/

package UserApp;

import SharedClassLibrary.UserInterface;
import SharedClassLibrary.MainData;
import SharedClassLibrary.NameIndex;
import java.io.*;

public class UserApp {
 
    public static void main(String[] args) throws IOException {
        UserInterface ui = new UserInterface();
        ui.openTD();
        ui.writeln("Starting UserApp");
        String command[];
        int i;
        MainData md = new MainData();
        
        ui.writeln("opened TransDataA3 file");

        NameIndex ni = new NameIndex();
        
        ni.recoverFromBackup();
        
        while(ui.hasNextLine()){
            command = ui.nextLine().split(" ");
            
            for(i = 2;i<command.length;i++){
                command[1] += " " + command[i];
            }
            
            ui.write(command[0]);
            
            switch (command[0]) { 
                case "LN":
                    ui.writeln();
                    ni.listAllByName(md);
                    break;
                case "QN":
                    ui.writeln(" " + command[1]);
                    ni.queryByName(command[1], md);
                    break;                  
                default:
                    ui.writeln("**Invalid Command");
                    break;
            }
            
        }
        
        md.closeFile();
        ui.finishUp('t');
    } //end main
    
} // end UserApp
