package ru.netology;


import java.io.BufferedOutputStream;

public class Main {
    final static int PORT = 9999;

    public static void main(String[] args) {

        final var server = new Server(PORT);

        server.start();

    }
}
