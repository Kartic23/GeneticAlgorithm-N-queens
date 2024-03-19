import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class UnitTest {

    @Test
    public void testConstrutorIndividuo() {
        int[] a = {1,3,0,2};
        Individuo parent1 = new Individuo(new Collision(),a);
        Assert.assertEquals(1,parent1.getPosicoes()[0]);
        Assert.assertEquals(3,parent1.getPosicoes()[1]);
        Assert.assertEquals(0,parent1.getPosicoes()[2]);
        Assert.assertEquals(2,parent1.getPosicoes()[3]);
        int[] b = {1,3,0,1};
        Individuo parent2 = new Individuo(new Collision(),b);
        Assert.assertEquals(1,parent2.getPosicoes()[0]);
        Assert.assertEquals(3,parent2.getPosicoes()[1]);
        Assert.assertEquals(0,parent2.getPosicoes()[2]);
        Assert.assertEquals(1,parent2.getPosicoes()[3]);
    }

    @Test
    public void testGetN() {
        Individuo parent1 = new Individuo(new Collision(),4,new Random());
        Assert.assertEquals(4,parent1.getPosicoes().length);
        Individuo parent2 = new Individuo(new Collision(),7,new Random());
        Assert.assertEquals(7, parent2.getPosicoes().length);
    }

    @Test
    public void testGetdim() {
        Population pop = new Population(5,new Random());
        Assert.assertEquals(100,pop.getDim());
    }

    @Test
    public void testNumberCollisions() {
        int[] a = {1,3,0,2};
        Individuo parent1 = new Individuo(new Collision(),a);
        Assert.assertEquals(4,(int)parent1.getFitness());
        int[] b = {1,3,0,1};
        Individuo parent2 = new Individuo(new Collision(),b);
        Assert.assertEquals(1,(int)parent2.getFitness());
    }

    @Test
    public void testsetPosicao() {
        int[] a = {1,3,0,2};
        Individuo parent1 = new Individuo(new Collision(),a);
        parent1.setPosicao(6,2);
        Assert.assertEquals(6, parent1.getPosicoes()[2]);
        parent1.setPosicao(23,3);
        Assert.assertEquals(23, parent1.getPosicoes()[3]);
    }

}
