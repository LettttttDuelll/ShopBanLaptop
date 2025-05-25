package com.example.demo.ui.Laptop;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import org.springframework.boot.test.context.SpringBootTest;
import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Timeout(10)
public class AddLaptopTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testAddLaptopModalFormSimplified() {
    driver.get("http://localhost:8080/admin/dsSP");

    // Click nút mở modal
    WebElement btnOpenModal = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOpenModal")));
    btnOpenModal.click();

    // Chờ modal hiện ra
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("laptopModal1")));

    // Nhập dữ liệu
    driver.findElement(By.id("laptop_name")).sendKeys("");
    driver.findElement(By.id("laptop_model")).sendKeys("Model X");
    driver.findElement(By.id("laptop_originalPrice")).sendKeys("1500");
    driver.findElement(By.id("laptop_currentPrice")).sendKeys("1300");
    driver.findElement(By.id("laptop_discord")).sendKeys("15%");
    driver.findElement(By.id("laptop_link")).sendKeys("http://example.com");
    driver.findElement(By.id("laptop_description")).sendKeys("Mô tả laptop test Selenium");

    // Click nút submit
    driver.findElement(By.cssSelector("#laptopModal1 button[type='submit']")).click();

    // Xử lý alert
    wait.until(ExpectedConditions.alertIsPresent()).accept();

    // Kiểm tra URL đã quay lại trang danh sách
    wait.until(ExpectedConditions.urlContains("/admin/dsSP"));
    Assertions.assertTrue(driver.getCurrentUrl().contains("/admin/dsSP"));
}

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
