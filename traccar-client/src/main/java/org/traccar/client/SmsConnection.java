/*
 * Copyright 2012 - 2013 Anton Tananaev (anton.tananaev@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.traccar.client;

import java.io.Closeable;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Asynchronous connection
 *
 * All methods should be called from UI thread only.
 */
public class SmsConnection {

    public static final String LOG_TAG = "Traccar.SmsConnection";
    public static final int SOCKET_TIMEOUT = 10 * 1000;

    private Context context;

    private boolean closed;
    private boolean busy;

    public boolean isClosed() {
        return closed;
    }

    public boolean isBusy() {
        return busy;
    }

    public SmsConnection(Context context) {
        this.context = context;
        closed = false;
        busy = false;
    }

    public void send(String number, String message) {
        busy = true;

        new AsyncTask<String, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(String... params) {
                try {
                    String SENT = "SMS_SENT";
                    String DELIVERED = "SMS_DELIVERED";

                    PendingIntent sentPI = PendingIntent.getBroadcast(context, 0,
                            new Intent(SENT), 0);

                    PendingIntent deliveredPI = PendingIntent.getBroadcast(context, 0,
                            new Intent(DELIVERED), 0);

                    context.registerReceiver(new SmsBroadcastReceiver(), new IntentFilter(SENT));
                    context.registerReceiver(new SmsBroadcastReceiver(), new IntentFilter(DELIVERED));

                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(params[0], null, params[1], sentPI, deliveredPI);
                    return true;
                } catch (Exception e) {
                    Log.w(LOG_TAG, e.getMessage());
                    return false;
                }
            }

            @Override
            protected void onCancelled() {
                if (!closed) {
                    busy = false;
                }
            }

            @Override
            protected void onPostExecute(Boolean result) {
                if (!closed) {
                    busy = false;
                }
            }

        }.execute(number, message);

    }

}