package redt.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ApplicationUploadConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private Environment env;

	// CONFIG - UPLOAD FILE
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver(){
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSizePerFile(5*1024*1024); // Max upload : 5Mb
		return multipartResolver;
	}

	// Serve static html, js, css, ... and image files
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String upload_path = env.getProperty("upload_path").toString();
		registry.addResourceHandler("/resources/**").addResourceLocations("file:" + upload_path);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
