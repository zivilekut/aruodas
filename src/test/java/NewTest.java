import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class NewTest {
    ChromeDriver _globalDriver;

    public WebElement snoozeUntilPresence(By by) {
        WebDriverWait wait = new WebDriverWait(_globalDriver, Duration.ofSeconds(30));

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void selectMunicipality(String municipality) {
        snoozeUntilPresence(By.xpath("//*[@id=\"newObjectForm\"]/ul/li[3]/span[1]/span")).click();
        snoozeUntilPresence(By.xpath("//*[@id=\"regionDropdown\"]/li[1]/input")).sendKeys(municipality);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            if (_globalDriver.findElement(By.xpath("//*[@id=\"regionDropdown\"]/li[64]")).isDisplayed()) {
                System.out.println("You should write a longer name, because more than 1 option appears.");
            } else {
                snoozeUntilPresence(By.xpath("//*[@id=\"regionDropdown\"]/li[63]")).click();
            }
        } catch (NoSuchElementException e) {
            snoozeUntilPresence(By.xpath("//*[@id=\"regionDropdown\"]/li[63]")).click();
        }

    }

    @BeforeTest
    public void setupWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        _globalDriver = new ChromeDriver(options);
        _globalDriver.get("https://www.aruodas.lt/ideti-skelbima/?obj=1");
        snoozeUntilPresence(By.id("onetrust-reject-all-handler")).click();
    }

    @Test //siauliai - zaliukiu k - danes g
    public void test1() {
        selectMunicipality("Šiauliai");
    }
}
