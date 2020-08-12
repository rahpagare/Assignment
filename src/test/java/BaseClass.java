import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    RemoteWebDriver driver;

    @BeforeClass
    @Parameters("browserName")
    public void Setup(String browserName) throws MalformedURLException {

        if (browserName.equalsIgnoreCase("chrome")) {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            capabilities.setPlatform(Platform.ANY);

            ChromeOptions options = new ChromeOptions();
            options.merge(capabilities);
            URL url = new URL("http://localhost:4444/wd/hub/");

            driver = new RemoteWebDriver(url, options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        }

        else if (browserName.equalsIgnoreCase("firefox")){
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("firefox");
            capabilities.setPlatform(Platform.ANY);

            URL url = new URL("http://localhost:4444/wd/hub/");

            driver = new RemoteWebDriver(url, capabilities);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
