package blindHire.form.controller;

import blindHire.form.controller.process.Onboardding1;
import blindHire.form.controller.process.SignUpForm;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JoinController {
    WebDriver driver;
    WebDriverWait webDriverWait;
    SignUpForm signUpForm;
    Onboardding1 onboardding1;



    @BeforeClass
    public void init(){
        driverInit();
        signUpForm = new SignUpForm(driver, webDriverWait);
        onboardding1 = new Onboardding1(driver, webDriverWait);
    }

    private void driverInit() {
        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        int timeOutInSeconds = 10;
        webDriverWait = new WebDriverWait(driver, timeOutInSeconds);
    }

    @Test
    public void SignUpForm_signUpFormOnly_th(){
        Assertions.assertThatCode(
                () -> {signUpForm.moveToForm();})
                .doesNotThrowAnyException();
    }

    @Test
    public void SingUpForm_inputEmail(){
        Assertions.assertThatCode(
                        () -> {signUpForm.InputEmail();})
                .doesNotThrowAnyException();
    }
}
