package com.emailage.javawrapper.test;

import java.io.IOException;

import com.emailage.javawrapper.model.Enums;
import com.emailage.javawrapper.model.ExtraInputParameter;
import com.emailage.javawrapper.utilities.EmailageClient;

public class TestClient {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		/* Results can be in JSON or XML format */
		Enums.Format resultFormat = Enums.Format.Json;
		Enums.SignatureMethod signatureMethod = Enums.SignatureMethod.HMAC_SHA256;

		/*
		 * OPTIONAL FIELD. Use this field if you want to associate the API call
		 * to a specific Emailage user.
		 */
		String user_email = null;
		Enums.Environment environment = Enums.Environment.Production;

		// Email validation
		try {
			System.out.println("Querying Email");

			String validResult = EmailageClient.QueryEmail("test@test.com", resultFormat, signatureMethod, user_email,
					environment);

			System.out.println(validResult);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Email + IP validation
		try {
			System.out.println("Querying Email + IP");

			String validResult = EmailageClient.QueryEmailAndIP("test@test.com", "147.12.12.13", resultFormat,
					signatureMethod, user_email, environment);

			System.out.println(validResult);

		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * Querying Email + IP and passing additional arguments, in this case,
		 * billpostal code
		 */
		try {
			System.out.println("Querying Email + IP + Extra Arguments");

			ExtraInputParameter extraArgs = new ExtraInputParameter();
			extraArgs.setbillpostal("85225");
			extraArgs.setbillcity("Chandler");

			String validResult = EmailageClient.QueryEmailAndIPPlusExtraArgs("test@test.com", "147.12.12.13", extraArgs,
					resultFormat, signatureMethod, user_email, environment);

			System.out.println(validResult);

		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * This method is used to report cases where a "confirmed fraud" or
		 * "confirmed good" email is found at the customer site.
		 */
		// Email + IP validation
		try {
			System.out.println("Marking an Email as Fraud");

			int fraudCodeID = 1;
			/*
			 * fraudcodeID parameter Reference: Fraud Code ID Description 1 Card
			 * Not Present Fraud 2 Customer Dispute (Chargeback) 3 First Party
			 * Fraud 4 First Payment Default 5 Identify Theft (Fraud
			 * Application) 6 Identify Theft (Account Take Over) 7 Suspected
			 * Fraud (Not Confirmed) 8 Synthetic ID 9 Other
			 */

			String validResult = EmailageClient.MarkEmailAsFraud("test@test.com", Enums.FraudFlag.Fraud, fraudCodeID,
					resultFormat, signatureMethod, user_email, environment);

			System.out.println(validResult);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
