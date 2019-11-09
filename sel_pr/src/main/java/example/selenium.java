package example;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class selenium {
	public static final String connect = "D:\\JavaProject\\selenium\\bin\\phantomjs.exe";
	public static void main(String[] args) {
		try {
			String start = "https://hh.ru";
			String current = null;
			DesiredCapabilities caps = new DesiredCapabilities();
			ArrayList<String> cliArgsCap = new ArrayList<String>();
			cliArgsCap.add("--web-security=no");
			cliArgsCap.add("--ssl-protocol=any");
			cliArgsCap.add("--ignore-ssl-errors=yes");
			caps.setJavascriptEnabled(true);
			caps.setCapability("takeScreenshot", true);
			caps.setCapability(
				    PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
			//caps.setJavascriptEnabled(true);
			//caps.setCapability("takesScreenshot", true); 
			caps.setCapability("phantomjs.page.settings.userAgent", "Safari/537.36");
			caps.setCapability(
                    PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                    connect
                );
			
			WebDriver driver = new PhantomJSDriver(caps);

			driver.get(start);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			System.out.println("Page title is: " + driver.getTitle());
			
			
	        if(driver.findElement(By.className("supernova-dashboard")).isEnabled()){
	        	
	        	
	        	WebElement element = driver.findElement(By.name("text"));
	        	Actions builder = new Actions(driver);
	        	builder.contextClick(element).build().perform();
	        	String previousURL = driver.getCurrentUrl();
	        	element.sendKeys("Программист");
		        element.submit();
		        System.out.println("Page title is: " + driver.getTitle());
	        	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	        	ExpectedCondition<Boolean> e = new ExpectedCondition<Boolean>() {
	                public Boolean apply(WebDriver d) {
	                	return (!d.getCurrentUrl().equals(previousURL));
	                }
	              };

	          wait.until(e);
	          current = driver.getCurrentUrl();
	          driver.get(current);
	        }
	        //HH-Pager-Controls-Next
			List<WebElement> elements = driver.findElements(By.xpath("*//div[@class='vacancy-serp']/div/div/div/div[@class='vacancy-serp-item__compensation']"));
	        List<String> zp= new ArrayList<String>();
	        int page = 1;
	        do {
	        	elements.forEach(x -> zp.add((x.getAttribute("innerHTML").replaceAll("[^0-9-]", ""))));
	        	driver.get(current+"&page="+page);
	        	elements = driver.findElements(By.xpath("*//div[@class='vacancy-serp']/div/div/div/div[@class='vacancy-serp-item__compensation']"));
	        	System.out.println("page: " + page);
	        	page++;
	        }while (/*driver.findElement(By.className("HH-Pager-Controls-Next")).isDisplayed()*/page<4);
	        zp.forEach(x -> System.out.println(x));
	        List<Integer> zp_list = new ArrayList<Integer>();
	        zp.forEach(x -> {
	        	if(x.contains("-")) {
	        		 String[] s = x.split("-");
	        		 zp_list.add((Integer.parseInt(s[0])+Integer.parseInt(s[1]))/2);
	        	} else zp_list.add(Integer.parseInt(x));
	        		
	        	
	        });
	        zp_list.sort(null);
	        zp_list.forEach(x -> System.out.println("Zp - "+x));
	        if(zp_list.size()%2==0) {
	        	int middle = zp_list.size()/2;
	        	int median = zp_list.get(middle-1)+zp_list.get(middle);
	        	System.out.println(median/2);
	        }

		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}
}
