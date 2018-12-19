rm -rf proto
mkdir proto
protoc --cpp_out=./proto --grpc_out=./proto --plugin=protoc-gen-grpc=`which grpc_cpp_plugin` protobuf/*.proto

g++ -std=c++11 -I/usr/local/include -Iproto/ -pthread  -c -o gatewayParameters.pb.o proto/protobuf/gatewayParameters.pb.cc
g++ -std=c++11 -I/usr/local/include -Iproto/ -pthread  -c -o gatewayParameters.grpc.pb.o proto/protobuf/gatewayParameters.grpc.pb.cc

g++ -std=c++11 -I/usr/local/include -Iproto/ -pthread  -c -o gatewayServer.pb.o proto/protobuf/gatewayServer.pb.cc
g++ -std=c++11 -I/usr/local/include -Iproto/ -pthread  -c -o gatewayServer.grpc.pb.o proto/protobuf/gatewayServer.grpc.pb.cc

g++ -std=c++11 -I/usr/local/include -c -o EnnMonitorSecurityGatewayMessageQueue.o src/EnnMonitorSecurityGatewayMessageQueue.cc

g++ -std=c++11 -I/usr/local/include -Iproto/ -pthread  -c -o EnnMonitorSecurityGatewayClient.o src/EnnMonitorSecurityGatewayClient.cc

g++ gatewayParameters.pb.o gatewayParameters.grpc.pb.o gatewayServer.pb.o gatewayServer.grpc.pb.o EnnMonitorSecurityGatewayMessageQueue.o EnnMonitorSecurityGatewayClient.o -L/usr/local/lib `pkg-config --libs grpc++ grpc` -Wl,--no-as-needed -lgrpc++_reflection -Wl,--as-needed -lprotobuf -lpthread -ldl -o EnnMonitorSecurityGatewayClient

