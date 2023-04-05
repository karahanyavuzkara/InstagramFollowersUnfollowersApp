import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\karah\\Downloads\\chromedriver_win32\\chromedriver.exe");

        // initialize the WebDriver
        WebDriver driver = new ChromeDriver();

        // navigate to your Instagram profile page
        driver.get("https://www.instagram.com/karahanac/");

        // wait for the page to load
        Thread.sleep(5000);

        // find the "Followers" button and click it
        WebElement followersButton = driver.findElement(By.xpath("//a[@href='/your_username/followers/']"));
        followersButton.click();

        // wait for the followers list to load
        Thread.sleep(5000);

        // find all the follower elements on the page
        List<WebElement> followerElements = driver.findElements(By.xpath("//div[@role='dialog']//ul//li"));

        // extract the username from each follower element and store it in a list
        List<String> followerUsernames = new ArrayList<>();
        for (WebElement followerElement : followerElements) {
            WebElement usernameElement = followerElement.findElement(By.tagName("a"));
            String username = usernameElement.getAttribute("href").substring(26, usernameElement.getAttribute("href").length() - 1);
            followerUsernames.add(username);
        }

        // output the list of follower usernames to the console
        System.out.println("Your followers:");
        for (String username : followerUsernames) {
            System.out.println(username);
        }

        // close the WebDriver
        driver.quit();
    }
}

