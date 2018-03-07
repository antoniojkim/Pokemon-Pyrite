/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Battle;

import Battle.BattleMoves;
import Main.p;
import Pokemon.BattlePokemon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//use unfinished tag, left off here
/**
 *
 * @author katrina :D
 *  hardcoding in philosophy class
 */
public class Direct2{
    
    public static void getEffect(String move, BattlePokemon attacker, BattlePokemon defender, List<List<String>> animations){
        if (move.equals("Aromatherapy")){
            attacker.getStatuses().clear();
        }
        else if (move.equals("Fake Tears")){
            defender.adjustStats(-2, 4,animations);
        }
        else if (move.equals("Overheat")||move.equals("Leaf Storm")||move.equals("Draco Meteor")){
            defender.getStats()[0][0] = defender.getStats()[0][0]-BattleMoves.calcDamage(move, attacker.getPokemon(), defender.getPokemon(), animations);
            attacker.adjustStats(-2, 3,animations);
        }
        
        else if (move.equals("Rock Tomb")){
            BattleMoves.damager(move, attacker, defender, animations);
            defender.adjustStats(-1, 5,animations);
        }
        else if (move.equals("Silver Wind")){
            BattleMoves.damager(move, attacker, defender, animations);
            if (Math.random()<=0.1){
                for (int whichStat=1; whichStat<6; whichStat++){
                    attacker.adjustStats(1, whichStat,animations);
                }
            }
        }
        else if (move.equals("Metal Sound")){
            defender.adjustStats(-2, 4,animations);
        }
        else if (move.equals("Grass Whistle")){
            defender.setStatus(new Status.Sleep(defender), animations);
        }
        else if (move.equals("Tickle")){
            defender.adjustStats(-1, 1,animations);
            defender.adjustStats(-1, 2,animations);
        }
        else if (move.equals("Cosmic Power")){
            attacker.adjustStats(1, 2,animations);
            attacker.adjustStats(1, 4,animations);
        }
        else if (move.equals("Water Spout")||move.equals("Wring Out")){
            BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(move))[3] = Integer.toString(150*(attacker.getStats()[0][0]/attacker.getStats()[0][1]));
            BattleMoves.damager(move, attacker, defender, animations);
        }
        else if (move.equals("Signal Beam")|| move.equals("Extrasensory")){
            BattleMoves.damager(move, attacker, defender, animations);
            if (Math.random()<=0.1){
                defender.setStatus(new Status.Confusion(), animations);
            }
        }
        else if (move.equals("Sand Tomb")){
            defender.setStatus(new Status.SandTomb(), animations);
        }
        else if (move.equals("Sheer Cold")){
            BattleMoves.damager(defender, defender.getStats()[0][0], animations);
        }
        
        else if (move.equals("Muddy Water")){
            BattleMoves.damager(move, attacker, defender, animations);
            if (Math.random()<0.30){
                defender.adjustStats(-1, 6,animations);
            }
        }
        else if (move.equals("Bullet Seed")||move.equals("Icicle Spear")|| move.equals ("Rock Blast")){
            int counter = p.randomint(2, 5);
            getEffect(move, attacker, defender, counter,animations);  
        }
        
