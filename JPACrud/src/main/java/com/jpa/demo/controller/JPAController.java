package com.jpa.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.demo.model.JPAModel;
import com.jpa.demo.model.Response;
import com.jpa.demo.service.JPAService;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class JPAController {
	
 @Autowired
 JPAService jpa;
 
 @PostMapping("/insert")
 public ResponseEntity<Response> createUser(@RequestBody JPAModel values){
	 return ResponseEntity.ok(jpa.createUser(values));
 }
 
 @PutMapping("/update")
 public ResponseEntity<Response> updateUser(@RequestParam String sNo ,String email){
	 return ResponseEntity.ok(jpa.updateUser(sNo,email));
 }
 @DeleteMapping("/delete")
 public ResponseEntity<Response> deleteUser(@RequestParam String sNo){
	 return ResponseEntity.ok(jpa.deleteUser(sNo));
 }
 @GetMapping("/getall")
 public ResponseEntity<Response> getallUser(){
	 return ResponseEntity.ok(jpa.getallUser());
 }
 @GetMapping("/getone")
 public ResponseEntity<Response> getOneUser(@RequestParam String sNo){
	 return ResponseEntity.ok(jpa.getOneUser(sNo));
 }
public ResponseEntity<Response> ScamUser(){
	 return ResponseEntity.ok(jpa.ScamUser());
 }

@PostMapping("/login")
public ResponseEntity<Response> loginUser(@RequestParam String email,String password){
	 return ResponseEntity.ok(jpa.loginUser(email,password));
 }
	



}
