package blindHire.form;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SignUpFormTest {
    WebDriver driver;
    WebDriverWait webDriverWait;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        int timeOutInSeconds = 10;
        webDriverWait = new WebDriverWait(driver, timeOutInSeconds);
    }

@Test
    public void all_Process_test() throws InterruptedException {

        //회원가입 단계
        joinProcess();

        //온보딩 1단계
        onBoardingStep1();

        //온보딩 2단계
        onBoardingStep2();

        //온보딩 3단계
        onBoardingStep3();

        //온보딩 4단계
        onBoardingStep4();

        //온보딩 5단계
        onBoardingStep5();

        onBoardingStep6WithFile();
        Thread.sleep(2000);

        onBoardingStep7WithFile();

        Thread.sleep(2000);
        onBoardingStep9();



        onBoardingFinish();

        //driver.get("http://kr.dev1.dkshk.net:6085/");

}


    private void joinProcess() throws InterruptedException {
        //회원가입 폼 페이지 이동
        driver.get("http://kr.dev1.dkshk.net:7092/");

        WebElement Link = driver.findElement(By.linkText("회원가입"));
        Link.click();

        //set email value
        WebElement signupEmail1 = findForDuplicatedIdByTagName(driver, "signup-email1", "input");
        String emailValueNum = Integer.toString((int) (Math.random() * 1000000));
        String emailValue = "test-email-" + emailValueNum;
        signupEmail1.sendKeys(emailValue);

        //도메인 선택 영역 클릭
        WebElement domain = driver.findElement(By.cssSelector(".fieldset.fieldset_slct"));
        domain.click();

        //도메인 리스트 생성 후 첫번째 도메인 선택
        WebElement list = driver.findElement(By.cssSelector(".fieldset.fieldset_slct .auto")); //CLASS 선택시에 같은 레벨에 있는 클래스는 .(점)을 붙여서 씀. 하위접근은 띄어쓰기
        //서버통신필요 case....
        List<WebElement> domainList = list.findElements(By.tagName("li"));
        WebElement emailDomainItem = domainList.get(0);
        String selectedEmailDomainText = emailDomainItem.getText(); //click 하기전에 미리 뺴야 오류안남
        emailDomainItem.click();
        WebElement signupEmail2 = findForDuplicatedIdByTagName(driver, "signup-email2", "input");
        //선택한 이메일 도메인값과 입력된이메일값 동일한지 검증
        System.out.println("selectedEmailDomainText: " + selectedEmailDomainText);
        String targetEmailDomainText = signupEmail2.getAttribute("value");
        System.out.println("targetEmailDomainText: " + targetEmailDomainText);
        assertThat(selectedEmailDomainText).isEqualTo(targetEmailDomainText);
        //패스워드 입력
        WebElement signupPassword = findForDuplicatedIdByTagName(driver, "signup-password", "input");
        String inputPassword = "iii888!!";
        signupPassword.sendKeys(inputPassword);
        //패스워드 확인 입력
        WebElement signupPasswordMatch = findForDuplicatedIdByTagName(driver, "signup-password-match", "input");
        signupPasswordMatch.sendKeys(inputPassword);
        //회원가입 버튼 선택
        WebElement signUpButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("button")));
        signUpButton.click();

        //지역 번호 선택
        WebElement inputName = findForDuplicatedIdByTagName(driver, "signup-name", "input");
        String nameValueNum = Integer.toString((int) (Math.random() * 1000000));
        inputName.sendKeys("QA_TEST_" + nameValueNum);

        WebElement phoneNum = driver.findElement(By.cssSelector(".form_wrap.form_cc .fieldset .btn_cc"));
        phoneNum.click();

        //TODO wating과 SELECTOR 추후 변경 필요
        Thread.sleep(1000);
        WebElement phoneNumList = driver.findElement(By.cssSelector(".form_wrap.form_cc .fieldset .auto"));

        List<WebElement> phoneNumListOpen = phoneNumList.findElements(By.tagName("li"));
        WebElement selectPhoneNum = phoneNumListOpen.get(4);
        selectPhoneNum.click();

        //휴대폰번호 입력
        WebElement inputPhoneNum = findForDuplicatedIdByTagName(driver, "signup-mobile", "input");
        String phoneValueNum = Integer.toString((int) (Math.random() * 1000000000));
        inputPhoneNum.sendKeys(phoneValueNum);

        //전체 동의 체크박스 선택
