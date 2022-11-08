package blindHire.form.controller.process;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SignUpForm
{
    WebDriver driver;
    WebDriverWait webDriverWait;
    public SignUpForm(WebDriver driver, WebDriverWait webDriverWait) {
        this.webDriverWait = webDriverWait;
        this.driver = driver;
    }

    public void moveToForm() {
        //회원가입 폼 페이지 이동
        driver.get("http://kr.dev1.dkshk.net:7092/");
        WebElement Link = driver.findElement(By.linkText("회원가입"));
        Link.click();
    }
    //set email value
    public void InputEmail(){
        WebElement signupEmail1 = findForDuplicatedIdByTagName(driver, "signup-email1", "input");
        String emailValueNum = Integer.toString((int) (Math.random() * 1000000));
        String emailValue = "test-email-" + emailValueNum;
        signupEmail1.sendKeys(emailValue);

    }

    private WebElement findForDuplicatedIdByTagName(WebDriver driver, String byId, String tagName) {
        List<WebElement> targetElements = driver.findElements(By.id(byId));
        WebElement inputElement = null;
        for (WebElement webElement : targetElements) {
            if (webElement.getTagName().equals(tagName)) {
                inputElement = webElement;
            }
        }
        return inputElement;
    }
}
