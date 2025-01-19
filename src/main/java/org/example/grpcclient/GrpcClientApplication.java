package org.example.grpcclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class GrpcClientApplication {

    @Autowired
    Client client;

    @Autowired
    GrpcClient grpcClient;

    public static void main(String[] args) {
        SpringApplication.run(GrpcClientApplication.class, args);
    }


    @GetMapping("/client")
    public String getRestResponse() {
        System.out.println("received " + client.getResponse());
        return "Wow!";
    }

    @GetMapping("/grpc/client")
    public String getGrpcResponse() {
        System.out.println("received " + grpcClient.getResponse());
        return "Wow!";
    }

}
