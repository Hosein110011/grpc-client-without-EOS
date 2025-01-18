package org.example.grpcclient;

import io.grpc.*;

public class ClientContentTypeInterceptor implements ClientInterceptor {
    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> method,
            CallOptions callOptions,
            Channel next) {

        return new ForwardingClientCall.SimpleForwardingClientCall<>(next.newCall(method, callOptions)) {
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                headers.put(Metadata.Key.of("Content-Type", Metadata.ASCII_STRING_MARSHALLER), "application/grpc+json");
                super.start(responseListener, headers);
            }
        };
    }
}