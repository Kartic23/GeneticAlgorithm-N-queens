/**
 * Classe Collision implementa a interface IProblem
 * Calcula o numero de colisões de rainhas num board
 * @author Daniel Fernandes
 * @author Diogo Zacarias
 * @author Kartic Premgi
 * @version 1.0
 */
public class Collision implements IProblem{

    /**
     * Funcao que calcula o numero de colisoes da rainha
     * @param b - board atual
     * @param line - posicao da linha da rainha
     * @return - retorna o numero total de colisoes da rainha
     */
    public int n_collision(int[] b,int line) {
        int colisions = 0;
        for (int i = line; i < b.length - 1; i++) {
            if (b[line] == b[i + 1]) {
                colisions++;
            }
            if (b[line] + line == b[i + 1] + (i + 1)) {
                colisions++;
            }
            if (b[line] - line == b[i + 1] - (i + 1)) {
                colisions++;
            }
        }
        return colisions;
    }

    /**
     * Funcao que retorna o numero de colisoes do board
     * @param a - board atual
     * @return - retorna o numero total de colisoes do board
     */
    public double fitness(int[] a) {
        int result=0;
        for (int line = 0; line < a.length; line++) {
            result += n_collision(a,line);
        }
        return result;
    }
}
