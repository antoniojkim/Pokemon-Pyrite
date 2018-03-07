/*
* To change this.pokemon license header, choose License Headers in Project Properties.
* To change this.pokemon template file, choose Tools | Templates
* and open the template in the editor.
*/
package Pokemon;
import Battle.BattlePanel;
import Main.Main;
import java.util.*;
import Status.Status;
import java.awt.image.BufferedImage;
/**
 *
 * @author Katrina
 */
public class BattlePokemon{
    
    
    private int multiTurnCount;
    private boolean trapped, statFix, metronome;
    private boolean ableToMove = true,  multihitText=false, statusIcon = false, drawable = true;
    private String move, lastMove;
    
    private ArrayList <Status> statuses = new ArrayList<>();
    
    private Pokemon pokemon;
    
    
    public Pokemon getPokemon(){
        return pokemon;
    }
    
    
    public BattlePokemon (String name, int level) {
        pokemon = new Pokemon(name, level);
        for (int x=1; x<8; x++){
            pokemon.getStats()[x][0] = pokemon.getStats()[x][1];
            pokemon.getStats()[x][2] = 0;
        }
        pokemon.setEndHealth(pokemon.getStats()[0][0]);
        pokemon.endExp = pokemon.getExp();
    }
    
    public BattlePokemon (Pokemon pokemon){
        this.pokemon = pokemon;
        for (int x=1; x<8; x++){
            pokemon.getStats()[x][0] = pokemon.getStats()[x][1];
            pokemon.getStats()[x][2] = 0;
        }
        pokemon.setEndHealth(pokemon.getStats()[0][0]);
        pokemon.endExp = pokemon.getExp();
    }
    
    public boolean equals(Pokemon pokemon){
        if (this.pokemon.name.equals(pokemon.getName()) && this.pokemon.displayname.equals(pokemon.getDisplayname()) && this.pokemon.level == pokemon.getLevel() && this.pokemon.gender == pokemon.getGender() && this.pokemon.ability.equals(pokemon.getAbility())
                && this.pokemon.iv[0] == pokemon.getIv()[0] && this.pokemon.iv[1] == pokemon.getIv()[1] && this.pokemon.iv[2] == pokemon.getIv()[2] && this.pokemon.iv[3] == pokemon.getIv()[3] && this.pokemon.iv[4] == pokemon.getIv()[4] && this.pokemon.iv[5] == pokemon.getIv()[5]){
            return true;
        }
        return false;
    }
    
    public void setStatus(Status status, List<List<String>> animations){
        boolean change = true;
        for (Status s: statuses){
            if (s.getClass().equals(status.getClass())){
                change=false;
                break;
            }
        }
        
        //only one of the following statuses at once: poison, toxic, burn, frozen, sleep, paralysis
        if (status.nonVolatile){
            for (int i=0; i<statuses.size(); i++){
                if (statuses.get(i).nonVolatile){
                    animations.add(new ArrayList<>(Arrays.asList("0", "A new status condition could not be added.")));
                    change= false;
                    break;
                }
            }
        }
        if (change){
            //animate status
            if (!status.getSetText().equals("")){
                String display = pokemon.name + status.getSetText();
                animations.add(new ArrayList<>(Arrays.asList("0", display)));
                if (this.equals(BattlePanel.getOpponent())){
                    animations.add(new ArrayList<>(Arrays.asList("4","opponent" )));
                }
                else if (this.equals(BattlePanel.getInbattle())){
                    animations.add(new ArrayList<>(Arrays.asList("4","inbattle" )));
                }
                
            }
            statuses.add(status);
            
        }
//
    }
    
    public void resetStatus(Status status){
        if (status.getClass().getName().equals("Status.Paralysis")){
            pokemon.stats[5][0]*=4;
            pokemon.nonVolatileStatus=-1;
        }
        else if (status.getClass().getName().equals("Status.Burn")||status.getClass().getName().equals("Status.Poison")||status.getClass().getName().equals("Status.Frozen")||status.getClass().getName().equals("Status.Sleep")){
            pokemon.nonVolatileStatus=-1;
        }
        statusIcon=false;
    }
    
    public boolean checkStatus (String status){
        for (int x=0; x<statuses.size(); x++){
            if (statuses.get(x).getClass().getName().equals("Status." + status)){
                return true;
            }
        }
        return false;
    }
    
