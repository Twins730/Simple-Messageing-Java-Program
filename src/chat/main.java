package chat;

public class main {

    public static UserSettingsManager fileManager = new UserSettingsManager();

    public static void main(String[] args){

        Gui gui = new Gui();
        fileManager.load();

        while (true){
            if(Gui.clientMode){
                ClientManager manager = new  ClientManager();
                break;
            }
            if(Gui.serverMode){
                ServerManager manager = new ServerManager(Gui.port);
                break;
            }
            System.out.println(".");
            continue;
        }
    }
}