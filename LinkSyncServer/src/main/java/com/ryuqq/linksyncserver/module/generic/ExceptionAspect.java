package com.ryuqq.linksyncserver.module.generic;

import com.ryuqq.linksyncserver.infra.config.MessageSourceHelper;
import com.ryuqq.linksyncserver.module.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Aspect
@Component
public class ExceptionAspect {

    private final MessageSourceHelper messageSourceHelper;

    @AfterThrowing(pointcut = "execution(* com.ryuqq.linksyncserver..*(..))", throwing = "ex")
    public void handleApplicationException(ApplicationException ex) {
        String baseMessage = messageSourceHelper.getMessage(ex.getErrorCode());
        String combinedMessage = String.format("%s: %s", baseMessage, ex.getMessage());
        ex.setMessage(combinedMessage);
    }

}
