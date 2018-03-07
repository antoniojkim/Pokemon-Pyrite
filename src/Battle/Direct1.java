/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Battle;

import Main.p;

import Pokemon.BattlePokemon;
import Main.Battle;
import Main.Main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Hurricane Kat
 */
public class Direct1{
    
    public static void getEffect(String move, BattlePokemon attacker, BattlePokemon defender, List<List<String>> animations){
//        if (!BattleMoves.multimovelist.contains(move))
//        {
//            //animate move here but NOT taking damage
//        }
if (move.equals("Transform")){
    animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" transformed!")));
    attacker.getMoveset().addAll(defender.getMoveset());
    attacker.getPp().addAll(defender.getPp());
    for (int x=0; x<attacker.getMoveset().size(); x++){
        attacker.getPp().get(x)[0]=5;
    }
    attacker.setImage(defender.getImage());
    attacker.setBackimage(defender.getBackimage());
    
}
else if (move.equals("Fury Attack")||move.equals("Fury Swipes")||move.equals("Double Slap")||move.equals("Comet Punch")||move.equals("Pin Missile")||move.equals("Spike Cannon")||move.equals("Bone Rush")||move.equals("Barrage")||move.equals("Arm Thrust")){
    int counter = p.randomint(2, 5);
    animations.add(new ArrayList(Arrays.asList("0", "It hit " + counter + " times!")));
    getEffect(move, attacker, defender, counter, animations);
}
else if (move.equals("Pay Day")){
    BattleMoves.damager(move, attacker, defender, animations);
    Battle.pay += attacker.getLevel()*5;
}
else if (move.equals("Fire Punch")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.10){
        defender.setStatus(new Status.Burn(defender), animations);
    }
}
else if (move.equals("Ice Punch")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.10){
        defender.setStatus(new Status.Frozen(defender), animations);
    }
}
else if (move.equals("Thunder Punch")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.10){
        defender.setStatus(new Status.Paralysis(defender), animations);
    }
}
else if (move.equals("Guillotine")||move.equals("Fissure")||move.equals("Horn Drill")){
    BattleMoves.damager(defender, defender.getStats()[0][0], animations);
}
else if (move.equals("Swords Dance")){
    attacker.adjustStats(2, 1, animations);
}
//        else if (move.equals("Razor Wind")){
//            if (attacker.getMultiTurnCount()<1){
//                attacker.setMultiTurnCount(2);
//            }
//            if (attacker.getMultiTurnCount()==2){
//                animations.add(new ArrayList(Arrays.asList("0", attacker.getName() + " whipped up a whirlwind!")));
//            }
//            else if (attacker.getMultiTurnCount()==1){
//                animations.add(new ArrayList(Arrays.asList("0", attacker.getName() + " used " + move + "!")));
//                BattleMoves.damager(move, attacker, defender, animations);
//            }
//        }
//        else if (move.equals("Thrash")||move.equals("Petal Dance")||move.equals("Outrage")){
//            if (attacker.getMultiTurnCount()<1){
//                attacker.setMultiTurnCount(p.randomint(2, 3));
//            }
//            if (attacker.getMultiTurnCount()>0){
//                BattleMoves.damager(move, attacker, defender, animations);
//            }
//            if (attacker.getMultiTurnCount()==1){
//                attacker.setStatus(new Status.Confusion(), animations);
//            }
//        }
//        else if (move.equals("Fly")){
//            if (attacker.getMultiTurnCount()<1){
//                attacker.setMultiTurnCount(2);
//            }
//            if (attacker.getMultiTurnCount()==2){
//                animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" flew up high!")));
//            }
//            else if (attacker.getMultiTurnCount()==1){
//                animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" used " + move + "!")));
//                BattleMoves.damager(move, attacker, defender, animations);
//            }
//
//        }
else if (move.equals("Double Kick")||move.equals("Twineedle")){
    getEffect(move, attacker, defender, 2,animations);
}
else if (move.equals("Rolling Kick")||move.equals("Headbutt")||move.equals("Bite")||move.equals("Stomp")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.30){
        defender.setStatus(new Status.Flinch(), animations);
    }
}
else if (move.equals("Sand Attack")){
    defender.adjustStats(-1, 6,animations);
}
else if (move.equals("Body Slam")||move.equals("Low Kick")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.30){
        defender.setStatus(new Status.Paralysis(defender), animations);
    }
}
//        else if (move.equals("Hyper Beam")||move.equals("Hydro Cannon")||move.equals("Blast Burn")){
//            if (attacker.getMultiTurnCount()<1){
//                attacker.setMultiTurnCount(2);
//            }
//            if (attacker.getMultiTurnCount()==2){
//                animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" used " + move + "!")));
//                BattleMoves.damager(move, attacker, defender, animations);
//            }
//            else if (attacker.getMultiTurnCount()==1){
//                animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" had to recharge.")));
//            }
//        }

