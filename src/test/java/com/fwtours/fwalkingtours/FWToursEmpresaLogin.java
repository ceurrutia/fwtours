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

public class FWToursEmpresaLogin {
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
        driver.findElement(By.id("email")).sendKeys("empresa1@fwtours.com");
        driver.findElement(By.id("password")).sendKeys("contraseñaSegura123");
        driver.findElement(By.className("btn-primary")).click();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/empresa/reservas"),
                "Login ha fallado. No ha redirigido al perfil de reservas de empresa");

        System.out.println("Inicio sesion correctamente como empresa a su perfil");

    }

    @AfterEach
    void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
