rm -rf ./src/main/java/
mkdir -p ./src/main/java/
protoc --java_out=./src/main/java ./protobuf/*
protoc --plugin=protoc-gen-grpc-java=/home/ennew_master_4/devtool/grpc-java/compiler/build/exe/java_plugin/protoc-gen-grpc-java --grpc-java_out=./src/main/java ./protobuf/*
#protoc --plugin=protoc-gen-grpc-java=/home/xh/grpc-java-1.2.0/compiler/build/exe/java_plugin/protoc-gen-grpc-java --grpc-java_out=./src/main/java ./protobuf/*
