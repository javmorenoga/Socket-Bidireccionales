## Comunicación por Sockets en Java (Cliente - Servidor)
Este proyecto implementa una aplicación de chat simple entre un cliente y un servidor usando sockets TCP en Java. Ambos tienen una interfaz gráfica construida con Swing y permiten el envío y recepción de mensajes en tiempo real.
## estructura del proyecto
```
sockets/
├── src/
│   └── socket/
│       ├── control.java      # Clase principal que inicia cliente y servidor
│       ├── socket1.java      # Cliente con interfaz gráfica
│       ├── socket1.form      # Archivo de diseño del formulario cliente
│       ├── socket2.java      # Servidor con interfaz gráfica
│       └── socket2.form      # Archivo de diseño del formulario servidor
├── nbproject/                # Configuración del proyecto NetBeans
├── build.xml                 # Script de construcción Ant
└── manifest.mf              # Archivo de manifiesto para JAR
```
###  Cliente (`socket1.java`)

- **Interfaz gráfica**:
  - Campo de texto para ingresar mensajes.
  - Botón "Enviar".
  - Área de texto para mostrar el historial del chat.
- **Conexión**: 
  - Se conecta al servidor en `127.0.0.1:9999`.
- **Funcionalidad**: 
  - Envía mensajes al servidor y muestra los recibidos.
- **Protocolo**: 
  - `Socket`, `DataOutputStream`, `DataInputStream`.

---

###  Servidor (`socket2.java`)

- **Interfaz gráfica**:
  - Área de texto para mostrar mensajes recibidos.
  - Campo de texto y botón para enviar mensajes.
- **Escucha**: 
  - Puerto `9999`.
- **Funcionalidad**: 
  - Acepta conexiones, recibe mensajes y puede responder.
- **Protocolo**: 
  - `ServerSocket`, `Socket`, `DataOutputStream`, `DataInputStream`.

---

### Controlador (`control.java`)

- Punto de entrada de la aplicación.
- Lanza automáticamente el **servidor** y luego el **cliente**.
- Utiliza `SwingUtilities.invokeLater` para abrir las ventanas gráficas en el hilo adecuado.

---

### Funcionamiento

![Funcionamiento](https://github.com/javmorenoga/Socket-Bidireccionales/blob/master/Captura%20de%20pantalla%202025-07-10%20172219.png)
