# REST_APIs

**Integrantes:**
Brayan Baez,
Melissa Pérez,
Nicolás Rincón

Se esta desarrollando un proyecto que se centra en proporcionar funciones para empresas de servicios, como salones de belleza o restaurantes. Esta API permite a los clientes reservar citas, verificar la disponibilidad, programar citas, cancelarlas y reprogramarlas según sea necesario. El objetivo principal es simplificar la gestión de citas y horarios para estas empresas.

### En la carpeta docs se encuentran los diagramas y las URL de postman.

# Cmd Docker


docker run -d -p 8090:3306 -e MYSQL_ROOT_PASSWORD=clave mysql --name database -e MYSQL_USER=yms_user -e MYSQL_PASSWORD=yms_password -e MYSQL_DATABASE=yms mysql

docker-compose up --build

docker build -t app .         




# Documentación
## Requerimientos funcionales
   #### Descripción general:

Se esta desarrollando un proyecto que se centra en proporcionar funciones para empresas de servicios, como salones de belleza o restaurantes. Esta API permite a los clientes reservar citas, verificar la disponibilidad, programar citas, cancelarlas y reprogramarlas según sea necesario. El objetivo principal es simplificar la gestión de citas y horarios para estas empresas.

#### Identificación de funciones clave:
- Crear franja de trabajo
- Ver las franjas disponibles en función de la fecha y el horario deseado
- Reservar una cita
- Cancelar cita
- Reprogramar cita

#### Descripción detallada:
- Crear franja de trabajo se realiza por parte de los administradores para establecer el horario y dias en los que se va a trabajar. Para crear una franja de trabajo se requiere una fecha, una hora de inicio y una hora de finalización de la franja.


- La segunda función es mirar las franjas disponibles que hay, esta función es principalmente para los usuarios y que con solamente el dia y hora que requieren una cita, revisar si se dará servicio. Esta función depende obviamente de las franjas de trabajo que se hayan creado anteriormente.


- Para reservar una cita los usuarios tienen que ingresar el servicio que desean, el personal que quieren tomando en cuenta que estos ya van a estar en la base de datos puesto que los administradores ya debieron ingresar estos anteriormente (Se inicia una base de datos directamente con una lista de trabajadores y servicios), una hora de inicio, de fin y una fecha. Para crear una cita esta debe estar en la franja de trabajo que los administradores hayan establecido.


- Al momento de cancelar una cita el usuario la podrá hacer directamente con solo el id de la cita que se le brinda después de realizar una reserva. La cita se eliminara directamente en la base de datos para no ocupar espacio pero de igual forma se guardará un registro en caso de algún problema.


- Por ultimo la reprogramación de cita también la podrán realizar los usuario con el id dado y brindando las horas de inicio y finalización además de la nueva fecha que se desea, por obvias razones para reprogramar una cita ya hay que tener una cita reservada. 


## Requerimientos no funcionales
   ### Rendimiento:

El rendimientos es esencial y aún más en un aplicativo que se encarga de realizar citas y la cantidad de usuarios pueden llegar a ser bastantes, para asegurar el rendimiento se definen los tiempos de respuesta del aplicativo y se busca disminuirlo lo máximo posible. 

#### - Tiempo de Respuesta:
-Se define como un tiempo máximo aceptable para las respuestas de las solicitudes de los clientes por mucho 3 segundos. Esto lo busco el equipo intentando optimizar al máximo el código y teniendo una base de datos estable y eficiente.

### Seguridad:

La seguridad es fundamental para un proyecto. Aquí hay algunas consideraciones que se manejan en el aplicativo:

#### - Autenticación y Autorización:
- Para lograr esto se utiliza el basic auth de spring boot que permite solicitar un usuario y contraseñas para realizar alguna solicitud. De la misma forma se tiene en cuenta que hay funciones que no requieren una autenticación y para eso también se puede configurar los path accesibles con y sin seguridad. 


### Mantenibilidad:

Esta sección se centra en hacer que el sistema sea fácil de mantener y actualizar, para un proyecto de citas que se planea para diferentes servicios es necesario tener una mantenibilidad constante para futuras actualizaciones y nuevas personas en el proyecto:

#### - Documentación:
- Se busca tener un código limpio y claro junto a diagramas C4 y 4+1 que permiten entender mucho mejor el proyecto, como funciona y mantenerlo.

## Estilo de arquitectura

- Nuestra aplicación sigue una arquitectura de estilo monolítica.

Elegimos un estilo monolítico para simplificar la implementación y el despliegue inicial devido a que es un proyecto relativamente pequeño y somos pocas personas las que trabajamos.
### Patrón utilizado
Utilizamos el patrón MVC para separar la lógica de presentación, la lógica de negocios y la gestión de la interfaz de usuario aunque en este caso las interfaces las encontramos en un proyecto de react.
Elegimos MVC para mejorar la modularidad y facilitar la prueba unitaria al separar las diferentes preocupaciones del sistema.
## Responsabilidad ética
### Contextos Globales y Sociales:
Privacidad de los datos: Garantizar la protección de la información personal de los clientes, teniendo en cuenta cada uno de las legislaciones del país. El sistema debe cumplir con las regulaciones de privacidad de datos para evitar el uso indebido de la información.

Transparencia y honestidad: Proporcionar información clara y precisa sobre los servicios, precios, disponibilidad y políticas de cancelación para mantener la confianza de los clientes.

Seguridad de la información: Implementar medidas de seguridad necesarias para prevenir posibles vulnerabilidades y ataques cibernéticos que puedan comprometer la información confidencial de los clientes.

### Contextos Económicos
Competencia justa: Asegurar que el sistema no favorezca injustamente a ciertos proveedores de servicios sobre otros y promueva una competencia leal entre las empresas.

Transacciones financieras seguras: Garantizar la seguridad en los procesos de pago y transacciones financieras para proteger tanto a los clientes como a las empresas.

### Responsabilidades Profesionales
Calidad del servicio: Asegurar que el sistema proporcione una experiencia de usuario óptima y una funcionalidad confiable para cumplir con las expectativas de los clientes y proveedores de servicios.

Cumplimiento normativo: Respetar las regulaciones locales, nacionales e internacionales relacionadas con el comercio electrónico, la protección de datos, entre otras.
