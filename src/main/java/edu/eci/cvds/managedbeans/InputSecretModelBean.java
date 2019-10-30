package edu.eci.cvds.managedbeans;

import java.sql.Date;
import java.util.GregorianCalendar;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
@ManagedBean
@RequestScoped
public class InputSecretModelBean {
	private Date date = (Date) new GregorianCalendar().getTime();
	private String password;
 
	public Date getDate() {
		return date;
	}
 
	public String getPassword() {
		return password;
	}
 
	public void setDate(Date date) {
		this.date = date;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
}
