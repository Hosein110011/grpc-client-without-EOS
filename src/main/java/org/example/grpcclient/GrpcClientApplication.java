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

    public static void main(String[] args) {
        SpringApplication.run(GrpcClientApplication.class, args);
    }


    @GetMapping("/client")
    public String get() {
        System.out.println("received " + client.getResponse());
        return "Wow!";
    }

}
