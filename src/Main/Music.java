/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Main;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Antonio's Laptop
 */
public class Music {
    
    Clip clip;
    File file = new File("./Audio/Lavender Town.mp3");
    
    public Music() {
        try{
            this.clip = AudioSystem.getClip();
        }catch(LineUnavailableException e){
            System.out.println("Could not play music");
        }
    }
    public Music(String path) {
        try{
            this.clip = AudioSystem.getClip();
            change(path);
        }catch(LineUnavailableException e){
            System.out.println("Could not play music");
        }
    }
    
    public void play(){
        clip.start();
        System.out.println("playing");
    }
    
    public void loop(int numtimes){
        clip.loop(numtimes);
//        stop();
        play();
    }
    
    public void stop(){
        clip.stop();
    }
    
    public void change(String path){
        if (path.endsWith(".wav")){
            try{
                file = new File(path);
                clip.open(AudioSystem.getAudioInputStream(file));
                play();
            }catch(LineUnavailableException | UnsupportedAudioFileException | IOException e){
                System.out.println("Could not play music");
            }
        }
        else{
            System.out.println("File not Supported");
        }
    }
    
}
