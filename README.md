# Encrypt and Decrypt

A Java-based security application that provides user authentication with encrypted password storage and management.

## Overview

This project is a simple yet effective encryption and decryption system designed to securely manage user credentials. It features:

- **User Authentication System**: Login functionality with secure password handling
- **Encryption & Decryption**: Secure password storage using cryptographic hashing algorithms
- **User Management**: Store and verify user credentials safely

## Features

✅ User registration and login system  
✅ Encrypted password storage (SHA-256 hashing)  
✅ Secure credential verification  
✅ Simple and intuitive interface  

## Project Structure

```
Encrypt-and-Decrypt/
├── src/
│   ├── controller/          # Login and authentication logic
│   ├── model/               # User model and data structures
│   ├── util/                # Encryption and decryption utilities
│   └── view/                # GUI components
├── bin/                     # Compiled Java bytecode
├── users.txt                # User credentials storage
├── .classpath               # Eclipse classpath configuration
├── .project                 # Eclipse project configuration
└── README.md                # Project documentation
```

## Technology Stack

- **Language**: Java
- **IDE**: Eclipse
- **Encryption**: SHA-256 hashing algorithm
- **Data Storage**: Text-based (users.txt)

## How It Works

### User Registration
1. Enter username and password
2. Password is encrypted using SHA-256
3. User credentials are stored in `users.txt`

### User Login
1. Enter username and password
2. Password is encrypted and compared with stored hash
3. Access granted if credentials match

## Security

This project uses **SHA-256 hashing** to securely store passwords:
- Passwords are never stored in plain text
- One-way encryption ensures passwords cannot be decrypted
- Each login attempt verifies the hashed password

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Eclipse IDE (or any Java IDE)

### Running the Application

1. **Clone the repository**
   ```bash
   git clone https://github.com/syedjunaid-max/Encrypt-and-Decrypt.git
   ```

2. **Open in Eclipse**
   - File → Open Projects from File System
   - Select the project directory

3. **Compile and Run**
   - Right-click on the project → Run As → Java Application
   - Or use the Run button in Eclipse toolbar

## Example Usage

```java
// Login with username and password
LoginController login = new LoginController();
boolean isAuthenticated = login.authenticate("syed", "password123");

if (isAuthenticated) {
    System.out.println("Login successful!");
} else {
    System.out.println("Invalid credentials!");
}
```

## File Descriptions

- **LoginController.java**: Handles user authentication logic
- **User.java**: User model class for storing user information
- **EncryptionUtil.java**: Utility class for password encryption/decryption
- **users.txt**: Stores username and password hashes

## Future Enhancements

- [ ] Add database support (MySQL, MongoDB)
- [ ] Implement salted hashing for enhanced security
- [ ] Add two-factor authentication
- [ ] Create a GUI with Swing/JavaFX
- [ ] Add user profile management
- [ ] Implement password reset functionality

## License

This project is open source and available under the MIT License.

## Author

**Syed Junaid**  
[GitHub Profile](https://github.com/syedjunaid-max)

## Contributing

Contributions are welcome! Feel free to:
- Fork the repository
- Create a feature branch
- Submit a pull request

## Support

If you have any questions or issues, please open an issue on the [GitHub Issues](https://github.com/syedjunaid-max/Encrypt-and-Decrypt/issues) page.

---

**Last Updated**: 2026-03-06  
**Status**: Active Development
