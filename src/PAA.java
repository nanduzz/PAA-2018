import java.util.Scanner;

public class PAA {

    public static void main(String[] args) {
        System.out.println("Entre com o array com cada valor separado por virgula (ex: 1,2,3,4,5,4,3,2,1 ): ");
        Scanner scanner = new Scanner(System.in);
        String entrada = scanner.nextLine();
        String[] array = entrada.split(",");
        int[] arrayInt = new int[array.length];
        for(int i = 0; i < array.length; i++ ){
            arrayInt[i] = Integer.valueOf(array[i]);
        }


        System.out.println("Posição do indice k:" + encontraPosicaoDeK(arrayInt, 0, arrayInt.length) + " - Valor do indice começa em 0");
        System.out.println("Array ordenado:");

        int[]arrayOrdenado = countingSort(arrayInt, arrayInt.length);
        for(int i = 0; i < arrayOrdenado.length; i++){
            System.out.print(arrayOrdenado[i]);
            if(i != arrayOrdenado.length - 1){
                System.out.print(',');
            }
        }

    }

    /**
     * Algoritimo para encontrar o K-ésimo elemento onde k-2 < k-1 < k > k+1 > k+2
     * @param array de entrada já ordenado da forma esperada pelo algoritimo
     * @param inicio indice inicial pelo qual as buscas devem ser começadas
     * @param fim indice maximo que deve ser utilizado pela busca
     * @return int contendo o valor do indice do k-ésimo elemento
     */
    public static int encontraPosicaoDeK(int[] array, int inicio, int fim) {
        if (array == null || array.length == 0) { //arrays invalidos
            return -1;
        } else if (array.length == 1) { // caso o array tenha apenas uma posição este é o indice médio
            return 0;
        }
        int pos = (inicio + fim) / 2;
        if ((pos - 1) < 0 && array[pos] > array[pos + 1]) { //caso esteja na primeira posição
            return pos;
        } else if ((array[pos - 1] < array[pos] && (pos + 1) < array.length && array[pos] > array[pos + 1]) //casos de posição intermediaria
                || (array[pos - 1] < array[pos] && (pos + 1) >= array.length)) { // caso esteja na ultima posição
            return pos;
        } else if (array[pos - 1] < array[pos] && array[pos] < array[pos + 1]) {
            inicio = pos; //seta uma nova posição inicial para busca
        } else {
            fim = pos; //seta uma nova posição final para a busca
        }
        pos = PAA.encontraPosicaoDeK(array, inicio, fim); //chamada recursiva
        return pos;
    }


    /**
     * Algoritimo de ordenação baseado nos slides aprensetados em aula
     * @param a array de entrada
     * @param k numero de elementos do array
     * @return array de interiso ordenado em tempo O(n)
     */
    public static int[] countingSort(int[] a, int k) {
        int tam = a.length;
        int[] c = new int[k + 1];
        int[] b = new int[tam];

        for (int j = 0; j <= tam - 1; j++) {
            c[a[j]] = c[a[j]] + 1;
        }

        for (int i = 1; i <= k; i++) {
            c[i] = c[i] + c[i - 1];
        }

        for (int j = tam - 1; j >= 0; j--) {
            b[c[a[j]] - 1] = a[j];
            c[a[j]] = c[a[j]] - 1;
        }

        return b;
    }
}
