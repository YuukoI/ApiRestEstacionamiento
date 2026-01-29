Proyecto: https://docs.google.com/document/d/1aL3pIsaic7FvLBBBD-5mlJyfxI04AyfJ/edit?usp=sharing&ouid=107552105633981259798&rtpof=true&sd=true

🚗 API REST: Sistema de Gestión de Estacionamientos
Este proyecto es una API RESTful diseñada para automatizar y controlar la operación diaria de playas de estacionamiento. El sistema no solo registra vehículos, sino que gestiona de forma inteligente la ocupación del espacio y automatiza el proceso de facturación según el uso.

📋 ¿Qué hace este proyecto?
El sistema centraliza la operativa del estacionamiento mediante las siguientes funcionalidades:

Control de Accesos (Check-in/Check-out): Registro automatizado de la entrada y salida de vehículos (patente, tipo de vehículo y hora exacta).

Gestión de Sectores y Capacidad: Monitoreo en tiempo real de los lugares disponibles y ocupados para evitar el sobrecupo.

Tarificador Inteligente: Cálculo automático del monto a pagar basándose en:

Tiempo de permanencia (fracción, hora, estadía).

Categoría del vehículo (Auto, Moto, Camioneta).

Administración de Clientes Frecuentes: Soporte para abonados mensuales con validación de pagos vigentes.

Reportes de Recaudación: Generación de datos sobre ingresos y flujo de vehículos por franjas horarias.

🛠️ Stack Tecnológico
Backend: Java & Spring Boot.

Seguridad: Spring Security (Validación de acceso a endpoints).

Persistencia: Spring Data JPA / Hibernate + MySQL.

Documentación: Swagger/OpenAPI (Exploración visual de la API).

Herramientas: Maven, Git, Postman.

📐 Detalles de Implementación
Para asegurar un código profesional, implementé:

Manejo de Excepciones: Respuestas claras (404, 400, etc.) cuando un vehículo no existe o un sector está lleno.

Uso de DTOs: Para proteger las entidades del modelo y optimizar el envío de datos.

Lógica de Negocio en Services: El cálculo de tarifas está desacoplado, permitiendo cambiar precios o reglas de negocio sin afectar el resto del sistema.

¿Cómo probarlo?
1. Clonar el repositorio.

2. Ejecutar la aplicación desde el IDE.

3. Abrir la documentación interactiva en: http://localhost:8080/swagger-ui.html para testear cada endpoint.
