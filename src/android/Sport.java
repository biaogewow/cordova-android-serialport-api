package android_serialport_api;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.math.BigInteger;

import android_serialport_api.SerialPort;
import android_serialport_api.SerialPortFinder;

public class Sport extends CordovaPlugin {

    private CallbackContext mCallbackContext;

    private SerialPort serialPort = null;
    protected OutputStream mOutputStream;
    private InputStream mInputStream;
    
    public void open(String message, String port, int baudrate) throws JSONException, SecurityException, IOException, InvalidParameterException {

        try {
                File file = new File (port);
                serialPort = new SerialPort(file, baudrate, 0);

                mOutputStream = serialPort.getOutputStream();
                mOutputStream.write(new BigInteger(message, 16).toByteArray());
                serialPort.close();
                serialPort = null;

                mCallbackContext.success("success");
                
        } catch (IOException ex) {
                ex.printStackTrace();
                
                mCallbackContext.error("open fail");
        }
    }

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        mCallbackContext = callbackContext;

        if (action.equals("send")) {

            try {
                String port = data.getJSONObject(0).getString("port");
                int baudrate = data.getJSONObject(0).getInt("baudrate");
                String message = data.getJSONObject(0).getString("data");

                this.open(message, port, baudrate);

            } catch (IOException ex) {
                
                callbackContext.error("error");
                ex.printStackTrace();

            }

            return true;

        } else {

            return false;

        }
    }
}
