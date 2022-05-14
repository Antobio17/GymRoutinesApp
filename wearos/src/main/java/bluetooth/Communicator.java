package bluetooth;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

public class Communicator extends Thread
{
    private final BluetoothSocket bluetoothSocket;
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private String message = null;

    public Communicator(BluetoothSocket socket)
    {
        this.bluetoothSocket = socket;
        InputStream inputTmp = null;
        OutputStream outputTmp = null;

        try {
            inputTmp = socket.getInputStream();
            outputTmp = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.inputStream = inputTmp;
        this.outputStream = outputTmp;
    }

    public void run()
    {
        byte[] buffer = new byte[1024];
        int bytes;

        while(true) {
            try {
                bytes = inputStream.read(buffer);
                this.message = new String(buffer,0, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(byte[] buffer)
    {
        try {
            outputStream.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBuffer()
    {
       return this.message;
    }

}
