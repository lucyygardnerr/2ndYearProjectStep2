import java.util.ArrayList;
import java.util.List;

public class RexRampage extends RideData.Ride {

    int groupSize = 2;
    int height;
    String wheelchair = "N";
    List<String> types = new ArrayList<>();

    public RexRampage(){
        types.add("Water");
        types.add("Adrenaline");
        types.add("Horror");
    }

    public int getGroupSize() {
        return groupSize;
    }

    public int getHeight() {
        return height;
    }

    public String getWheelchair() {
        return wheelchair;
    }

    public List<String> getTypes() {
        return types;
    }
}
