import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SizeInput extends JDialog implements ActionListener {
    JLabel hintRow;
    JLabel hintCol;
    JTextField inputRow;
    JTextField inputCol;
    JButton buttonConfirm;
    int row = 32;
    int col = 32;

    public SizeInput() {
        this.setLayout(null);
        buttonConfirm = new JButton("确认");
        hintRow = new JLabel("行:");
        hintCol = new JLabel("列:");
        inputRow = new JTextField();
        inputCol = new JTextField();

        inputRow.addActionListener(this);
        inputCol.addActionListener(this);

        this.setTitle("请输入长宽信息");
        this.setBounds(500, 300, 200, 200);
        hintRow.setBounds(25, 25, 25, 25);
        hintCol.setBounds(25, 75, 25, 25);
        inputRow.setBounds(75, 25, 100, 25);
        inputCol.setBounds(75, 75, 100, 25);
        buttonConfirm.setBounds(50, 115, 75, 25);

        inputRow.addActionListener(this);
        inputCol.addActionListener(this);
        buttonConfirm.addActionListener(this);
        this.add(buttonConfirm);
        this.add(hintRow);
        this.add(hintCol);
        this.add(inputRow);
        this.add(inputCol);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (inputRow.getText().equals("") || inputCol.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "行和列不能为空！");
            return;
        }
        if (s == "确认") {
            int tRow = Integer.parseInt(inputRow.getText());
            int tCol = Integer.parseInt(inputCol.getText());
//            System.out.println(tRow + " " + tCol);
            this.setRow(tRow);
            this.setCol(tCol);
            this.dispose();
        }
    }
}