        else if (move.equals("Iron Defense")){
            attacker.adjustStats(2, 2,animations);
        }
        else if (move.equals("Block")){
            defender.setStatus(new Status.Trapped(),animations);
        }
        else if (move.equals("Howl")){
            attacker.adjustStats(1, 1,animations);
        }
        
//        else if (move.equals("Frenzy Plant")||move.equals("Rock Wrecker")){
//            if (attacker.getMultiTurnCount()<1) {
//                attacker.setMultiTurnCount (2);
//            }
//            if (attacker.getMultiTurnCount()==2){
//                animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" used Frenzy Plant!")));
//                BattleMoves.damager(move, attacker, defender, animations);
//            }
//            else if (attacker.getMultiTurnCount()==1){
//                animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" had to recharge.")));
//                
//            }
//        }
        else if (move.equals("Bulk Up")){
            for (int x=1; x<3; x++){
                attacker.adjustStats(1, x,animations);
            }
        }
//        else if (move.equals("Bounce")){
//            if (attacker.getMultiTurnCount()<1){
//                attacker.setMultiTurnCount(2);
//            }
//            if (attacker.getMultiTurnCount()==2){
//                animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" sprang up!")));
//            }
//            else if (attacker.getMultiTurnCount()==1){
//                animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" used " + move + "!")));
//                BattleMoves.damager(move, attacker, defender, animations);
//            }
//        }
        else if (move.equals("Mud Shot")){
            BattleMoves.damager(move, attacker, defender, animations);
            defender.adjustStats(-1, 5,animations);
        }
        else if (move.equals("Poison Tail")){
            BattleMoves.damager(move, attacker, defender, animations);
            if (Math.random()<=0.1){
                defender.setStatus(new Status.Poison(defender), animations);
            }
        }
        else if (move.equals("Volt Tackle")){
            int damage = BattleMoves.calcDamage(move, attacker.getPokemon(), defender.getPokemon(), animations);
            BattleMoves.damager(defender, damage, animations);
            BattleMoves.damager(attacker, damage/3, animations);
            animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" was hurt by recoil!")));
            if (Math.random()<=0.10){
                defender.setStatus(new Status.Paralysis(defender), animations);
            }
        }
        
        else if (move.equals("Calm Mind")){
            for (int x=3; x<5; x++){
                attacker.adjustStats(1, x,animations);
            }
        }
        
        else if (move.equals("Dragon Dance")){
            attacker.adjustStats(1, 1,animations);
            attacker.adjustStats(1, 5,animations);
        }
        
        else if (move.equals("Water Pulse")){
            if (Math.random()<0.20){
                defender.setStatus(new Status.Confusion(), animations);
            }
        }
        else if (move.equals("Psycho Boost")){
            BattleMoves.damager(move, attacker, defender, animations);
            defender.adjustStats(-2, 4,animations);
            
        }
        else if (move.equals("Roost")){
            attacker.getStats()[0][0]+= attacker.getStats()[0][1]/2;
        }
        
        else if (move.equals("Wake-up Slap")){
            if (defender.getNonVolatileStatus()==(4)){
                defender.getStats()[0][0] -= 2*BattleMoves.calcDamage(move, attacker.getPokemon(), defender.getPokemon(), animations);
                defender.setNonVolatileStatus (-1);
            }
            else{
                BattleMoves.damager(move, attacker, defender, animations);
            }
        }
        else if (move.equals("Hammer Arm")){
            BattleMoves.damager(move, attacker, defender, animations);
            attacker.adjustStats(-1, 5,animations);
        }
        else if (move.equals("Gyro Ball")){
            BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(move))[3] = Integer.toString(25*(defender.getStats()[5][0]/defender.getStats()[5][0]));
            defender.getStats()[0][0]-= BattleMoves.calcDamage(move, attacker.getPokemon(), defender.getPokemon(), animations);
        }
        else if (move.equals("Brine")){
            if (defender.getStats()[0][1]/defender.getStats()[0][0]>=2)
                BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(move))[3]=Integer.toString(25*(defender.getStats()[5][0]/defender.getStats()[5][0]));
            defender.getStats()[0][0]-= BattleMoves.calcDamage(move, attacker.getPokemon(), defender.getPokemon(), animations);
        }
        else if (move.equals("Tailwind")){
            attacker.adjustStats(1,5,animations);
        }
        else if (move.equals("Acupressure")){
            int stat = p.randomint(1, 5);
            attacker.adjustStats(2, stat,animations);
        }
        else if (move.equals("Close Combat")){
            BattleMoves.damager(move, attacker, defender, animations);
            attacker.adjustStats(-1, 2,animations);
            attacker.adjustStats(-1, 4,animations);
        }
        else if (move.equals("Power Trick")){
            int holder = attacker.getStats()[1][0];
            attacker.getStats()[1][0] = attacker.getStats()[2][0];
            attacker.getStats()[2][0] = holder;
        }
        else if (move.equals("Power Swap")){
            int changer;
            for (int x=1; x<4;x+=2){
                changer=attacker.getStats()[x][2];
                attacker.getStats()[x][2] = defender.getStats()[x][2];
                defender.getStats()[x][2] = changer;
                attacker.adjustStats(0, x,animations);
                defender.adjustStats(0,x,animations);
            }
        }
        else if (move.equals("Guard Swap")){
            int changer;
            for (int x=2; x<5;x+=2){
                changer=attacker.getStats()[x][2];
                attacker.getStats()[x][2] = defender.getStats()[x][2];
                defender.getStats()[x][2] = changer;
                attacker.adjustStats(0, x,animations);
                defender.adjustStats(0,x,animations);
            }
        }
        else if (move.equals("Punishment")){
            int total=0;
            for (int x=1; x<6; x++){//all stats except HP, accuracy, evasion
                if (defender.getStats()[x][2]>0) {
                    total+= defender.getStats()[x][2];
                }
            }
            BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(move))[3]=Integer.toString(60 + 20*total);
            BattleMoves.damager(move, attacker, defender, animations);
        }
        
        else if (move.equals("Worry Seed")){
            defender.setAbility("Insomnia");
        }
        else if (move.equals("Heart Swap")){
            int holder = 0;
            for (int x=1; x<6; x++){
                holder = attacker.getStats()[x][2];
                attacker.getStats()[x][2] = defender.getStats()[x][2];
                defender.getStats()[x][2] = holder;
                attacker.adjustStats(0, x,animations);
                defender.adjustStats(0,x,animations);
            }
        }
        else if (move.equals("Aqua Ring")){
            attacker.setStatus(new Status.AquaRing(), animations);
        }
        else if (move.equals("Flare Blitz")){
            int damage = BattleMoves.calcDamage(move, attacker.getPokemon(), defender.getPokemon(), animations);
            BattleMoves.damager(defender, damage, animations);
            BattleMoves.damager(attacker, damage/3, animations);
            animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" was hurt by recoil!")));
            
            if (Math.random()<0.10){
                defender.setStatus(new Status.Burn(defender), animations);
            }
        }
        else if (move.equals("Force Palm")){
            BattleMoves.damager(move, attacker, defender, animations);
            if (Math.random()<0.30){
                defender.setStatus(new Status.Paralysis(defender), animations);
            }
        }
        else if (move.equals("Rock Polish")){
            attacker.adjustStats(2, 5,animations);
        }
        else if (move.equals("Poison Jab")){
            BattleMoves.damager(move, attacker, defender, animations);
            if (Math.random()<0.30){
                defender.setStatus(new Status.Poison(defender), animations);
            }
        }
        else if (move.equals("Dark Pulse")||move.equals("Air Slash")||move.equals("Dragon Rush")){
            BattleMoves.damager(move, attacker, defender, animations);
            if (Math.random()<0.20){
                defender.setStatus(new Status.Flinch(), animations);
            }
        }
        
        else if (move.equals("Drain Punch")){
            int damage = BattleMoves.calcDamage(move, attacker.getPokemon(), defender.getPokemon(), animations);
            BattleMoves.damager(defender, damage, animations);
            BattleMoves.healer(attacker, (int)0.5*damage, animations);
        }
        else if (move.equals("Focus Blast")||move.equals("Bug Buzz")||move.equals("Energy Ball")||move.equals("Earth Power")){
            BattleMoves.damager(move, attacker, defender, animations);
            if (Math.random()<0.10){
                defender.adjustStats(-1, 5,animations);
            }
        }
        else if (move.equals("Brave Bird")){
            int damage = BattleMoves.calcDamage(move, attacker.getPokemon(), defender.getPokemon(), animations);
            BattleMoves.damager(defender, damage, animations);
            BattleMoves.damager(attacker, damage/3, animations);
        }
        
