import org.junit.Test;

import java.io.IOException;

public class Step2Test {

    private Menu menu = new Menu();

    @Test
    public void testQuit() throws IOException {
        menu.start();

    }

    @Test
    public void testSetWheelchair() {
        //Also tests isWheelchair method
        Group group = new Group();
        group.setWheelchair("Y");
        System.out.println("Wheelchair set to Y: " + group.isWheelchair());
        group.setWheelchair("N");
        System.out.println("Wheelchair set to N: " + group.isWheelchair());
    }

    @Test
    public void testSetEmail() {
        // Also tests validateEmail method
        Group group = new Group();
        System.out.println("Correct case: ");
        group.setEmail("lucy@email.com");
        System.out.println("Incorrect case: ");
        group.setEmail("lucy-email.com");
    }

    @Test
    public void testSetHeight() {
        Group group = new Group();
        System.out.println("Within rage of 1 - 2.5: ");
        group.setHeight(1);
        System.out.println("Group's height: " + group.getHeight());
        System.out.println("Outwith range (2.7): ");
        group.setHeight(2.6);
    }

}
