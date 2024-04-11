import org.example.models.Plot;
import org.example.models.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class PlotsTests {
    // public static ChromeDriver _globalDriver;
    public static WebDriverWait wait;

    public static void acceptCookies() {
        Utils._globalDriver.get("https://www.aruodas.lt/");
        Utils._globalDriver.findElement(By.id("onetrust-reject-all-handler")).click();
    }

    @BeforeClass
    public void setUp() {
        //options.addArguments("--start-headless");
        //options.addArguments("--disable-notifications");
        Utils._globalDriver = new ChromeDriver();
        wait = new WebDriverWait(Utils._globalDriver, Duration.ofSeconds(20));
        Utils._globalDriver.manage().window().maximize();
        Utils._globalDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        acceptCookies();
    }

    @Test
    public void test1() {
        Plot plot = new Plot("Vilnius", "Vilniaus m.", "Antakalnis", "A. Goštauto g.", "14", "12345678");
        plot.fillAd();
        Assert.assertEquals(true, true);
    }
}
