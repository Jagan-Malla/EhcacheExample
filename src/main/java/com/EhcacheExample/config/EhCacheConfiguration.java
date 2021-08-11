package com.EhcacheExample.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableJpaRepositories(basePackages = "com.EhcacheExample.repositories")
@EnableCaching
@Configuration
@EnableWebMvc
public class EhCacheConfiguration extends WebMvcConfigurerAdapter {
	
	 @Bean
	    public CacheManager cacheManager() {
	        return new EhCacheCacheManager(cacheMangerFactory().getObject());
	    }

	    @Bean
	    public EhCacheManagerFactoryBean cacheMangerFactory() {
	        EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
	        bean.setConfigLocation(new ClassPathResource("ehcache.xml"));
	        bean.setShared(true);
	        return bean;
	    }
	    
	    @Override
	    public void configureViewResolvers(ViewResolverRegistry registry) {
	        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	        resolver.setPrefix("/webapp/WEB-INF/jsp/");
	        resolver.setSuffix(".jsp");
	        resolver.setViewClass(JstlView.class);
	        registry.viewResolver(resolver);
	    }
	    
	    

}
