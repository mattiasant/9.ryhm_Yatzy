package org.example.graafikaga;

/**
 * @class Paigutaja     paigutab täringute skoori vastavalt objektile Mängija.
 * @method paiguta      paigutab skoori
 *
 * ülejäänud meetodid arvutavad vajaliku skoori
 *
 */

import javafx.scene.control.Alert;

import java.util.ArrayList;


public class Paigutaja {

    public Paigutaja() {
    }

    public void paiguta(M2ngija m2ngija, String kuhu, ArrayList<T2ring> t2ringud) throws AlreadyAValueError {

        if (m2ngija.getValue(kuhu) != -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Selection");
            alert.setHeaderText(null);
            alert.setContentText("Seal juba on väärtus.");
            alert.showAndWait();
            throw new AlreadyAValueError();
        } else {
            int summa = 0;
            switch (kuhu) {
                case "Ühed" -> {
                    for (T2ring t2ring : t2ringud) {
                        if (t2ring.getSilmad() == 1) {
                            summa += 1;
                        }
                    }
                    m2ngija.setYhed(summa);
                }
                case "Kahed" -> {
                    for (T2ring t2ring : t2ringud) {
                        if (t2ring.getSilmad() == 2) {
                            summa += 2;
                        }
                    }
                    m2ngija.setKahed(summa);
                }
                case "Kolmed" -> {
                    for (T2ring t2ring : t2ringud) {
                        if (t2ring.getSilmad() == 3) {
                            summa += 3;
                        }
                    }
                    m2ngija.setKolmed(summa);
                }
                case "Neljad" -> {
                    for (T2ring t2ring : t2ringud) {
                        if (t2ring.getSilmad() == 4) {
                            summa += 4;
                        }
                    }
                    m2ngija.setNeljad(summa);
                }
                case "Viied" -> {
                    for (T2ring t2ring : t2ringud) {
                        if (t2ring.getSilmad() == 5) {
                            summa += 5;
                        }
                    }
                    m2ngija.setViied(summa);
                }
                case "Kuued" -> {
                    for (T2ring t2ring : t2ringud) {
                        if (t2ring.getSilmad() == 6) {
                            summa += 6;
                        }
                    }
                    m2ngija.setKuued(summa);
                }
                case "Paar" -> m2ngija.setPaar(paar(t2ringud));
                case "Kaks paari" -> m2ngija.setKakspaar(kaksPaar(t2ringud));
                case "Kolmik" -> m2ngija.setKolmik(kolmikSumma(t2ringud));
                case "Nelik" -> m2ngija.setNelik(nelikSumma(t2ringud));
                case "Väike rida" -> m2ngija.setV2ikeRida(v2ikeRida(t2ringud));
                case "Suur rida" -> m2ngija.setSuurRida(suurRida(t2ringud));
                case "Yatzy" -> m2ngija.setYatzi(yatzy(t2ringud));
                case "Juhuslik" -> m2ngija.setJuhuslik(juhuslik(t2ringud));
                case "Maja" -> m2ngija.setMaja(maja(t2ringud));
            }
        }
    }
    private int paar(ArrayList<T2ring> t2ringud) {
        int[] lugeja = new int[6];
        int suurimSumma = 0;

        for (T2ring t2ring : t2ringud) {
            lugeja[t2ring.getSilmad() - 1]++;
        }
        for (int i = 0; i < lugeja.length; i++) {
            if (lugeja[i] >= 2) {
                int paarisumma = (i + 1) * 2;
                suurimSumma = Math.max(suurimSumma, paarisumma);
            }
        }
        return suurimSumma;
    }

    private int kaksPaar(ArrayList<T2ring> t2ringud) {
        int[] lugeja = new int[6];
        int paarideSumma = 0;
        int paarid = 0;

        for (T2ring t2ring : t2ringud) {
            lugeja[t2ring.getSilmad() - 1]++;
        }
        for (int i = 0; i < lugeja.length; i++) {
            if (lugeja[i] >= 2) {
                paarideSumma += (i + 1) * 2;
                paarid++;
            }
        }
        return paarid == 2 ? paarideSumma : 0;
    }

    private int kolmikSumma(ArrayList<T2ring> t2ringud) {
        int[] counts = new int[6];
        for (T2ring t2ring : t2ringud) {
            counts[t2ring.getSilmad() - 1]++;
        }
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] >= 3) {
                return (i + 1) * 3;
            }
        }
        return 0;
    }

    private int nelikSumma(ArrayList<T2ring> t2ringud) {
        int[] counts = new int[6];
        for (T2ring t2ring : t2ringud) {
            counts[t2ring.getSilmad() - 1]++;
        }
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] >= 4) {
                return (i + 1) * 4;
            }
        }
        return 0;
    }

    private int v2ikeRida(ArrayList<T2ring> t2ringud) {
        int[] counts = new int[6];

        for (T2ring t2ring : t2ringud) {
            counts[t2ring.getSilmad() - 1]++;
        }

        if (counts[0] == 1 && counts[1] == 1 && counts[2] == 1 && counts[3] == 1 && counts[4] == 1) {
            return 15;
        } else return 0;
    }

    private int suurRida(ArrayList<T2ring> t2ringud) {
        int[] counts = new int[6];
        for (T2ring t2ring : t2ringud) {
            counts[t2ring.getSilmad() - 1]++;
        }
        if (counts[1] == 1 && counts[2] == 1 && counts[3] == 1 && counts[4] == 1 && counts[5] == 1) {
            return 20;
        } else return 0;
    }

    private int maja(ArrayList<T2ring> t2ringud) {
        boolean paar = false;
        boolean kolmik = false;
        int[] counts = new int[6];

        for (T2ring t2ring : t2ringud) {
            counts[t2ring.getSilmad() - 1]++;
        }

        for (int count : counts) {
            if (count == 3) {
                kolmik = true;
            }
            if (count == 2) {
                paar = true;
            }
        }
        if (kolmik && paar) {
            int sum = 0;
            for (T2ring t2ring : t2ringud) {
                sum += t2ring.getSilmad();
            }
            return sum;
        }
        return 0;
    }

    private int juhuslik(ArrayList<T2ring> t2ringud) {
        int summa = 0;
        for (T2ring t2ring : t2ringud) {
            summa += t2ring.getSilmad();
        }
        return summa;
    }

    private int yatzy(ArrayList<T2ring> t2ringud) {
        int[] counts = new int[6];
        for (T2ring t2ring : t2ringud) {
            counts[t2ring.getSilmad() - 1]++;
        }
        for (int count : counts) {
            if (count == 5) {
                return 100;
            }
        }
        return 0;
    }

    public void boonus(M2ngija m2ngija) {
        if (m2ngija.getYhed()+ m2ngija.getKahed()+ m2ngija.getKolmed()+ m2ngija.getNeljad()+ m2ngija.getViied()+ m2ngija.getKuued()>=63) {
            m2ngija.setBoonus(50);
        }
    }
}

