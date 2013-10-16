/*******************************************
 * Class: SetupProgram
 * Description: 
 * Project: WorldDataProject V3
 * Author: Moses Troyer
 * For Dr. Kaminski's 3310 Data and File Structures, WMU
 *******************************************/

package SetupProgram;

import SharedClassLibrary.MainData;
import java.io.*;

public class SetupProgram {
    
    public static void main(String[] args) throws IOException {
        MainData md = new MainData();
        
        md.closeFile();
    } //end main
} //end SetupProgram class
