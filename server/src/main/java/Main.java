import chess.*;
import server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        System.out.println("♕ 240 Chess Server Starting Up ♕");
        int port = server.run(8080);
        System.out.println("\nServer running on port " + port);
    }
}