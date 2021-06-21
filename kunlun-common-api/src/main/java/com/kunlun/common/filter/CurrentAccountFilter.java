package com.kunlun.common.filter;

import com.kunlun.common.model.ClientToken;
import com.kunlun.common.model.Context;
import com.kunlun.common.model.CurrentAccount;
import com.kunlun.common.utils.JwtTokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.util.ObjectUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 当前账户Filter
 */
@Order(Integer.MAX_VALUE - 8)
public class CurrentAccountFilter implements Filter {

    private Logger logger = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("CurrentAccountFilter init ...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("CurrentAccountFilter doFilter ...");
        CurrentAccount currentAccount = Context.getCurrentAccount();
        String authorization = ((HttpServletRequest) servletRequest).getHeader("Authorization");
        if (ObjectUtils.isEmpty(currentAccount) && !ObjectUtils.isEmpty(authorization)) {
            ClientToken clientToken = JwtTokenUtil.getClientToken(authorization);
            currentAccount = new CurrentAccount(clientToken);
            Context.setCurrentAccount(currentAccount);
            logger.info("CurrentAccount Context setting finish");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("CurrentAccountFilter destroy");
    }
}
