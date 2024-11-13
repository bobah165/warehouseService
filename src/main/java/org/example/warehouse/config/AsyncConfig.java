package org.example.warehouse.config;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

  @Override
  public Executor getAsyncExecutor() {
    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    threadPoolTaskExecutor.setThreadNamePrefix("WarehouseAsyncExecutor-");
    threadPoolTaskExecutor.setCorePoolSize(4);
    threadPoolTaskExecutor.setMaxPoolSize(32);
    threadPoolTaskExecutor.setQueueCapacity(64);
    threadPoolTaskExecutor.initialize();
    return threadPoolTaskExecutor;
  }
}
