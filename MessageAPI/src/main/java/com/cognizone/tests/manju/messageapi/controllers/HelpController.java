package com.cognizone.tests.manju.messageapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelpController {
	
	
	@ResponseBody
	public String getHelp(){
		return "  -------------------  MEssage API HELP ------------------" +
				" 1. Create Message : URI = /";
	}
	
	
	

}
