package com.flipkartPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObject {
	private WebDriver driver;
	/* Search box  */
	@FindBy(css = "input[name='q']")
	private WebElement searchBox;
	/* Search Button*/
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement searchBtn;

	protected PageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/* Search for item in SearchBox */
	public void search(String query) {
		searchBox.sendKeys(query);
		searchBtn.click();
	}

}
