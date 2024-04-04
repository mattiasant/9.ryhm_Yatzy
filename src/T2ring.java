/**
 * @class T2ring    jäljendab tavalist täringut, ning hoiab endas ka indeksit
 * @method veereta  veeretab täringut
 */

import java.util.Random;

public class T2ring {
    private final String koht;
    private int silmad;

    public T2ring(String koht) {
        this.koht = koht;
    }

    public int getSilmad() {
        return silmad;
    }

    public void veereta() {
        Random random = new Random();
        this.silmad = random.nextInt(6) + 1;
    }

    public String getKoht() {
        return koht;
    }
}
