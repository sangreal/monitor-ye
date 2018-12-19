#include <chrono>
#include <memory>

#include <unistd.h>

#include "EnnMonitorSecurityGatewayMessageQueue.h"

EnnMonitorSecurityGatewayMessageQueue::EnnMonitorSecurityGatewayMessageQueue() {
	pthread_mutex_init (&m_queueMutex, NULL);
}

EnnMonitorSecurityGatewayMessageQueue::~EnnMonitorSecurityGatewayMessageQueue() {
	while ( !m_queue.empty() )
	{
		PTOKENDATA pData = m_queue.front();
		m_queue.pop();

		if (pData != NULL) {
			delete pData;
		}
	}
}

void EnnMonitorSecurityGatewayMessageQueue::push(PTOKENDATA pData) {
	pthread_mutex_lock (&m_queueMutex);

	if (m_queue.size() > 100000) {
		printf("overflow queue, %d\n", m_queue.size());
		pthread_mutex_unlock(&m_queueMutex);
		sleep(1);

		pthread_mutex_lock (&m_queueMutex);
	}

	m_queue.push( pData );
	pthread_mutex_unlock(&m_queueMutex);
}

PTOKENDATA EnnMonitorSecurityGatewayMessageQueue::pop() {
	PTOKENDATA pData = NULL;

	pthread_mutex_lock (&m_queueMutex);

	if ( !m_queue.empty() )
	{
		pData = m_queue.front();
		if ( pData != NULL )
			m_queue.pop();
	} else {
		pthread_mutex_unlock(&m_queueMutex);
		sleep(1);
		pthread_mutex_lock (&m_queueMutex);
	}

	pthread_mutex_unlock(&m_queueMutex);

	return pData;
}
