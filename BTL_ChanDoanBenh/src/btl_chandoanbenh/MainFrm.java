/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl_chandoanbenh;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import utils.IOFile;

/**
 *
 * @author Admin 88
 */
public class MainFrm extends javax.swing.JFrame {

    ArrayList<String> rulesListInput;
    ArrayList<String> listBieuHien;
    String factsResult;

    boolean coKetQuaXetNghiem = false;
    DefaultListModel listModel;

    public MainFrm() {
        initComponents();
        listModel = new DefaultListModel();
        rulesListInput = IOFile.readFromFile("tiensubenh.csv");
        listBieuHien = IOFile.readFromFile("bieuhienbenh.csv");
    }

    void disableGroupUongCon() {
        btnGroupThoiGianUong.clearSelection();
        btnGroupCachThucUong.clearSelection();

        ruou1.setEnabled(false);
        ruou2.setEnabled(false);
        ruou3.setEnabled(false);
        ruou4.setEnabled(false);
        ruou5.setEnabled(false);
    }

    void enableGroupUongCon() {
        ruou1.setEnabled(true);
        ruou2.setEnabled(true);
        ruou3.setEnabled(true);
        ruou4.setEnabled(true);
        ruou5.setEnabled(true);
    }

//    void showAllRule() {
//        rulesListInput = IOFile.readFromFile("rules.csv");
//        listModel = new DefaultListModel();
//        for (String item : rulesListInput) {
//            listModel.addElement(item);
//        }
//        jList1.setModel(listModel);
//    }
    // bieu hien nguoi dung chon
    String convertSelectionToString() {
        String facts = "";

        String gender = congender.getSelectedItem().toString();

        // bieu hien
        if (con1.isSelected()) {
            facts += con1.getActionCommand() + ",";
        }
        if (con2.isSelected()) {
            facts += con2.getActionCommand() + ",";
        }
        if (con3.isSelected()) {
            facts += con3.getActionCommand() + ",";
        }
        if (con4.isSelected()) {
            facts += con4.getActionCommand() + ",";
        }
        if (con5.isSelected()) {
            facts += con5.getActionCommand() + ",";
        }
        if (con6.isSelected()) {
            facts += con6.getActionCommand() + ",";
        }
        if (con7.isSelected()) {
            facts += con7.getActionCommand() + ",";
        }
        if (con8.isSelected()) {
            facts += con8.getActionCommand() + ",";
        }
        if (con9.isSelected()) {
            facts += con9.getActionCommand() + ",";
        }
        if (con10.isSelected()) {
            facts += con10.getActionCommand() + ",";
        }
//        if (con11.isSelected()) {
//            facts += con11.getActionCommand() + ",";
//        }
//        if (con12.isSelected()) {
//            facts += con12.getActionCommand() + ",";
//        }

//        if (btnGroupDoUongCon.getSelection() != null) {
//            facts += "Su dung do uong co con " + btnGroupDoUongCon.getSelection().getActionCommand() + ",";
//        }
//
//        if (btnGroupThoiGianUong.getSelection() != null) {
//            facts += "Thoi gian uong " + btnGroupThoiGianUong.getSelection().getActionCommand() + ",";
//        }
//
//        if (btnGroupCachThucUong.getSelection() != null) {
//            facts += "Cach thuc uong " + btnGroupCachThucUong.getSelection().getActionCommand() + ",";
//        }
        int ind = facts.lastIndexOf(",");
        String tempFacts = facts.substring(0, ind);
        return tempFacts;
    }

    //tien su nguoi dung chon
    String tiensuToString() {
        String tiensu = "";
        if (con11.isSelected()) {
            tiensu += con11.getActionCommand() + ",";
        }
        if (con12.isSelected()) {
            tiensu += con12.getActionCommand() + ",";
        }

        if (btnGroupDoUongCon.getSelection() != null) {
            tiensu += "Su dung do uong co con " + btnGroupDoUongCon.getSelection().getActionCommand() + ",";
        }

        if (btnGroupThoiGianUong.getSelection() != null) {
            tiensu += "Thoi gian uong " + btnGroupThoiGianUong.getSelection().getActionCommand() + ",";
        }

        if (btnGroupCachThucUong.getSelection() != null) {
            tiensu += "Cach thuc uong " + btnGroupCachThucUong.getSelection().getActionCommand() + ",";
        }
        int index = tiensu.lastIndexOf(",");
        String temp = tiensu.substring(0, index);
        return temp;
    }

