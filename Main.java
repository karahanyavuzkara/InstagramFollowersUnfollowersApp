import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\path\\to\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            driver.get("https://www.instagram.com/");
            WebElement username = driver.findElement(By.xpath("//input[@name='username']"));
            username.sendKeys("your_username");
            WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
            password.sendKeys("your_password");
            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
            loginButton.click();

            // Wait until the login process is completed
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.urlContains("https://www.instagram.com/"));

            // Navigate to the profile page
            driver.get("https://www.instagram.com/your_username/");

            // Click on the followers button
            WebElement followersLink = driver.findElement(By.xpath("//a[contains(@href,'/followers/')]"));
            followersLink.click();

            // Wait until the followers modal is loaded
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='dialog']")));

            // Scroll down to load all followers
            WebElement followersList = driver.findElement(By.xpath("//div[@class='isgrP']"));
            while (true) {
                followersList.sendKeys(Keys.END);
                Thread.sleep(1000);
                WebElement loadMoreButton = driver.findElement(By.xpath("//button[contains(@class,'sqdOP') and contains(@class,'L3NKy') and contains(@class,'_8A5w5')]"));
                if (!loadMoreButton.isDisplayed()) {
                    break;
                }
                loadMoreButton.click();
            }

            // Get the number of followers
            WebElement followersCount = driver.findElement(By.xpath("//a[contains(@href,'/followers/')]/span[@class='g47SY ']"));
            System.out.println("Number of followers: " + followersCount.getText());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
