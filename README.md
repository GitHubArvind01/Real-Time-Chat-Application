# Real-Time Chat Application in Java

## Project Description
This project is a real-time chat application implemented in Java. It consists of two primary components: a server to manage client connections and communication, and a client application to allow users to send and receive messages. The project demonstrates core concepts of network programming, multithreading, and graphical user interface (GUI) development in Java.

## ScreenShots 
![Screenshot 2024-12-05 144002](https://github.com/user-attachments/assets/0c2e5d17-eee4-4ef3-82b8-f8aafdd8cdc3)
![Screenshot 2024-12-05 143937](https://github.com/user-attachments/assets/885aa8bf-7acd-4bd9-a7b6-fd57f81969e2)


## Key Features

1. **Real-Time Communication**
   - Clients can connect to the server and exchange text messages in real time.

2. **User-Friendly GUI**
   - A visually appealing interface with intuitive controls for message input and display.

3. **Icons and Graphics**
   - Custom icons are used for buttons (e.g., "Send" button) and other UI elements to enhance the user experience.

4. **Multithreading**
   - The server is designed to handle multiple clients concurrently using threads.

5. **Scalability**
   - The architecture can be extended to add new features like message encryption, user authentication, or file sharing.

## Technical Details

### 1. Server (Server.java)
The Server program is the backbone of the application. It listens for incoming connections from clients and relays messages between them.

- **Responsibilities**:
  - Initializes a server socket to listen on a specified port.
  - Handles multiple client connections using a separate thread for each client.
  - Receives messages from a connected client and broadcasts them to other clients.

- **Key Components**:
  - **Socket Programming**: Implements the server's communication channel.
  - **Threading**: Creates a new thread for each connected client to ensure seamless communication.
  - **Message Broadcasting**: Ensures that all connected clients receive messages in real time.

- **Code Snippet (Initialization)**:
```java
ServerSocket serverSocket = new ServerSocket(12345);
while (true) {
    Socket clientSocket = serverSocket.accept();
    // Handle client in a new thread
    new ClientHandler(clientSocket).start();
}
```

### 2. Client (Client.java)
The Client program provides the user interface for the chat application, allowing users to connect to the server, send messages, and view received messages.

- **Responsibilities**:
  - Connects to the server using a socket.
  - Provides a GUI for user interaction.
  - Listens for incoming messages from the server and displays them.

- **Graphical User Interface (GUI)**:
  - Built using Swing, the interface includes:
    - A message input field.
    - A "Send" button with a custom icon (`send_icon.png`).
    - A chat area where messages are displayed.

- **Code Snippet (GUI Initialization)**:
```java
JTextField messageField = new JTextField();
JButton sendButton = new JButton(new ImageIcon(getClass().getResource("/icons/send_icon.png")));
sendButton.addActionListener(e -> sendMessage());
```

## Dependencies and Prerequisites

- **Java Development Kit (JDK)**: Ensure JDK 8 or later is installed.
- **Classpath Configuration**: The `icons` folder must be included in the classpath for the application to locate and load images correctly.

## How It Works

1. **Server Initialization (Step 1)**:
   - First, start the `Server.java` program.
   - It listens for client connections and assigns each client a separate thread.

2. **Client Connection (Step 2)**:
   - Next, start the `Client.java` program to connect to the server.

3. **Message Exchange**:
   - The user types a message in the text field and clicks the "Send" button.
   - The message is sent to the server, which broadcasts it to all connected clients.

4. **Message Display**:
   - Received messages are displayed in the chat area of the client interface.

## Challenges Addressed

- **Resource Path Management**:
  - Custom icons are loaded dynamically using `getClass().getResource()` to avoid hardcoding absolute paths.

- **Thread Safety**:
  - Multi-threaded operations are synchronized to prevent race conditions.

- **Error Handling**:
  - Includes robust exception handling for scenarios such as lost client connections or unavailable resources.

## Potential Enhancements

- **User Authentication**: Implement a login system to secure the chat.
- **Message Encryption**: Add end-to-end encryption to ensure privacy.
- **File Sharing**: Extend the application to support file uploads and downloads.
- **Group Chats**: Allow clients to create or join private chat groups.

