package ru.netology;


import java.io.BufferedOutputStream;
import java.io.IOException;

import static ru.netology.Server.addHandler;
import static ru.netology.Server.getHandlers;

public class Main {
    final static int PORT = 9999;

    public static void main(String[] args) {

        final var server = new Server(PORT);

        /*
        // добавление хендлеров (обработчиков)
        addHandler("GET", "/messages", (request, responseStream) -> {
            try {
                MyHandler.pathFound(request, responseStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addHandler("POST", "/messages", (request, responseStream) -> {
            try {
                MyHandler.pathFound(request, responseStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println(getHandlers());
        */

        server.start();


    }
}
