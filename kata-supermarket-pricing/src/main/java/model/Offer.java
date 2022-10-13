package model;

public class Offer {

    private float qte;
    private double price;

    public Offer(float qte, double price) {
        super();
        this.qte = qte;
        this.price = price;
    }

    public float getQte() {
        return qte;
    }

    public void setQte(float qte) {
        this.qte = qte;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Offer [qte=" + qte + ", price=" + price + "]";
    }

}
