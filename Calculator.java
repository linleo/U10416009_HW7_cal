//U10416009
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import java.util.*;

public class Calculator extends Application 
{
	TextField textField = new TextField("0");
	
	// Override the start method in the Application class
	@Override
	public void start(Stage primaryStage) 
	{
		Button button[] = new Button[28];
		String buttonName[] = {"MC", "MR", "MS", "M+", "M-",
							   "←", "CE", "C", "±", "√",
							   "7", "8", "9", "/", "%",
							   "4", "5", "6", "*", "1/x",
							   "1", "2", "3", "-",
							   "0", ".", "+", "="};
		
		//pane
		Pane pane = new Pane();
		
		//menu bar
		MenuBar menuBar = new MenuBar();
		menuBar.setPrefSize(270,10);
		Menu menuView = new Menu("View");
		Menu menuEdit = new Menu("Edit");
		Menu menuHelp = new Menu("Help");
		menuBar.getMenus().addAll(menuView, menuEdit, menuHelp);
		pane.getChildren().add(menuBar);

		//menu item
		MenuItem itemView_1 = new MenuItem("標準型");
		MenuItem itemView_2 = new MenuItem("工程型");
		MenuItem itemView_3 = new MenuItem("程式設計師");
		MenuItem itemView_4 = new MenuItem("統計資料\n");
		MenuItem itemView_5 = new MenuItem("歷程紀錄");
		MenuItem itemView_6 = new MenuItem("數字分位\n");
		MenuItem itemView_7 = new MenuItem("基本");
		MenuItem itemView_8 = new MenuItem("單位轉換");
		MenuItem itemView_9 = new MenuItem("日期換算");
		MenuItem itemView_10 = new MenuItem("工作表");
		
		MenuItem itemEdit_1 = new MenuItem("複製");
		MenuItem itemEdit_2 = new MenuItem("貼上");
		MenuItem itemEdit_3 = new MenuItem("歷程記錄");
		
		MenuItem itemHelp_1 = new MenuItem("檢視說明");
		MenuItem itemHelp_2 = new MenuItem("關於小算盤");
		
		menuView.getItems().addAll(itemView_1, itemView_2, itemView_3, itemView_4, itemView_5,
								   itemView_6, itemView_7, itemView_8, itemView_9, itemView_10);
		menuEdit.getItems().addAll(itemEdit_1, itemEdit_2, itemEdit_3);
		menuHelp.getItems().addAll(itemHelp_1, itemHelp_2);
		
		textField.setPrefSize(200, 20);
		textField.setLayoutX(35);
		textField.setLayoutY(45);
		textField.setEditable(false);
		textField.setAlignment(Pos.BASELINE_RIGHT);
		pane.getChildren().add(textField);
		
		//button 
		for (int i = 0; i < 20; i++)
		{
			button[i] = new Button(buttonName[i]);
			button[i].setPrefSize(40,40);
			if (i >= 0 && i < 5)
			{
				button[i].setLayoutX(10 + ((i%5)*50));
				button[i].setLayoutY(90);
			}
			else if (i >= 5 && i < 10)
			{
				button[i].setLayoutX(10 + ((i%5)*50));
				button[i].setLayoutY(140);
			}
			else if (i >= 10 && i < 15)
			{
				button[i].setLayoutX(10 + ((i%5)*50));
				button[i].setLayoutY(190);
			}
			else
			{
				button[i].setLayoutX(10 + ((i%5)*50));
				button[i].setLayoutY(240);
			}
			pane.getChildren().add(button[i]);
		}
		
		for (int i = 20; i <= 27; i++)
		{
			button[i] = new Button(buttonName[i]);
		}
		
		button[20].setPrefSize(40,40);
		button[20].setLayoutX(10);
		button[20].setLayoutY(290);
		
		button[21].setPrefSize(40,40);
		button[21].setLayoutX(60);
		button[21].setLayoutY(290);
		
		button[22].setPrefSize(40,40);
		button[22].setLayoutX(110);
		button[22].setLayoutY(290);
		
		button[23].setPrefSize(40,40);
		button[23].setLayoutX(160);
		button[23].setLayoutY(290);
		
		button[24].setPrefSize(90,40);
		button[24].setLayoutX(10);
		button[24].setLayoutY(340);
		
		button[25].setPrefSize(40,40);
		button[25].setLayoutX(110);
		button[25].setLayoutY(340);
		
		button[26].setPrefSize(40,40);
		button[26].setLayoutX(160);
		button[26].setLayoutY(340);
		
		button[27].setPrefSize(40,90);
		button[27].setLayoutX(210);
		button[27].setLayoutY(290);
		
		pane.getChildren().addAll(button[20], button[21], button[22], button[23], button[24], button[25], button[26], button[27]);
		
		Scene scene = new Scene(pane, 260, 380);
		primaryStage.setTitle("Calculator");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false); 
		primaryStage.show();
		
