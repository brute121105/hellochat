package org.liufeng.course.service;

import java.sql.Timestamp;

import dao.PhoneDao;
import mapping.PhoneCar;



public class ParsePhoneService {
	public String savePhone(String phone){
		String msg = "不是11位数";
		if(phone.trim().matches("\\d{11}")){
			PhoneDao dao = new PhoneDao();
			int size = dao.getOne(phone);
			msg = "存在";
			System.out.println("size:"+size);
			if(size==0){
				PhoneCar pc = new PhoneCar();
				pc.setC_time(new Timestamp(System.currentTimeMillis()));
				pc.setName("张黄2号");
				pc.setP_count(1);
				pc.setText("人车.....");
				pc.setPhone(phone);
				dao.insertOne(pc);
				msg="success";
			}
		}
		return msg;
	}

}
