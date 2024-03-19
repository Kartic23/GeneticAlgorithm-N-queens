import java.util.Arrays;
import java.util.Random;
/**
 * Classe GeneticAlgorithm.
 * Algoritmo de IA que usamos para resolver o problema n-queens
 * pop representa a populacao
 * @author Daniel Fernandes
 * @author Diogo Zacarias
 * @author Kartic Premgi
 * @version 1.0
 */
public class GeneticAlgorithm{
    Population pop;

    /**
     * Construtor do GeneticAlgorithm
     * @param population - representa a populacao
     */
    public GeneticAlgorithm(Population population){
        pop = population;
    }

    /**
     * Funcao que poe em pratica o algoritmo "GeneticAlgorithm"
     * @param g representa o gerador de random
     * @pre n > 3
     * @return a primeira solucao encontrada
     */
    public Individuo genetic_algoritm(Random g){
        int index;
        int n_geracoes = 0;
        Individuo[] res;
        while(true){
            index = pop.check_solution();
            if(index != -1){
                System.out.println("Nº de gerações: " + n_geracoes);
                return pop.getPop()[index];
            }


            Individuo[] temp = pop.tournament_selection_without_replacement(20,g);
            pop.setPop(temp);
            for (int i = 0; i < pop.getDim(); i+=2){
                res = pop.getPop()[i].crossover(pop.getPop()[i],pop.getPop()[i+1],g);
                pop.setIndividuo(res[0],i);
                pop.setIndividuo(res[1],i+1);

            }
            for (int i = 0; i < pop.getDim(); i++){
                pop.getPop()[i].mutation(pop.getPop()[i],g);
            }
            n_geracoes++;
        }
    }
}