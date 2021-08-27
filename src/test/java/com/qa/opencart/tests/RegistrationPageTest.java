package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void registrationPageSetUp() {
		regPage=homePage.doClickOnRegistrationLink("Register");
	}
	
	public String getRandomEmail() {
		Random random=new Random();//generates random numbers
		String email="mythiliautomation"+random.nextInt(5000)+"@gmail.com";
		System.out.println(email);
		return email;
	}
	
	@DataProvider
	public Object[][] getTestRegData() {
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}
	
	@Test(dataProvider="getTestRegData")
	public void doRegisterTest(String firstName,String lastName,String phoneNo,String password,String subscribe) {
		Assert.assertTrue(regPage.accountRegistration(firstName,lastName,getRandomEmail(),
				phoneNo,password,subscribe));
	}

}
