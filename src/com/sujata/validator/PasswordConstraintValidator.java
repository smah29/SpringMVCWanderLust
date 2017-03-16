package com.sujata.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<Password, String>{

	
	@Override
	public void initialize(Password String) { }

	@Override
	public boolean isValid(String passwordField, ConstraintValidatorContext cxt) {
		if(passwordField == null) {
			return false;
		}
		
		//return passwordField.matches("[0-9]*&[A-Z]*&[a-z]*&[@#$%]*");
		else 
			return true;
	}
}
