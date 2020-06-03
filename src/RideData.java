import java.io.IOException;
import java.util.List;

/**
 * This interface deals with the data on rides.
 *
 * pupil - gmb18183
 */

public interface RideData {

    List<Ride> getRides() throws IOException;

    // internal Ride class used with file handling.
    class Ride {
        String name;
        int height;
        Range heightRange;
        String wheelchair;
        int groupSize;
        Range groupRange;
        String theme;
        List<String> types;

        Ride() {
        }

        public String getName() {
            return name;
        }

        public int getHeight() {
            return height;
        }

        public String getWheelchair() {
            return wheelchair;
        }

        public Range getGroupRange() {
            return groupRange;
        }

        public int getGroupSize() {
            return groupSize;
        }

        public String getTheme() {
            return theme;
        }

        public List<String> getTypes() {
            return types;
        }

    }

}


