package Demo;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DetailsOfPushpaMovieFromTwoWebsites
{
	 String releaseDateFromIMDB;
	 String countryFromIMDB;
	 String releaseDateFromWiki;
	 String countryFromWiki;
	
	 /*
	 * This method is used to fetch Pushpa movie release date and country details using IMDB website
	 */
	@Test
	public void getPushpaMovieDetailsUsingIMDB()
	{
		WebDriverManager.chromedriver().setup();
	    WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		String movieName= "pushpa:The rise";
		
		//open IMDB website
		driver.get("https://www.imdb.com/");
		driver.findElement(By.xpath("//input[@placeholder='Search IMDb']")).sendKeys(movieName);
		driver.findElement(By.xpath("//button[@id='suggestion-search-button']")).click();
		
		//click on pushpa 1 link
		driver.findElement(By.xpath("//a[text()='Pushpa: The Rise - Part 1']")).click();
		JavascriptExecutor js =(JavascriptExecutor)driver;
	    js.executeScript("window.scrollBy(0,6000)");
	    
	    //fetching release date details
	    releaseDateFromIMDB=driver.findElement(By.xpath("//div[@data-testid='title-details-section']/descendant::li[@data-testid='title-details-releasedate']")).getText();
		System.out.println(releaseDateFromIMDB);
		
		//fetching country details
		countryFromIMDB=driver.findElement(By.xpath("//li[@data-testid='title-details-origin']/descendant::a")).getText();
	    System.out.println(countryFromIMDB);
	    
	    //close the browser
	    driver.quit();
	}
	
	/*
	 * This method is used to fetch Pushpa movie release date and country details using wikipedia website
	 */
	@Test
	public void getPushpaMovieDetailsUsingWiki()
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//open wiki 
		driver.get("https://www.wikipedia.org/");
		driver.findElement(By.xpath("//input[@id='searchInput']")).sendKeys("pushpa");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//driver.findElement(By.xpath("//a[@title='Pushpa: The Rise']")).click();
		
		JavascriptExecutor js =(JavascriptExecutor)driver;
	    js.executeScript("window.scrollBy(0,700)");
	    
	    //fetching release date details
	    releaseDateFromWiki=driver.findElement(By.xpath("//table[@class='infobox vevent']/tbody/tr[12]")).getText();
		System.out.println(releaseDateFromWiki);
		
		//fetching country details
		countryFromWiki=driver.findElement(By.xpath("//table[@class='infobox vevent']/tbody/tr/th[text()='Country']/following-sibling::td")).getText();
	    System.out.println(countryFromWiki);
	    
	    //close the browser
	    driver.quit();
	    
	  }
	
	/*
	 * This method to validate country of pushpa movie from Both the website
	 */
	@Test
	public void validateCountry()
	{

		//verify released country of Pushpa movie from IMDB and Wiki
		Assert.assertEquals(countryFromWiki, countryFromIMDB);
	}
	
	
	/*
	 * This method to validate release date of pushpa movie from Both the website
	 */
	@Test
	public void validateReleaseDate()
	{
		//verify release date of Pushpa movie from IMDB and Wiki
		if(releaseDateFromIMDB.contains(releaseDateFromWiki))
		{
			System.out.println("movie released date is same for pushpa in both website");
		}
		
    }
	
	

}
