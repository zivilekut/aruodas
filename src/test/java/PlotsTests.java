import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class PlotsTests {
    public static ChromeDriver _globalDriver;
    public static WebDriverWait wait;

public static void acceptCookies(){
    _globalDriver.get("https://www.aruodas.lt/");
    _globalDriver.findElement(By.id("onetrust-reject-all-handler")).click();
}

    @BeforeClass
    public void setUp() {
        //options.addArguments("--start-headless");
        //options.addArguments("--disable-notifications");
        _globalDriver = new ChromeDriver();
        wait = new WebDriverWait(_globalDriver, Duration.ofSeconds(20));
        _globalDriver.manage().window().maximize();
        acceptCookies();
    }

    @Test
    public void test1() {
        _globalDriver.get("https://www.aruodas.lt/ideti-skelbima/?obj=11&offer_type=1");

    }
}
