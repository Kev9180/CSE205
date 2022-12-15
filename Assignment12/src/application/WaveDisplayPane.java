// Assignment: 12
//       Name:
//  StudentID: 
//    Lecture: 
//Description: The WaveDisplayPane class provides methods to instantiate a WaveDisplayPane
//			   and modify several properties of a wave.

//Comment out package statement before submitting
package application;

import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class WaveDisplayPane extends Pane {

	//Local variables - timeline
	private Timeline timeline;
	
	//Local variables - ints
	private int time;
	private int waveLength;
	private int waveAmplitude;
	private int paneWidth;
	
	//Local variables - color
	private Color color;
	
	//Constructor to instantiate a new WaveDisplayPane and set default values
	public WaveDisplayPane(int width, Color color) {
		this.waveAmplitude = 80;
		this.waveLength = 50;
		this.paneWidth = width;
		this.color = color;
		this.time = 0;
		super.setStyle("-fx-background-color: WHITE;");
		super.setStyle("-fx-border-color: BLACK;");
		
		//Instantiate a KeyFrame
		KeyFrame keyFrame = new KeyFrame(Duration.millis(500), new WaveHandler());
		
		//Instantiate timeline with the keyFrame
		timeline = new Timeline(keyFrame);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.setRate(20);
		timeline.play();

	}

	//Resume the animation
	public void resume() {
		timeline.play();
	}
	
	//Suspend the animation
	public void suspend() {
		timeline.stop();
		this.time = 0;
	}
	
	//Change the color of the wave
	public void changeColor(Color color) {
		this.color = color;
	}

	//Clear the pane
	public void clearPane() {
		super.getChildren().clear();
		suspend();
	}
	
	//Set the wavelength
	public void setWaveLength(int waveLength) {
		this.waveLength = waveLength; 
	}
	
	//Set the wave amplitude
	public void setWaveAmplitude(int waveAmplitude) {
		this.waveAmplitude = waveAmplitude;
	}
	
	//Set the rate (or "speed") of the wave
	public void setRate(int rate) {
		this.timeline.setRate(rate);
	}


	// defines an event listener to draw a new point
	private class WaveHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			time++;
			int x = (waveLength * time) / 314;
			int y = (int) (waveAmplitude * Math.sin((0.0174533) * time) + 115);

			if (x < paneWidth) {
				Circle dot = new Circle(x, y, 2);
				dot.setFill(color);
				getChildren().add(dot);
			} else suspend();
		}
	}
}
