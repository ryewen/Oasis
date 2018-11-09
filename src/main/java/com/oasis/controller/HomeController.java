package com.oasis.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oasis.dao.HistoryDAO;
import com.oasis.dao.IdentityDAO;
import com.oasis.model.Book;
import com.oasis.model.History;
import com.oasis.model.HistoryCode;
import com.oasis.model.User;
import com.oasis.service.FileService;
import com.oasis.service.HistoryService;
import com.oasis.service.UserService;

@Controller
public class HomeController {
	
	public static final String USER_SESSION = "user";
	
	private static String DIR_PATH = "";
	
	static {
		if (System.getProperty("os.name").toLowerCase().indexOf("linux") >= 0) {
			DIR_PATH = "/home/novel/";
		} else {
			DIR_PATH = "D:/files/";
		}
	}
	
	private static final int DOGCOM_STATUS_RUNNING = 1;
	
	private static final int DOGCOM_STATUS_CLOSING = 0;
	
	private static final int DOGCOM_STATUS_STOP = -1;
	
	private static int DOGCOM_STATUS = DOGCOM_STATUS_STOP; //Running Closing Stop 

	@Autowired
	UserService userService;
	
	@Autowired
	HistoryService historyService;
	
	@Autowired
	IdentityDAO identityDAO;
	
	@Autowired
	HistoryDAO historyDAO;
	
