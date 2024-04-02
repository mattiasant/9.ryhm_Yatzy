import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean mängi =true;
        List<M2ngija> m2ngijad = new ArrayList<>();
        T2ring t1= new T2ring();
        List<T2ring> t2ringud = new {}

        System.out.print("Mitu mängijat on? ");
        int m2ngijateArv = scanner.nextInt();
        System.out.println(m2ngijateArv);

        for (int i = 0; i < m2ngijateArv; i++) {
            System.out.print("Sisesta mängija nimi: ");
            M2ngija isik = new M2ngija(scanner.next());
            m2ngijad.add(isik);
        }

        while (mängi) {
            for (M2ngija m2ngija : m2ngijad) {

                System.out.println(m2ngija.getNimi());
            }
            mängi=false;
        }



    }
}