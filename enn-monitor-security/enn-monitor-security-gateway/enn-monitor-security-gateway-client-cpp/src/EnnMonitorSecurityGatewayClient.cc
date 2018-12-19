/*
 * EnnMonitorSecurityGatewayClient.cc
 *
 *  Created on: 2017年11月14日
 *      Author: ennew_master_4
 */

#include <iostream>
#include <memory>
#include <string>

#include <grpc++/grpc++.h>

#include <pthread.h>

#include "protobuf/gatewayParameters.pb.h"
#include "protobuf/gatewayServer.pb.h"
#include "protobuf/gatewayServer.grpc.pb.h"

#ifndef _cplusplus
#define _cplusplus
#include "EnnMonitorSecurityGatewayClient.h"
#endif

#include "EnnMonitorSecurityGatewayMessageQueue.h"

using grpc::Channel;
using grpc::ClientContext;
using grpc::Status;

class EnnMonitorSecurityGatewayClient {
 	 public:
		EnnMonitorSecurityGatewayClient(std::shared_ptr<Channel> channel)
      	  : stub_(EnnMonitorSecurityGatewayServer::NewStub(channel)) {}

		void put(const char *token, const char *source, const char *json) {
			EnnMonitorSecurityGatewayRequest request;

			request.set_token(token);
			request.set_source(source);
			request.set_jsonlist(json);

			EnnMonitorSecurityGatewayResponse reply;

			ClientContext context;

			Status status = stub_->put(&context, request, &reply);

			if (status.ok()) {
				if (reply.issuccess() == false) {
					std::cout << reply.error() << std::endl;
				}
			} else {
				std::cout << status.error_code() << ": " << status.error_message() << std::endl;
			}
		}

 	 private:
		std::unique_ptr<EnnMonitorSecurityGatewayServer::Stub> stub_;
};

static pthread_mutex_t mutex;
static EnnMonitorSecurityGatewayClient *client = NULL;

static EnnMonitorSecurityGatewayMessageQueue ennMonitorSecurityGatewayMessageQueue;

void* thread_fun(void *data)
{
	EnnMonitorSecurityGatewayMessageQueue *messageQueue = (EnnMonitorSecurityGatewayMessageQueue *)data;

	while ( true )
	{
		PTOKENDATA pData = messageQueue->pop();

		if (pData != NULL)
		{
			client->put(pData->token, pData->source, pData->json);
			delete pData;
		}
	}

	return NULL;
}


void initClient(const char *host, int port, int threadNum) {
	int i;
	char buf[1000];

	pthread_t pid;

	sprintf(buf, "%s:%d", host, port);

	pthread_mutex_init (&mutex, NULL);

	pthread_mutex_lock (&mutex);

	if (client == NULL) {
		client = new EnnMonitorSecurityGatewayClient(grpc::CreateChannel(buf, grpc::InsecureChannelCredentials()));

		for (i = 0; i < threadNum; ++i) {
			pthread_create(&pid, NULL, thread_fun, (void *)&ennMonitorSecurityGatewayMessageQueue);
		}
	}

	pthread_mutex_unlock(&mutex);
}

void put(const char *token, const char *source, const char *json) {
	PTOKENDATA pData = new TOKENDATA(token, source, json);
	ennMonitorSecurityGatewayMessageQueue.push(pData);
}

void destroyClient() {
	delete client;
	client = NULL;
}

/*int main(int argc, char** argv) {

	initClient("localhost", 10300);
	put("test", "topic", "json");
	destroyClient();

	return 0;
}*/
