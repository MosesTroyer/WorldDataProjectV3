/*******************************************
 * Class: PrettyPrintUtility
 * Description: 
 * Project: WorldDataProject V3
 * Author: Moses Troyer
 * For Dr. Kaminski's 3310 Data and File Structures, WMU
 *******************************************/

package PrettyPrintUtility;

import SharedClassLibrary.Logger;
import java.io.*;

public class PrettyPrintUtility {
    
    public static void main(String[] args) throws IOException {
        Logger log = new Logger();
        log.writeln("Starting PrettyPrintUtility");
        
        RandomAccessFile backup = new RandomAccessFile("IndexBackup.bin", "r");
        log.writeln("opened IndexBackup file");
        
        log.writeln();
        
        short rootPtr = backup.readShort();
        short n = backup.readShort();
        int i, nameLength = 18, c;
        short l, drp, r;

        log.writeln("RootPtr is " + rootPtr + 
                ", N is " + n);
        
        log.writeln("[SUB]  LCH  NAME--------------  DRP  RCH");
        
        for(i=0;i<n;i++){
            //[SUB]
            if(i > 99) log.write("[" + i + "]  ");
            else if(i > 9) log.write("[0" + i + "]  ");
            else log.write("[00" + i + "]  ");
            
            //LCH
            l = backup.readShort();
            if(l > 99) log.write(l + "  ");
            else if(l > 9) log.write("0" + l + "  ");
            else if(l == -1) log.write("-0" + Math.abs(l) + "  ");
            else log.write("00" + l + "  ");
            
            for(c = 0;c<nameLength;c++){
                log.write(Character.toString(backup.readChar()));
            }
            log.write("  ");
            
            //DRP
            drp = backup.readShort();
            if(drp > 99) log.write(drp + "  ");
            else if(drp > 9) log.write("0" + drp + "  ");
            else log.write("00" + drp + "  ");
            
            //RCH
            r = backup.readShort();
            if(r > 99) log.write(r + "");
            else if(r > 9) log.write("0" + r);
            else if(r == -1) log.write("-0" + Math.abs(r) + " ");
            else log.write("00" + r);
            
            log.writeln();
        }
        
        
        log.writeln("closed IndexBackup file");
        
        backup.close();
        log.close();
    } //end main
    
} //end PrettyPrintUtility class
