package gui;

import crypto.AESUtil;
import crypto.HashUtil;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class Dashboard extends JFrame {

    private JButton encryptButton, decryptButton, showHashButton;
    private JTextField passwordField;

    public Dashboard() {
        setTitle("Secure File Sharing");
        setSize(420, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel label = new JLabel("Password:");
        label.setBounds(30, 30, 100, 25);
        add(label);

        passwordField = new JTextField();
        passwordField.setBounds(130, 30, 200, 25);
        add(passwordField);

        encryptButton = new JButton("Encrypt File");
        encryptButton.setBounds(30, 80, 120, 30);
        encryptButton.addActionListener(e -> encryptFile());
        add(encryptButton);

        decryptButton = new JButton("Decrypt File");
        decryptButton.setBounds(160, 80, 120, 30);
        decryptButton.addActionListener(e -> decryptFile());
        add(decryptButton);

        showHashButton = new JButton("Show SHA-256");
        showHashButton.setBounds(290, 80, 120, 30);
        showHashButton.addActionListener(e -> showHash());
        add(showHashButton);
    }

    private void encryptFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File inputFile = fileChooser.getSelectedFile();
            File outputFile = new File(inputFile.getParent(), inputFile.getName() + ".enc");

            try {
                String password = passwordField.getText();
                SecretKey key = AESUtil.getKeyFromPassword(password);
                AESUtil.encryptFile(inputFile, outputFile, key);
                JOptionPane.showMessageDialog(this, "File Encrypted Successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Encryption Failed: " + ex.getMessage());
            }
        }
    }

    private void decryptFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File inputFile = fileChooser.getSelectedFile();
            String fileName = inputFile.getName().replace(".enc", "");
            File outputFile = new File(inputFile.getParent(), "decrypted_" + fileName);

            try {
                String password = passwordField.getText();
                SecretKey key = AESUtil.getKeyFromPassword(password);
                AESUtil.decryptFile(inputFile, outputFile, key);
                JOptionPane.showMessageDialog(this, "File Decrypted Successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Decryption Failed: " + ex.getMessage());
            }
        }
    }

    private void showHash() {
        try {
            String password = passwordField.getText();
            String hash = HashUtil.sha256(password);
            JOptionPane.showMessageDialog(this, "SHA-256 Hash:\n" + hash);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error generating hash: " + ex.getMessage());
        }
    }
}
