package com.uz.laboratory.statistical.app;

import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.service.SpringFXMLLoader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;


public class PonabStatisticalReports extends Application {

    final static Logger logger = Logger.getLogger(PonabStatisticalReports.class);
    private static Stage stage;

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            logger.error("failed!", e);
        }
    }

    public static void restartMainStage() throws IOException {
        stage.setOnHidden(null);
        stage.hide();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring/application-context.xml");
        context.getBeanFactory().registerResolvableDependency(Stage.class, stage);
        stage.setTitle(Constants.MAIN_TITLE);
        stage.setScene(new Scene((Parent) context
                .getBean(SpringFXMLLoader.class)
                .load(Constants.MAIN_FXML_PATH), 945, 750));
        stage.setOnHidden(event -> {
            Platform.exit();
        });
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Platform.setImplicitExit(false);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring/application-context.xml");
        context.getBeanFactory().registerResolvableDependency(Stage.class, primaryStage);
        primaryStage.setTitle(Constants.MAIN_TITLE);
        primaryStage.setScene(new Scene((Parent) context
                .getBean(SpringFXMLLoader.class)
                .load(Constants.MAIN_FXML_PATH), 945, 750));
        primaryStage.setOnHidden(new EventHandler<WindowEvent>() {
                                     @Override
                                     public void handle(WindowEvent event) {
                                         Platform.exit();
                                     }
                                 }
        );
        stage = primaryStage;
        primaryStage.show();
    }
}
