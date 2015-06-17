package javafxtest;

import java.util.concurrent.Executors;

import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.DependencyManager;
import org.apache.felix.dm.annotation.api.Inject;
import org.apache.felix.dm.annotation.api.Start;
import org.apache.felix.dm.annotation.api.Stop;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import eduonix.osgi.democode.OsgiDemoService;


@Component
public class App extends Application {


	@Start
	public void startBundle() {

		Executors.defaultThreadFactory().newThread(() -> {
				Thread.currentThread().setContextClassLoader(
						this.getClass().getClassLoader());
				launch();
			}).start();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BundleContext bc = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		DependencyManager dm = new DependencyManager(bc);
		
		dm.add(dm.createComponent()
				.setInterface(OsgiDemoService.class.getName(), null)
				.setImplementation(new StageServiceImpl(primaryStage)));
	}

	@Stop
	public void stopBundle() {
		Platform.exit();
	}
}
