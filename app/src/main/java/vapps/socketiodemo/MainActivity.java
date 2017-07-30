package vapps.socketiodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {

    private Socket socket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        JSONObject job = new JSONObject();
        try {
            job.put("id","ryakgC9i4QnBxbhmwAoiyMzh9TdFbJAhccL3M0DwgbXgWOjZlhZnktPDlufDinUe");
            job.put("userId","2");
        } catch (JSONException e) {
            e.printStackTrace();
        }



        setContentView(R.layout.activity_main);

        socket = MyApp.getInstance().getSocket();

        socket.on(AppConst.EVENT_NAME, eventListener);
        socket.connect();

        socket.emit(AppConst.AUTH_EVENT,job);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        socket.disconnect();
        socket.off(AppConst.EVENT_NAME, eventListener);
    }

    private Emitter.Listener eventListener = new Emitter.Listener() {
        @Override
        public void call(final  Object... args) {
           runOnUiThread(new Runnable() {
               @Override
               public void run() {
                   JSONObject data = (JSONObject) args[0];
                   Toast.makeText(MainActivity.this, "data"+data.toString(), Toast.LENGTH_SHORT).show();
               }
           });
        }
    };

}
