# API ScholaGate

ScholaGate es el nombre de un proyecto final de CFGS - Desarrollo Aplicaciones Multiplataforma,

Es una API REST desarrollada con Spring Boot y Java. Proporciona funcionalidades para gestionar alumnos, grupos y usuarios.

## Despliegue

Para instalar el proyecto, sigue estos pasos:



## Configuración de Variables de Entorno

Antes de ejecutar la aplicación, asegúrate de configurar las siguientes variables de entorno:

- `SG_DB_URL`: La URL de la base de datos MySQL.
- `SG_DB_USER`: El nombre de usuario de tu base de datos.
- `SG_DB_PASS`: La contraseña de tu base de datos.
- `SG_MAIL_USER`: El nombre de usuario de tu servidor de correo.
- `SG_MAIL_PASS`: La contraseña de tu servidor de correo.
- `SG_SSL_PASS`: La contraseña de tu almacén de claves SSL.
- `SG_JWT_ID`: El ID de tu token JWT.
- `SG_HASH_PEPPER`: El valor de "pepper" para tu función de hash.


## Uso

Para iniciar la aplicación, ejecuta `mvn spring-boot:run` en la raíz del proyecto.

La ruita base de la API es `https://localhost/api/vi/`.


## Documentación

La documentación de la API está disponible en `https://scholagate.me/swagger-ui.html`.


##Enlaces a las otras partes del proyecto

[App Android](https://github.com/JonathanBetPer/ScholaGate-AppAndroid) en Kotlin

[App Web](https://github.com/carlosaldea3/ScholaGate-AppWeb) en React


## Licencia

Este proyecto está licenciado bajo los términos de la licencia MIT.
