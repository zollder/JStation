/*
 * SpringApplicationConfig.java
 * Created by zollder.
 */
package org.station.configuration;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling
@ComponentScan("org.station")
@PropertySource("classpath:/org/station/props/application.properties")
public class SpringApplicationConfig implements SchedulingConfigurer
{
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar)
	{
		taskRegistrar.setScheduler(singleThreadedScheduledExecutor());
	};

	@Bean(destroyMethod="shutdown")
	public Executor singleThreadedScheduledExecutor()
	{
		return Executors.newSingleThreadScheduledExecutor();
	}

	@Bean(destroyMethod="shutdown")
	public Executor cachedThreadedExecutor()
	{
		return Executors.newCachedThreadPool(threadFactory());
	}

	@Bean
	public ThreadFactory threadFactory()
	{
		return new ThreadFactory()
		{
			public Thread newThread(Runnable runnable)
			{
				Thread thread = new Thread(runnable);
				thread.setDaemon(true);
				return thread;
			}
		};
	}

	@Bean
	@Scope("singleton")
	public ConcurrentLinkedQueue<Number> dataQueue()
	{
		return new ConcurrentLinkedQueue<Number>();
	}

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }
/*
    @Bean
    public ThreadPoolTaskExecutor taskExecutor()
    {
    	ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    	executor.setCorePoolSize(1);
    	executor.setMaxPoolSize(2);
    	executor.setQueueCapacity(10);
    	executor.setWaitForTasksToCompleteOnShutdown(true);
    	return executor;
    }
*/
/*    @Bean
    public static PropertySourcesPlaceholderConfigurer properties()
    {
        final PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setLocation(new ClassPathResource("/org/station/props/application.properties"));
        return propertySourcesPlaceholderConfigurer;
    }*/
}
