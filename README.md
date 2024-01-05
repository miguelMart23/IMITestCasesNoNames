# TestSuite para el Institut Municipal d'Informàtica de Barcelona

## Descripción

Este repositorio contiene el código fuente y la configuración necesaria para llevar a cabo el desarrollo de pruebas automatizadas para la página web de la Oficina Virtual de Trámites de Barcelona. El proyecto está desarrollado en Java y utiliza herramientas como Selenium WebDriver y Allure Reports para garantizar la calidad y estabilidad de la plataforma.

## Requisitos del Sistema

Para ejecutar y contribuir al proyecto, asegúrate de contar con las siguientes herramientas y dependencias instaladas:

Java: Asegúrate de tener Java instalado en tu sistema. Puedes descargar la última versión desde java.com.

Selenium WebDriver: Se utiliza para interactuar con la interfaz de usuario de la aplicación web. Asegúrate de tener el controlador adecuado para el navegador que prefieras (por ejemplo, ChromeDriver o GeckoDriver).

Allure Reports: Recientemente, se ha integrado la herramienta Allure Reports para mejorar la visualización y análisis de los resultados de las pruebas. Puedes obtener más información sobre Allure Reports en el sitio oficial.

## Estructura del Proyecto

El proyecto está organizado de la siguiente manera:

src/test: Contiene los scripts de prueba desarrollados en Java con Selenium para validar la funcionalidad de la Oficina Virtual de Trámites de Barcelona.

allure-results/: Almacena los informes generados por Allure Reports después de la ejecución de las pruebas.

screenshots/: Almacena capturas de pantalla de diferentes expeciones o pasos en los tests.

## Ejecución de Pruebas

Para ejecutar las pruebas, utiliza el siguiente comando:

Esto ejecutará todas las pruebas y generará informes en el directorio reports/. Puedes visualizar estos informes utilizando Allure Reports ejecutando:
```
mvn clean test
```

Esto abrirá una interfaz de usuario en tu navegador predeterminado para navegar y analizar los resultados de las pruebas.
```
allure serve reports
```

## Acerca del Proyecto

Este proyecto tiene como objetivo principal desarrollar y mantener un conjunto robusto de pruebas automatizadas para la Oficina Virtual de Trámites de Barcelona. Las pruebas abarcan diversas áreas, como el registro de usuarios, la navegación por la plataforma y la realización de trámites.

La integración de Allure Reports proporciona una visualización detallada de los resultados de las pruebas, facilitando la identificación y resolución de problemas. Contribuciones y mejoras al proyecto son bienvenidas para fortalecer la calidad de la aplicación y garantizar una experiencia fluida para los usuarios de la Oficina Virtual de Trámites de Barcelona.
