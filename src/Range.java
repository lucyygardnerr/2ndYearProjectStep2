public class Range {

    private double low;
    private double high;

    public Range(double low, double high){
        this.low = low;
        this.high = high;
    }

    public boolean check(double number){
        return (number >= low && number <= high);
    }
}
