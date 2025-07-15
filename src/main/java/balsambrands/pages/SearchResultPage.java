package balsambrands.pages;

import java.util.List;

import org.apache.logging.log4j.core.util.SystemNanoClock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import balsambrands.base.BaseTest;

public class SearchResultPage extends BaseTest{

	@FindBy(xpath="//*[@data-testid='search-result-header']")
	WebElement searchResultHeader;
	
	public static String productName;
	public static String productPrice;
	
	public SearchResultPage() {
		PageFactory.initElements(driver, this);
	}
	
	/*As per requirement : Select the third result that appears on the results page.
	 * This method will automatically click the 3rd result from the page
	 * 
	 */
	public void clickSearchResult() {
		waitForPageToLoad();
		waitForElementVisible(searchResultHeader, 30);
		highlightElement(searchResultHeader);
		
		WebElement eleProductName = driver.findElement(By.xpath("(//*[contains(@class,'productCard_detail')]/descendant::a[contains(@data-testid,'lbl')])[3]"));
		WebElement eleProductPrice = driver.findElement(By.xpath("(//*[contains(@class,'productCard_detail')]/descendant::span[contains(@class,'sale-price')]/descendant::span)[3]"));
		
		productName = eleProductName.getText();
		productPrice = eleProductPrice.getText();
		
		System.out.println("Product Name  : " + productName);
		System.out.println("Product Price : " + productPrice);

		clickElement(eleProductName);
	}
}
