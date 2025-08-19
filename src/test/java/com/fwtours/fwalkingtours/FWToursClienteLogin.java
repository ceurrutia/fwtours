package com.fwtours.fwalkingtours;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FWToursClienteLogin {

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
        driver.findElement(By.id("email")).sendKeys("ceciur@mail.com");
        driver.findElement(By.id("password")).sendKeys("contraseñaSegura123");
        driver.findElement(By.className("btn-primary")).click();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/cliente/reservas"),
                "Login ha fallado. No ha redirigido al perfil de reservas de cliente");

        System.out.println("Inicio sesion correctamente como cliente");

    }

    @AfterEach
    void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