//        WebElement allAgreeButton = driver.findElement(By.xpath("//label[text()='전체 동의']"));
        WebElement allAgreeButton = driver.findElement(By.cssSelector("#agree_all+label"));
        allAgreeButton.click();

        //회원가입하기 버튼 선택
        WebElement signupSucButton = driver.findElement(By.name("button"));
        signupSucButton.click();

        //확인 및 가입 완료하기 버튼 선택
        WebElement finshButton = driver.findElement(By.className("submit"));
//        throw new WebDriverException("my error");
        finshButton.click();

    }

    private void onBoardingStep9() {

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".tit_area")));
        WebElement nextSelectButton = driver.findElement(By.cssSelector(".btn_area_v2 .btn_postpone"));
        nextSelectButton.click();

    }

    private void onBoardingFinish(){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".intro_area")));
        WebElement finishButton = driver.findElement(By.cssSelector(".btn_apply"));
        finishButton.click();

    }

    private void onBoardingStep7WithFile() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".upload_area")));
        //파일업로드 영역선택
        Thread.sleep(2000);
        WebElement upload_file2 = driver.findElement(By.cssSelector(".file_pre #file"));
        upload_file2.sendKeys("C:\\Users\\SAMSUNG\\IdeaProjects\\untitled\\src\\test\\resources\\drivers\\asd.pdf");
        //다음버튼 누르기
        WebElement onboardingStep7NextButton = driver.findElement(By.className("btn_next"));
        onboardingStep7NextButton.click();

    }

/*        private void onBoardingStep6() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".upload_area")));
        WebElement linkInputTab = driver.findElement(By.cssSelector(".cv_wrap.on_upload .link_area .tab"));
        linkInputTab.click();
        Thread.sleep(3000);
        WebElement urlInput = driver.findElement(By.cssSelector(".link_area .link_inr .form_obj .form_wrap .fieldset :nth-child(1)"));
        urlInput.click();
        urlInput.sendKeys("https://www.google.com");
        WebElement onboardingStep6NextButton = driver.findElement(By.className("btn_next"));
        onboardingStep6NextButton.click();
    }*/

    private void onBoardingStep6WithFile() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".upload_area")));
        //파일업로드 영역선택
        WebElement upload_file = driver.findElement(By.cssSelector(".file_pre #file"));
        Thread.sleep(500);
        upload_file.sendKeys("C:\\Users\\SAMSUNG\\IdeaProjects\\untitled\\src\\test\\resources\\drivers\\asd.pdf");
        //다음버튼 누르기
        WebElement onboardingStep6NextButton = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("btn_next")));
        onboardingStep6NextButton.click();
    }


    private void onBoardingStep5() {
        //회사 검색 후 선택
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".fieldset.fieldset_srm :nth-child(1)")));
        WebElement universitySearch = driver.findElement(By.cssSelector(".fieldset.fieldset_srm :nth-child(1)"));
        universitySearch.click();
        universitySearch.sendKeys("서울");

        //TODO SELECTOR 추후 변경 필요
        WebElement universityItem = webDriverWait.until(ExpectedConditions.presenceOfElementLocated((By.cssSelector(".fieldset.fieldset_srm :nth-child(2) ul li :nth-child(1)"))));
        universityItem.click();

        WebElement major = driver.findElement(By.cssSelector(".form_cols :nth-child(1) .form_wrap .fieldset :nth-child(1)"));
        major.sendKeys("전공");

        WebElement degree = driver.findElement(By.cssSelector(".form_cols :nth-child(2) .form_wrap .fieldset :nth-child(1)"));
        degree.sendKeys("학위");

        WebElement onboardingStep5NextButton = driver.findElement(By.className("btn_next"));
        onboardingStep5NextButton.click();


    }

    private void onBoardingStep4() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ctt_area .write_area .write_ing .txtarea :nth-child(1)")));
        WebElement mainJobArea = driver.findElement(By.cssSelector(".ctt_area .write_area .write_ing .txtarea :nth-child(1)"));
        mainJobArea.sendKeys("12345678901234567890123456789012345678901234567890");
        WebElement onboardingStep4NextButton = driver.findElement(By.className("btn_next"));
        onboardingStep4NextButton.click();
    }

    private void onBoardingStep3() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".form_chk")));
        //회사 검색 후 선택
        WebElement companySearch = driver.findElement(By.cssSelector(".fieldset.fieldset_srm :nth-child(1)"));
        companySearch.click();
        companySearch.sendKeys("teamblind11회사이름");

