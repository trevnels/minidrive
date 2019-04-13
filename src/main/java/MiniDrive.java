import com.fazecast.jSerialComm.SerialPort;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.util.Scanner;

public class MiniDrive {
    public static void main(String[] args) {
//        System.out.println(System.setProperty("java.library.path", "./natives"));
        System.out.println("Please select a controller:");
        Controller[] ca = ControllerEnvironment.getDefaultEnvironment().getControllers();
        for (int i = 0; i < ca.length; i++) {
            System.out.println(i + ": "+ca[i].getName());
        }

        Scanner s = new Scanner(System.in);
        System.out.print("> ");
        Controller selected = ca[Integer.parseInt(s.nextLine())];

        System.out.println("Please select a serial port:");
        SerialPort[] comms = SerialPort.getCommPorts();
        for (int i = 0; i < comms.length; i++) {
            System.out.println(i+": "+comms[i].getSystemPortName());
        }
        System.out.print("> ");
        SerialPort p = comms[Integer.parseInt(s.nextLine())];
        p.setBaudRate(19200);


        UpdateRunnable updater = new UpdateRunnable(selected, p);
        while(true) {
            System.out.print("> ");
            String cmd = s.nextLine().trim();
            if(cmd.equals("q")){
                updater.stop();
                break;
            } else if(cmd.equals("e")){
                System.out.println("Please wait...");
                updater.start();
            } else if(cmd.equals("d")){
                System.out.println("Please wait...");
                updater.stop();
            }
        }
    }

}
