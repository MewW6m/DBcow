package com.dbcow.config;

import java.util.HashMap;
import java.util.Map;

public class Constant {
  
  public static enum TT {
	  
	  t1(1, "1", "x","x","01","01","01"),
	  t2(13, "2", "x","x","02","01","02");

	  public final Integer r1; // 
	  public final String r2; // 
	  public final String r3; // 
	  public final String r4; // 
	  public final String r5; // 
	  public final String r6; // 
	  public final String r7; // 
	  
	  private TT(Integer r1, String r2, String r3, String r4, String r5, String r6, String r7) {
		  this.r1 = r1;
		  this.r2 = r2;
		  this.r3 = r3;
		  this.r4 = r4;
		  this.r5 = r5;
		  this.r6 = r6;
		  this.r7 = r7;
	  }
	  
	  
	  public static TT getKaiinType(Integer id) {
		  for (TT tt : TT.values()) {
	            if (tt.r1.equals(id)) {
	                return tt;
	            }
	        }
	      return null;
	  }
	  
	  public static Map<Integer, String> getTT() {
		  Map ttmap = new HashMap<Integer, String>();
		  for (TT tt1 : TT.values()) {
			ttmap.put(tt1.r1, tt1.r2);
	      }
		  return ttmap;
	  }
  }
}