    // ket qua xet nghiem
    String xuLyKetQuaXetNghiem() {
        String facts = "";

        String gender = congender.getSelectedItem().toString();

        // xet nghiem
        String astVal = xn1.getText().trim();
        String xn2Val = xn2.getText().trim();
        String xn3Val = xn3.getText().trim();
        String xn4Val = xn4.getText().trim();
        String xn5Val = xn5.getText().trim();
        String hbsag = xn6.getSelectedItem().toString();
        String antihbcigm = xn7.getSelectedItem().toString();
        String antihbcigg = xn8.getSelectedItem().toString();
        String hbeag = xn9.getSelectedItem().toString();
        String antihcv = xn10.getSelectedItem().toString();
        String hcvrna = xn11.getSelectedItem().toString();
        String xn12Val = xn12.getText().trim();

        float ast, alt, bilGT, bilTT, albumin, fiboE, fiboCAP;
        int tieucau;
        int tuoi;

        try {
            tuoi = Integer.valueOf(inpTuoi.getText().trim());
            ast = Float.parseFloat(astVal);
            alt = Float.parseFloat(xn2Val);
            bilGT = Float.parseFloat(xn3Val);
            bilTT = Float.parseFloat(xn4Val);
            albumin = Float.parseFloat(xn5Val);
            tieucau = Integer.valueOf(xn12Val);
            fiboE = Float.parseFloat(fbE.getText().trim());
            fiboCAP = Float.parseFloat(fbCAP.getText().trim());

        } catch (NumberFormatException e) {
            return "\nThông tin xét nghiệm phải nhập đủ và đúng định dạng";
        }

        // ast
        if ((ast < 35.0 && gender.equalsIgnoreCase("Nu")) || (ast < 50.0 && gender.equalsIgnoreCase("Nam")) || (ast < 60.0 && gender.equalsIgnoreCase("Tre em"))) {
            astVal = "Binh thuong";
        }
        if (ast < 100.0) {
            astVal = "Tang nhe";
        }
        if (ast < 300.0) {
            astVal = "Tang vua";
        }
        if (ast >= 300) {
            astVal = "Tang cao";
        }

        // viem gan b
        boolean viem_gan_b_man_tinh = false;
        if (hbsag.equals("-")) {
            facts += "\n- Không nhiễm viêm gan B";
        } else {
            if (antihbcigm.equals("+")) {
                facts += "\n- Viêm gan B cấp tính";
            } else {
                if (antihbcigg.equals("+")) {
                    if (hbeag.equals("+")) {
                        facts += "\n- Viêm gan B mãn tính, Virut đang nhân lên";
                        viem_gan_b_man_tinh = true;
                    } else {
                        facts += "\n- Viêm gan B mãn tính, Virut không hoạt động";
                        viem_gan_b_man_tinh = true;
                    }
                } else {
                    facts += "\n- Viêm gan B cấp tính";
                }
            }
        }

        // viem gan c
        boolean viem_gan_c_man_tinh = false;
        if (antihcv.equals("-")) {
            facts += "\n- Không nhiễm viêm gan C";
        } else {
            if (hcvrna.equals("-")) {
                facts += "\n- Hiện tại không nhiễm viêm gan C (đã từng nhiễm trong quá khứ)";
            } else {
                facts += "\n- Nhiễm viêm gan C mãn tính";
                viem_gan_c_man_tinh = true;
            }
        }

        // gan nhiem mo
        boolean gan_nhiem_mo = false;
        if (fiboCAP <= 233) {
            facts += "\n- Không nhiễm gan nhiễm mỡ";
        } else if (fiboCAP <= 258) {
            facts += "\n- Gan nhiễm mỡ nhẹ";
            gan_nhiem_mo = true;
        } else if (fiboCAP <= 290) {
            facts += "\n- Gan nhiễm mỡ vừa";
            gan_nhiem_mo = true;
        } else {
            facts += "\n- Gan nhiễm mỡ nặng";
            gan_nhiem_mo = true;
        }

        // viem gan do ruou
        if (gan_nhiem_mo) {
            if (btnCoUongCon.isSelected()) {
                facts += " do sử dụng rượu";

                if (!astVal.equals("Binh thuong") && (float) (1.0 * ast / alt) > 2.0) {
                    facts += "\n- Viêm gan do rượu";
                }
            }
        }

        // danh gia xo gan
        // fib-4
        float fib4 = (float) ((tuoi * ast) / (tieucau * Math.sqrt(alt)));

        if (fib4 < 1.45) {
            if (viem_gan_b_man_tinh || viem_gan_c_man_tinh) {
                facts += "\n- Loại trừ khả năng xơ gan";
            }
        } else if (fib4 <= 3.25) {
            if (viem_gan_b_man_tinh) {
                facts += "\n- Xơ hoá nhẹ do viêm gan B";
            }
            if (viem_gan_c_man_tinh) {
                facts += "\n- Xơ hoá nhẹ do viêm gan C";
            }
        } else {
            if (viem_gan_b_man_tinh) {
                facts += "\n- Xơ hoá nặng do viêm gan B";
            }
            if (viem_gan_c_man_tinh) {
                facts += "\n- Xơ hoá nặng do viêm gan C";
            }
        }

        return facts;
    }

