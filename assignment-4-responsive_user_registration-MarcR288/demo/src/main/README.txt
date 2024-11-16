    UserController

The `UserController` class is part of a Spring Boot application that handles user registration, and login.

This controller manages the following functionalities:
    - Displaying registration and login forms.
    - Handling user registration.
    - Validating and processing user login requests.
    - Managing user sessions, including logout functionality.
    - Displaying a list of registered users.

Endpoints
    -GET `/register`**: Show the user registration form.
    -GET `/login`**: Show the login form.
    -GET `/login/{username}`**: Display the user page for the specified username.
    -POST `/login`**: Handle user login attempts.
    -POST `/register`**: Register a new user.
    -GET `/logout`**: Log out the current user and invalidate the session.
    -GET `/users`**: Display a list of all registered users.

Dependencies
    - Spring Web
    - Spring Data JPA
    - Spring Security
    - Jakarta Servlet API

User Model

The `User` class represents a user entity in the application, mapping to the `users` table in the database.

Description

The `User` model encapsulates user-related data and includes fields for the user's ID, username, email, and password.

Entity Details
- Table Name: `users`
- Fields:
  - userId: Unique identifier for the user (auto-generated).
  - username: Unique username for the user (required).
  - email: Unique email address for the user (required).
  - password: User's password (required).

Getters and Setters

- `getUserId()`: Returns the unique ID of the user.
- `getUsername()`: Returns the username.
- `setUsername(String username)`: Sets the username.
- `getEmail()`: Returns the email address.
- `setEmail(String email)`: Sets the email address.
- `getPassword()`: Returns the password.
- `setPassword(String password)`: Sets the password.


UserService

The `UserService` class is part of a Spring Boot application that handles user-related business logic,
particularly user registration.

Description
The `UserService` class provides functionality to register users, ensuring that usernames are unique and passwords are
securely hashed before storing them in the database.

Dependencies
    - Spring Data JPA
    - Spring Security

Methods
`registerUser(String username, String email, String password)`
- Description: Registers a new user after validating input. Throws an exception if the username already exists.
- Parameters:
  - `username`: The desired username for the new user.
  - `email`: The email address of the new user.
  - `password`: The password for the new user.
- Transactional: This method is annotated with `@Transactional` to ensure that database operations are performed atomically.

