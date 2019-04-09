import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import net.java.games.input.Component;
import net.java.games.input.Controller;

import java.util.concurrent.atomic.AtomicBoolean;

public class UpdateRunnable implements Runnable {

    private Controller controller;
    private SerialPort serialPort;

    public UpdateRunnable(Controller controller, SerialPort serialPort) {
        this.controller = controller;
        this.serialPort = serialPort;
    }

    private AtomicBoolean running = new AtomicBoolean(false);
    private Thread worker;

    public void start() {
        serialPort.openPort();
        worker = new Thread(this);
        worker.start();

    }

    public void stop() {
        running.set(false);
        serialPort.closePort();
    }

    @Override
    public void run() {
        running.set(true);

        while(running.get()) {
            controller.poll();

            for (Component component : controller.getComponents()) {
//            System.out.println(component.getName());
                if (component.getName().equals("A")) {
                    if (component.getPollData() == 1.0) {
                        byte[] b = "{\"light\": true}\n".getBytes();
                        serialPort.writeBytes(b, b.length);
                    } else {
                        byte[] b = "{\"light\": false}\n".getBytes();
                        serialPort.writeBytes(b, b.length);
                    }
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}
