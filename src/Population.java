import java.util.Random;
import static java.lang.Math.round;

/**
 * Classe Population
 * Representa um conjunto de individuos que sera a nossa populacao
 * pop populacao
 * dim sera a dimensao da populacao
 * N sera o tamanho de cada individuo da populacao
 * @author Daniel Fernandes
 * @author Diogo Zacarias
 * @author Kartic Premgi
 * @version 1.0
 */

public class Population{
    private Individuo[] pop;
    private final int dim = 100;
    private final int N;

    /**
     * Construtor da Populacao
     * Inicializa a populacao com o tamanho da variavel "dim"
     * @param N tamanho de cada individuo
     * @param r random que vamos utilizar
     */
    public Population(int N,Random r){
        pop = new Individuo[dim];
        this.N = N;
        for (int i = 0; i < dim; i++) {
            pop[i] = new Individuo(new Collision(),N,r);
        }
    }

    /**
     * Getter da Pop
     * @return o array de individuos da population
     */
    public Individuo[] getPop(){
        return pop;
    }

    /**
     * Setter da Pop
     * Recebe uma populacao e atualiza a populacao
     * @param pop array que sera a nova populacao
     */
    public void setPop(Individuo[] pop) {
        this.pop = pop;
        for (int i = 0; i < dim; i++) {
            this.pop[i].calcula_colisoes();
        }
    }

    /**
     * Getter da Dim
     * @return o tamanho da populacao
     */
    public int getDim() {
        return dim;
    }

    /**
     * Setter de um indivduo da populacao
     * Faz o set de um individuo da populacao
     * @param I individuo que sera o novo individuo na posicao p
     * @param p indice a ser atualizado
     * @pre >= 0
     * @pre < dim
     */
    public void setIndividuo(Individuo I, int p){
        pop[p] = I;
    }


    /**
     * Funcao que procura na populacao a solucao
     * Sera solucao o individuo que tiver fitness igual a N, ou seja, tenha N rainhas sem colisoes
     * @return '-1' se não houver solucao, caso contraio retorna o indice da solucao
     */
    public int check_solution(){
        for(int i = 0; i < dim; i++) {
           if(pop[i].getFitness() == N){
               return i;
           }
        }
        return -1;
    }

    /**
     * Funcao que calcula um indice no intervalo [a,b]
     * @param a indice do intervalo a comecar
     * @param b indice do intervalo a terminar
     * @param u valor random gerado
     * @return um indice nesse intervalo
     */
    public static int aleatory_number(int a, int b, double u){
        return (int) (a + round(u * (b - a)));
    }

    /**
     * Funcao que gera um array de indices random
     * @param generator random a ser utilizado
     * @return um array com indices postos de forma aleatoria
     */
    private int[] random_permutation(Random generator){
        int[] v = new int[dim];
        double u;
        int x;
        for(int i = 0; i < dim; i++) {
            v[i] = i;
        }
        for(int i = 0; i < dim-1; i++){
            u = generator.nextDouble();
            x = aleatory_number(i,dim-1,u);
            int temp = v[i];
            v[i] = v[x];
            v[x] = temp;
        }
        return v;
    }

    /**
     * Funcao que ira atualizar as posicoes com o array recebido
     * @param p array com os indices random
     * @return um array de Individuos conforme os novos indices propostos pela o array "a"
     */
    public Individuo[] create_with_random_permutation(int[] p){
        Individuo[] res = new Individuo[dim];
        for (int i = 0; i < dim; i++) {
            res[i] = pop[p[i]];
        }
        return res;
    }

    /**
     * Funcao que calcula o maximo no intervalo [low,high] do array a
     * @param a array de individuos
     * @param low indice menor
     * @param high indice maior
     * @pre low >= 0
     * @pre high < dim
     * @return o indice do maximo no intervalo recebido
     */
    public int max(Individuo[] a,int low, int high){
        int indice = low;
        double max = a[low].getFitness();
        for (int i = low+1; i < high; i++) {
            if(a[i].getFitness() > max){
                max = a[i].getFitness();
                indice = i;
            }
        }
        return indice;
    }

    /**
     * Funcao que faz torneiros de tamanho s sem reposicao
     * @param s representa o tamanho do intervalo
     * @param g random que vamos utilizar
     * @return um array de individuos com os vencedores do torneio
     */
    public Individuo[] tournament_selection_without_replacement(int s, Random g){
        Individuo[] res = new Individuo[dim];
        int contador = 0;
        for (int i = 0; i < s; i++) {
            int[] r = random_permutation(g);
            Individuo[] pop_permutation = create_with_random_permutation(r);
            for (int j = 0; j < dim/s; j++) {
                int max = max(pop_permutation,j*s,(j*(s))+s);
                res[contador] = pop_permutation[max];
                contador++;
            }
        }
        return res;
    }
}
