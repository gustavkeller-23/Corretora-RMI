import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CRUDRemote extends Remote {
    public String listarAcoes() throws RemoteException;
    public String atualizarPreco(String nome, double preco) throws RemoteException;
    public String listarItem(String nome) throws RemoteException;
    public void addCliente(ClienteRemote cliente) throws RemoteException;
}
