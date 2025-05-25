package com.example.demo.ui.Laptop;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
@Timeout(10)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AddLaptopParameterizedTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/laptop_testcases.csv", numLinesToSkip = 1)
    public void testAddLaptopWithData(String name, String model, String originalPrice, String currentPrice,
                                      String discord, String link, String description) {

        driver.get("http://localhost:8080/admin/dsSP");

        // Mở modal thêm laptop
        WebElement btnOpenModal = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOpenModal")));
        btnOpenModal.click();

        // Chờ modal hiện ra
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("laptopModal1")));

        // Nhập dữ liệu vào form
        driver.findElement(By.id("laptop_name")).clear();
        driver.findElement(By.id("laptop_name")).sendKeys(name);

        driver.findElement(By.id("laptop_model")).clear();
        driver.findElement(By.id("laptop_model")).sendKeys(model);

        driver.findElement(By.id("laptop_originalPrice")).clear();
        driver.findElement(By.id("laptop_originalPrice")).sendKeys(originalPrice);

        driver.findElement(By.id("laptop_currentPrice")).clear();
        driver.findElement(By.id("laptop_currentPrice")).sendKeys(currentPrice);

        driver.findElement(By.id("laptop_discord")).clear();
        driver.findElement(By.id("laptop_discord")).sendKeys(discord);

        driver.findElement(By.id("laptop_link")).clear();
        driver.findElement(By.id("laptop_link")).sendKeys(link);

        driver.findElement(By.id("laptop_description")).clear();
        driver.findElement(By.id("laptop_description")).sendKeys(description);

        // Submit form
        driver.findElement(By.cssSelector("#laptopModal1 button[type='submit']")).click();

            /// Chờ alert hiện ra
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        // Lấy text của alert
        String alertText = alert.getText();

        // Kiểm tra text alert
        Assertions.assertEquals("Thêm laptop thành công!", alertText);

        // Chấp nhận alert (OK)
        alert.accept();

        // Đợi URL quay lại trang danh sách (nếu thêm thành công)
        wait.until(ExpectedConditions.urlContains("/admin/dsSP"));
        Assertions.assertTrue(driver.getCurrentUrl().contains("/admin/dsSP"));
    }
}
