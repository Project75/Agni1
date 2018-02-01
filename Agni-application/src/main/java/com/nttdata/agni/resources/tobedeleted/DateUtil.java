package com.nttdata.agni.resources.tobedeleted;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

import ca.uhn.fhir.model.primitive.DateDt;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.util.DateUtils;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class DateUtil {
	Date dateJ;
	DateDt date;
	DateTimeDt dateTime;
	//ca.uhn.fhir.util.DateUtils DateUtils;
	
	public static void main(String[] args){
		String fhir="a.b.c";
		System.out.println("--"+fhir.split(Pattern.quote("."))[0]);
		//System.out.println("--"+DateUtils.parseDate("2017-02-08"));
		Date date= new Date();
		date.setDate(20170101);
		//date.setYear(2017);
		System.out.println("-1-"+DateUtils.formatDate(date));
		String[] dateFormats = {"yyyyMMdd"};
		//String str = (String) Optional.ofNullable(null).orElse("2017");
		String str = "abcd";
		Date date2= DateUtils.parseDate(str,new String[]{"yyyyMMdd"});
		System.out.println("-2-"+date2.toString());
		//new String[]{"yyyyMMdd"}
		Date date3= new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			
			date3 = formatter.parse("2017-10-10");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-2-"+date3.toString());
	}
	
	Date getDateJ(String input ){
		
		dateJ = DateUtils.parseDate("20170208",new String[]{"yyyyMMdd"});
		return dateJ;
	}
}