else if (move.equals("Wrap")){
    BattleMoves.damager(move, attacker, defender, animations);
    defender.setStatus(new Status.Wrap(), animations);
}
else if (move.equals("Take Down")){
    int damage = BattleMoves.calcDamage(move, attacker.getPokemon(), defender.getPokemon(), animations);
    BattleMoves.damager(defender, damage, animations);
    animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" was hurt by recoil!")));
    BattleMoves.damager(attacker, damage/4, animations);
}
else if (move.equals("Double-edge")){
    int damage = BattleMoves.calcDamage(move, attacker.getPokemon(), defender.getPokemon(), animations);
    BattleMoves.damager(defender, damage, animations);
    animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" was hurt by recoil!")));
    BattleMoves.damager(attacker, damage/3, animations);
}

else if (move.equals("Tail Whip")){
    defender.adjustStats(-1, 2,animations);
}
else if (move.equals("Poison Sting")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.3){
        defender.setStatus(new Status.Poison(defender), animations);
    }
}
else if (move.equals("Leer")){
    defender.adjustStats(-1, 2,animations);
}
else if (move.equals("Growl")){
    defender.adjustStats(-1, 1,animations);
}
else if (move.equals("Sing")){
    defender.setStatus(new Status.Sleep(defender), animations);
}
else if (move.equals("Sonic Boom")){
    BattleMoves.damager(defender, 20, animations);
}
else if (move.equals("Supersonic")||move.equals("Confuse Ray")||move.equals("Sweet Kiss")){
    defender.setStatus(new Status.Confusion(), animations);
}
else if (move.equals("Acid")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.10){
        defender.adjustStats(-1, 2,animations);
    }
}
else if (move.equals("Ember")||move.equals("Flamethrower")||move.equals("Flame Wheel")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.10){
        defender.setStatus(new Status.Burn(defender), animations);
    }
}
else if (move.equals("Mist")){
    attacker.setStatus(new Status.Mist(), animations);
}
else if (move.equals("Surf")){
    BattleMoves.damager(move, attacker, defender, animations);
    
}
else if (move.equals("Ice Beam")||move.equals("Blizzard")||move.equals("Powder Snow")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.10){
        defender.setStatus(new Status.Frozen(defender), animations);
    }
}
else if (move.equals("Bubble Beam")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.10){
        defender.adjustStats(-1, 5,animations);
    }
}
else if (move.equals("Aurora Beam")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.10){
        defender.adjustStats(-1, 1,animations);
    }
}
else if (move.equals("Seismic Toss")){
    BattleMoves.damager(defender, attacker.getLevel(), animations);
}
else if (move.equals("Leech Seed")){
    defender.setStatus(new Status.LeechSeed(attacker), animations);
}
else if (move.equals("Absorb")||move.equals("Mega Drain")||move.equals("Giga Drain")){
    int damage = BattleMoves.calcDamage(move, attacker.getPokemon(), defender.getPokemon(), animations);
    BattleMoves.damager(defender, damage, animations);
    BattleMoves.healer(attacker, damage, animations);
}
else if (move.equals("Growth")){
    attacker.adjustStats(1, 3,animations);
    
}
//        else if (move.equals("Solar Beam")){
//            if (attacker.getMultiTurnCount()<1){
//                attacker.setMultiTurnCount(2);
//            }
//            if (attacker.getMultiTurnCount()==2){
//                animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" absorbed sunlight.")));
//            }
//            else if (attacker.getMultiTurnCount()==1){
//                BattleMoves.damager(move, attacker, defender, animations);
//                animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" used Solar Beam!")));
//            }
//        }
else if (move.equals("Poison Powder")||move.equals("Poison Gas")){
    defender.setStatus(new Status.Poison(defender), animations);
}
else if (move.equals("Stun Spore")||move.equals("Thunder Wave")|move.equals("Glare")){
    defender.setStatus(new Status.Paralysis(defender), animations);
}
else if (move.equals("Sleep Powder")||move.equals("Spore")){
    defender.setStatus(new Status.Sleep(defender), animations);
}
else if (move.equals("String Shot")){
    defender.adjustStats(-1, 5,animations);
}
else if (move.equals("Dragon Rage")){
    BattleMoves.damager(defender, 40, animations);
}
else if (move.equals("Fire Spin")){
    BattleMoves.damager(move, attacker, defender, animations);
    defender.setStatus(new Status.FireSpin(), animations);
}
else if (move.equals("Thunder Shock")||move.equals("Thunderbolt")||move.equals("Dragon Breath")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.10){
        defender.setStatus(new Status.Paralysis(defender), animations);
    }
}
else if (move.equals("Thunder")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.30){
        defender.setStatus(new Status.Paralysis(defender), animations);
    }
}
else if (move.equals("Earthquake")){
    BattleMoves.damager(move, attacker, defender, animations);
}
//        else if (move.equals("Dig")){
//            if (attacker.getMultiTurnCount()<1){
//                attacker.setMultiTurnCount(2);
//            }
//            if (attacker.getMultiTurnCount()==2){
//                animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" dug underground!")));
//            }
//            else if (attacker.getMultiTurnCount()==1){
//                animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" used " + move + "!")));
//                BattleMoves.damager(move, attacker, defender, animations);
//            }
//        }
else if (move.equals("Toxic")){
    defender.setStatus(new Status.Toxic(defender), animations);
}
else if (move.equals("Confusion")||move.equals("Psybeam")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.1){
        defender.setStatus(new Status.Confusion(), animations);
    }
}
else if (move.equals("Psychic")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.10){
        defender.adjustStats(-1, 4,animations);
    }
}
else if (move.equals("Hypnosis")){
    defender.setStatus(new Status.Sleep(defender), animations);
}
else if (move.equals("Meditate")){
    attacker.adjustStats(1, 1,animations);
}
else if (move.equals("Agility")){
    attacker.adjustStats(2, 5,animations);
}
else if (move.equals("Night Shade")){
    BattleMoves.damager(defender, attacker.getLevel(),animations);
}
else if (move.equals("Screech")){
    defender.adjustStats(-2, 2,animations);
}
else if (move.equals("Double Team")){
    attacker.adjustStats(1, 7,animations);
}
else if (move.equals("Recover")||move.equals("Soft-Boiled")||move.equals("Slack Off")){
    BattleMoves.healer(attacker, attacker.getStats()[0][1]/2, animations);
}
else if (move.equals("Harden")){
    attacker.adjustStats(1, 2,animations);
}
else if (move.equals("Minimize")){
    attacker.adjustStats(1, 7,animations);
}
else if (move.equals("Smokescreen")){
    defender.adjustStats(-1, 6,animations);
}
else if (move.equals("Withdraw")){
    attacker.adjustStats(1, 2,animations);
}
else if (move.equals("Defense Curl")){
    attacker.adjustStats(1, 2,animations);
}
else if (move.equals("Barrier")){
    attacker.adjustStats(2, 2,animations);
}
else if (move.equals("Light Screen")){
    attacker.adjustStats(1, 4,animations);
}
else if (move.equals("Haze")){
    for (int x=1; x<6; x++){
        attacker.getStats()[x][0]=attacker.getStats()[x][1];
        attacker.getStats()[x][0]=0;
    }
    attacker.setStatus(new Status.Mist(), animations);
}
else if (move.equals("Reflect")){
    attacker.adjustStats(1, 2,animations);
}

