package testing;

import java.net.URL;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DashboardScreenTest {
    public static AppiumDriver driver;
    

    public static void main(String[] args) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "motorola motorola edge 50 fusion");
        caps.setCapability("appPackage", "com.example.healthfitnesstrackerapp");
        caps.setCapability("appActivity", "com.example.healthfitnesstrackerapp.SplashActivity"); // Your DashboardActivity
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
         // Wait for the Dashboard screen to load
        Thread.sleep(3200);
        WebElement skipButton;
        try {
            skipButton = driver.findElement(By.id("skipButton")); // Check for Skip button on onboarding screens
            if (skipButton.isDisplayed()) {
                System.out.println("Skipping onboarding screen...");
                skipButton.click();
                Thread.sleep(2000); // Wait for the transition
            }
        } catch (Exception e) {
            System.out.println("No skip button found. Skipping this step.");
        }

        // Verify if Steps Text is present
        WebElement stepsText = driver.findElement(By.id("tvSteps"));
        if (stepsText.isDisplayed()) {
            System.out.println("steps is visible");
        }

        // Verify if Calories Text is present
        WebElement caloriesText = driver.findElement(By.id("tvCalories"));
        if (caloriesText.isDisplayed()) {
            System.out.println("Calories is visible");
        }

        // Verify if Sync Button is present
        WebElement syncButton = driver.findElement(By.id("btnSync"));
        if (syncButton.isDisplayed()) {
            System.out.println("Sync Button is present.");
            // Click on the Sync button
            syncButton.click();
            System.out.println("Clicked on Sync button.");
        }

        // Wait for data sync to complete
        Thread.sleep(5000); // Wait for sync text to appear

        // Verify if Sync Status Text is present after Sync
        WebElement syncStatusText = driver.findElement(By.id("tvSyncStatus"));
        if (syncStatusText.isDisplayed()) {
            System.out.println("Sync completed successfully  " );
        } else {
            System.out.println("Sync failed or status not updated.");
        }

        // Close driver
        driver.quit();
    }
}


