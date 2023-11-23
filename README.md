# REST_APIs

**Integrantes:**
Brayan Baez
Melissa Pérez
Nicolás Rincón

Se esta desarrollando un proyecto que se centra en proporcionar funciones para empresas de servicios, como salones de belleza o restaurantes. Esta API permite a los clientes reservar citas, verificar la disponibilidad, programar citas, cancelarlas y reprogramarlas según sea necesario. El objetivo principal es simplificar la gestión de citas y horarios para estas empresas.

Para correr el proyecto se requiere Java con JDK 17 

En la carpeta docs se encuentran los diagramas y las URL de postman

Para correr en el docker:


docker run -d -p 8090:3306 -e MYSQL_ROOT_PASSWORD=clave mysql --name database -e MYSQL_USER=yms_user -e MYSQL_PASSWORD=yms_password -e MYSQL_DATABASE=yms mysql

docker-compose up --build

docker build -t app .         

primero debes abrir docker.


#Documentación

##Responsabilidad ética
###Contextos Globales y Sociales:
Privacidad de los datos: Garantizar la protección de la información personal de los clientes, teniendo en cuenta cada uno de las legislaciones del país. El sistema debe cumplir con las regulaciones de privacidad de datos para evitar el uso indebido de la información.

Transparencia y honestidad: Proporcionar información clara y precisa sobre los servicios, precios, disponibilidad y políticas de cancelación para mantener la confianza de los clientes.

Seguridad de la información: Implementar medidas de seguridad necesarias para prevenir posibles vulnerabilidades y ataques cibernéticos que puedan comprometer la información confidencial de los clientes.

###Contextos Económicos
Competencia justa: Asegurar que el sistema no favorezca injustamente a ciertos proveedores de servicios sobre otros y promueva una competencia leal entre las empresas.

Transacciones financieras seguras: Garantizar la seguridad en los procesos de pago y transacciones financieras para proteger tanto a los clientes como a las empresas.

###Responsabilidades Profesionales
Calidad del servicio: Asegurar que el sistema proporcione una experiencia de usuario óptima y una funcionalidad confiable para cumplir con las expectativas de los clientes y proveedores de servicios.

Cumplimiento normativo: Respetar las regulaciones locales, nacionales e internacionales relacionadas con el comercio electrónico, la protección de datos, entre otras.
