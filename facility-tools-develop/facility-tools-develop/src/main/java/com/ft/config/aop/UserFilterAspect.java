package com.ft.config.aop;

import com.ft.service.impl.UserServiceImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.EntityManager;

/**
 * Filter calls for items by tenantId.
 */
@Aspect
@Configuration
public class UserFilterAspect {

    private final EntityManager entityManager;
    private final UserServiceImpl service;

    public UserFilterAspect(EntityManager entityManager, UserServiceImpl service) {
        this.entityManager = entityManager;
        this.service = service;
    }

    /**
     * Before accessing any other controller everything should be handled here.
     */
    @Before(value = ("execution(* com.ft.controllers.securedControllers.*.*(..))"))
    public void setUserFilter() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String tenantId = service
                .findByUsername(auth.getPrincipal().toString())
                .getTenant()
                .getId();


        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("tenant").setParameter("tenantid", tenantId);
    }
}
