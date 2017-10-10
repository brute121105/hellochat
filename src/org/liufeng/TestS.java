package org.liufeng;

import org.junit.After;
import org.junit.Test;
import org.liufeng.course.service.ParsePhoneService;

public class TestS {

	@Test
	public void test() {
		ParsePhoneService service = new ParsePhoneService();
		String s = service.savePhone("12345678912");
		System.out.println(s);
	}

}
