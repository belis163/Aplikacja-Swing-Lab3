package com.company;

import org.mariuszgromada.math.mxparser.*;

import javax.swing.*;
import java.awt.event.*;
import java.rmi.server.ExportException;
import java.security.Key;

public class Main extends JFrame /*implements KeyListener*/{


    private javax.swing.JButton jButton1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private JScrollPane jScrollPane;
    private double lastResult;


    public Main() {
        initComponents();
    }

    private void initComponents() {

        this.setTitle("Calc");

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jScrollPane = new JScrollPane(jTextArea1);

        jTextArea1.setEditable(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Evaluate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
                Expression expression = new Expression(jTextField1.getText());
                double wynik = expression.calculate();
                String dzialanie = jTextField1.getText();
                if(Double.isNaN(wynik))
                {
                    String poprzednie = jTextArea1.getText();
                    jTextArea1.setText(poprzednie + "\n");
                    String errorMessage = expression.getErrorMessage();
                    JOptionPane.showMessageDialog(null, errorMessage, "Not a Number!!",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {
                    String poprzednie = jTextArea1.getText();
                    if(poprzednie.isEmpty())
                    jTextArea1.setText(dzialanie + " = " + wynik);
                    else {
                        jTextArea1.setText(poprzednie + "\n" + dzialanie + " = " + wynik);
                        lastResult = wynik;
                    }
                }
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
                Expression expression = new Expression(jTextField1.getText());
                double wynik = expression.calculate();
                String dzialanie = jTextField1.getText();
                if(Double.isNaN(wynik))
                {
                    String poprzednie = jTextArea1.getText();
                    jTextArea1.setText(poprzednie + "\n");
                    String errorMessage = expression.getErrorMessage();
                    JOptionPane.showMessageDialog(null, errorMessage, "Not a Number!!",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {
                    String poprzednie = jTextArea1.getText();
                    if(poprzednie.isEmpty())
                        jTextArea1.setText(dzialanie + " = " + wynik);
                    else {
                        jTextArea1.setText(poprzednie + "\n" + dzialanie + " = " + wynik);
                        lastResult = wynik;
                    }
                }
            }
        });
       // jTextField1.addKeyListener(this);
        jList1.setModel(new javax.swing.DefaultListModel<String>() {
            String[] strings = {"Sinus", "Cosinus", "Tangens", "Cotangens", "Sqrt", "+", "-", "*"};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });


        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);

                if(isFocusable())
                {
                    String selected = jList1.getSelectedValue().toString();
                    if(selected=="Sinus")
                    {
                        jTextField1.setText("sin()");
                        jTextField1.grabFocus();
                        jTextField1.select(4,4);
                    }
                    else if(selected == "Cosinus")
                    {
                        jTextField1.setText("cos()");
                        jTextField1.grabFocus();
                        jTextField1.select(4,4);
                    }
                    else if(selected == "Tangens")
                    {
                        jTextField1.setText("tg()");
                        jTextField1.grabFocus();
                        jTextField1.select(3,3);
                    }
                    else if(selected == "Cotangens")
                    {
                        jTextField1.setText("ctan()");
                        jTextField1.grabFocus();
                        jTextField1.select(5,5);
                    }
                    else if(selected == "Sqrt")
                    {
                        jTextField1.setText("sqrt()");
                        jTextField1.grabFocus();
                        jTextField1.select(5,5);
                    }
                    else if(selected == "+")
                    {
                        jTextField1.setText("+");
                        jTextField1.grabFocus();
                        jTextField1.select(0,0);
                    }
                    else if(selected == "-")
                    {
                        jTextField1.setText("-");
                        jTextField1.grabFocus();
                        jTextField1.select(0,0);
                    }
                    else if(selected == "*")
                    {
                        jTextField1.setText("*");
                        jTextField1.grabFocus();
                        jTextField1.select(0,0);
                    }

                }

            }
        });


        jScrollPane2.setViewportView(jList1);

        jMenu1.setText("Opcje");

        jMenuItem1.setText("Reset");
        jMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField1.setText("");
                jTextArea1.setText("");
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);

            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                                        .addComponent(jTextField1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane2)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane2)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );



        pack();

        jTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
               if (e.getKeyCode() == KeyEvent.VK_UP) {
                    jTextField1.setText(Double.toString(lastResult));
                }
            }
        });

    }



    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {

    }


    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }


    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

/*
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent event) {
        Object source = event.getSource();
        int keyCode = event.getKeyCode();
        if (source == jTextArea1 && keyCode == KeyEvent.VK_UP) {
            jTextField1.setText(Double.toString(lastResult));
        }
    }*/
}
