package pageObject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.*;


public class ItemDetail {
	
		
	@SuppressWarnings("deprecation")
	public static void validateItemInfo(WebDriver driver, String KeyWord) throws Exception {
	//Wait for page to be loaded
	
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid='addToCart']")));
		
	//get the unique itemId from url	
	String url = driver.getCurrentUrl();
	String[] arrOfStr = url.split("w/");
	String selectItemId=arrOfStr[1];
	
	//get the item informations
	String selectItemTitle = (driver.findElement(By.xpath("//*[@class='row header breadCrumbs']")).getText()).replace("\n"," ").toLowerCase();
	String selectCountry = driver.findElement(By.xpath("//*[@data-cy='breadcrumb-country']")).getText().toLowerCase();
	String selectRegion = driver.findElement(By.xpath("//*[@data-cy='breadcrumb-region']")).getText().toLowerCase();
	String selectWineName = (driver.findElement(By.xpath("//*[@class='row header breadCrumbs']")).getText()).replace("\n"," ");
	
	//Identify the row number of stored value
	int desiredRow = ExcelUtils.getRowContains(selectItemId, 0, "DataStore");
	
	//Verify the current attribute value matched with stored attribute value.
	verifyValues(selectItemTitle,"Title",desiredRow,1);
	verifyValues(selectCountry,"Country",desiredRow,2);
	verifyValues(selectRegion,"Region",desiredRow,3);
	
	//verify which attribute contains the KeyWord
	SearchPage.attributeMatchKeyword(selectItemTitle, "Title", KeyWord,selectWineName);
	SearchPage.attributeMatchKeyword(selectCountry, "Country", KeyWord,selectWineName);
	SearchPage.attributeMatchKeyword(selectRegion, "Region", KeyWord,selectWineName);
	
	
	}
	
	//This function Verify the current item values with stored values
	public static void verifyValues(String value, String attributeName, int row, int col) throws Exception {
		if ((ExcelUtils.getCellData(row, col, "DataStore")).equalsIgnoreCase(value))
		{
			System.out.println("Random Items "+attributeName+" matched with stored details");
			Log.info("Random Items "+attributeName+" matched with stored details");
		}
		else
		{
			System.out.println("Random Items "+attributeName+ " did not matched with stored details");
			Log.info("Random Items "+attributeName+ " did not matched with stored details");
		}
	}
	

}
