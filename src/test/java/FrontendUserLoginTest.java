import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
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
        WebDriverWait wait = new WebDriverWait(driver,20);

        driver.get("https://phptravels.com/demo/");
        driver.findElement(By.xpath("//a[@href='//www.phptravels.net']")).click();

        Set<String> allWindows = driver.getWindowHandles();
        List<String> list = new ArrayList<String>(allWindows);

        String parentWindow = list.get(0);
        String childWindow = list.get(1);

        driver.switchTo().window(parentWindow);
        driver.close();
        driver.switchTo().window(childWindow);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='javascript:void(0);'][contains(.,'My Account')]")));

        driver.findElement(By.xpath("//a[@href='javascript:void(0);'][contains(.,'My Account')]")).isDisplayed();
        driver.findElement(By.xpath("//a[@href='javascript:void(0);'][contains(.,'My Account')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://www.phptravels.net/login']")));

        driver.findElement(By.xpath("//a[@href='https://www.phptravels.net/login']")).click();

        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(Email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Password);
        driver.findElement(By.xpath("//button[@type='submit'][contains(.,'Login')]")).click();

        Reporter.log("User logged in successfully in frontend site");
    }

    @Test(priority = 2)
    public void verifyMenuLinks(){
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='nav-link go-text-right active']")));

        Assert.assertEquals("BOOKINGS",driver.findElement(By.xpath("//a[@class='nav-link go-text-right active']")).getText());
        Assert.assertEquals("MY PROFILE",driver.findElement(By.xpath("//a[contains(text(),'My Profile')]")).getText());

        Reporter.log("Menu links verified under frontend site.");
    }

    @Test(priority = 3)
    public void userLogout(){
        driver.findElement(By.xpath("//a[contains(.,'Demo')]")).click();
        driver.findElement(By.xpath("//a[@href='javascript:void(0);'][contains(.,'Demo')]")).click();

        Reporter.log("User logged out successfully from frontend site.");
    }

}
