# REST_APIs

**Integrantes:**
Brayan Baez
Melissa Pérez
Nicolás Rincón

Se esta desarrollando un proyecto que se centra en proporcionar funciones para empresas de servicios, como salones de belleza o restaurantes. Esta API permite a los clientes reservar citas, verificar la disponibilidad, programar citas, cancelarlas y reprogramarlas según sea necesario. El objetivo principal es simplificar la gestión de citas y horarios para estas empresas.

Para correr el proyecto se requiere Java con JDK 20 

En la carpeta docs se encuentran los diagramas y las URL de postman

Para correr en el docker:


docker run -d -p 8090:3306 -e MYSQL_ROOT_PASSWORD=clave mysql --name database -e MYSQL_USER=yms_user -e MYSQL_PASSWORD=yms_password -e MYSQL_DATABASE=yms mysql

docker-compose up --build
docker build -t app .   


Se requiere iniciar docker antes.