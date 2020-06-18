/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] strings = new GuitarString[37];
        for(int i = 0; i < 37; i += 1){
            double freq = 440 * Math.pow(2, (i - 24.0)/ 12.0);
            //System.out.println(44100/freq);
            strings[i] = new GuitarString(freq);
        }
        while (true) {

            // check if the user has typed a key; if so, process it
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index != -1) {
                    strings[index].pluck();
                }
                  /*if      (key == 'a') { stringA.pluck(); }
                  else if (key == 'c') { stringC.pluck(); }*/
            }

            // compute the superposition of samples
            double sample = 0;
            for (GuitarString string : strings) {
                sample += string.sample();
            }

            // play the sample on standard audio
            // note: this is just playing the double value YOUR GuitarString
            //       class is generating.
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
/*              stringA.tic();
              stringC.tic();*/
            for (GuitarString string : strings) {
                string.tic();
            }
        }



    }
}
