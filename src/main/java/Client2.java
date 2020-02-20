import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            SocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), 8080);
            socket.connect(address);
            while (true) {
                Scanner scanner = new Scanner(System.in);
                socket.getOutputStream().write(scanner.next().getBytes());
            }
        } catch (Exception e) {

        } finally {

        }
    }
}
