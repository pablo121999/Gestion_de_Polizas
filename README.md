⚙️ Configuración del proyecto
1️⃣ Crear Base de Datos MySQL
CREATE DATABASE polizas_db;

Verificar que application.properties tenga configurado:

spring.datasource.url=jdbc:mysql://localhost:3306/polizas_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
2️⃣ Ejecutar el proyecto

Desde la raíz del proyecto:

mvn clean spring-boot:run

El servidor iniciará en:

http://localhost:8080
🧪 Datos Iniciales (Opcional)

Crear una póliza manualmente:

INSERT INTO POLIZA (TIPO, ESTADO, CANON_MENSUAL, PRIMA)
VALUES ('COLECTIVA', 'ACTIVA', 1000000, 12000000);
🔐 Seguridad

Todas las peticiones requieren el siguiente header obligatorio:

x-api-key: 123456

En Postman agregar en la sección Headers:

KEY	VALUE
x-api-key	123456
📌 Endpoints Disponibles
📌 Listar pólizas
GET http://localhost:8080/polizas?tipo=COLECTIVA&estado=ACTIVA
📌 Agregar riesgo a una póliza
POST http://localhost:8080/polizas/1/riesgos
📌 Renovar póliza
POST http://localhost:8080/polizas/1/renovar?ipc=0.05
📌 Cancelar póliza
POST http://localhost:8080/polizas/1/cancelar
📌 Cancelar riesgo
POST http://localhost:8080/riesgos/1/cancelar
🌐 Mock Externo Obligatorio

Endpoint simulado para enviar eventos al CORE.

📌 Enviar evento
POST http://localhost:8080/core-mock/evento
Body JSON
{
  "evento": "ACTUALIZACION",
  "polizaId": 555
}

Este endpoint solo registra en logs que el evento fue enviado correctamente.

🏗 Arquitectura

Controladores REST

Capa Service con @Transactional

Repositorios con Spring Data JPA

EntityGraph para carga controlada

Manejo de Lazy Loading

Seguridad básica con API Key

Mock externo para integración simulada

📌 Flujo del Sistema

Crear póliza

Agregar riesgos

Renovar con IPC

Cancelar póliza (cancela riesgos asociados)

Cancelar riesgo individual

Enviar evento al mock CORE

👨‍💻 Autor

Pablo
Proyecto académico / técnico – Gestión de Pólizas
