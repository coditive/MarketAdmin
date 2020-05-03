package com.syrous.lib_bluetooth;

public interface Printer {
    void send(Ticket ticket);

    boolean isAvailable();

    boolean isEnabled();

    boolean isConnected();

    void setDeviceCallbacks(DeviceCallbacks callbacks);

}
