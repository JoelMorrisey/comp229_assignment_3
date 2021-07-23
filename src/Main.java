import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.Duration;
import java.time.Instant;

class Main extends JFrame {
    
    class App extends JPanel implements MouseListener {
        
        Stage stage;

        public App() {
            setPreferredSize(new Dimension(880, 720));
            this.addMouseListener(this);
            stage = StageReader.readStage("data/stage1.rvb");
        }

        @Override
        public void paint(Graphics g) {
            stage.paint(g, getMousePosition());
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            stage.mouseClicked(e.getX(), e.getY());
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

    }

    public static void main(String[] args) throws Exception {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new java.io.File("data/full_pack_2025.ttf")));
        Main window = new Main();
        window.run();
    }

    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        App canvas = new App();
        this.setContentPane(canvas);
        this.pack();
        this.setVisible(true);
    }

    public void run() {
        while (true) {
            Instant startTime = Instant.now();
            this.repaint();
            Instant endTime = Instant.now();
            long howLong = Duration.between(startTime, endTime).toMillis();
            try{
                Thread.sleep(20l - howLong);
            } catch (InterruptedException e){
                System.out.println("thread was interrupted, but who cares?");
            } catch (IllegalArgumentException e){
                System.out.println("application can't keep up with framerate");
            }
        }
    }
}