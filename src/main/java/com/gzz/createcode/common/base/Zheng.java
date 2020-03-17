package com.gzz.createcode.common.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zheng {
	
	public static void main(String[] args) {
		
		String str="[MyGirl美媛馆]2020.01.22 VOL.429 悠悠酱yoyoyo [50+1P]";
		
		
		Matcher matcher =  Pattern.compile("(.*OL.)(\\d{3})(\\s+.*)").matcher(str);
     
 
        //因此可以改写为
        if (matcher.matches()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));
        }
		
	}

}
