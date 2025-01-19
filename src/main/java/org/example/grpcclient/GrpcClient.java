package org.example.grpcclient;

import com.example.api_gateway.grpc.MyGrpcServiceGrpc;
import com.example.api_gateway.grpc.TestRequest;
import com.example.api_gateway.grpc.TestResponse;
import io.grpc.ManagedChannel;
import org.springframework.stereotype.Component;

@Component
public class GrpcClient {

    ManagedChannel channel;
    MyGrpcServiceGrpc.MyGrpcServiceBlockingStub myGrpcServiceBlockingStub;

    public GrpcClient(ManagedChannel managedChannel) {
        this.channel = managedChannel;
        myGrpcServiceBlockingStub = MyGrpcServiceGrpc.newBlockingStub(managedChannel);
    }

    public TestResponse getResponse() {
        var testRequest = TestRequest.newBuilder().setData("Wowwwwwww!!!!!!!!!!!").build();
        return myGrpcServiceBlockingStub.testGrpcGateway(testRequest);
    }
}