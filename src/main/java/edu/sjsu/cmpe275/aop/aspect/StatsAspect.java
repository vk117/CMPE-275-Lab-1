package edu.sjsu.cmpe275.aop.aspect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import edu.sjsu.cmpe275.aop.TweetStatsImpl;

@Aspect
@Order(0)
public class StatsAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     */

	@Autowired TweetStatsImpl stats;
	
	/*@After("execution(public void edu.sjsu.cmpe275.aop.TweetService.tweet(..))")
	public void dummyAfterAdvice(JoinPoint joinPoint) {
		System.out.printf("After the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		//stats.resetStats();
	}
	
	@Before("execution(public void edu.sjsu.cmpe275.aop.TweetService.follow(..))")
	public void dummyBeforeAdvice(JoinPoint joinPoint) {
		System.out.printf("Before the executuion of the metohd %s\n", joinPoint.getSignature().getName());
	}*/
	
	@AfterReturning("execution(public void edu.sjsu.cmpe275.aop.TweetService.tweet(..))")
	public void longTweet(JoinPoint joinPoint) {
		
		String user = (String) joinPoint.getArgs()[0];
		String msg = (String) joinPoint.getArgs()[1];
		int len;
		
		if(msg!=null) {
			len = msg.length();
			if(len > TweetStatsImpl.largestTweet) TweetStatsImpl.largestTweet = len;
		}
		
		if((user != null && !user.isEmpty() && msg!=null && !msg.isEmpty()) && RetryAspect.depleted!=true) {
			len = msg.length();
			if(len <= 140) {
				if(TweetStatsImpl.prodUsr.containsKey(user)) {
					TweetStatsImpl.prodUsr.put(user, TweetStatsImpl.prodUsr.get(user) + len);
				}
				else {
					TweetStatsImpl.prodUsr.put(user, len);
				}
				
				List<String> realFollowers = whoToSend(user);
				
				if(realFollowers != null)
				TweetStatsImpl.msgPop.put(msg, realFollowers.size());
				
				
			}
		
		}
		
		RetryAspect.depleted = false;
	}
	
	@AfterReturning("execution(public void edu.sjsu.cmpe275.aop.TweetService.follow(..))")
	public void followStats(JoinPoint joinPoint) {
		
		String follower = (String) joinPoint.getArgs()[0];
		String followee = (String) joinPoint.getArgs()[1];
		
		if(((follower != null && !follower.isEmpty()) && (followee != null && !followee.isEmpty())) && RetryAspect.depleted!=true) {
			if(TweetStatsImpl.followedUsr.containsKey(followee)) {
				
				ArrayList<String> list = new ArrayList<String>();
				list = TweetStatsImpl.followedUsr.get(followee);
				if(!list.contains(follower)) list.add(follower);
				TweetStatsImpl.followedUsr.put(followee, list);
			}
			else {
				TweetStatsImpl.followedUsr.put(followee, new ArrayList<String>(Arrays.asList(follower)));
			}
		}
		
		if(RetryAspect.depleted == true) RetryAspect.depleted = false;
	}
	
	
	@AfterReturning("execution(public void edu.sjsu.cmpe275.aop.TweetService.block(..))")
	public void blockStats(JoinPoint joinPoint) {
		
		String blocker = (String) joinPoint.getArgs()[0];
		String blocked = (String) joinPoint.getArgs()[1];
		
		if(((blocker != null && !blocker.isEmpty()) && (blocked != null && !blocked.isEmpty())) && RetryAspect.depleted!=true) {
			if(TweetStatsImpl.blockedUsr.containsKey(blocked)) {
				
				ArrayList<String> list = new ArrayList<String>();
				list = TweetStatsImpl.blockedUsr.get(blocked);
				if(!list.contains(blocker)) list.add(blocker);
				TweetStatsImpl.blockedUsr.put(blocked, list);
			}
			else {
				TweetStatsImpl.blockedUsr.put(blocked, new ArrayList<String>(Arrays.asList(blocker)));
			}
		}
		
		if(RetryAspect.depleted == true) RetryAspect.depleted = false;
	
	}
	
	
	public List<String> whoToSend(String tweeter) {
		
		List<String> a = new ArrayList<String>();
		a = TweetStatsImpl.followedUsr.get(tweeter);
		
		List<String> b = new ArrayList<String>();
		for(Entry<String, ArrayList<String>> entry : TweetStatsImpl.blockedUsr.entrySet()) {
			
			if(entry.getValue().contains(tweeter)) {
				b.add(entry.getKey());
			}
		}
		
		if(a != null && b != null)
		a.removeAll(b);
		
		return a;
	}
	
}
