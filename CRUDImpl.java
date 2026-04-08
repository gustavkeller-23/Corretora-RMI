import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.rmi.RemoteException;


public class CRUDImpl extends UnicastRemoteObject implements CRUDRemote {

    Alerts alerta;
    ArrayList<Acao> listaAcoes = new ArrayList<>();

    protected CRUDImpl() throws RemoteException{
        super();
    }

    public String listarAcoes() throws RemoteException{
        if (listaAcoes.isEmpty()) return "Nenhuma acao cadastrada.";
        StringBuilder sb = new StringBuilder();
        for(Acao a : listaAcoes)
            sb.append("Lista item: ").append(a.getNome()).append(", ").append(a.getValor()).append("\n");
        return sb.toString().trim();
    }

    public String listarItem(String nome) throws RemoteException {
        for(Acao a : listaAcoes){
            if (nome.equals(a.getNome())) {
                return "O valor da "+nome+" com valor atual de "+a.getValor();
            }
        }
        return "Acao com nome de ("+nome+") nao encontrado.";
    }

    public String atualizarPreco(String nome, double preco) throws RemoteException {
        for(Acao a : listaAcoes){
            if (nome.equals(a.getNome())) {
                String antes = "Atualizando...\nO valor da "+nome+" com valor atual de "+a.getValor()+" alterado para "+preco;
                a.atualizarPreco(preco);
                alerta.atualizarPreco(nome);
                return antes + "\nAtualizado com sucesso!\nO valor da "+a.getNome()+" com valor atual de "+a.getValor();
            }
        }
        return "Acao com nome de ("+nome+") nao encontrado.";
    }

    public void addList(String nome, double preco) throws RemoteException{
        Acao newAcao = new Acao(nome, preco);
        listaAcoes.add(newAcao);
        alerta.alertNovaAcao(nome);
    }

    public void addCliente(ClienteRemote cliente) throws RemoteException{
        alerta.addCliente(cliente);
    }

    public void setAlerta(Alerts alerta){
        this.alerta = alerta;
    }

}
