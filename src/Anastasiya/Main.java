package Anastasiya;

import javax.swing.*;

public class Main extends JFrame{
    private JPanel contenPanel;
    private static JButton rysuj;

    public static JButton getRysuj() {
        return rysuj;
    }

    private static JButton przeniesc;
    public static JButton getPrzeniesc() {
        return przeniesc;
    }

    private static JButton dodac;
    public static JButton getDodac(){
        return dodac;
    }

    public Main(){
        super("Zadanie 2");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        contenPanel = new JPanel();
        contenPanel.setLayout(null);

        rysuj = new JButton("Rysuj");
        przeniesc = new JButton("Przenieść");
        dodac = new JButton("Dodaj");
        rysuj.setBounds(10, 170, 80, 30);
        dodac.setBounds(100, 170, 80, 30);
        przeniesc.setBounds(190, 170, 100, 30);

        MyPanel panel = new MyPanel();

        contenPanel.add(panel);
        contenPanel.add(rysuj);
        contenPanel.add(dodac);
        contenPanel.add(przeniesc);


        setContentPane(contenPanel);
        setSize(400,250);
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    Main test = new Main();
                    test.setVisible(true);
                }catch (Exception e){
                    e.printStackTrace(System.err);
                }
            }
        });
    }
}