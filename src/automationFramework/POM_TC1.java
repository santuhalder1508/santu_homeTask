package automationFramework;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// Import package pageObject.*

import pageObject.HomePage;
import pageObject.SearchPage;
import utility.ExcelUtils;
import utility.Log;
import pageObject.ItemDetail;

public class POM_TC1 {
	private static WebDriver driver = null;
		public static String KeyWord = null;
	   public static void main(String[] args) throws Exception {
		   

			DOMConfigurator.configure("log4j.xml");
		   String path = System.getProperty("user.dir");
		   ExcelUtils.setExcelFile(path+"\\DataEngine.xlsx");
		   System.setProperty("webdriver.chrome.driver", path+"\\chromedriver.exe");
		    driver = new ChromeDriver();
		    driver.manage().window().maximize();
		     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		     driver.get("https://www.vivino.com");
		     
		     
		     //Search Text
		     KeyWord = ExcelUtils.getCellData(1, 1, "Test Cases");
		     HomePage.txt_SearchBox(driver).sendKeys(KeyWord);
		     HomePage.SearchText(driver);
		     		     
		     //Parse the search list of first page and store in excel
		     SearchPage.searchList(driver,KeyWord);
		     
		     //Random click on wine title
		     SearchPage.randomSelectWine(driver).click();
		     	
		     //get the random clicked wine details and matched with store values
		     
		     ItemDetail.validateItemInfo(driver,KeyWord);
		     
		     //Close the driver
		     driver.quit();
	   }

}
