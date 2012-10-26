package org.megam.deccanplato.provider.crm.test;

import org.apache.wink.client.RestClient;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.megam.deccanplato.provider.salesforce.info.SalesforceCRM;
import org.apache.wink.client.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SaleforceAdapterTest {

	private static String access_stuff;
	
    
	@BeforeClass
	public static void setUp() {
		System.out.println("setUp");
		RestClient rc = new RestClient();
		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm");
		access_stuff = resource.accept("application/json").get(String.class);
		System.out.println("Access =>" + access_stuff);
	}

	/*@Ignore
	@Test
	public void testCreateUser() {
		System.out.println("testCreateUser :" + access_stuff);
		RestClient rc = new RestClient();

		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm");
		String response = resource.contentType("application/json")
				.accept("application/json").post(String.class, access_stuff);
		System.out.println("testCreateUser :" + response);

	}
    @Ignore   
	@Test
	public void testListUser() {
		System.out.println("testListUser :"+ access_stuff);
		RestClient rc = new RestClient();
		Gson gson = new GsonBuilder().create();
		SalesforceCRM salesforceCRM = gson.fromJson(access_stuff,
				SalesforceCRM.class);
		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm/list");
		String response = resource.accept("application/json").get(String.class);
		System.out.println("testListUser :"+response);

	}
	@Ignore
	@Test
	public void SalesforceAccount(){
		System.out.println("testCreateAccount :" + access_stuff);
		RestClient rc = new RestClient();

		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm/account");
		String response = resource.contentType("application/json")
				.accept("application/json").post(String.class, access_stuff);
		System.out.println("testCreateAccount :" + response);
	}
	
	@Test
	public void testListAccount() {
		System.out.println("testListAccount :"+ access_stuff);
		RestClient rc = new RestClient();
		Gson gson = new GsonBuilder().create();
		SalesforceCRM salesforceCRM = gson.fromJson(access_stuff,
				SalesforceCRM.class);
		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm/account/list");
		String response = resource.accept("application/json").get(String.class);
		System.out.println("testListAccount :"+response);

	}
	@Ignore
	@Test
	public void Accountdelete(){
		System.out.println("testDeleteAccount :" + access_stuff);
		RestClient rc = new RestClient();

		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm/account");
		String response = resource.contentType("application/json")
				.accept("application/json").delete(String.class);
		System.out.println("testCreateAccount :" + response);
	}*/
	@Ignore
	@Test
	public void SalesforceLead(){
		System.out.println("testCreateLead :" + access_stuff);
		RestClient rc = new RestClient();

		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm/leads");
		String response = resource.contentType("application/json")
				.accept("application/json").post(String.class, access_stuff);
		System.out.println("testCreateAccount :" + response);
	}
	
	@Test
	public void testListLead() {
		System.out.println("testListLead :"+ access_stuff);
		RestClient rc = new RestClient();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		SalesforceCRM salesforceCRM = gson.fromJson(access_stuff,
				SalesforceCRM.class);
		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm/leads");
		String response = resource.accept("application/json").get(String.class);
		System.out.println("testListLead :"+response);

	}
	@Test
	public void Leaddelete(){
		System.out.println("testDeleteLead :");
		RestClient rc = new RestClient();

		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm/leads");
		String response = resource.contentType("application/json")
				.accept("application/json").delete(String.class);
		System.out.println("testdeleteLead :" + response);
	}
}