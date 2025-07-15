package balsambrands.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import balsambrands.base.BaseTest;


public class HomePage extends BaseTest{
	
	@FindBy(id="constructor-search-input")
	public WebElement txtboxSearch;
	
	@FindBy(id="cookieBanner")
	public WebElement cookieBanner;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifySearchBox(){
		return txtboxSearch.isDisplayed();
	}
	
	public void closeCookieNotificationBanner() {
		if(isElementDisplayed(cookieBanner)) {
			WebElement btnCloseBanner = cookieBanner.findElement(By.id("bannerCloseButton"));
			clickElement(btnCloseBanner);
		}
	}
	
	public void searchProduct(String product) {
		waitForPageToLoad();
		closeCookieNotificationBanner();
		enterText(txtboxSearch, product + Keys.ENTER);
	}
	
	
}
