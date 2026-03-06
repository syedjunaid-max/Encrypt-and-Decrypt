package gui;

import storage.UserStorage;

import javax.swing.*;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 80, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 30, 160, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 70, 80, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 160, 25);
        add(passwordField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(60, 110, 100, 30);
        loginBtn.addActionListener(e -> {
			try {
				handleLogin(e);
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		});
        add(loginBtn);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(170, 110, 100, 30);
        registerBtn.addActionListener(e -> {
			try {
				handleRegister(e);
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        add(registerBtn);
    }

    private void handleLogin(ActionEvent e) throws HeadlessException, Exception {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        try {
            if (UserStorage.validateLogin(username, password)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                dispose();
                new Dashboard().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void handleRegister(ActionEvent e) throws HeadlessException, Exception {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        try {
            if (UserStorage.registerUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Registration successful! Please login.");
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists!");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
