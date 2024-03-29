# BPM Camunda demo
This demo works with the Camunda standalone server. That was an architectural choice.

1. Download the Open Source edition of Camunda.
2. Start the server. sh start-camunda.sh - it should start in the port 8080.
3. Now run mvn compile quarkus:dev to initialize the API in port 8086
4. (Optionally) Run the com.demo.external.CreateTemplate class. This class is optional because as 
you can see in the diagram below we can handle errors.


## Diagram of the process modeled
![Camunda diagram](/camunda-demo.png)


## Example requests
Create a form using the Quarkus endpoint - should return a 204, meaning that the process has been started. The server prints the process business key (key used to uniquivocally identify the process instance).

```bash
curl -X POST \
  http://localhost:8086/form \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Length: 313' \
  -H 'Content-Type: application/json' \
  -H 'Cookie: JSESSIONID=62FD762C6253367EF376A65C500FE922.208' \
  -H 'Host: localhost:8086' \
  -H 'Postman-Token: e000aec6-f5b2-4d48-b358-b3d3f6ad3dea,66c47b15-cc1e-4f23-a62f-d5753913506d' \
  -H 'User-Agent: PostmanRuntime/7.19.0' \
  -H 'cache-control: no-cache' \
  -d '{
	"formTitle": "Demo example form",
	"fieldList": [
		{
			"name": "Example form",
			"fieldType": "STRING",
			"validationList": [
				{
					"validationType": "LESS_THAN",
					"value": "9"
				}
				],
			"value": "10"
		}
		],
	"rolesAllowed": [
		{
			"role": "admin"
		},	
		{
			"role": "pikachu"
		}
	]
}'
```


Start the create form process by directly invoking the Camunda engine - this will create an empty form (the body is an empty json object):

```bash
curl -X POST \
  http://localhost:8080/engine-rest/process-definition/key/bpmUnifiedProcess/start \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Length: 2' \
  -H 'Content-Type: application/json' \
  -H 'Cookie: JSESSIONID=62FD762C6253367EF376A65C500FE922.208' \
  -H 'Host: localhost:8080' \
  -H 'Postman-Token: eed00342-d2b9-473a-a039-fefc344504d9,69672e67-7340-4606-93c9-4d5b3f216809' \
  -H 'User-Agent: PostmanRuntime/7.19.0' \
  -H 'cache-control: no-cache' \
  -d '{}'
```
