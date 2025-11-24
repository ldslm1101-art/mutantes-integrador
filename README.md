# 🧬 Examen MercadoLibre - Detector de Mutantes

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.5-green.svg)](https://spring.io/projects/spring-boot)
[![Coverage](https://img.shields.io/badge/Coverage-97%25-brightgreen.svg)]()

Este proyecto es una solución técnica al desafío de MercadoLibre para reclutar mutantes. Consiste en una **API REST** capaz de detectar secuencias genéticas mutantes dentro de una cadena de ADN, gestionando altas cargas de tráfico y almacenando estadísticas.

---
## ☁️ Acceso al Deploy (Nube)

El proyecto se encuentra desplegado en **Render** y listo para ser probado.

* 🔗 **URL Base de la API:**
  `https://mutantes-integrador.onrender.com`

* 📄 **Swagger UI (Documentación Interactiva):**
  `https://mutantes-integrador.onrender.com/swagger-ui/index.html`
  *(Utilizar este enlace para probar los endpoints visualmente).*
---
## 📋 Contexto del Desafío

Magneto quiere reclutar la mayor cantidad de mutantes para luchar contra los X-Men.
Se ha desarrollado un programa que cumple con la siguiente firma: `boolean isMutant(String[] dna)`.

**Reglas de Negocio:**
* Se recibe un array de Strings que representan una tabla de **(NxN)** con la secuencia de ADN.
* Las letras permitidas son solo: **(A, T, C, G)**.
* Un humano es **Mutante** si se encuentran **más de una secuencia de cuatro letras iguales**, de forma oblicua, horizontal o vertical.
* En caso contrario, es considerado **Humano**.

---

## 🚀 Tecnologías Utilizadas

* **Lenguaje:** Java 21
* **Framework:** Spring Boot 3 (Web, Data JPA)
* **Base de Datos:** H2 Database (En memoria, para alto rendimiento en pruebas)
* **Pruebas:** JUnit 5 (Cobertura de código > 80%)
* **Documentación:** Swagger (OpenAPI)
* **Nube:** Render (Despliegue continuo)

---

## 📡 Guía de la API y Ejemplos de Uso

La API expone dos servicios principales alojados en la nube (o localmente en el puerto 8080).

### 1. Detectar Mutante (`POST`)

Envía una secuencia de ADN para verificar si el sujeto es reclutable.

* **Endpoint:** `/mutant/`
* **Método:** `POST`
* **Respuesta Esperada:**
    * `200 OK`: Es Mutante.
    * `403 Forbidden`: Es Humano.

#### 🟢 Ejemplo: CASO MUTANTE (Devuelve 200 OK)
Este ADN contiene secuencias repetidas (como "AAAA" o diagonales coincidentes).

```json
{
    "dna": [
        "ATGCGA",
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CCCCTA",
        "TCACTG"
    ]
}
```
## 🔴 Ejemplo: CASO HUMANO (Devuelve 403 Forbidden)

Este ADN **NO** contiene secuencias repetidas suficientes para ser considerado mutante.

```json
{
  "dna": [
    "AAAT",
    "AACC",
    "TTAC",
    "GGTC"
  ]
}
```
### 2. Estadísticas (`GET`)
Devuelve un JSON con las estadísticas de las verificaciones de ADN realizadas hasta el momento.
* **Endpoint:** `/stats`
* **Método:** `GET`
* **Respuesta de Ejemplo:**
```json
{
    "count_mutant_dna": 40,
    "count_human_dna": 100,
    "ratio": 0.4
}
```
(Donde ratio = `count_mutant_dna` / `count_human_dna`).
# ⚙️ Instrucciones de Ejecución Local
Si deseas correr el proyecto en tu máquina:

### 1-Clonar el repositorio:
`(Bash)`
```bash
git clone https://github.com/TU_USUARIO/examen-mercadolibre.git
cd examen-mercadolibre
```

### 2-Ejecutar con Gradle

`(Bash)`
```bash
./gradlew bootRun
```

Ejecutar con Gradle:

### 3-Links de Interés (Una vez iniciado):

* **📄 Swagger UI (Documentación Visual)**: http://localhost:8080/swagger-ui/index.html

* **💾 H2 Console (Base de Datos)**: http://localhost:8080/h2-console

    * ***JDBC URL***: `jdbc:h2:mem:testdb`.
    * ***User***: sa.
    * ***Password***: (dejar vacío).

# 🧪 Testing y Cobertura
El proyecto cuenta con tests automáticos que validan la lógica de negocio y aseguran una cobertura superior al 80%.
* **Comando para correr tests:**
  `(Bash)`
```bash
./gradlew test
```

* **Reporte**: La cobertura actual es del 97%

**ALUMNO: Lautaro Montenegro**.

**MAIL: lds.lm1101@gmail.com**

**LEGAJO: 51208**

