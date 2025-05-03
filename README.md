**EventMasterPro** es una aplicación de consola desarrollada en Java para la gestión integral de eventos, enfocada en espectáculos culturales o musicales. Permite organizar todos los aspectos relacionados con eventos, incluyendo artistas, asistentes, presupuestos, entradas y control financiero.

## Funcionalidades principales

- Crear y administrar eventos  
- Registrar artistas y sus necesidades técnicas  
- Gestionar asistentes y control de acceso  
- Emitir y vender entradas  
- Realizar seguimiento financiero de cada evento  
- Consultar registros de ingresos y gastos  

## Estructura del proyecto

El sistema está diseñado con un enfoque orientado a objetos, con clases que representan cada entidad clave del sistema:

- **Main**: clase principal que contiene el menú y la lógica base del programa  
- **Event**: representa un evento con su información básica (fecha, lugar, categoría, etc.)  
- **Artist**: define los datos de un artista y sus requerimientos  
- **Attendee**: gestiona la información de los asistentes al evento  
- **Ticket**: modela una entrada, con opciones de venta y validación  
- **TicketSale**: administra el proceso de compra de boletos  
- **AccessControl**: valida el acceso de los asistentes al evento  
- **Budget** y **FinancialRecord**: permiten llevar el control de los costos, ingresos y el balance general  
- **Location**: gestiona información sobre los lugares donde se realizan los eventos  

## Tecnologías utilizadas

- Java (versión 8 o superior)  
- Maven para gestión del proyecto y dependencias  
- Librerías estándar de Java (`java.util`, `java.time`, etc.)  

## Requisitos

- Tener instalado Java (JDK 8 o superior)  
- Tener Maven configurado en el sistema  
- Acceso a una terminal o consola para compilar y ejecutar la aplicación
