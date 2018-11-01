package edu.sjsu.cmpe275.aop;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TweetStatsImpl implements TweetStats {
    /***
     * Following is a dummy implementation.
     * You are expected to provide an actual implementation based on the requirements.
     */
	
	
	public static int largestTweet = 0;
	
	public static Map<String, ArrayList<String>> followedUsr = new HashMap<String, ArrayList<String>>();
	public static Map<String, Integer> msgPop = new HashMap<String, Integer>();
	public static Map<String, Integer> prodUsr = new HashMap<String, Integer>();
	public static Map<String, ArrayList<String>> blockedUsr = new HashMap<String, ArrayList<String>>();
	

	@Override
	public void resetStatsAndSystem() {
		// TODO Auto-generated method stub
		largestTweet = 0;
		followedUsr.clear();
		msgPop.clear();
		prodUsr.clear();
		blockedUsr.clear();
	}
    
	@Override
	public int getLengthOfLongestTweet() {
		// TODO Auto-generated method stub
		return largestTweet;
	}

	@Override
	public String getMostFollowedUser() {
		// TODO Auto-generated method stub
		
		int maxFollowers = Integer.MIN_VALUE;
		
		for(Entry<String, ArrayList<String>> entry : followedUsr.entrySet()) {
				
			if(entry.getValue().size() > maxFollowers) maxFollowers = entry.getValue().size();
		}
		
		List<String> list = new ArrayList<String>();
		
		for(Entry<String, ArrayList<String>> entr : followedUsr.entrySet()) {
			if(Objects.equals(maxFollowers, entr.getValue().size())) {
				list.add(entr.getKey());
				Collections.sort(list);
			}
		}
		
		if(!list.isEmpty()) return list.get(0);
		else return null;
	}
	
	

	@Override
	public String getMostPopularMessage() {
		// TODO Auto-generated method stub
		int popMsg = Integer.MIN_VALUE;
		
		for(Entry<String, Integer> entry : msgPop.entrySet()) {
			if(entry.getValue() > popMsg) popMsg = entry.getValue();
		}
		
		List<String> list = new ArrayList<String>();
		
		for(Entry<String, Integer> entry : msgPop.entrySet()) {
			if(Objects.equals(popMsg, entry.getValue())) {
				list.add(entry.getKey());
				Collections.sort(list);
				//System.out.println(entry.getKey());
			}
		}
		
		if(!list.isEmpty()) return list.get(0);
		else return null;
	}
	
	
	
	@Override
	public String getMostProductiveUser() {
		// TODO Auto-generated method stub
		int prodMsg = Integer.MIN_VALUE;
		
		for(Entry<String, Integer> entr : prodUsr.entrySet()) {
			if(entr.getValue() > prodMsg) prodMsg = entr.getValue();
		}
		
		List<String> list = new ArrayList<String>();
		
		for(Entry<String, Integer> entry : prodUsr.entrySet()) {
			 if (Objects.equals(prodMsg, entry.getValue())) {
				 	
				 	list.add(entry.getKey());
		            Collections.sort(list);
				 
		        }
		}
		
		if(!list.isEmpty()) return list.get(0);
		else return null;
	}

	
	
	@Override
	public String getMostBlockedFollower() {
		// TODO Auto-generated method stub
		int blockedNum = Integer.MIN_VALUE;
		
		for(Entry<String, ArrayList<String>> entry : blockedUsr.entrySet()) {
			if(entry.getValue().size() > blockedNum) blockedNum = entry.getValue().size();
		}
		
		List<String> list = new ArrayList<String>();
		
		for(Entry<String, ArrayList<String>> entry : blockedUsr.entrySet()) {
			if(Objects.equals(entry.getValue().size(), blockedNum)) {
				
				list.add(entry.getKey());
				Collections.sort(list);
			}
		}
		
		if(!list.isEmpty()) return list.get(0);
		else return null;
	}
	
	
}



