import javax.swing.JFrame;

public class Driver {
    public static void main(String[] args){
        JFrame frame = new JFrame("Permutation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PermutationPanel panel = new PermutationPanel();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
