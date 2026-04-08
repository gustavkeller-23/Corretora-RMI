public class Acao {
    private String nome;
    private double valor;

    Acao(String nome, double valor){
        this.valor = valor;
        this.nome = nome;
    }

    public void atualizarPreco(double valor){
        this.valor = valor;
    }

    public String getNome(){ return nome; }
    public double getValor(){ return valor; }
}
