public class App {
    static final char CERTO = 'X';
    static final char ERRADO = 'O';

    static final char AGUA = '~';
    static final char NAVIO = 'N';
    static final int[] FROTA = { 1, 2, 3, 4, 5 };

    public static char[][] criarCenario() {
        char[][] cenario = new char[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cenario[i][j] = AGUA;
            }
        }
        return cenario;
    }

    public static void checarCenario(int linha, int coluna, int tamanho, boolean vertical, char[][] cenario) {
        if (linha < 0 || linha + tamanho >= 10 || coluna < 0 || coluna + tamanho >= 10) {
            throw new IllegalArgumentException("ERRO: Limite de Cenário.");
        }

        for (int i = 0; i < tamanho; i++) {
            if (vertical) {
                if (cenario[linha + i][coluna] == NAVIO) {
                    throw new IllegalArgumentException("ERRO: Navio Sobreposto.");
                }
            } else {
                if (cenario[linha][coluna + i] == NAVIO) {
                    throw new IllegalArgumentException("ERRO: Navio Sobreposto.");
                }
            }
        }
    }

    public static char[][] gerarCenario() {
        char[][] cenario = criarCenario();
        gerarFrota(cenario);
        return cenario;
    }

    public static void mostrarCenario(char[][] cenario) {
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(cenario[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void criarNavio(int linha, int coluna, int tamanho, boolean vertical, char[][] cenario) {
        checarCenario(linha, coluna, tamanho, vertical, cenario);

        if (vertical) {
            for (int i = 0; i < tamanho; i++) {
                cenario[linha + i][coluna] = NAVIO;
            }
        } else {
            for (int i = 0; i < tamanho; i++) {
                cenario[linha][coluna + i] = NAVIO;
            }
        }
    }

    public static void gerarFrota(char[][] cenario) {
        int count = 0;

        while (true) {
            if (count == FROTA.length) {
                break;
            }
            try {
                criarNavio((int) (Math.random() * 9), (int) (Math.random() * 9), FROTA[count], Math.random() < 0.5,
                        cenario);
                count++;
            } catch (Exception e) {
                continue;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        char[][] jogadorUm = gerarCenario();
        mostrarCenario(jogadorUm);
    }
}
