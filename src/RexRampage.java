import java.util.ArrayList;
import java.util.List;

class RexRampage extends RideData.Ride {

    private List<String> types = new ArrayList<>();
    private int groupSize = 2;
    private String wheelchair = "N";

    public List<String> getTypes() {
        return types;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public String getWheelchair() {
        return wheelchair;
    }

    RexRampage(){
        types.add("Water");
        types.add("Adrenaline");
        types.add("Horror");
    }

}
