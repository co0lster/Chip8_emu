public class Main {
    public static void main(String[] args) {

        Chip8 chip8 = new Chip8();
        chip8.init();

        for(;;)
        {
            chip8.emulate();

//            if(Chip8.drawFlag)
//                drawGraphics();
//
//            chip8.setKeys();
        }




    }
}
