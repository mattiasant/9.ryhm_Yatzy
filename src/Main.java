/***********************************
 * Objektorienteeritud programmeerimine. LTAT.03.003
 * 2023/2024 kevadsemester
 *
 * Yatzy mäng Javas
 *
 * Autorid: Mattias Antsov, Toomas Kangur
 *
 **********************************/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Main meetod, kus toimub mängu mängimine. Mänguks vajalikud objektid (täringud, mängijad, mänguväli) on viidud eraldi
 * klassidesse.
 * Mäng küsib mitu mängijat on, küsib mängijate nimesid ning siis töötab vastavalt Yatzy mängu ülesehitusele.
 *
 * Juhiseks on nii palju, et igal täringul on oma indeks (1-5) ning nad ilmuvad õiges järjekorras.
 * Et täringut alles jätta, on vaja sisestada selle indeks (nt, et jätta alles täringud 2,3 ja 5, tuleb sisestada 235).
 * Et kõik täringud uuesti visata, tuleb sisestada 0.
 * Et lõpetada enne, kui 3 viset täis on, tuleb sisestada -1.
 *
 * Kui mäng küsib kategooriat, siis valikud, mida sisestada (ja nii tuleb ka sisestada) on {"ühed", "kahed", "kolmed",
 * "neljad", "viied", "kuued", "paar", "2paar", "kolmik", "nelik", "väikerida", "suurrida", "maja", "yatzy", "juhuslik"}.
 *
 * Meeldivat mängukogemust!
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String j2ta = "";
        int round = 1;
        Paigutaja paigutaja = new Paigutaja();

        M2nguv2li yatzi = new M2nguv2li(15);
        List<M2ngija> m2ngijad = new ArrayList<>();
        ArrayList<T2ring> t2ringud = new ArrayList<>(Arrays.asList(new T2ring("1"),
                new T2ring("2"), new T2ring("3"),
                new T2ring("4"), new T2ring("5")));

        System.out.print("Mitu mängijat on? ");
        int m2ngijateArv = scanner.nextInt();
        System.out.println(m2ngijateArv);

        for (int i = 0; i < m2ngijateArv; i++) {
            System.out.print("Sisesta mängija nimi: ");
            M2ngija isik = new M2ngija(scanner.next());
            m2ngijad.add(isik);
        }

        while (yatzi.getTyhjadV2ljad() > 0) {
            System.out.println("Round number: " + (round++));
            for (M2ngija m2ngija : m2ngijad) {
                System.out.println("Mängija " + m2ngija.getNimi() + " kord. Veeretan...");
                j2ta = "0";
                for (int i = 0; i < 3; i++) {
                    for (T2ring t2ring : t2ringud) {
                        if (!j2ta.contains(t2ring.getKoht()) || j2ta.equals("0")) {
                            t2ring.veereta();
                        }
                        System.out.print(t2ring.getSilmad() + " ");
                    }
                    if (i < 2) {
                        System.out.println("Millised täringud alles jätta?");
                        j2ta = scanner.next();
                        if (j2ta.equals("-1")) {
                            break;
                        }
                    }
                }
                System.out.println("Millisesse kategooriasse soovid summa paigutada? ");
                String kuhu = scanner.next();
                paigutaja.paiguta(m2ngija, kuhu, t2ringud);

            }
            yatzi.setTyhjadV2ljad(yatzi.getTyhjadV2ljad() - 1);
        }

        for (M2ngija m2ngija : m2ngijad) {
           paigutaja.boonus(m2ngija);
           m2ngija.getSumma();
        }

        m2ngijad.sort(M2ngija::compareTo);

        System.out.println(m2ngijad);
    }

}