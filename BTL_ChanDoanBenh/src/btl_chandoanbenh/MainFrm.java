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
            System.out.println(btnGroupDoUongCon.getSelection().getActionCommand());
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
        String altVal = xn2.getText().trim();
        String xn3Val = xn3.getText().trim();
        String bilTTVal = xn4.getText().trim();
        String albuminVal = xn5.getText().trim();
        String hbsag = xn6.getSelectedItem().toString();
        String antihbcigm = xn7.getSelectedItem().toString();
        String antihbcigg = xn8.getSelectedItem().toString();
        String hbeag = xn9.getSelectedItem().toString();
        String antihcv = xn10.getSelectedItem().toString();
        String hcvrna = xn11.getSelectedItem().toString();
        String xn12Val = xn12.getText().trim();

        float ast, alt, bilGT, bilTT, albumin, fiboE, fiboCAP;
        int tieucau;

        try {
            ast = Float.parseFloat(astVal);
            alt = Float.parseFloat(altVal);
            bilGT = Float.parseFloat(xn3Val);
            bilTT = Float.parseFloat(bilTTVal);
            albumin = Float.parseFloat(albuminVal);
            tieucau = Integer.valueOf(xn12Val);
            fiboE = Float.parseFloat(fbE.getText().trim());
            fiboCAP = Float.parseFloat(fbCAP.getText().trim());

        } 
        catch (NumberFormatException e) {
            return "\nThông tin xét nghiệm phải nhập đủ và đúng định dạng";
        }
        
        // alt
        if ((alt < 19.0 && gender.equalsIgnoreCase("Nu")) || (alt < 30.0 && gender.equalsIgnoreCase("Nam")) || (alt < 40.0 && gender.equalsIgnoreCase("Tre em"))) {
            altVal = "Binh thuong";
        }
        if (alt < 100.0) {
            altVal = "Tang nhe";
        }
        if (alt < 300.0) {
            altVal = "Tang vua";
        }
        if (alt >= 300) {
            altVal = "Tang cao";
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
        
        //albumin
        if ((albumin < 3.5 && gender.equalsIgnoreCase("Nu")) || (albumin < 3.5 && gender.equalsIgnoreCase("Nam")) || (albumin < 3.2 && gender.equalsIgnoreCase("Tre em"))) {
            albuminVal = "Thap";
        } else if ((albumin < 4.8 && gender.equalsIgnoreCase("Nu")) || (albumin < 4.8 && gender.equalsIgnoreCase("Nam")) || (albumin < 4.5 && gender.equalsIgnoreCase("Tre em"))) {
            albuminVal = "Binh thuong";
        } else {
            albuminVal = "Cao";
        }
        // bil TT
        if (bilTT < 0.4) {
            bilTTVal = "Binh thuong";
        }
        if (bilTT >= 0.4) {
            bilTTVal = "Cao";
        }

        // viem gan b
        boolean viem_gan_b_man_tinh = false;
        String viem_gan_b = "";
        if (hbsag.equals("-")) {
            facts += "\n- Không nhiễm viêm gan B";
        } else {
            if (antihbcigm.equals("+")) {
                facts += "\n- Viêm gan B cấp tính";
                viem_gan_b = "viem gan B cap tinh";
            } else {
                if (antihbcigg.equals("+")) {
                    if (hbeag.equals("+")) {
                        facts += "\n- Viêm gan B mãn tính, Virut đang nhân lên";
                        viem_gan_b_man_tinh = true;
                        viem_gan_b = "viem gan B man tinh";
                    } else {
                        facts += "\n- Viêm gan B mãn tính, Virut không hoạt động";
                        viem_gan_b_man_tinh = true;
                        viem_gan_b = "viem gan B man tinh";
                    }
                } else {
                    facts += "\n- Viêm gan B cấp tính";
                    viem_gan_b = "viem gan B cap tinh";
                }
            }
        }

        // viem gan c
        boolean viem_gan_c_man_tinh = false;
        String viem_gan_c = "";
        if (antihcv.equals("-")) {
            facts += "\n- Không nhiễm viêm gan C";
        } else {
            if (hcvrna.equals("-")) {
                facts += "\n- Hiện tại không nhiễm viêm gan C (đã từng nhiễm trong quá khứ)";
            } else {
                facts += "\n- Nhiễm viêm gan C mãn tính";
                viem_gan_c_man_tinh = true;
                viem_gan_c = "viem gan c man tinh";
            }
        }

        // gan nhiem mo
        boolean gan_nhiem_mo = false;
//        String gan_nhiem_mo = "";
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
        // fibroscran
        boolean f4 = false, f3 = false, f2 = false;
        String xo_gan = "";
        if (viem_gan_b_man_tinh) {
            if (fiboE <= 6.0) {
                facts += "\n- Xơ hoá nhẹ do viêm gan B";
            } else if (fiboE <= 9.0) {
                facts += "\n- Xơ hoá đáng kể do viêm gan B";
            } else if (fiboE <= 12) {
                facts += "\n- Xơ hoá nặng do viêm gan B";
            } else {
                xo_gan += "\n- Xơ gan do viêm gan B";
                f4 = true;
            }
        }
        if (viem_gan_c_man_tinh) {
            if (fiboE <= 7.0) {
                facts += "\n- Xơ hoá nhẹ do viêm gan B";
            } else if (fiboE <= 9.5) {
                facts += "\n- Xơ hoá đáng kể do viêm gan C";
            } else if (fiboE <= 12) {
                facts += "\n- Xơ hoá nặng do viêm gan C";
            } else {
                xo_gan += "\n- Xơ gan do viêm gan C";
                f4 = true;
            }
        }

        // danh gia xo gan
        if (gan_nhiem_mo && btnCoUongCon.isSelected()) {
            if ((float) (ast / alt) > 1.0 && !f4) {
                facts += "\n- Nguy cơ xơ gan";
                f4 = true;
            } else if ((float) (ast / alt) > 2.0 && gan_nhiem_mo) {
                xo_gan += "\n- Xơ gan do rượu";
                f4 = true;
            }
        }

        if (f4 && con4.isSelected()) {
            xo_gan += "\n- Xơ gan còn bù";
        } else if (f4 && con7.isSelected() && con6.isSelected() && albuminVal.equalsIgnoreCase("Thap")) {
            xo_gan += "\n- Xơ gan mất bù";
        } else if (f4 && con1.isSelected() && con3.isSelected() && bilTTVal.equalsIgnoreCase("Cao")) {
            xo_gan += "\n- Xơ gan mất bù";
        }

        facts += xo_gan;

        String totalRestuls = "\nKết luận:";
        if (f4) {
            totalRestuls += " Gan bị xơ giai đoạn f4";
        } else if (f3) {
            totalRestuls += " Gan bị xơ hoá giai đoạn f3";
        } else if(f2){
            totalRestuls += " Gan bị xơ hoá giai đoạn f2";
        } else if(gan_nhiem_mo){
            totalRestuls += " Gan bị nhiễm mỡ";
        } else {
            totalRestuls += " Gan bình thường";
        }

        facts += totalRestuls;

        return facts;
    }

    void compareRules(String facts, String tiensu) {
//        System.out.println(facts);
        taFactsResult.setText("Kết quả lâm sàng: ");
        for (String bieuhien : listBieuHien) {
            int bieuhienIndex = bieuhien.lastIndexOf(",");
            String con = bieuhien.substring(0, bieuhienIndex);
            String chandoan = bieuhien.substring(bieuhienIndex + 1).trim();
            if (con.equals(facts)) {
                System.out.println("ok");
                for (String rule : rulesListInput) {
                    int ruleResultIdx = rule.indexOf('"');
                    String condition = rule.substring(0, ruleResultIdx-1);
//                    System.out.println(condition);
                    String ruleResult = rule.substring(ruleResultIdx + 1, rule.length()-1).trim(); // lay sau dau ,
                    if (condition.equalsIgnoreCase(chandoan + "," + tiensu)) {
                        // do something
                        taFactsResult.append(ruleResult);
                        return;
                    }
                }
            }
        }

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
        jRadioButton11 = new javax.swing.JRadioButton();
        btnCoUongCon = new javax.swing.JRadioButton();
        jLabel35 = new javax.swing.JLabel();

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
        taFactsResult.setLineWrap(true);
        taFactsResult.setRows(5);
        jScrollPane1.setViewportView(taFactsResult);

        jLabel55.setText("Sử dụng đồ uống có cồn");

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

        btnGroupDoUongCon.add(jRadioButton11);
        jRadioButton11.setText("Không");
        jRadioButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton11ActionPerformed(evt);
            }
        });

        btnGroupDoUongCon.add(btnCoUongCon);
        btnCoUongCon.setText("Có");
        btnCoUongCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCoUongConActionPerformed(evt);
            }
        });

        jLabel35.setText("mg/dL");

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
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(xn2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel12)
                                        .addGap(4, 4, 4))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(con12, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(66, 66, 66)
                                                .addComponent(congender, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(con11, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel16)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(xn5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel17)
                                                .addGap(32, 32, 32)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel18)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(xn6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel22)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(xn10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel56)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(94, 94, 94)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(con10)
                                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(jRadioButton11)
                                                                .addComponent(con5))
                                                            .addComponent(con4)))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(18, 18, 18)
                                                        .addComponent(ruou1)
                                                        .addGap(29, 29, 29)
                                                        .addComponent(ruou2)
                                                        .addGap(33, 33, 33)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(ruou5)
                                                            .addComponent(ruou3)))))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel55)
                                                .addGap(29, 29, 29)
                                                .addComponent(btnCoUongCon))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel57)
                                                .addGap(18, 18, 18)
                                                .addComponent(ruou4)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel14)
                                                .addGap(106, 106, 106)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(xn4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel15))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(xn12, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel25)))
                                        .addGap(47, 47, 47))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(319, 319, 319)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(xn3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel35))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel5)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(xn7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(43, 43, 43)
                                                        .addComponent(jLabel20)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(xn8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(106, 106, 106)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(fbCAP, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(26, 26, 26)
                                                .addComponent(selectXetNghiem)
                                                .addGap(18, 18, 18)
                                                .addComponent(selectKhongXetNghiem))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(xn1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel11))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel21)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(xn9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(14, 14, 14)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel23)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(xn11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(fbE, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 72, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(con1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(con2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(con3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(con9))
                                .addGap(92, 92, 92)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(con7)
                                    .addComponent(con6)
                                    .addComponent(con8))
                                .addGap(363, 363, 363)))
                        .addContainerGap(9, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(bnTuVan, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel26))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(con6)
                            .addComponent(con10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(con7)
                            .addComponent(con4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(con8)
                            .addComponent(con5))
                        .addGap(60, 60, 60)
                        .addComponent(jRadioButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ruou3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(congender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(con1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(con2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(con3)
                        .addGap(3, 3, 3)
                        .addComponent(con9)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(con11)
                            .addComponent(jLabel55)
                            .addComponent(btnCoUongCon))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(con12)
                            .addComponent(jLabel56)
                            .addComponent(ruou1)
                            .addComponent(ruou2))))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(ruou4)
                    .addComponent(ruou5))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(selectXetNghiem)
                    .addComponent(selectKhongXetNghiem))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(xn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9)
                            .addComponent(xn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(xn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(xn4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel35))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(xn5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(xn6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(xn7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(xn8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(xn12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(xn9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(xn10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(xn11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(fbE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(fbCAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnTuVan)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tư vấn", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCoUongConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCoUongConActionPerformed
        // TODO add your handling code here:
        btnCoUongCon.setActionCommand("Co");
        enableGroupUongCon();
    }//GEN-LAST:event_btnCoUongConActionPerformed

    private void jRadioButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton11ActionPerformed
        // TODO add your handling code here:
        jRadioButton11.setActionCommand("Khong");
        disableGroupUongCon();
    }//GEN-LAST:event_jRadioButton11ActionPerformed

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

    private void bnTuVanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnTuVanActionPerformed
        // lam sang
        String facts = convertSelectionToString();
        String tiensu = tiensuToString();
        System.out.println("input: " + facts + ","+ tiensu);
        compareRules(facts, tiensu);

        // xet nghiem
        if (coKetQuaXetNghiem) {
            taFactsResult.append("\nKết luận xét nghiệm:");
            taFactsResult.append(xuLyKetQuaXetNghiem());
        }
    }//GEN-LAST:event_bnTuVanActionPerformed


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
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
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
