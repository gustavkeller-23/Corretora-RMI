import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Cliente extends UnicastRemoteObject implements ClienteRemote {

    private CRUDRemote crud;

    public Cliente(CRUDRemote crud) throws RemoteException {
        super(); 
        this.crud = crud;
    }
    
    
    @Override
    public void notification(String Message) throws RemoteException {
        System.out.println(Message);
    }

    public void UserUIInterface(CRUDRemote stub) throws RemoteException {
        
        Scanner scanner = new Scanner(System.in);
        String userActivity;
        
        while (true) {

            userActivity = scanner.nextLine();

            if (userActivity.equals("help")) {

                System.out.println("Comandos :");
                System.out.println("list     -  listar acoes possiveis");
                System.out.println("update   -  atualizar uma acao");
                System.out.println("only     -  mostrar preco de uma acao");
                System.out.println("closed   -  fechar conexao");

            } else if (userActivity.equals("list")) {

                System.out.println(stub.listarAcoes());

            } else if (userActivity.equals("update")) {

                System.out.println("Digite o nome da acao: ");
                String nome = scanner.nextLine();

                System.out.println("Digite o novo valor da acao: ");
                double valor = scanner.nextDouble();

                System.out.println(stub.atualizarPreco(nome, valor));

            } else if (userActivity.equals("only")) {

                System.out.println("Digite o nome da acao: ");
                String nome = scanner.nextLine();

                System.out.println(stub.listarItem(nome));

            } else if (userActivity.equals("closed")) {
                
                    scanner.close();
                    return;
                
            } else {   System.out.println("Comando inexistente.\nDigite 'help' para mais informacoes");   }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int timeWait = 5; // Tempo de espera para reconexão
        int tentativaReconect = 1; // tentativas de reconexão

        while (true) {

            System.out.println("Tentando conexão com o servidor...");

            try {

                Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                CRUDRemote stub = (CRUDRemote) registry.lookup("CRUDImpl");
            
                Cliente self = new Cliente(stub);
                stub.addCliente(self); // Registra para receber notificações

                tentativaReconect = 1;   // Reset das falhas de conexão  
                System.out.println("Servidor Conectado com sucesso");

                self.UserUIInterface(stub);

            } catch (Exception e) {
                
                System.out.println("Falha ao conectar com o servidor....");

                for (int i = timeWait * tentativaReconect; i > 0; i--) {
                    System.out.println("Tentando reconexão em " + i);
                    Thread.sleep(1000);
                }

                if (tentativaReconect == 4) {

                    try { // Questão estética
                        // Detecta o sistema operacional
                        String sistema = System.getProperty("os.name").toLowerCase();

                        if (sistema.contains("windows")) {
                            // Windows usa "cls"
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        } else {
                            // Linux e Mac usam "clear"
                            new ProcessBuilder("clear").inheritIO().start().waitFor();
                        }
                    } catch (IOException | InterruptedException j) {
                    }

                    System.out.println("Servidor indisponível, retorne mais tarde....");
                    return;
                }

                tentativaReconect++;
            }
        }
    }
}
