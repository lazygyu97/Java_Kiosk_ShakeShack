package com.company.Frame;

import com.company.Food.*;
import com.company.Menu.Menu;
import com.company.Menu.MenuAddOperation;
import com.company.Order.OrderAddOperation;
import com.company.Order.OrderCancleOperation;
import com.company.Order.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener {

    JFrame window,payment,receipt;
    JButton[] menu;

    JButton[] burger;
    JButton[] side;
    JButton[] drink;

    JButton order;
    JButton cancle;
    JButton back;
    JButton card;
    JButton cash;
    JButton notice;
    JButton home;

    int waitingLine=0;

    JPanel menu_panle_1,menu_panle_2,menu_panle_3,total;

    JTextArea food,price,count,total_order_1,total_order_2,total_order_3,total_order_4,total_order_5
            ,view_receipt_1,view_receipt_2,view_receipt_3,view_receipt_4,view_receipt_5;

    Color bg =new Color(227,227,227);

    NumberFormat numberFormat = NumberFormat.getInstance();

    // 메뉴 클래스 선언
    Menu menuAdd=new Menu(new MenuAddOperation());
    public static ArrayList<Menu> menuList = new ArrayList<>();

    //음식 클래스 선언
    Food burgerAdd = new Food(new BurgerAddOperation());
    Food sideAdd = new Food(new SideAddOperation());
    Food drinkAdd = new Food(new DrinkAddOperation());
    public static ArrayList<Food> burgerList = new ArrayList<>();
    public static ArrayList<Food> sideList = new ArrayList<>();
    public static ArrayList<Food> drinkList = new ArrayList<>();

    //주문 관련 선언
    Order orderAdd= new Order(new OrderAddOperation());
    Order orderCancle= new Order(new OrderCancleOperation());
    public static ArrayList<Order> orderList = new ArrayList<>();

    public void addOrder(Food food){

        String foodName = food.foodName;
        int price = food.price;
        int count =1;
        int count_index = 0;
        boolean check = false;

        int total_price=0;
        int total_count=0;

        //장바구니에 아무것도 없는 상황
        if(orderList.size()==0){

            System.out.println("장바구니에 추가합니다!");
            orderAdd.Order(foodName,price,count);
            total_order_1.setText("          "+orderList.get(0).food);
            total_order_2.setText("          "+numberFormat.format(orderList.get(0).price)+" 원");
            total_order_3.setText("          "+orderList.get(0).count+" 개");
            total_order_4.setText("          총 수량 :"+orderList.get(0).count+" 개");
            total_order_5.setText("    결제하실 가격 :"+numberFormat.format(orderList.get(0).price)+" 원");
        }else if(orderList.size()>0 ){

            total_order_1.setText(null);
            total_order_2.setText(null);
            total_order_3.setText(null);
            total_order_4.setText(null);
            total_order_5.setText(null);

            for (int i=0;i<orderList.size();i++){
                System.out.println(i);
                if(orderList.get(i).food==foodName){
                    System.out.println("이미 추가 되었습니다. 값을 늘립니다.");
                    count_index=i;
                    check=true;
                    break;
                }else {
                    System.out.println("장바구니에 추가합니다!");

                }
            }
            if(check==true){
                orderAdd.Order(foodName,price,++orderList.get(count_index).count);
                orderList.remove(count_index);

            }else if(check==false){
                orderAdd.Order(foodName,price,count);
            }

            for(int i =0;i <orderList.size();i++){

                total_order_1.append("          "+orderList.get(i).food+"\n");
                total_order_2.append("          "+numberFormat.format(orderList.get(i).price)+" 원\n");
                total_order_3.append("          "+orderList.get(i).count+" 개\n");
                if(orderList.get(i).count>1){
                    for(int j=1;j<=orderList.get(i).count;j++){
                        total_price+=orderList.get(i).price;
                    }
                }else {
                    total_price+=orderList.get(i).price;

                }
                total_count+=orderList.get(i).count;

            }

            total_order_4.setText("          총 수량 :"+total_count+" 개");
            total_order_5.setText("    결제하실 가격 :"+  numberFormat.format(total_price)+" 원");

        }

    }//addOrder

    @Override
    public void actionPerformed(ActionEvent e) {
        int qut_data;

        if(e.getSource()==menu[0]) {
            menu_panle_1.setVisible(true);
            menu_panle_2.setVisible(false);
            menu_panle_3.setVisible(false);
        }else if(e.getSource()==menu[1]) {
            menu_panle_1.setVisible(false);
            menu_panle_2.setVisible(true);
            menu_panle_3.setVisible(false);
        }else if(e.getSource()==menu[2]) {
            menu_panle_1.setVisible(false);
            menu_panle_2.setVisible(false);
            menu_panle_3.setVisible(true);
        }
        else if(e.getSource()==order) {
            if(orderList.size()!=0){
                window.setVisible(false);
                paymentFrame();
                payment.setVisible(true);
            }else{
              JOptionPane.showMessageDialog(window, "상품을 선택해주세요","주문 실패",JOptionPane.ERROR_MESSAGE);

            }

        }else if(e.getSource()==back) {
            payment.dispose();
            window.setVisible(true);

        }
        else if(e.getSource()==cancle) {
            if (orderList.size()>0){
                qut_data = JOptionPane.showConfirmDialog(window, "장바구니를 비우시겠습니까?","장바구니 비우기", JOptionPane.YES_NO_OPTION);
                if(qut_data==0){
                    orderList.clear();
                    total_order_1.setText(null);
                    total_order_2.setText(null);
                    total_order_3.setText(null);
                    total_order_4.setText(null);
                    total_order_5.setText(null);
                }
            }else {
                JOptionPane.showMessageDialog(window, "담겨진 상품이 없습니다.","장바구니 비우기",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource()==card) {
            waitingLine+=1;
            payment.dispose();
            receiptFrame();
            receipt.setVisible(true);
             notice.setText("<html><body><center>영수증<br>카드결제 완료되었습니다! 이용해주셔서 감사합니다.<br>대기번호 : "+waitingLine+"번</center></body></html>");
        }
        else if(e.getSource()==cash) {
            waitingLine+=1;
            payment.dispose();
            receiptFrame();
            receipt.setVisible(true);
            notice.setText("<html><body><center>영수증<br>현금결제 완료되었습니다! 이용해주셔서 감사합니다.<br>대기번호 : "+waitingLine+"번</center></body></html>");
        }
        else if(e.getSource()==home) {
            receipt.dispose();
            total_order_1.setText(null);
            total_order_2.setText(null);
            total_order_3.setText(null);
            total_order_4.setText(null);
            total_order_5.setText(null);
            orderList.clear();
            menu_panle_1.setVisible(true);
            menu_panle_2.setVisible(false);
            menu_panle_3.setVisible(false);
            window.setVisible(true);
        }

        for (int i=0 ;i<burger.length;i++){
            if(e.getSource()==burger[i]){
                qut_data = JOptionPane.showConfirmDialog(window, burgerList.get(i).foodName+"을(를) 장바구니에 추가하시겠습니까? ","장바구니 추가 확인", JOptionPane.YES_NO_OPTION);
                if(qut_data==0){
                    Food food= burgerList.get(i);
                    int count=1;
                    addOrder(food);
                }else {
                    System.out.println("장바구니에 넣지 않습니다.");
                }
                break;
                }
            }
        for (int i=0 ;i<side.length;i++){
            if(e.getSource()==side[i]){
                qut_data = JOptionPane.showConfirmDialog(window, sideList.get(i).foodName+"을(를) 장바구니에 추가하시겠습니까? ","장바구니 추가 확인", JOptionPane.YES_NO_OPTION);
                if(qut_data==0){
                    Food food= sideList.get(i);
                    int count=1;
                    addOrder(food);
                }else {
                    System.out.println("장바구니에 넣지 않습니다.");
                }
                break;
            }
        }
        for (int i=0 ;i<drink.length;i++){
            if(e.getSource()==drink[i]){
                qut_data = JOptionPane.showConfirmDialog(window, drinkList.get(i).foodName+"을(를) 장바구니에 추가하시겠습니까? ","장바구니 추가 확인", JOptionPane.YES_NO_OPTION);
                if(qut_data==0){
                    Food food= drinkList.get(i);
                    int count=1;
                    addOrder(food);
                }else {
                    System.out.println("장바구니에 넣지 않습니다.");
                }
                break;
            }
        }



    }


    // 메뉴 버튼 추가 함수
    private void setMenu() {
        menuList=menuAdd.Menu("버거","res/image/menu_tit_01.gif",71,138,195,35);
        menuList=menuAdd.Menu("사이드","res/image/menu_tit_02.gif",353,138,195,35);
        menuList=menuAdd.Menu("음료","res/image/menu_tit_03.gif",635,138,195,35);
        menu=new JButton[menuList.size()];
    }

    // 음식 추가 함수
    public void setFoodList() {
        //버거 메뉴 추가 - 5개
        burgerList=burgerAdd.Food("쉑 버거","res/image/burger_01.jpeg",8400,35,41,195,205);
        burgerList=burgerAdd.Food("스모크쉑","res/image/burger_02.jpeg",10600,316,41,195,205);
        burgerList=burgerAdd.Food("슈룸 버거","res/image/burger_03.jpeg",10000,597,41,195,205);
        burgerList=burgerAdd.Food("쉑 스택","res/image/burger_04.jpeg",14800,35,305,195,205);
        burgerList=burgerAdd.Food("햄버거","res/image/burger_05.jpeg",6800,316,305,195,205);

        //사이드 메뉴 추가- 6개
        sideList=sideAdd.Food("치즈 프라이","res/image/side_01.jpeg",5900,35,41,195,205);
        sideList=sideAdd.Food("프라이","res/image/side_02.jpeg",4800,316,41,195,205);
        sideList=sideAdd.Food("핫도그","res/image/side_03.jpeg",4800,597,41,195,205);
        sideList=sideAdd.Food("쉑 어택","res/image/side_04.jpeg",6200,35,305,195,205);
        sideList=sideAdd.Food("허니 버터 크런치","res/image/side_05.jpeg",6200,316,305,195,205);
        sideList=sideAdd.Food("쉑 인 더 가든","res/image/side_06.jpeg",6200,597,305,195,205);

        //음료 메뉴 추가- 8개
        drinkList=drinkAdd.Food("블루베리 레몬에이드","res/image/drink_01.jpeg",5900,35,41,195,205);
        drinkList=drinkAdd.Food("레몬에이드","res/image/drink_02.jpeg",4800,316,41,195,205);
        drinkList=drinkAdd.Food("유기농 아이스티","res/image/drink_03.jpeg",4800,597,41,195,205);
        drinkList=drinkAdd.Food("피프티/피프티","res/image/drink_04.jpeg",6200,35,305,195,205);
        drinkList=drinkAdd.Food("콜라","res/image/drink_05.jpeg",6200,316,305,195,205);
        drinkList=drinkAdd.Food("루트 비어","res/image/drink_06.jpeg",6200,597,305,195,205);
        drinkList=drinkAdd.Food("핫 티","res/image/drink_07.jpeg",6200,35,569,195,205);
        drinkList=drinkAdd.Food("커피","res/image/drink_08.jpeg",6200,316,569,195,205);

        burger=new JButton[burgerList.size()];
        side=new JButton[sideList.size()];
        drink=new JButton[drinkList.size()];


    }

    //영수증 만들기
    public void makeReceipt(){

        int total_price=0;
        int total_count=0;

        for(int i = 0; i <orderList.size(); i++){

            view_receipt_1.append("\n\n         "+orderList.get(i).food+"\n\n");
            view_receipt_2.append("\n\n          "+numberFormat.format(orderList.get(i).price)+" 원\n\n");
            view_receipt_3.append("\n\n          "+orderList.get(i).count+" 개\n\n");

            total_price+=orderList.get(i).price;
            total_count+=orderList.get(i).count;

        }

        view_receipt_4.setText("\n\n          총 수량 :"+total_count+" 개");
        view_receipt_5.setText("\n\n    결제하실 가격 :  "+  numberFormat.format(total_price)+" 원");

    }
    public void paymentFrame(){
        payment = new JFrame("SHAKE SHACK : 결제창");
        payment.setBackground(new Color(255 ,254,255));
        payment.setSize(900,1400);
        payment.setLocationRelativeTo(null);
        payment.getContentPane().setLayout(null);
        payment.setResizable(false);

        //로고 이미지 사이즈 변경 코드
        ImageIcon icon = new ImageIcon("res/image/ss_logo.png");
        Image img= icon.getImage();
        Image changeImg = img.getScaledInstance(369,81,Image.SCALE_SMOOTH);
        ImageIcon changeIcon =new ImageIcon(changeImg);

        // 메인 로고 생성 및 설정
        JButton logoBtn = new JButton(changeIcon);
        logoBtn.setBounds(239,12,422,93);
        logoBtn.setBorderPainted(false);

        notice = new JButton("아래 영수증을 확인하시고, 결제 수단을 선택해주세요");
        notice.setBounds(53,140,795,100);
        notice.setHorizontalTextPosition(JLabel.CENTER);
        notice.setVerticalTextPosition(JLabel.BOTTOM);
        notice.setBorderPainted(false);
        notice.setFont(new Font("고딕", Font.BOLD,20));

        view_receipt_1= new JTextArea();
        view_receipt_1.setBounds(52,277,265,702);
        view_receipt_1.setLayout(null);
        view_receipt_1.setBackground(bg);
        view_receipt_1.setFont(new Font("고딕", Font.BOLD,15));

        view_receipt_2= new JTextArea();
        view_receipt_2.setBounds(317,277,265,702);
        view_receipt_2.setLayout(null);
        view_receipt_2.setBackground(bg);
        view_receipt_2.setFont(new Font("고딕", Font.BOLD,15));

        view_receipt_3= new JTextArea();
        view_receipt_3.setBounds(582,277,265,702);
        view_receipt_3.setLayout(null);
        view_receipt_3.setBackground(bg);
        view_receipt_3.setFont(new Font("고딕", Font.BOLD,15));

        view_receipt_4= new JTextArea();
        view_receipt_4.setBounds(52,1000,280,100);
        view_receipt_4.setLayout(null);
        view_receipt_4.setBackground(bg);
        view_receipt_4.setFont(new Font("고딕", Font.BOLD,15));

        view_receipt_5= new JTextArea();
        view_receipt_5.setBounds(332,1000,280,100);
        view_receipt_5.setLayout(null);
        view_receipt_5.setBackground(bg);
        view_receipt_5.setFont(new Font("고딕", Font.BOLD,15));

        back = new JButton("뒤로가기");
        back.setBounds(643,1000,204,100);
        back.setFont(new Font("고딕", Font.BOLD,17));
        back.addActionListener(this);

        card = new JButton("카드 결제");
        card.setBounds(53,1120,390,166);
        card.setFont(new Font("고딕", Font.BOLD,17));
        card.addActionListener(this);

        cash = new JButton("현금 결제");
        cash.setBounds(458,1120,390,166);
        cash.setFont(new Font("고딕", Font.BOLD,17));
        cash.addActionListener(this);

        makeReceipt();

        payment.add(logoBtn);
        payment.add(notice);
        payment.add(back);
        payment.add(card);
        payment.add(cash);

        payment.add(view_receipt_1);
        payment.add(view_receipt_2);
        payment.add(view_receipt_3);
        payment.add(view_receipt_4);
        payment.add(view_receipt_5);

    }

    public void receiptFrame(){

        receipt = new JFrame("SHAKE SHACK : 결제창");
        receipt.setBackground(new Color(255 ,254,255));
        receipt.setSize(900,1400);
        receipt.setLocationRelativeTo(null);
        receipt.getContentPane().setLayout(null);
        receipt.setResizable(false);

        //로고 이미지 사이즈 변경 코드
        ImageIcon icon = new ImageIcon("res/image/ss_logo.png");
        Image img= icon.getImage();
        Image changeImg = img.getScaledInstance(369,81,Image.SCALE_SMOOTH);
        ImageIcon changeIcon =new ImageIcon(changeImg);

        // 메인 로고 생성 및 설정
        JButton logoBtn = new JButton(changeIcon);
        logoBtn.setBounds(239,12,422,93);
        logoBtn.setBorderPainted(false);

        notice = new JButton("아래 영수증을 확인하시고, 결제 수단을 선택해주세요");
        notice.setBounds(53,140,795,100);
        notice.setHorizontalTextPosition(JLabel.CENTER);
        notice.setVerticalTextPosition(JLabel.BOTTOM);
        notice.setBorderPainted(false);
        notice.setFont(new Font("고딕", Font.BOLD,20));

        view_receipt_1= new JTextArea();
        view_receipt_1.setBounds(52,277,265,702);
        view_receipt_1.setLayout(null);
        view_receipt_1.setBackground(bg);
        view_receipt_1.setFont(new Font("고딕", Font.BOLD,15));

        view_receipt_2= new JTextArea();
        view_receipt_2.setBounds(317,277,265,702);
        view_receipt_2.setLayout(null);
        view_receipt_2.setBackground(bg);
        view_receipt_2.setFont(new Font("고딕", Font.BOLD,15));

        view_receipt_3= new JTextArea();
        view_receipt_3.setBounds(582,277,265,702);
        view_receipt_3.setLayout(null);
        view_receipt_3.setBackground(bg);
        view_receipt_3.setFont(new Font("고딕", Font.BOLD,15));

        view_receipt_4= new JTextArea();
        view_receipt_4.setBounds(170,1000,280,100);
        view_receipt_4.setLayout(null);
        view_receipt_4.setBackground(bg);
        view_receipt_4.setFont(new Font("고딕", Font.BOLD,15));

        view_receipt_5= new JTextArea();
        view_receipt_5.setBounds(450,1000,280,100);
        view_receipt_5.setLayout(null);
        view_receipt_5.setBackground(bg);
        view_receipt_5.setFont(new Font("고딕", Font.BOLD,15));

        home = new JButton("홈으로");
        home.setBounds(348,1120,204,100);
        home.setFont(new Font("고딕", Font.BOLD,17));
        home.addActionListener(this);



        makeReceipt();

        receipt.add(logoBtn);
        receipt.add(notice);
        receipt.add(home);

        receipt.add(view_receipt_1);
        receipt.add(view_receipt_2);
        receipt.add(view_receipt_3);
        receipt.add(view_receipt_4);
        receipt.add(view_receipt_5);
    }

    public MainFrame(){ //프레임 생성

        //jframe 생성 및 설정
        window = new JFrame("SHAKE SHACK");
        window.setBackground(new Color(255 ,254,255));
        window.setSize(900,1400);
        window.setLocationRelativeTo(null);
        window.getContentPane().setLayout(null);
        window.setResizable(false);


        //로고 이미지 사이즈 변경 코드
        ImageIcon icon = new ImageIcon("res/image/ss_logo.png");
        Image img= icon.getImage();
        Image changeImg = img.getScaledInstance(369,81,Image.SCALE_SMOOTH);
        ImageIcon changeIcon =new ImageIcon(changeImg);


        // 메인 로고 생성 및 설정
        JButton logoBtn = new JButton(changeIcon);
        logoBtn.setBounds(239,12,422,93);
        logoBtn.setBorderPainted(false);

        // 메뉴 버튼 설정 함수
        setMenu();

        //각 메뉴판 버튼
        for(int i = 0; i<menuList.size();i++){
            menu[i] = new JButton(new ImageIcon(menuList.get(i).mapping));
            menu[i].setBounds(menuList.get(i).x,menuList.get(i).y,menuList.get(i).width,menuList.get(i).height);
            menu[i].setBorderPainted(false);
            menu[i].addActionListener(this);
            window.add(menu[i]);
        }

        // 각 매뉴 패널 생성 코드
        // 버거 메뉴판
        menu_panle_1= new JPanel();
        menu_panle_1.setBounds(35,220,829,805);
        menu_panle_1.setLayout(null);
        menu_panle_1.setBackground(bg);

        //사이드 메뉴판
        menu_panle_2= new JPanel();
        menu_panle_2.setBounds(35,220,829,805);
        menu_panle_2.setLayout(null);
        menu_panle_2.setBackground(bg);

        // 음료 메뉴판
        menu_panle_3= new JPanel();
        menu_panle_3.setBounds(35,220,829,805);
        menu_panle_3.setLayout(null);
        menu_panle_3.setBackground(bg);

        // 메뉴 추가 함수
        setFoodList();

        // 메뉴판에 메뉴 추가
        // 버거 메뉴 추가
        for(int i =0;i<burgerList.size();i++){
            burger[i] =new JButton("<html><body><center>"+burgerList.get(i).foodName+"<br>"+burgerList.get(i).price+"</center></body></html>",new ImageIcon(burgerList.get(i).mapping));
            burger[i].setBounds(burgerList.get(i).x,burgerList.get(i).y,burgerList.get(i).width,burgerList.get(i).height);
            burger[i].setHorizontalTextPosition(JLabel.CENTER);
            burger[i].setVerticalTextPosition(JLabel.BOTTOM);
            burger[i].addActionListener(this);
            menu_panle_1.add(burger[i]);
        }

        // 사이드 메뉴 추가
        for(int i =0;i<sideList.size();i++){
            side[i] =new JButton("<html><body><center>"+sideList.get(i).foodName+"<br>"+sideList.get(i).price+"</center></body></html>",new ImageIcon(sideList.get(i).mapping));
            side[i].setBounds(sideList.get(i).x,sideList.get(i).y,sideList.get(i).width,sideList.get(i).height);
            side[i].setHorizontalTextPosition(JLabel.CENTER);
            side[i].setVerticalTextPosition(JLabel.BOTTOM);
            side[i].addActionListener(this);
            menu_panle_2.add(side[i]);
        }

        // 음료 메뉴 추가
        for(int i =0;i<drinkList.size();i++){
            drink[i] =new JButton("<html><body><center>"+drinkList.get(i).foodName+"<br>"+drinkList.get(i).price+"</center></body></html>",new ImageIcon(drinkList.get(i).mapping));
            drink[i].setBounds(drinkList.get(i).x,drinkList.get(i).y,drinkList.get(i).width,drinkList.get(i).height);
            drink[i].setHorizontalTextPosition(JLabel.CENTER);
            drink[i].setVerticalTextPosition(JLabel.BOTTOM);
            drink[i].addActionListener(this);
            menu_panle_3.add(drink[i]);
        }

        // 버거 메뉴가 먼저 보이게 설정
        menu_panle_1.setVisible(true);
        menu_panle_2.setVisible(false);
        menu_panle_3.setVisible(false);

        // 장바구니 패널
        total= new JPanel();
        total.setBounds(35,1050,829,275);
        total.setLayout(null);
        total.setBackground(bg);

        food=new JTextArea();
        food.setText("          상품명");
        food.setBounds(125,1100,150,30);
        food.setLayout(null);
        food.setBackground(Color.white);
        food.setEditable(false);

        price=new JTextArea();
        price.setText("          가격");
        price.setBounds(275,1100,150,30);
        price.setLayout(null);
        price.setBackground(Color.white);
        price.setEditable(false);

        count=new JTextArea();
        count.setText("          수량");
        count.setBounds(425,1100,150,30);
        count.setLayout(null);
        count.setBackground(Color.white);
        count.setEditable(false);

        //장바구니 상품명
        total_order_1=new JTextArea();
        total_order_1.setBounds(125,1130,150,110);
        total_order_1.setLayout(null);
        total_order_1.setBackground(Color.white);
        total_order_1.setEditable(false);
        //장바구니 가격
        total_order_2=new JTextArea();
        total_order_2.setBounds(275,1130,150,110);
        total_order_2.setLayout(null);
        total_order_2.setBackground(Color.white);
        total_order_2.setEditable(false);
        //장바구니 수량
        total_order_3=new JTextArea();
        total_order_3.setBounds(425,1130,150,110);
        total_order_3.setLayout(null);
        total_order_3.setBackground(Color.white);
        total_order_3.setEditable(false);
        //장바구니 총 수량
        total_order_4=new JTextArea();
        total_order_4.setBounds(125,1240,225,40);
        total_order_4.setLayout(null);
        total_order_4.setBackground(Color.white);
        total_order_4.setEditable(false);
        //장바구니 총 가격
        total_order_5=new JTextArea();
        total_order_5.setBounds(350,1240,225,40);
        total_order_5.setLayout(null);
        total_order_5.setBackground(Color.white);
        total_order_5.setEditable(false);

        order =new JButton("주문하기");
        order.setBounds(615,1106,160,75);
        order.setHorizontalTextPosition(JLabel.CENTER);
        order.addActionListener(this);

        cancle =new JButton("주문취소");
        cancle.setBounds(615,1191,160,75);
        cancle.setHorizontalTextPosition(JLabel.CENTER);
        cancle.addActionListener(this);

        //생성한 요소 (버튼,패널) 추가
        window.add(logoBtn);
        window.add(menu_panle_1);
        window.add(menu_panle_2);
        window.add(menu_panle_3);
        window.add(food);
        window.add(price);
        window.add(count);
        window.add(total_order_1);
        window.add(total_order_2);
        window.add(total_order_3);
        window.add(total_order_4);
        window.add(total_order_5);
        window.add(order);
        window.add(cancle);
        window.add(total);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public static void main(String[] args) {

      new MainFrame();

    }
}
