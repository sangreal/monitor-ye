#ifndef ENNMONITORSECURITYGATEWAYMESSAGEQUEUE_H
#define ENNMONITORSECURITYGATEWAYMESSAGEQUEUE_H

#include <iostream>
#include <memory>
#include <string.h>

#include <queue>

#include <pthread.h>

typedef struct token_data
{
	char token[1000];
	char source[1000];
	char *json = NULL;

	token_data(const char *pToken, const char *pSource, const char *pJson) {
		strcpy(token, pToken);
		strcpy(source, pSource);
		json = new char[strlen(pJson) + 1];
		strcpy(json, pJson);
	}

	~token_data() {
		if (json != NULL) {
			delete [] json;
			json = NULL;
		}
	}

} TOKENDATA, *PTOKENDATA;

class EnnMonitorSecurityGatewayMessageQueue
{
	public:
		EnnMonitorSecurityGatewayMessageQueue();
		~EnnMonitorSecurityGatewayMessageQueue();

		void push( PTOKENDATA pData );
		PTOKENDATA pop();

	private:
		pthread_mutex_t m_queueMutex;
		std::queue<PTOKENDATA> m_queue;
};

#endif
