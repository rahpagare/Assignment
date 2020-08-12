import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FrontendUserLoginTest extends BaseClass {

    String Email = "user@phptravels.com";
    String Password = "demouser";

    @Test(priority = 1)
    public void userLogin() {
        driver.get("https://phptravels.com/demo/");
        driver.findElement(By.xpath("//a[@href='//www.phptravels.net']")).click();

        Set<String> allWindows = driver.getWindowHandles();
        List<String> list = new ArrayList<String>(allWindows);

        String parentWindow = list.get(0);
        String childWindow = list.get(1);

        driver.switchTo().window(parentWindow);
        driver.close();
        driver.switchTo().window(childWindow);
        driver.findElement(By.xpath("//a[@href='javascript:void(0);'][contains(.,'My Account')]")).isDisplayed();
        driver.findElement(By.xpath("//a[@href='javascript:void(0);'][contains(.,'My Account')]")).click();
        driver.findElement(By.xpath("//a[@href='https://www.phptravels.net/login']")).click();
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(Email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Password);
        driver.findElement(By.xpath("//button[@type='submit'][contains(.,'Login')]")).click();
    }

    @Test(priority = 2)
    public void verifyMenuLinks(){
        Assert.assertEquals("HOME",driver.findElement(By.xpath("//a[@title='home']")).getText());
        Assert.assertEquals("COMPANY",driver.findElement(By.xpath("(//a[contains(.,'company')])[1]")).getText());
    }

    @Test(priority = 3)
    public void userLogout(){
        driver.findElement(By.xpath("//a[contains(.,'Demo')]")).click();
        driver.findElement(By.xpath("//a[@href='javascript:void(0);'][contains(.,'Demo')]")).click();
    }

}
