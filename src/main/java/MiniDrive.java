import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MiniDrive {
    public static void main(String[] args) {

        System.out.println("Please select a controller:");
        Controller[] ca = ControllerEnvironment.getDefaultEnvironment().getControllers();
        for (int i = 0; i < ca.length; i++) {
            System.out.println(i + ": "+ca[i].getName());
        }

        Scanner s = new Scanner(System.in);
        Controller selected = ca[s.nextInt()];

        System.out.println("Please select a serial port:");
        SerialPort[] comms = SerialPort.getCommPorts();
        for (int i = 0; i < comms.length; i++) {
            System.out.println(i+": "+comms[i].getSystemPortName());
        }
        SerialPort p = comms[s.nextInt()];
        p.setBaudRate(19200);


        UpdateRunnable updater = new UpdateRunnable(selected, p);
        s.reset();
        while(true) {
            System.out.print("> ");
            String cmd = s.nextLine().trim();
            if(cmd.equals("q")){
                updater.stop();
                break;
            } else if(cmd.equals("e")){
                updater.start();
            } else if(cmd.equals("d")){
                updater.stop();
            }
        }
        System.out.println("DONE");
    }

}
