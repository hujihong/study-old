package com.github.im.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class ShiroTest {

	public static void main(String[] args) {
		
		Factory<org.apache.shiro.mgt.SecurityManager> f = new IniSecurityManagerFactory("classpath:testShiro.ini");
		org.apache.shiro.mgt.SecurityManager s = (org.apache.shiro.mgt.SecurityManager)f.getInstance();
		SecurityUtils.setSecurityManager(s);
		UsernamePasswordToken token = new UsernamePasswordToken("javass", "cc");
		token.setRememberMe(true);
		
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.login(token);
		
		// boolean flag = currentUser.isPermitted("p1");
		boolean flag = currentUser.hasRole("role1");
		System.out.println("flag=" + flag);
		
		
		String str = new Sha256Hash("cc").toHex();
		System.out.println(str);
		
	
		
		
		
		
		
		

	}

}
