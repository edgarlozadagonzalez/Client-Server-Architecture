# Client-Server-Architecture
Desarrollar

- Una aplicación de Java que permita listar y transferir archivos bajo una arquitectura
cliente/servidor.
- El servidor será una aplicación de consola y el cliente una aplicación de escritorio.
- El servidor deberá mostrar la información de su actividad todo el tiempo miestras este
ejecutándose.
- El servidor podrá tener un máximo de n clientes conectados, implementar patrón Object
Pool, cada conexión será tratada bajo un hilo de ejecución.
- El cliente/servidor deberán implementar capas para separar responsabilidades.
- La estructura de la información a transmitir entre las partes será bajo el formato JSON.
- El protocolo de comunicación entre el cliente/servidor será TCP-IP

El servidor deberá exponer los siguiente servicios:
- Listado de clientes conectados (dirección IP – Fecha – Hora Inicio)
- Listado de documentos disponibles (nombre – tamaño – extensión – Propietario (local – Externo (Dirección IP)) )
- Informar al cliente que no puede aceptar la conexión
- Enviar un documento al cliente
- Recibir documentos de los clientes
  
El cliente deberá:
- Conectarse al servidor indicando la dirección IP y el puerto de escucha.
- Listar los documentos disponibles en el servidor (nombre – tamaño – extensión)
- Recibir mensajes del servidor
- Enviar un documento al servidor
- Recibir un documento del servidor
- Para el cliente la estructura de interfaz grafica de usuario será de libre elección, el cliente puede ser construido con
tecnologías diferentes a Java.
