# API ScholaGate

ScholaGate es un proyecto final de CFGS - Desarrollo Aplicaciones Multiplataforma,

El este repositorio es una API REST desarrollada con Spring Boot. Proporciona funcionalidades para gestionar principalmente alumnos, grupos, usuarios y reportes en un sistema escolar. La API permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en los recursos mencionados y también proporciona funcionalidades de autenticación y autorización.


## Dependencias

Las dependencias clave que utiliza el proyecto son:

1. **Spring Boot Starter**: Proporciona las bibliotecas necesarias para iniciar una aplicación Spring Boot, incluyendo autoconfiguración y soporte para Spring MVC.

2. **Spring Boot Starter Data JPA**: Proporciona soporte para la persistencia de datos utilizando Java Persistence API (JPA) y Hibernate como proveedor de JPA.

3. **Spring Boot Starter Security**: Proporciona soporte para autenticación, autorización y protección contra ataques comunes.

4. **Spring Boot Starter Mail**: Proporciona soporte para el envío de correos electrónicos.

6. **Springfox Swagger 2**: Proporciona herramientas para generar documentación de la API basada en la especificación OpenAPI (anteriormente conocida como Swagger).

7. **Springdoc OpenAPI UI**: Proporciona una interfaz gráfica para visualizar y probar la API documentada por Swagger.

8. **MySQL Connector Java**: Proporciona el driver JDBC para conectar la aplicación con una base de datos MySQL.

9. **Lombok**: Proporciona anotaciones para reducir el código repetitivo en las clases de Java, como getters, setters, constructores y más.

10. **Password4j**: Proporciona funcionalidades para el manejo seguro de contraseñas, incluyendo el hashing de contraseñas.

11. **JSON Web Token (JWT)**: Proporciona funcionalidades para generar y verificar tokens JWT, que se utilizan para la autenticación y la autorización en la API.


## Instalación

Para instalar el proyecto, sigue estos pasos:

1. Clona el repositorio en tu máquina local usando `git clone https://github.com/JonathanBetPer/ScholaGate-API.git`
2. Navega al directorio del proyecto usando `cd scholagate-api`
3. Compila el proyecto usando `mvn clean install`


## Configuración de Variables de Entorno

Antes de ejecutar la aplicación, asegúrate de configurar las siguientes variables de entorno:

- `SG_DB_URL`: La URL de la base de datos MySQL.
- `SG_DB_USER`: El nombre de usuario de tu base de datos.
- `SG_DB_PASS`: La contraseña de tu base de datos.
- `SG_MAIL_USER`: El nombre de usuario de tu servidor de correo.
- `SG_MAIL_PASS`: La contraseña de tu servidor de correo.
- `SG_SSL_PASS`: La contraseña de tu almacén de claves SSL.
- `SG_JWT_ID`: El ID de tu token JWT.
- `SG_HASH_PEPPER`: El valor de "Pepper" para las funciones de Hash.


## Uso

Para iniciar la aplicación, ejecuta `mvn spring-boot:run` en la raíz del proyecto.

La ruita base de la API es `https://localhost/api/vi/`.


## Documentación

La documentación de la API está disponible en [`https://scholagate.me/swagger-ui.html`](https://scholagate.me/swagger-ui.html).


##Enlaces a las otras partes del proyecto

[App Android](https://github.com/JonathanBetPer/ScholaGate-AppAndroid) en Kotlin

[App Web](https://github.com/carlosaldea3/ScholaGate-AppWeb) en React


## Licencia

Este proyecto está licenciado bajo los términos de la licencia MIT.
