package br.ce.camille.kamimura.tasks.functional;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
		
		//System.setProperty("webdriver.chrome.driver", "C:\\tools-dev\\seleniumDrivers\\chromedriver.exe");
		
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.9:4444/wd/hub"), cap);
			
		//WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://192.168.0.9:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
			
		WebDriver driver = acessarAplicacao();
		
		try {
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a descricao
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("31/10/2022");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
	
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			assertEquals("Success!", message);
		} finally {
			//fechar o browser
			driver.quit();	
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
			
		WebDriver driver = acessarAplicacao();
		
		try {
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("31/10/2022");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
	
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			assertEquals("Fill the task description", message);
		} finally {
			//fechar o browser
			driver.quit();	
		}
	}
	
	@Test
	public void deveSalvarTarefaSemData() throws MalformedURLException {
			
		WebDriver driver = acessarAplicacao();
		
		try {
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a descricao
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
	
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			assertEquals("Fill the due date", message);
		} finally {
			//fechar o browser
			driver.quit();	
		}
	}
	
	@Test
	public void deveSalvarTarefaComDataPassada() throws MalformedURLException {
			
		WebDriver driver = acessarAplicacao();
		
		try {
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a descricao
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("31/10/2020");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
	
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			assertEquals("Due date must not be in past", message);
		} finally {
			//fechar o browser
			driver.quit();	
		}
	}
}
