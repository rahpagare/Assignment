import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AdminLoginTest extends BaseClass {

    String Email = "admin@phptravels.com";
    String Password = "demoadmin";

    @Test(priority = 1)
    public void userLogin(){
        driver.get("https://phptravels.com/demo/");
        driver.findElement(By.xpath("//a[contains(.,'http://www.phptravels.net/admin')]")).click();

        Set<String> allWindows = driver.getWindowHandles();
        List<String> list = new ArrayList<String>(allWindows);

        String parentWindow = list.get(0);
        String childWindow = list.get(1);

        //driver.switchTo().window(childWindow);
        driver.switchTo().window(parentWindow);
        driver.close();
        driver.switchTo().window(childWindow);

        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(Email);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(Password);
        driver.findElement(By.xpath("//span[contains(.,'Login')]")).click();

        Reporter.log("User logged in successfully in admin site");

    }


    @Test(priority = 2)
    public void verifyMenuLinks(){

        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-success']")));

        Assert.assertEquals("DASHBOARD",driver.findElement(By.xpath("//p[@class='serverHeader__title']")).getText());

        Assert.assertEquals("DASHBOARD",driver.findElement(By.xpath("//strong[contains(text(),'Dashboard')]")).getText());

        Assert.assertEquals("UPDATES",driver.findElement(By.xpath("//span[contains(.,'Updates')]")).getText());

        Reporter.log("Menu links verified under frontend site");
    }

    @Test(priority = 3)
    public void userLogout(){

        driver.findElement(By.xpath("//strong[contains(.,'Logout')]")).click();

        Reporter.log("User logged in successfully in admin site");
    }

}
