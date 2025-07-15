package balsambrands.pages;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import balsambrands.base.BaseTest;


public class ProductOrderDetailPage extends BaseTest{
	
	@FindBy(xpath="//*[contains(@class,'product-name-div')]/descendant::h1")
	WebElement eleProductName;
	
	@FindBy(xpath="//*[contains(@class,'productPrice_new-price')]/descendant::span")
	WebElement lblProductPrice;
	
	@FindBy(xpath="//*[contains(@class,'btn-conatiner')]/descendant::button[text()='Add to Cart']")
	WebElement btnAddToCart;
	
	@FindBy(xpath="//button[text()='View Cart']")
	WebElement btnViewCart;
	
	public static String productName;
	public static String productDetailsDefaultPrice;
	public static String productDetailsUpdatedPrice;
	
	public ProductOrderDetailPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void validateDefaultProductPrice() {
		SearchResultPage searchResultPage = new SearchResultPage();
		
		waitForPageToLoad();
		
		waitForElementVisible(eleProductName, 30);
		productName = eleProductName.getText();
		
		waitForElementVisible(lblProductPrice, 30);
		productDetailsDefaultPrice = lblProductPrice.getText();		
		
		assertEquals(searchResultPage.productPrice, productDetailsDefaultPrice);
	}
	
	public void customizeProductHeight(String height) {
		List<WebElement> eleProductHeight = driver.findElements(By.xpath("//*[@id='product_filter_height']/ancestor::div[@class='accordion-item']/descendant::div[contains(@class,'product-filter-box')]/descendant::span[contains(@class,'renderSelectBoxFilterItem_select-box')]"));
		for(int i=0; i<eleProductHeight.size();i++) {
			String currentProductHeight = eleProductHeight.get(i).getText();
			System.out.println(currentProductHeight);
			try {
				if(currentProductHeight.equalsIgnoreCase(height)) {
					waitForElementToBeClickable(eleProductHeight.get(i), 30);
					clickElement(eleProductHeight.get(i));
					System.out.println("Product Height Selected : " + currentProductHeight);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void customizeProductShape(String height) {
		List<WebElement> eleProductShapes = driver.findElements(By.xpath("//*[@id='product_filter_shape']/ancestor::div[@class='accordion-item']/descendant::div[contains(@class,'product-filter-box')]/descendant::span[contains(@class,'renderSelectBoxFilterItem_select-box')]"));
		for(int i=0; i<eleProductShapes.size();i++) {
			String currentProductShape = eleProductShapes.get(i).getText();
			System.out.println(currentProductShape);
			try {
				if(currentProductShape.equalsIgnoreCase(height)) {
					waitForElementToBeClickable(eleProductShapes.get(i), 30);
					clickElement(eleProductShapes.get(i));
					System.out.println("Product Shape Selected : " + currentProductShape);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void customizeProductLighting(String lights) {
		List<WebElement> eleProductLights = driver.findElements(By.xpath("//*[@id='product_filter_lights']/ancestor::div[@class='accordion-item']/descendant::div[contains(@class,'product-filter-box')]/descendant::span[contains(@class,'renderSelectBoxFilterItem_select-box')]"));
		for(int i=0; i<eleProductLights.size();i++) {
			String currentProductLight = eleProductLights.get(i).getText();
			System.out.println(currentProductLight);
			try {
				if(currentProductLight.equalsIgnoreCase(lights)) {
					waitForElementToBeClickable(eleProductLights.get(i), 30);
					clickElement(eleProductLights.get(i));
					System.out.println("Product Light Selected : " + eleProductLights);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void addOrderToCart() {
		productDetailsUpdatedPrice = lblProductPrice.getText();	
		waitForElementVisible(btnAddToCart, 30);
		clickElement(btnAddToCart);
		waitForPageToLoad();
	}
	
	public void viewCart() {
		waitForElementVisible(btnViewCart, 30);
		clickElement(btnViewCart);
		waitForPageToLoad();
	}
	
}