	@RequestMapping(value = {"/", "/home"})
	@ResponseBody
	public ModelAndView home(HttpServletRequest request) {
		if (! userService.ifLogined(request)) return new ModelAndView("redirect:/login");
		int admin = 0;
		if (((User) request.getSession().getAttribute(USER_SESSION)).getAuthority().equals(User.AUTH_ADMIN)) admin = 1;
		ModelAndView model = new ModelAndView("/home");
		model.addObject("username", ((User) request.getSession().getAttribute(USER_SESSION)).getUsername());
		model.addObject("admin", admin);
		return model;
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public ModelAndView loginView() {
		return new ModelAndView("/login");
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public ModelAndView logout(HttpServletRequest request) {
		if (! userService.ifLogined(request)) return new ModelAndView("redirect:/login");
		request.getSession().removeAttribute(USER_SESSION);
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(value = "/loginForm", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView login(HttpServletRequest request, String username, String password) {
		if (username == null || username.equals(" ") || password == null || password.equals(" ")) return new ModelAndView("/error");
		User user = identityDAO.queryUser(username);
		if (user == null) return new ModelAndView("/error");
		if (! user.getPassword().equals(password)) return new ModelAndView("/error");
		request.getSession().setAttribute(USER_SESSION, user);
		request.getSession().setMaxInactiveInterval(0);
		return new ModelAndView("redirect:/home");
	}
	
	@RequestMapping(value = "/upload")
	@ResponseBody
	public ModelAndView uploadView(HttpServletRequest request) {
		if (! userService.ifLogined(request)) return new ModelAndView("redirect:/login");
		return new ModelAndView("/upload");
	}
	
	@RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView recvFile(HttpServletRequest request, @RequestParam("file") MultipartFile file, String filename) {
		if (! userService.ifLogined(request)) return new ModelAndView("redirect:/login");
		if (!file.isEmpty()) {
			File dir = new File(DIR_PATH);
			if (! dir.exists()) dir.mkdir();
			if (filename == null || filename.equals("")) filename = file.getOriginalFilename();
			if (filename.indexOf(".txt") == -1) filename += ".txt";
			String filePath = DIR_PATH + filename;
			System.out.println(filePath);
			if (new File(filePath).exists()) return new ModelAndView("/error");
			try {
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				out.write(file.getBytes());
				out.flush();
				out.close();
				String txtDirPath = DIR_PATH + filename.split(".txt")[0];
				File txtDir = new File(txtDirPath);
				if (txtDir.exists()) txtDir.delete();
				txtDir.mkdir();
				String code = FileService.getFileCharset(filePath);
		        System.out.println(code);
				BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), code));
				int i = 0;
				int rows = 80;
				int index = 1;
				String line = bfr.readLine();
				BufferedWriter bfw = new BufferedWriter(new FileWriter(txtDirPath + "/" + index + ".txt"));
				while (line != null) {
					//System.out.println(line);
					bfw.write(line);
					bfw.newLine();
					if (++ i >= rows) {
						bfw.flush();
						bfw.close();
						i = 0;
						index ++;
						bfw = new BufferedWriter(new FileWriter(txtDirPath + "/" + index + ".txt"));
					}
					line = bfr.readLine();
				}
				if (i != 0) {
					bfw.flush();
					bfw.close();
				}
				bfr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new ModelAndView("/success");
	}
	
	@RequestMapping(value = "/viewAll")
	@ResponseBody
	public ModelAndView viewAll(HttpServletRequest request) {
		if (! userService.ifLogined(request)) return new ModelAndView("redirect:/login");
		File dir = new File(DIR_PATH);
		if (! dir.exists()) return new ModelAndView("/error");
		File[] txtFiles = dir.listFiles();
		List<Book> books = new ArrayList<Book>();
		for (File file : txtFiles) {
			if (file.isDirectory()) {
				String bookname = file.getName();
				books.add(new Book(bookname, FileService.base64Encode(bookname)));
			}
		}
		if (books.size() == 0) return new ModelAndView("/error");
		ModelAndView model = new ModelAndView("/viewAll");
		model.addObject("amount", books.size());
		model.addObject("books", books);
		return model;
	}
	
	@RequestMapping(value = "/book")
	@ResponseBody
	public ModelAndView book(HttpServletRequest request, String bookcode) {
		if (! userService.ifLogined(request)) return new ModelAndView("redirect:/login");
		if (bookcode == null) return new ModelAndView("/error");
		if (bookcode == "") return new ModelAndView("/error");
		File dir = new File(DIR_PATH);
		if (! dir.exists()) return new ModelAndView("/error");
		String bookname = FileService.base64Decode(bookcode);
		File[] txtFiles = dir.listFiles();
		boolean bookExist = false;
		for (File file : txtFiles) if (file.isDirectory()) if (file.getName().equals(bookname)) bookExist = true;
		if (! bookExist) return new ModelAndView("/error");
		ModelAndView model = new ModelAndView("/book");
		model.addObject("bookname", bookname);
		model.addObject("bookcode", bookcode);
		File[] files = new File(DIR_PATH + bookname).listFiles();
		if (files.length == 0) return new ModelAndView("/error");
		List<Integer> txtNums = new ArrayList<Integer>();
		for (File file : files) txtNums.add(Integer.valueOf(file.getName().split(".txt")[0]));
		Collections.sort(txtNums);
		List<List<Integer>> numRows = new ArrayList<List<Integer>>();
		int rowSize = 5;
		int i = 0;
		List<Integer> numRow = null;
		Iterator<Integer> it = txtNums.iterator();
		while (it.hasNext()) {
			if (i == 0) {
				numRow = new ArrayList<Integer>();
				numRows.add(numRow);
			}
			numRow.add((Integer) it.next());
			if (++ i >= rowSize) i = 0;
		}
		model.addObject("numRows", numRows);
		return model;
	}
	
	@RequestMapping(value = "/viewBook")
	@ResponseBody
	public ModelAndView viewBook(HttpServletRequest request, String bookcode, int index) {
		if (! userService.ifLogined(request)) return new ModelAndView("redirect:/login");
		if (bookcode == null) return new ModelAndView("/error1");
		if (bookcode == "") return new ModelAndView("/error2");
		String bookname = FileService.base64Decode(bookcode);
		if (index <= 0) return new ModelAndView("/error3");
		if (! new File(DIR_PATH + bookname).exists()) return new ModelAndView("/error4");
		int indexs = new File(DIR_PATH + bookname).listFiles().length;
		if (index > indexs) return new ModelAndView("/error5");
		File txt = new File(DIR_PATH + bookname + "/" + index + ".txt");
		if (! txt.exists()) return new ModelAndView("/error6");
		String text = "";
		try {
			BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(txt), "utf-8"));
			String str = null;
			while ((str = bfr.readLine()) != null) {
				if (str.equals("") || str.equals("　")) continue;
				if (str.length() >= 2 && str.substring(0, 2).equals("　　")) {
					text += "<p>";
				} else {
					text += "<p id='text'>";
				}
				text += str;
				text += "</p>";
			}
			bfr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (text == "") return new ModelAndView("/error7");
		ModelAndView model = new ModelAndView("viewBook");
		model.addObject("bookname", bookname);
		model.addObject("bookcode", bookcode);
		model.addObject("index", index);
		model.addObject("text", text);
		int left = 0;
		if (index == 1)
			left = -1;
		else
			left = index - 1;
		int right = 0;
		if (index == indexs)
			right = -1;
		else
			right = index + 1;
		model.addObject("left", left);
		model.addObject("right", right);
		historyService.updateHistory(request, bookname, index);
		return model;
	}
	
	@RequestMapping(value = "/history")
	@ResponseBody
	public ModelAndView history(HttpServletRequest request) {
		if (! userService.ifLogined(request)) return new ModelAndView("redirect:/login");
		String username = ((User) request.getSession().getAttribute(HomeController.USER_SESSION)).getUsername();
		List<History> histories = historyDAO.queryHistoriesByTime(username);
		if (histories == null) return new ModelAndView("/error");
		List<HistoryCode> historyCodes = new ArrayList<HistoryCode>();
		Iterator<History> it = histories.iterator();
		while (it.hasNext()) {
			History history = (History) it.next();
			historyCodes.add(new HistoryCode(history, FileService.base64Encode(history.getBookname())));
		}
		ModelAndView model = new ModelAndView("/history");
		model.addObject("username", username);
		model.addObject("histories", historyCodes);
		model.addObject("amount", histories.size());
		return model;
	}
	
	@RequestMapping(value = "/createUser")
	@ResponseBody
	public ModelAndView createUser(HttpServletRequest request) {
		if (! userService.ifLogined(request)) return new ModelAndView("redirect:/login");
		if (! ((User) request.getSession().getAttribute(USER_SESSION)).getAuthority().equals(User.AUTH_ADMIN)) return new ModelAndView("/error");
		return new ModelAndView("/createUser");
	}
	
	@RequestMapping(value = "/createUserForm", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView recvCreateUser(HttpServletRequest request, String username, String password, String authority) {
		if (username == null) return new ModelAndView("/error");
		if (username.equals("")) return new ModelAndView("/error");
		if (password == null) return new ModelAndView("/error");
		if (password.equals("")) return new ModelAndView("/error");
		if (authority == null) return new ModelAndView("/error");
		if (authority.equals("")) return new ModelAndView("/error");
		if (! authority.equals("admin") && ! authority.equals("user")) return new ModelAndView("/error");
		if (! userService.ifLogined(request)) return new ModelAndView("redirect:/login");
		if (! ((User) request.getSession().getAttribute(USER_SESSION)).getAuthority().equals(User.AUTH_ADMIN)) return new ModelAndView("/error");
		if (authority.equals("admin")) authority = User.AUTH_ADMIN;
		if (authority.equals("user")) authority = User.AUTH_USER;
		User user = identityDAO.queryUser(username);
		if (user == null)
			identityDAO.insertUser(username, password, authority);
		else
			return new ModelAndView("/error");
		return new ModelAndView("/success");
	}
	
	@RequestMapping(value = "/dogcomStatus")
	@ResponseBody
	public ModelAndView dogcomStatus(HttpServletRequest request) {
		if (! userService.ifLogined(request)) return new ModelAndView("redirect:/login");
		if (! ((User) request.getSession().getAttribute(USER_SESSION)).getAuthority().equals(User.AUTH_ADMIN)) return new ModelAndView("/error");
		ModelAndView model = new ModelAndView("/dogcomStatus");
		String status = "";
		switch (this.DOGCOM_STATUS) {
			case 1:
				status = "Running";
				break;
			case 0:
				status = "Closing";
				break;
			case -1:
				status = "Stop";
				break;
			default:
				status = "Error";
		}
		model.addObject("status", status);
		return model;
	}
	
	@RequestMapping(value = "/runningDogcom")
	@ResponseBody
	public String runningDogcom(HttpServletRequest request, String username, String password) {
		if (username == null || password == null) return "error";
		if (username.equals("admin") && password.equals("123456")) {
			this.DOGCOM_STATUS = this.DOGCOM_STATUS_RUNNING;
			return "success";
		}
		return "error";
	}
	
	@RequestMapping(value = "/closeDogcom")
	@ResponseBody
	public ModelAndView closeDogcom(HttpServletRequest request) {
		if (! userService.ifLogined(request)) return new ModelAndView("redirect:/login");
		if (! ((User) request.getSession().getAttribute(USER_SESSION)).getAuthority().equals(User.AUTH_ADMIN)) return new ModelAndView("/error");
		this.DOGCOM_STATUS = this.DOGCOM_STATUS_CLOSING;
		System.out.println(this.DOGCOM_STATUS);
		return new ModelAndView("redirect:/home");
	}
	
	@RequestMapping(value = "/ifCloseDogcom")
	@ResponseBody
	public int ifCloseDogcom(HttpServletRequest request, String username, String password) {
		if (username == null || password == null) return -1;
		if (username.equals("admin") && password.equals("123456")) {
			return 1 - this.DOGCOM_STATUS;
		}
		return -1;
	}
	
	@RequestMapping(value = "/closedDogcom")
	@ResponseBody
	public String closedDogcom(HttpServletRequest request, String username, String password) {
		if (username == null || password == null) return "error";
		if (username.equals("admin") && password.equals("123456")) {
			this.DOGCOM_STATUS = this.DOGCOM_STATUS_STOP;
			return "success";
		}
		return "error";
	}
}
