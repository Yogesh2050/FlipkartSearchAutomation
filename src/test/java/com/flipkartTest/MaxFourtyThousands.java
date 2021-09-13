package com.flipkartTest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.flipkartPages.HomePage;
import com.flipkartPages.SearchPage;
import com.opencsv.CSVWriter;

public class MaxFourtyThousands {
	protected WebDriver driver;
	protected String url = null;
	protected int underPrice = 40000;
/* Initialize Browser*/
	@BeforeSuite
	public void initializeBrowser() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers/chromedriver.exe");
		driver = new ChromeDriver(new ChromeOptions().addArguments("start-maximized"));
		url = "https://www.flipkart.com";
	}

	@BeforeTest
	public void beforeTest() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
	}

	@Test
	public void search() throws TimeoutException {
		HomePage homePage = new HomePage(driver);

		if (homePage.loginPopUpExists()) {
			homePage.closeLoginPopup();
		}

		homePage.search("iPhone");

		Assert.assertEquals(true, true, "");

		SearchPage searchPage = new SearchPage(driver);
		searchPage.applyMobileFilter();
		searchPage.applyPriceFilter(underPrice);
		searchPage.appplyAvailabilityFilter();

		List<String[]> iphones = searchPage.getResultDetails();

		try {
			CSVWriter writer = new CSVWriter(new FileWriter("searchResult.csv"));
			writer.writeAll(iphones);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/* quit Browser*/
	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}
