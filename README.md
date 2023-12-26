# MUNICS SAPP Práctica 1

<div align="center">

***Seguridad de Aplicaciones: Práctica 1 - Store App***

[![OpenJDK_17](https://img.shields.io/badge/OpenJDK_17-black?logo=openjdk&logoColor=white&labelColor=grey&color=orange)](<https://openjdk.org/>)
[![Spring](https://img.shields.io/badge/Spring-black?logo=spring&logoColor=white&labelColor=grey&color=%236DB33F)](<https://spring.io/>)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-black?logo=springboot&logoColor=white&labelColor=grey&color=%236DB33F)](<https://spring.io/>)
[![Hibernate](https://img.shields.io/badge/Hibernate-black?logo=hibernate&logoColor=white&labelColor=grey&color=%2359666C)
](<https://hibernate.org/>)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-black?logo=thymeleaf&logoColor=white&labelColor=grey&color=%23005F0F)](<https://www.thymeleaf.org/>)
[![jQuery](https://img.shields.io/badge/jQuery-black?logo=jquery&logoColor=white&labelColor=grey&color=%230769AD)](<https://jquery.com/>)
[![bootstrap](https://img.shields.io/badge/bootstrap-black?logo=bootstrap&logoColor=white&labelColor=grey&color=%237952B3)](<https://getbootstrap.com/>)
[![Static Badge](https://img.shields.io/badge/Maven-black?logo=apachemaven&logoColor=white&labelColor=grey&color=%23C71A36)](<https://maven.apache.org/>)

[![License: MIT](<https://img.shields.io/github/license/danielfeitopin/MUNICS-SAPP-P1>)](LICENSE "License")
[![GitHub issues](https://img.shields.io/github/issues/danielfeitopin/MUNICS-SAPP-P1)](<https://github.com/danielfeitopin/MUNICS-SAPP-P1> "Issues")
[![GitHub stars](https://img.shields.io/github/stars/danielfeitopin/MUNICS-SAPP-P1)](<https://github.com/danielfeitopin/MUNICS-SAPP-P1/stargazers> "Stars")

</div>

## Índice

- [MUNICS SAPP Práctica 1](#munics-sapp-práctica-1)
  - [Índice](#índice)
  - [Configuración](#configuración)
    - [Software requerido](#software-requerido)
  - [Uso](#uso)
    - [Compilación y ejecución de la aplicación web](#compilación-y-ejecución-de-la-aplicación-web)
  - [Estructura del proyecto](#estructura-del-proyecto)
  - [Licencia](#licencia)
  - [Contacto](#contacto)


## Configuración

### Software requerido

- [AdoptOpenJDK 17](<https://adoptium.net/>)
- [Maven](<https://maven.apache.org/download.cgi>)

## Uso

### Compilación y ejecución de la aplicación web

Compilación:

```sh
mvn sql:execute install
```

Ejecución:

```sh
mvn spring-boot:run
```

Restauración de la base de datos:

```sh
mvn sql:execute
```

Acceso: <http://localhost:8888/>

## Estructura del proyecto

La aplicación web se distribuye dentro de un proyecto Maven:
- `pom.xml`: fichero de configuración del proyecto.
- `src/main/java`: directorio de código fuente.
- `src/main/resources`: ficheros de configuración.
  - `src/main/resources/templates`: plantillas HTML.
  - `src/main/resources/static`: recursos estáticos.
  - `src/main/resources/application.properties`: ichero principal de configuración de la aplicación.

## Licencia

El contenido de este repositorio está bajo la licencia [GPL3](LICENSE).

## Contacto

¡No dudes en ponerte en contacto conmigo!

<div align="center">

[![GitHub](https://img.shields.io/badge/GitHub-%23181717?style=for-the-badge&logo=github&logoColor=%23181717&color=white)](<https://github.com/danielfeitopin>)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-white?style=for-the-badge&logo=linkedin&logoColor=white&color=%230A66C2)](<https://www.linkedin.com/in/danielfeitopin/>)

</div>