    void compareRules(String facts, String tiensu) {
        System.out.println(facts);
        taFactsResult.setText("Kết quả lâm sàng: ");
        for (String bieuhien : listBieuHien) {
            int bieuhienIndex = bieuhien.lastIndexOf(",");
            String con = bieuhien.substring(0, bieuhienIndex);
            String chandoan = bieuhien.substring(bieuhienIndex + 1).trim();
            if (con.equals(facts)) {
//                System.out.println("ok");
                for (String rule : rulesListInput) {
                    int ruleResultIdx = rule.indexOf('"');
                    String condition = rule.substring(0, ruleResultIdx-1);
//                    System.out.println(condition);
                    String ruleResult = rule.substring(ruleResultIdx + 1).trim(); // lay sau dau ,
                    if (condition.equalsIgnoreCase(chandoan + "," + tiensu)) {
                        // do something
                        taFactsResult.append(ruleResult);
                        return;
                    }
                }
            }
        }
//        for (String rule : rulesListInput) {
//            int ruleResultIdx = rule.lastIndexOf(",");
//            String condition = rule.substring(0, ruleResultIdx + 1);
//            String ruleResult = rule.substring(ruleResultIdx + 1).trim(); // lay sau dau ,
//            if (condition.equalsIgnoreCase(facts)) {
//                // do something
//                taFactsResult.append(ruleResult);
//                return;
//            }
//        }

        taFactsResult.append("Nhập thêm thông tin để tư vấn");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupThoiGianUong = new javax.swing.ButtonGroup();
        btnGroupDoUongCon = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        btnGroupCachThucUong = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        btnGroupXetNghiem = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        con1 = new javax.swing.JCheckBox();
        con11 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        con3 = new javax.swing.JCheckBox();
        con4 = new javax.swing.JCheckBox();
        con5 = new javax.swing.JCheckBox();
        con7 = new javax.swing.JCheckBox();
        con6 = new javax.swing.JCheckBox();
        con8 = new javax.swing.JCheckBox();
        con9 = new javax.swing.JCheckBox();
        con10 = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        con2 = new javax.swing.JCheckBox();
        con12 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        congender = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        xn1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        xn2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        xn3 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        xn4 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        xn5 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        xn6 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        xn7 = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        xn8 = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        xn9 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        xn10 = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        xn11 = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        xn12 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        bnTuVan = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taFactsResult = new javax.swing.JTextArea();
        jLabel55 = new javax.swing.JLabel();
        btnCoUongCon = new javax.swing.JRadioButton();
        jRadioButton12 = new javax.swing.JRadioButton();
        jLabel56 = new javax.swing.JLabel();
        ruou1 = new javax.swing.JRadioButton();
        ruou2 = new javax.swing.JRadioButton();
        ruou3 = new javax.swing.JRadioButton();
        jLabel57 = new javax.swing.JLabel();
        ruou4 = new javax.swing.JRadioButton();
        ruou5 = new javax.swing.JRadioButton();
        selectXetNghiem = new javax.swing.JRadioButton();
        selectKhongXetNghiem = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fbE = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        fbCAP = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        inpTuoi = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jCheckBox21 = new javax.swing.JCheckBox();
        jCheckBox22 = new javax.swing.JCheckBox();
        jLabel27 = new javax.swing.JLabel();
        jCheckBox23 = new javax.swing.JCheckBox();
        jCheckBox24 = new javax.swing.JCheckBox();
        jCheckBox25 = new javax.swing.JCheckBox();
        jCheckBox26 = new javax.swing.JCheckBox();
        jCheckBox27 = new javax.swing.JCheckBox();
        jCheckBox28 = new javax.swing.JCheckBox();
        jCheckBox29 = new javax.swing.JCheckBox();
        jCheckBox30 = new javax.swing.JCheckBox();
        jLabel28 = new javax.swing.JLabel();
        jCheckBox31 = new javax.swing.JCheckBox();
        jCheckBox32 = new javax.swing.JCheckBox();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jCheckBox40 = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel53 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel54 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jLabel52 = new javax.swing.JLabel();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        con1.setText("Vang da; mat");

        con11.setText("Gia dinh co nguoi bi benh gan");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Các triệu chứng");

        con3.setText("Nuoc tieu sam mau");

        con4.setText("Met moi; chan an; roi loan tieu hoa");

        con5.setText("Dau bung; non mua");

        con7.setText("Bung phinh");

        con6.setText("Chan phu ne");

        con8.setText("Ngua da; me day; mun nhot");

        con9.setText("Sot nhe");

        con10.setText("Dau tuc hay kho chiu vung gan (vung bung ben phai)");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Tiền sử");

        con2.setText("Phan nhat mau");

        con12.setText("Tung truyen mau");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Thông tin");

        congender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nu", "Tre em" }));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Các chỉ số xét nghiệm");

        jLabel8.setText("AST");

        jLabel11.setText("UI/L");

        jLabel9.setText("ALT");

        jLabel12.setText("UI/L");

        jLabel10.setText("Bilirubin GT");

        jLabel14.setText("mg/dL");

        jLabel13.setText("Bilirubin TT");

        jLabel15.setText("mg/dL");

        jLabel16.setText("Albumin");

        jLabel17.setText("g/dL");

        jLabel18.setText("HBsAg");

