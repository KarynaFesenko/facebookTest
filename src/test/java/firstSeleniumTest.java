import org.bouncycastle.util.Strings;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class firstSeleniumTest {

    public static final String homePage = "https://www.facebook.com/";
    private static WebDriver driver;

    @BeforeAll
    public static void classSetup() {
        driver = SharedDrive.getWebDriver();
        driver.get(homePage);
    }

    @AfterAll
    public static void classTearDown() {
        SharedDrive.closeDriver();
    }

    @AfterEach
    public void testTearDown() {
        driver.get(homePage);
    }

    @Test
    public void homePageUrlTest() {

        String actualUrl = driver.getCurrentUrl();
        assertEquals(actualUrl, homePage, "urls are not matching");

    }

    @Test
    public void findEmailFieldTest() {
//    WebElement element = driver.findElement(By.id("email"));
//    WebElement element = driver.findElement(By.name("email"));
//    WebElement element = driver.findElement(By.linkText("Create a Page"));
//    WebElement element = driver.findElement(By.partialLinkText("a Page"));
//    WebElement element = driver.findElement(By.cssSelector("#email"));
        List<WebElement> element = driver.findElements(By.className("inputtext"));
        System.out.println(element.size());
        assertNotNull(element);
    }

    @Test
    public void findElementsByXpathTest() {
        WebElement emailElement = driver.findElement(By.xpath("//input [@name = 'email']"));
        assertNotNull(emailElement);

        WebElement passwordElement = driver.findElement(By.xpath("//input [@data-testid = 'royal_pass']"));
        assertNotNull(passwordElement);

        WebElement loginButtonElement = driver.findElement(By.xpath("//button[@type = 'submit']"));
        assertNotNull(loginButtonElement);

//        WebElement forgotPasswordElement = driver.findElement(By.xpath("//a[text()='Forgot password?']"));
//        assertNotNull(forgotPasswordElement);

//        WebElement createNewAccElement = driver.findElement(By.xpath("//a[text()='Create new account']"));
//        assertNotNull(createNewAccElement);
    }

    @Test
    public void loginScreenTest() {
        WebElement emailElement = driver.findElement(By.xpath("//input [@name = 'email']"));
        assertNotNull(emailElement);
        emailElement.sendKeys("karinafesenko@gmail.com");
        String emailValue = emailElement.getAttribute("value");
        assertEquals("karinafesenko@gmail.com", emailValue);

//    WebElement passwordElement = driver.findElement(By.xpath("//input [@data-testid = 'royal_pass']"));
//    assertNotNull(passwordElement);
//    passwordElement.sendKeys("1234567");
//    String passwordValue = passwordElement.getAttribute("value");
//    assertEquals("1234567","value");

        WebElement loginButtonElement = driver.findElement(By.xpath("//button[@type = 'submit']"));
        assertNotNull(loginButtonElement);
        loginButtonElement.click();
    }

    @Test
    public void accountCreatingPage() throws InterruptedException {
        WebElement createNewAccElement = driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']"));
        assertNotNull(createNewAccElement);
        createNewAccElement.click();
        Thread.sleep(1000);

        WebElement MobNumberOrEmailElement = driver.findElement(By.xpath("//input [@name ='reg_email__']"));
        assertNotNull(MobNumberOrEmailElement);
        MobNumberOrEmailElement.click();
        MobNumberOrEmailElement.sendKeys("fesenkokarina17@gmail.com");

        WebElement NewPasswordElement = driver.findElement(By.xpath("//input [@autocomplete = 'new-password']"));
        assertNotNull(NewPasswordElement);
        NewPasswordElement.click();
        NewPasswordElement.sendKeys("qwerty123");

        WebElement birthdayElement = driver.findElement(By.xpath("//div [@class = 'mtm mbs _2_68']"));
        assertNotNull(birthdayElement);


        WebElement firstNameElement = driver.findElement(By.xpath("//input [@name = 'firstname']"));
        assertNotNull(firstNameElement);
        firstNameElement.click();
        firstNameElement.sendKeys("Karina");

        WebElement lastNameElement = driver.findElement(By.xpath("//input [@name = 'lastname']"));
        assertNotNull(lastNameElement);
        lastNameElement.click();
        lastNameElement.sendKeys("Fesenko");


        WebElement genderElement = driver.findElement(By.xpath("//div [@class = 'mtm mbs _2_68']"));
        assertNotNull(genderElement);
        WebElement genderCustomElement = driver.findElement(By.xpath("//label [@class = '_58mt']"));
        assertNotNull(genderCustomElement);
        genderCustomElement.click();


        WebElement signUpButton = driver.findElement(By.xpath("//button [@name = 'websubmit']"));
        assertNotNull(signUpButton);
        signUpButton.click();


    }


    @ParameterizedTest
    @ValueSource(strings = {"fesenkokaryna@gmail.com", "qwerty123", "women", "Karyna", "Fesenko"})
    public void checkInputs(String value) {
        System.out.println("The value is " + value);


    }
//Module10

    @Test
    public void signupTest() {
        driver.get(homePage);
        driver.findElement(By.xpath("//a[@data-testid = 'open-registration-form-button']")).click();
    }

    @Test
    public void gendersTest() throws InterruptedException {
        String femaleXpath = "//*[@name = 'sex' and @value = 1]";
        String maleXpath = "//*[@name = 'sex' and @value = 2]";

        driver.findElement(By.xpath("//a[@data-testid = 'open-registration-form-button']")).click();
        assertNotNull(driver.findElement(By.xpath("//button [@type = 'submit']")));
        Thread.sleep(1000);

        //verify female gender is checked
        WebElement femaleButton = driver.findElement(By.xpath(femaleXpath));
        femaleButton.click();
        String isFemaleChecked = driver.findElement(By.xpath(femaleXpath)).getAttribute("checked");
        assertNotNull(isFemaleChecked);
        assertEquals("true", isFemaleChecked);

        //verify male gender is checked
        WebElement maleButton = driver.findElement(By.xpath(maleXpath));
        maleButton.click();
        String isMaleChecked = driver.findElement(By.xpath(maleXpath)).getAttribute("checked");
        assertNotNull(isMaleChecked);
        assertEquals("true", isMaleChecked);
    }

//    @Test
//    public void errorMessageTest() throws InterruptedException {
//        driver.findElement(By.xpath("//a[@data-testid = 'open-registration-form-button']")).click();
//        assertNotNull(driver.findElement(By.xpath("//button [@type = 'submit']")));
//        Thread.sleep(2000);
//
//        driver.findElement(By.xpath("//button [@name = 'websubmit']")).click();
//        driver.findElement(By.xpath("//input [@name= 'reg_email__']")).click();
//        WebElement errorMessage = driver.findElement(By.xpath("//*[contains(text(), 'для']"));
//        assertNotNull(errorMessage);
//    }

    @Test
    public void birthYearTest() throws InterruptedException {
        driver.findElement(By.xpath("//a[@data-testid = 'open-registration-form-button']")).click();
        assertNotNull(driver.findElement(By.xpath("//button [@type = 'submit']")));
        Thread.sleep(2000);

        driver.findElement(By.xpath("//* [@name = 'birthday_year']")).click();
        driver.findElement(By.xpath("//* [text() = '2002']")).click();
        String yearValue = driver.findElement(By.xpath("//* [@name = 'birthday_year']")).getAttribute("value");
        assertEquals("2002", yearValue);


    }


    @ParameterizedTest
    @ValueSource(strings = {"1910", "1950", "1978", "2024"})
    public void birthYearParametrizedTest(String birthYear) throws InterruptedException {
        driver.findElement(By.xpath("//a[@data-testid = 'open-registration-form-button']")).click();
        assertNotNull(driver.findElement(By.xpath("//button [@type = 'submit']")));
        Thread.sleep(2000);

        driver.findElement(By.xpath("//* [@name = 'birthday_year']")).click();
        driver.findElement(By.xpath("//* [text() = '" + birthYear + "']")).click();
        String yearValue = driver.findElement(By.xpath("//* [@name = 'birthday_year']")).getAttribute("value");
        assertEquals(birthYear, yearValue);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void actionTest() {
        driver.get("https://daviktapes.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a [text() = 'Company']")));
        WebElement element = driver.findElement(By.xpath("//a [text() = 'Company']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    @Test
    public void visibilityTest() {
        driver.get("https://daviktapes.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a [text() = 'Company']"))).click();
        WebElement element = driver.findElement(By.xpath("//a [text() = 'Company']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void pause() {
        try {
            Thread.sleep(5000);
        } catch (Exception error) {
            System.out.println("Something went wrong");
        }
    }
}

