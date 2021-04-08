# DEMO-ACCIONA

Demo que mediante Twitter streaming se obtiene Tweets y los guarda en una base de datos en local.
Se creó servicios RestFull para el consumo de los tweets almacenados.

## Tecnologías y libs
- Spring Boot
- lombok  
- Spring Integration
- H2 database

## Pasos para la creación del proyecto.

1. Para la interacción con la API de Twitter, como requisito era utilizar la librería `twitter4j`.

2. Dado que nunca había utilizado la API de Twitter, estuve investigando un poco y leyendo la forma de consumir los tweets vía streaming.
Encontré por internet una forma que me pareció muy interesante al momento de consumir y utilizaba `Spring Integration`.
   
3. Utilicé Spring integration para consumir vía streaming, porque me pareció una manera sencilla de poder consumir tweets, adicionalmente que utiliza patrones empresariales, como el Messaging Channel, que se utilizó en este ejemplo.

4. He separado en el paquete `com.demo.acciona.twitter.*` todo lo referente a la lógica de cosumir tweets.

5. Se separó la lógica de consumir los tweets guardados en el paquete `com.demo.acciona.manage.*`.

6. Se creó la paquetería orientada una arquitectura hexagonal, en la cual tiene 3 grandes paquetes que son `application`, `domain` y `infraestructure`.
Dentro de esos paquetes se creó un paquete con el nombre `tweet`, orientado a que se pueda agrupar toda la funcionalidad de tweets en un solo paquete.
    - Existen diversas opiniones sobre la paquetería y todas con sus pro y sus contras.

7. De la parte de la aplicación que lee los tweet guardados en la base de datos, se trato de crear la API agrupando 2 criterios de búsqueda en 1 solo.  
        - Consultar todos los tweet y Consultar los tweets validados por usuario.
He tratado de reutilizar correctamente los verbos http y crear una sola API de nombre `tweets`. 
He visto conveniente de enviar como parámetro no requerido el user y el flag validado 
y según si viene informado o no aplico un criterio de búsqueda o de lo contrario
busco todo. 
   

## Por mejorar
- Separación de proyecto por un lado un proyecto para el streaming y otro para consumir de la base de datos.
- Utilizar el driver reactivo (no bloqueante) para la persistencia en H2.