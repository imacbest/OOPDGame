package nl.han.oopd.toadparcour;

import android.gameengine.icadroids.sound.MusicPlayer;
import android.util.Log;

/**
 * @author Thomas
 */
public class SoundController {
    // ToDo: fix music class
    public SoundController(){
        playMusic();
    }

    public void playMusic() {
        MusicPlayer.play("lucas", true);
        Log.d("Music", "Play");
    }

    public void pauseMusic(){
        MusicPlayer.pauseAll();
        Log.d("Music", "Pause");

    }

    public void stopMusic(){
        MusicPlayer.stop();
        Log.d("Music", "Stop");
    }

    public void resumeMusic(){
        MusicPlayer.resumeAll();
        Log.d("Music", "Resume");
    }

}
