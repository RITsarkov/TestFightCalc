//import main.utils.StatMap;
//
//import java.util.Map;
//import java.util.Set;
//
///**
// * Created by user on 18.06.2017.
// */
//public class Test {
//
//    static int doge = 40;
//
//    public static void main(String[] arg){
//        StatMap<Integer, Integer> result = new StatMap<>();
//        int ROUND = 1000000;
//        for(int i = 1; i<= ROUND; i++){
//            int hpCounter = 0;
//            int curRound = 1;
//            while (1==1){
//
//                double random = Math.random();
//                if (doge >0 && (random * 100) <= 40) {
//
//                }
//                else{
//                    hpCounter = hpCounter + 25;
//                }
//                if(hpCounter >= 6000){
//                    result.putInkKey(curRound);
//                    break;
//                }
//                curRound++;
//            }
//        }
//
//
//
//
//
//        for (Map.Entry<Integer, Integer> entry : (Set<Map.Entry>) result.entrySet()) {
//            Double d = Double.valueOf(entry.getValue())/ ROUND * 100;
//            System.out.println("Rounds: " + entry.getKey() + "; Total wins: " + entry.getValue() + "; " + formatD(d)+"%");
//        }
//
//        int extrem = 0;
//        int extremRounds = 0;
//        for(Map.Entry<Integer, Integer> e: (Set<Map.Entry>) result.entrySet()){
//            int curVal = e.getValue();
//            if(curVal > extrem) {
//                extrem = curVal;
//                extremRounds = e.getKey();
//            }
//        }
//        if(extremRounds != 0)
//            System.out.println("maxWins in " + extremRounds + " val: " + extrem);
//    }
//
//    private static String formatD(Double d){
//        return String.format("%.2f", d);
//    }
//}
//
//
