package com.panzhenchao.redis.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.panzhenchao.entity.User;
import com.pzc.common.utils.RandomUtil;
import com.pzc.common.utils.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-redis.xml")
public class Test {
	
	@Resource
	private RedisTemplate redisTemplate;
	
	@org.junit.Test
	public void testJDK() {
		//创建集合
		List<User> list_jdk = new ArrayList<User>();
		
		//循环遍历
		for (int i = 1; i <= 50000; i++) {
			//name随机姓名
			String name = StringUtil.generateChineseName();
			//随机性别
			String sex = "男";
			//随机手机号
			String phone = "13" + RandomUtil.randomNumber(9);
			//随机邮箱
			String emaill = RandomUtil.randomNumber(9) + "qq.com";
			//随机时间
			Date birthday = new Date();
			//创建对象
			User user = new User();
			user.setId(i);
			user.setName(name);
			user.setSex(sex);
			user.setPhone(phone);
			user.setEmaill(emaill);
			user.setBirthday(birthday);
			//存入对象
			list_jdk.add(user);
		}
		
		ListOperations opsForList = redisTemplate.opsForList();
		//开始的时间
		long start = System.currentTimeMillis();
		//存入redis
		opsForList.leftPush("list_jdk", list_jdk);
		//结束的时间
		long end = System.currentTimeMillis();
		//结束的时间减去开始的时间等于一共用的时间
		System.out.println("jdk总共用时"+(end-start)+"毫秒");
	}
	
	@org.junit.Test
	public void testjson() {
		//創建集合
		List<User> list_json = new ArrayList<User>();
		
		for (int i = 1; i <= 5; i++) {
			//name随机姓名
			String name = StringUtil.generateChineseName();
			//随机性别
			String sex = "男";
			//随机手机号
			String phone = "13" + RandomUtil.randomNumber(9);
			//随机邮箱
			String emaill = RandomUtil.randomNumber(9) + "qq.com";
			//随机时间
			Date birthday = new Date();
			//创建对象
			User user = new User();
			user.setId(i);
			user.setName(name);
			user.setSex(sex);
			user.setPhone(phone);
			user.setEmaill(emaill);
			user.setBirthday(birthday);
			//存入对象
			list_json.add(user);
		}
		ListOperations opsForList = redisTemplate.opsForList();
		
		long start = System.currentTimeMillis();
		
		opsForList.leftPush("list_json", list_json);
		
		long end = System.currentTimeMillis();
		//结束的时间减去开始的时间等于一共的时间
		System.out.println("jdk总共用时"+(end-start)+"毫秒");
	}
	
	
	@org.junit.Test
	public void testhash() {
		Map<String,User> map = new HashMap<String, User>();
		
		for (int i = 1; i <= 5; i++) {
			//name随机姓名
			String name = StringUtil.generateChineseName();
			//随机性别
			String sex = "男";
			//随机手机号
			String phone = "13" + RandomUtil.randomNumber(9);
			//随机邮箱
			String emaill = RandomUtil.randomNumber(9) + "qq.com";
			//随机时间
			Date birthday = new Date();
			//创建对象
			User user = new User();
			user.setId(i);
			user.setName(name);
			user.setSex(sex);
			user.setPhone(phone);
			user.setEmaill(emaill);
			user.setBirthday(birthday);
			//存入对象
			map.put(i+"", user);
		}
		ListOperations opsForList = redisTemplate.opsForList();
		
		long start = System.currentTimeMillis();
		//存入Redis
		opsForList.leftPush("hashmap", map);
		
		long end = System.currentTimeMillis();
		//结束的时间减去开始的时间等于一共的时间
		System.out.println("jdk总共用时"+(end-start)+"毫秒");
	}
}
