package edu.sjsu.cmpe275.aop;

import java.io.IOException;

public class TweetServiceImpl implements TweetService {

    /***
     * Following is a dummy implementation.
     * You can tweak the implementation to suit your need, but this file is NOT part of the submission.
     */
	
	public static int counter = 0;

	@Override
    public void tweet(String user, String message) throws IllegalArgumentException, IOException {
    	
		if(counter-- > 0) throw new IOException();
		if(message == null || message.isEmpty() || message.length()>140 || user == null || user.isEmpty()) throw new IllegalArgumentException();
		
		System.out.printf("User %s tweeted message: %s\n", user, message);
    }

	@Override
    public void follow(String follower, String followee) throws IOException {
		
		if(counter-- > 0) throw new IOException();
		if(follower == null || follower.isEmpty() || followee == null || followee.isEmpty() || follower.equals(followee)) throw new IllegalArgumentException();
		
       	System.out.printf("User %s followed user %s \n", follower, followee);
    }

	@Override
	public void block(String user, String follower) throws IOException {
		
		if(counter-- > 0) throw new IOException();
		if(follower == null || follower.isEmpty() || user == null || user.isEmpty() || user.equals(follower)) throw new IllegalArgumentException();
		
       	System.out.printf("User %s blocked user %s \n", user, follower);		
	}

}
