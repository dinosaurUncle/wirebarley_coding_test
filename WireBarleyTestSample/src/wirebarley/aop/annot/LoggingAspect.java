package wirebarley.aop.annot;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	protected static Logger logger = Logger.getLogger(LoggingAspect.class.getName());
	
	@Before("execution(public * wirebarley..*(..))")
	public void before(JoinPoint jointPoint) {
		String signatureString = jointPoint.getSignature().getName();
		logger.info("@Before [ " + signatureString + " ] 메서드 실행 전처리 수행 ");
		for(Object arg : jointPoint.getArgs()) {
			logger.info("@Before [ " + signatureString + " ] 아규먼트 " + arg);	
		}
	}
	
	@AfterReturning(pointcut="execution(public * wirebarley.exchange.service.*.*(..))", returning="ret")
	public void afterReturning(JoinPoint joinPoint, Object ret) {
		String signatureString = joinPoint.getSignature().getName();
		logger.info("@AfterReturing [ " + signatureString + " ] 메서드 실행 후처리 수행");
		logger.info("@AfterReturing [ " + signatureString + " ] 리턴값=" + ret);
	}
	
	 @AfterThrowing(pointcut="execution(* *..ExchangeService*.*(..))", 
	    		throwing="ex")
	public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
		String signatureString = joinPoint.getSignature().getName();
		logger.info("@AfterThrowing [ " + signatureString + " ] 메서드 실행 중 예외 발생");
		logger.info("@AfterThrowing [ " + signatureString + " ] 예외=" + ex.getMessage());
	}
	    
    @After("execution(* *..*.*Exchage(..))")
	public void afterFinally(JoinPoint joinPoint) {
		String signatureString = joinPoint.getSignature().getName();
		logger.info("@After [ " + signatureString + " ] 메서드 실행 완료");
	}
}
