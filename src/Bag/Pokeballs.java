package Bag;

import Main.p;
import Pokemon.BattlePokemon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Antonio's Laptop
 */
public class Pokeballs {
    
    public static int isCaught(BattlePokemon wild,  String pokeBall){
        if (pokeBall.equals("Master Ball")){
            return 4;
        }
        return getShakeCheck(wild, pokeBall, 1);
        
    }
    
    public static int getShakeCheck(BattlePokemon wild,  String pokeBall, int shakes){
        double bonusStatus=1;
        if (wild.getNonVolatileStatus()==0||wild.getNonVolatileStatus()==2||wild.getNonVolatileStatus()==3){
            bonusStatus = 1.5;
        }
        else if (wild.getNonVolatileStatus()==1 || wild.getNonVolatileStatus()==4){
            bonusStatus = 2;
        }
        double bonusBall=1;
        if (pokeBall.equals("Great Ball")){
          bonusBall = 1.5;  
        }
        else if (pokeBall.equals("Ultra Ball")){
          bonusBall = 2;
        }
        double a = 100*(((double)wild.getStats()[0][1]-(double)wild.getStats()[0][0])/(double)wild.getStats()[0][1] + 0.01)*bonusBall*bonusStatus;
        if (shakes==4){
            return 4;
        }
        if (p.randomint(0,450)>a){
            return getShakeCheck(wild, pokeBall, shakes+1);
        }
//        
//        double a = ((3*wild.getStats()[0][1]- 2*wild.getStats()[0][0])/(3*wild.getStats()[0][1]))*(rate*bonusBall*bonusStatus);    //Modified Catch Rate
//        int b = (int)(1048560/Math.sqrt(Math.sqrt(16711680/a))); // shake probability
//        int r = (int)((65536)*Math.random());
//        if (r<b){
//            return true;
//        }
        return shakes;
    }
    
}
