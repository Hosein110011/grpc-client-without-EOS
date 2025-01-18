package org.example.grpcclient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public ManagedChannel createChannel() {
        return ManagedChannelBuilder.forAddress("localhost", 7000).intercept(new IgnoreGrpcStatusInterceptor()).usePlaintext().build();
    }
}
