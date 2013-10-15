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
	public void detectedException(JoinPoint point, Exception ex){
		logger.error("************ Exception from: " + point.getSignature().getName());
		logger.error("************ Exception: " + ex.getMessage(), ex);
	}
}
