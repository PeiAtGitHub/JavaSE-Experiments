package pei.java.design.pattern.lab.facade;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * @author pei
 *
 */
public class FacadeDemo {

    public static void main(String[] args) {
        new Computer().startComputer();
    }
    
}

/* 
 * Computer is a Facade 
 */
class Computer {
    private PowerUnit powerUnit = new PowerUnit();
    private CPU cpu = new CPU();
    private Memory memory = new Memory();
    private HardDrive hardDrive = new HardDrive();
    
    private final long BOOT_ADDRESS = 8008l;
    private final long BOOT_SECTOR = 1l;
    private final int SECTOR_SIZE = 128;

    /*
     * the facade method
     */
    public void startComputer() {
        System.out.println("Starting computer...");
        powerUnit.powerOn();
        memory.load(BOOT_ADDRESS, hardDrive.read(BOOT_SECTOR, SECTOR_SIZE));
        cpu.point(BOOT_ADDRESS);
        cpu.execute();
    }
}

class PowerUnit {
    public void powerOn() {
        System.out.println("Power on.");
    }

    public void powerOff() {
        System.out.println("Power off.");
    }
}

class CPU {
    private long pointer;
    
    public void point(long address) {
        pointer = address;
    }

    public void execute() {
        System.out.println("CPU executing command at " + pointer);
    }
}

class Memory {
    public void load(long address, byte[] data) {
        System.out.println("Memory loading data to address " + address);
        System.out.println(Arrays.toString(data));
    }
}

class HardDrive {
    public byte[] read(long lba, int size) {
        System.out.format("Reading %s bytes data from hard drive sector %s.%n", size, lba);
        byte[] hdData = new byte[size];
        new Random().nextBytes(hdData);
        return hdData;
    }
}


