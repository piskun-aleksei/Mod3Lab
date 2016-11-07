import java.util.Random;

/**
 * Created by Brotorias on 06.11.2016.
 */
public class ConditionGenerator {
    public boolean R;
    public boolean P1;
    public boolean P2;
    private static Random rand = new Random();
    private Double RChance;
    private Double P1Chance;
    private Double P2Chance;

    private Double times;
    private Double RActual;
    private Double P1Actual;
    private Double P2Actual;

    public ConditionGenerator(Double RChance, Double P1Chance, Double P2Chance){
        this.RChance = RChance;
        this.P1Chance = P1Chance;
        this.P2Chance = P2Chance;
        times = 0D;
        RActual = 0D;
        P1Actual = 0D;
        P2Actual = 0D;
    }

    public void generate(){
        times ++;
        Double d = rand.nextDouble();
        if(d > RChance) {
            R = false;
        }
        else{
            R = true;
            RActual++;
        }
        d = rand.nextDouble();
        if(d > P1Chance) {
            P1 = false;
        }
        else{
            P1Actual++;
            P1 = true;
        }
        d = rand.nextDouble();
        if(d > P2Chance) {
            P2 = false;
        }
        else{
            P2Actual++;
            P2 = true;
        }
    }

    public boolean isR() {
        return R;
    }

    public boolean isP1() {
        return P1;
    }

    public boolean isP2() {
        return P2;
    }

    public Double getRActual() {
        return RActual/times;
    }

    public Double getP2Actual() {
        return P2Actual/times;
    }

    public Double getP1Actual() {
        return P1Actual/times;
    }
}