    //stats is the array of stats, stage is the amount of change, whichStat is altered
    //each status move needs the stat which it affects and how many stages it lowers/raises it;
    public void adjustStats(int stage, int whichStat, List<List<String>> animations) {
        
        
        double multiplier = 1.0;
        if (pokemon.stats[whichStat][2] + stage > 6) {
            pokemon.stats[whichStat][2] = 6;
        }
        else if (pokemon.stats[whichStat][2] + stage < -6) {
            pokemon.stats[whichStat][2] = -6;
        }
        else {
            pokemon.stats[whichStat][2] += stage;
        }
        
        //most stats: 2/8  2/7  2/6  2/5  2/4  2/3  2/2  3/2  4/2  5/2  6/2  7/2  8/2
        if (whichStat < 6) {
            multiplier = 2.0 / (Math.abs(pokemon.stats[whichStat][2]) + 2);
        }
        
        else {//accuracy and evasion: 3/8 3/7 3/6 3/5 3/4 3/3 4/3 5/3 6/3 7/3 8/3
            multiplier = 3.0 / (Math.abs(pokemon.stats[whichStat][2]) + 3);
        }
        if (pokemon.stats[whichStat][2] > 0) {
            multiplier = 1 / multiplier;
        }
        pokemon.stats[whichStat][0] = (int) Math.round(pokemon.stats[whichStat][1] * multiplier);
        
        //animate
        
        String stat = "";
        switch (whichStat) {
            case 1: stat = "attack";
            break;
            case 2: stat = "defense";
            break;
            case 3:stat = "special attack";
            break;
            case 4:stat = "special defense";
            break;
            case 5:stat = "speed";
            break;
            case 6: stat = "accuracy";
            break;
            case 7: stat = "evasion";
            break;
            default:
                break;
        }
        String change = "";
        if (stage==1){
            change = " rose";
        }
        else if (stage==-1){
            change = " fell";
        }
        else if (stage<-1){
            change = " sharply fell";
        }
        else if (stage>1){
            change = " sharply rose";
        }
        if (stage!=0){
            String display = pokemon.name + "'s " + stat + change + "!";
            animations.add(new ArrayList<>(Arrays.asList("0", display)));
        }
        
    }
    
    
    public void faint (List<List<String>> animations){
        if (pokemon.endHealth==0) {
            pokemon.fainted=true;
            ableToMove = false;
            animations.add(new ArrayList<>(Arrays.asList("0", pokemon.name+" fainted!")));
                        
            if (this.equals(Main.map.getBP().getOpponent())){
                Main.map.getBP().adjustHealth();
                Main.map.getBP().distribute(Main.data.EVYield[this.pokemon.getIndex()]);
                animations.add(new ArrayList<>(Arrays.asList("2", "opponent"))); //fainting animation
                if (Main.map.getBP().getInbattle().getLevel()<100){
                    animations.add(new ArrayList<>(Arrays.asList("0", Main.map.getBP().getInbattle().getName() + " gained experience!")));
                  animations.add(new ArrayList<>(Arrays.asList("5")));  //exp increase
                }
                Main.map.getBP().getOpponentParty().remove(this); //gets rid of opponent pokemon
                
                if (Main.map.getBP().getOpponentParty().isEmpty()){
                    if (Main.map.getBP().isTrainerBattle()){
                        animations.add(new ArrayList<>(Arrays.asList("0", Main.it.getName() + " has won the battle!")));
                        animations.add(new ArrayList<>(Arrays.asList("0", "Earned " + Main.map.getBP().getPay() +" poké!")));
                    }    
                }
                animations.add(new ArrayList<>(Arrays.asList("6", "opponent"))); //checks for win
                
            }
            else if (this.equals(Main.map.getBP().getInbattle())){
                Main.map.getBP().getPlayerParty().remove(this); //gets rid of pokemon from list of those able to battle
                animations.add(new ArrayList<>(Arrays.asList("2", "inbattle")));
                
                //when you lose the game
                if (Main.map.getBP().getPlayerParty().isEmpty()){
                    if (Main.map.getBP().isTrainerBattle()){
                        animations.add(new ArrayList<>(Arrays.asList("0", Main.it.getName() + " has no pokémon left!")));
                        int payout = Main.it.getMoney()/4;
                        if (Main.map.getBP().isTrainerBattle()){
                            animations.add(new ArrayList<>(Arrays.asList("0", Main.it.getName() + " paid " + payout + " poké to the winner.")));
                        }
                        else{
                            animations.add(new ArrayList<>(Arrays.asList("0", Main.it.getName() + " dropped " + payout + " poké in the chaos!")));
                        }
                        Main.it.adjustMoney(-payout);
                        animations.add(new ArrayList<>(Arrays.asList("0", "...")));
                        animations.add(new ArrayList<>(Arrays.asList("0", Main.it.getName() + " blacked out!")));
                    }   
                }
                animations.add(new ArrayList<>(Arrays.asList("6", "inbattle"))); //checks for win
                
            }
            
        }
        
    }
    public int getMultiTurnCount() {
        return multiTurnCount;
    }
    
    
    public boolean isTrapped() {
        return trapped;
    }
    
