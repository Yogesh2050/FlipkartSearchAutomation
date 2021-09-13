package com.flipkartPages;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends PageObject{
	private WebDriver driver;
	private Actions action;
	private WebDriverWait wait;
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	/*Checks for Login PopUp*/
	public boolean loginPopUpExists() throws TimeoutException {
		wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@tabindex='-1']/div"))));
		}catch(NoSuchElementException te) {
			return false;
		}
		return true;
	}
	
	/*Skips Login Pup up*/
	public boolean closeLoginPopup() {
		try {		
			driver.findElement(By.xpath("//div[@tabindex='-1']/div/button")).click();
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}
	

}
