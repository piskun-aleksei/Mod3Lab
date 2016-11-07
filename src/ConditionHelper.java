import java.util.HashMap;
import java.util.Map;

/**
 * Created by Brotorias on 06.11.2016.
 */
public class ConditionHelper {
    private String currentCond;
    private Double requestCount;
    private Double responseCount;
    private Double requestsDropped;
    private Double requestsInSystem;
    private Double requestsTime;
    private Map<String, Double> conditions;
    private int times;


    public ConditionHelper(){
        currentCond = new String("000");
        conditions = new HashMap<>();
        conditions.put("000", 0D);
        conditions.put("100", 0D);
        conditions.put("001", 0D);
        conditions.put("101", 0D);
        conditions.put("111", 0D);
        conditions.put("011", 0D);
        conditions.put("021", 0D);
        conditions.put("121", 0D);
        requestCount = 0D;
        responseCount = 0D;
        requestsDropped = 0D;
        requestsInSystem = 0D;
        requestsTime = 0D;
        times = 0;
    }

    public String process(ConditionGenerator cd){
        times ++;

        switch (currentCond){
            case "000":
                process000(cd);
                System.out.println(currentCond);
                break;
            case "100":
                process100(cd);
                System.out.println(currentCond);
                break;
            case "001":
                process001(cd);
                System.out.println(currentCond);
                break;
            case "101":
                process101(cd);
                System.out.println(currentCond);
                break;
            case "111":
                process111(cd);
                System.out.println(currentCond);
                break;
            case "011":
                process011(cd);
                System.out.println(currentCond);
                break;
            case "021":
                process021(cd);
                System.out.println(currentCond);
                break;
            case "121":
                process121(cd);
                System.out.println(currentCond);
                break;
            default:
                System.out.println("Unknown condition");
        }
        return currentCond;
    }

    private void process000(ConditionGenerator cd){
        if(cd.R){
            requestCount++;
            currentCond = "100";
            conditions.replace(currentCond, conditions.get(currentCond) + 1);
        }
        else{
            currentCond = "000";
            conditions.replace(currentCond, conditions.get(currentCond) + 1);
        }
        requestsTime += getCurrentRequests() * 1;
    }

    private void process100(ConditionGenerator cd){
        if(cd.P1){
            currentCond = "100";
            conditions.replace(currentCond, conditions.get(currentCond) + 1);
            if(!cd.R){
                requestCount++;
                requestsDropped++;
            }
        }
        else{
            if(cd.R) {
                currentCond = "001";
                conditions.replace(currentCond, conditions.get(currentCond) + 1);
            }
            else{
                requestCount++;
                currentCond = "101";
                conditions.replace(currentCond, conditions.get(currentCond) + 1);
            }
        }

        requestsTime += getCurrentRequests() * 1;
    }

    private void process001(ConditionGenerator cd){
        if(cd.R){
            if(cd.P2){
                currentCond = "001";
                conditions.replace(currentCond, conditions.get(currentCond) + 1);
            }
            else {
                currentCond = "000";
                conditions.replace(currentCond, conditions.get(currentCond) + 1);
                responseCount++;
            }
        }
        else{
            requestCount++;
            if(cd.P2){
                currentCond = "101";
                conditions.replace(currentCond, conditions.get(currentCond) + 1);
            }
            else {
                currentCond = "100";
                conditions.replace(currentCond, conditions.get(currentCond) + 1);
                responseCount++;
            }
        }
        requestsTime += getCurrentRequests() * 1;
    }

    private void process101(ConditionGenerator cd){
        if(cd.P1 && !cd.P2){
            currentCond = "100";
            conditions.replace(currentCond, conditions.get(currentCond) + 1);
            responseCount++;
            if(!cd.R){
                requestCount++;
                requestsDropped++;
            }
        }
        if(cd.R && !cd.P1 && !cd.P2){
            currentCond = "001";
            conditions.replace(currentCond, conditions.get(currentCond) + 1);
            responseCount++;
        }
        if((cd.P1 && cd.P2) || (!cd.R && !cd.P1 && !cd.P2)){
            currentCond = "101";
            conditions.replace(currentCond, conditions.get(currentCond) + 1);
            if(!cd.R){
                requestCount++;
                if(cd.P1){
                    requestsDropped++;
                }
            }
            if(!cd.P2){
                responseCount++;
            }
        }
        if(!cd.R && !cd.P1 && cd.P2){
            currentCond = "111";
            conditions.replace(currentCond, conditions.get(currentCond) + 1);
            requestCount++;
        }
        if(cd.R && !cd.P1 && cd.P2){
            currentCond = "011";
            conditions.replace(currentCond, conditions.get(currentCond) + 1);
        }
        requestsTime += getCurrentRequests() * 1;
    }