//        else if (move.equals("Giga Impact")){
//            if (attacker.getMultiTurnCount()<1){
//                attacker.setMultiTurnCount(2);
//            }
//            if (attacker.getMultiTurnCount()==2){
//                animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" used Giga Impact!")));
//                BattleMoves.damager(move, attacker, defender, animations);
//            }
//            else if (attacker.getMultiTurnCount()==1){
//                animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" had to recharge.")));
//            }
//        }
        else if (move.equals("Nasty Plot")){
            attacker.adjustStats(2,3,animations);
        }
        else if (move.equals("Thunder Fang")){
            BattleMoves.damager(move, attacker, defender, animations);
            if (Math.random()<0.10){
                defender.setStatus(new Status.Paralysis(defender), animations);
            }
        }
        else if (move.equals("Ice Fang")){
            BattleMoves.damager(move, attacker, defender, animations);
            if (Math.random()<0.10){
                defender.setStatus(new Status.Frozen(defender), animations);
            }
        }
        else if (move.equals("Fire Fang")){
            BattleMoves.damager(move, attacker, defender, animations);
            if (Math.random()<0.10){
                defender.setStatus(new Status.Burn(defender), animations);
            }
        }
        else if (move.equals("Mud Bomb")||move.equals("Mirror Shot")){
            BattleMoves.damager(move, attacker, defender, animations);
            if (Math.random()<0.30){
                defender.adjustStats(-1, 6,animations);
            }
        }
        else if (move.equals("Zen Headbutt")){
            BattleMoves.damager(move, attacker, defender, animations);
            if (Math.random()<0.20){
                defender.setStatus(new Status.Flinch(), animations);
            }
        }
        else if (move.equals("Flash Cannon")){
            BattleMoves.damager(move, attacker, defender, animations);
            if (Math.random()<0.10){
                defender.adjustStats(-1,4,animations);
            }
        }
        else if (move.equals("Rock Climb")){
            BattleMoves.damager(move, attacker, defender, animations);
            if (Math.random()<0.20){
                defender.setStatus(new Status.Confusion(), animations);
            }
        }
        else if (move.equals("Discharge")){
            BattleMoves.damager(move, attacker, defender, animations);
            if (Math.random()<0.30){
                defender.setStatus(new Status.Paralysis(defender), animations);
            }
        }
        else if (move.equals("Lava Plume")){
            BattleMoves.damager(move, attacker, defender, animations);
            if (Math.random()<0.30){
                defender.setStatus(new Status.Burn(defender), animations);
            }
        }
        else if (move.equals("Cross Poison")){
            BattleMoves.damager(move, attacker, defender, animations);
            if (Math.random()<0.10){
                defender.setStatus(new Status.Poison(defender), animations);
            }
        }
        else if (move.equals("Gunk Shot")){
            BattleMoves.damager(move, attacker, defender, animations);
            if (Math.random()<0.30){
                defender.setStatus(new Status.Poison(defender), animations);
            }
        }
        else if (move.equals("Iron Head")){
            BattleMoves.damager(move, attacker, defender, animations);
            if (Math.random()<0.30){
                defender.setStatus(new Status.Flinch(), animations);
            }
        }

        else if (move.equals("Captivate")){
            defender.adjustStats(-2, 3,animations);
        }
        else if (move.equals("Charge Beam")){
            BattleMoves.damager(move, attacker, defender, animations);
            if (Math.random()<0.70){
                defender.adjustStats(1,3,animations);
            }
        }
        else if (move.equals("Wood Hammer")){
            int damage = BattleMoves.calcDamage(move, attacker.getPokemon(), defender.getPokemon(), animations);
            BattleMoves.damager(defender, damage, animations);
            BattleMoves.damager(attacker, damage/3, animations);
            animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" was hurt by recoil!")));
        }
        else if (move.equals("Defend Order")){
            attacker.adjustStats(1,2,animations);
            attacker.adjustStats(1,4,animations);
        }
        else if (move.equals("Heal Order")){
            BattleMoves.healer(attacker, attacker.getStats()[0][1]/2, animations);
        }
        else if (move.equals("Head Smash")){
            int damage = BattleMoves.calcDamage(move, attacker.getPokemon(), defender.getPokemon(), animations);
            BattleMoves.damager(defender, damage, animations);
            BattleMoves.damager(attacker, damage/3, animations);
            animations.add(new ArrayList<>(Arrays.asList("0", attacker.getName()+" was hurt by recoil!")));
        }
        else if (move.equals("Double Hit")){
            getEffect(move, attacker, defender, 2,animations);
        }
        
        /////////////////////////END OFF HERE
