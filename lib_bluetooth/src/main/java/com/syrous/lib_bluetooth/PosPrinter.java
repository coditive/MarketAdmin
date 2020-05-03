package com.syrous.lib_bluetooth;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.syrous.lib_bluetooth.bluetooth.BTService;
import com.syrous.lib_bluetooth.bluetooth.SearchPrinterDialog;

import org.apache.commons.lang3.ArrayUtils;

import java.util.List;

public abstract class PosPrinter implements Printer {
    private final Activity activity;

    private final BTService btService;
    private OnStateChangedListener onStateChangedListener;

    int charsOnLine;
    String charsetName = "ASCII";

    PosPrinter(Activity activity) {
        this.activity = activity;
        this.charsOnLine = getCharsOnLine();

        btService = new BTService(activity, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (onStateChangedListener != null) {
                    onStateChangedListener.onChanged(msg.arg1, msg);
                }
            }
        });
        btService.start();

        PrinterFonts.setShift(0);
        PrinterFonts.setFontGroup(PrinterFonts.FontGroup.BASIC);
    }

    abstract int getCharsOnLine();

    @Override
    public void send(Ticket ticket) {
        if (isConnected()) {
            List<Byte> data = ticket.getData();
            btService.send(ArrayUtils.toPrimitive(data.toArray(new Byte[data.size()])));
            return;
        }

        ticket.saveFiscalData();
    }

    @Override
    public boolean isAvailable() {
        return btService.isAvailable();
    }

    @Override
    public boolean isEnabled() {
        return btService.isEnabled();
    }

    @Override
    public boolean isConnected() {
        return btService.getState() == BTService.STATE_CONNECTED;
    }

    @Override
    public void setDeviceCallbacks(DeviceCallbacks callbacks) {
        btService.setDeviceCallbacks(callbacks);
    }

    public void setStateChangedListener(OnStateChangedListener listener) {
        onStateChangedListener = listener;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        btService.onActivityResult(requestCode, resultCode, data);
    }

    public void connect() {
        if (!isEnabled()) {
            btService.requestBTActivation(activity, new BTService.BTCallback() {
                @Override
                public void onEnableBTResult(boolean isEnabled) {
                    if (isEnabled) connect();
                }
            });

            return;
        }

        new SearchPrinterDialog(activity, btService) {

            @Override
            public void onCancelled() {
            }
        };
    }

    public void disconnect() {
        btService.stop();
    }

    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }

    public interface OnStateChangedListener {
        void onChanged(int state, Message msg);
    }
}
