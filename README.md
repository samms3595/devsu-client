## Api Rest Clientes
* Java 17
* Docker
* Maven
* Spring boot 3.1.6
* Lombok
* Open Feign
* Mysql 8.0.33

## Configuración inicial del proyecto
## 1. Configurar Base de datos en entorno docker.
En la raiz del proyecto se encuentra un archivo docker-compose.yml que levanta una base de datos en mysql en el puerto 3306.
Tambien se crea una red en donde se conectan los microservicios entre si, para poder tener comunicacion entre ellos, ya que 
usé el cliente Feign
```
 ./docker-compose.yml
```
Estando en la raiz del proyecto se procedera a abrir terminal y ejecutar el comando

```
docker compose up
```
si solicita algun permiso se le debera anteponer la palabra sudo para darle permisos de administrador.
```
sudo docker compose up
```
## 2. Ejecutar proyecto.
El proyecto corre sobre el puerto 9091
## 3. Api disponible

* Link: http://localhost:9091

## 4. Postman
En la raiz del proyecto se encuentra el archivo.
```
./Clientes-BankDev.postman_collection.json
```
## 5. Postman
Link de documentación Swagger.
```
http://localhost:9091/swagger-ui/index.html#/
```
# Clientes API

Esta colección de Postman ofrece una amplia gama de servicios de API para la gestión integral de operaciones bancarias, cubriendo cuentas, movimientos y operaciones relacionadas con los clientes.

## Descripción General de los Servicios

### Gestión de Clientes `/clientes`
La API facilita una gestión completa de los clientes, permitiendo realizar diversas operaciones cruciales:

- **Crear Cliente:** Los usuarios pueden agregar un nuevo cliente al sistema, proporcionando datos esenciales como nombre, identificación y detalles de contacto. Este paso es fundamental para iniciar cualquier relación bancaria.

- **Obtener Información del Cliente:** Permite acceder a los detalles específicos de un cliente, como su historial de transacciones, perfiles de cuentas y datos personales. Esta funcionalidad es clave para la gestión de relaciones con los clientes y la personalización de servicios.

- **Actualizar Detalles del Cliente:** Ofrece la capacidad de modificar la información existente de un cliente, lo cual es crucial para mantener actualizados los registros y responder a los cambios en las circunstancias del cliente.

- **Eliminar Cliente:** Esta función permite eliminar los datos de un cliente del sistema, adecuada para casos en los que se termina la relación bancaria o en cumplimiento de políticas de privacidad y retención de datos.


## Uso de la API

Los endpoints están diseñados para ser claros y fáciles de usar, permitiendo a los usuarios y desarrolladores interactuar eficientemente con la API a través de Postman. Se recomienda revisar la documentación de cada endpoint para comprender mejor los parámetros requeridos y los formatos de las solicitudes.
