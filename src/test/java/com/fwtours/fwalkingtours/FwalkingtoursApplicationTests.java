package com.fwtours.fwalkingtours;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FwalkingtoursApplicationTests {

	private WebDriver driver;

	@AfterAll
	static void setupAll(){
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	void setup(){
		driver = new ChromeDriver();
	}

	@Test
	void testLoginAdminHappyPath() {
		driver.get("http://localhost:8080/login");
		driver.findElement(By.id("email")).sendKeys("admin60@fwtours.com");
		driver.findElement(By.id("password")).sendKeys("adminSeguro123");
		driver.findElement(By.className("btn-primary")).click();

		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.contains("/admin/usuarios"),
				"Login ha fallado. No ha redirigido al admin de usuarios");

		System.out.println("Inicio sesion correctamente");

	}

	@AfterEach
    void tearDown(){
			if (driver != null) {
				driver.quit();
			}
		}
	}
