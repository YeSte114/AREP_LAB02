# TALLER 2: TALLER DISEÑO Y ESTRUCTURACIÓN DE APLICACIONES DISTRIBUIDAS EN INTERNET
En este taller usted explorará la arquitectura de las aplicaciones distribuidas. Concretamente, exploraremos la arquitectura de  los servidores web y el protocolo http sobre el que están soportados. 

## Iniciando
Para descargar el repositorio y verlo local, utiliza el siguiente código:
```
  git clone https://github.com/YeSte114/AREP_LAB01.git
```
```
  cd AREP_LAB01
```
### Prerequisitos
Es necesario tener Java 8 y Maven instalados. Compruebe con los siguientes comandos:

```
java --version
mvn --version
```
### Instalación

Habilitar su IDE para correr el programa con maven, en este caso se usa NetBeans:
![](.README_Images/net.png)

y darler run a la clase main de la clase HttpServer. Por otro lado tambien puede corre el proyecto con los siguientes comandos

```
mvn package
```

Y luego

```
mvn clean package exec:java -D "exec.mainClass"="edu.escuelaing.arep.app.AREP_LAB02.App"
```

Finalmente ingrese al navegador de su preferencia con el siguiente link:
http://localhost:35000

Para ver JavaScript puede ingresar al siguiente link: http://localhost:35000/apps/index.html

En el caso de quere ver la página html que fue creada, en caso de buscar un servicio que no existe en el momento se le dirigirá a una página de error 404, por ejemplo usando el siguiente link:
http://localhost:35000/apps/anonimo.html

Si desea ver como tal un archivo deseado, como por ejemplo solo el css, ingrese al siguiente link:
http://localhost:35000/apps/index.css

## Construcción

* [Maven](https://maven.apache.org/) - Manejo de dependencias
* [JavaScript](https://developer.mozilla.org/es/docs/Web/JavaScript) - Statics files (frontend)

## Autor

* **Yeison Steven Barreto Rodriguez** - *AREP - LAB02* - [YeSte114](https://github.com/YeSte114)


---

### DESCRIPCIÓN  DE LA ARQUITECTURA:

![](.README_Images/estructura.png)

##### Requerimientos:

Escriba un servidor web que soporte múlltiples solicitudes seguidas (no concurrentes). El servidor debe leer los archivos del disco local y retornar todos los archivos solicitados, incluyendo páginas html, archivos java script, css e imágenes. Construya una aplicación web con  javascript, css, e imágenes para probar su servidor. Incluya en la aplicación la comunicación asíncrona con unos servicios REST en el backend. NO use frameworks web como Spark o Spring, use solo Java y las librerías para manejo de la red.

## Documentación
Se encontrar la documentación en la carpeta nombrada "javadoc", para generar nueva documentación puede correr el siguiente comando
```
mvn javadoc:javadoc
```
La nueva documentación generada puede encontrarla en la ruta /target/site/apidocs

## Corriendo Tests unitatios

Para correr los test ubiquese en la carpeta principal de repositorio y corra el siguiente comando desde la consola

```
mvn test
```
## Explicaciones técnicas
La aplicación se basa en una arquitectura de API REST, que proporciona una interfaz uniforme para la comunicación con el API externo. Para lograr la extensibilidad, se utiliza el patrón de fachada. Este patrón proporciona una interfaz única para acceder a diferentes implementaciones de la API externa. Para cambiar la dirección de la URL de la API externa, simplemente se debe modificar el atributo url de la clase APIConnection.

- La modularización se logra aplicando los principios de única responsabilidad y separación de preocupaciones. Cada clase implementa solo una responsabilidad, lo que facilita la extensión del código.

- Patrones utilizados

  - Fachada: El patrón de fachada proporciona una interfaz única para acceder a diferentes implementaciones de la API externa. Esto facilita la extensibilidad y el mantenimiento del código.
  - Singleton: El patrón de singleton garantiza que solo exista una instancia de un objeto dado. Esto es importante en el caso del caché, ya que se debe garantizar que no haya conflictos en el acceso a los datos.
- Extensibilidad

  Para cambiar la dirección de la URL de la API externa, simplemente se debe modificar el atributo url de la clase APIConnection. Esto se puede hacer de forma programática o modificando el archivo de configuración de la aplicación.

- Modularización

  Todas las clases implementan métodos que cumplen el principio de única responsabilidad. Esto significa que cada clase solo tiene una responsabilidad, lo que facilita la extensión del código. Por ejemplo, la clase APIConnection solo se encarga de conectar con la API externa. La clase HttpServer solo se encarga de procesar los datos de la API externa y formatearlos en una tabla.
