package test.profile.batch;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DeadlockLoserDataAccessException;

@Aspect
public class ApplicationCommonAspect {

	private static Logger logger = Logger.getLogger(ApplicationCommonAspect.class);
	
	/**
	 * 
	 */
	public ApplicationCommonAspect() {
		// TODO Auto-generated constructor stub
	}

	
	@AfterThrowing(pointcut = "execution(* org.springframework.batch.core.*.*.*(..)))", 
			throwing = "ex")
	public void x(JoinPoint point, DeadlockLoserDataAccessException ex){
		System.out.println("************ Exception from: " + point.getSignature().getName());
		System.out.println("************ Exception: " + ex.getMessage());
	}
}
