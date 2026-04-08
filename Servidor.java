import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) {
        try {

            CRUDImpl obj = new CRUDImpl();     // CRUD LOCAL
            Alerts alerta = new Alerts();            
            obj.setAlerta(alerta);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("CRUDImpl", obj);

            System.out.println("Servidor RMI pronto...");

            
            obj.addList("BTC", 544.23);
            obj.addList("ETH", 824.70);
            obj.addList("SOL", 1490.92);


            Scanner scanner = new Scanner(System.in);
            String userActivity;


            while(true){

                userActivity = scanner.nextLine();

                if (userActivity.equals("help")) {
                    
                    System.out.println("Comandos :");
                    System.out.println("list     -  listar acoes possiveis");
                    System.out.println("update   -  atualizar uma acao");
                    System.out.println("only     -  mostrar preco de uma acao");
                    System.out.println("add      -  cadastrar uma nova acao");
                    System.out.println("closed   -  fechar servidor");

                } else if (userActivity.equals("list")) {
                    
                    System.out.println(obj.listarAcoes());
                
                } else if (userActivity.equals("update")) {
                
                    System.out.println("Digite o nome da acao: ");
                    String nome = scanner.nextLine();

                    System.out.println("Digite o novo valor da acao: ");
                    double valor = scanner.nextDouble();
                    
                    System.out.println(obj.atualizarPreco(nome, valor));
                
                } else if (userActivity.equals("only")) {
                
                    System.out.println("Digite o nome da acao: ");
                    String nome = scanner.nextLine();
                    System.out.println(obj.listarItem(nome));
                
                } else if (userActivity.equals("add")) {
                
                    System.out.println("Digite o nome da acao: ");
                    String nome = scanner.nextLine();

                    System.out.println("Digite o novo valor da acao: ");
                    double valor = scanner.nextDouble();

                    obj.addList(nome, valor);
                
                } else if (userActivity.equals("closed")) {
                
                    scanner.close();
                    System.exit(0);
                
                } else {   System.out.println("Comando inexistente.\nDigite 'help' para mais informacoes");   }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

