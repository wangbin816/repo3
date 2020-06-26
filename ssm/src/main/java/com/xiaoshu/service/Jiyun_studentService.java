package com.xiaoshu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.Jiyun_studentMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.*;
import com.xiaoshu.entity.UserExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class Jiyun_studentService {

	@Autowired(required = false)
	Jiyun_studentMapper jiyun_studentMapper;
	@Autowired
	private CourseService courseService;

	// 查询所有
	public List<Jiyun_student> getAll() throws Exception {
		Example example = new Example(Jiyun_student.class);
		example.setOrderByClause("id desc");
		return jiyun_studentMapper.selectByExample(example);
	};

//	// 数量
//	public int countUser(User t) throws Exception {
//		return userMapper.selectCount(t);
//	};
//
//	// 通过ID查询
//	public User findOneUser(Integer id) throws Exception {
//		return userMapper.selectByPrimaryKey(id);
//	};

	// 新增
	public void addJiyun_student(Jiyun_student jiyun_student) throws Exception {
		jiyun_studentMapper.insert(jiyun_student);
	};

	// 修改
	public void updateJiyun_student(Jiyun_student jiyun_student) throws Exception {
		jiyun_studentMapper.updateByPrimaryKeySelective(jiyun_student);
	};

//	// 删除
//	public void deleteUser(Integer id) throws Exception {
//		userMapper.deleteByPrimaryKey(id);
//	};
//
//	// 登录
//	public User loginUser(User user) throws Exception {
//		UserExample example = new UserExample();
//		Criteria criteria = example.createCriteria();
//		criteria.andPasswordEqualTo(user.getPassword()).andUsernameEqualTo(user.getUsername());
//		List<User> userList = userMapper.selectByExample(example);
//		return userList.isEmpty()?null:userList.get(0);
//	};

	// 通过用户名判断是否存在，（新增时不能重名）
	public boolean existName(String name) throws Exception {
		List<Jiyun_student> all = jiyun_studentMapper.selectAll();
		for (Jiyun_student l:all) {
			if (l.getName().equals(name)){
				return false;
			}
		}
		return true;
	};

//	// 通过角色判断是否存在
//	public User existUserWithRoleId(Integer roleId) throws Exception {
//		UserExample example = new UserExample();
//		Criteria criteria = example.createCriteria();
//		criteria.andRoleidEqualTo(roleId);
//		List<User> userList = userMapper.selectByExample(example);
//		return userList.isEmpty()?null:userList.get(0);
//	}

	public PageInfo<Jiyun_student> findUserPage( int pageNum, int pageSize, String ordername, String order) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		ordername = StringUtil.isNotEmpty(ordername)?ordername:"id";
		order = StringUtil.isNotEmpty(order)?order:"desc";
		Example example = new Example(Jiyun_student.class);
		example.setOrderByClause(ordername+" "+order);
//		example.and().andLike("name","%"+sousuo.getName()+"%");
//		example.and().andLike("grade","%"+sousuo.getGrade()+"%");
		List<Jiyun_student> jiyun_students = jiyun_studentMapper.selectByExample(example);
		for (Jiyun_student l:jiyun_students) {
			Course course = courseService.getCourse(l.getCourse_id());
			l.setCourse(course);
		}
		PageInfo<Jiyun_student> pageInfo = new PageInfo<Jiyun_student>(jiyun_students);
		return pageInfo;
	}



}
