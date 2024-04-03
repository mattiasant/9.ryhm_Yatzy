public class T2ring {
    private String koht;
    private int silmad;

    public T2ring(String koht) {
        this.koht = koht;
    }

    public int getSilmad() {
        return silmad;
    }

    public void veereta() {
        this.silmad = (int) (1 + Math.random()*5);
    }

    public String getKoht() {
        return koht;
    }
}
