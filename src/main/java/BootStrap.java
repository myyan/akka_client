import akka.Client;
import akka.ClientHandler;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.io.Tcp;

import java.net.InetSocketAddress;

/**
 * Created by chenhao on 2016/11/4.
 */
public class BootStrap {
    public static void main(String[] args) {
        ActorSystem client = ActorSystem.create("client");
        ActorRef tcpManager = Tcp.get(client).manager();
        InetSocketAddress address = new InetSocketAddress("localhost",8080);
        ActorRef clientHandler = client.actorOf(Props.create(ClientHandler.class),"clientHandler");
        client.actorOf(Client.props(address,clientHandler),"client_");

    }
}