    public boolean isStatFix() {
        return statFix;
    }
    
    public boolean isFainted() {
        return pokemon.isFainted();
    }
    
    public boolean isAbleToMove() {
        return ableToMove;
    }
    
    public String getMove() {
        return move;
    }
    
    public String getLastMove() {
        return lastMove;
    }
    
    public int getLevel(){
        return pokemon.getLevel();
    }
    
    public List<int[]> getPp(){
        return pokemon.getPp();
    }
    
    public List<String> getMoveset(){
        return pokemon.getMoveset();
    }
    
    public BufferedImage getBackimage(){
        return pokemon.getBackimage();
    }
    
    public void setBackimage(BufferedImage image){
        pokemon.setBackimage(image);
    }
    
    public BufferedImage getImage(){
        return pokemon.getImage();
    }
    
    public void setImage(BufferedImage image){
        pokemon.setImage(image);
    }
    
    public String getName(){
        return pokemon.getName();
    }
    
    public String getDisplayname(){
        return pokemon.getDisplayname();
    }
    
    public int[][] getStats(){
        return pokemon.getStats();
    }
    
    public void adjustHealth (int adjust){
        pokemon.adjustHealth(adjust);
    }
    
    public int getEndHealth(){
        return pokemon.endHealth;
    }
    public void adjustEndHealth(int num){
        pokemon.adjustEndHealth(num);
    }
    public void setEndHealth(int num){
        pokemon.setEndHealth(num);
    }
    
    public int getGender(){
        return pokemon.getGender();
    }
    
    public int getNonVolatileStatus(){
        return pokemon.getNonVolatileStatus();
    }
    public void setNonVolatileStatus(int num){
        pokemon.setNonVolatileStatus(num);
    }
    
    public ArrayList<Status> getStatuses() {
        return statuses;
    }
    
    public void setMultiTurnCount(int multiTurnCount) {
        this.multiTurnCount = multiTurnCount;
    }
    public void lowerMultiTurnCount(){
        multiTurnCount--;
    }
    
    public void setAbility (String ability){
        pokemon.setAbility(ability);
    }
    
    public void setTrapped(boolean trapped) {
        this.trapped = trapped;
    }
    
    public void setStatFix(boolean statFix) {
        this.statFix = statFix;
    }
    
    public void setAbleToMove(boolean ableToMove) {
        this.ableToMove = ableToMove;
    }
    
    public void setMove(String move) {
        this.move = move;
    }
    
    public void setLastMove(String lastMove) {
        this.lastMove = lastMove;
    }
    
    public void setStatuses(ArrayList<Status> statuses) {
        this.statuses = statuses;
    }
    
    public void setFainted(boolean fainted) {
        pokemon.setFainted(fainted);
    }
    
    public boolean isMultihitText() {
        return multihitText;
    }
    
    public void setMultihitText(boolean multihitText) {
    }
    
    public boolean isStatusIcon() {
        return statusIcon;
    }
    
    public void setStatusIcon(boolean statusIcon) {
        this.statusIcon = statusIcon;
    }

    public boolean isMetronome() {
        return metronome;
    }

    public void setMetronome(boolean metronome) {
        this.metronome = metronome;
    }

    public int getEndExp() {
        return pokemon.endExp;
    }

    public void setEndExp(int endExp) {
        pokemon.endExp = endExp;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
    
    public int getExp(){
        return pokemon.getExp();
    }
    public int getExpToNext(){
        return pokemon.getExpToNext();
     }
    
    public void adjustExp(int adjust){
        pokemon.incExp(adjust);
    }
    
    public void adjustEndExp (int adjust){
        pokemon.adjustEndExp (adjust);
        
    }
    
    public int getExpToLevel(){
        return pokemon.expToLevel;
    }
    
    public void adjustExpToLevel(){
        pokemon.adjustExpToLevel();
    }
    
    public void levelUp(){
        pokemon.levelUp();
    }
    public String getCry(){
        return pokemon.getCry();
    }

    public boolean isDrawable() {
        return drawable;
    }

    public void setDrawable(boolean drawable) {
        this.drawable = drawable;
    }
    
    
}
