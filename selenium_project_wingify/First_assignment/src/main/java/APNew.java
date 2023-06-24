import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
//import org.openqa.selenium.chrome.ChromeOptions;

public class APNew {

    public static WebDriver driver = null;

    public static  boolean verify_loginPage(){
        boolean flag = true;
        WebElement loginTest_path = driver.findElement(By.xpath("//h4[@class='auth-header']"));
        String loginTest = loginTest_path.getText();
//        System.out.println("Login Form Name"+loginTest);
        String expectedText = "Login Form";
        Assert.assertEquals(loginTest,expectedText);
        if(loginTest.equalsIgnoreCase("Login Form")){
            flag = true;
            System.out.println("Getting the Log in page");
        }else{
            flag = false;
            System.out.println("Did not getting Log in page");
        }
        return flag;
    }
//    Verify after log in
    public static boolean verify_login(){
        boolean flag = true;
        WebElement login_title = driver.findElement(By.xpath("//div[@class='logo-label']"));
        String actual_logintitle = login_title.getText();

        if(actual_logintitle.equalsIgnoreCase("DEMO")){
            System.out.println("login Successful");
            flag=true;
        }else{
            System.out.println("login unsuccessful");
            flag= false;
        }
        return flag;
    }
    public static boolean verify_sort(){
        boolean flag = true;
//        getting the list of the values in amount header
        List<WebElement> headerList = driver.findElements(By.xpath("//td[@class='text-right bolder nowrap']//span"));
//        System.out.println("size of list"+ headerList.size());
        for(int i=1; i<headerList.size();i++){
            double first_finalValue = 0;
            String first_value = driver.findElement(By.xpath("(//td[@class='text-right bolder nowrap'])["+i+"]//span")).getText();
            first_value = first_value.replaceAll(" ","");
            first_value = first_value.replaceAll("USD","");
            first_value = first_value.replaceAll(",","");
//            System.out.println("first Value"+first_value);
            first_finalValue = Double.parseDouble(first_value);

            String nextValue = driver.findElement(By.xpath("(//td[@class='text-right bolder nowrap'])["+(i+1)+"]//span")).getText();
            nextValue = nextValue.replaceAll(" ","");
            nextValue = nextValue.replaceAll("USD","");
            nextValue = nextValue.replaceAll(",","");
            double final_nextValue = Double.parseDouble(nextValue);
//            System.out.println("next Value"+ nextValue);
            if(final_nextValue<first_finalValue){
                System.out.println("Values are not sorted");
                return flag = false;
            }else{
                first_finalValue = final_nextValue;
            }
        }
        System.out.println("Values are sorted");

        return flag=true;
    }
//    verify amount header is there or not
    public static boolean amountHeader(){
        boolean flag = true;
        String aHeader = driver.findElement(By.xpath("//th[@id='amount']")).getText();
        if(aHeader.equalsIgnoreCase("Amount")){
            System.out.println("Amount header is there");
            flag=true;
        }else{
            System.out.println("Amount header is not there");
            flag=false;
        }
        return flag;
    }

    public static void main(String[] args) {
        boolean flag = true;
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
//        for maximize the window
        driver.manage().window().maximize();
//        for getting the url
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://sakshingp.github.io/assignment/login.html");
//        Validate the correct browser is open
       boolean r1 =  verify_loginPage();
//        Login with username and password
//        Set user name as: - Ankita priyadarshini
        WebElement user_name = driver.findElement(By.xpath("//input[@id='username']"));
        user_name.sendKeys("Ankita priyadarshini");
//        Set password as: - Ankita@123
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        password.sendKeys("Ankita@123");
//        Click on log-in button
        WebElement login_button = driver.findElement(By.xpath("//button[@id='log-in']"));
        login_button.click();
//      Validate if user is successfully log in or not
        boolean r2 = verify_login();
//        verify mamount header is there or not
        amountHeader();
//        Click on Amount Header
        WebElement amount_header = driver.findElement(By.xpath("//th[@id='amount']"));
        amount_header.click();
//        Validate the values are sorted or not
        boolean r3 = verify_sort();
        driver.close();

        if(flag == true){
            System.out.println("PASS");
        }else{
            System.out.println("Fail");
        }


    }
}
