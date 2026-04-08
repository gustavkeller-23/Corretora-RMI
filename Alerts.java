import java.rmi.RemoteException;
import java.util.ArrayList;

public class Alerts{
    
    ArrayList<ClienteRemote> clientes = new ArrayList<>();
    
    public void addCliente(ClienteRemote cliente){
        clientes.add(cliente);
        System.out.println("Novo cliente conectado: "+ cliente);
    }


    public void atualizarPreco(String acao) throws RemoteException{
        if(clientes == null) return;
        ArrayList<ClienteRemote> mortos = new ArrayList<>();

        for (ClienteRemote c : clientes){
            try {
                c.notification("O preco da acao: "+acao+" acabou de ser atualizado");
            } 
            catch (Exception e) {
                System.out.println("Cliente " +c+" se desconectou.");
                mortos.add(c);
            }
        }
        clientes.removeAll(mortos);
    }

    
    public void alertNovaAcao(String acao) throws RemoteException{
        if(clientes == null) return;
        ArrayList<ClienteRemote> mortos = new ArrayList<>();
        
        for (ClienteRemote c : clientes){
            try {
                c.notification("A nova acao: "+acao+", acabou de ser adicionada.");
            } 
            catch (Exception e) {
                System.out.println("Cliente " +c+" se desconectou.");
                mortos.add(c);
            }
        }
        clientes.removeAll(mortos);
    }
}
