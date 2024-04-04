/**
 * @class M2ngija   on objekti Mängija loomiseks
 */

public class M2ngija implements Comparable<M2ngija>{

    private String nimi;
    private int yhed;
    private int kahed;
    private int kolmed;
    private int neljad;
    private int viied;
    private int kuued;
    private int boonus;
    private int paar;
    private int kakspaar;
    private int kolmik;
    private int nelik;
    private int maja;
    private int v2ikeRida;
    private int suurRida;
    private int juhuslik;
    private int yatzi;
    private int summa;

    public M2ngija(String nimi) {
        this.nimi = nimi;
        this.yhed = 0;
        this.kahed = 0;
        this.kolmed = 0;
        this.neljad = 0;
        this.viied = 0;
        this.kuued = 0;
        this.boonus = 0;
        this.paar = 0;
        this.kakspaar = 0;
        this.kolmik = 0;
        this.nelik = 0;
        this.maja = 0;
        this.v2ikeRida = 0;
        this.suurRida = 0;
        this.juhuslik = 0;
        this.yatzi = 0;
        this.summa = 0;
    }

    public void setYhed(int yhed) {
        this.yhed = yhed;
    }

    public void setKahed(int kahed) {
        this.kahed = kahed;
    }

    public void setKolmed(int kolmed) {
        this.kolmed = kolmed;
    }

    public void setNeljad(int neljad) {
        this.neljad = neljad;
    }

    public void setViied(int viied) {
        this.viied = viied;
    }

    public void setKuued(int kuued) {
        this.kuued = kuued;
    }

    public void setBoonus(int boonus) {
        this.boonus = boonus;
    }

    public void setPaar(int paar) {
        this.paar = paar;
    }

    public void setKakspaar(int kakspaar) {
        this.kakspaar = kakspaar;
    }

    public void setKolmik(int kolmik) {
        this.kolmik = kolmik;
    }

    public void setNelik(int nelik) {
        this.nelik = nelik;
    }

    public void setMaja(int maja) {
        this.maja = maja;
    }

    public void setV2ikeRida(int v2ikeRida) {
        this.v2ikeRida = v2ikeRida;
    }

    public void setSuurRida(int suurRida) {
        this.suurRida = suurRida;
    }

    public void setJuhuslik(int juhuslik) {
        this.juhuslik = juhuslik;
    }

    public void setYatzi(int yatzi) {
        this.yatzi = yatzi;
    }

    public String getNimi() {
        return nimi;
    }

    public int getYhed() {
        return yhed;
    }

    public int getKahed() {
        return kahed;
    }

    public int getKolmed() {
        return kolmed;
    }

    public int getNeljad() {
        return neljad;
    }

    public int getViied() {
        return viied;
    }

    public int getKuued() {
        return kuued;
    }

    public int getSumma() {
        summa = yhed + kahed + kolmed + neljad + viied + kuued + boonus + paar + kakspaar + kolmik +
                +nelik + maja + v2ikeRida + suurRida + juhuslik + yatzi;
        this.summa = summa;
        return summa;
    }

    @Override
    public String toString() {
        return "M2ngija{" +
                "nimi='" + nimi + '\'' +
                ", summa=" + summa +
                '}';
    }


    @Override
    public int compareTo(M2ngija o) {
        return Integer.compare(this.getSumma(),o.getSumma());
    }
}
