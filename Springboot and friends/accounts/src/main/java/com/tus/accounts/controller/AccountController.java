package com.tus.accounts.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tus.accounts.constants.AccountsConstants;
import com.tus.dto.CustomerDto;
import com.tus.dto.ResponseDto;

@RestController
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController{

	@PostMapping
	public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){
			return ResponseEntity
						.status(HttpStatus.CREATED)
						.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
	
	
}}