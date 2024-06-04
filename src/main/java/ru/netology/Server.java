package ru.netology;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    final int COUNT_PORT = 64;



    ExecutorService executorService = Executors.newFixedThreadPool(COUNT_PORT);
    /*
    ExecutorService с фиксированным количеством (n) потоков обладает следующей логикой:
    1.Максимум n потоков будут активны для обработки задач.
    2.Если передано более n задач, они будут удерживаться в очереди до момента, пока потоки не освободятся.
    3.Если в работе одного из потоков произойдет сбой и он завершит свою работу, будет создан новый поток на место сломанного.
    4.Любой поток из пула активен до тех пор, пока пул не закрыт.
     */
    /*
    ConcurrentHashMap - это тот же HashMap, но рассчитан для работы в многопоточной среде.
    Главным отличием является то, что ему не нужно иметь синхронизированные блоки данных для доступа или записи информации.
    То есть ему ненужно блокировать самого себя для других потоков.
     */

    public Server(int port) {
        this.port = port;
    }

    public void start() {

        try (final var serverSocket = new ServerSocket(port)) { //создали экземпляр ServerSocket

            while (true) {
                final var socket = serverSocket.accept(); //получаем экземпляр socket
                var thread = new MyHandler(socket);

                //Метод submit() в интерфейсе ExecutorService ставит задачу в очередь на выполнение.
                executorService.submit(thread);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

