import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class FileHandler implements RideData {

    private String fileName = "Rides.txt";
    List<Ride> rides = new ArrayList<>();

    @Override
    public List<Ride> getRides() throws IOException {
        //yes - increment the end number, save and return it
        //If no - create a new number in the file, save and return it

        //check if file exists first
        if(!Files.exists(Paths.get(fileName))) {
            System.out.println("File does not exist");
        }

        BufferedReader br = new BufferedReader(new FileReader("Rides.txt"));

        for (String line = br.readLine(); line != null; line = br.readLine()){
            String parts[] = line.split(", ");
            Ride ride = new Ride();
            rides.add(ride);
            ride.name = parts[0];
            checkHeight(ride, parts[1]);
            ride.wheelchair = parts[2];
            checkGroupSize(ride, parts[3]);
            ride.theme = parts[4];
            System.out.println(Arrays.asList(parts).subList(5, parts.length));
            ride.types.addAll(Arrays.asList(parts).subList(5, parts.length));
        }

        return rides;
    }

    private void checkHeight(Ride ride, String height){
        if(height.equals("N")){
            return;
        }
        if (height.contains("-")){
            String[] ranges = height.split("-");
            ride.heightRange = new Range(parseInt(ranges[0]), parseInt(ranges[1]));
        }
        if( height.contains(">")){
            String[] ranges = height.split(">");
            ride.heightRange = new Range(parseInt(ranges[1]), 2.5);
        }
        if( height.contains("<")){
            String[] ranges = height.split("<");
            ride.heightRange = new Range(0.7, parseInt(ranges[1]));
        }
    }

    private void checkGroupSize(Ride ride, String groupSize){
        if(groupSize.equals("N")){
            return;
        }
        if(groupSize.contains("-")){
            String[] ranges = groupSize.split("-");
            ride.groupRange = new Range(parseInt(ranges[0]), parseInt(ranges[1]));
        }
        else{
            System.out.println(groupSize);
            ride.groupSize = parseInt(groupSize);
        }
    }


}
