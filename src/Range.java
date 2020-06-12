// LUCY GARDNER GMB18183
public class Range {

    private double low;
    private double high;

    Range(double low, double high){
        this.low = low;
        this.high = high;
    }

    boolean check(double number){
        return (number >= low && number <= high);
    }

    public String toString(){
        return "The range is: " + low + " - " + high;
    }
}
