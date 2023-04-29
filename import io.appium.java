import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class FitnessAppTest {

    private static AndroidDriver<MobileElement> driver;

    public static void main(String[] args) {

        try {
            DesiredCapabilities caps = new DesiredCapabilities();

            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
            caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
            caps.setCapability(MobileCapabilityType.APP, "/path/to/fitness_app.apk");

            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

            MobileElement logWorkoutButton = driver.findElementById("com.fitness.app:id/btn_log_workout");
            logWorkoutButton.click();

            // Enter exercise details
            MobileElement exerciseField = driver.findElementById("com.fitness.app:id/exercise_field");
            exerciseField.sendKeys("Bench Press");

            MobileElement setsField = driver.findElementById("com.fitness.app:id/sets_field");
            setsField.sendKeys("3");

            MobileElement repsField = driver.findElementById("com.fitness.app:id/reps_field");
            repsField.sendKeys("8");


            MobileElement saveWorkoutButton = driver.findElementById("com.fitness.app:id/save_workout_button");
            saveWorkoutButton.click();

            // Verify that the workout is saved by checking the workout history
            MobileElement workoutHistoryButton = driver.findElementById("com.fitness.app:id/btn_workout_history");
            workoutHistoryButton.click();

            MobileElement lastWorkout = driver.findElementById("com.fitness.app:id/last_workout");
            String lastWorkoutText = lastWorkout.getText();
            if (lastWorkoutText.contains("Bench Press") && lastWorkoutText.contains("3 sets x 8 reps")) {
                System.out.println("Log workout test passed!");
            } else {
                System.out.println("Log workout test failed!");
            }

            // Set a fitness goal
            MobileElement setGoalButton = driver.findElementById("com.fitness.app:id/btn_set_goal");
            setGoalButton.click();

            MobileElement goalField = driver.findElementById("com.fitness.app:id/goal_field");
            goalField.sendKeys("Run a 5k in under 30 minutes");

            MobileElement saveGoalButton = driver.findElementById("com.fitness.app:id/save_goal_button");
            saveGoalButton.click();

            
            MobileElement goalScreenButton = driver.findElementById("com.fitness.app:id/btn_goal_screen");
            goalScreenButton.click();

            MobileElement savedGoal = driver.findElementById("com.fitness.app:id/saved_goal");
            String savedGoalText = savedGoal.getText();
            if (savedGoalText.equals("Run a 5k in under 30 minutes")) {
                System.out.println("Set goal test passed!");
            } else {
                System.out.println("Set goal test failed!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());