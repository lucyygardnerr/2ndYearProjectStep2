import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

public class Menu{

    private Scanner scanner = new Scanner(System.in);
    private String eOrP;
    private Group group = new Group();
    private int groupSize;
    private List<String> rideTypes = new ArrayList<>();
    private List<String> reasons = new ArrayList<>();
    private List<RideData.Ride> applicableRides = new ArrayList<>();
    private RideData.Ride ride = new RideData.Ride();
    private int option;
    private FileHandler fileHandler = new FileHandler();

    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        menu.start();
    }

    void start() throws IOException {
        // Ride chosen is Rex Rampage
        System.out.println("Welcome to Rex Rampage!");

        printMenu();

        boolean run = true;

        while(run) {
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    option =1;
                    ride = new RexRampage();
                    System.out.println("Please enter information on your group so we can generate your recommendations! \nPlease enter your first name: ");
                    group.setName(scanner.next());
                    email();
                    break;
                case 2:
                    option =2;
                    fileHandler.getRides();
                    System.out.println("Please enter information on your group so we can generate your recommendations! \nPlease enter your first name: ");
                    group.setName(scanner.next());
                    email();
                case 3:
                    System.out.println("Goodbye - thank you for visiting.");
                    run = false;
                    System.exit(0);
            }
        }
    }

    private void printMenu(){
        System.out.println("\nPlease choose an option:");
        System.out.println();
        System.out.println("1. Get recommendations for Rex Rampage.");
        System.out.println("2. Get recommendations for the entire theme park");
        System.out.println("3. Quit.");
        System.out.println();
    }

    private void email() throws IOException {
        System.out.println("Hi " + group.getName() + " would you prefer me to print your recommendations or email them to you? \n ** Please enter capital E for email or P for print** ");
        eOrP = scanner.next();
        if(!eOrP.equals("E") && !eOrP.equals("P")){
            System.out.println("Invalid - please enter either E or P: ");
            eOrP = scanner.next();
        }
        if(eOrP.equals("E")){
            System.out.println("You have chosen email - please enter a valid email address: ");
            boolean validated = group.setEmail(scanner.next());
            while(!validated) {
                validated = group.setEmail(scanner.next());
            }
            setGroupSize();
        }
        else if(eOrP.equals("P")) {
            System.out.println("You have chosen to print your recommendations.");
            setGroupSize();
        }
    }

    private void setGroupSize() throws IOException {
        System.out.println("\nHow many people are in your party? ");
        groupSize = scanner.nextInt();
        setHeight();
    }

    private void setHeight() throws IOException {
        System.out.println("Please use the height chart next to this terminal to measure the height of the SMALLEST group in your group. \n** Please enter height in meters? e.g 1.2 **");
        boolean validated = group.setHeight(scanner.nextDouble());
        while(!validated){
            validated = group.setHeight(scanner.nextDouble());
        }
        setWheelchair();
    }

    private void setWheelchair() throws IOException {
        System.out.println("Is anyone in your group a wheelchair user ? \n** Please enter capital Y for yes and N for no **");
        boolean validated = group.setWheelchair(scanner.next());
        while(!validated){
            validated = group.setWheelchair(scanner.next());
        }
        getRides();
    }


    private void getRides() throws IOException {

        String input;
        List<String> types = new ArrayList<>();
        types.add("Kids");
        types.add("Water");
        types.add("Horror");
        types.add("Adrenaline");

        System.out.println("Which of the following ride types does your group like? ");
        System.out.println("** Please enter capital Y for yes and N for no **");
        for (int i=0; i< types.size(); i++){
            System.out.println(types.get(i) + ": ");
            input = scanner.next();

            if(!input.equals("Y") && !input.equals("N")){
                System.out.println("Please enter either capital letter Y or N! ");
                i--;
            }
            if(input.equals("Y")){
                rideTypes.add(types.get(i));
            }
        }

        System.out.println("\nGetting your recommendations... ");
        if(option == 1){
            getRecommendations();
        }
        else if( option == 2){
            getParkRecommendations();
        }
    }

    private void getRecommendations() throws IOException {

        System.out.println("\nRecommendations for " + group.getName() + "'s group created on " + setDateTime() + "->");

        preOrder(buildTree());

        printEndOptions();
    }

    private void getParkRecommendations() throws IOException {

        System.out.println("\nRecommendations for " + group.getName() + "'s group created on " + setDateTime() + "->");

        ride = fileHandler.rides.get(0);
        LinkedBinaryTree.Node<Supplier<Boolean>> root = buildTree();

        for(int i = 0; i < fileHandler.rides.size(); i++){
            ride = fileHandler.rides.get(i);
            preOrder(root);
        }

        printEndOptionsFull();

    }

    private LinkedBinaryTree.Node<Supplier<Boolean>> buildTree(){
        LinkedBinaryTree.Node<Supplier<Boolean>> rideT = new LinkedBinaryTree.Node<>(() ->true, null, null );

        LinkedBinaryTree.Node<Supplier<Boolean>> wheelchairYes4 = new LinkedBinaryTree.Node<>(this::checkTypes, rideT, rideT );
        LinkedBinaryTree.Node<Supplier<Boolean>> wheelchairNo4 = new LinkedBinaryTree.Node<>(this::checkTypes, rideT, rideT );
        LinkedBinaryTree.Node<Supplier<Boolean>> wheelchairYes3 = new LinkedBinaryTree.Node<>(this::checkTypes, rideT, rideT );
        LinkedBinaryTree.Node<Supplier<Boolean>> wheelchairNo3 = new LinkedBinaryTree.Node<>(this::checkTypes, rideT, rideT );
        LinkedBinaryTree.Node<Supplier<Boolean>> wheelchairYes2 = new LinkedBinaryTree.Node<>(this::checkTypes, rideT, rideT );
        LinkedBinaryTree.Node<Supplier<Boolean>> wheelchairNo2 = new LinkedBinaryTree.Node<>(this::checkTypes, rideT, rideT );
        LinkedBinaryTree.Node<Supplier<Boolean>> wheelchairYes = new LinkedBinaryTree.Node<>(this::checkTypes, rideT, rideT );
        LinkedBinaryTree.Node<Supplier<Boolean>> wheelchairNo = new LinkedBinaryTree.Node<>(this::checkTypes, rideT, rideT );

        LinkedBinaryTree.Node<Supplier<Boolean>> heightNo = new LinkedBinaryTree.Node<>(this::checkWheelchair, wheelchairNo, wheelchairYes );
        LinkedBinaryTree.Node<Supplier<Boolean>> heightYes = new LinkedBinaryTree.Node<>(this::checkWheelchair, wheelchairNo2, wheelchairYes2 );

        LinkedBinaryTree.Node<Supplier<Boolean>> heightNo2 = new LinkedBinaryTree.Node<>(this::checkWheelchair, wheelchairNo3, wheelchairYes3 );
        LinkedBinaryTree.Node<Supplier<Boolean>> heightYes2 = new LinkedBinaryTree.Node<>(this::checkWheelchair, wheelchairNo4, wheelchairYes4 );
        LinkedBinaryTree.Node<Supplier<Boolean>> groupYes = new LinkedBinaryTree.Node<>(this::checkHeight, heightNo2, heightYes2 );

        LinkedBinaryTree.Node<Supplier<Boolean>> height = new LinkedBinaryTree.Node<>(this::checkHeight, heightNo, heightYes );
        LinkedBinaryTree.Node<Supplier<Boolean>> groupNo = new LinkedBinaryTree.Node<>(this::checkHeight, height, null );

        return new LinkedBinaryTree.Node<>(this::checkGroupSize, groupNo, groupYes);
    }

    private void preOrder(LinkedBinaryTree.Node<Supplier<Boolean>> node) {
        if (node == null) {
            return;
        }
        if(node.getElement().get()){
            preOrder(node.getLeft());
        }
        else {
            preOrder(node.getRight());
        }
    }

    private boolean checkTypes(){
        boolean types;
        if(rideTypes.contains("Kids") && !ride.types.contains("Kids")){
            reasons.add("- This ride is not intended for kids.");
            applicableRides.remove(ride);
            types = false;
        }
        if(!rideTypes.contains("Adrenaline") && !ride.types.contains("Adrenaline")){
            reasons.add("- This ride is in the adrenaline category.");
            applicableRides.remove(ride);
            types = false;
        }
        if(!rideTypes.contains("Horror") && !ride.types.contains("Horror")){
            reasons.add("- This ride is in the horror category.");
            applicableRides.remove(ride);
            types = false;

        }
        if(!rideTypes.contains("Water") && !ride.types.contains("Water")){
            reasons.add("- This ride is in the water category.");
            applicableRides.remove(ride);
            types = false;
        }
        else{
            types = true;
        }
        return types;
    }

    private boolean checkWheelchair(){
        if(group.isWheelchair().equals("Y") && ride.wheelchair.equals("N")){
            reasons.add("- This ride is not suitable for wheelchair users.");
            applicableRides.remove(ride);
            return false;
        }
        return true;
    }

    private boolean checkHeight(){
        if(ride.heightRange != null){
            if(!ride.heightRange.check(group.getHeight())){
                reasons.add("- The smallest person in your group is not a suitable height for this ride.");
                applicableRides.remove(ride);
                return false;
            }
        }
        if(group.getHeight() < ride.height){
            reasons.add("- The smallest person in your group is not a suitable height for this ride.");
            applicableRides.remove(ride);
            return false;
        }
        return true;
    }

    private boolean checkGroupSize(){
        if(ride.groupRange != null){
            if(!ride.groupRange.check(groupSize)){
                reasons.add("- This ride is only suitable for groups of 2 people.");
                return false;
            }
        }
        if(groupSize != ride.groupSize) {
            reasons.add("- This ride is only suitable for groups of 2 people.");
            return false;
        }
        else{
            applicableRides.add(ride);
            return true;
        }
    }

    private void printEndOptionsFull() throws IOException {
        System.out.println("\nMedieval Zone: ");
        for (RideData.Ride ride: applicableRides){
            if(ride.theme.equals("Medieval")){
                System.out.println("\n" + ride.name);
            }
        }

        System.out.println("\nFuturistic Zone: ");
        for (RideData.Ride ride: applicableRides){
            if(ride.theme.equals("Futuristic")){
                System.out.println(ride.name);
            }
        }

        System.out.println("\nJurassic Zone: ");
        for (RideData.Ride ride: applicableRides){
            if(ride.theme.equals("Jurassic")){
                System.out.println(ride.name);
            }
        }

        System.out.println("\nIndustrial Zone: ");
        for (RideData.Ride ride: applicableRides){
            if(ride.theme.equals("Industrial")){
                System.out.println(ride.name);
            }
        }

        lastOptions();
    }
    private void printEndOptions() throws IOException {
        if(reasons.size() != 0){
            System.out.println("\nBased on your inputs Rex Rampage is not suitable for your party because: ");
            for (String reason : reasons) {
                System.out.println(reason);
            }
        }
        else{
            System.out.println("Rex Rampage is suitable for your party - enjoy!");
        }

        lastOptions();
    }

    private void lastOptions() throws IOException {

        System.out.println("\nDo you want to revise your choices or continue? \n** Please enter R to revise C to continue ** ");
        String input = scanner.next();
        switch (input){
            case "R":
                reasons.clear();
                rideTypes.clear();
                email();
                break;
            case "C":
                if(eOrP.equals("P")){
                    System.out.println("Thank you " + group.getName() + " for visiting - we hope you enjoyed your time at Rex Rampage!");
                    System.exit(0);
                }
                else{
                    System.out.println("Thank you " + group.getName() + ", your reommendations have been emailed to " + group.getEmail() + ". We hope you enjoyed your time at Rex Rampage!");
                    System.exit(0);
                }
        }
    }

    private String setDateTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy @ HH:mm");
        Date date = new Date();
        return formatter.format(date);
    }

}
