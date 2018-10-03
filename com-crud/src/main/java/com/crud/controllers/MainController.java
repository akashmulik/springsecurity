package com.crud.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.catalina.connector.Request;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.crud.entities.LoginBean;
import com.crud.entities.UsersBean;
import com.crud.servicesapi.UserService;
import com.crud.view.ExcelReportView;

@Controller
@SuppressWarnings("unused")
public class MainController {

	private static final Log LOG = LogFactory.getLog(MainController.class);
	
	@Autowired
	private UserService service;
	
	//Sign up
	@GetMapping("/signup")
	public ModelAndView viewSignup(Model model){
		model.addAttribute(new UsersBean());
		return new ModelAndView("signup");
	}
	
	@PostMapping("/signup")
	public String signup(@Validated UsersBean bean, BindingResult result) {

		if (result.hasErrors())
			return "signup";

		bean.setStatus(true);
		if (service.createOrUpdate(bean)) {
			return "redirect:/login?signup";
		}

		return "signup";
	}
	
	//Login
	@GetMapping(value = "/login")
	public ModelAndView viewLoginPage(@RequestParam(value = "error",required = false) String error,
	@RequestParam(value = "logout",	required = false) String logout,
	@RequestParam(value = "signup",	required = false) String signup) {
		
		ModelAndView model = new ModelAndView();
		if (error != null)
			model.addObject("error", "Invalid credentials provided.");

		if (logout != null)
			model.addObject("message", "Logged out successfully.");

		model.setViewName("login");
		return model;
	}
	
	//home page
	@GetMapping("/homePage")
	public ModelAndView homePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("homePage");
		return model;
	}
	
	//profile page
	@GetMapping("/myProfile")
	public ModelAndView myProfile() {
		ModelAndView model = new ModelAndView();
		model.setViewName("myProfile");
		return model;
	}
	
	//Access denied page
	@GetMapping("/accessDenied")
	public ModelAndView accessDenied() {
		ModelAndView model = new ModelAndView();
		model.setViewName("accessDenied");
		return model;
	}
	
	//Reports page
	@GetMapping("/reportsPage")
	public ModelAndView loadReportsPage() {
		ModelAndView mv = new ModelAndView("reportsPage");
		return mv;
	}
	
	//View users page
	@GetMapping("/viewUsers")
	public ModelAndView getViewUsersPage() {
		return new ModelAndView("viewUsers");
	}
	
	//View users page
		@GetMapping("/aboutUs")
		public ModelAndView aboutUs() {
			return new ModelAndView("aboutUs");
		}
	
	@GetMapping("/getAllUsers")
	public @ResponseBody Map<String, Object> getAllUsers() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<UsersBean> list = service.getAllUsers();
		map.put("status", "200");
		map.put("message", "Data found");
		map.put("data", list);
		return map;
	}
	
	//excel reports
	@GetMapping(value = "/Users_Details_report")
	public ModelAndView getExcelReport() {
		List<UsersBean> usersList = service.getAllUsers();
		return new ModelAndView(new ExcelReportView(), "usersList", usersList);
	}
	
	// Delete user
	@PostMapping("/deleteUser")
	public ResponseEntity<String> deleteUser(LoginBean bean) {

		if (service.deleteUser(bean)) {
			return ResponseEntity.ok("deleted");
		} else {
			return ResponseEntity.ok("error");
		}
	}
	
	//update user
	@PostMapping("/updateUser")
	public ResponseEntity<String> updateUser(UsersBean bean) {
		if(service.updateUser(bean))
			return ResponseEntity.ok("updated");
		return ResponseEntity.ok("error");
	}
	
	//suspend or activate user
	@PostMapping("toggleUserStatus")
	public ResponseEntity<String> toggleUserStatus(UsersBean bean){
		
		if(service.toggleUserStatus(bean))
			return ResponseEntity.ok("success");
		return ResponseEntity.ok("error");
	}
	
	@ResponseBody
	@GetMapping("getMyProfile")
	public UsersBean getProfile(){
		
		String email = ((UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal()).getUsername();
		
		return service.getUserByEmail(email);
	}
	
	@PostMapping("uploadPhotoSign")
	public ResponseEntity<String> savePhotoSign(
			@RequestParam MultipartFile photo, 
			@RequestParam MultipartFile sign, 
			int id) {

		UsersBean bean = new UsersBean();
		try {
			if (photo != null) {
				bean.setPhoto(photo.getBytes());
			}
			if (sign != null) {
				bean.setSign(sign.getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		bean.setId(id);
		if (service.savePhotoSign(bean)) {
			return ResponseEntity.ok("success");
		}
		return ResponseEntity.ok("error");
	}
}