else if (move.equals("Metronome")){
    int moveIndex = p.randomint(1, BattleMoves.moveindex.size()-2);
    attacker.setMetronome(true);
    attacker.setMove(BattleMoves.moveindex.get(moveIndex));
    MoveEffect.getEffect(attacker, defender, animations);
}

else if (move.equals("Self-destruct")||move.equals("Explosion")){
    BattleMoves.damager(move, attacker, defender, animations);
    BattleMoves.damager(attacker, attacker.getStats()[0][0], animations);
}
else if (move.equals("Lick")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.30){
        defender.setStatus(new Status.Paralysis(defender), animations);
    }
}
else if (move.equals("Smog")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.40){
        defender.setStatus(new Status.Poison(defender), animations);
    }
}
else if (move.equals("Sludge")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.30){
        defender.setStatus(new Status.Poison(defender), animations);
    }
}
else if (move.equals("Bone Club")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.10){
        defender.setStatus(new Status.Flinch(), animations);
    }
}
else if (move.equals("Fire Blast")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.30){
        defender.setStatus(new Status.Burn(defender), animations);
    }
}
//else if (move.equals("Skull Bash")){
//    if (attacker.getMultiTurnCount()<1){
//        attacker.setMultiTurnCount(2);
//    }
//    if (attacker.getMultiTurnCount()==2){
//        attacker.adjustStats(1, 2,animations);
//        animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" drew in its head.")));
//    }
//    else if (attacker.getMultiTurnCount()==1){
//        animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" used Skull Bash!")));
//        BattleMoves.damager(move, attacker, defender, animations);
//    }
//}
else if (move.equals("Constrict")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.10){
        defender.adjustStats(-1, 5,animations);
    }
}
else if (move.equals("Amnesia")){
    attacker.adjustStats(2, 4,animations);
}
else if (move.equals("Dream Eater")){
    if (defender.checkStatus("Sleep")){
        int damage = BattleMoves.calcDamage(move, attacker.getPokemon(), defender.getPokemon(), animations);
        BattleMoves.damager(defender, damage, animations);
        BattleMoves.healer(attacker, damage/2, animations);
    }
    else{
        animations.add(new ArrayList<>(Arrays.asList("0", "It had no effect.")));
    }
}
else if (move.equals("Lovely Kiss")){
    defender.setStatus(new Status.Sleep(defender), animations);
}
//else if (move.equals("Sky Attack")){
//    if (attacker.getMultiTurnCount()<1){
//        attacker.setMultiTurnCount (2);
//    }
//    if (attacker.getMultiTurnCount()==2){
//        animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" had to recharge.")));
//    }
//    else if (attacker.getMultiTurnCount()==1){
//        BattleMoves.damager(move, attacker, defender, animations);
//        animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" had to recharge.")));
//    }
//}