//        else if (move.equals("Roar Of Time")){
//            Effect2.RoarOfTime(move, attacker, defender);
//        }
//        else if (move.equals("Spacial Rend")){
//            Effect2.SpacialRend(move, attacker, defender);
//        }
//        else if (move.equals("Lunar Dance")){
//            Effect2.LunarDance(move, attacker, defender);
//        }
//        else if (move.equals("Crush Grip")){
//            Effect2.CrushGrip(move, attacker, defender);
//        }
//        else if (move.equals("Magma Storm")){
//            Effect2.MagmaStorm(move, attacker, defender);
//        }
//        else if (move.equals("Dark Void")){
//            Effect2.DarkVoid(move, attacker, defender);
//        }
//        else if (move.equals("Seed Flare")){
//            Effect2.SeedFlare(move, attacker, defender);
//        }
//        else if (move.equals("Ominous Wind")){
//            Effect2.OminousWind(move, attacker, defender);
//        }
//        else if (move.equals("Shadow Force")){
//            Effect2.ShadowForce(move, attacker, defender);
        
//        }
//        else if (move.equals("Hone Claws")){
//            Effect2.HoneClaws(move, attacker, defender);
//        }
//        else if (move.equals("Wide Guard")){
//            Effect2.WideGuard(move, attacker, defender);
//        }
//        else if (move.equals("Guard Split")){
//            Effect2.GuardSplit(move, attacker, defender);
//        }
//        else if (move.equals("Power Split")){
//            Effect2.PowerSplit(move, attacker, defender);
//        }
//        else if (move.equals("Wonder Room")){
//            Effect2.WonderRoom(move, attacker, defender);
//        }
//        else if (move.equals("Psyshock")){
//            Effect2.Psyshock(move, attacker, defender);
//        }
//        else if (move.equals("Venoshock")){
//            Effect2.Venoshock(move, attacker, defender);
//        }
//        else if (move.equals("Autotomize")){
//            Effect2.Autotomize(move, attacker, defender);
//        }
//        else if (move.equals("Rage Powder")){
//            Effect2.RagePowder(move, attacker, defender);
//        }
//        else if (move.equals("Telekinesis")){
//            Effect2.Telekinesis(move, attacker, defender);
//        }
//        else if (move.equals("Magic Room")){
//            Effect2.MagicRoom(move, attacker, defender);
//        }
        
        //Finish here?
