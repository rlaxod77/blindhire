package blindHire.form.controller.process;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Onboardding1 {
    WebDriver driver;
    WebDriverWait webDriverWait;
    public Onboardding1(WebDriver driver, WebDriverWait webDriverWait) {
        this.webDriverWait = webDriverWait;
        this.driver = driver;
    }
}