        xn6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "+" }));

        jLabel19.setText("Anti-HBc IgM");

        xn7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "+" }));

        jLabel20.setText("Anti-HBc IgG");

        xn8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "+" }));

        jLabel21.setText("HBeAg");

        xn9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "+" }));

        jLabel22.setText("Anti-HCV");

        xn10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "+" }));

        jLabel23.setText("HCV RNA");

        xn11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "+" }));

        jLabel24.setText("Tiểu cầu");

        jLabel25.setText("ml");

        bnTuVan.setText("Tư vấn");
        bnTuVan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnTuVanActionPerformed(evt);
            }
        });

        jButton2.setText("Nhập lại");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Kết quả");

        taFactsResult.setColumns(20);
        taFactsResult.setRows(5);
        jScrollPane1.setViewportView(taFactsResult);

        jLabel55.setText("Sử dụng đồ uống có cồn");

        btnGroupDoUongCon.add(btnCoUongCon);
        btnCoUongCon.setText("Có");
        btnCoUongCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCoUongConActionPerformed(evt);
            }
        });

        btnGroupDoUongCon.add(jRadioButton12);
        jRadioButton12.setText("Không");
        jRadioButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton12ActionPerformed(evt);
            }
        });

        jLabel56.setText("Thời gian uống");

        btnGroupThoiGianUong.add(ruou1);
        ruou1.setText("< 1 năm");
        ruou1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ruou1ActionPerformed(evt);
            }
        });

        btnGroupThoiGianUong.add(ruou2);
        ruou2.setText("1-3 năm");
        ruou2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ruou2ActionPerformed(evt);
            }
        });

        btnGroupThoiGianUong.add(ruou3);
        ruou3.setText("> 3 năm");
        ruou3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ruou3ActionPerformed(evt);
            }
        });

        jLabel57.setText("Các thức uống");

        btnGroupCachThucUong.add(ruou4);
        ruou4.setText("Liên tục (4-5 lần/ tuần)");
        ruou4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ruou4ActionPerformed(evt);
            }
        });

        btnGroupCachThucUong.add(ruou5);
        ruou5.setText("Thỉnh thoảng (1-2 lần/tuần)");
        ruou5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ruou5ActionPerformed(evt);
            }
        });

        btnGroupXetNghiem.add(selectXetNghiem);
        selectXetNghiem.setText("có");
        selectXetNghiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectXetNghiemActionPerformed(evt);
            }
        });

        btnGroupXetNghiem.add(selectKhongXetNghiem);
        selectKhongXetNghiem.setSelected(true);
        selectKhongXetNghiem.setText("không");
        selectKhongXetNghiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectKhongXetNghiemActionPerformed(evt);
            }
        });

        jLabel3.setText("Fibroscan:");

        jLabel4.setText("E (kPa)");

        jLabel5.setText("CAP (dB/m)");

        jLabel58.setText("Tuổi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(con1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(con3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(con5, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(con4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(con2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(con12, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(con11, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(66, 66, 66)
                                        .addComponent(congender, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(xn1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(14, 14, 14)
                                                .addComponent(jLabel11))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel21)
                                                .addGap(18, 18, 18)
                                                .addComponent(xn9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(41, 41, 41)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel22)
                                                .addGap(18, 18, 18)
                                                .addComponent(xn10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel18)
                                                .addGap(18, 18, 18)
                                                .addComponent(xn6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(xn2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(14, 14, 14)
                                                .addComponent(jLabel12)))))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel20)
                                                .addGap(18, 18, 18)
                                                .addComponent(xn8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(52, 52, 52)
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(xn3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel14)
                                                .addGap(58, 58, 58)
                                                .addComponent(jLabel13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(xn4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel15)
                                        .addGap(73, 73, 73))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(con9)
                                                    .addComponent(con8)
                                                    .addComponent(con7)
                                                    .addComponent(con10)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel55)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(btnCoUongCon)
                                                        .addGap(46, 46, 46)
                                                        .addComponent(jRadioButton12))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel56)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(ruou1)
                                                        .addGap(29, 29, 29)
                                                        .addComponent(ruou2)
                                                        .addGap(38, 38, 38)
                                                        .addComponent(ruou3))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel57)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(ruou4)
                                                        .addGap(46, 46, 46)
                                                        .addComponent(ruou5))
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                            .addComponent(jLabel58)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(inpTuoi))
                                                        .addComponent(con6, javax.swing.GroupLayout.Alignment.LEADING))))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(269, 269, 269)
                                                .addComponent(jLabel24)
                                                .addGap(18, 18, 18)
                                                .addComponent(xn12, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel25)))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(26, 26, 26)
                                        .addComponent(selectXetNghiem)
                                        .addGap(18, 18, 18)
                                        .addComponent(selectKhongXetNghiem))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(xn5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel17)
                                        .addGap(195, 195, 195)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel23)
                                                .addGap(18, 18, 18)
                                                .addComponent(xn11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel19)
                                                .addGap(18, 18, 18)
                                                .addComponent(xn7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(fbE, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(34, 34, 34)
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(fbCAP, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bnTuVan, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(congender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel58)
                            .addComponent(inpTuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(con1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(con2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(con3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(con4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(con5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(con6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(con7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(con8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(con9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(con10)
                        .addGap(8, 8, 8)))
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(con11)
                    .addComponent(jLabel55)
                    .addComponent(btnCoUongCon)
                    .addComponent(jRadioButton12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(ruou1)
                    .addComponent(ruou2)
                    .addComponent(ruou3))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(con12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57)
                            .addComponent(ruou4)
                            .addComponent(ruou5))))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(selectXetNghiem)
                    .addComponent(selectKhongXetNghiem))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(xn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel9)
                    .addComponent(xn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10)
                    .addComponent(xn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(xn4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(xn5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(xn6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(xn7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(xn8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(xn9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(xn10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(xn11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(xn12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(fbE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(fbCAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnTuVan)
                    .addComponent(jButton2))
                .addGap(26, 26, 26)
                .addComponent(jLabel26)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tư vấn", jPanel1);

        jCheckBox21.setText("Vang da; mat");

        jCheckBox22.setText("Gia đinh co nguoi bi benh gan");
        jCheckBox22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox22ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Các triệu chứng");

        jCheckBox23.setText("Nuoc tieu sam mau");
        jCheckBox23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox23ActionPerformed(evt);
            }
        });

        jCheckBox24.setText("Met moi; chan an; roi loan tieu hoa");

        jCheckBox25.setText("Dau bung; non mua");

        jCheckBox26.setText("Bung phinh");

        jCheckBox27.setText("Chan phu ne");

        jCheckBox28.setText("Ngua da; me đay; mun nhot");

        jCheckBox29.setText("Sot nhe");
        jCheckBox29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox29ActionPerformed(evt);
            }
        });

        jCheckBox30.setText("Dau tuc hay kho chiu vung gan (vung bung ben phai)");
        jCheckBox30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox30ActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Tiền sử");

        jCheckBox31.setText("Phan nhat mau");

        jCheckBox32.setText("Tung truyen mau");
        jCheckBox32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox32ActionPerformed(evt);
            }
        });

        jLabel29.setText("Su dung do uong co con");

        jLabel30.setText("Thoi gian uong");

        jLabel31.setText("Cach thuc uong");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel32.setText("Nhập điều kiện");

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nu" }));
        jComboBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox8ActionPerformed(evt);
            }
        });

        jCheckBox40.setText("Tre em");

        jButton3.setText("Thêm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Xóa");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel53.setText("Kết luận chẩn đoán");

        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jList1);

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel54.setText("Danh sách luật");

        btnGroupThoiGianUong.add(jRadioButton1);
        jRadioButton1.setText("< 1 nam");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        btnGroupThoiGianUong.add(jRadioButton2);
        jRadioButton2.setText("> 3 nam");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        btnGroupThoiGianUong.add(jRadioButton3);
        jRadioButton3.setText("1-3 nam");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        btnGroupDoUongCon.add(jRadioButton4);
        jRadioButton4.setText("Co");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        btnGroupDoUongCon.add(jRadioButton5);
        jRadioButton5.setText("Khong");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        btnGroupCachThucUong.add(jRadioButton6);
        jRadioButton6.setText("Lien tuc (4-5 lan/ tuan)");
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });

        btnGroupCachThucUong.add(jRadioButton7);
        jRadioButton7.setText("Thinh thoang (1-2 lan/tuan)");
        jRadioButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton7ActionPerformed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel52.setText("Tuổi");

        buttonGroup5.add(jRadioButton8);
        jRadioButton8.setText("0-4 thang");
        jRadioButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton8ActionPerformed(evt);
            }
        });

        buttonGroup5.add(jRadioButton9);
        jRadioButton9.setText("4-6 thang");
        jRadioButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton9ActionPerformed(evt);
            }
        });

        buttonGroup5.add(jRadioButton10);
        jRadioButton10.setText("> 16 tuoi");
        jRadioButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton10ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jScrollPane4.setViewportView(jList2);

        jLabel33.setText("Chẩn đoán qua biểu hiện");

        jLabel34.setText("Chẩn đoán kết hợp tiền sử");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox40, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton8)
                                .addGap(20, 20, 20)
                                .addComponent(jRadioButton9)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton10))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jCheckBox31, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBox25))
                                    .addComponent(jCheckBox21, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(148, 148, 148)
                                        .addComponent(jCheckBox24))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jCheckBox23, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBox26)))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox30)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jCheckBox27)
                                        .addGap(96, 96, 96)
                                        .addComponent(jCheckBox29))
                                    .addComponent(jCheckBox28)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox22)
                                    .addComponent(jCheckBox32))
                                .addGap(92, 92, 92)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel30)
                                            .addComponent(jLabel31))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(36, 36, 36)
                                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addComponent(jRadioButton6)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jRadioButton7)))
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addComponent(jRadioButton4)
                                                    .addGap(73, 73, 73)
                                                    .addComponent(jRadioButton5))
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addComponent(jRadioButton1)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jRadioButton3)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jRadioButton2)))))))
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(73, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel34))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox40)
                    .addComponent(jLabel52)
                    .addComponent(jRadioButton8)
                    .addComponent(jRadioButton9)
                    .addComponent(jRadioButton10))
                .addGap(18, 18, 18)
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox21)
                    .addComponent(jCheckBox24)
                    .addComponent(jCheckBox27)
                    .addComponent(jCheckBox29))
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox31)
                            .addComponent(jCheckBox25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox23)
                            .addComponent(jCheckBox26)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jCheckBox28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox30)))
                .addGap(18, 18, 18)
                .addComponent(jLabel28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(jRadioButton4)
                            .addComponent(jRadioButton5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton3)
                            .addComponent(jRadioButton2)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox32)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jRadioButton6)
                    .addComponent(jRadioButton7))
                .addGap(17, 17, 17)
                .addComponent(jLabel53)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel34))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý luật", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void selectKhongXetNghiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectKhongXetNghiemActionPerformed
        coKetQuaXetNghiem = false;
    }//GEN-LAST:event_selectKhongXetNghiemActionPerformed

    private void selectXetNghiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectXetNghiemActionPerformed
        coKetQuaXetNghiem = true;
    }//GEN-LAST:event_selectXetNghiemActionPerformed

    private void ruou5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ruou5ActionPerformed
        ruou5.setActionCommand("Thinh thoang (1-2 lan/tuan)");
    }//GEN-LAST:event_ruou5ActionPerformed

    private void ruou4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ruou4ActionPerformed
        ruou4.setActionCommand("Lien tuc (4-5 lan/ tuan)");
    }//GEN-LAST:event_ruou4ActionPerformed

    private void ruou3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ruou3ActionPerformed
        ruou3.setActionCommand("> 3 nam");
    }//GEN-LAST:event_ruou3ActionPerformed

    private void ruou2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ruou2ActionPerformed
        ruou2.setActionCommand("1-3 nam");
    }//GEN-LAST:event_ruou2ActionPerformed

    private void ruou1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ruou1ActionPerformed
        ruou1.setActionCommand("< 1 nam");
    }//GEN-LAST:event_ruou1ActionPerformed

    private void jRadioButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton12ActionPerformed
        btnCoUongCon.setActionCommand("Khong");

        disableGroupUongCon();
    }//GEN-LAST:event_jRadioButton12ActionPerformed

    private void btnCoUongConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCoUongConActionPerformed
        btnCoUongCon.setActionCommand("Co");

        enableGroupUongCon();
    }//GEN-LAST:event_btnCoUongConActionPerformed

    private void bnTuVanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnTuVanActionPerformed
        // lam sang
        String facts = convertSelectionToString();
        String tiensu = tiensuToString();
        System.out.println("input: " + facts + tiensu);
        compareRules(facts, tiensu);

        // xet nghiem
        if (coKetQuaXetNghiem) {
            taFactsResult.append("\nKết luận xét nghiệm:");
            taFactsResult.append(xuLyKetQuaXetNghiem());
        }
    }//GEN-LAST:event_bnTuVanActionPerformed

    private void jRadioButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton10ActionPerformed
        // TODO add your handling code here:
        jRadioButton10.setActionCommand("> 16 tuoi");
    }//GEN-LAST:event_jRadioButton10ActionPerformed

    private void jRadioButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton9ActionPerformed
        // TODO add your handling code here:
        jRadioButton9.setActionCommand("4-6 thang");
    }//GEN-LAST:event_jRadioButton9ActionPerformed

    private void jRadioButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton8ActionPerformed
        // TODO add your handling code here:
        jRadioButton8.setActionCommand("0-4 thang");
    }//GEN-LAST:event_jRadioButton8ActionPerformed

    private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton7ActionPerformed
        // TODO add your handling code here:
        jRadioButton7.setActionCommand("Thinh thoang (1-2 lan/tuan)");
    }//GEN-LAST:event_jRadioButton7ActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        // TODO add your handling code here:
        jRadioButton6.setActionCommand("Lien tuc (4-5 lan/tuan)");
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // TODO add your handling code here:
        jRadioButton5.setActionCommand("Khong");
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
        jRadioButton4.setActionCommand("Co");
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
        jRadioButton3.setActionCommand("1-3 nam");
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        jRadioButton1.setActionCommand("> 3 nam");
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        jRadioButton1.setActionCommand("< 1 nam");
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        listModel.removeElementAt(jList1.getSelectedIndex());
        ArrayList<String> ls = new ArrayList<>();
        for (int i = 0; i < jList1.getModel().getSize(); i++) {
            String item = jList1.getModel().getElementAt(i);
            ls.add(item);
        }
        try {
            utils.IOFile.writeFromFile("rules.csv", ls);
        } catch (IOException ex) {
            Logger.getLogger(MainFrm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //        jButton3.setActionCommand("");
        String bieuHien = getBieuHien();
        String tienSu = getTienSu();
        //        DefaultListModel<String> list = new DefaultListModel<>();
        DefaultListModel listRuleBieuHien = new DefaultListModel();
        listRuleBieuHien.addElement(bieuHien + jTextField13.getText());
        jList1.setModel(listRuleBieuHien);
        writeToFile(bieuHien, tienSu);

        DefaultListModel listRuleTienSu = new DefaultListModel();
        listRuleTienSu.addElement(jTextField13.getText()+","+tienSu+jTextArea1.getText());
        jList2.setModel(listRuleTienSu);
        System.out.println(bieuHien + "," + tienSu);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox8ActionPerformed

    private void jCheckBox32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox32ActionPerformed

    private void jCheckBox30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox30ActionPerformed

    private void jCheckBox29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox29ActionPerformed

    private void jCheckBox23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox23ActionPerformed

    private void jCheckBox22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox22ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private String getBieuHien() {
        String bieuHien = "";
        if (jCheckBox21.isSelected()) {
            bieuHien += jCheckBox21.getText() + ",";
        }
        if (jCheckBox24.isSelected()) {
            bieuHien += jCheckBox24.getText() + ",";
        }
        if (jCheckBox27.isSelected()) {
            bieuHien += jCheckBox27.getText() + ",";
        }
        if (jCheckBox23.isSelected()) {
            bieuHien += jCheckBox23.getText() + ",";
        }
        if (jCheckBox31.isSelected()) {
            bieuHien += jCheckBox31.getText() + ",";
        }
        if (jCheckBox25.isSelected()) {
            bieuHien += jCheckBox25.getText() + ",";
        }
        if (jCheckBox26.isSelected()) {
            bieuHien += jCheckBox26.getText() + ",";
        }
        if (jCheckBox28.isSelected()) {
            bieuHien += jCheckBox28.getText() + ",";
        }
        if (jCheckBox29.isSelected()) {
            bieuHien += jCheckBox29.getText() + ",";
        }
        if (jCheckBox30.isSelected()) {
            bieuHien += jCheckBox30.getText() + ",";
        }
//        if (jCheckBox22.isSelected()) {
//            rule += jCheckBox22.getText() + ",";
//        }
//        if (jCheckBox32.isSelected()) {
//            rule += jCheckBox32.getText() + ",";
//        }
//        if (btnGroupThoiGianUong.getSelection() != null) {
//            rule = rule + btnGroupThoiGianUong.getSelection().getActionCommand() + ",";
//        }
//        if (btnGroupDoUongCon.getSelection() != null) {
//            rule = rule + btnGroupDoUongCon.getSelection().getActionCommand() + ",";
//        }
//        if (btnGroupCachThucUong.getSelection() != null) {
//            rule = rule + btnGroupCachThucUong.getSelection().getActionCommand() + ",";
//        }

//        if (jTextField7.getText() != null) {
//            int valAST = Integer.parseInt(jTextField7.getText());
//            String gender = jComboBox8.getSelectedItem().toString();
//            if (gender.equals("Nu") && valAST < 35) {
//                rule += "AST binh thuong,";
//            } else if (gender.equals("Nam") && valAST < 50) {
//                rule += "AST binh thuong,";
//            } else if (gender.equals("Nu") && valAST >= 35 && valAST <= 100) {
//                rule += "AST tang nhe,";
//            } else if (gender.equals("Nam") && valAST >= 50 && valAST <= 100) {
//                rule += "AST tang nhe,";
//            } else if (jCheckBox40.getText().equals("Tre em") && valAST >= 60 && valAST <= 100) {
//                rule += "AST tang nhe,";
//            } else if (valAST >= 100 && valAST <= 300) {
//                rule += "AST tang vua,";
//            } else if (valAST >= 3000) {
//                rule += "AST tang cao,";
//            }
//        }
//
//        if (jTextField8.getText() != null) {
//            int valALT = Integer.parseInt(jTextField8.getText());
//            String gender = jComboBox8.getSelectedItem().toString();
//            if (gender.equals("Nu") && valALT < 19) {
//                rule += "ALT binh thuong,";
//            } else if (gender.equals("Nam") && valALT < 30) {
//                rule += "ALT binh thuong,";
//            } else if (valALT <= 100) {
//                rule += "ALT tang nhe,";
//            } else if (valALT >= 3000) {
//                rule += "ALT tang cao,";
//            }
//        }
//
//        if (!jTextField9.getText().equals("")) {
//            double valBilirubinGT = Double.parseDouble(jTextField9.getText());
//            if (valBilirubinGT >= 0.1 && valBilirubinGT <= 1.0) {
//                rule += "Bilirubin GT binh thuong,";
//            } else if (valBilirubinGT > 1.0) {
//                rule += "Bilirubin GT cao,";
//            }
//        }
//
//        if (!jTextField10.getText().equals("")) {
//            double valBilirubinTT = Double.parseDouble(jTextField10.getText());
//            if (valBilirubinTT >= 0 && valBilirubinTT <= 0.4) {
//                rule += "Bilirubin TT binh thuong,";
//            } else if (valBilirubinTT > 0.4) {
//                rule += "Bilirubin TT cao,";
//            }
//        }
//
//        if (jTextField11.getText().equals("")) {
//            double valAlbumin = Double.parseDouble(jTextField11.getText());
//            String age = buttonGroup5.getSelection().getActionCommand();
//            if ((age.equals("0-4 thang") && valAlbumin < 2.0)
//                    || (age.equals("4-6 thang") && valAlbumin < 3.2)
//                    || (age.equals("16 tuoi") && valAlbumin < 3.5)) {
//                rule += "Albumin thap,";
//            } else if ((age.equals("0-4 thang") && valAlbumin >= 2.0 && valAlbumin <= 4.5)
//                    || (age.equals("4-6 thang") && valAlbumin >= 3.2 && valAlbumin <= 5.2)
//                    || (age.equals("16 tuoi") && valAlbumin >= 3.5 && valAlbumin <= 4.8)) {
//                rule += "Albumin binh thuong,";
//            } else if ((age.equals("0-4 thang") && valAlbumin > 4.5)
//                    || (age.equals("4-6 thang") && valAlbumin > 5.2)
//                    || (age.equals("16 tuoi") && valAlbumin > 4.8)) {
//                rule += "Albumin cao,";
//            }
//
//        }

//        String HbsAg = jComboBox9.getSelectedItem().toString();
//        String Anti_HBc_IgM = jComboBox10.getSelectedItem().toString();
//        String Anti_HBc_IgG = jComboBox11.getSelectedItem().toString();
//        String HbeAg = jComboBox12.getSelectedItem().toString();
//        String Anti_HCV = jComboBox13.getSelectedItem().toString();
//        String HCV_RNA = jComboBox14.getSelectedItem().toString();
//        rule += HbeAg + "," + Anti_HBc_IgM + "," + Anti_HBc_IgG + "," + HbeAg + "," + Anti_HCV + "," + HCV_RNA + ",";
//
//        if (!jTextField12.getText().equals("")) {
//            int valTieuCau = Integer.parseInt(jTextField12.getText());
//            if (valTieuCau < 150000) {
//                rule += "Tieu cau thap";
//            } else if (valTieuCau >= 150000 && valTieuCau <= 450000) {
//                rule += "Tieu cau binh thuong";
//            }
//        }

        return bieuHien;
    }

    //lay tien su
    private String getTienSu(){
        String tienSu = "";
        if (jCheckBox22.isSelected()) {
            tienSu += jCheckBox22.getText() + ",";
        }
        if (jCheckBox32.isSelected()) {
            tienSu += jCheckBox32.getText() + ",";
        }
        
        return tienSu;
    }
    
    private void writeToFile(String bieuHien, String tienSu) {
        String fileName = "bieuhienbenh.csv";
        String f = "tiensubenh.csv";
        try {
            FileWriter fileWriter;
            fileWriter = new FileWriter(fileName, true);
            fileWriter.append(bieuHien + jTextField13.getText());
            fileWriter.append("\n");
            fileWriter.flush();
            fileWriter.close();
            
            fileWriter = new FileWriter(f, true);
            fileWriter.append(jTextField13.getText()+"," + tienSu+ jTextArea1.getText());
            fileWriter.append("\n");
            
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Add rule success!");
        } catch (IOException ex) {
            Logger.getLogger(MainFrm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnTuVan;
    private javax.swing.JRadioButton btnCoUongCon;
    private javax.swing.ButtonGroup btnGroupCachThucUong;
    private javax.swing.ButtonGroup btnGroupDoUongCon;
    private javax.swing.ButtonGroup btnGroupThoiGianUong;
    private javax.swing.ButtonGroup btnGroupXetNghiem;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.JCheckBox con1;
    private javax.swing.JCheckBox con10;
    private javax.swing.JCheckBox con11;
    private javax.swing.JCheckBox con12;
    private javax.swing.JCheckBox con2;
    private javax.swing.JCheckBox con3;
    private javax.swing.JCheckBox con4;
    private javax.swing.JCheckBox con5;
    private javax.swing.JCheckBox con6;
    private javax.swing.JCheckBox con7;
    private javax.swing.JCheckBox con8;
    private javax.swing.JCheckBox con9;
    private javax.swing.JComboBox<String> congender;
    private javax.swing.JTextField fbCAP;
    private javax.swing.JTextField fbE;
    private javax.swing.JTextField inpTuoi;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox21;
    private javax.swing.JCheckBox jCheckBox22;
    private javax.swing.JCheckBox jCheckBox23;
    private javax.swing.JCheckBox jCheckBox24;
    private javax.swing.JCheckBox jCheckBox25;
    private javax.swing.JCheckBox jCheckBox26;
    private javax.swing.JCheckBox jCheckBox27;
    private javax.swing.JCheckBox jCheckBox28;
    private javax.swing.JCheckBox jCheckBox29;
    private javax.swing.JCheckBox jCheckBox30;
    private javax.swing.JCheckBox jCheckBox31;
    private javax.swing.JCheckBox jCheckBox32;
    private javax.swing.JCheckBox jCheckBox40;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JRadioButton ruou1;
    private javax.swing.JRadioButton ruou2;
    private javax.swing.JRadioButton ruou3;
    private javax.swing.JRadioButton ruou4;
    private javax.swing.JRadioButton ruou5;
    private javax.swing.JRadioButton selectKhongXetNghiem;
    private javax.swing.JRadioButton selectXetNghiem;
    private javax.swing.JTextArea taFactsResult;
    private javax.swing.JTextField xn1;
    private javax.swing.JComboBox<String> xn10;
    private javax.swing.JComboBox<String> xn11;
    private javax.swing.JTextField xn12;
    private javax.swing.JTextField xn2;
    private javax.swing.JTextField xn3;
    private javax.swing.JTextField xn4;
    private javax.swing.JTextField xn5;
    private javax.swing.JComboBox<String> xn6;
    private javax.swing.JComboBox<String> xn7;
    private javax.swing.JComboBox<String> xn8;
    private javax.swing.JComboBox<String> xn9;
    // End of variables declaration//GEN-END:variables
}
