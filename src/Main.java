import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Random r = new Random(0);
        Population population = new Population(N,r);
        GeneticAlgorithm ga = new GeneticAlgorithm(population);
        double start = System.currentTimeMillis();
        Individuo result = ga.genetic_algoritm(r);
        System.out.println((System.currentTimeMillis() - start)/1000 + " seconds");
        System.out.println(result);
    }
}
