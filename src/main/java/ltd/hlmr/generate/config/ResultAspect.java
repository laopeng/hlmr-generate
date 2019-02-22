package ltd.hlmr.generate.config;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import lombok.extern.slf4j.Slf4j;
import ltd.hlmr.generate.pojo.LayuiTableResult;

/**
 * 记录日志，并修改返回结果为规定的格式
 *
 */
@Aspect
@Component
@Slf4j
public class ResultAspect {

	@Pointcut("execution(* ltd.hlmr.generate.controller..*.*(..))")
	public void result() {
	}

	/**
	 * 记录请求日志
	 * 
	 * @param joinPoint
	 */
	@Before("result()")
	public void doBefore(JoinPoint joinPoint) {
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		log.info("url : " + request.getRequestURL().toString());
		log.info("http_method : " + request.getMethod());
		log.info("ip : " + request.getRemoteAddr());
		log.info("class_method : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		log.info("args : " + Arrays.toString(joinPoint.getArgs()));
	}

	/**
	 * 重新包装返回结果
	 * 
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("result()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object object = proceedingJoinPoint.proceed(); // 运行doSth()，返回值用一个Object类型来接收
		Object newObject = JSON.toJSON(object);
		if (newObject instanceof JSONArray) {
			JSONArray jsonArray = (JSONArray) newObject;
			LayuiTableResult result = new LayuiTableResult();
			result.setCode(0);
			result.setCount((long) jsonArray.size());
			result.setData(object);
			object = result;
		}
		return object;
	}

	/**
	 * 记录返回结果日志
	 * 
	 * @param result
	 */
	@AfterReturning(pointcut = "result()", returning = "result")
	public void doAfterReturning(Object result) {
		log.info(JSON.toJSONString(result));
	}

}
