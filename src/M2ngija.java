public class M2ngija {

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
        this.yhed = -1;
        this.kahed = -1;
        this.kolmed = -1;
        this.neljad = -1;
        this.viied = -1;
        this.kuued = -1;
        this.boonus = -1;
        this.paar = -1;
        this.kakspaar = -1;
        this.kolmik = -1;
        this.nelik = -1;
        this.maja = -1;
        this.v2ikeRida = -1;
        this.suurRida = -1;
        this.juhuslik = -1;
        this.yatzi = -1;
        this.summa = getSumma();
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

    public int getSumma() {
        summa = yhed+kahed+kolmed+neljad+viied+kuued+boonus+paar+kakspaar+kolmik+
                +nelik+maja+v2ikeRida+suurRida+juhuslik+yatzi;
        return summa;
    }

    @Override
    public String toString() {
        return "M2ngija{" +
                "nimi='" + nimi + '\'' +
                ", yhed=" + yhed +
                ", kahed=" + kahed +
                ", kolmed=" + kolmed +
                ", neljad=" + neljad +
                ", viied=" + viied +
                ", kuued=" + kuued +
                ", boonus=" + boonus +
                ", paar=" + paar +
                ", kakspaar=" + kakspaar +
                ", kolmik=" + kolmik +
                ", nelik=" + nelik +
                ", maja=" + maja +
                ", v2ikeRida=" + v2ikeRida +
                ", suurRida=" + suurRida +
                ", juhuslik=" + juhuslik +
                ", yatzi=" + yatzi +
                ", summa=" + summa +
                '}';
    }
}
