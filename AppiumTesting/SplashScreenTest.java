package testing;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class SplashScreenTest {
    
    public static void main(String[] args) throws MalformedURLException {
        
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "motorola motorola edge 50 fusion");
        caps.setCapability("appPackage", "com.example.healthfitnesstrackerapp");
        caps.setCapability("appActivity", "com.example.healthfitnesstrackerapp.SplashActivity"); 
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        long startTime = System.currentTimeMillis(); 

   
        WebElement logo = driver.findElement(By.id("com.example.healthfitnesstrackerapp:id/splash_logo"));
        if (logo.isDisplayed()) {
            System.out.println("App Logo is visible.");
        } else {
            System.out.println("Logo not showing.");
        }

        
        while (true) {
            try {
                logo.isDisplayed(); 
            } catch (Exception e) {
                break; 
            }
            
            if ((System.currentTimeMillis() - startTime) >= 3000) {
                break; 
            }
        }

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Applogo was displayed for: " + (duration / 1000.0) + " seconds");

        WebElement onboardingScreen = driver.findElement(By.id("com.example.healthfitnesstrackerapp:id/skipButton"));
        if (onboardingScreen.isDisplayed()) {
            System.out.println("Onboarding screen is displayed with slide items .");
        } else {
            System.out.println("Onboarding screen is not visible.");
        }

        driver.quit();
    }
}