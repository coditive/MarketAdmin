package com.syrous.lib_bluetooth;

import android.app.Activity;

public class PosPrinter80mm extends PosPrinter {

  public PosPrinter80mm(Activity activity) {
    super(activity);
  }

  @Override
  int getCharsOnLine() {
    return 48;
  }
}
