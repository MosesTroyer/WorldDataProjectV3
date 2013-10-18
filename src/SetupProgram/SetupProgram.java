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
        Logger log = new Logger();
        log.writeln("Starting SetupProgram");
        
        int recProcessed = 0;
        
        RawData rd = new RawData();
        String line[];
        NameIndex ni = new NameIndex();
        
        while((line = rd.readLine()) != null){
            ni.addCountry(line[2], Short.parseShort(line[0]));
            recProcessed++;
        }
        
        rd.closeFile();
        
        log.writeln("Setup completed: 26 records processed");
        
        ni.writeBackupBST();
        
        log.close();
    } //end main
} //end SetupProgram class