else if (move.equals("Bubble")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.30){
        defender.adjustStats(-1, 5,animations);
    }
}
else if (move.equals("Dizzy Punch")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.20){
        defender.setStatus(new Status.Confusion(), animations);
    }
}
else if (move.equals("Flash")){
    defender.adjustStats(-1, 6,animations);
}
else if (move.equals("Splash")){
    animations.add(new ArrayList<>(Arrays.asList("0", "It had no effect.")));
}

else if (move.equals("Acid Armor")){
    attacker.adjustStats(2, 2,animations);
}
else if (move.equals("Bonemerang")){
    getEffect(move, attacker, defender, 2,animations);
}
else if (move.equals("Rest")){
    attacker.setStatus(new Status.Sleep(attacker), animations);
    BattleMoves.healer(attacker, attacker.getStats()[0][1]/2, animations);
}
else if (move.equals("Rock Slide")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.30){
        defender.setStatus(new Status.Flinch(), animations);
    }
}
else if (move.equals("Hyper Fang")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.10){
        defender.setStatus(new Status.Flinch(), animations);
    }
}
else if (move.equals("Sharpen")){
    attacker.adjustStats(1, 1,animations);
}
else if (move.equals("Tri Attack")){
    BattleMoves.damager(move, attacker, defender, animations);
    double chance = Math.random();
    if (chance<0.07){
        defender.setStatus(new Status.Burn(defender), animations);
    }
    else if (chance<0.14){
        defender.setStatus(new Status.Frozen(defender), animations);
    }
    else if (chance<0.21){
        defender.setStatus(new Status.Paralysis(defender), animations);
    }
}
else if (move.equals("Super Fang")){
    BattleMoves.damager(defender, (int)(Math.ceil(defender.getStats()[0][0]/2.0)), animations);
}
else if (move.equals("Struggle")){
    BattleMoves.damager(move, attacker, defender, animations);
    BattleMoves.damager (attacker, attacker.getStats()[0][1]/6, animations);
    animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" was hurt by recoil!")));
}
else if (move.equals("Triple Kick")){
    getEffect(move, attacker, defender, 3,animations);
}
else if (move.equals("Spider Web")){
    defender.setStatus(new Status.Trapped(),animations);
}
else if (move.equals("Nightmare")){
    boolean asleep = false;
    if (defender.checkStatus("Sleep")){
        defender.setStatus(new Status.Nightmare(), animations);
    }
    
}
else if (move.equals("Snore")||move.equals("Sleep Talk")){
    if (attacker.checkStatus("Sleep")){
        BattleMoves.damager(move, attacker, defender, animations);
    }
    else{
        animations.add(new ArrayList<>(Arrays.asList("0", "It had no effect.")));
    }
}
else if (move.equals("Curse")){
    defender.setStatus(new Status.Curse(), animations);
}
else if (move.equals("Cotton Spore")){
    
}
else if (move.equals("Reversal")){
    double ratio = attacker.getStats()[0][0]/attacker.getStats()[0][1];
    if (ratio<0.25){
        BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(move))[3] = "100";
    }
    else if (ratio<0.5){
        BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(move))[3] ="75";
    }
    else if (ratio<0.75){
        BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(move))[3] = "50";
    }
    else{
        BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(move))[3] = "25";
    }
    BattleMoves.damager(move, attacker, defender, animations);
}
else if (move.equals("Protect")||move.equals("Detect")){
    attacker.setStatus(new Status.Protect(), animations);
}
else if (move.equals("Scary Face")){
    defender.adjustStats(-2, 5,animations);
}
else if (move.equals("Belly Drum")){
    if (attacker.getStats()[0][0]<attacker.getStats()[0][1]/2){
        animations.add(new ArrayList(Arrays.asList("0", "HP is too low to use move.")));
    }
    else{
        BattleMoves.damager(attacker, attacker.getStats()[0][1]/2, animations);
        attacker.getStats()[1][2]=6;
        attacker.adjustStats(0, 1,animations);
    }
    
}
else if (move.equals("Sludge Bomb")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.30){
        defender.setStatus(new Status.Poison(defender), animations);
    }
}
else if (move.equals("Mud-slap")){
    BattleMoves.damager(move, attacker, defender, animations);
    defender.adjustStats(-1, 6,animations);
}
else if (move.equals("Octazooka")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.50){
        defender.adjustStats(-1, 6,animations);
    }
}
else if (move.equals("Zap Cannon")){
    BattleMoves.damager(move, attacker, defender, animations);
    defender.setStatus(new Status.Paralysis(defender), animations);
}
else if (move.equals("Perish Song")){
    defender.setStatus(new Status.PerishSong(), animations);
    attacker.setStatus(new Status.PerishSong(), animations);
}
else if (move.equals("Icy Wind")){
    BattleMoves.damager(move, attacker, defender, animations);
    defender.adjustStats(-1,5,animations);
}
else if (move.equals("Endure")){
    attacker.setStatus(new Status.Endure(), animations);
}
else if (move.equals("Charm")){
    defender.adjustStats(-2, 1,animations);
}
else if (move.equals("False Swipe")){
    int damage = BattleMoves.calcDamage(move, attacker.getPokemon(), defender.getPokemon(), animations);
    if (defender.getStats()[0][0]-damage<=0){
        BattleMoves.damager(defender, defender.getStats()[0][0]-1, animations);
    }
    else{
        BattleMoves.damager(defender, damage, animations);
    }
}
else if (move.equals("Swagger")){
    defender.adjustStats(2, 1,animations);
    defender.setStatus(new Status.Confusion(), animations);
}
else if (move.equals("Milk Drink")){
    BattleMoves.healer(attacker, 100, animations);
}
else if (move.equals("Spark")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.30){
        defender.setStatus(new Status.Paralysis(defender), animations);
    }
}
else if (move.equals("Mean Look")){
    defender.setStatus(new Status.Trapped(), animations);
}
else if (move.equals("Attract")){
    defender.setStatus(new Status.Infatuation(attacker), animations);
}
else if (move.equals("Present")){
    double probability = Math.random();
    if (probability<0.25){
        BattleMoves.damager(defender, 10, animations);
    }
    else if (probability<0.5){
        BattleMoves.damager(defender, 40, animations);
    }
    else if (probability<0.75){
        BattleMoves.damager(defender, 70, animations);
    }
    else{
        BattleMoves.healer(defender, 50, animations);
    }
}
else if (move.equals("Pain Split")){
    int hp = (attacker.getStats()[0][0] + defender.getStats()[0][0])/2;
    if (attacker.getStats()[0][0]>hp){
        BattleMoves.damager(attacker, attacker.getStats()[0][0]-hp, animations);
        BattleMoves.healer(defender, hp-defender.getStats()[0][0], animations);
    }
    else if (defender.getStats()[0][0]<hp){
        BattleMoves.damager(defender, defender.getStats()[0][0]-hp, animations);
        BattleMoves.healer(attacker, hp-attacker.getStats()[0][0], animations);
    }
}
else if (move.equals("Sacred Fire")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.50){
        defender.setStatus(new Status.Burn(defender), animations);
    }
}
else if (move.equals("Magnitude")){
    String magnitude = Integer.toString(p.randomint(1, 10));
    animations.add(new ArrayList(Arrays.asList("0", "Magnitude " + magnitude + "!")));
    BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(move))[3] = magnitude;
    BattleMoves.damager(move, attacker, defender, animations);
}
else if (move.equals("Dynamic Punch")){
    BattleMoves.damager(move, attacker, defender, animations);
    defender.setStatus(new Status.Confusion(), animations);
}
else if (move.equals("Encore")){
    defender.setStatus(new Status.Encore(attacker.getMove()), animations);
}
else if (move.equals("Rapid Spin")){
    BattleMoves.damager(move, attacker, defender, animations);
    for (int x=0; x<defender.getStatuses().size(); x++){
        if (defender.getStatuses().get(x).getClass().getSuperclass().getName().equals("Status.BindingStatus")){
            defender.resetStatus(defender.getStatuses().get(x));
        }
    }
}
else if (move.equals("Sweet Scent")){
    defender.adjustStats(-1, 7,animations);
}
else if (move.equals("Iron Tail")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.30){
        defender.adjustStats(-1, 2,animations);
    }
}
else if (move.equals("Metal Claw")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.10){
        defender.adjustStats(1, 1,animations);
    }
}
else if (move.equals("Morning Sun")||move.equals("Synthesis")||move.equals("Moonlight")){
    BattleMoves.healer(attacker, attacker.getStats()[0][1]/2, animations);
}
else if (move.equals("Hidden Power")){
    BattleMoves.damager(defender, p.randomint(10, 60), animations);
}
else if (move.equals("Twister")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.10){
        defender.setStatus(new Status.Flinch(), animations);
    }
}
else if (move.equals("Crunch")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.20){
        defender.adjustStats(-1, 2,animations);
    }
}
else if (move.equals("Psych Up")){
    for (int x=1; x<6; x++){
        attacker.getStats()[x][2]=defender.getStats()[x][2];
        attacker.adjustStats(0,x,animations);
    }
}
else if (move.equals("Ancient Power")||move.equals("Superpower")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<=0.1){
        for (int whichStat=1; whichStat<6; whichStat++){
            attacker.adjustStats(1, whichStat,animations);
        }
    }
}
else if (move.equals("Shadow Ball")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.20){
        defender.adjustStats(-1, 4,animations);
    }
}
else if (move.equals("Whirlpool")){
    BattleMoves.damager(move, attacker, defender, animations);
    defender.setStatus(new Status.Whirlpool(), animations);
}
else if (move.equals("Beat Up")){
    BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(move))[3] = Integer.toString(10*Main.it.getParty().size());
    BattleMoves.damager(move, attacker, defender, animations);
}
else if (move.equals("Fake Out")){
    BattleMoves.damager(move, attacker, defender, animations);
    defender.setStatus(new Status.Flinch(), animations);
}
else if (move.equals("Heat Wave")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.10){
        defender.setStatus(new Status.Burn(defender), animations);
    }
}
else if (move.equals("Flatter")){
    defender.adjustStats(2,3,animations);
    defender.setStatus(new Status.Confusion(), animations);
}
else if (move.equals("Will-O-Wisp")){
    defender.setStatus(new Status.Burn(defender), animations);
}
else if (move.equals("Facade")){
    if (attacker.getNonVolatileStatus()!=-1){
        BattleMoves.damager(attacker, BattleMoves.calcDamage(move, attacker.getPokemon(), defender.getPokemon(), animations)*2, animations);
    }
    else{
        BattleMoves.damager(move, attacker, defender, animations);
    }
}
else if (move.equals("Smelling Salts")){
    if (defender.getNonVolatileStatus()==(2)){
        defender.getStats()[0][0] -= 2*BattleMoves.calcDamage(move, attacker.getPokemon(), defender.getPokemon(), animations);
        defender.setNonVolatileStatus (-1);
    }
    else{
        BattleMoves.damager(move, attacker, defender, animations);
    }
}
else if (move.equals("Charge")){
    attacker.adjustStats(1, 4,animations);
}
else if (move.equals("Wish")){
    attacker.setStatus(new Status.Wish(), animations);
}
else if (move.equals("Ingrain")){
    attacker.setStatus(new Status.Ingrain(attacker), animations);
}
else if (move.equals("Endeavor")){
    int damage = defender.getStats()[0][0]-attacker.getStats()[0][0];
    if (damage>0){
        BattleMoves.damager(defender, damage, animations);
    }
    else{
        animations.add(new ArrayList<>(Arrays.asList("0", "It had no effect.")));
    }
}
else if (move.equals("Eruption")){
    BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(move))[3] = Integer.toString(150*(attacker.getStats()[0][0]/attacker.getStats()[0][1]));
    BattleMoves.damager(move, attacker, defender, animations);
}
else if (move.equals("Refresh")){
    int num = attacker.getStatuses().size();
    for (int x=0; x<num; x++){
        attacker.resetStatus(attacker.getStatuses().get(x));
        attacker.getStatuses().remove(x);
    }
}
//else if (move.equals("Dive")){
//    if (attacker.getMultiTurnCount()<1){
//        attacker.setMultiTurnCount(2);
//    }
//    if (attacker.getMultiTurnCount()==2){
//        animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" dove underwater!")));
//    }
//    else if (attacker.getMultiTurnCount()==1){
//        animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" used " + move + "!")));
//        BattleMoves.damager(move, attacker, defender, animations);
//    }
//}
else if (move.equals("Tail Glow")){
    attacker.adjustStats(3, 3,animations);
}
else if (move.equals("Luster Purge")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.50){
        defender.adjustStats(-1, 4,animations);
    }
}
else if (move.equals("Mist Ball")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.50){
        defender.adjustStats(-1, 3,animations);
    }
}
else if (move.equals("Feather Dance")){
    defender.adjustStats(-2, 1,animations);
}


