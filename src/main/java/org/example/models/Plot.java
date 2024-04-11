package org.example.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Plot {
    String region;
    String district;

    String quartal;

    String street;
    String addressNo;
    String rcNo;


    public Plot(String region, String district, String quartal, String street, String addressNo, String rcNo) {
        this.region = region.toLowerCase();
        this.district = district.toLowerCase();
        this.quartal = quartal.toLowerCase();
        this.street = street.toLowerCase();
        this.addressNo = addressNo;
        this.rcNo = rcNo;
    }

    public void fillAd() {
        Utils._globalDriver.get("https://www.aruodas.lt/ideti-skelbima/?obj=11&offer_type=1");
        fillAddress();
        fillRcNo();
    }

    public void fillAddress() {
        fillRegion();
        fillDistrict();
        fillQuartal();
        fillStreet();
        fillAddressNo();
    }

    public void fillRegion() {
        Utils._globalDriver.findElement(By.xpath("//*[@id=\"newObjectForm\"]/ul/li[3]/span[1]/span")).click();
        List<WebElement> regions = Utils._globalDriver.findElement(By.id("regionDropdown")).findElements(By.tagName("li"));
        for (WebElement region : regions) {
            if (region.getText().toLowerCase().contains(this.region)) {
                region.click();
                break;
            }
        }
    }

    public void fillDistrict() {
        Utils._globalDriver.findElement(By.xpath("//*[@id=\"district\"]/span")).click();
        List<WebElement> districts = Utils._globalDriver.findElements(By.className("dropdown-input-values-address")).get(1).findElements(By.tagName("li"));
        for (WebElement district : districts) {
            if (district.getText().toLowerCase().contains(this.district)) {
                district.click();
                break;
            }
        }
    }

    public void fillQuartal() {

    }

    public void fillStreet() {

    }

    public void fillAddressNo() {

    }

    public void fillRcNo() {
        Utils._globalDriver.findElement(By.name("RCNumber")).sendKeys(this.rcNo);
    }
}
