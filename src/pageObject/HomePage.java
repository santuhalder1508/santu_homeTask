package pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class HomePage {



private static WebElement element = null;

public static WebElement txt_SearchBox(WebDriver driver){

element = driver.findElement(By.name("q"));

return element;

}

public static void SearchText(WebDriver driver){

	try {
		   Actions act = new Actions(driver);
		   
		   act.sendKeys(Keys.ENTER).build().perform();
		}
	    catch(Exception e) {
	    	
	    }

}

}