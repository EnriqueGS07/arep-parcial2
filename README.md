# arep-parcial2
Se diseñó, construyó y desplego un aplicación web para investigar la conjetura de Collatz. El programa está desplegado en un microcontenedor Docker corriendo en AWS. LAs tecnologías usadas en la solución son maven, git, github, maven, sparkjava, html5, y js. No se uso liberías adicionales.

### Prerequisitos
- mvn
- java 11

### Construccion 
- Para crear la imagen de forma local ponemos el siguiente codigo en el arhivo dockerfile
```
FROM openjdk:11

WORKDIR /usrapp/bin

ENV PORT 6000

COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","org.arep.Collatz"]
```
- Luego el siguiente comando en la consola
```
docker build --tag collatz .       
```
- Deberia aparecer algo asi
```
REPOSITORY                TAG       IMAGE ID       CREATED             SIZE 
collatz                   latest    c2560444419a   About an hour ago   657MB
```

- Luego esta imagen se debe subir a dockerhub para poder usarla desde la maquina virtual
- Para crear el contenedor, entramos a la consola de aws de la maquina virtual EC2
![image](https://user-images.githubusercontent.com/98104282/229220081-5e761de5-0aa7-49e5-9322-0d8e3c6fbc5e.png)
- Luego ponemos el comando para pullear y ejecutar la imagen creada anteriormente
```
docker run -d -p 4567:6000 --name collatz kikegonzalez202/collatz
```
- Luego el comando docker ps para ver que contenedores
```
[ec2-user@ip-172-31-3-125 ~]$ docker ps
```
- Deberia aparecer lo siguiente
```
CONTAINER ID   IMAGE                     COMMAND                  CREATED          STATUS          PORTS                                       NAMES
ffec4006a022   kikegonzalez202/collatz   "java -cp ./classes:…"   36 minutes ago   Up 36 minutes   0.0.0.0:4567->6000/tcp, :::4567->6000/tcp   collatz
```
- A contuniacion va un video demo
  https://youtu.be/CEk4W1kRq6o
