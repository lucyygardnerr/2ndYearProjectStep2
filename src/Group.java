import java.util.List;

public class Group {

    String name;
    String wheelchair;
    String email;
    double height;
    List rides;

    public Group(){

    }

    public String isWheelchair() {
        return wheelchair;
    }

    public boolean setWheelchair(String wheelchair) {
        if(!wheelchair.equals("Y") && !wheelchair.equals("N")){
            System.out.println("Please enter either capital letter Y or N: ");
            return false;
        }
        else{
            this.wheelchair = wheelchair;
            return true;
        }
    }

    public void setRides(List rides) {
        this.rides = rides;
    }

    public List getRides() {

        return rides;
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
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

    public boolean validateEmail(String email){
        if(email.contains("@")){
            return true;
        }
        else{
            return false;
        }
    }

    public double getHeight() {
        return height;
    }

    public boolean setHeight(double height) {
        if(height >= 1 && height < 2.5) {
            this.height = height;
            return true;
        }
        else{
            System.out.println("Please re-enter a normal height: ");
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
