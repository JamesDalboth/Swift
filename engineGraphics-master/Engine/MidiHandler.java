package Engine;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
public class MidiHandler
{
    public boolean[] keys = new boolean[24];
    private MidiDevice device;
    public MidiHandler()
    {
        for (boolean key: keys) {
            key = false;
        }
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (int i = 0; i < infos.length; i++) {
            try {
                device = MidiSystem.getMidiDevice(infos[i]);
                //does the device have any transmitters?
                //if it does, add it to the device list
                System.out.println(infos[i]);

                //get all transmitters
                List<Transmitter> transmitters = device.getTransmitters();
                //and for each transmitter

                for(int j = 0; j<transmitters.size();j++) {
                    //create a new receiver
                    transmitters.get(j).setReceiver(
                            //using my own MidiInputReceiver
                            new MidiInputReceiver(device.getDeviceInfo().toString())
                    );
                }

                Transmitter trans = device.getTransmitter();
                trans.setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString()));

                //open each device
                device.open();//if code gets this far without throwing an exception
                //print a success message
                System.out.println(device.getDeviceInfo()+" Was Opened");

            } catch (MidiUnavailableException e) {}
        }

    }
    //tried to write my own class. I thought the send method handles an MidiEvents sent to it
    public class MidiInputReceiver implements Receiver {
        public String name;
        public MidiInputReceiver(String name) {
            this.name = name;
        }
        public void send(MidiMessage msg, long timeStamp) {
            ShortMessage m = (ShortMessage) msg;
            int noteInd = m.getData1() - 48;
            System.out.println(noteInd);
            if (noteInd < 24 && noteInd >= 0) {
                if (m.getStatus() == ShortMessage.NOTE_ON) {
                    keys[noteInd] = true;
                } else {
                    keys[noteInd] = false;
                }
            }
        }
        public void close() {}
    }

    public void close() {
        device.close();
    }
}