		for (int i = 0; i < 28; i++)
		{
			final int ii = i;
			button[i].setOnMouseClicked(e -> {
				calculate(buttonName, ii);
			});
		}
	}
	
	double num = 0, ans = 0, buf = 0;
	String text = "";
	boolean add = false, sub = false, mul = false, div = false, rewrite = true, fCEqual = true;
	ArrayList<Integer> numList = new ArrayList<>();
	void calculate(String buttonName[], int i)
	{
		if (buttonName[i].equals("C"))
		{
			textField.setText("0");
			num = 0;
			ans = 0;
			add = false;
			sub = false; 
			mul = false;
			div = false; 
			rewrite = true; 
			fCEqual = true;
		}
		else if (buttonName[i].equals("CE"))
		{
			textField.setText("0");
		}				
		else if (textField.getText().equals("0"))
		{
			textField.setText("");
			text = textField.getText() + buttonName[i]; 
			textField.setText(text);
		}				
		else if (buttonName[i].equals("←"))
		{
			if (textField.getText().length() == 1)
			{
				textField.setText("0");
			}
			else
			{
				text = textField.getText().substring(0,textField.getText().length()-1); 
				textField.setText(text);
			}
		}
		else if (buttonName[i].equals("±"))
		{
			if (textField.getText().charAt(0) == '-')
			{
				text = textField.getText().substring(1,textField.getText().length()); 
				textField.setText(text);
			}	
			else if (!textField.getText().equals("0"))
			{
				text = "-" + textField.getText();
				textField.setText(text);
			}
		}
		else if (buttonName[i].equals("√"))
		{
			num = Double.parseDouble(textField.getText());
			num = Math.sqrt(num);
			text = String.valueOf(num);
			textField.setText(text);		
		}
		else if (buttonName[i].equals("1/x"))
		{
			num = Double.parseDouble(textField.getText());
			num = 1 / num;
			text = String.valueOf(num);
			textField.setText(text);		
		}
		else if (buttonName[i].equals("+"))
		{
			num = Double.parseDouble(textField.getText());
			rewrite = true;
			fCEqual = true;
			add = true;
			sub = false;
			mul = false;
			div = false;
		}
		else if (buttonName[i].equals("-"))
		{
			num = Double.parseDouble(textField.getText());
			rewrite = true;
			fCEqual = true;
			sub = true;
			add = false;
			mul = false;
			div = false;
		}
		else if (buttonName[i].equals("*"))
		{
			num = Double.parseDouble(textField.getText());
			rewrite = true;
			fCEqual = true;
			mul = true;
			add = false;
			sub = false;
			div = false;
		}
		else if (buttonName[i].equals("/"))
		{
			num = Double.parseDouble(textField.getText());	
			rewrite = true;
			fCEqual = true;
			div = true;
			add = false;
			sub = false;
			mul = false;
		}
		else if (buttonName[i].equals("="))
		{
			if (add)
			{
				if (fCEqual)
				{
					ans = Double.parseDouble(textField.getText());	
					ans = num + ans;
					text = String.valueOf(ans);
					textField.setText(text);
					fCEqual = false;
				}
			}
			else if (sub)
			{
				if (fCEqual)
				{
					ans = Double.parseDouble(textField.getText());	
					ans = num - ans;
					text = String.valueOf(ans);
					textField.setText(text);
					fCEqual = false;
				}
			}
			else if (mul)
			{
				if (fCEqual)
				{
					ans = Double.parseDouble(textField.getText());	
					ans = num * ans;
					text = String.valueOf(ans);
					textField.setText(text);
					fCEqual = false;
				}
			}
			else if (div)
			{
				if (fCEqual)
				{
					ans = Double.parseDouble(textField.getText());	
					ans = num / ans;
					text = String.valueOf(ans);
					textField.setText(text);
					fCEqual = false;
				}
			}
		}
		else if (num != 0)
		{
			if (rewrite)
			{
				text = buttonName[i];
				textField.setText(text);
				rewrite = false;
			}
			else
			{
				text = textField.getText() + buttonName[i];
				textField.setText(text);
			}
		}
		else
		{
			text = textField.getText() + buttonName[i];
			textField.setText(text);
		}
	}
}
