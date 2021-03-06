package btl_chandoanbenh;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import utils.IOFile;

public class MainFrm extends javax.swing.JFrame {

    ArrayList<String> rulesListInput;
    ArrayList<String> listBieuHien;
    String factsResult;

    boolean coKetQuaXetNghiem = false;
    DefaultListModel listModel;

    public MainFrm() {
        initComponents();
        listModel = new DefaultListModel();
        rulesListInput = IOFile.readFromFile("tiensubenh.txt");
        listBieuHien = IOFile.readFromFile("bieuhienbenh.txt");
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

    // bieu hien nguoi dung chon
    String convertSelectionToString() {
        String facts = "";

        String gender = congender.getSelectedItem().toString();

        // bieu hien
        if (vangdamat.isSelected()) {
            facts += vangdamat.getActionCommand() + ",";
        }
        if (phannhatmau.isSelected()) {
            facts += phannhatmau.getActionCommand() + ",";
        }
        if (nuoctieusammau.isSelected()) {
            facts += nuoctieusammau.getActionCommand() + ",";
        }
        if (metmoichanan.isSelected()) {
            facts += metmoichanan.getActionCommand() + ",";
        }
        if (daubungnonmua.isSelected()) {
            facts += daubungnonmua.getActionCommand() + ",";
        }
        if (chanphune.isSelected()) {
            facts += chanphune.getActionCommand() + ",";
        }
        if (bungphinh.isSelected()) {
            facts += bungphinh.getActionCommand() + ",";
        }
        if (nguadamunnhot.isSelected()) {
            facts += nguadamunnhot.getActionCommand() + ",";
        }
        if (sotnhe.isSelected()) {
            facts += sotnhe.getActionCommand() + ",";
        }
        if (dautucgan.isSelected()) {
            facts += dautucgan.getActionCommand() + ",";
        }

        if (!facts.equals("")) {
            int ind = facts.lastIndexOf(",");
            String tempFacts = facts.substring(0, ind);
            return tempFacts;
        }
        return facts;

    }

    //tien su nguoi dung chon
    String tiensuToString() {
        String tiensu = "";
        if (tiensugiadinh.isSelected()) {
            tiensu += tiensugiadinh.getActionCommand() + ",";
        }
        if (tungtruyenmau.isSelected()) {
            tiensu += tungtruyenmau.getActionCommand() + ",";
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

        if (!tiensu.equals("")) {
//            JOptionPane.showMessageDialog(this, "H??y ch???n ??t nh???t 1 ti???n s???!");
            int index = tiensu.lastIndexOf(",");
            String temp = tiensu.substring(0, index);
            return temp;
        }
        return tiensu;
    }

    // so sanh bieu hien, tieu su
    void compareRules(String facts, String tiensu) {
//        System.out.println(facts);
        taFactsResult.setText("K????t qua?? l??m sa??ng: ");
        for (String bieuhien : listBieuHien) {
            int bieuhienIndex = bieuhien.lastIndexOf(",");
            String con = bieuhien.substring(0, bieuhienIndex);
            String chandoan = bieuhien.substring(bieuhienIndex + 1).trim();
//            System.out.println("chan doan: " + chandoan);
            if (con.equals(facts)) {
                for (String rule : rulesListInput) {
                    if (!rule.contains(chandoan)) {
                        continue;
                    }
                    int ruleResultIdx = rule.indexOf("\"");
                    String condition = rule.substring(0, ruleResultIdx - 1);
//                    System.out.println(condition);
                    String ruleResult = rule.substring(ruleResultIdx + 1, rule.length() - 1).trim(); // lay sau dau ,
                    if (condition.equalsIgnoreCase(chandoan + "," + tiensu)) {
                        // do something
                        taFactsResult.append(ruleResult);
                        return;
                    }
                }
            }
        }

        taFactsResult.append("Nh???p th??m th??ng tin ????? t?? v???n");
    }
    
    // ket qua xet nghiem
    String xuLyKetQuaXetNghiem() {
        String facts = "";

        String gender = congender.getSelectedItem().toString();

        // xet nghiem
        String astVal = xn1.getText().trim();
        String altVal = xn2.getText().trim();
        String bilTTVal = xn4.getText().trim();
        String albuminVal = xn5.getText().trim();
        String hbsag = xn6.getSelectedItem().toString();
        String antihbcigm = xn7.getSelectedItem().toString();
        String antihbcigg = xn8.getSelectedItem().toString();
        String hbeag = xn9.getSelectedItem().toString();
        String antihcv = xn10.getSelectedItem().toString();
        String hcvrna = xn11.getSelectedItem().toString();

        float ast, alt, bilTT, albumin, fiboE, fiboCAP;

        // bat ngoai le
        try {
            ast = Float.parseFloat(astVal);
            if (ast < 0) {
                throw new InputMismatchException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Gi?? tr??? AST ph???i nh???p ??? d???ng s???", "L???i", JOptionPane.ERROR_MESSAGE);
            return "";
        } catch (InputMismatchException e) {
            JOptionPane.showMessageDialog(null, "Gi?? tr??? AST ph???i l???n h??n 0", "L???i", JOptionPane.ERROR_MESSAGE);
            return "";
        }
        try {
            alt = Float.parseFloat(altVal);
            if (alt < 0) {
                throw new InputMismatchException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Gi?? tr??? ALT ph???i nh???p ??? d???ng s???", "L???i", JOptionPane.ERROR_MESSAGE);
            return "";
        } catch (InputMismatchException e) {
            JOptionPane.showMessageDialog(null, "Gi?? tr??? ALT ph???i l???n h??n 0", "L???i", JOptionPane.ERROR_MESSAGE);
            return "";
        }
        try {
            bilTT = Float.parseFloat(bilTTVal);
            if (bilTT < 0) {
                throw new InputMismatchException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Gi?? tr??? Bilirubin TT ph???i nh???p ??? d???ng s???", "L???i", JOptionPane.ERROR_MESSAGE);
            return "";
        } catch (InputMismatchException e) {
            JOptionPane.showMessageDialog(null, "Gi?? tr??? Bilirubin TT ph???i l???n h??n 0", "L???i", JOptionPane.ERROR_MESSAGE);
            return "";
        }
        try {
            albumin = Float.parseFloat(albuminVal);
            if (albumin < 0) {
                throw new InputMismatchException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Gi?? tr??? Albumin ph???i nh???p ??? d???ng s???", "L???i", JOptionPane.ERROR_MESSAGE);
            return "";
        } catch (InputMismatchException e) {
            JOptionPane.showMessageDialog(null, "Gi?? tr??? Albumin ph???i l???n h??n 0", "L???i", JOptionPane.ERROR_MESSAGE);
            return "";
        }
        try {
            fiboE = Float.parseFloat(fbE.getText().trim());
            if (fiboE < 0) {
                throw new InputMismatchException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Gi?? tr??? Fibroscan E ph???i nh???p ??? d???ng s???", "L???i", JOptionPane.ERROR_MESSAGE);
            return "";
        } catch (InputMismatchException e) {
            JOptionPane.showMessageDialog(null, "Gi?? tr??? Fibroscan E ph???i l???n h??n 0", "L???i", JOptionPane.ERROR_MESSAGE);
            return "";
        }
        try {
            fiboCAP = Float.parseFloat(fbCAP.getText().trim());
            if (fiboCAP < 0) {
                throw new InputMismatchException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Gi?? tr??? Fibroscan CAP ph???i nh???p ??? d???ng s???", "L???i", JOptionPane.ERROR_MESSAGE);
            return "";
        } catch (InputMismatchException e) {
            JOptionPane.showMessageDialog(null, "Gi?? tr??? Fibroscan CAP ph???i l???n h??n 0", "L???i", JOptionPane.ERROR_MESSAGE);
            return "";
        }

        // alt
        if ((alt < 19.0 && gender.equalsIgnoreCase("Nu")) || (alt < 30.0 && gender.equalsIgnoreCase("Nam")) || (alt < 40.0 && gender.equalsIgnoreCase("Tre em"))) {
            altVal = "Binh thuong";
        }
        else if (alt < 100.0) {
            altVal = "Tang nhe";
        }
        else if (alt < 300.0) {
            altVal = "Tang vua";
        }
        else if (alt >= 300) {
            altVal = "Tang cao";
        }

        // ast
        if ((ast < 35.0 && gender.equalsIgnoreCase("Nu")) || (ast < 50.0 && gender.equalsIgnoreCase("Nam")) || (ast < 60.0 && gender.equalsIgnoreCase("Tre em"))) {
            astVal = "Binh thuong";
        }
        else if (ast < 100.0) {
            astVal = "Tang nhe";
        }
        else if (ast < 300.0) {
            astVal = "Tang vua";
        }
        else if (ast >= 300) {
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
        else if (bilTT >= 0.4) {
            bilTTVal = "Cao";
        }

        // viem gan b
        boolean viem_gan_b_man_tinh = false;
        String viem_gan_b = "";
        if (hbsag.equals("-")) {
            facts += "\n- Kh??ng nhi????m vi??m gan B";
        } else {
            if (antihbcigm.equals("+")) {
                facts += "\n- Vi??m gan B c????p ti??nh";
                viem_gan_b = "viem gan B cap tinh";
            } else {
                if (antihbcigg.equals("+")) {
                    if (hbeag.equals("+")) {
                        facts += "\n- Vi??m gan B ma??n ti??nh, Virut ??ang nh??n l??n";
                        viem_gan_b_man_tinh = true;
                        viem_gan_b = "viem gan B man tinh";
                    } else {
                        facts += "\n- Vi??m gan B ma??n ti??nh, Virut kh??ng hoa??t ??????ng";
                        viem_gan_b_man_tinh = true;
                        viem_gan_b = "viem gan B man tinh";
                    }
                } else {
                    facts += "\n- Vi??m gan B c????p ti??nh";
                    viem_gan_b = "viem gan B cap tinh";
                }
            }
        }

        // viem gan c
        boolean viem_gan_c_man_tinh = false;
        String viem_gan_c = "";
        if (antihcv.equals("-")) {
            facts += "\n- Kh??ng nhi????m vi??m gan C";
        } else {
            if (hcvrna.equals("-")) {
                facts += "\n- Hi????n ta??i kh??ng nhi????m vi??m gan C (??a?? t????ng nhi????m trong qua?? kh????)";
            } else {
                facts += "\n- Nhi????m vi??m gan C ma??n ti??nh";
                viem_gan_c_man_tinh = true;
                viem_gan_c = "viem gan c man tinh";
            }
        }

        // gan nhiem mo
        String gan_nhiem_mo = "";
        if (fiboCAP <= 233) {
            facts += "\n- Kh??ng nhi????m gan nhi????m m????";
        } else if (fiboCAP <= 258) {
            facts += "\n- Gan nhi????m m???? nhe??";
            gan_nhiem_mo = "S1";
        } else if (fiboCAP <= 290) {
            facts += "\n- Gan nhi????m m???? v????a";
            gan_nhiem_mo = "S2";
        } else {
            facts += "\n- Gan nhi????m m???? n????ng";
            gan_nhiem_mo = "S3";
        }

        // viem gan do ruou
        if (!gan_nhiem_mo.equals("")) {
            if (btnCoUongCon.isSelected()) {
                facts += " do s???? du??ng r??????u";

                if (!astVal.equals("Binh thuong") && (float) (1.0 * ast / alt) > 2.0) {
                    facts += "\n- Vi??m gan do r??????u";
                }
            }
        }

        // danh gia xo gan
        // fibroscran
        boolean f4 = false, f3 = false, f2 = false, f1 = false;
        String xo_gan = "";
        if (viem_gan_b_man_tinh) {
            if (fiboE <= 6.0) {
                facts += "\n- X?? hoa?? nhe?? do vi??m gan B";
                f1 = true;
            } else if (fiboE <= 9.0) {
                facts += "\n- X?? hoa?? ??a??ng k???? do vi??m gan B";
                f2 = true;
            } else if (fiboE <= 12) {
                facts += "\n- X?? hoa?? n????ng do vi??m gan B";
                f3 = true;
            } else {
                xo_gan += "\n- X?? gan do vi??m gan B";
                f4 = true;
            }
        }
        if (viem_gan_c_man_tinh) {
            if (fiboE <= 7.0) {
                facts += "\n- X?? hoa?? nhe?? do vi??m gan C";
                f1 = true;
            } else if (fiboE <= 9.5) {
                facts += "\n- X?? hoa?? ??a??ng k???? do vi??m gan C";
                f2 = true;
            } else if (fiboE <= 12) {
                facts += "\n- X?? hoa?? n????ng do vi??m gan C";
                f3 = true;
            } else {
                xo_gan += "\n- X?? gan do vi??m gan C";
                f4 = true;
            }
        }

        // danh gia xo gan
        if (!gan_nhiem_mo.equals("") && btnCoUongCon.isSelected() && (float) (ast / alt) > 1.0 && !f4) {
            xo_gan += "\n- X?? gan do r??????u";
            f4 = true;
        }

        if (f4 && metmoichanan.isSelected()) {
            xo_gan += "\n- X?? gan co??n bu??";
        } else if (f4 && bungphinh.isSelected() && chanphune.isSelected() && albuminVal.equalsIgnoreCase("Thap")) {
            xo_gan += "\n- X?? gan c??? ch?????ng??";
        } else if (f4 && vangdamat.isSelected() && nuoctieusammau.isSelected() && bilTTVal.equalsIgnoreCase("Cao")) {
            xo_gan += "\n- X?? gan c??? ch?????ng";
        }

        facts += xo_gan;

        String totalRestuls = "\nK????t lu????n:";
        if (f4) {
            totalRestuls += " Gan bi?? x?? giai ??oa??n f4";
        } else if (f3) {
            totalRestuls += " Gan bi?? x?? hoa?? giai ??oa??n f3";
        } else if (f2) {
            totalRestuls += " Gan bi?? x?? hoa?? giai ??oa??n f2";
        } else if (f1) {
            totalRestuls += " Gan bi?? x?? hoa?? giai ??oa??n f1";
        } else if (!gan_nhiem_mo.equals("")) {
            totalRestuls += " Gan bi?? nhi????m m??? giai ??o???n " + gan_nhiem_mo;
        } else if (!viem_gan_b.equals("")) {
            totalRestuls += " " + viem_gan_b;
        } else if (!viem_gan_c.equals("")) {
            totalRestuls += " " + viem_gan_c;
        } else if (!astVal.equals("Binh thuong") || !altVal.equalsIgnoreCase("Binh thuong")) {
            if (astVal.equalsIgnoreCase("Tang nhe")) {
                totalRestuls += " Nghi ng??? vi??m gan virus c???p, c???n theo d??i th??m";
            } else if (astVal.equalsIgnoreCase("Tang vua")) {
                totalRestuls += " Nghi ng??? gan nhi???m m???, c???n theo d??i th??m";
            } else if (astVal.equalsIgnoreCase("Tang cao") || (float)(2.0 * ast / alt) >= 2.0) {
                totalRestuls += " Nghi ng??? x?? gan, c???n theo d??i th??m";
            }
        } else if (!bilTTVal.equalsIgnoreCase("Binh thuong") || !albuminVal.equalsIgnoreCase("Binh thuong")) {
            totalRestuls += " Nghi ng??? x?? gan, c???n theo d??i th??m";
        } else {
            totalRestuls += " Gan ho???t ?????ng b??nh th?????ng";
        }

        return facts + totalRestuls;
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
        vangdamat = new javax.swing.JCheckBox();
        tiensugiadinh = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        nuoctieusammau = new javax.swing.JCheckBox();
        metmoichanan = new javax.swing.JCheckBox();
        daubungnonmua = new javax.swing.JCheckBox();
        bungphinh = new javax.swing.JCheckBox();
        chanphune = new javax.swing.JCheckBox();
        nguadamunnhot = new javax.swing.JCheckBox();
        sotnhe = new javax.swing.JCheckBox();
        dautucgan = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        phannhatmau = new javax.swing.JCheckBox();
        tungtruyenmau = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        congender = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        xn1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        xn2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
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
        bnTuVan = new javax.swing.JButton();
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
        jLabel36 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        vangdamat.setText("Vang da; mat");

        tiensugiadinh.setText("Gia dinh co nguoi bi benh gan");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("C??c tri???u ch???ng");

        nuoctieusammau.setText("Nuoc tieu sam mau");

        metmoichanan.setText("Met moi; chan an; roi loan tieu hoa");

        daubungnonmua.setText("Dau bung; non mua");

        bungphinh.setText("Bung phinh");

        chanphune.setText("Chan phu ne");

        nguadamunnhot.setText("Ngua da; me day; mun nhot");

        sotnhe.setText("Sot nhe");

        dautucgan.setText("Dau tuc hay kho chiu vung gan (vung bung ben phai)");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Ti???n s???");

        phannhatmau.setText("Phan nhat mau");

        tungtruyenmau.setText("Tung truyen mau");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Th??ng tin");

        congender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nu", "Tre em" }));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("C??c ch??? s??? x??t nghi???m");

        jLabel8.setText("AST");

        jLabel11.setText("UI/L");

        jLabel9.setText("ALT");

        jLabel12.setText("UI/L");

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

        bnTuVan.setText("T?? v???n");
        bnTuVan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnTuVanActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("K???t qu???");

        taFactsResult.setEditable(false);
        taFactsResult.setColumns(20);
        taFactsResult.setLineWrap(true);
        taFactsResult.setRows(5);
        jScrollPane1.setViewportView(taFactsResult);

        jLabel55.setText("S??? d???ng ????? u???ng c?? c???n");

        jLabel56.setText("Th???i gian u???ng");

        btnGroupThoiGianUong.add(ruou1);
        ruou1.setText("< 1 n??m");
        ruou1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ruou1ActionPerformed(evt);
            }
        });

        btnGroupThoiGianUong.add(ruou2);
        ruou2.setText("1-3 n??m");
        ruou2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ruou2ActionPerformed(evt);
            }
        });

        btnGroupThoiGianUong.add(ruou3);
        ruou3.setText("> 3 n??m");
        ruou3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ruou3ActionPerformed(evt);
            }
        });

        jLabel57.setText("C??c th???c u???ng");

        btnGroupCachThucUong.add(ruou4);
        ruou4.setText("Li??n t???c (4-5 l???n/ tu???n)");
        ruou4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ruou4ActionPerformed(evt);
            }
        });

        btnGroupCachThucUong.add(ruou5);
        ruou5.setText("Th???nh tho???ng (1-2 l???n/tu???n)");
        ruou5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ruou5ActionPerformed(evt);
            }
        });

        btnGroupXetNghiem.add(selectXetNghiem);
        selectXetNghiem.setText("c??");
        selectXetNghiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectXetNghiemActionPerformed(evt);
            }
        });

        btnGroupXetNghiem.add(selectKhongXetNghiem);
        selectKhongXetNghiem.setSelected(true);
        selectKhongXetNghiem.setText("kh??ng");
        selectKhongXetNghiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectKhongXetNghiemActionPerformed(evt);
            }
        });

        jLabel3.setText("Fibroscan:");

        jLabel4.setText("E (kPa)");

        jLabel5.setText("CAP (dB/m)");

        btnGroupDoUongCon.add(jRadioButton11);
        jRadioButton11.setText("Kh??ng");
        jRadioButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton11ActionPerformed(evt);
            }
        });

        btnGroupDoUongCon.add(btnCoUongCon);
        btnCoUongCon.setText("C??");
        btnCoUongCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCoUongConActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel36.setText("* ph???i ch???n c??? tri???u ch???ng v?? ti???n s???");

        btnReset.setText("Nh???p l???i");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fbCAP, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fbE, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 298, Short.MAX_VALUE))
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
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12)
                                        .addGap(32, 32, 32))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(tungtruyenmau, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(66, 66, 66)
                                                .addComponent(congender, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(tiensugiadinh, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addComponent(jLabel36)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel56)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(94, 94, 94)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(dautucgan)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jRadioButton11)
                                                        .addComponent(daubungnonmua))
                                                    .addComponent(metmoichanan)))
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
                                        .addComponent(ruou4))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(xn9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(xn10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel23)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(xn11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(xn4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel15)
                                        .addGap(125, 125, 125))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
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
                                                .addComponent(jLabel11)))
                                        .addGap(65, 65, 65)
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(xn5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel17)))
                                .addGap(0, 311, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(vangdamat, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(phannhatmau, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(nuoctieusammau, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(sotnhe))
                                        .addGap(92, 92, 92)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(bungphinh)
                                            .addComponent(chanphune)
                                            .addComponent(nguadamunnhot))
                                        .addGap(363, 363, 363))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(93, 93, 93)))))
                        .addContainerGap(9, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(xn6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(188, 188, 188)
                                .addComponent(xn7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(xn8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bnTuVan, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26))
                                .addGap(43, 43, 43)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnReset)
                                    .addComponent(jLabel19))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chanphune)
                            .addComponent(dautucgan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bungphinh)
                            .addComponent(metmoichanan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nguadamunnhot)
                            .addComponent(daubungnonmua))
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
                        .addComponent(vangdamat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(phannhatmau)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nuoctieusammau)
                        .addGap(3, 3, 3)
                        .addComponent(sotnhe)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tiensugiadinh)
                            .addComponent(jLabel55)
                            .addComponent(btnCoUongCon))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tungtruyenmau)
                            .addComponent(jLabel56)
                            .addComponent(ruou1)
                            .addComponent(ruou2))))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(ruou4)
                    .addComponent(ruou5)
                    .addComponent(jLabel36))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(selectXetNghiem)
                    .addComponent(selectKhongXetNghiem))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(xn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel9)
                    .addComponent(xn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel16)
                    .addComponent(xn5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel13)
                    .addComponent(xn4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel18)
                    .addComponent(xn6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(xn7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(xn8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(xn9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(xn10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xn11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(fbE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(fbCAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnTuVan)
                    .addComponent(btnReset))
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("T?? v???n", jPanel1);

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
        ruou4.setActionCommand("Lien tuc (4-5 lan/tuan)");
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
        String facts = "";
        String tiensu = "";
        taFactsResult.setText("");
        
        facts = convertSelectionToString();
        tiensu = tiensuToString();
        if (!facts.equals("") && !tiensu.equals("")) {
            System.out.println("input: " + facts + "," + tiensu);
            compareRules(facts, tiensu);
        }
        else if(!facts.equals("") && tiensu.equals("")){
            JOptionPane.showMessageDialog(this, "H??y ch???n ??t nh???t 1 ti???n s???!");
        }
        
        else if(facts.equals("") && !tiensu.equals("")){
            JOptionPane.showMessageDialog(this, "H??y ch???n ??t nh???t 1 bi???u hi???n!");
        }
        // xet nghiem
        if (coKetQuaXetNghiem) {
            taFactsResult.append("\nK????t lu????n xe??t nghi????m:");
            taFactsResult.append(xuLyKetQuaXetNghiem());
        }
    }//GEN-LAST:event_bnTuVanActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        //gender
        congender.setSelectedItem("Nam");

        // bieu hien
        vangdamat.setSelected(false);
        phannhatmau.setSelected(false);
        nuoctieusammau.setSelected(false);
        sotnhe.setSelected(false);
        chanphune.setSelected(false);
        bungphinh.setSelected(false);
        nguadamunnhot.setSelected(false);
        dautucgan.setSelected(false);
        metmoichanan.setSelected(false);
        daubungnonmua.setSelected(false);
        
        // tien su
        tiensugiadinh.setSelected(false);
        tungtruyenmau.setSelected(false);
        btnGroupDoUongCon.clearSelection();
        btnGroupCachThucUong.clearSelection();
        btnGroupThoiGianUong.clearSelection();
        selectKhongXetNghiem.setSelected(true);
        
        //xet nghiem
        coKetQuaXetNghiem = false;
        xn1.setText("");
        xn2.setText("");
        xn4.setText("");
        xn5.setText("");
        fbE.setText("");
        fbCAP.setText("");
        xn6.setSelectedItem("-");
        xn7.setSelectedItem("-");
        xn8.setSelectedItem("-");
        xn9.setSelectedItem("-");
        xn10.setSelectedItem("-");
        xn11.setSelectedItem("-");
        
        //result
        taFactsResult.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

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
    private javax.swing.JButton btnReset;
    private javax.swing.JCheckBox bungphinh;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.JCheckBox chanphune;
    private javax.swing.JComboBox<String> congender;
    private javax.swing.JCheckBox daubungnonmua;
    private javax.swing.JCheckBox dautucgan;
    private javax.swing.JTextField fbCAP;
    private javax.swing.JTextField fbE;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel36;
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
    private javax.swing.JCheckBox metmoichanan;
    private javax.swing.JCheckBox nguadamunnhot;
    private javax.swing.JCheckBox nuoctieusammau;
    private javax.swing.JCheckBox phannhatmau;
    private javax.swing.JRadioButton ruou1;
    private javax.swing.JRadioButton ruou2;
    private javax.swing.JRadioButton ruou3;
    private javax.swing.JRadioButton ruou4;
    private javax.swing.JRadioButton ruou5;
    private javax.swing.JRadioButton selectKhongXetNghiem;
    private javax.swing.JRadioButton selectXetNghiem;
    private javax.swing.JCheckBox sotnhe;
    private javax.swing.JTextArea taFactsResult;
    private javax.swing.JCheckBox tiensugiadinh;
    private javax.swing.JCheckBox tungtruyenmau;
    private javax.swing.JCheckBox vangdamat;
    private javax.swing.JTextField xn1;
    private javax.swing.JComboBox<String> xn10;
    private javax.swing.JComboBox<String> xn11;
    private javax.swing.JTextField xn2;
    private javax.swing.JTextField xn4;
    private javax.swing.JTextField xn5;
    private javax.swing.JComboBox<String> xn6;
    private javax.swing.JComboBox<String> xn7;
    private javax.swing.JComboBox<String> xn8;
    private javax.swing.JComboBox<String> xn9;
    // End of variables declaration//GEN-END:variables
}
