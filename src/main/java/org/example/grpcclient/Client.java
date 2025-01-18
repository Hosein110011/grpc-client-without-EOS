package org.example.grpcclient;


import com.example.grpcclient.grpc.MyGrpcServiceGrpc;
import com.example.grpcclient.grpc.TestRequest;
import com.example.grpcclient.grpc.TestResponse;
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

    public TestResponse getResponse() {
        var testRequest = TestRequest.newBuilder().setData("Wowwwwwww!!!!!!!!!!!").build();
        return userServiceBlockingStub.testGateway(testRequest);
    }
}