import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AruodasTest {
    ChromeDriver driver;
    String _email;
    String _user;

    @BeforeTest
    public void SetupWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        _email = generateRandomEmail();
        _user = generateRandomUsername();

    }

    public static String generateRandomEmail() { // galima naudoti, kai reikės random emails
        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "example.com"};
        String[] characters = {"abcdefghijklmnopqrstuvwxyz", "0123456789"};

        Random random = new Random();

        StringBuilder email = new StringBuilder();

        // Generate username part
        int usernameLength = random.nextInt(10) + 8; //
        for (int i = 0; i < usernameLength; i++) {
            String characterSet = characters[random.nextInt(2)]; // Selecting either alphabets or numbers
            char randomChar = characterSet.charAt(random.nextInt(characterSet.length()));
            email.append(randomChar);
        }

        // Adding '@' symbol
        email.append("@");

        // Selecting random domain
        String randomDomain = domains[random.nextInt(domains.length)];
        email.append(randomDomain);

        return email.toString();
    }

    public static String generateRandomUsername() { // galima naudoti, kai reikės random username

        String[] characters = {"abcdefghijklmnopqrstuvwxyz"};

        Random random = new Random();

        StringBuilder username = new StringBuilder();

        int usernameLength = random.nextInt(10) + 5; // Random length between 5 to 14 characters
        for (int i = 0; i < usernameLength; i++) {
            String characterSet = characters[random.nextInt(1)]; // Selecting either alphabets or numbers
            char randomChar = characterSet.charAt(random.nextInt(characterSet.length()));
            username.append(randomChar);
        }

        return username.toString();
    }

    @Test //Registracija su valid data
    public void test1() {
        driver.get("https://www.aruodas.lt");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("onetrust-reject-all-handler")).click();

        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/div[1]/div[1]/div/div[2]/div")).click(); // prisijungti
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("/html/body/div[8]/div/div[10]/div/div/div/div[2]/div[2]/div[1]/a")).click(); // registruotis
        driver.findElement(By.id("userName")).sendKeys(_email);
        driver.findElement(By.id("password")).sendKeys(_user);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("registerButton")).click();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("/html/body/div[1]/form/button")).click(); // tikrina ar robotas


       /* WebElement resultText = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/div[1]/div[1]/a/span[2]"));
        Assert.assertEquals(resultText.getText(), "Mano aruodas");*/
        driver.close();
    }

    @Test //Prisijungimas su valid data
    public void test2() {
        driver.get("https://www.aruodas.lt");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("onetrust-reject-all-handler")).click();

        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/div[1]/div[1]/div/div[2]/div")).click(); // prisijungti

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("userName")).sendKeys("test@test.com");
        driver.findElement(By.id("password")).sendKeys("Testing#1");
        driver.findElement(By.id("loginAruodas")).click();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement resultText = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/div[1]/div[1]/a/span[2]"));
        Assert.assertEquals(resultText.getText(), "Mano aruodas");
        driver.close();
    }

    @Test // Skelbimo įdėjimas
    public void test3() {
        driver.get("https://www.aruodas.lt");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("onetrust-reject-all-handler")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div/a")).click(); // įdėkite skelbimą

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("/html/body/div[1]/div[2]/ul/li[2]/ul/li[1]")).click(); // butas
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/ul/li[3]/ul/li[1]")).click(); // pardavimui
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[3]/span[1]/span")).click(); // savivaldybė
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[3]/span[1]/ul[2]/li[2]")).click(); // Vilnius
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[4]/span[1]/span")).click(); // gyvenvietė
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[4]/span[1]/ul[2]/li[8]")).click(); // Baltosios Vokės k.

        driver.findElement(By.id("fieldFAreaOverAll")).sendKeys("100"); // plotas
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[15]/div/div[3]/div[2]")).click(); // kambarių sk.

        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[16]/div[1]/span[2]/span")).click(); // aukštas
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[16]/div[1]/span[2]/ul/li[1]")).click(); // 1 aukštas
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[16]/div[2]/span[1]/span")).click(); // iš
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[16]/div[2]/span[1]/ul/li[5]")).click(); // iš 5

        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[17]/div[1]/span[1]/span/input")).sendKeys("1990"); // statybos metai
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[18]/div/div[1]/div[2]")).click(); // mūrinis
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[19]/div/div[1]/div[2]")).click(); // įrengtas

        driver.findElement(By.id("uploadPhotoBtn")).sendKeys("C:\\Users\\zivil\\Downloads\\kekw.jpg"); // statybos metai


    }
}