//        else if (move.equals("Smack Down")){
//            Effect2.SmackDown(move, attacker, defender);
//        }
//        else if (move.equals("Storm Throw")){
//            Effect2.StormThrow(move, attacker, defender);
//        }
//        else if (move.equals("Flame Burst")){
//            Effect2.FlameBurst(move, attacker, defender);
//        }
//        else if (move.equals("Sludge Wave")){
//            Effect2.SludgeWave(move, attacker, defender);
//        }
//        else if (move.equals("Quiver Dance")){
//            for (int x=3; x<6; x++){
//                attacker.adjustStats(1, x,animations);
//            }
//        }
//        else if (move.equals("Heavy Slam")){
//            Effect2.HeavySlam(move, attacker, defender);
//        }
//        else if (move.equals("Synchronoise")){
//            Effect2.Synchronoise(move, attacker, defender);
//        }
//        else if (move.equals("Electro Ball")){
//            Effect2.ElectroBall(move, attacker, defender);
//        }
//        else if (move.equals("Soak")){
//            Effect2.Soak(move, attacker, defender);
//        }
//        else if (move.equals("Flame Charge")){
//            Effect2.FlameCharge(move, attacker, defender);
//        }
        else if (move.equals("Coil")){
            for (int x=1; x<3;x++){
                attacker.adjustStats(1, x,animations);
            }
            attacker.adjustStats(1, 6,animations);
        }
