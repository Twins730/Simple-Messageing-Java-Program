package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientManager {

    //Consumer
    public static ServerSocket skt;
    public static Socket s;
    public static DataInputStream in;
    public static DataOutputStream  out;

    public ClientManager() {
        try {
            skt = new ServerSocket(6001);
            while (true) {
                s = skt.accept();
                in = new DataInputStream(s.getInputStream());
                out = new DataOutputStream(s.getOutputStream());

                String s = "";

                while (true) {
                    s = in.readUTF();
                    Gui.addUserChat(s);

                }
            }


        } catch (Exception e) {
        }

    }
}