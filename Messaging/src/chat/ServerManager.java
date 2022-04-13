package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ServerManager {

    //HOST
    public static Socket s;
    public static DataInputStream in;
    public static DataOutputStream out;

    public ServerManager(String p){
        try{
            s = new Socket(p, 6001);
            in  = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());

            String s = "";

            while (true){
                s = in.readUTF();
                Gui.addUserChat(s);
            }

        }catch(Exception e){}
    }
}