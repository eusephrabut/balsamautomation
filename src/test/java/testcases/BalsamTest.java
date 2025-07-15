package testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import balsambrands.base.BaseTest;
import balsambrands.pages.HomePage;
import balsambrands.pages.OrderCartPage;
import balsambrands.pages.ProductOrderDetailPage;
import balsambrands.pages.SearchResultPage;

public class BalsamTest extends BaseTest{

	HomePage homePage;
	SearchResultPage searchResultPage;
	ProductOrderDetailPage productOrderDetails;
	OrderCartPage orderCartPage;
	
	public BalsamTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage();	
		searchResultPage = new SearchResultPage();
		productOrderDetails = new ProductOrderDetailPage();
		orderCartPage = new OrderCartPage();
	}
	
	@Test(priority=1)
	public void balsam_test_scenario() {		
		homePage.searchProduct("Christmas Tree");
		searchResultPage.clickSearchResult();
		
		productOrderDetails.validateDefaultProductPrice();
		productOrderDetails.addOrderToCart();
		productOrderDetails.viewCart();
		
		orderCartPage.validateOrderItemPrice();
		orderCartPage.removeOrder();
		
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
