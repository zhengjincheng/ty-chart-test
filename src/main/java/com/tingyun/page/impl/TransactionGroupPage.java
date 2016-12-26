package com.tingyun.page.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransactionGroupPage {
	private  WebDriver driver;
	public TransactionGroupPage(WebDriver driver){
		this.driver=driver;
	}
	
	public void clickAddButton(){
		driver.findElement(By.id("addTransaction")).click();
	}
	
	//设置事务名称
	public void setName(String name){
		 WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(new ExpectedCondition<WebElement>(){
				@Override
				public WebElement apply(WebDriver d) {
					return d.findElement(By.id("tranName"));
				}}).clear();
			
		//driver.findElement(By.id("tranName")).clear();
        driver.findElement(By.id("tranName")).sendKeys(name);
	}
	//点击应用过程按钮
	public void clickAddYygcButton(){
		driver.findElement(By.id("createData")).click();
	}
	
	//选择应用名称
	public void selectApplication(String name){
		driver.findElement(By.id("tran_applicationId")).click();
		driver.findElement(By.xpath("//div[@id='tran_applicationId_chosen']/div/ul/li[2]")).click();
		
		
		
		
	}
	
	
	
}