    private void process111(ConditionGenerator cd){
        if(cd.P1 && !cd.P2){
            currentCond = "101";
            conditions.replace(currentCond, conditions.get(currentCond) + 1);
            responseCount++;
            if(!cd.R){
                requestCount++;
                requestsDropped++;
            }
        }
        if((!cd.R && !cd.P1 && !cd.P2) || (cd.P1 && cd.P2)){
            currentCond = "111";
            conditions.replace(currentCond, conditions.get(currentCond) + 1);
            if(!cd.R){
                requestCount++;
                if(cd.P1){
                    requestsDropped++;
                }
            }
        }
        if(cd.R && !cd.P1 && !cd.P2){
            currentCond = "011";
            conditions.replace(currentCond, conditions.get(currentCond) + 1);
            responseCount++;
        }
        if(!cd.P1 && cd.P2){
            if(cd.R) {
                currentCond = "021";
                conditions.replace(currentCond, conditions.get(currentCond) + 1);
            }
            else{
                currentCond = "121";
                conditions.replace(currentCond, conditions.get(currentCond) + 1);
                requestCount++;
            }
        }
        requestsTime += getCurrentRequests() * 1;
    }

    private void process011(ConditionGenerator cd){
        if(cd.R){
            if(cd.P2){
                currentCond = "011";
                conditions.replace(currentCond, conditions.get(currentCond) + 1);
            }
            else{
                currentCond = "001";
                conditions.replace(currentCond, conditions.get(currentCond) + 1);
                responseCount++;
            }
        }
        else{
            requestCount++;
            if(cd.P2){
                currentCond = "111";
                conditions.replace(currentCond, conditions.get(currentCond) + 1);
            }
            else{
                currentCond = "101";
                conditions.replace(currentCond, conditions.get(currentCond) + 1);
                responseCount++;
            }
        }
        requestsTime += getCurrentRequests() * 1;
    }

    private void process021(ConditionGenerator cd){
        if(cd.R){
            if(cd.P2){
                currentCond = "021";
                conditions.replace(currentCond, conditions.get(currentCond) + 1);
            }
            else{
                currentCond = "011";
                conditions.replace(currentCond, conditions.get(currentCond) + 1);
                responseCount++;
            }
        }
        else{
            requestCount++;
            if(cd.P2){
                currentCond = "121";
                conditions.replace(currentCond, conditions.get(currentCond) + 1);
            }
            else{
                currentCond = "111";
                conditions.replace(currentCond, conditions.get(currentCond) + 1);
                responseCount++;
            }
        }
        requestsTime += getCurrentRequests() * 1;
    }

    private void process121(ConditionGenerator cd){
        if(cd.P1){
            if(cd.P2){
                currentCond = "121";
                conditions.replace(currentCond, conditions.get(currentCond) + 1);
                if(!cd.R){
                    requestCount++;
                    requestsDropped++;
                }
            }
            else{
                currentCond = "111";
                conditions.replace(currentCond, conditions.get(currentCond) + 1);
                if(!cd.R){
                    requestCount++;
                    requestsDropped++;
                }
            }
        }
        else{
            if(cd.R){
                currentCond = "021";
                conditions.replace(currentCond, conditions.get(currentCond) + 1);
                if(!cd.P2){
                    responseCount++;
                }
                else {
                    requestsDropped++;
                }
            }
            else{
                currentCond = "121";
                conditions.replace(currentCond, conditions.get(currentCond) + 1);
                requestCount++;
                requestsDropped++;
            }
        }
        requestsTime += getCurrentRequests() * 1;
    }

    private int getCurrentRequests(){
        return Integer.parseInt(String.valueOf(currentCond.charAt(0))) + Integer.parseInt(String.valueOf(currentCond.charAt(1))) + Integer.parseInt(String.valueOf(currentCond.charAt(2)));
    }

    public Double getTime(){
        return requestsTime/requestCount;
    }

    public Double getProbability(String cond){
        Double t = new Double(times);
        return conditions.get(cond)/t;
    }

    public Double getQ(){
        return responseCount/requestCount;
    }
}
