
# Microservicio de Productos - Tienda PC

## Descripción
Microservicio REST desarrollado con Java 21, Spring Boot 4.0.8, Spring Data JPA y MySQL 8. Gestiona productos de una tienda de componentes de PC, incluyendo sus imágenes.

## Tecnologías
- Java 21
- Spring Boot 4.0.8
- Maven
- Spring Data JPA / Hibernate
- MySQL 8
- Postman

## Requisitos
- JDK 21
- MySQL Server 8
- Git

## Instalación y ejecución

1. Clonar el repositorio:

   `git clone https://github.com/JuanLecarosDuarte21/ms-productos.git`

2. Entrar a la carpeta:

   `cd ms-productos`

3. Crear la base de datos en MySQL:

   `CREATE DATABASE IF NOT EXISTS tienda_productos_db;`

4. Configurar las variables de entorno `DB_USERNAME` y `DB_PASSWORD` con las credenciales locales de MySQL.

5. Compilar desde PowerShell:

   `.\mvnw.cmd clean package`

6. Ejecutar:

   `java -jar target/ms-productos-0.0.1-SNAPSHOT.jar`

## Endpoints REST

| Método | Ruta | Descripción |
|---|---|---|
| GET | /api/productos | Listar productos |
| GET | /api/productos/{id} | Buscar por ID |
| POST | /api/productos | Crear producto |
| PUT | /api/productos/{id} | Actualizar producto |
| DELETE | /api/productos/{id} | Eliminar producto |

## Arquitectura
- `controller`: endpoints REST.
- `service`: lógica de negocio.
- `repository`: acceso a datos mediante JPA.
- `model`: entidades Producto e ImagenProducto.
- `config`: configuración adicional.

## Persistencia
Base de datos: `tienda_productos_db`.

Las entidades Producto e ImagenProducto tienen una relación uno a muchos (`@OneToMany`) y muchos a uno (`@ManyToOne`).

## Pruebas
Usar Postman en `http://localhost:8082/api/productos`.

Probar respuestas HTTP 200, 201, 204, 400 y 404 según la operación y los datos enviados.

## Compilación
Ejecutar `.\mvnw.cmd clean install` para compilar e instalar localmente el proyecto, o `.\mvnw.cmd clean package` para generar el archivo JAR.
