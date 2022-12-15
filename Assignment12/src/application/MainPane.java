// Assignment: 12
//       Name:
//  StudentID:
//    Lecture:
//Description: The MainPane class sets up the main pane.

//Comment out package statement before submitting
package application;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MainPane extends Pane {

	// The constructor creates two panes with waves and movement controls
	// Initial colors for the waves are BLACK and PINK
	// The two panes are arranged in a GridPane
	public MainPane(int width, int height) {
		WaveControlPane[] wavePane;
		wavePane = new WaveControlPane[2];
		wavePane[0] = new WaveControlPane(width, height / 2 - 5, Color.BLACK);
		wavePane[1] = new WaveControlPane(width, height / 2 - 5, Color.PINK);

		GridPane pane1 = new GridPane();
		pane1.setPadding(new Insets(10, 10, 10, 10));
		pane1.setVgap(10);
		pane1.add(wavePane[0], 0, 0);
		pane1.add(wavePane[1], 0, 1);

		this.getChildren().add(pane1);
	}
}
