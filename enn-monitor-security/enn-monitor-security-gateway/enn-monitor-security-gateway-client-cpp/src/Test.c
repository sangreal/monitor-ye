#include <ctype.h>
#include <stdio.h>

#include "EnnMonitorSecurityGatewayClient.h"

int main(int argc, char** argv) {

	//initClient("10.19.248.200", 30111, 64);
	initClient(argv[1], atoi(argv[2]), atoi(argv[3]));

	while (1) {
		put("token", "test", "json");
	}

	destroyClient();

	return 0;
}
