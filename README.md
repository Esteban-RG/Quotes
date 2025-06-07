# 📦 Quotes App

Aplicación web de gestión de productos con categorías y unidades de medida. Permite registrar productos con imagen, descripción, precio y relaciones con otras entidades. Backend desarrollado en **Spring Boot**, frontend en **React** y base de datos en **MySQL**.

---

## 🚀 Stack Tecnológico

| Tecnología      | Descripción                               |
|----------------|-------------------------------------------|
| 🧠 Backend      | Java 17, Spring Boot 3, Spring Data JPA   |
| 🎨 Frontend     | React, Parcel                             |
| 🗄️ Base de datos | MySQL                                     |
| 🌐 API          | RESTful APIs con DTOs                     |
| 🛠️ Build Tool   | Maven                                     |

---

## 🗂️ Estructura del Proyecto

```Tree
quotes-app/
│
│ ── src/
│ ── pom.xml
│
├── frontend/
│ ├── src/
│ └── index.html
│
├── README.md
└── .gitignore
```


---

## ⚙️ Configuración del Proyecto

### 🔐 Variables de entorno

Configura las siguientes variables en tu entorno o en el archivo `application.properties`:

```properties
spring.datasource.url=${MYSQL_URL}
spring.datasource.username=${MYSQL_USER}
spring.datasource.password=${MYSQL_PASSWORD}
```

---

### 🧪 Ejecución local

- Clona el repositorio:

```bash
git clone https://github.com/Esteban-RG/quotes-app.git
cd quotes
```

- Inicia la base de datos (puedes usar Docker o XAMPP).

- Ejecuta el proyecto:

```bash
cd backend
./mvnw spring-boot:run
```

---

### ✅ TODOs / Cosas por hacer:

- Agregar DTO's a entidades (Category , UnitOfMeasure)

---

### ✨ Autor

Desarrollado con 💻 por Esteban-RG

📧 Contacto: ricardoespace@gmail.com