else if (move.equals("Teeter Dance")){
    defender.setStatus(new Status.Confusion(), animations);
}
else if (move.equals("Blaze Kick")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.10){
        defender.setStatus(new Status.Burn(defender), animations);
    }
}
else if (move.equals("Needle Arm")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.30){
        defender.setStatus(new Status.Flinch(), animations);
    }
}
else if (move.equals("Poison Fang")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.30){
        defender.setStatus(new Status.Toxic(defender), animations);
    }
}
else if (move.equals("Crush Claw")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.50){
        defender.adjustStats(-1, 2,animations);
    }
}

else if (move.equals("Meteor Mash")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.20){
        defender.adjustStats(1, 1,animations);
    }
}
else if (move.equals("Astonish")){
    BattleMoves.damager(move, attacker, defender, animations);
    if (Math.random()<0.30){
        defender.setStatus(new Status.Flinch(), animations);
    }
}
else{
    if (!BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(move))[3].equals("?")){
        BattleMoves.damager(move, attacker, defender, animations);
    }
};

    }
    //for all multi-hit moves (multiple in one turn)
    public static void getEffect(String move, BattlePokemon attacker, BattlePokemon defender, int counter, List<List<String>>animations){
        if (counter>0){
            BattleMoves.damager(move, attacker, defender, animations);
            getEffect(move, attacker, defender, counter-1, animations);
        }
    }
    
    
    
    
    
}
