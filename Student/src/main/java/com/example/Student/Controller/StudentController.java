package com.example.Student.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Student.Model.Student;
import com.example.Student.Service.StudentService;
import com.google.gson.Gson;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentServices;
	
	@GetMapping("/")
	public String viewstu(Model model) {
		
		
		Student student = new Student();
		
		model.addAttribute("student", student);
		return "index";
	}
	
	@ResponseBody
	@GetMapping("/home")
	public String viewHomePage(Model model)
	{
		
		model.addAttribute("listStudents", studentServices.findall());
		Gson gson = new Gson();


		String json = gson.toJson(studentServices.findall());

		//String json = gson.toJson(studentServices.getStudentById(11));
		
		System.out.println(json);
		return json;

	}
	
	@ResponseBody
	@RequestMapping(value = "/register" ,method = RequestMethod.POST)
	public String register(Model model,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws ParseException {
		if(httpServletRequest.getParameter("id") == null){
			String date =httpServletRequest.getParameter("birthdate");
			Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(date); 
		
			Student addstu = new Student();
			addstu.setFirstname(httpServletRequest.getParameter("firstname"));
			addstu.setLastname(httpServletRequest.getParameter("lastname"));
			addstu.setMobileno(httpServletRequest.getParameter("mobileno"));
			addstu.setBirthdate(date1);
			studentServices.saveOrUpdate(addstu);
			model.addAttribute("listStudents", studentServices.findall());
			
			
			Gson gson = new Gson();

			String json = gson.toJson(studentServices.findall());

			
			System.out.println(json);
			return json;
		}else {
			String date =httpServletRequest.getParameter("birthdate");
			Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(date); 
		
			Student addstu = studentServices.findbyId(Long.parseLong(httpServletRequest.getParameter("id")));
			addstu.setFirstname(httpServletRequest.getParameter("firstname"));
			addstu.setLastname(httpServletRequest.getParameter("lastname"));
			addstu.setMobileno(httpServletRequest.getParameter("mobileno"));
			addstu.setBirthdate(date1);
			studentServices.saveOrUpdate(addstu);
			model.addAttribute("listStudents", studentServices.findall());
			
			
			Gson gson = new Gson();

			String json = gson.toJson(studentServices.findall());

			System.out.println(json);
			return json;
		}
		}
	@ResponseBody
	@GetMapping("/showupdate/{id}")
	public String showupdate(@PathVariable(value = "id" )long id ,Model model)
	{
		System.out.println("hiii");
		Student studentModal = studentServices.findbyId(id);

		model.addAttribute("student" , studentModal);
		Gson gson = new Gson();

		String json = gson.toJson(studentModal);
		
		return json;

		
	}
	
	@ResponseBody
	@GetMapping("/delete/{id}")
	public String deletestudent(@PathVariable (value = "id") long id,Model model) {
		studentServices.deleteById(id);

		model.addAttribute("listStudents", studentServices.findall());
		
		
		Gson gson = new Gson();

		String json = gson.toJson(studentServices.findall());

		System.out.println(json);
		return json; 	
	}

}
