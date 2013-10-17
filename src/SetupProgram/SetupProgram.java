/*******************************************
 * Class: SetupProgram
 * Description: 
 * Project: WorldDataProject V3
 * Author: Moses Troyer
 * For Dr. Kaminski's 3310 Data and File Structures, WMU
 *******************************************/

package SetupProgram;

import SharedClassLibrary.MainData;
import SharedClassLibrary.RawData;
import SharedClassLibrary.NameIndex;
import SharedClassLibrary.Logger;
import java.io.*;

public class SetupProgram {
    
    public static void main(String[] args) throws IOException {
        System.out.println("Starting SetupProgram");
        
        Logger log = new Logger();
        log.writeln("Starting SetupProgram");
        
        RawData rd = new RawData();
        String line[];
        NameIndex ni = new NameIndex();
        
        while((line = rd.readLine()) != null){
            ni.addCountry(line[2], Short.parseShort(line[0]));
        }
        
        ni.printBST();
        
        log.close();
    } //end main
} //end SetupProgram class
