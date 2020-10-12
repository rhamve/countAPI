Download the code from git-hub URL

Build docker 

```
docker build -t count:latest .
```
 
Run docker

```
docker run -it count:latest /bin/bash
```
    
Now start tomcat 

 ```
cd /usr/local/tomcat/bin
./startup.sh
 ```
Finally, ready to run the curl commands one by one to execute APIs inside docker with host as localhost

```
root@93ff21879831:/usr/local/tomcat/bin# curl -X POST http://localhost:8080/counter-api/search -H "Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -d '{"searchText":["Duis", "Sed", "Donec", "Augue", "Pellentesque", "123"]}' -H "Content-Type: application/json" 
```

```
curl http://localhost:8080/counter-api/top/5 -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -H"Accept: application/json"
```
