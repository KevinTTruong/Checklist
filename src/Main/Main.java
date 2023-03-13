package Main;

import java.awt.EventQueue;
import java.io.File;

import BackendObjects.Checklist;
import GUI.ChecklistUI;

public class Main {
	public static void main(String[] args) {
//		new File("resources/data").delete();
//		Checklist list = new Checklist("resources/data");
//		list.addItem("test1");
//		list.addItem("test2loooooooooooooooooooong");
//		list.addItem("test3loooooooooooooooooooooooooooooooooooooooooooonger");
//		list.addItem("test4");
//		list.addItem("test5");
//		list.addItem("test6");
//		list.addItem("test7");
//		list.addItem("test8");
//		list.addItem("test9");
//		list.addItem("test10");
//		list.checkItem(1);
//		list.checkItem(0);
//		list.uncheckItem(0);
//		System.out.println(list);
//		list.save();
//		
//		Checklist list2 = new Checklist("data");
//		System.out.println(list);
		

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChecklistUI frame = new ChecklistUI(new Checklist());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
