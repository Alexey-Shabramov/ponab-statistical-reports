package com.uz.laboratory.statistical.app;

import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.service.SpringFXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class PonabStatisticalReports extends Application {

    final static Logger logger = Logger.getLogger(PonabStatisticalReports.class);

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            logger.error("failed!", e);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring/application-context.xml");
        context.getBeanFactory().registerResolvableDependency(Stage.class, primaryStage);
        primaryStage.setTitle(Constants.MAIN_TITLE);
        primaryStage.setScene(new Scene((Parent) context
                .getBean(SpringFXMLLoader.class)
                .load(Constants.MAIN_FXML_PATH), 914, 600));
        primaryStage.show();
    }
}
