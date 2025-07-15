package balsambrands.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import balsambrands.util.BrowserManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest extends BrowserManager{
	
	public static WebDriver driver;
	public static Properties prop;
	
	//public static EventFiringWebDriver e_driver;
	//public static WebEventListener eventListener;
	
	
	public BaseTest(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/balsambrands"
					+ "/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initialization() {
		String browserName = prop.getProperty("browser").toUpperCase();
		
		if(browserName.equals("CHROME")) {
			WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = getChromeOptions();
            driver = new ChromeDriver(chromeOptions);
		} else if(browserName.equals("FIREFOX")) {
			WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = getFirefoxOptions();
            driver = new FirefoxDriver(firefoxOptions);
		} else if(browserName.equals("EDGE")) {
			WebDriverManager.edgedriver().setup();
            EdgeOptions edgeOptions = getEdgeOptions();
            driver = new EdgeDriver(edgeOptions);
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get(prop.getProperty("url"));
	}

    
	public void waitForPageToLoad() {
	    ExpectedCondition<Boolean> pageLoadCondition = driver -> 
	        ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");

	    try {
	        WebDriverWait pageWait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        pageWait.until(pageLoadCondition);
	    } catch (TimeoutException e) {
	        System.out.println("Page did not load completely within timeout.");
	    }
	}
	
	public void highlightElement(WebElement element) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    String originalStyle = element.getAttribute("style");

	    // Apply highlight style
	    js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", 
	                     element, "style", "border: 2px solid red;");

	    // keep highlight for a moment then revert
	    try {
	        Thread.sleep(200); // Sleep for visibility (adjust if needed)
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }

	    // Revert to original style
	    js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", 
	                     element, "style", originalStyle);
	}
	
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public static WebElement waitForElementVisible(WebElement element, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

	public static WebElement waitForElementToBeClickable(WebElement element, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		waitForElementVisible(element, timeoutSeconds);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
				
	}
	
    // Reusable method to enter text into an input field
    public void enterText(By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.clear(); // clear any existing text
        element.sendKeys(text);
    }
    
    // Optional: overloaded version accepting WebElement directly
    public void enterText(WebElement element, String text) {
    	highlightElement(element);
        element.clear();
        element.sendKeys(text);
    }
    
    public void clickElement(By locator) {
        driver.findElement(locator).click();
    }
    
    public void clickElement(WebElement element) {
    	try {
    		waitForElementToBeClickable(element, 30);
            element.click();
    	} catch (ElementClickInterceptedException e) {
    		e.printStackTrace();
    	}
    	
    }

    public String getElementText(By locator) {
        return driver.findElement(locator).getText();
    }
    
    public String getElementText(WebElement element) {
        return element.getText();
    }

    public boolean isElementDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }
    
    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }
    
}
