// LUCY GARDNER GMB18183
import java.util.ArrayList;
import java.util.List;

// Ride class to handle the data on the rides of the theme park
class Ride {

    String name;
    Range heightRange;
    String wheelchair;
    int groupSize;
    Range groupRange;
    String theme;
    List<String> types = new ArrayList<>();

    Ride() {
    }

    String getName() {
        return name;
    }

    Range getHeightRange() {
        return heightRange;
    }

    String getWheelchair() {
        return wheelchair;
    }

    int getGroupSize() {
        return groupSize;
    }

    Range getGroupRange() {
        return groupRange;
    }

    List<String> getTypes() {
        return types;
    }
}


