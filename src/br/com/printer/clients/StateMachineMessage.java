package br.com.printer.clients;

import br.com.printer.Message;
import br.com.printer.enums.EnumStateMachine;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.LinkedList;
import java.util.List;

public class StateMachineMessage {

    private EnumStateMachine state;
    private StringBuilder content;
    private Message msg;
    private  final Charset iso88591charset = Charset.forName("ISO-8859-1");

    public List<Message> trataDados(byte[] data, int length){
        List<Message> ret = new LinkedList<>();

        for (int i = 0; i < length; i++) {
            switch (state){
                case WAIT_STX:
                    if(data[i] == 0x02){
                        
                    }
                    break;
            }
        }




        return ret;
    }

}
