package br.com.printer.enums;

public enum EnumStateMachine {
    WAIT_STX,
    WAIT_PrinterCode,
    WAIT_OPCODE,
    WAIT_ETX
}
