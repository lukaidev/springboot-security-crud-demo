
# coding exercise

This application is deployed using docker containter and digitalocean for hosting.
  - you can access it via swagger here at http://165.22.58.190:8086/swagger-ui.html
  - also you use mysql workbench to access the database.
    - hostname: 165.22.58.190:3308
    - username: root
    - password: 123456

Requirements:

1) Identify two entities with a Parent-Child relationship. 
    - For this I created a entity Menu and MenuItem under pachage com.example.entity.model with a parent-child relationship.
  
2) Develop a web application that will demonstrate the CRUD (CREATE, READ, UPDATE, DELETE) operations on both parent and child entities.
    - Please go to http://165.22.58.190:8086/swagger-ui.html tp test the crud functionality.
    - Also please see the actual codes for the flow of the app.
    
3) You can choose any language and frameworks for this exercise. Then deploy the application on any platform that will be accessible via internet. Please forward to us the URL to the application once you're done.
    - I have used java springboot, mysql and digitalocean for hosting for this exercise.
    
4) Provide a short write up on what you did and what were your considerations for the exercise.
    - Because this application will be deployed via web hosting and the apis will be consumed by a frontend I decided to use:
      - Spring security for authentication.
      - Docker for simplicity and faster configurations.
      - DigitalOcean for Docker ready droplet.
      - Mysql for database.
      - Swagger for api documentations.
      
      
