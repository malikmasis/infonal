package com.infonal.controller;


import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.infonal.model.User;
import com.mysql.jdbc.PreparedStatement;
 
@Controller
public class UserController {
	String message = "Welcome to Spring MVC!";
	String cekilenVeri=null;
	ArrayList<User> userList= new ArrayList<User>();
	
	static public void dbOpen() throws ClassNotFoundException, SQLException{
		   Class.forName("com.mysql.jdbc.Driver");
           String url = "jdbc:mysql://localhost:3306/infonal";
           String kullaniciad = "root";
           String sifre = "";
        
	}
	
	
	@RequestMapping("/")
	protected String redirect() 
	{
	    return "index";
	}
	
	
	
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller list");
		
	       
		try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/infonal";
            String kullaniciad = "root";
            String sifre = "";
            Connection con = null; Statement st = null; ResultSet rs = null;
            con = DriverManager.getConnection(url, kullaniciad, sifre);
            st = con.createStatement();
            
            //verileri sirala 
            String vericek = "SELECT name,surname,phone FROM user";
            rs = st.executeQuery(vericek);
            userList.clear();
            while(rs.next()) {
                System.out.println("Isim= "+rs.getString("name")+"  Soyisim= "+rs.getString("surname"));
                
                cekilenVeri=(rs.getString("name")+"  "+rs.getString("surname") + " " + rs.getString("phone"));
        		User user = new User();
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));	
                user.setPhone(rs.getString("phone"));
                userList.add(user);
        		
            }
            
           
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();        
           System.out.println("Surucu projeye eklenmemis!");
        } catch (SQLException ex) {
            ex.printStackTrace();
          System.out.println("Veritabanina baglanti saglanamadi!");
        }
		
		ModelAndView mv= new ModelAndView("list");
		mv.addObject("message", message);
		mv.addObject("name", name);
		mv.addObject("cekilenVeri", cekilenVeri);
		mv.addObject("userList" ,userList); 
		return mv;
		
		
	}



	//save
	
	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request, ModelMap model,
			
			@RequestParam(value = "name", required = false, defaultValue = "") String name, String surname, String phone ) {
		System.out.println("in controller save");
		
		

		 User user = new User();
		try {
            if(!name.equals("")){


            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/infonal";
            String kullaniciad = "root";
            String sifre = "";
            Connection con = null; Statement st = null; ResultSet rs = null;
            con = DriverManager.getConnection(url, kullaniciad, sifre);
            st = con.createStatement();
            System.out.println("in controller save1");
            //new user
           user.setName(name);
    		System.out.print("**" + user.getName());
    			 String sql = "INSERT INTO user (name, surname, phone)" +
    	                    "VALUES (?, ?, ?)";
    	            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
    	            preparedStatement.setString(1,name);
    	            preparedStatement.setString(2, surname);
    	            preparedStatement.setString(3, phone);
    	            preparedStatement.executeUpdate();

    	            System.out.println("in controller save2");
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
           System.out.println("Surucu projeye eklenmemis!");
        } catch (SQLException ex) {
            ex.printStackTrace();
          System.out.println("Veritabanina baglanti saglanamadi!");
        }


		ModelAndView mvr = new ModelAndView("save");
		 mvr.addObject("name",name);
		  mvr.addObject("surname",user.getSurname());
		mvr.addObject("phone",user.getPhone());
		/*mvr.addObject("userList",userList);*/

		return mvr;

	}

	//uptade
	@RequestMapping("/uptade/{name}/{surname}/{phone}")
	public ModelAndView uptade(HttpServletRequest request, ModelMap model,
			
			
			@PathVariable("name") String name,
			@PathVariable("surname") String surname,
			@PathVariable("phone") String phone,
			@RequestParam(value = "name", required = false)   String nameNew,
			@RequestParam(value = "surname", required = false)   String surnameNew,
			@RequestParam(value = "phone", required = false)   String phoneNew
			){
		System.out.println("in controller uptade");
		
		ArrayList<User> userList= new ArrayList<User>();
		User user= new User();
		try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/infonal";
            String kullaniciad = "root";
            String sifre = "";
            Connection con = null; 
            Statement st = null;
            ResultSet rs = null;
            con = DriverManager.getConnection(url, kullaniciad, sifre);
            st = con.createStatement();
            System.out.println("in controller uptade1");
            
            
            System.out.print("**"+ user.getName() + "***" + name);
            
            String query = "update user set name= ? , surname= ?     where phone=? ";
            PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
            
            preparedStmt.setString (1, nameNew);
            preparedStmt.setString (2, surnameNew);
            preparedStmt.setString (3,phoneNew );
            System.out.println("in controller uptade2");
            System.err.print(".." + name + " .." + surname + "!!" + user.getName() + " .." + nameNew + surnameNew);
            // execute the java preparedstatement
            preparedStmt.executeUpdate();
          
           
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();        
           System.out.println("Surucu projeye eklenmemis!");
        } catch (SQLException ex) {
            ex.printStackTrace();
          System.out.println("Veritabanina baglanti saglanamadi!");
        }
		
	    
		ModelAndView mvr = new ModelAndView("uptade");
        mvr.addObject("name",name);
        mvr.addObject("nameNew", nameNew);
        mvr.addObject("surnameNew", surnameNew);
        mvr.addObject("phoneNew", phoneNew);
		mvr.addObject("surname",surname);
		mvr.addObject("phone",phone);
		mvr.addObject("userList",userList);
		
		return mvr;
		
	}
	
	
	
	
	@RequestMapping("/list/{name}")
	public ModelAndView delete(HttpServletRequest request, ModelMap model,
			@PathVariable("name") String name,
			@RequestParam(value = "nameNew", required = false)   String nameNew
			){
		System.out.println("in controller delete");
		
		ArrayList<User> userList= new ArrayList<User>();
		User user= new User();
		try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/infonal";
            String kullaniciad = "root";
            String sifre = "";
            Connection con = null; 
            Statement st = null;
            ResultSet rs = null;
            con = DriverManager.getConnection(url, kullaniciad, sifre);
            st = con.createStatement();
            
            
            System.out.print("**"+ user.getName() + "***" + name +".." +nameNew );
            
            String query = "delete from user where name = ?";
            PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.execute();
            
               
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();        
           System.out.println("Surucu projeye eklenmemis!");
        } catch (SQLException ex) {
            ex.printStackTrace();
          System.out.println("Veritabanina baglanti saglanamadi!");
        }
		
		
		
		try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/infonal";
            String kullaniciad = "root";
            String sifre = "";
            Connection con = null; Statement st = null; ResultSet rs = null;
            con = DriverManager.getConnection(url, kullaniciad, sifre);
            st = con.createStatement();
            
            //verileri sirala 
            String vericek = "SELECT name,surname,phone FROM user";
            rs = st.executeQuery(vericek);
            userList.clear();
            while(rs.next()) {
                System.out.println("Isim= "+rs.getString("name")+"  Soyisim= "+rs.getString("surname"));
                
                cekilenVeri=(rs.getString("name")+"  "+rs.getString("surname") + " " + rs.getString("phone"));
        		User user1 = new User();
                user1.setName(rs.getString("name"));
                user1.setSurname(rs.getString("surname"));	
                user1.setPhone(rs.getString("phone"));
                userList.add(user1);
        		
            }
            
           
           
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();        
           System.out.println("Surucu projeye eklenmemis!");
        } catch (SQLException ex) {
            ex.printStackTrace();
          System.out.println("Veritabanina baglanti saglanamadi!");
        }
		
		
		
		
		
	    
		ModelAndView mvr = new ModelAndView("list");
        mvr.addObject("name",name);
		mvr.addObject("userList",userList);
		
		return mvr;
		
	}
	
	
	
	
	
}