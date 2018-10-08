public class Chip8 {
    short opcode;
    byte[] memory = new byte[4096];
    byte[] V = new byte[16]; // 15 8-bit general purpose registers named from V0 to VE. The 16th register is used as a carry flag.
    short indexRegister;
    short programCounter;
    boolean[] gfx = new boolean[64 * 32];
    byte delay_timer;
    byte sound_timer;
    short stack[] = new short[16];
    short stackPointer;
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
