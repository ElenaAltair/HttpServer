package ru.netology;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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

    private final static Map<String, Map<String, Handler>> handlers = new ConcurrentHashMap<>();
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
            /*
            ServerSocket — это класс в пакете java.net, который реализует серверный сокет.
            Он ожидает запросы, приходящие от клиентов по сети, и может отправлять ответ.
             */
            while (true) {
                final var socket = serverSocket.accept(); //получаем экземпляр socket
                /*
                Метод accept() класса ServerSocket используется для принятия входящего запроса к сокету.
                Для завершения запроса менеджер безопасности проверяет адрес хоста,
                номер порта и локальный порт.
                 */
                var thread = new MyHandler(socket);

                //Метод submit() в интерфейсе ExecutorService ставит задачу в очередь на выполнение.
                executorService.submit(thread);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void addHandler(String method, String path, Handler handler) {

        if (handlers.containsKey(method)) {
            handlers.get(method).put(path, handler);
        } else {
            handlers.put(method, new ConcurrentHashMap<>(Map.of(path, handler)));
        }

    }

    public static Map<String, Map<String, Handler>> getHandlers() {
        return handlers;
    }

}

