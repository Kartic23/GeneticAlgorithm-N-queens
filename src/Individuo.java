import java.util.Random;
import static java.lang.Math.round;

/**
 * Classe Individuo
 * Representa um individuo
 * posicoes — posicoes das rainhas
 * fitness — fitness de cada individuo
 * IProblem — interface IProblem usada para obter o fitness
 * @author Daniel Fernandes
 * @author Diogo Zacarias
 * @author Kartic Premgi
 * @version 1.0
 */
public class Individuo {
    private int[] posicoes;
    private double fitness;
    private final IProblem IProblem;

    /**
     * Construtor do Individuo
     * Inicializa um individuo de tamanho N
     * @param I — interface IProblem para obter o fitness do individuo
     * @param N — tamanho da população
     * @param r — random generator
     */

    public Individuo(IProblem I,int N,Random r){
        posicoes = new int[N];
        for (int i = 0; i < N; i++) {
            posicoes[i] = i;
        }
        for (int i = 0; i < N; i++) {
            int p = r.nextInt(N);
            int temp = posicoes[i];
            posicoes[i] = posicoes[p];
            posicoes[p] = temp;
        }
        this.IProblem = I;
        calcula_colisoes();
    }




    /**
     * Construtor do Individuo
     * @param I — interface IProblem para obter o fitness do individuo
     * @param a — array que representa as posicoes das rainhas
     */
    public Individuo(IProblem I,int [] a){
        posicoes = a;
        this.IProblem = I;
    }

    /**
     * Construtor do Individuo
     * @param I — interface IProblem para obter o fitness do individuo
     */
    public Individuo(IProblem I,int N){
        posicoes = new int[N];
        for (int i = 0; i < N; i++) {
            posicoes[i] = -1;
        }
        this.IProblem = I;
    }

    /**
     * Getter do fitness
     * @return o fitness do individuo
     */
    public double getFitness(){
        return fitness;
    }

    /**
     * Getter das posicoes
     * @return o array de posicoes
     */
    public int[] getPosicoes(){
        return posicoes;
    }

    /**
     * Setter da posicao
     * faz set da posicao de uma rainha
     * @param y — coluna da rainha
     * @param p — linha da rainha
     */
    public void setPosicao(int y, int p){
        posicoes[p] = y;
    }

    /**
     * Funcao que calcula as colisoes das rainhas de cada individuo
     */
    public void calcula_colisoes(){
        this.fitness = posicoes.length - IProblem.fitness(posicoes);
    }

    /**
     * Funcão que faz o preenchimento dos campos vazios
     * @param res sera o individuo final depois do crossover
     * @return o individuo resultante do preenchimento
     */
    public Individuo check(Individuo res){
        for (int i = 0; i < res.posicoes.length; i++) {
            boolean encontrou1 = false;
            for (int j = 0; j < res.posicoes.length; j++) {
                if(res.posicoes[j] == i)
                    encontrou1 = true;
            }
            if(!encontrou1){
                for(int j = 0; j < res.posicoes.length ; j++){
                    if(res.posicoes[j] == -1){
                        res.posicoes[j] = i;
                        break;
                    }
                }
            }
        }
        return res;
    }

    /**
     * Funcao que faz crossover
     * @param father1 — individuo 1
     * @param father2 — individuo 2
     * @param generator — random generator
     * @return um array com dois individuos após o crossover
     */
    public Individuo[] crossover(Individuo father1,Individuo father2,Random generator){
        Individuo[] result = new Individuo[2];
        double u = generator.nextDouble();
        int indexinitial =1 + (int)  round(u * (father1.posicoes.length - 1));
        u = generator.nextDouble();
        int indexfinal = 1 + (int)  round(u * (father1.posicoes.length - 1));
        if(indexfinal < indexinitial){
             int temp = indexfinal;
             indexfinal = indexinitial;
             indexinitial = temp;
        }
        Individuo res1 = new Individuo(new Collision(),father1.posicoes.length);
        Individuo res2 = new Individuo(new Collision(),father1.posicoes.length);
        for (int i = indexinitial; i < indexfinal; i++){
            res1.setPosicao(father2.posicoes[i],i);
            res2.setPosicao(father1.posicoes[i],i);
        }
        res1 = check(res1);
        res2 = check(res2);
        res1.calcula_colisoes();
        res2.calcula_colisoes();
        result[0] = res1;
        result[1] = res2;
        return result;
    }


    /**
     * Funcao que faz mutation de um individuo com probabilidade de 80%
     * @param father — individuo
     * @param generator — random generator
     */
    public void mutation(Individuo father,Random generator){
        double u = generator.nextDouble();
        if(u < 0.8){
            u = generator.nextDouble();
            int index = (int) round(u * (father.posicoes.length - 1));
            u = generator.nextDouble();
            int index1 = (int) round(u * (father.posicoes.length - 1));
            int temp = father.posicoes[index];
            father.posicoes[index] = father.posicoes[index1];
            father.posicoes[index1] = temp;
            father.calcula_colisoes();
        }
    }

    /**
     * toString converte o individuo para String
     * @return o individuo em String
     */
    public String toString(){
        String result = "";
        for (int i = 0; i < posicoes.length; i++){
            for (int j = 0; j <posicoes.length; j++){
                if(j == posicoes[i])
                    result += "1 ";
                else
                    result += "0 ";
            }
            result += '\n';
        }
        return result;
    }
}