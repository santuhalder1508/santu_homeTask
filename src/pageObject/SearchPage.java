package pageObject;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import utility.Log;
import utility.ExcelUtils;

public class SearchPage {
	private static WebElement element = null;
	
	
	/* Parse the search list of first page and store itemID, ItemTitle, Country, Rating, Price stored in excel */
	public static void searchList(WebDriver driver, String KeyWord) throws Exception{
		
		
		
		List<WebElement> elementId = driver.findElements(By.xpath("//*[@class='card card-lg']/div"));
		System.out.println(elementId.size());
		for(int i=1;i<=elementId.size();i++)
		{	
			/*Capturing attribute or text from search object*/
			
			String itemId = driver.findElement(By.xpath("//*[@class='card card-lg']["+i+"]/div")).getAttribute("data-wine");
			String ItemTitle = driver.findElement(By.xpath("//*[@class='card card-lg']["+i+"]/div/div/div/span/a/span")).getText().toLowerCase();
			String country = driver.findElement(By.xpath("//*[@class='card card-lg']["+i+"]/div/div/div/span[@class='text-block wine-card__region']/a[2]")).getText().toLowerCase();
			String region = driver.findElement(By.xpath("//*[@class='card card-lg']["+i+"]/div/div/div/span[@class='text-block wine-card__region']/a[1]")).getText().toLowerCase();
			String rating = driver.findElement(By.xpath("//*[@class='card card-lg']["+i+"]/div/div/div[@class='text-color-alt-gray wine-card__averages']/div/div")).getText().toLowerCase();
			String wineName = driver.findElement(By.xpath("//*[@class='card card-lg']["+i+"]/div/div/div/span/a/span")).getText();
			
			/*Writing value in Excel*/
			
			ExcelUtils.setCellData(itemId, i, 0, "DataStore");
			ExcelUtils.setCellData(ItemTitle, i, 1, "DataStore");
			ExcelUtils.setCellData(country, i, 2, "DataStore");
			ExcelUtils.setCellData(region, i, 3, "DataStore");
			ExcelUtils.setCellData(rating, i, 4, "DataStore");
			
			
			
			/*Identify which attribute contain the keyword and print out the attribute name along with Wine name*/
			
			attributeMatchKeyword(ItemTitle,"Title",KeyWord,wineName);
			attributeMatchKeyword(country,"Country",KeyWord,wineName);
			attributeMatchKeyword(region,"Region",KeyWord,wineName);
			

		}
	
	}

	/*Select random item select function*/
	public static WebElement randomSelectWine(WebDriver driver) {
		/*Select random number generation between 1-20*/
		int a = (int) (Math.random()*(20-1+1)+1); 
		element = driver.findElement(By.xpath("//*[@class='card card-lg']["+a+"]/div/div/div/span/a/span"));

		return element;
		
	}
	
	/*matching attribute with Keyword function*/
	public static void attributeMatchKeyword(String attributeVal, String attributeName,String KeyWord, String wineName) {
		String lowerCaseKeyword= KeyWord.toLowerCase();
		int intIndex = attributeVal.indexOf(lowerCaseKeyword);
		if (intIndex == -1)
				{	
					
					System.out.println(attributeName+" attribute did not contains the Keyword");
					Log.info(attributeName+" attribute did not contains the Keyword");
				}
		else
		{
				System.out.println("'"+wineName+"'"+" - "+attributeName+" attribute contains the Keyword");
				Log.info("'"+wineName+"'"+" - "+attributeName+" attribute contains the Keyword");
		}
	}


}
