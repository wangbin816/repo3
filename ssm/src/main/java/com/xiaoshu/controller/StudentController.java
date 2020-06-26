package com.xiaoshu.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.config.util.ConfigUtil;
import com.xiaoshu.entity.*;
import com.xiaoshu.service.*;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.WriterUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("jiyunstudent")
public class StudentController {
	static Logger logger = Logger.getLogger(StudentController.class);

	@Autowired
	private Jiyun_studentService jiyun_studentService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private OperationService operationService;
	
	
	@RequestMapping("jiyunstudentIndex")
	public String index(HttpServletRequest request,Integer menuid) throws Exception{
		List<Course> courses = courseService.getAll();
		List<Operation> operationList = operationService.findOperationIdsByMenuid(menuid);
		request.setAttribute("operationList", operationList);
		request.setAttribute("courses", courses);
		return "student";
	}
	
	
	@RequestMapping(value="jiyunstudentList",method=RequestMethod.POST)
	public void studentList(HttpServletRequest request, HttpServletResponse response, String offset, String limit) throws Exception{
		try {
			String order = request.getParameter("order");
			String ordername = request.getParameter("ordername");
			Integer pageSize = StringUtil.isEmpty(limit)?ConfigUtil.getPageSize():Integer.parseInt(limit);
			Integer pageNum =  (Integer.parseInt(offset)/pageSize)+1;
			PageInfo<Jiyun_student> pageInfo = jiyun_studentService.findUserPage(pageNum, pageSize, ordername, order);
			
//			request.setAttribute("username", username);
//			request.setAttribute("roleid", roleid);
//			request.setAttribute("usertype", usertype);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("total",pageInfo.getTotal() );
			jsonObj.put("rows", pageInfo.getList());
	        WriterUtil.write(response,jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户展示错误",e);
			throw e;
		}
	}
	
	
	// 新增或修改
	@RequestMapping("reserveStudent")
	public void reserveUser(HttpServletRequest request,Jiyun_student jiyun_student,HttpServletResponse response){
		Integer id = jiyun_student.getId();
//		Integer userId = user.getUserid();
		jiyun_student.setCreatetime(new Date());
		JSONObject result=new JSONObject();
		try {
			if (id != null) {   // userId不为空 说明是修改

					jiyun_studentService.updateJiyun_student(jiyun_student);
					result.put("success", true);

			}else {   // 添加
				if(jiyun_studentService.existName(jiyun_student.getName())){  // 没有重复可以添加
					jiyun_studentService.addJiyun_student(jiyun_student);
					result.put("success", true);
				} else {
					result.put("success", true);
					result.put("errorMsg", "该用户名被使用");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存用户信息错误",e);
			result.put("success", true);
			result.put("errorMsg", "对不起，操作失败");
		}
		WriterUtil.write(response, result.toString());
	}

//
//	@RequestMapping("deleteUser")
//	public void delUser(HttpServletRequest request,HttpServletResponse response){
//		JSONObject result=new JSONObject();
//		try {
//			String[] ids=request.getParameter("ids").split(",");
//			for (String id : ids) {
//				userService.deleteUser(Integer.parseInt(id));
//			}
//			result.put("success", true);
//			result.put("delNums", ids.length);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("删除用户信息错误",e);
//			result.put("errorMsg", "对不起，删除失败");
//		}
//		WriterUtil.write(response, result.toString());
//	}
//
//	@RequestMapping("editPassword")
//	public void editPassword(HttpServletRequest request,HttpServletResponse response){
//		JSONObject result=new JSONObject();
//		String oldpassword = request.getParameter("oldpassword");
//		String newpassword = request.getParameter("newpassword");
//		HttpSession session = request.getSession();
//		User currentUser = (User) session.getAttribute("currentUser");
//		if(currentUser.getPassword().equals(oldpassword)){
//			User user = new User();
//			user.setUserid(currentUser.getUserid());
//			user.setPassword(newpassword);
//			try {
//				userService.updateUser(user);
//				currentUser.setPassword(newpassword);
//				session.removeAttribute("currentUser");
//				session.setAttribute("currentUser", currentUser);
//				result.put("success", true);
//			} catch (Exception e) {
//				e.printStackTrace();
//				logger.error("修改密码错误",e);
//				result.put("errorMsg", "对不起，修改密码失败");
//			}
//		}else{
//			logger.error(currentUser.getUsername()+"修改密码时原密码输入错误！");
//			result.put("errorMsg", "对不起，原密码输入错误！");
//		}
//		WriterUtil.write(response, result.toString());
//	}
	@RequestMapping("addCourse")
	public String addCourse(Course course){
		course.setCreatetime(new Date());
		courseService.addCourse(course);
		return "redirect:jiyunstudentIndex.htm?menuid=12";
	}
}
