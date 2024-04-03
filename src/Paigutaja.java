import java.util.ArrayList;

public class Paigutaja {

    public Paigutaja() {
    }

    public void paiguta(M2ngija m2ngija, String kuhu, ArrayList<T2ring> t2ringud) {
        int summa = 0;
        if (kuhu.equals("ühed")){
            for (T2ring t2ring : t2ringud) {
                if (t2ring.getSilmad()==1) {
                    summa+=1;
                }
            }
            m2ngija.setYhed(summa);
        }
        else if (kuhu.equals("kahed")){
            for (T2ring t2ring : t2ringud) {
                if (t2ring.getSilmad()==2) {
                    summa+=2;
                }
            }
            m2ngija.setYhed(summa);
        }
        else if (kuhu.equals("kolmed")){
            for (T2ring t2ring : t2ringud) {
                if (t2ring.getSilmad()==3) {
                    summa+=3;
                }
            }
            m2ngija.setYhed(summa);
        }
        else if (kuhu.equals("neljad")){
            for (T2ring t2ring : t2ringud) {
                if (t2ring.getSilmad()==4) {
                    summa+=4;
                }
            }
            m2ngija.setYhed(summa);
        }
        else if (kuhu.equals("viied")){
            for (T2ring t2ring : t2ringud) {
                if (t2ring.getSilmad()==5) {
                    summa+=5;
                }
            }
            m2ngija.setYhed(summa);
        }
        else if (kuhu.equals("kuued")){
            for (T2ring t2ring : t2ringud) {
                if (t2ring.getSilmad()==6) {
                    summa+=6;
                }
            }
            m2ngija.setYhed(summa);
        }
        else if (kuhu.equals("paar")) {
            m2ngija.setPaar(paarSumma(t2ringud));
        }
        else if (kuhu.equals("2paar")) {
            m2ngija.setKakspaar(kaksPaar(t2ringud));
        }
    }

    public int paarSumma(ArrayList<T2ring> t2ringud) {
        int[] lugeja = new int[6];
        int suurimSumma = 0;

        for (T2ring t2ring:t2ringud) {
            lugeja[t2ring.getSilmad()-1]++;
        }
        for (int i =0;i<lugeja.length;i++) {
            if (lugeja[i] >= 2) {
                int paarisumma = (i+1)*2;
                suurimSumma = Math.max(suurimSumma,paarisumma);
            }
        }
        return suurimSumma;
    }

    public int kaksPaar(ArrayList<T2ring> t2ringud) {
        int[] lugeja = new int[6];
        int paarideSumma = 0;
        int paarid = 0;

        // Count occurrences of each value
        for (T2ring t2ring : t2ringud) {
            lugeja[t2ring.getSilmad() - 1]++;
        }

        // Check if any value occurs exactly twice
        for (int i = 0; i < lugeja.length; i++) {
            if (lugeja[i] >= 2) {
                // Calculate the sum of the pair
                paarideSumma += (i + 1) * 2;
                paarid++;
            }
        }

        // If two pairs are found, return the sum; otherwise, return 0
        return paarid == 2 ? paarideSumma : 0;
    }
}

