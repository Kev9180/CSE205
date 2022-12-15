// Assignment: 12
//       Name: 
//  StudentID: 
//    Lecture: 
//Description: The WaveControlPane class creates all the components, sets their
//			   handlers and listeners, and arranges them using layout panes.

//Comment out package statement before submitting
package application;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;

public class WaveControlPane extends Pane {
	
	//Private class variables
	private WaveDisplayPane wavePane;
	private int width, height;
	private Color color;
	private ColorPicker picker;
	
	//Buttons
	private Button startBtn;
	private Button stopBtn;
	private Button clearBtn;
	private Button defaultBtn;
	
	//Sliders
	private Slider speedSlider;
	private Slider widthSlider;
	private Slider heightSlider;
	
	//Labels
	private Label speedLabel;
	private Label widthLabel;
	private Label heightLabel;
	
	//Constructor to create all components, set their handler/listener,
	//and arrange them using layout panes.
	public WaveControlPane(int h, int w, Color initialColor) {
		this.color = initialColor;
		this.width = (int) (h * 0.68);
		this.height = w - 10;

		//Creates a pane to display waves with the specified color and sets the pane size
		wavePane = new WaveDisplayPane(width, color);
		wavePane.setMinSize(width, height);
		wavePane.setMaxSize(width, height);

		//Create a color picker with the specified initial color and set its size
		picker = new ColorPicker(color);
		picker.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		//Instantiate the 4 previously defined buttons
		startBtn = new Button("Start");
		stopBtn = new Button("Stop");
		clearBtn = new Button("Clear");
		defaultBtn = new Button("Default");
		
		//Set the max width of each button to the Double.MAX_VALUE constant
		startBtn.setMaxWidth(Double.MAX_VALUE);
		stopBtn.setMaxWidth(Double.MAX_VALUE);
		clearBtn.setMaxWidth(Double.MAX_VALUE);
		defaultBtn.setMaxWidth(Double.MAX_VALUE);

		//Add the buttons to the buttonPane, set size and alignment of buttonPane
		VBox buttonPane = new VBox(startBtn, stopBtn, clearBtn, defaultBtn, picker);
		buttonPane.setPrefSize(100, 100);
		buttonPane.setAlignment(Pos.CENTER);

		//Instantiate the 3 previously defined labels
		speedLabel = new Label("Speed");
		widthLabel = new Label("Width");
		heightLabel = new Label("Height");
		
		//Instantiate the 3 previously defined sliders
		speedSlider = new Slider(10, 50, 20);
		widthSlider = new Slider(20, 100, 50);
		heightSlider = new Slider(20, 100, 80);
		
		//Set orientation of sliders so they are vertical
		speedSlider.setOrientation(Orientation.VERTICAL);
		widthSlider.setOrientation(Orientation.VERTICAL);
		heightSlider.setOrientation(Orientation.VERTICAL);
		
		//Set up speed slider tick marks
		speedSlider.setMajorTickUnit(10);
		speedSlider.setMinorTickCount(5);
		speedSlider.setShowTickMarks(true);
		speedSlider.setShowTickLabels(true);
		
		//Set up width slider tick marks
		widthSlider.setMajorTickUnit(20);
		widthSlider.setMinorTickCount(5);
		widthSlider.setShowTickMarks(true);
		widthSlider.setShowTickLabels(true);
		
		//Set up height slider tick mars
		heightSlider.setMajorTickUnit(20);
		heightSlider.setMinorTickCount(5);
		heightSlider.setShowTickMarks(true);
		heightSlider.setShowTickLabels(true);
		
		//Add the sliders and labels to their respective VBoxes
		VBox speedSliderPane = new VBox(speedLabel, speedSlider);
		VBox waveLengthSliderPane = new VBox(widthLabel, widthSlider);
		VBox waveAmplitudeSliderPane = new VBox(heightLabel, heightSlider);

		//Set up a tile pane and add the sliders
		TilePane sliderPane = new TilePane();
		sliderPane.setPrefColumns(3);
		sliderPane.setPadding(new Insets(5, 5, 5, 5));
		sliderPane.setAlignment(Pos.CENTER);
		sliderPane.getChildren().addAll(speedSliderPane, waveLengthSliderPane, waveAmplitudeSliderPane);

		//Create and set up an HBox with the buttonPane and sliderPane added to it
		HBox controls = new HBox(buttonPane, sliderPane);
		controls.setAlignment(Pos.CENTER);

		//Create and set up a BorderPane
		BorderPane controlsAndWaves = new BorderPane();
		controlsAndWaves.setLeft(controls);
		controlsAndWaves.setCenter(wavePane);
		this.getChildren().add(controlsAndWaves);
		
		//Create button handlers for each of the 4 buttons
		ButtonHandler startHandler = new ButtonHandler();
		ButtonHandler stopHandler = new ButtonHandler();
		ButtonHandler clearHandler = new ButtonHandler();
		ButtonHandler defaultHandler = new ButtonHandler();
		
		//Register buttons with their respective handlers
		startBtn.setOnAction(startHandler);
		stopBtn.setOnAction(stopHandler);
		clearBtn.setOnAction(clearHandler);
		defaultBtn.setOnAction(defaultHandler);
		
		//Create slider handler for each of the 3 sliders
		speedSlider.valueProperty().addListener(new SpeedHandler());
		widthSlider.valueProperty().addListener(new WaveLengthHandler());
		heightSlider.valueProperty().addListener(new WaveAmplitudeHandler());
		
		//Create color handler for the color picker, and register picker with the color handler
		ColorHandler colorHandler = new ColorHandler();
		picker.setOnAction(colorHandler);
		
	}
	
	//ButtonHandler class handles the actions to be taken when each button is pressed
	private class ButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			
			//Start button was pressed
			if (e.getSource() == startBtn) {
				wavePane.resume();
			}
			
			//Stop button was pressed
			else if (e.getSource() == stopBtn) {
				wavePane.suspend();
			}
			
			//Clear button was pressed
			else if (e.getSource() == clearBtn) {
				wavePane.clearPane();
			}
			
			//Default button was pressed, set all properties to default values
			else {
				wavePane.suspend();
				wavePane.setWaveAmplitude(60);
				wavePane.setWaveLength(50);
				wavePane.setRate(30);
				wavePane.changeColor(Color.rgb(255, 0, 0));
				picker.setValue(Color.rgb(255, 0, 0));
				speedSlider.setValue(30);
				heightSlider.setValue(60);
				widthSlider.setValue(50);
				wavePane.resume();
			}
		}
	}
	
	//ColorHandler class changes the color of the wave to the color selected by the user
	private class ColorHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			color = picker.getValue();
			wavePane.changeColor(color);
		}
	}
	
	//SpeedHandler class handles adjusting the speed of the wave
	private class SpeedHandler implements ChangeListener<Number> {
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			wavePane.setRate((int) speedSlider.getValue());
		}
	}
	
	//WaveLengthHandler class handles adjusting the wavelength of the wave
	private class WaveLengthHandler implements ChangeListener<Number> {
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			wavePane.suspend();
			wavePane.setWaveLength((int) widthSlider.getValue());
		}
	}
	
	//WaveAmplitudeHandler class handles adjusting the wave amplitude of the wave
	private class WaveAmplitudeHandler implements ChangeListener<Number> {
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			wavePane.suspend();
			wavePane.setWaveAmplitude((int) heightSlider.getValue());
		}
	}
}
