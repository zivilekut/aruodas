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
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[21]/div/div[1]/label/span")).click(); // centrinis

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[42]/div/div[1]/a/input")).sendKeys("C:\\Users\\zivil\\Downloads\\kekw.png"); // foto
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[47]/span[2]/input")).sendKeys("100000"); // kaina
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[48]/span[1]/input")).sendKeys("63111111"); // tel nr
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[49]/span[1]/input")).sendKeys(_email); // email
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/ul/li[52]/span[1]/div/div/label/span")).click(); // sutikimas

        driver.findElement(By.id("submitFormButton")).click(); // įvesti skelbimą

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement resultText = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/span"));
        Assert.assertEquals(resultText.getText(), "Paslaugų paketo pasirinkimas");
        driver.close();
    }

    @Test // Detalioji paieška
    public void test4() {
        driver.get("https://www.aruodas.lt");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("onetrust-reject-all-handler")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/form/div/div[1]/div/div[4]/div[3]/div[2]/label/span[1]")).click(); // detalioji paieška
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("display_text_obj")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/form/div/div[1]/div/div[1]/div[1]/div[1]/div/div/div/div/ul/li[8]/label")).click(); // sklypai
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("display_FRegion")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/form/div/div[1]/div/div[1]/div[1]/div[2]/div/div/div/div/ul/li[31]/label")).click(); // Marijampolės sav.

        driver.findElement(By.id("input_FAreaOverAllMin")).sendKeys("10");
        driver.findElement(By.id("input_FPriceMax")).sendKeys("30000");
        driver.findElement(By.id("input_FSpecial_3")).click(); //vanduo
        driver.findElement(By.id("input_FOwnerDbId0")).click(); //Iš privačių asmenų
        driver.findElement(By.id("input_FOwnerDbId2")).click(); //Iš Iš agentūrų

        driver.findElement(By.id("display_text_FOrder")).click(); //rūšiavimas
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/form/div/div[1]/div/div[4]/div[1]/div/div/div/div/ul/li[3]/label")).click(); // pigesni viršuje

        driver.findElement(By.id("buttonSearchForm")).click(); //ieškoti

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement resultText = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div[5]/div/div[1]/h1/span[1]"));
        Assert.assertEquals(resultText.getText(), "Sklypas pardavimui Marijampolėje");
        driver.close();
    }

    @Test //Užsienio objektų paieška
    public void test5() {
        driver.get("https://www.aruodas.lt");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("onetrust-reject-all-handler")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/ul/li[3]/a")).click(); //užsienio objectai
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("display_object_type")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/div/div/div[1]/div/div/div/div/ul/li[1]/label")).click(); // butai pardavimui

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("display_country")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/div/div/div[2]/div/div/div/div/ul/li[4]/label")).click(); // Ispanija
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div[2]/div[1]/a/div[2]/h2")).click(); // pirmas skelbimas
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement resultText = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div[3]/div[5]/div/div[2]/div[2]"));
        Assert.assertEquals(resultText.getText(), "Ispanija");
        driver.close();
    }

    @Test //NT specialistų siūlomų objektų paieška
    public void test6() {
        driver.get("https://www.aruodas.lt");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("onetrust-reject-all-handler")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/ul/li[4]/a")).click(); //NT specialistai
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("display_obj")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/div/div/div[1]/div/div/div/div/ul/li[9]/label")).click(); //sodybos
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("display_FRegion")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/div/div/div[2]/div/div/div/div/ul/li[8]/label")).click(); //Palanga
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div[1]/div/div[4]/div[2]/div/div[1]/ul/li[1]/a")).click(); //2, namai pardavimui
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[2]/table/thead/tr/th[1]/label[1]/select")).click(); //išrikiuoti
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[2]/table/thead/tr/th[1]/label[1]/select/option[4]")).click(); //brangesni viršuje
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[2]/div[1]/a[1]/div")).click(); //atsidaryti brangiausią
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.close();
    }

    @Test //Peržiūrėti ir filtruoti visus naujus projektus
    public void test7() {
        driver.get("https://www.aruodas.lt");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("onetrust-reject-all-handler")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div[3]/div[2]/div[1]/div[3]/a")).click(); //žiūrėti visus projektus
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[5]/div/div[1]/div[2]/div[4]/a")).click(); //Klaipėda
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div[3]/div[1]/a/div[3]/div[3]/div")).click(); //Daugiau apie projektą
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement resultText = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div[3]/div[2]/div[1]/div[2]/div"));
        Assert.assertEquals(resultText.getText(), "Paklauskite vystytojo apie projektą");
        driver.close();
    }

    @Test //Kalbos pakeitimas į anglų ir po to atkeitimas į lietuvių
    public void test8() {
        driver.get("https://www.aruodas.lt");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("onetrust-reject-all-handler")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/div[1]/div[2]")).click(); //LT
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/div[1]/div[2]/ul/li[2]/a")).click(); //EN
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement resultText = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/form/div/div[2]/div/div/div/div/b"));
        Assert.assertEquals(resultText.getText(), "Here you will find your searches");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/div[1]/div[2]")).click(); //EN
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/div[1]/div[2]/ul/li[2]/a")).click(); //LT

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement resultText2 = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/form/div/div[2]/div/div/div/div/b"));
        Assert.assertEquals(resultText2.getText(), "Čia matysite savo paieškas");
        driver.close();
    }

    @Test //Butų kainų statistika (Vilnius-Kaunas)
    public void test9() {
        driver.get("https://www.aruodas.lt");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("onetrust-reject-all-handler")).click();
        driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/p/a[1]")).click(); //kainų statistika
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("objectSelect")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div/form/table/tbody/tr[2]/td[2]/select/option[2]")).click(); //butai pardavimui
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div/form/table/tbody/tr[3]/td[2]/select")).click(); //Savivaldybė
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div/form/table/tbody/tr[3]/td[2]/select/option[2]")).click(); //Vilnius
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div/form/table/tbody/tr[9]/td[2]/input")).click(); //Palyginti

        driver.findElement(By.id("objectSelect1")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div/form/table/tbody/tr[2]/td[2]/select/option[2]")).click(); //butai pardavimui
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div/form/table/tbody/tr[3]/td[3]/select")).click(); //Savivaldybė
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div/form/table/tbody/tr[3]/td[3]/select/option[3]")).click(); //Kaunas

        //datų pasirinkimas nuo
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div/form/table/tbody/tr[12]/td[2]/select[1]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div/form/table/tbody/tr[12]/td[2]/select[1]/option[2]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div/form/table/tbody/tr[12]/td[2]/select[2]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div/form/table/tbody/tr[12]/td[2]/select[2]/option[1]")).click();
        //datų pasirinkimas iki
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div/form/table/tbody/tr[12]/td[2]/select[3]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div/form/table/tbody/tr[12]/td[2]/select[3]/option[2]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div/form/table/tbody/tr[12]/td[2]/select[4]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div/form/table/tbody/tr[12]/td[2]/select[4]/option[12]")).click();

        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div/form/table/tbody/tr[12]/td[3]/div/input")).click(); //rodyti
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("userName")).sendKeys("test@test.com");
        driver.findElement(By.id("password")).sendKeys("Testing#1");
        driver.findElement(By.id("loginAruodas")).click();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement resultText2 = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div/form/table/tbody/tr[1]/td/h1"));
        Assert.assertEquals(resultText2.getText(), "Nekilnojamojo turto kainų statistika (€)");
        driver.close();
    }
}
