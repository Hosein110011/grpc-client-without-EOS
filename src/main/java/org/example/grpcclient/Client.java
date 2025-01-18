package org.example.grpcclient;

import com.example.api_gateway.grpc.MyGrpcServiceGrpc;
import com.example.api_gateway.grpc.TestRequest;
import com.example.api_gateway.grpc.TestResponse;
import io.grpc.ManagedChannel;
import org.springframework.stereotype.Component;

@Component
public class Client {

    ManagedChannel userServiceChannel;
    MyGrpcServiceGrpc.MyGrpcServiceBlockingStub userServiceBlockingStub;

    public Client(ManagedChannel userServiceChannel) {
        this.userServiceChannel = userServiceChannel;
        userServiceBlockingStub = MyGrpcServiceGrpc.newBlockingStub(userServiceChannel);
    }

    public TestResponse getUser() {
        var getUserRequest = TestRequest.newBuilder().setData("ٌWowwwwwww!!!!!!!!!!!").build();
        return userServiceBlockingStub.testGateway(getUserRequest);
    }
}