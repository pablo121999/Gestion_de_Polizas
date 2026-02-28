# Configuración del proyecto

# Crear Base de Datos MySQL
CREATE DATABASE polizas_db;

# Verificar que application.properties tenga configurado:

spring.datasource.url=jdbc:mysql://localhost:3306/polizas_db?useSSL=false&serverTimezone=UTC

spring.datasource.username=root

spring.datasource.password=TU_PASSWORD

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

spring.jpa.properties.hibernate.format_sql=true

spring.jpa.open-in-view=false

# Ejecutar el proyecto

Desde la raíz del proyecto:

mvn clean spring-boot:run

El servidor iniciará en:

http://localhost:8080

# Datos Iniciales

Crear una póliza manualmente:

INSERT INTO POLIZA (TIPO, ESTADO, CANON_MENSUAL, PRIMA)
VALUES ('COLECTIVA', 'ACTIVA', 1000000, 1200000);

# Seguridad

Todas las peticiones requieren el siguiente header obligatorio:

x-api-key: 123456


# Endpoints Disponibles

📌 Listar pólizas

GET http://localhost:8080/polizas?tipo=COLECTIVA&estado=ACTIVA

📌 Agregar riesgo a una póliza

POST http://localhost:8080/polizas/1/riesgos

📌 Renovar póliza

POST http://localhost:8080/polizas/1/renovar?ipc=0.01

📌 Cancelar póliza

POST http://localhost:8080/polizas/1/cancelar

📌 Cancelar riesgo

POST http://localhost:8080/riesgos/1/cancelar

# Mock Externo Obligatorio

Endpoint simulado para enviar eventos al CORE.

📌 Enviar evento

POST http://localhost:8080/core-mock/evento

Body JSON
{
  "evento": "ACTUALIZACION",
  "polizaId": 1
}

Este endpoint solo registra en logs que el evento fue enviado correctamente.

# 👨‍💻 Autor

Pablo Andres Aroca Garcia
