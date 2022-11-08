package com.springcloud.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
	
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info("::::::::::::::::::Entrando a POST");
		Long tiempoInicio = (Long)request.getAttribute("tiempoInicio");
		Long tiempoFinal = System.currentTimeMillis();
		Long tiempoTranscurrido = tiempoFinal - tiempoInicio;
		log.info(String.format(":::::::::Tiempo transcurrido en segundos %s seg", tiempoTranscurrido.doubleValue()/1000.00));
		log.info(String.format(":::::::::Tiempo transcurrido en milisegundos %s ms", tiempoTranscurrido));

		return null;
	}

	@Override
	public String filterType() {
		// antes de que se resuelva la comunicacion
		return "post";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

}
