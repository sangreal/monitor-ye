g++ -std=c++11 -I/usr/local/include -Iproto/ -pthread proto/protobuf/gatewayParameters.pb.cc proto/protobuf/gatewayParameters.grpc.pb.cc proto/protobuf/gatewayServer.pb.cc proto/protobuf/gatewayServer.grpc.pb.cc src/EnnMonitorSecurityGatewayMessageQueue.cc src/EnnMonitorSecurityGatewayClient.cc -shared -o libEnnMonitorSecurityGatewayClient.so -I./ -fPIC

gcc src/Test.c -o Test -lEnnMonitorSecurityGatewayClient -L./ -L/usr/local/lib `pkg-config --libs grpc++ grpc` -Wl,--no-as-needed -Wl,--as-needed -lprotobuf -lpthread -ldl -Isrc -Xlinker -rpath=./

