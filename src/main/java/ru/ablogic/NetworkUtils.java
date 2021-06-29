package ru.ablogic;

public class NetworkUtils {
    public static void getConn()  {
        try {
        Thread.sleep(2000);} // Деламе таймаут 2 секунды. Тест завалится.
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return;
    }
}
