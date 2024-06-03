package com.alerts.SafetyNet.configuration;




public class DtoConstants {
	

	public static enum PersonField {
		FIRST_NAME, LAST_NAME, ADDRESS, CITY, ZIP, PHONE, EMAIL, AGE, MEDICATIONS, ALLERGIES
	}
	
	// preset of fields for "/covergePerson/get" URL
		public static PersonField[] UrlFirestationCoveragePerson = new PersonField[] { PersonField.FIRST_NAME,
				PersonField.LAST_NAME, PersonField.ADDRESS, PersonField.PHONE };
	
	

}
