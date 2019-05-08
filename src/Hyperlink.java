import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
public class Hyperlink {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		 System.setProperty("webdriver.gecko.driver", "C:\\Users\\smita.mane\\Desktop\\Workspace\\Geckodriver\\geckodriver.exe");
		 WebDriver driver = new FirefoxDriver();
		 //Maximize the browser
		 driver.manage().window().maximize();
		 //Implicit wait for 10 seconds
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 //To launch UAT url.com
		 driver.get("https://uat.enwisen.com/asi/login.aspx");
		//Login to required site
		 driver.findElement(By.id("txtUserId")).sendKeys("enwsmane");	       
		 driver.findElement(By.id("txtPassword")).sendKeys("Feb_1234");
		 driver.findElement(By.id("txtOrganization")).sendKeys("asbeta");
		 driver.findElement(By.id("btnLogin")).click();
		 	 
		 Thread.sleep(5000);
		 
		 //Click on Home icon
		 //driver.findElement(By.xpath("//span[contains(@id,'hcm-button')][contains(@class,'x-btn-button x-btn-button-default-toolbar-small  x-btn-no-text x-btn-icon x-btn-icon-left x-btn-button-center ')]")).click();
		 driver.findElement(By.xpath("//span[@id='hcm-button-1138-btnEl']")).click();
		 //span[@id='hcm-button-1138-btnEl']
		 Thread.sleep(2000);
		 //Click on Site map
		 driver.findElement(By.xpath("//span[contains(@class,'x-tree-node-text')][contains(text(),'Site Map')]")).click();
		 Thread.sleep(5000);
		//Used tagName method to collect the list of items with tagName "a"
		 //findElements - to find all the elements with in the current page. It returns a list of all webelements or an empty list if nothing matches
		 List<WebElement> links = driver.findElements(By.tagName("a")); 
		 //To print the total number of links
		 System.out.println("Total links are "+links.size()); 
		 //used for loop to 
		 for(int i=0; i<links.size(); i++) {
		 WebElement element = links.get(i);
		 //By using "href" attribute, we could get the url of the requried link
		 String url=element.getAttribute("href");
		 //calling verifyLink() method here. Passing the parameter as url which we collected in the above link
		 //See the detailed functionality of the verifyLink(url) method below
		 verifyLink(url); 
		 } 
		 }
		 
		 // The below function verifyLink(String urlLink) verifies any broken links and return the server status. 
		 public static void verifyLink(String urlLink) {
			 
		        //Sometimes we may face exception "java.net.MalformedURLException". Keep the code in try catch block to continue the broken link analysis
		        try {
		 //Use URL Class - Create object of the URL Class and pass the urlLink as parameter 
		 URL link = new URL(urlLink);
		 // Create a connection using URL object (i.e., link)
		 HttpURLConnection httpConn =(HttpURLConnection)link.openConnection();
		 //Set the timeout for 2 seconds
		 httpConn.setConnectTimeout(2000);
		 //connect using connect method
		 httpConn.connect();
		 //use getResponseCode() to get the response code. 
		 if(httpConn.getResponseCode()== 200) { 
		 System.out.println(urlLink+" - "+"PASS "+httpConn.getResponseMessage());
		 }
		 else if(httpConn.getResponseCode()!= 200) {
		 System.out.println(urlLink+" - "+"FAIL "+httpConn.getResponseMessage());
		 }
		}
		 //getResponseCode method returns = IOException - if an error occurred connecting to the server. 
		 catch (Exception e) {
		 //e.printStackTrace();
		 }
		        
		    } 
		 
}
