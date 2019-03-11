public class Chip8 {

    /* Chip8 has 35 opcodes each one 2 bytes long */
    short opcode;
    byte[] memory = new byte[4096];

    /* 15 8-bit general purpose registers named from V0 to VE. The 16th register is used as a carry flag. */
    byte[] V = new byte[16];

    /*
    * indexRegister and programCounter can have a value from 0x000 to 0xFFF
    * 0x000-0x1FF - Chip 8 interpreter (contains font set in emu)
    * 0x050-0x0A0 - Used for the built in 4x5 pixel font set (0-F)
    * 0x200-0xFFF - Program ROM and work RAM
    */
    short indexRegister;
    short programCounter;

    /*
    * Graphics are black and white on 2048 pixels
    * Drawing is done with XOR operation
    * if pixel if off the VR register is set
    */
    boolean[] gfx = new boolean[64 * 32];

    /* Timer count at 60Hz down to 0 */
    byte delay_timer;
    byte sound_timer;

    short stack[] = new short[16];
    short stackPointer;

    /* chip8 has a HEX based keypad (0x0 - 0xF) */
    byte[] key = new byte [16];

    public void emulate()
    {
        fetch();
        decode();
        updateTimers();

    }

    private void updateTimers() {
        if(delay_timer > 0)
            --delay_timer;
        if(sound_timer > 0)
        {
            if(sound_timer == 1)
                System.out.println("BEEP");
            --sound_timer;
        }
    }

    private void decode() {
        switch (opcode & 0xF000)
        {
            case 0x000:
                switch(opcode & 0x000F)
                {
                    case 0x000:
                        // clear the screen
                        break;
                    case 0x00E:
                        // returms from subrutine
                        break;
                    default:
                        System.out.println("Unknown opcode: " + opcode);

                }

            case 0xA000: // ANNN: Sets I to adress NNN
                indexRegister  = (short) (opcode & 0x0FFF);
                programCounter += 2;
                break;

                default:
                    System.out.println("Unknown opcode: " + opcode);
        }
    }

    private void fetch() {
        opcode = (short) (memory[programCounter] << 8 | memory[programCounter +1]);
    }

    public void init(){
        programCounter = 0x200;
        opcode = 0;
        indexRegister = 0;
        stackPointer = 0;

        for ( int i = 0; i < 80; ++i)
        {
//            memory[i + 512] = chip8_fontset[i];
        }
    }

    public void loadProgram()
    {

    }




}
