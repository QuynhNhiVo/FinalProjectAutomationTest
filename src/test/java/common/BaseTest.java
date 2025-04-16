package common;

import constants.ConfigData;
import drivers.DriverManager;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browser){
        WebDriver driver = setupBrowser(browser);
        DriverManager.setDriver(driver);
    }

    @AfterMethod
    public void closeDriver(){
        DriverManager.quit();
    }

    public WebDriver setupBrowser(String browser){
        WebDriver driver;
        switch (browser.trim().toLowerCase()){
            case "chrome":
                driver = initChromeBrowser();
                break;
            case "edge":
                driver = initEdgeBrowser();
                break;
            case "firefox":
                driver = initFirefoxBrowser();
                break;
            default:
                LogUtils.info("Launching default browser (Chrome).......");
                driver = initChromeBrowser();
        }
        return driver;
    }

    private WebDriver initChromeBrowser(){
        WebDriver driver;
        ChromeOptions options = new ChromeOptions();
        // Block notifications
        options.addArguments("--incognito");
        if (ConfigData.HEADLESS.equals("true")){
            LogUtils.info("Launching Chrome (Headless).......");
            options.addArguments("--headless");
        }else {
            LogUtils.info("Launching Chrome.......");
        }
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initEdgeBrowser(){
        WebDriver driver;
        EdgeOptions options = new EdgeOptions();
        if (ConfigData.HEADLESS.equals("true")){
            LogUtils.info("Launching Edge (Headless).......");
            options.addArguments("--headless");
        }else {
            LogUtils.info("Launching Edge.......");
        }
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initFirefoxBrowser(){
        WebDriver driver;
        FirefoxOptions options = new FirefoxOptions();
        if (ConfigData.HEADLESS.equals("true")){
            LogUtils.info("Launching Firefox (Headless).......");
            options.addArguments("--headless");
        }else {
            LogUtils.info("Launching Firefox.......");
        }
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        return driver;
    }
}
