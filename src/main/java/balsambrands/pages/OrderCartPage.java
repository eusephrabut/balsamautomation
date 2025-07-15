package balsambrands.pages;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import balsambrands.base.BaseTest;

public class OrderCartPage extends BaseTest{
	
	@FindBy(xpath="//*[text()='Your Shopping Cart']")
	WebElement eleShoppingCartLabel;
	
	@FindBy(xpath="//span[text()='No Thanks']/ancestor::button")
	WebElement btnNoThanks;
	
	@FindBy(xpath="//a[contains(@class,'cart-order-entry-product-name')]")
	List<WebElement> productOrder;
	
	public OrderCartPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void validateOrderItemPrice() {
		waitForPageToLoad();
		
		waitForElementVisible(eleShoppingCartLabel, 30);
		highlightElement(eleShoppingCartLabel);
		
		WebElement eleProductOrderPrice = driver.findElement(By.xpath("//span[contains(@class,'cartProductDetailItem_new_price')]/descendant::span"));
		highlightElement(eleProductOrderPrice);
		String productOrderPrice = eleProductOrderPrice.getText();
		
		assertEquals(productOrderPrice, ProductOrderDetailPage.productDetailsUpdatedPrice);
	}
	
	public void removeOrder() {
		waitForPageToLoad();
		
		WebElement eleTotalPrice = driver.findElement(By.xpath("//span[contains(@class,'totalAmountCard_total-value')]"));
		highlightElement(eleTotalPrice);
		Actions actions = new Actions(driver);
		actions.moveToElement(eleTotalPrice).perform();

		WebElement btnRemoveOrder = driver.findElement(By.xpath("//span[contains(@class,'cartProductDetailItem_delete-icon')]/ancestor::button[contains(@class,'btn-link')]"));
		highlightElement(btnRemoveOrder);
		clickElement(btnRemoveOrder);
		
		waitForPageToLoad();
	}

}
