import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClienteRemote extends Remote {
    void notification(String message) throws RemoteException;
}
