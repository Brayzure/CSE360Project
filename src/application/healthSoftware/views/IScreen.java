package application.healthSoftware.views;

import javafx.scene.layout.Region;

public interface IScreen {
	public Region getLayout(); // Every Screen should have a layout we can show
	public void refreshData(); // Every Screen should define how its data gets set and reset
}
