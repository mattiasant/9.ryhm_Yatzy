import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String j2ta="";
        int round = 1;
        Paigutaja paigutaja = new Paigutaja();

        M2nguv2li yatzi = new M2nguv2li(16);
        List<M2ngija> m2ngijad = new ArrayList<>();
        ArrayList<T2ring> t2ringud = new ArrayList<>(Arrays.asList(new T2ring("1"),
                new T2ring("2"),new T2ring("3"),
                new T2ring("4"),new T2ring("5")));

        System.out.print("Mitu mängijat on? ");
        int m2ngijateArv = scanner.nextInt();
        System.out.println(m2ngijateArv);

        for (int i = 0; i < m2ngijateArv; i++) {
            System.out.print("Sisesta mängija nimi: ");
            M2ngija isik = new M2ngija(scanner.next());
            m2ngijad.add(isik);
        }

        while (yatzi.getTyhjadV2ljad()>0) {
            System.out.println("Round number: " + (round++));
            for (M2ngija m2ngija : m2ngijad) {
                System.out.println("Mängija " + m2ngija.getNimi() + " kord. Veeretan...");
                for (int i = 0; i < 3; i++) {
                    for (T2ring t2ring:t2ringud) {
                        if (!j2ta.contains(t2ring.getKoht()) || j2ta.isEmpty()) {
                            t2ring.veereta();}
                        System.out.print(t2ring.getSilmad() + " ");
                        }
                    if (i <2) {
                        System.out.println("Millised täringud alles jätta?");
                        j2ta = scanner.next();
                        if (j2ta.equals("0")) {
                            break;
                        }
                    }
                }
                System.out.println("Millisesse asja soovid summa paigutada?");
                String kuhu = scanner.next();
                paigutaja.paiguta(m2ngija,kuhu,t2ringud);



            }
            yatzi.setTyhjadV2ljad(yatzi.getTyhjadV2ljad()-1);

        }
    }

}