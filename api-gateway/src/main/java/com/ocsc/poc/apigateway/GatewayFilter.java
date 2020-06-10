package com.ocsc.poc.apigateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.ocsc.poc.util.ErrorResponse;
import com.ocsc.poc.util.JWTTokenHelper;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GatewayFilter extends ZuulFilter {

	private static List<String> nonProtectedresource = Arrays.asList("/login");

    @Override
    public boolean shouldFilter() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        HttpServletResponse response = requestContext.getResponse();
        if (isProtectedResource(request.getRequestURI())) {
            String token = request.getHeader("Authorization");
            String user = JWTTokenHelper.verifyToken(token.replace("Bearer ", ""));
            if (user == null) {
                ErrorResponse errorResponse = new ErrorResponse("Not a valid token", new ArrayList(Collections.singleton("Not a valid token")));
                try {
                    requestContext.setResponseBody(new ObjectMapper().writeValueAsString(errorResponse));
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    //requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
                    requestContext.setResponse(response);
                    requestContext.setSendZuulResponse(false);
                } catch (JsonProcessingException e) {
                    throw new ZuulException("Processing error", 400, "Error while Converting object to JSON");
                }
            }
        }
        return null;
    }

    @Override
    public String filterType() {
        // TODO Auto-generated method stub
        return "pre";
    }

    @Override
    public int filterOrder() {
        // TODO Auto-generated method stub
        return 1;
    }

	private boolean isProtectedResource(String requestUri){
		for (String resource : GatewayFilter.nonProtectedresource) {
			if(requestUri.contains(resource)){
				return false;
			}
		}

		return true;
	}


}
