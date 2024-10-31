# GeeUIComponents

**GeeUIComponents** es una librería de componentes de para facilitar la creación de otras funcionalidades del robot

## Características

### CommChannel

**Funcionalidad**: Archivos que definen la estructura y el comportamiento de los comandos que se pueden utilizar para interactuar con un robot

### Components

**App Info**: Diseñado para interactuar con las aplicaciones instaladas en el robot, facilitando la gestion y permisos.
**Base Log**: Clases que estan diseñadas para formatear y leer de forma cómoda los logs.
**Broadcast**: Clase diseñada para recibir eventos de broadcast en la aplicacion del robot, centrada principalmente en los eventos de la batería
**Charging**: Clases que trabajan para gestionar el estado de la batería, por una perte el receiver para escuchar los cambios y el callback para notificar a los componentes los cambios.
**Expression**: Claseas que trabajan para manejar la recuperación y notificación de paths de los archivos de las expresiones (face)
**Locale/Logfile**: Utilidad para el robot como comprobar si esta en chino y utilidad para los logs
**Network**: Funciones de encriptación (MD5 y Sha256) para la signatura del dispositivo y del robot
***Lexnet***: Funciones para manejar la obtención y actualización de tokens de sesión
***Nets***: Clases con funciones para la conexion wifi, para el cambio de estados de la bateria del robot
***System***: Funciones de utilidad para todos los repositorios del robot
**Nodata**: Clase para la gestion de las Views sin información, ya sea para incluir mensajes o redirigir al usuario mediante codigos QR
**Storage**: Clases para la gestion del guardado de información en la sharedpreferences
**Utils**: Utilidad general, como TimeUtils, QRCodeUtils, AssetsUtils y demás...
**View**: Vistas custom como por ejemplo RoundImageView, ImageBGView, etc... para customizar al maximo las vistas

### GeeUIWidget

**Funcionalidad**: Archivos diseñados para definir botones especificos, usados por el robot

## Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/AlejandroDCK/GeeUIComponents



### Contribute

Contributing to this software is warmly welcomed. There are 3 ways you can contribute to this project:

1. Test and report. Let us know if there is something missing in the issue section.
2. Helps us solve current issues or other bugs .
3. Suggest or request new blocks.

You can do this basically by, committing modifications and then open a pull request. Please explain the changes and make sure they have been tested.

Just make sure to keep consistency in the naming and make a record of the change or improvement made.

 Thanks for your contribution.
