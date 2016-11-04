package akka;

import akka.actor.UntypedActor;
import akka.io.Tcp;
import akka.io.TcpMessage;
import akka.util.ByteString;
import akka.util.ByteStringBuilder;

/**
 * Created by chenhao on 2016/11/4.
 */
//handler 在我这边测试的时候几乎没有用   直接把收到消息的client当做就行了  不需要handler
public class ClientHandler extends UntypedActor {
    public void onReceive(Object message) throws Exception {
        if (message instanceof Tcp.CommandFailed) {
            System.out.println("command failed");
        } else if (message instanceof Tcp.Connected) {
            System.out.println("connected");
            System.out.println("send message " + getString());
            getSender().tell(TcpMessage.write(getString()), getSelf());
        } else if (message instanceof Tcp.Received) {
            System.out.println("receive message");
            System.out.println(((Tcp.Received) message).data());
        }
    }

    public ByteString getString() {
        ByteStringBuilder stringBuilder = new ByteStringBuilder();
        stringBuilder.putBytes("123".getBytes());
        return stringBuilder.result();
    }
}
