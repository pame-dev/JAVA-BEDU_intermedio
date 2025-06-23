## 🚀 **Java - Ejercicios**

Este repositorio contiene diversos ejercicios prácticos en **Java** que simulan situaciones de sistemas reales usando:
- **CompletableFuture** para programación asincrónica.
- **Project Reactor (Flux)** para flujos reactivos.
- **Stream API** y **Optional** para el procesamiento funcional y seguro de colecciones.

---

## 📝 **Ejercicios incluidos**

### 1️⃣ **PizzeríaApp (Stream API + Optional)**  
Procesa pedidos de una pizzería:  
✔ Filtra pedidos a domicilio.  
✔ Genera mensajes de confirmación si el número telefónico está presente.  

📌 _Conceptos:_ `Stream`, `Optional`, `filter`, `map`, `flatMap`.

---

### 2️⃣ **MovilidadApp (CompletableFuture)**  
Simula el flujo asincrónico de una app de movilidad (tipo Uber o DiDi):  
✔ Calcula la ruta óptima.  
✔ Estima la tarifa.  
✔ Combina ambos procesos en paralelo.  
✔ Maneja errores potenciales.

📌 _Conceptos:_ `CompletableFuture`, `thenCombine`, `exceptionally`.

---

### 3️⃣ **AeropuertoControl (CompletableFuture)**  
Gestiona condiciones para autorizar un aterrizaje:  
✔ Verifica en paralelo pista, clima, tráfico aéreo y personal.  
✔ Autoriza solo si todo es óptimo.  
✔ Simula fallas aleatorias y maneja errores.

📌 _Conceptos:_ `CompletableFuture.allOf`, `join`, `exceptionally`.

---

### 4️⃣ **Meridian Prime (Project Reactor - Flux)**  
Monitorea sistemas críticos en una ciudad inteligente:  
✔ Sensores de tráfico, contaminación, accidentes, trenes maglev y semáforos.  
✔ Filtra y muestra eventos críticos.  
✔ Simula backpressure.  
✔ Detecta alertas globales si hay múltiples eventos críticos simultáneos.

📌 _Conceptos:_ `Flux`, `merge`, `onBackpressureBuffer`, `buffer`.

---

### 5️⃣ **UCI Monitor (Project Reactor - Flux)**  
Simula el monitoreo de signos vitales en una UCI:  
✔ Monitorea FC, PA, SpO2 de varios pacientes en tiempo real.  
✔ Filtra valores críticos.  
✔ Aplica backpressure para no saturar.  
✔ Prioriza eventos críticos de FC.

📌 _Conceptos:_ `Flux`, `merge`, `delayElements`, `sort`.

---

## ⚙ **Requisitos**
✅ Java 11+  
✅ [Project Reactor](https://projectreactor.io/) (`reactor-core`)  

Si usas Maven:
```xml
<dependency>
  <groupId>io.projectreactor</groupId>
  <artifactId>reactor-core</artifactId>
  <version>3.5.11</version>
</dependency>
