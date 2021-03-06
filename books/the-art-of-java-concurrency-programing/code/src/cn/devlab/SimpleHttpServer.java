package cn.devlab;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhong on 2016/11/29.
 */
public class SimpleHttpServer {

    static ThreadPoll<HttpRequestHanlder> threadPooll  = new DefaultThreadPool<>();

    static String basePath;

    static ServerSocket serverSocket;

    static int port = 8080;

    public  static void setPort(int port){
        if(port > 0){
            SimpleHttpServer.port = port;
        }
    }

    public static void setBasePath(String basePath){
        if(basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()){
            SimpleHttpServer.basePath = basePath;
        }
    }

    public static void start() throws Exception{

        serverSocket = new ServerSocket(port);
        Socket socket = null;

        while ((socket = serverSocket.accept()) != null){
            threadPooll.execute(new HttpRequestHanlder(socket));
        }

        socket.close();
    }







    static class HttpRequestHanlder implements Runnable{
        private Socket socket;

        public HttpRequestHanlder(Socket socket) {
            this.socket = socket;
        }



        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            InputStream in = null;

            try{
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();

                String filePath = basePath + header.split(" ")[1];

                out = new PrintWriter(socket.getOutputStream());
                if(filePath.endsWith("jgp") || filePath.endsWith("ico")){
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i = 0;
                    while ((i = in.read()) != -1){
                        baos.write(i);
                    }

                    byte[] array = baos.toByteArray();
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-type: image/jpeg");
                    out.println("Content-Length: " + array.length);
                    socket.getOutputStream().write(array, 0, array.length);
                }else{
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out = new PrintWriter(socket.getOutputStream());
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-type: text/html");

                    while ((line = br.readLine()) != null){
                        out.print(line);
                    }
                }
                out.flush();
            }catch (Exception e){
                out.print("HTTP/1.1 500");
                out.print("");
                out.flush();
            }finally {
                try {
                    br.close();
                    reader.close();
                    out.close();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
