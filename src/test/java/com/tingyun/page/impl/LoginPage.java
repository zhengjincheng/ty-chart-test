package com.tingyun.page.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	private  WebDriver driver;
	public LoginPage(WebDriver driver){
		this.driver=driver;
	}
	public void login(String name,String passwd){
		System.out.println("begin login");
		//登录页面
        //输入用户�?
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(name);
        //输入密码
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(passwd);
        //点击确定按钮
        try {
        	driver.findElement(By.cssSelector("div.mty-btn-pc.mty-btn-blue")).click();
        }catch (NoSuchElementException e) {
        	driver.findElement(By.cssSelector("a.submit")).click();
        }
		System.out.println("end login");

	}
}
