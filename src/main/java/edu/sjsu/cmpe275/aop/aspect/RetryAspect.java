package edu.sjsu.cmpe275.aop.aspect;

import java.io.IOException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.aspectj.lang.annotation.Around;

@Aspect
@Order(1)
public class RetryAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     */

	
	public static boolean depleted = false;
	
	@Around("execution(public void edu.sjsu.cmpe275.aop.TweetService.*(..))")
	public void retry(ProceedingJoinPoint joinPoint) throws Throwable{
		
		boolean flag = true;
		
		
		for(int i = 0; (i<=3) && (flag = true); i++) {
			try {
				//System.out.println("Attempt number " + i);
				joinPoint.proceed();
				flag = false;
				if(!flag) {
					break;
				}
			}catch(IllegalArgumentException e) {
				e.printStackTrace();
				break;
			}catch(IOException x) {
				if(i == 3) {x.printStackTrace();  depleted = true; break;}
				else continue;
			}
		}
	}
	
	
		
}


