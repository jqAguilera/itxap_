
/**
 * Se tiene el siguiente programa cliente escrito en JAVA:
 */
public class DemoPlayer extends AudioPlayer{ //SOLUCION

    private static AudioPlayer player;

    public static void main(String[] args) {
        configurar(); // define el tipo de audio a utilizar
        playSong(); // reproduce el audio
    }

    static void configurar() {
        // si primer argumento del programa es ogg,
        // entonces preparar reproducción de audio de ese tipo
        if (args[0] == "ogg") {
            /* Es formato .ogg */
            player = new OGGAudio(); //SOLUCION

        } else { // sino, preparar reproducción de audio de tipo mp3 por defecto.
        /* Es formato .mp3 */
            player = new MP3Audio(); //SOLUCION

        }
 }
 static void playSong() {
        player.Play(); // invoca método Play() del reproductor de audios
    }
}

/**
 * considerar la siguiente interface para definir el tipo audio
 */
public interface IAudio {
    // ... other props ...

    int duration;
    int bitrate;

    Byte[] getAudioStream();
    // ... other methods ...
}



* además dos clases para tipos distintos de audio,
* una clase implementa el audio para tipos MP3 y la otra para tipos OGG.
*/
public class MP3Audio implements IAudio {

    public int duration;
    public int bitrate;

    public Byte[] getAudioStream() {
        // ... mp3 logic ...
    }
}

public class OGGAudio implements IAudio {

    public int duration;
    public int bitrate;

    Byte[] getAudioStream() {
        // ... ogg logic ...
    }
}

/**
 * por último, considerar la siguiente clase abstracta que define al reproductor
 * de audios genérico
 */
public abstract class AudioPlayer {
    // para el objetivo del ejercicio solo nos centraremos en que tenemos
    // un método Play(), y un método abstracto createAudioObj()

    public void Play() {
        // business logic...
        IAudio audio = createAudioObj(); // función que crea el objeto de audio
        PlayStream(audio.getAudioStream()); // obtiene stream de audio y reproduce
        // business logic...
    }

    public abstract IAudio createAudioObj();
}
