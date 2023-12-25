# Todo App
This is a simple Todo application built using Java, Spring Boot, Hibernate, and MySQL. It allows users to manage their todo items by performing CRUD operations.

## Features
- Create new todo items with titles and descriptions.
- Retrieve a list of all todo items.
- Retrieve a specific todo item by ID.
- Update existing todo items.
- Delete todo items.
  
## Prerequisites
Make sure you have the following installed:

- Java Development Kit (JDK)
- Maven
- MySQL


## Getting Started
**1. Clone the repository:**

```
git clone https://github.com/laziz511/EPAM-Generative-AI-Task.git
```

**2. Database Configuration:**

Create a MySQL database named `todo_app`.

Update the database configurations in `src/main/resources/application.properties` with your **MySQL** credentials:

```
spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
spring.datasource.username=your-username
spring.datasource.password=your-password
```

**3. Run the Application:**

```
cd todo-app
mvn spring-boot:run
```


**4. API Endpoints:**

Get all todo items: `GET /api/todo`
Get todo item by ID: `GET /api/todo/{id}`
Create todo item: `POST /api/todo`
Update todo item: `PUT /api/todo/{id}`
Delete todo item: `DELETE /api/todo/{id}`
Usage
Use any REST client (e.g., Postman) or make HTTP requests to the provided endpoints to interact with the Todo API.
Questions from the task:

```
- Was it easy to complete the task using AI? yes
- How long did task take you to complete? (Please be honest, we need it to gather anonymized statistics) 2.5 hours
- Was the code ready to run after generation? not completely, I had to ask for some additions
- What did you have to change to make it usable? I had to ask gpt to add exception handling and improving the service layer
- Which challenges did you face during completion of the task? I had problems while writing tests for controller layer
- Which specific prompts you learned as a good practice to complete the task? asking GPT to give example prompts to ask further in the conversation.
```

**5. ChatGPT Logs:**
```
https://chat.openai.com/share/5b5f9084-a780-41ff-8ef2-ef01c5b8f85b
```
