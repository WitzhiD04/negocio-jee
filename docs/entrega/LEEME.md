# Entrega — Taller No. 2 Jakarta EE + Tier de Negocio

| Entregable del enunciado | Dónde está |
|---|---|
| Código fuente del proyecto | Raíz del repositorio (`pom.xml`, `src/`, `Dockerfile`) |
| Diseño de la arquitectura y explicación del desacoplamiento | `informe.pdf` (secciones 1 y 2) y `arquitectura.md` |
| Evidencias de funcionamiento | `informe.pdf` (secciones 3 a 5), `capturas/`, `evidencias/` y `postman/` |

| Carpeta / archivo | Contenido |
|---|---|
| `informe.tex` / `.pdf` | Informe: arquitectura, desacoplamiento, pruebas JUnit y pruebas con Postman |
| `arquitectura.md` | Documento de diseño completo (vistas, decisiones y contrato REST) |
| `diagramas/` | Diagramas de la arquitectura: fuente Mermaid (`.mmd`) e imagen (`.png`) |
| `postman/` | Colección de Postman con las 13 peticiones y sus aserciones |
| `capturas/` | Capturas de Postman que usa el informe |
| `evidencias/` | Salidas reales: `mvn clean package`, ejecución de la colección con Newman y respuestas JSON |

## Reproducir las pruebas

```
mvn clean package      # compila y ejecuta las 13 pruebas JUnit
mvn cargo:run          # despliega en Payara 7 en http://localhost:8080
```

En Postman: Import → `postman/negocio-jee.postman_collection.json` → Run collection, con el
servidor recién iniciado (las peticiones dependen del orden y de los id 1 y 2).

## Recompilar el informe

Subir esta carpeta completa a Overleaf o, con LaTeX local, ejecutar
`pdflatex informe.tex` dos veces (para el índice y las referencias).
