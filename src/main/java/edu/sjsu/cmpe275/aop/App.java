package edu.sjsu.cmpe275.aop;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App {
    public static void main(String[] args) {
        /***
         * Following is a dummy implementation of App to demonstrate bean creation with Application context.
         * You may make changes to suit your need, but this file is NOT part of the submission.
         */

    	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        TweetService tweeter = (TweetService) ctx.getBean("tweetService");
        TweetStats stats = (TweetStats) ctx.getBean("tweetStats");

        try {
        	tweeter.tweet("Varun", "Hello");
        	tweeter.tweet("Tarun", "Hable");
        	System.out.println(stats.getMostPopularMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*System.out.println("Most productive user: " + stats.getMostProductiveUser());
        System.out.println("Most popular user: " + stats.getMostFollowedUser());
        System.out.println("Length of the longest tweet: " + stats.getLengthOfLongestTweet());
        System.out.println("Most unpopular follower " + stats.getMostBlockedFollower());
        ctx.close();*/
        
    }
        
       
}
