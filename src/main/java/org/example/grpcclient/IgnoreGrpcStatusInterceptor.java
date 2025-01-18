package org.example.grpcclient;


import io.grpc.*;

public class IgnoreGrpcStatusInterceptor implements ClientInterceptor {

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                Listener<RespT> wrappedListener = new ForwardingClientCallListener.SimpleForwardingClientCallListener<>(responseListener) {
                    @Override
                    public void onClose(Status status, Metadata trailers) {
                        if (status.getCode() == Status.Code.INTERNAL) {
                            Metadata.Key<String> myHeaderKey = Metadata.Key.of("grpc-status", Metadata.ASCII_STRING_MARSHALLER);
                            trailers.put(myHeaderKey, "0");
                            super.onClose(Status.OK, trailers);
                        } else {
                            super.onClose(status, trailers);
                        }
                    }
                };
                super.start(wrappedListener, headers);
            }
        };
    }
}