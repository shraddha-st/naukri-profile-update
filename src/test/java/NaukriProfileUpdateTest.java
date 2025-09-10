import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class NaukriProfileUpdateTest {

    @Test
    public void updateProfile() {

        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");  // headless mode
//        options.addArguments("--headless=new");
        options.addArguments("--incognito");

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--user-data-dir=/tmp/chrome-" + System.currentTimeMillis());

        WebDriver driver = new ChromeDriver(options);

        try {

//            ExtentReport.createTestName("dropDownTest");
            String url = "https://www.naukri.com/nlogin/login";
            driver.get(url);
//            ExtentManager.getExtentTest().pass("Opening URL : " + url);

            driver.manage().window().maximize();

//            DriverManager.getDriver().get("https://www.naukri.com/nlogin/login");


            // Login


            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            WebElement username = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("usernameField"))
            );
//            driver.findElement(By.id("usernameField"))
                    username.sendKeys("sakhi.mali.work@gmail.com");
//            username.sendKeys(System.getenv("NAUKRI_USER")); // from GitHub secret

            WebElement password = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("passwordField"))
            );
//            driver.findElement(By.id("passwordField"))
            password.sendKeys("DRftgyhu@25");
//            password.sendKeys(System.getenv("NAUKRI_PASS")); // from GitHub secret

            WebElement loginBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']"))
            );
//            driver.findElement(By.xpath("//button[text()='Login']")).click();
            loginBtn.click();
            Thread.sleep(5000);

            // Navigate to profile
            driver.navigate().to
                    ("https://www.naukri.com/mnjuser/profile");
            Thread.sleep(5000);
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


            // Example: click “update” button to refresh profile
            WebElement resumehead = driver.findElement(By.xpath("//*[@id='attachCVMsgBox']"));

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", resumehead);
// wait.until(
//            ExpectedConditions.elementToBeClickable(

            WebElement resumeHeadlineUpdate = driver.findElement(
                    By.xpath("//div[@class='widgetHead']/span[@class='edit icon'][1]"));

//            ((JavascriptExecutor) DriverManager.getDriver())
//                    .executeScript("arguments[0].scrollIntoView(true);", resumeheadlineUpdate);
//            Thread.sleep(5000);

            resumeHeadlineUpdate.click();
            Thread.sleep(5000);
//            resumeheadlineUpdate.sendKeys("14 years Experienced in Manual and Automation Testing with Appium , Web applications, Mobile Testing, Functional, Non Functional Testing in the BANK, E-commerce domain. Expertise of Selenium Framework, TestNG, extent Reports, Java");
            WebElement resumeheadlineUpdateSave = driver.findElement(By.xpath("//button[text()='Save']"));

            resumeheadlineUpdateSave.click();
            Thread.sleep(5000);

//            ExtentManager.getExtentTest().info("✅ Profile refreshed successfully!");

            System.out.println("✅ Profile refreshed successfully!");

            WebElement userDrawer = driver.findElement((By.xpath("//div[@class='nI-gNb-drawer__icon-img-wrapper']")));
            userDrawer.click();
            Thread.sleep(5000);

            WebElement userLogout = driver.findElement((By.xpath("//a[@title='Logout']")));
            userLogout.click();
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }


    }
}
