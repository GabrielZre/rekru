package modelproduct;

public class Rate {
    String rate;
    int count;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Rate() {}

    public Rate(String rate, int count) {
        this.rate = rate;
        this.count = count;
    }
}
