package javafxtest;
 
import eduonix.osgi.democode.OsgiDemoService;
import javafx.stage.Stage;

public class StageServiceImpl implements OsgiDemoService{

	private final Stage m_stage;
	
	public StageServiceImpl(Stage stage) {
		m_stage = stage;
	}


	@Override
	public Stage getRootNode() {
		return m_stage;
	}
}
