package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Invoice extends JFrame implements ActionListener {




    public JTextArea textArea ;
    JButton createNewInvoice, deleteInvoice, save, cancel;
    JLabel invoiceDate,customername,totalInvoice,invoiceNumber;
    JTextField enterdate, entername,nom,enterInvoice;


    private JTable table;
    private String[] cols= {"No.","Date","Customer","Total"};
    private String[][] data= {
            {"2","22/1/2019","Ali","3000"},
            {"1","22/1/2019","Ibrahim","200"}
    };

    private JTable table2;
    private String[] cols2= {"No","Item Name","Item Price","count","Item Total"};
    private String[][] data2= {
            {"2","Laptop","3000","1","3000"},

    };

    private JMenuBar mb;

    private JMenu fileMenu;

    private JMenuItem saveItem;
    private JMenuItem openItem;

    private JTextArea text;



    Invoice()
    {
        //Start

      setBounds(300,100,1020,740);
      setLayout(new FlowLayout());

        text =new JTextArea();
        mb=new JMenuBar();

        fileMenu=new JMenu("File");

        openItem=new JMenuItem("Open",'o');
        openItem.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_DOWN_MASK));
        openItem.addActionListener(this);
        openItem.setActionCommand("O");

        saveItem =new JMenuItem("Save Item",'s');
        saveItem.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_DOWN_MASK));
        saveItem.addActionListener(this);
        saveItem.setActionCommand("S");


        setJMenuBar(mb);
        mb.add(fileMenu);

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        add(new JScrollPane(text));




        invoiceDate = new JLabel("Invoice Date");
        add(invoiceDate);

        enterdate = new JTextField();
        add(enterdate);

        customername = new JLabel("Customer Name");
        add(customername);

        entername = new JTextField();

        add(entername);

        invoiceNumber = new JLabel("Invoice Number");
        add(invoiceNumber);

        nom = new JTextField();
        add(nom);
        nom.setVisible(false);


        totalInvoice = new JLabel("Total Invoice");
        add(totalInvoice);

        enterInvoice = new JTextField();
        add(enterInvoice);
        enterInvoice.setVisible(false);



        table = new JTable(data,cols);
        add(new JScrollPane (table));

        table2 = new JTable(data2,cols2);
        add(new JScrollPane (table2));

        createNewInvoice = new JButton("create New Invoice");
        createNewInvoice.setFocusable(false);
        add(createNewInvoice);

        deleteInvoice = new JButton("Delete Invoice");
        deleteInvoice.setFocusable(false);
        add(deleteInvoice);

        save= new JButton("save");
        save.setFocusable(false);
        add(save);

        cancel = new JButton("cancel");
        cancel.setFocusable(false);
        add(cancel);



        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }





    public static void main(String[] args)
    {
        new Invoice();


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()){
            case "O":

                openFile();

                break;

            case "S":

                saveContent();

                break;



        }

    }

    private void openFile()  {

        JFileChooser fc =new JFileChooser();
        int result =fc.showOpenDialog(this);
        if (result==JFileChooser.APPROVE_OPTION){
            String path= fc.getSelectedFile().getPath();
            FileInputStream fis=null;
            try {


                fis = new FileInputStream(path);
                int size = fis.available();
                byte[] b = new byte[size];
                fis.read(b);
                text.setText(new String(b));
            }catch (FileNotFoundException e){


            }catch (IOException e){

            }finally {


            }
            try {
                fis.close();
            }catch (IOException e){}


        }





    }

    private void saveContent(){

        JFileChooser fc =new JFileChooser();
        int result =fc.showOpenDialog(this);
        if (result==JFileChooser.APPROVE_OPTION){
            String path= fc.getSelectedFile().getPath();

            FileOutputStream fos=null;


            try {
                fos=new FileOutputStream(path);
            } catch (FileNotFoundException e) {}
            byte[] b=text.getText().getBytes();
            try {
                fos.write(b);
            } catch (IOException e) {}
            try {
                fos.close();
            } catch (IOException e) {}
        }


    }
}
