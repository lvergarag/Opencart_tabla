package stepdefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import utils.CSVReader;

import java.util.List;
import java.io.*;

import static org.junit.Assert.assertEquals;

public class OpenCartSteps {

    WebDriver driver;
    HomePage home;

    @Given("user opens opencart")
    public void open_site() throws InterruptedException {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        Thread.sleep(1000); // ⏱ espera 1 segundo

        home = new HomePage(driver);
        home.open();

        Thread.sleep(2000); // ⏱ espera a que cargue la página
    }

    @When("user validates phones from table")
    public void validate_from_table() throws InterruptedException {

        String path = "src/test/resources/data/phones.csv";

        List<String[]> phones = CSVReader.getData(path);

        int index = getCurrentIndex();

        // Si se pasa del tamaño, vuelve a empezar
        if (index >= phones.size()) {
            index = 0;
        }

        String[] phone = phones.get(index);

        String product = phone[0];
        String expected = phone[1];

        Thread.sleep(1000); // ⏱ antes de hacer click
        home.clickProduct(product);

        Thread.sleep(2000); // ⏱ espera a que cargue el producto

        String actual = home.getProductCode();

        Thread.sleep(1000); // ⏱ antes de validar
        assertEquals(expected, actual);

        System.out.println(" ");
        System.out.println("Validando: " + product + " -> " + actual);
        System.out.println(" ");

        Thread.sleep(1000); // ⏱ antes de volver atrás
        driver.navigate().back();

        saveNextIndex(index + 1);

        Thread.sleep(1000); // ⏱ antes de cerrar
        driver.quit();
    }

    // =========================
    // MÉTODOS AUXILIARES
    // =========================

    private int getCurrentIndex() {
        try {
            File file = new File("index.txt");

            if (!file.exists()) {
                return 0;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            br.close();

            return Integer.parseInt(line);

        } catch (Exception e) {
            return 0;
        }
    }

    private void saveNextIndex(int index) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("index.txt"));
            bw.write(String.valueOf(index));
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}