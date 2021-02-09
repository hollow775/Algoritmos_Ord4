import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader("/Users/macbook/Documents/IF_BBC/EAD/Estrutura_de_dados_II/Arquivos_ordenacao/dados_1.txt");
        BufferedReader readFile = new BufferedReader(file);
        FileWriter novoArquivo = new FileWriter("/Users/macbook/Documents/IF_BBC/EAD/Estrutura_de_dados_II/Arquivos_ordenacao/Algoritmos_ord_4/dadosOrdenados_1.txt");
        PrintWriter gravarArquivo = new PrintWriter(novoArquivo);

        gravarArquivo.printf("Desenvolvedor: Clayton Rodrigues Dos Prazeres.\n ___________ \n");
        // preparando o documento

        double contador = 0.0;
        String line = "";
        long tempoInicial;
        long tempoFinal;

        String linha = readFile.readLine();
        while (linha != null) {
            linha = readFile.readLine();
            if (linha != null) {
                line = linha;
            }
        }
        file.close();
        line = line.replace("[", "");
        line = line.replace("]", "");
        line = line.replace(" ", "");
        String[] str = line.split(",");
        int[] dados = new int[str.length];
        iniciarVetor(dados, str);

        //RadixSort
        tempoInicial = System.currentTimeMillis();
        radixSort(dados);
        tempoFinal = System.currentTimeMillis() - tempoInicial;
        System.out.println("Radix Sort Executado em = " + (tempoFinal) + " ms\nCom " + contador + " movimentos.\n");
        gravarArquivo.printf("\nRadix Sort: \n--------------------\n");
        gravarArquivo(gravarArquivo, contador, tempoFinal, dados);

        //GnomeSort
        iniciarVetor(dados, str);
        tempoInicial = System.currentTimeMillis();
        contador = gnomeSort(dados, dados.length, contador);
        tempoFinal = System.currentTimeMillis() - tempoInicial;
        System.out.println("Gnome Sort Executado em = " + (tempoFinal) + " ms\nCom " + contador + " movimentos.\n");
        gravarArquivo.printf("\nGnome Sort: \n--------------------\n");
        gravarArquivo(gravarArquivo, contador, tempoFinal, dados);

        //CountingSort
        iniciarVetor(dados, str);
        tempoInicial = System.currentTimeMillis();
        countSort(dados);
        tempoFinal = System.currentTimeMillis() - tempoInicial;
        System.out.println("Counting Sort Executado em = " + (tempoFinal) + " ms\nCom " + contador + " movimentos.\n");
        gravarArquivo.printf("\nCounting Sort: \n--------------------\n");
        gravarArquivo(gravarArquivo, contador, tempoFinal, dados);


        gravarArquivo.close();
    }

    public static void gravarArquivo(PrintWriter gravarArquivo, double contador, long tempoFinal, int dados[]) {
        gravarArquivo.printf("O números de movimentos foi: " + contador + ".\n");
        gravarArquivo.printf("O tempo gasto foi: " + tempoFinal + " ms.\n");
        gravarArquivo.printf("O vetor ordenado é: \n");
        for (int i = 0; i < dados.length; i++) {
            if (i == 0) {
                gravarArquivo.printf("[" + dados[i] + ", ");
            } else if (i == dados.length - 1) {
                gravarArquivo.printf(dados[i] + "]");
            } else {
                gravarArquivo.printf(dados[i] + ", ");
            }
        }
        gravarArquivo.printf("_______________________________\n\n\n");
    }

    public static void iniciarVetor(int dados[], String str[]) {
        for (int i = 0; i < str.length; i++) {
            dados[i] = Integer.parseInt(str[i]);
        }
    }
    static double gnomeSort(int arr[], int n, double contador)
    {
        int index = 0;
        while (index < n) {
            if (index == 0)
                index++;
            if (arr[index] >= arr[index - 1])
                index++;
            else {
                int temp = 0;
                temp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = temp;
                contador++;
                index--;
            }
        }
        return contador;
    }
    static void countSort(int[] arr)
    {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;
        int count[] = new int[range];
        int output[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }
    public static int[] radixSort(int[] lista) {

        int numeroDeDigitos = 1;
        int[][] balde = new int[20][lista.length > 19 ? lista.length : 20];

        for(int i = 1, ordem = 0; i <= numeroDeDigitos; ordem = 0) {

            boolean gastoDeDigitos = false;
            int[] qtdNumeros = new int[20];

            for(int num : lista) {
                int digito = (num / i) % 10;

                digito += 10;
                balde[digito][qtdNumeros[digito]++] = num;

                if(num >= (numeroDeDigitos * 10) && !gastoDeDigitos) {

                    numeroDeDigitos *= 10;
                    gastoDeDigitos = true;
                }
            }

            for(int j = 0; j < 20; j++) {
                for (int k = 0; k < qtdNumeros[j]; k++) {
                    lista[ordem++] = balde[j][k];
                }
            }

            i *= 10;
        }

        return lista;
    }
}