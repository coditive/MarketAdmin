package com.syrous.lib_bluetooth;

@SuppressWarnings("UnusedDeclaration")
class PrinterCommands {
  public static final byte[] INIT = {27, 64};
  public static final byte[] FEED_LINE = {10};
  public static final byte[] NULL_BYTE = {0x00};
  public static final byte[] FEED_PAPER_AND_CUT = {0x1D, 0x56, 66, 0x00};
  public static final byte[] SELECT_CYRILLIC_CHARACTER_CODE_TABLE = {0x1B, 0x74, 0x11};
  public static final byte[] SELECT_FONT = {0x1b, 0x21, 0x8};
}
