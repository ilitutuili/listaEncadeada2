import java.util.Scanner;

class Node {
    int valor;
    Node anterior;
    Node proximo;

    public Node(int valor) {
        this.valor = valor;
        this.anterior = null;
        this.proximo = null;
    }
}

class ListaDuplamenteEncadeada {
    private Node inicio;
    private Node fim;

    public ListaDuplamenteEncadeada() {
        this.inicio = null;
        this.fim = null;
    }

    // Inserção em ordem crescente
    public void inserirOrdenado(int valor) {
        Node novo = new Node(valor);

        // Caso a lista esteja vazia
        if (inicio == null) {
            inicio = fim = novo;
            return;
        }

        // Inserir no início
        if (valor <= inicio.valor) {
            novo.proximo = inicio;
            inicio.anterior = novo;
            inicio = novo;
            return;
        }

        // Inserir no final
        if (valor >= fim.valor) {
            fim.proximo = novo;
            novo.anterior = fim;
            fim = novo;
            return;
        }

        // Inserir no meio
        Node atual = inicio;
        while (atual != null && atual.valor < valor) {
            atual = atual.proximo;
        }

        Node anterior = atual.anterior;
        anterior.proximo = novo;
        novo.anterior = anterior;
        novo.proximo = atual;
        atual.anterior = novo;
    }

    // Imprimir crescente
    public void imprimirCrescente() {
        Node atual = inicio;
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    // Imprimir decrescente
    public void imprimirDecrescente() {
        Node atual = fim;
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.anterior;
        }
        System.out.println();
    }

    // Verifica se um número é primo
    private boolean ehPrimo(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // Remove todos os números primos
    public void removerPrimos() {
        Node atual = inicio;
        while (atual != null) {
            Node proximo = atual.proximo;
            if (ehPrimo(Math.abs(atual.valor))) {
                Node remover = atual;
                if (remover.anterior != null)
                    remover.anterior.proximo = remover.proximo;
                else
                    inicio = remover.proximo;

                if (remover.proximo != null)
                    remover.proximo.anterior = remover.anterior;
                else
                    fim = remover.anterior;
            }
            atual = proximo;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();

        System.out.print("Quantos números deseja inserir? ");
        int qtd = sc.nextInt();
        int[] vetor = new int[qtd];

        System.out.println("Digite os " + qtd + " números (entre -9999 e 9999):");
        for (int i = 0; i < qtd; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            vetor[i] = sc.nextInt();
        }

        System.out.println("\n📦 Vetor inserido:");
        for (int num : vetor) {
            System.out.print(num + " ");
        }

        System.out.println("\n\n🔗 Inserindo elementos na lista em ordem crescente...");
        for (int num : vetor) {
            lista.inserirOrdenado(num);
        }

        System.out.println("\n📈 Lista em ordem crescente:");
        lista.imprimirCrescente();

        System.out.println("\n📉 Lista em ordem decrescente:");
        lista.imprimirDecrescente();

        System.out.println("\n❌ Removendo números primos...");
        lista.removerPrimos();

        System.out.println("\n📈 Lista após remover primos (ordem crescente):");
        lista.imprimirCrescente();

        System.out.println("\n📉 Lista após remover primos (ordem decrescente):");
        lista.imprimirDecrescente();

        sc.close();
    }
}
