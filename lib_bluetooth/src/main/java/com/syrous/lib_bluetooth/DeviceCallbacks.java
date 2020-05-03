package com.syrous.lib_bluetooth;

public interface DeviceCallbacks {
    void onConnected();

    void onFailure();

    void onDisconnected();
}