//        else if (move.equals("Low Sweep")){
//            Effect2.LowSweep(move, attacker, defender);
//        }
//        else if (move.equals("Acid Spray")){
//            Effect2.AcidSpray(move, attacker, defender);
//        }
//        else if (move.equals("Foul Play")){
//            Effect2.FoulPlay(move, attacker, defender);
//        }
//        else if (move.equals("Simple Beam")){
//            Effect2.SimpleBeam(move, attacker, defender);
//        }
//        else if (move.equals("Entrainment")){
//            Effect2.Entrainment(move, attacker, defender);
//        }
//        else if (move.equals("After You")){
//            Effect2.AfterYou(move, attacker, defender);
//        }
//        else if (move.equals("Round")){
//            Effect2.Round(move, attacker, defender);
//        }
//        else if (move.equals("Echoed Voice")){
//            Effect2.EchoedVoice(move, attacker, defender);
//        }
//        else if (move.equals("Chip Away")){
//            Effect2.ChipAway(move, attacker, defender);
//        }
//        else if (move.equals("Clear Smog")){
//            Effect2.ClearSmog(move, attacker, defender);
//        }
//        else if (move.equals("Stored Power")){
//            Effect2.StoredPower(move, attacker, defender);
//        }
//        else if (move.equals("Quick Guard")){
//            Effect2.QuickGuard(move, attacker, defender);
//        }
//        else if (move.equals("Ally Switch")){
//            Effect2.AllySwitch(move, attacker, defender);
//        }
//        else if (move.equals("Scald")){
//            Effect2.Scald(move, attacker, defender);
//        }
//        else if (move.equals("Shell Smash")){
//            Effect2.ShellSmash(move, attacker, defender);
//        }
//        else if (move.equals("Heal Pulse")){
//            Effect2.HealPulse(move, attacker, defender);
//        }
//        else if (move.equals("Hex")){
//            Effect2.Hex(move, attacker, defender);
//        }
//        else if (move.equals("Sky Drop")){
//            Effect2.SkyDrop(move, attacker, defender);
//        }
//        else if (move.equals("Shift Gear")){
//            Effect2.ShiftGear(move, attacker, defender);
//        }
//        else if (move.equals("Circle Throw")){
//            Effect2.CircleThrow(move, attacker, defender);
//        }
//        else if (move.equals("Incinerate")){
//            Effect2.Incinerate(move, attacker, defender);
//        }
//        else if (move.equals("Quash")){
//            Effect2.Quash(move, attacker, defender);
//        }
//        else if (move.equals("Acrobatics")){
//            Effect2.Acrobatics(move, attacker, defender);
//        }
//        else if (move.equals("Reflect Type")){
//            Effect2.ReflectType(move, attacker, defender);
//        }
//        else if (move.equals("Retaliate")){
//            Effect2.Retaliate(move, attacker, defender);
//        }
//        else if (move.equals("Final Gambit")){
//            Effect2.FinalGambit(move, attacker, defender);
//        }
//        else if (move.equals("Bestow")){
//            Effect2.Bestow(move, attacker, defender);
//        }
//        else if (move.equals("Inferno")){
//            Effect2.Inferno(move, attacker, defender);
//        }
//        else if (move.equals("Water Pledge")){
//            Effect2.WaterPledge(move, attacker, defender);
//        }
//        else if (move.equals("Fire Pledge")){
//            Effect2.FirePledge(move, attacker, defender);
//        }
//        else if (move.equals("Grass Pledge")){
//            Effect2.GrassPledge(move, attacker, defender);
//        }
//        else if (move.equals("Volt Switch")){
//            Effect2.VoltSwitch(move, attacker, defender);
//        }
//        else if (move.equals("Struggle Bug")){
//            Effect2.StruggleBug(move, attacker, defender);
//        }
//        else if (move.equals("Bulldoze")){
//            Effect2.Bulldoze(move, attacker, defender);
//        }
//        else if (move.equals("Frost Breath")){
//            Effect2.FrostBreath(move, attacker, defender);
//        }
//        else if (move.equals("Dragon Tail")){
//            Effect2.DragonTail(move, attacker, defender);
//        }
//        else if (move.equals("Work Up")){
//            Effect2.WorkUp(move, attacker, defender);
//        }
//        else if (move.equals("Electroweb")){
//            Effect2.Electroweb(move, attacker, defender);
//        }
//        else if (move.equals("Wild Charge")){
//            Effect2.WildCharge(move, attacker, defender);
//        }
//        else if (move.equals("Drill Run")){
//            Effect2.DrillRun(move, attacker, defender);
//        }
//        else if (move.equals("Dual Chop")){
//            Effect2.DualChop(move, attacker, defender);
//        }
//        else if (move.equals("Heart Stamp")){
//            Effect2.HeartStamp(move, attacker, defender);
//        }
//        else if (move.equals("Horn Leech")){
//            Effect2.HornLeech(move, attacker, defender);
//        }
//        else if (move.equals("Sacred Sword")){
//            Effect2.SacredSword(move, attacker, defender);
//        }
//        else if (move.equals("Razor Shell")){
//            Effect2.RazorShell(move, attacker, defender);
//        }
//        else if (move.equals("Heat Crash")){
//            Effect2.HeatCrash(move, attacker, defender);
//        }
//        else if (move.equals("Leaf Tornado")){
//            Effect2.LeafTornado(move, attacker, defender);
//        }
//        else if (move.equals("Steamroller")){
//            Effect2.Steamroller(move, attacker, defender);
//        }
//        else if (move.equals("Cotton Guard")){
//            Effect2.CottonGuard(move, attacker, defender);
//        }
//        else if (move.equals("Night Daze")){
//            Effect2.NightDaze(move, attacker, defender);
//        }
//        else if (move.equals("Psystrike")){
//            Effect2.Psystrike(move, attacker, defender);
//        }
//        else if (move.equals("Tail Slap")){
//            Effect2.TailSlap(move, attacker, defender);
//        }
//        else if (move.equals("Hurricane")){
//            Effect2.Hurricane(move, attacker, defender);
//        }
//        else if (move.equals("Head Charge")){
//            Effect2.HeadCharge(move, attacker, defender);
//        }
//        else if (move.equals("Gear Grind")){
//            Effect2.GearGrind(move, attacker, defender);
//        }
//        else if (move.equals("Searing Shot")){
//            Effect2.SearingShot(move, attacker, defender);
//        }
//        else if (move.equals("Techno Blast")){
//            Effect2.TechnoBlast(move, attacker, defender);
//        }
//        else if (move.equals("Relic Song")){
//            Effect2.RelicSong(move, attacker, defender);
//        }
//        else if (move.equals("Secret Sword")){
//            Effect2.SecretSword(move, attacker, defender);
//        }
//        else if (move.equals("Glaciate")){
//            Effect2.Glaciate(move, attacker, defender);
//        }
//        else if (move.equals("Bolt Strike")){
//            Effect2.BoltStrike(move, attacker, defender);
//        }
//        else if (move.equals("Blue Flare")){
//            Effect2.BlueFlare(move, attacker, defender);
//        }
//        else if (move.equals("Fiery Dance")){
//            Effect2.FieryDance(move, attacker, defender);
//        }
//        else if (move.equals("Freeze Shock")){
//            Effect2.FreezeShock(move, attacker, defender);
//        }
//        else if (move.equals("Ice Burn")){
//            Effect2.IceBurn(move, attacker, defender);
//        }
//        else if (move.equals("Snarl")){
//            Effect2.Snarl(move, attacker, defender);
//        }
//        else if (move.equals("Icicle Crash")){
//            Effect2.IcicleCrash(move, attacker, defender);
//        }
//        else if (move.equals("V-create")){
//            Effect2.Vcreate(move, attacker, defender);
//        }
//        else if (move.equals("Fusion Flare")){
//            Effect2.FusionFlare(move, attacker, defender);
//        }
//        else if (move.equals("Fusion Bolt")){
//            Effect2.FusionBolt(move, attacker, defender);
//        }
//        else if (move.equals("Flying Press")){
//            Effect2.FlyingPress(move, attacker, defender);
//        }
//        else if (move.equals("Mat Block")){
//            Effect2.MatBlock(move, attacker, defender);
//        }
//        else if (move.equals("Belch")){
//            Effect2.Belch(move, attacker, defender);
//        }
//        else if (move.equals("Rototiller")){
//            Effect2.Rototiller(move, attacker, defender);
//        }
//        else if (move.equals("Sticky Web")){
//            Effect2.StickyWeb(move, attacker, defender);
//        }
//        else if (move.equals("Fell Stinger")){
//            Effect2.FellStinger(move, attacker, defender);
//        }
//        else if (move.equals("Phantom Force")){
//            Effect2.PhantomForce(move, attacker, defender);
//        }
//        else if (move.equals("Trick-or-treat")){
//            Effect2.Trickortreat(move, attacker, defender);
//        }
//        else if (move.equals("Noble Roar")){
//            Effect2.NobleRoar(move, attacker, defender);
//        }
//        else if (move.equals("Ion Deluge")){
//            Effect2.IonDeluge(move, attacker, defender);
//        }
//        else if (move.equals("Parabolic Charge")){
//            Effect2.ParabolicCharge(move, attacker, defender);
//        }
//        else if (move.equals("Forest's Curse")){
//            Effect2.ForestsCurse(move, attacker, defender);
//        }
//        else if (move.equals("Petal Blizzard")){
//            Effect2.PetalBlizzard(move, attacker, defender);
//        }
//        else if (move.equals("Freeze-dry")){
//            Effect2.Freezedry(move, attacker, defender);
//        }
//        else if (move.equals("Disarming Voice")){
//            Effect2.DisarmingVoice(move, attacker, defender);
//        }
//        else if (move.equals("Parting Shot")){
//            Effect2.PartingShot(move, attacker, defender);
//        }
//        else if (move.equals("Topsy-turvy")){
//            Effect2.Topsyturvy(move, attacker, defender);
//        }
//        else if (move.equals("Draining Kiss")){
//            Effect2.DrainingKiss(move, attacker, defender);
//        }
//        else if (move.equals("Crafty Shield")){
//            Effect2.CraftyShield(move, attacker, defender);
//        }
//        else if (move.equals("Flower Shield")){
//            Effect2.FlowerShield(move, attacker, defender);
//        }
//        else if (move.equals("Grassy Terrain")){
//            Effect2.GrassyTerrain(move, attacker, defender);
//        }
//        else if (move.equals("Misty Terrain")){
//            Effect2.MistyTerrain(move, attacker, defender);
//        }
//        else if (move.equals("Electrify")){
//            Effect2.Electrify(move, attacker, defender);
//        }
//        else if (move.equals("Play Rough")){
//            Effect2.PlayRough(move, attacker, defender);
//        }
//        else if (move.equals("Fairy Wind")){
//            Effect2.FairyWind(move, attacker, defender);
//        }
//        else if (move.equals("Moonblast")){
//            Effect2.Moonblast(move, attacker, defender);
//        }
//        else if (move.equals("Boomburst")){
//            Effect2.Boomburst(move, attacker, defender);
//        }
//        else if (move.equals("Fairy Lock")){
//            Effect2.FairyLock(move, attacker, defender);
//        }
//        else if (move.equals("King's Shield")){
//            Effect2.KingsShield(move, attacker, defender);
//        }
//        else if (move.equals("Play Nice")){
//            Effect2.PlayNice(move, attacker, defender);
        //}
        else if (move.equals("Confide")){
            attacker.adjustStats(-1, 3,animations);
        }
