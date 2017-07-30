package vapps.socketiodemo;

import android.app.Application;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by Mahmoud.Ramadan on 7/25/2017.
 */

public class MyApp extends Application {

    private static  MyApp mInstance;
    private Socket socket;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        try {
            socket = IO.socket(AppConst.SOCKET_URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mInstance = null;
    }

    public static MyApp getInstance(){
        return mInstance;
    }

    public  Socket getSocket(){
        return socket;
    }
}
