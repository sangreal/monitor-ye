#ifndef ENNMONITORSECURITYGATEWAYLOGCLIENT_H
#define ENNMONITORSECURITYGATEWAYLOGCLIENT_H

#ifdef _cplusplus
	extern "C" {
#endif

	void initClient(const char *host, int port, int threadNum);
	void put(const char *token, const char *source, const char *json);
	void destroyClient();

#ifdef _cplusplus
	}
#endif

#endif