//        WebElement companyList = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".fieldset.fieldset_srm :nth-child(2) ul")));
        WebElement companyItem = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".fieldset.fieldset_srm :nth-child(2) ul li :nth-child(1)")));
//        WebElement companyItem = companyList.findElements(By.tagName("li")).get(0);
        companyItem.click();

        WebElement department = driver.findElement(By.cssSelector(".form_cols :nth-child(1) .form_wrap .fieldset :nth-child(1)"));
        department.sendKeys("부서");

        WebElement position = driver.findElement(By.cssSelector(".form_cols :nth-child(2) .form_wrap .fieldset :nth-child(1)"));
        position.sendKeys("직책");

        WebElement startDate = driver.findElement(By.cssSelector(".form_cal :nth-child(1) .month-picker-input-container .month-picker-input"));
        startDate.click();
        WebElement startDateItem = driver.findElement(By.cssSelector(".month-picker__container.month-picker--default .month-picker .month-picker__month"));
        startDateItem.click();

        WebElement endDate = driver.findElement(By.cssSelector(".form_cal :nth-child(3) .month-picker-input-container .month-picker-input"));
        endDate.click();
        WebElement endDateItem = driver.findElement(By.cssSelector(".form_cal :nth-child(3) .month-picker-input-container .month-picker__container.month-picker--default .month-picker .month-picker__month"));
        endDateItem.click();
        WebElement onBoardingStep3NextButton = driver.findElement(By.className("btn_next"));
        onBoardingStep3NextButton.click();
    }

    private void onBoardingStep1() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".dtl_area :nth-child(1) .form_wrap .flexselect .choices .choices__inner")));
        //직군 리스트 선택하기
        WebElement jobListElement = driver.findElement(By.cssSelector(".dtl_area :nth-child(1) .form_wrap .flexselect"));
        jobListElement.click();

        WebElement jobElement = driver.findElement(By.cssSelector(".dtl_area :nth-child(1) .form_wrap .flexselect .choices .choices__list.choices__list--dropdown .choices__list :nth-child(2)"));
        Thread.sleep(500);
        jobElement.click();

        //세부직군 리스트 선택 하기
        WebElement subjobListElement = driver.findElement(By.cssSelector(".dtl_area :nth-child(2) .form_wrap .flexselect"));
        subjobListElement.click();
        WebElement subjobElement = driver.findElement(By.cssSelector(".dtl_area :nth-child(2) .form_wrap .flexselect .choices .choices__list.choices__list--dropdown .choices__list :nth-child(2)"));
        Thread.sleep(500);
        subjobElement.click();

        //총 경력 선택하기
        WebElement yoeListElement = driver.findElement(By.cssSelector(".dtl_area :nth-child(3) .form_wrap .flexselect"));
        yoeListElement.click();
        WebElement yoeElement = driver.findElement(By.cssSelector(".dtl_area :nth-child(3) .form_wrap .flexselect .choices .choices__list.choices__list--dropdown .choices__list :nth-child(2)"));
        Thread.sleep(500);
        yoeElement.click();
        //다음 버튼 선택
        WebElement onboardingSetp1NextButton = driver.findElement(By.className("btn_next"));
        onboardingSetp1NextButton.click();
    }

    private void onBoardingStep2() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".tags_input_wrap")));
        //스킬 검색 후 선택
        WebElement skillSearch = driver.findElement(By.cssSelector(".fieldset_srm :nth-child(1)"));
        skillSearch.sendKeys("java");
        skillSearch.click();

        WebElement skillList = webDriverWait.until(ExpectedConditions.presenceOfElementLocated((By.cssSelector(".fieldset.fieldset_srm :nth-child(2) ul li :nth-child(1)"))));
        skillList.click();

        //추천 스킬 키워드 선택
        WebElement tagList = driver.findElement(By.cssSelector(".tag_list"));
        List<WebElement> tagLiList = tagList.findElements(By.tagName("li"));
        WebElement tagItem = tagLiList.get(0);
        tagItem.click();
        //다음 버튼 서택
        WebElement onboardingSetp2NextButton = driver.findElement(By.className("btn_next"));
        onboardingSetp2NextButton.click();
    }

   /* @Test
    public void 대기시간_예() {

        int timeOutInSeconds = 20;
        String id = "id";

        WebElement webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));

        WebElement domain = driver.findElement(By.cssSelector(".fieldset.fieldset_slct li"));
        WebElement webElement2 = webDriverWait.until(ExpectedConditions.visibilityOf(domain));
    }
    */

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

/*
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
 */
}