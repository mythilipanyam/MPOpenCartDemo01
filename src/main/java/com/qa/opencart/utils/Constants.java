package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {
//Arrays.asList is used to compare two lists
	
	public static final String HOMEPAGE_TITLE="Your Store";
	public static final String HOMEPAGE_HEADER_TEXT="Your Store";
	public static final String TOPLINK1_TEXT="123456789";
	public static final String TOPLINK2_TEXT="My Account";
	public static final String TOPLINK3_TEXT="Wish List (0)";
	public static final String TOPLINK4_TEXT="Shopping Cart";
	public static final String TOPLINK5_TEXT="Checkout";
	public static final List<String> EXPECTED_NAVBARLINKS_LIST=Arrays.asList("Desktops","Laptops & Notebooks","Components",
			"Tablets","Software","Phones & PDAs","Cameras","MP3 Players");
	public static final List<String> MYACCOUNT_DROPDOWNLINKS_LIST=Arrays.asList("Register","Login");
	public static final String LOGINPAGE_URL_PARTIAL_VALUE="login";
	public static final String LOGINPAGE_TITLE="Account Login";
	public static final List<String> EXPECTED_RHSLINKS_LIST=Arrays.asList("Login","Register","Forgotten Password","My Account",
        "Address Book","Wish List","Order History","Downloads","Recurring payments","Reward Points",
          "Returns","Transactions","Newsletter");
	public static final String ACCOUNTPAGE_URL_PARTIAL_VALUE="/account";
	public static final String ACCOUNTPAGE_TITLE="My Account";
	public static final List<String> EXPECTED_ACCOUNTPAGE_LINKS_LIST=Arrays.asList("My Account","My Orders",
			"My Affiliate Account","Newsletter");
	
    public static final String REGISTER_SUCCESS_MESSAGE="Your Account Has Been Created";
    public static final String REGISTER_SHEET_NAME="Registration";
			
    public static final String WISHLISTPAGE_HEADER_TEXT="My Wish List";
    public static final String CARTPAGE_PARTIAL_HEADER_TEXT="Shopping Cart";
	
			

}
