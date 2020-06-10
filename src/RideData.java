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

        public String getName() {
            return name;
        }

        public Range getHeightRange() {
            return heightRange;
        }

        public String getWheelchair() {
            return wheelchair;
        }

        public int getGroupSize() {
            return groupSize;
        }

        public Range getGroupRange() {
            return groupRange;
        }

        public String getTheme() {
            return theme;
        }

        public List<String> getTypes() {
            return types;
        }
    }

}


