package pl.edu.agh.sr.facecursor.utils;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import timber.log.Timber;

public class NetworkUtils {

    private static String ADDRESS = "192.168.0.3";
    private static int PORT = 5005;

    private DatagramSocket socket;

//    {
//        try {
//            socket = new DatagramSocket();
//            address = InetAddress.getByName(ADDRESS);
//            Timber.d("Created socket");
//        } catch (SocketException | UnknownHostException e) {
//            e.printStackTrace();
//        }
//    }

    private InetAddress address;

    public NetworkUtils() {
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName(ADDRESS);
            Timber.d("Created socket");
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void send(int x, int y, int click) {
        if(x == 0 && y == 0 && click == 0)
            return;
        Timber.d("x = " + x + ", y = " + y + ", click = " + click);
        byte[] buf = (x / 5 +  " " + y / 5 + " " + click).getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);

        try {
            socket.send(packet);
//            Timber.d("Sent successfully message");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
