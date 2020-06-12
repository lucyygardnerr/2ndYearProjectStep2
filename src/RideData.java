// LUCY GARDNER GMB18183
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This interface deals with the data on rides.
 *
 * pupil - gmb18183
 */

public interface RideData {

    void getRidesFromFile() throws IOException;

    // internal Ride class used with file handling.
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

}


