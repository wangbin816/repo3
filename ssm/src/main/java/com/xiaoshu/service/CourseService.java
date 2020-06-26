package com.xiaoshu.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.CourseMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Course;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

	@Autowired(required = false)
	 private CourseMapper courseMapper;
	@Autowired(required = false)
	private RedisTemplate redisTemplate;

	// 查询所有
	public List<Course> getAll() throws Exception {
		return courseMapper.selectAll();
	};

//	// 数量
//	public int countUser(User t) throws Exception {
//		return userMapper.selectCount(t);
//	};
//
	// 通过ID查询
	public Course getCourse(Integer id) throws Exception {
		return courseMapper.selectByPrimaryKey(id);
	};


//
	// 新增
	public void addCourse(Course course){
		courseMapper.insert(course);
		List<Course> Course = courseMapper.selectAll();
		for (Course l:Course) {
			if (l.getName().equals(course.getName())){
				redisTemplate.boundHashOps(l.getId()+"").put(l.getId()+"",JSON.toJSONString(l));
			}
		}
	};
//
//	// 修改
//	public void updateUser(User t) throws Exception {
//		userMapper.updateByPrimaryKeySelective(t);
//	};
//
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
//
//	// 通过用户名判断是否存在，（新增时不能重名）
//	public User existUserWithUserName(String userName) throws Exception {
//		UserExample example = new UserExample();
//		Criteria criteria = example.createCriteria();
//		criteria.andUsernameEqualTo(userName);
//		List<User> userList = userMapper.selectByExample(example);
//		return userList.isEmpty()?null:userList.get(0);
//	};
//
//	// 通过角色判断是否存在
//	public User existUserWithRoleId(Integer roleId) throws Exception {
//		UserExample example = new UserExample();
//		Criteria criteria = example.createCriteria();
//		criteria.andRoleidEqualTo(roleId);
//		List<User> userList = userMapper.selectByExample(example);
//		return userList.isEmpty()?null:userList.get(0);
//	}
//
//	public PageInfo<User> findUserPage(User user, int pageNum, int pageSize, String ordername, String order) {
//		PageHelper.startPage(pageNum, pageSize);
//		ordername = StringUtil.isNotEmpty(ordername)?ordername:"userid";
//		order = StringUtil.isNotEmpty(order)?order:"desc";
//		UserExample example = new UserExample();
//		example.setOrderByClause(ordername+" "+order);
//		Criteria criteria = example.createCriteria();
//		if(StringUtil.isNotEmpty(user.getUsername())){
//			criteria.andUsernameLike("%"+user.getUsername()+"%");
//		}
//		if(user.getUsertype() != null){
//			criteria.andUsertypeEqualTo(user.getUsertype());
//		}
//		if(user.getRoleid() != null){
//			criteria.andRoleidEqualTo(user.getRoleid());
//		}
//		List<User> userList = userMapper.selectUserAndRoleByExample(example);
//		PageInfo<User> pageInfo = new PageInfo<User>(userList);
//		return pageInfo;
//	}


}
