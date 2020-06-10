import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

class Group {

    private String name;
    private String wheelchair;
    private String email;
    private double height;
    private int groupSize;

    public List<String> getRideTypes() {
        return rideTypes;
    }

    public String getWheelchair() {
        return wheelchair;
    }

    public void clearRideTypes() {
        rideTypes.clear();

    }

    public void addRideType(String rideType) {
        rideTypes.add(rideType);

    }

    private List<String> rideTypes = new ArrayList<>();

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    Group(){

    }

    String isWheelchair() {
        return wheelchair;
    }

    boolean setWheelchair(String wheelchair) {
        if(!wheelchair.equals("Y") && !wheelchair.equals("N")){
            System.out.println("Please enter either capital letter Y or N: ");
            return false;
        }
        else{
            this.wheelchair = wheelchair;
            return true;
        }
    }

    String getEmail() {
        return email;
    }

    boolean setEmail(String email) {
        if(validateEmail(email)){
            this.email = email;
            System.out.println("Thank you, your recommendations will be emailed to " + email);
            return true;
        }
        else{
            System.out.println("Sorry " + name + " " + email + " is not a valid email address. Please try again: ");
            return false;
        }
    }

    private boolean validateEmail(String email){
        return email.contains("@");
    }

    double getHeight() {
        return height;
    }

    boolean setHeight(double height) {
        /*String stringHeight = Double.toString(height);
        if(Pattern.matches("[a-zA-Z]+", stringHeight)){
            System.out.println("Please re-enter only using numbers: ");
            return false;
        }*/
        if(height >= 0.7 && height < 2.5) {
            this.height = height;
            return true;
        }
        else{
            System.out.println("Please re-enter a normal height (between 0.7 and 2.5m) : ");
            return false;
        }
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }
}
