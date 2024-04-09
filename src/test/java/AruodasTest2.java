import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;

public class AruodasTest2 {
    ChromeDriver _globalDriver;

    @BeforeTest
    public void setupWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        _globalDriver = new ChromeDriver(options);
        _globalDriver.get("https://www.aruodas.lt/ideti-skelbima/?obj=1");
        _globalDriver.findElement(By.id("onetrust-reject-all-handler")).click();
    }
}
