package com.syrous.lib_bluetooth;

import android.app.Activity;

public class PosPrinter60mm extends PosPrinter {

  public PosPrinter60mm(Activity activity) {
    super(activity);
  }

  @Override
  int getCharsOnLine() {
    return 32;
  }
}
