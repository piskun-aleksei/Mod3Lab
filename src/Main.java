import java.util.Random;

/**
 * Created by Brotorias on 06.11.2016.
 */
public class Main {
    public static void main(String... args) {
        ConditionGenerator generator = new ConditionGenerator(0.75, 0.7, 0.65);
        ConditionHelper helper = new ConditionHelper();

        for(int i = 0; i < 100000; i ++){
            generator.generate();
            helper.process(generator);
        }

        System.out.println("Prob 000: " + helper.getProbability("000"));
        System.out.println("Prob 100: " + helper.getProbability("100"));
        System.out.println("Prob 001: " + helper.getProbability("001"));
        System.out.println("Prob 101: " + helper.getProbability("101"));
        System.out.println("Prob 111: " + helper.getProbability("111"));
        System.out.println("Prob 011: " + helper.getProbability("011"));
        System.out.println("Prob 021: " + helper.getProbability("021"));
        System.out.println("Prob 121: " + helper.getProbability("121"));
        System.out.println();
        System.out.println("Average time in system: " + helper.getTime());
        System.out.println();
        System.out.println("Q: " + helper.getQ());
        /*System.out.println();
        System.out.println("R Act: " + generator.getRActual());
        System.out.println();
        System.out.println("P1 Act: " + generator.getP1Actual());
        System.out.println();
        System.out.println("P2 Act: " + generator.getP2Actual());*/
    }
}
