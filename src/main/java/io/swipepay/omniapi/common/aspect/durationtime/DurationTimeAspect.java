package io.swipepay.omniapi.common.aspect.durationtime;

import java.time.Instant;
import java.time.LocalDateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCcRepository;

@Aspect
@Component
public class DurationTimeAspect {
	
	@Autowired
	private PaymentTxCcRepository paymentTxCcRepository;
	
	@Around("@annotation(io.swipepay.omniapi.common.aspect.durationtime.DurationTimeTxCc)")
	@Transactional(readOnly = false)
	public Object logDurationTimeTxCc(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = Instant.now().toEpochMilli();
	    Object proceed = joinPoint.proceed();
	    long durationTime = Instant.now().toEpochMilli() - start;
	    
	    Object[] args = joinPoint.getArgs();
	    PaymentTxCc paymentTxCc = null;
	    
	    switch (joinPoint.getSignature().getName()) {
		case "routeTx":
			paymentTxCc = (PaymentTxCc) args[0];
			paymentTxCc.setTxDurationTime(durationTime);
			break;
		case "payment":
			paymentTxCc = (PaymentTxCc) args[1];
			paymentTxCc.setDurationTime(durationTime);
			break;
		case "preauth":
			paymentTxCc = (PaymentTxCc) args[1];
			paymentTxCc.setDurationTime(durationTime);
			break;
		default:
			break;
		}
	    
	    paymentTxCc.setModified(LocalDateTime.now());
		paymentTxCcRepository.saveAndFlush(paymentTxCc);
	    return proceed;
	}
}