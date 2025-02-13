package testing;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class OnboardingScreenTest {
    public static AppiumDriver driver;

    public static void main(String[] args) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "motorola motorola edge 50 fusion");
        caps.setCapability("appPackage", "com.example.healthfitnesstrackerapp");
        caps.setCapability("appActivity", "com.example.healthfitnesstrackerapp.SplashActivity");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        Thread.sleep(3000); // Wait for the screen to load

        // Check if the slider (ViewPager) is present
        WebElement swipe = driver.findElement(By.id("viewPager"));
        if (swipe.isDisplayed()) {
            System.out.println("Swipe (ViewPager) is present.");
        }

        // Capture initial text/content before swipe
        WebElement contentElement = driver.findElement(By.id("viewPager")); // Change to correct element ID
        String beforeSwipeText = contentElement.getText();
        System.out.println("Before Swipe: " + beforeSwipeText);

        // Perform swipe left
        swipeLeft();
        Thread.sleep(2000); // Wait for animation

        // Capture text/content after swipe
        String afterSwipeText = contentElement.getText();
        System.out.println("After Swipe: " + afterSwipeText);

        // Check if content has changed
        if (!beforeSwipeText.equals(afterSwipeText)) {
            System.out.println("Swipe left worked. Content has changed.");
        } else {
            System.out.println("Swipe left failed. Content did not change.");
        }

        // Check if the Skip button is present
        WebElement skipButton = driver.findElement(By.id("skipButton")); // Change ID if necessary
        if (skipButton.isDisplayed()) {
            System.out.println("Skip button is present.");
            skipButton.click();
            System.out.println("Clicked on Skip button.");
        }
        Thread.sleep(3000); 

        // Verify if Dashboard screen is displayed (Replace with actual Dashboard element ID)
        try {
            WebElement dashboardElement = driver.findElement(By.id("tvSteps")); // Replace with actual ID
            if (dashboardElement.isDisplayed()) {
                System.out.println("Navigation to Dashboard successful!");
            } else {
                System.out.println("Navigation to Dashboard failed.");
            }
        } catch (Exception e) {
            System.out.println("Dashboard element not found. Navigation might have failed.");
        }

        // Close driver
        driver.quit();
    }

    public static void swipeLeft() {
        if (driver == null) {
            System.out.println("Driver is null. Cannot perform swipe.");
            return;
        }

        int startX = driver.manage().window().getSize().width * 3 / 4;
        int endX = driver.manage().window().getSize().width / 4;
        int startY = driver.manage().window().getSize().height / 2;

        // Create a PointerInput instance for touch gestures
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(finger, 1);

        // Press down at (startX, startY)
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        // Move to (endX, startY) to simulate swipe
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, startY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform the swipe action
        driver.perform(Arrays.asList(swipe));

        System.out.println("Swiped left successfully.");
    }
}