//        else if (move.equals("Diamond Storm")){
//            Effect2.DiamondStorm(move, attacker, defender);
//        }
//        else if (move.equals("Steam Eruption")){
//            Effect2.SteamEruption(move, attacker, defender);
//        }
//        else if (move.equals("Hyperspace Hole")){
//            Effect2.HyperspaceHole(move, attacker, defender);
//        }
//        else if (move.equals("Water Shuriken")){
//            Effect2.WaterShuriken(move, attacker, defender);
//        }
//        else if (move.equals("Mystical Fire")){
//            Effect2.MysticalFire(move, attacker, defender);
//        }
//        else if (move.equals("Spiky Shield")){
//            Effect2.SpikyShield(move, attacker, defender);
//        }
//        else if (move.equals("Aromatic Mist")){
//            Effect2.AromaticMist(move, attacker, defender);
//        }
//        else if (move.equals("Eerie Impulse")){
//            Effect2.EerieImpulse(move, attacker, defender);
//        }
//        else if (move.equals("Venom Drench")){
//            Effect2.VenomDrench(move, attacker, defender);
//        }
//        else if (move.equals("Powder")){
//            Effect2.Powder(move, attacker, defender);
//        }
//        else if (move.equals("Geomancy")){
//            Effect2.Geomancy(move, attacker, defender);
//        }
//        else if (move.equals("Magnetic Flux")){
//            Effect2.MagneticFlux(move, attacker, defender);
//        }
//        else if (move.equals("Happy Hour")){
//            Effect2.HappyHour(move, attacker, defender);
//        }
//        else if (move.equals("Electric Terrain")){
//            Effect2.ElectricTerrain(move, attacker, defender);
//        }
//        else if (move.equals("Dazzling Gleam")){
//            Effect2.DazzlingGleam(move, attacker, defender);
//        }
//        else if (move.equals("Celebrate")){
//            Effect2.Celebrate(move, attacker, defender);
//        }
//        else if (move.equals("Hold Hands")){
//            Effect2.HoldHands(move, attacker, defender);
//        }
//        else if (move.equals("Baby-doll Eyes")){
//            Effect2.BabydollEyes(move, attacker, defender);
//        }
//        else if (move.equals("Nuzzle")){
//            Effect2.Nuzzle(move, attacker, defender);
//        }
//        else if (move.equals("Hold Back")){
//            Effect2.HoldBack(move, attacker, defender);
//        }
//        else if (move.equals("Infestation")){
//            Effect2.Infestation(move, attacker, defender);
//        }
//        else if (move.equals("Power-up Punch")){
//            Effect2.PowerupPunch(move, attacker, defender);
//        }
//        else if (move.equals("Oblivion Wing")){
//            Effect2.OblivionWing(move, attacker, defender);
//        }
//        else if (move.equals("Thousand Arrows")){
//            Effect2.ThousandArrows(move, attacker, defender);
//        }
//        else if (move.equals("Thousand Waves")){
//            Effect2.ThousandWaves(move, attacker, defender);
//        }
//        else if (move.equals("Land's Wrath")){
//            Effect2.LandsWrath(move, attacker, defender);
//        }
//        else if (move.equals("Light Of Ruin")){
//            Effect2.LightOfRuin(move, attacker, defender);
//        }
//        else if (move.equals("Origin Pulse")){
//            Effect2.OriginPulse(move, attacker, defender);
//        }
//        else if (move.equals("Precipice Blades")){
//            Effect2.PrecipiceBlades(move, attacker, defender);
//        }
//        else if (move.equals("Dragon Ascent")){
//            Effect2.DragonAscent(move, attacker, defender);
//        }
//        else if (move.equals("Hyperspace Fury")){
//            Effect2.HyperspaceFury(move, attacker, defender);
//        }
        else{
            if (!BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(move))[3].equals("?")){
                BattleMoves.damager(move, attacker, defender, animations);
            }
        }
    }
    //for all multi-hit moves
    public static void getEffect(String move, BattlePokemon attacker, BattlePokemon defender, int counter, List<List<String>> animations){
        if (counter>0){
            BattleMoves.damager(move, attacker, defender, animations);
            getEffect(move, attacker, defender, counter-1, animations);
        }
        
    }
    
    
}
